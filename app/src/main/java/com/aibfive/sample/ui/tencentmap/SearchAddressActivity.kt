package com.aibfive.sample.ui.tencentmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.aibfive.basetools.util.L
import com.aibfive.sample.R
import com.aibfive.sample.adapter.SearchAddressAdapter
import com.aibfive.sample.bean.tencentmap.AddressBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tencent.lbssearch.TencentSearch
import com.tencent.lbssearch.`object`.param.Geo2AddressParam
import com.tencent.lbssearch.`object`.param.SearchParam
import com.tencent.lbssearch.`object`.result.Geo2AddressResultObject
import com.tencent.lbssearch.`object`.result.SearchResultObject
import com.tencent.lbssearch.httpresponse.BaseObject
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.tools.net.http.HttpResponseListener
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_search_address.*

class SearchAddressActivity : AppCompatActivity(), TencentLocationListener, BaseQuickAdapter.OnItemClickListener {

    lateinit var addressAdapter: SearchAddressAdapter
    lateinit var tencentSearch : TencentSearch

    companion object {
        private const val TAG = "SendLocationActivity"
        private const val RADIUS = 3000//搜索范围半径

        fun start(context: Context){
            val intent = Intent(context, SearchAddressActivity::class.java)
            context.startActivity(intent)
        }
    }

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_address)
        tencentSearch = TencentSearch(this)
        addressAdapter = SearchAddressAdapter()
        recyclerView.adapter = addressAdapter
        addressAdapter.setOnItemClickListener(this)
        startLocation()
    }

    /**
     * 发起单点定位
     * TencentLocationRequest传null即可，即使传入一个自己配置的TencentLocationRequest，单点定位还是会按默认设置返回结果。
     */
    private fun startLocation(){
        TencentLocationManager.getInstance(this).requestSingleFreshLocation(null, this, Looper.getMainLooper())
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

    /**
     * TencentLocationListener
     * 腾讯定位SDK位置变化回调
     */
    override fun onLocationChanged(tencentLocation: TencentLocation?, i: Int, s: String?) {
        L.i(TAG, "位置变化回调经纬度：" + i + "," + tencentLocation!!.latitude + "," + tencentLocation.longitude)
        if(i == TencentLocation.ERROR_OK){
            geo2address(tencentLocation.latitude, tencentLocation.longitude)
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
        //圆形范围搜索
        val nearby = SearchParam.Nearby()
        //设置搜索半径--3000
        nearby.r(RADIUS)
        val searchParam = SearchParam()
        //设置搜索关键字
        searchParam.keyword(keyword)
        searchParam.boundary(nearby)
        tencentSearch.search(searchParam, object : HttpResponseListener<BaseObject>{
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
                val obj = baseObject as SearchResultObject
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

    }
}