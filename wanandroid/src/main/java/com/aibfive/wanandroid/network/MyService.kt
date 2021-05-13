package com.aibfive.wanandroid.network

import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.ui.my.UserInfoModel
import retrofit2.http.GET

/**
 * @author: 小李
 * @date: 2021/5/11 21:40
 */
interface MyService {

    /**
     * 获取用户信息
     */
    @GET(HostUrl.GET_USER_INFO)
    suspend fun getUserInfo() : BaseModel<UserInfoModel>
}