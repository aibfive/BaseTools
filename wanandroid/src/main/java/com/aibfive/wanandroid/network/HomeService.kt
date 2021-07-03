package com.aibfive.wanandroid.network

import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.ui.base.PageModel
import com.aibfive.wanandroid.ui.home.ArticleModel
import com.aibfive.wanandroid.ui.home.BannerModel
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET(HostUrl.GET_BANNER)
    suspend fun getBanner() : BaseModel<List<BannerModel>>

    @GET(HostUrl.GET_HOME_ARTICLE)
    suspend fun getHomeArticle(@Path("page") page : Int) : BaseModel<PageModel<ArticleModel>>
}