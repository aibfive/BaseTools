package com.aibfive.sample.ui.tencentmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.L
import com.aibfive.sample.R
import com.aibfive.sample.adapter.SearchAddressAdapter
import com.aibfive.sample.bean.tencentmap.AddressBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tencent.lbssearch.TencentSearch
import com.tencent.lbssearch.`object`.param.Geo2AddressParam
import com.tencent.lbssearch.`object`.param.SuggestionParam
import com.tencent.lbssearch.`object`.result.Geo2AddressResultObject
import com.tencent.lbssearch.`object`.result.SuggestionResultObject
import com.tencent.lbssearch.httpresponse.BaseObject
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationRequest
import com.tencent.map.tools.net.http.HttpResponseListener
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_search_address.*

//https://lbs.qq.com/geosdk/doc/com/tencent/map/geolocation/package-summary.html
class SearchAddressActivity : BaseActivity(), TencentLocationListener, BaseQuickAdapter.OnItemClickListener {

    lateinit var addressAdapter: SearchAddressAdapter
    lateinit var tencentSearch : TencentSearch
    var locationManager : TencentLocationManager? = null;
    var locationRequest : TencentLocationRequest? = null;
    var city : String = ""

    companion object {
        private const val TAG = "SearchAddressActivity"
        private const val RADIUS = 3000//搜索范围半径
        private const val INTERVAL = 1000L//定位周期1秒

        fun start(context: Context){
            val intent = Intent(context, SearchAddressActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocation()
        tencentSearch = TencentSearch(this)
        addressAdapter = SearchAddressAdapter()
        recyclerView.adapter = addressAdapter
        addressAdapter.setOnItemClickListener(this)
        startLocation()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search_address
    }

    /**
     * 定位的一些初始化设置
     */
    private fun initLocation(){
        locationManager = TencentLocationManager.getInstance(this)
        //定位请求, 客户端使用本类指定定位周期等参数.
        locationRequest = TencentLocationRequest.create();
        //设置定位周期(位置监听器回调周期), 单位为 ms (毫秒).
        locationRequest?.setInterval(INTERVAL)
        //定位结果信息级别: 1号定位接口, 包含经纬度, 位置名称, 位置地址
        // TODO: 2020/10/9 但这里设置无效，定位成功后onLocationChanged的TencentLocation的city还是null值。
        locationRequest?.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME)
    }

    /**
     * 发起定位
     */
    private fun startLocation(){
        //TencentLocationRequest传null即可，即使传入一个自己配置的TencentLocationRequest，单点定位还是会按默认设置返回结果。
        //TencentLocationManager.getInstance(this).requestSingleFreshLocation(null, this, Looper.getMainLooper())
        //这里还是使用周期定位，如果单次定位失败，可执行至定位成功
        if(locationManager != null && locationRequest != null) {
            locationManager?.requestLocationUpdates(locationRequest, this, Looper.myLooper())
        }
    }

    /**
     * 停止定位
     */
    private fun stopLocation(){
        //停止定位，释放资源
        if(locationManager != null) {
            locationManager?.removeUpdates(this)
        }
        locationManager = null
        locationRequest = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocation()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }


    /**
     * TencentLocationListener
     * 腾讯定位SDK位置变化回调
     */
    override fun onLocationChanged(tencentLocation: TencentLocation?, i: Int, s: String?) {
        L.i(TAG, "位置变化回调经纬度：" + i + "," + tencentLocation!!.latitude + "," + tencentLocation!!.longitude + "," + tencentLocation!!.city)
        if(i == TencentLocation.ERROR_OK){
            if(tencentLocation != null) {
                city = tencentLocation!!.city
                stopLocation()//停止定位
                geo2address(tencentLocation.latitude, tencentLocation.longitude)//搜索当前定位周围的地址列表
            }
        }
    }

    /**
     * TencentLocationListener
     * GPS, WiFi, Radio 等状态发生变化
     * @param s
     * @param i
     * @param s1
     */
    override fun onStatusUpdate(s: String?, p1: Int, s1: String?) {
        L.i(TAG, s + "：s----s1：" + s1)
    }

    /**
     * 根据关键字搜索地址
     */
    private fun searchAddress(keyword : String){
        /*val nearby = SearchParam.Nearby()
        val searchParam = SearchParam()
        //设置搜索关键字
        searchParam.keyword(keyword)
        searchParam.boundary(nearby)*/

        tencentSearch.suggestion(SuggestionParam(keyword, city), object : HttpResponseListener<BaseObject>{
            /**
             * HttpResponseListener<BaseObject>
             * poi检索成功
             * @param i
             * @param baseObject
             */
            override fun onSuccess(i: Int, baseObject: BaseObject?) {
                if(baseObject == null){
                    return
                }
                val obj = baseObject as SuggestionResultObject
                if(obj.data == null || obj.data.size == 0){
                    return
                }
                val list = ArrayList<AddressBean>()
                for(item in obj.data){
                    L.i(TAG, "poi检索成功：title:" + item.title + ";" + item.address)
                    list.add(AddressBean(item.title, item.address, item.latLng.latitude, item.latLng.longitude))
                }
                addressAdapter.setNewData(list)
            }

            /**
             * HttpResponseListener<BaseObject>
             * poi检索失败
             * @param i
             * @param s
             * @param throwable
             */
            override fun onFailure(i: Int, s: String?, throwable: Throwable?) {
                L.i(TAG, "poi检索失败："+s)
            }
        })
    }

    /**
     * poi检索--搜索附近地址
     */
    private fun geo2address(latitude : Double, longitude : Double){
        //圆形范围搜索
        val latLng = LatLng(latitude, longitude)
        val geo2AddressParam = Geo2AddressParam(latLng)
        val poiOptions = Geo2AddressParam.PoiOptions()
        //设置搜索半径--3000
        poiOptions.setRadius(RADIUS)
        //位置共享场景策略，用户经常用于发送位置、位置分享等场景的热门地点优先排序
        poiOptions.setPolicy(Geo2AddressParam.PoiOptions.POLICY_SHARE)
        //是否返回周边POI列表： true.返回；false 不返回(默认)
        geo2AddressParam.getPoi(true)
        //设置poi的设置选项
        geo2AddressParam.setPoiOptions(poiOptions)
        //逆地址解析。
        tencentSearch.geo2address(geo2AddressParam, object : HttpResponseListener<BaseObject> {
            /**
             * HttpResponseListener<BaseObject>
             * poi检索成功
             * @param i
             * @param baseObject
             */
            override fun onSuccess(i: Int, baseObject: BaseObject?) {
                if(baseObject == null){
                    return
                }
                val obj = baseObject as Geo2AddressResultObject
                if(obj.result == null || obj.result.pois.size == 0){
                    return
                }
                val list = ArrayList<AddressBean>()
                for(poi in obj.result.pois){
                    L.i(TAG, "poi检索成功：title:" + poi.title + ";" + poi.address)
                    list.add(AddressBean(poi.title, poi.address, poi.latLng.latitude, poi.latLng.longitude))
                }
                addressAdapter.setNewData(list)
            }

            /**
             * HttpResponseListener<BaseObject>
             * poi检索失败
             * @param i
             * @param s
             * @param throwable
             */
            override fun onFailure(i: Int, s: String?, throwable: Throwable?) {
                L.i(TAG, "poi检索失败："+s)
            }
        })
    }



    /**
     * 关闭页面
     */
    fun onBackClick(view : View?){
        finish()
    }

    /**
     * 搜索页面
     */
    fun onSearchClick(view : View?){
        if(searchEt.length() == 0){
            Toast.makeText(this, getString(R.string.hint_input_keyword), Toast.LENGTH_SHORT).show()
        }
        searchAddress(searchEt.text.toString())
    }

    /**
     * 选择并确定位置
     */
    fun onDetermineClick(view : View?){
        val bean = addressAdapter.selectedItem
        if(bean == null){
            L.i(TAG,"请选择地址")
            return
        }
        L.i(TAG,"选择地址：title:" + bean.title + ";" + bean.address)
        Toast.makeText(this, "选择地址：title:" + bean.title + ";" + bean.address, Toast.LENGTH_LONG).show()
    }
}