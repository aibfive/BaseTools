package com.aibfive.sample.network

import com.aibfive.sample.bean.ArticleBean
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * Date : 2020/11/9/009
 * Time : 9:47
 * author : Li
 */
interface ApiService {

    // 文章列表
    @GET("article/list/{page}/json1")
    suspend fun getArticlesSuspend(@Path("page") page: Int): BaseBean<ArticleBean>

    // 文章列表
    @GET("article/list/{page}/json")
    suspend fun getArticlesSuspend1(@Path("page") page: Int): BaseBean<String>


}