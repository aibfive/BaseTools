package com.aibfive.sample.ui.tencentmap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aibfive.basetools.adapter.itemdecoration.LinearItemDecoration;
import com.aibfive.basetools.util.DisplayUtil;
import com.aibfive.basetools.util.L;
import com.aibfive.sample.R;
import com.aibfive.sample.adapter.SearchAddressAdapter;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.param.SearchParam;
import com.tencent.lbssearch.object.result.SearchResultObject;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationManagerOptions;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TextureMapView;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/**
 * 发送定位
 */
public class SendLocationActivity extends AppCompatActivity implements LocationSource, TencentLocationListener, TencentMap.OnMyLocationClickListener, TencentMap.OnCameraChangeListener, HttpResponseListener<BaseObject> {

    private final String TAG = "SendLocationActivity";

    private RecyclerView recyclerView;
    private TencentMap tencentMap;
    private Location location;
    private TencentSearch tencentSearch;
    private TencentLocationManager locationManager;
    private TencentLocationRequest locationRequest;
    private OnLocationChangedListener locationChangedListener;

    private SearchAddressAdapter addressAdapter;

    private final int zoomLevel = 19;//缩放等级
    private final int radius = 3000;//搜索范围半径

    public static void start(Context context) {
        Intent starter = new Intent(context, SendLocationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);
        initSearch();
        initLocation();
        initView();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new LinearItemDecoration(
                DisplayUtil.dip2px(this, 0.5f), ContextCompat.getColor(this, R.color.colorF5F5F5),
                LinearItemDecoration.VERTICAL, LinearItemDecoration.VERTICAL_INCLUDE_BOTTOM));
        recyclerView.setAdapter(addressAdapter = new SearchAddressAdapter());
        SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_frag);
        tencentMap = supportMapFragment.getMap();
        //地图上设置定位数据源
        tencentMap.setLocationSource(this);
        //设置当前位置可见
        tencentMap.setMyLocationEnabled(true);
        //SDK版本4.3.5新增内置定位标点击回调监听
        tencentMap.setMyLocationClickListener(this);
        //监测地图画面的移动
        tencentMap.setOnCameraChangeListener(this);
    }

    /**
     * 定位的一些初始化设置
     */
    private void initLocation(){
        //用于访问腾讯定位服务的类, 周期性向客户端提供位置更新
        locationManager = TencentLocationManager.getInstance(this);
        //创建定位请求
        locationRequest = TencentLocationRequest.create();
        //设置定位周期（位置监听器回调周期）为3s
        locationRequest.setInterval(300000);
    }

    /**
     * poi检索初始化
     */
    private void initSearch(){
        tencentSearch = new TencentSearch(this);
    }

    /**
     * poi检索--搜索附近地址
     */
    private void searchPoi(double latitude, double longitude){
        //圆形范围搜索
        LatLng latLng = new LatLng(latitude, longitude);
        /*SearchParam.Nearby nearBy = new SearchParam.Nearby(latLng, radius);
        SearchParam searchParam = new SearchParam();
        searchParam.boundary(nearBy);*/

        SearchParam.Region region = new SearchParam.Region();
        region.poi("广州");
        region.autoExtend(false);//设置搜索范围不扩大
        region.center(latLng);
        SearchParam searchParam = new SearchParam();
        searchParam.boundary(region);
        searchParam.keyword("ktv");
        tencentSearch.search(searchParam, this);
    }

    /**
     * HttpResponseListener<BaseObject>
     * poi检索成功
     * @param i
     * @param baseObject
     */
    @Override
    public void onSuccess(int i, BaseObject baseObject) {
        if (baseObject == null) {
            return;
        }
        SearchResultObject obj = (SearchResultObject) baseObject;
        if(obj.data == null){
            return;
        }
        tencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(obj.data.get(0).latLng,15f, 0, 0)));
        for(SearchResultObject.SearchResultData data : obj.data){
            L.i(TAG,"title:"+data.title + ";" + data.address);
        }
        addressAdapter.setNewData(obj.data);
    }

    /**
     * HttpResponseListener<BaseObject>
     * poi检索失败
     * @param i
     * @param s
     * @param throwable
     */
    @Override
    public void onFailure(int i, String s, Throwable throwable) {
        L.i(TAG, "poi检索失败："+s);
    }

    /**
     * TencentMap.OnCameraChangeListener
     * 地图视图改变回调
     * @param cameraPosition
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        L.i(TAG, "视图改变回调经纬度："+cameraPosition.target.latitude+","+cameraPosition.target.longitude+";zoom："+cameraPosition.zoom);
    }

    /**
     * TencentMap.OnCameraChangeListener
     * 地图视图改变结束回调
     * @param cameraPosition
     */
    @Override
    public void onCameraChangeFinished(CameraPosition cameraPosition) {
        if(cameraPosition == null || cameraPosition.target == null){
            return;
        }
        L.i(TAG, "视图改变结束回调经纬度："+cameraPosition.target.latitude+","+cameraPosition.target.longitude+";zoom："+cameraPosition.zoom);
        searchPoi(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }

    /**
     * TencentMap.OnMyLocationClickListener
     * 内置定位标点击回调
     * @param latLng
     * @return
     */
    @Override
    public boolean onMyLocationClicked(LatLng latLng) {
        return false;
    }

    /**
     * TencentLocationListener
     * 腾讯定位SDK位置变化回调
     */
    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        L.i(TAG, "位置变化回调经纬度："+i+","+tencentLocation.getLatitude()+","+tencentLocation.getLongitude());
        //其中 locationChangeListener 为 LocationSource.active 返回给用户的位置监听器
        //用户通过这个监听器就可以设置地图的定位点位置
        if(i == TencentLocation.ERROR_OK && locationChangedListener != null){
            location = new Location(tencentLocation.getProvider());
            //设置经纬度
            location.setLatitude(tencentLocation.getLatitude());
            location.setLongitude(tencentLocation.getLongitude());
            //设置精度，这个值会被设置为定位点上表示精度的圆形半径
            location.setAccuracy(tencentLocation.getAccuracy());
            //设置定位标的旋转角度，注意 tencentLocation.getBearing() 只有在 gps 时才有可能获取
            location.setBearing(tencentLocation.getBearing());
            //将位置信息返回给地图
            locationChangedListener.onLocationChanged(location);

            //定位成功后，停止定位，这里只定位一次。
        }
    }

    /**
     * LocationSource
     * @param onLocationChangedListener
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        //这里我们将地图返回的位置监听保存为当前 Activity 的成员变量
        locationChangedListener = onLocationChangedListener;
        int err = locationManager.requestLocationUpdates(
                locationRequest, this, Looper.myLooper());
        switch (err) {
            case 1:
                L.i(TAG, "设备缺少使用腾讯定位服务需要的基本条件");
                break;
            case 2:
                L.i(TAG, "manifest 中配置的 key 不正确");
                break;
            case 3:
                L.i(TAG, "自动加载libtencentloc.so失败");
                break;
            default:
                L.i(TAG, "err："+err);
                break;
        }
    }

    /**
     * TencentLocationListener
     * GPS, WiFi, Radio 等状态发生变化
     * @param s
     * @param i
     * @param s1
     */
    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        L.i(TAG, s + "：s----s1：" + s1);
    }

    /**
     * LocationSource
     */
    @Override
    public void deactivate() {
        //当不需要展示定位点时，需要停止定位并释放相关资源
        locationManager.removeUpdates(this);
        locationManager = null;
        locationRequest = null;
        locationChangedListener = null;
    }

    /**
     * 定位
     * @param view
     */
    public void onLocationClick(View view){
        if(location != null){
            updateLocationCamera(location.getLatitude(), location.getLongitude());
        }
    }

    /**
     * 以动画的方式把地图变换到指定的状态（位置，缩放等级，偏离，旋转）
     * @param latitude
     * @param longitude
     */
    private void updateLocationCamera(double latitude, double longitude) {
        tencentMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude, longitude), zoomLevel, 0, 0)));
    }
}