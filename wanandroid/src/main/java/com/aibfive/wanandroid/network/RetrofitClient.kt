package com.aibfive.wanandroid.network

import com.aibfive.basetools.util.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val retrofit : Retrofit by lazy{
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LogUtil.v(RetrofitClient::class.simpleName, message)
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .addInterceptor(CookieInterceptor())//头部拦截器
                .callTimeout(30, TimeUnit.SECONDS)//设置调用超时时间为30秒
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间为30秒
                .readTimeout(60, TimeUnit.SECONDS)//阅读超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//写入超时时间
                .build()
        LogUtil.v(RetrofitClient::class.simpleName, "我创建了")
        Retrofit.Builder()
                .baseUrl(HostUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    inline fun <reified T> getService() = retrofit.create(T::class.java)

}