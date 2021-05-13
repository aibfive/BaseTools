package com.aibfive.sample.network

import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.ui.mvvm.BannerBean
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Date : 2020/11/9/009
 * Time : 9:47
 * author : Li
 */
interface ApiService {

    // 文章列表
    @GET(HostUrl.GET_DATA)
    suspend fun getArticlesSuspend(@Path("page") page: Int): BaseBean<ArticleBean>

    // Banner数据
    @GET(HostUrl.GET_BANNER_DATA)
    suspend fun getBannerDataSuspend(): BaseBean<BannerBean>

    // Banner数据
    @GET(HostUrl.GET_BANNER_DATA)
    fun getBannerData(): Deferred<BaseBean<BannerBean>>

}