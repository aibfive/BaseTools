package com.aibfive.wanandroid.network

import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.ui.home.BannerModel
import retrofit2.http.GET

interface HomeService {

    @GET(HostUrl.GET_BANNER)
    suspend fun getBanner() : BaseModel<List<BannerModel>>

}