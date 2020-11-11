package com.aibfive.sample.network

import com.aibfive.sample.bean.network.BannerBean
import com.aibfive.sample.bean.network.BarInfoBean
import com.aibfive.basetools.http.BaseBean
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

/**
 * Date : 2020/11/9/009
 * Time : 9:47
 * author : Li
 */
interface ApiService {

    @GET(HostUrl.GET_BANNER)
    fun getBanner() : Call<BaseBean<BannerBean>>

    @GET(HostUrl.GET_COLLECT_LIST)
    fun getCollectList() : Call<BaseBean<Any>>

    @POST(HostUrl.GET_BAR_INFO)
    @FormUrlEncoded
    fun getBarIndex(
            @Field("bar_id") bar_id : Int
    ) : Call<BaseBean<BarInfoBean>>

    @POST(HostUrl.GET_BAR_INFO)
    @FormUrlEncoded
    fun getBarIndexDeferred(
            @Field("bar_id") bar_id : Int
    ) : Deferred<BaseBean<BarInfoBean>>



}