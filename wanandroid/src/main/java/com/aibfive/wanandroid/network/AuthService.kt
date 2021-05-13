package com.aibfive.wanandroid.network

import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.base.UserLoginModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 确认密码
     */
    @POST(HostUrl.REGISTER)
    @FormUrlEncoded
    suspend fun register(
            @Field("username") username : String,
            @Field("password") password : String,
            @Field("repassword") repassword : String,
    ) : BaseModel<UserLoginModel>

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    @POST(HostUrl.LOGIN)
    @FormUrlEncoded
    suspend fun login(
            @Field("username") username : String,
            @Field("password") password : String
    ) : BaseModel<UserLoginModel>

}