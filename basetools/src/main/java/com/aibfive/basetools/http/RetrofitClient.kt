package com.aibfive.basetools.http

import com.aibfive.basetools.util.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Date : 2020/11/5/005
 * Time : 17:23
 * author : Li
 */
object RetrofitClient {

    var client : Retrofit? = null

    fun getInstance() : Retrofit? {
        if(client == null){
            synchronized(RetrofitClient::class.java){
                if(client == null){
                    client = getRetrofit("")
                }
            }
        }
        return client
    }

    /**
     * 获取Retrofit实例
     *
     */
    private fun getRetrofit(baseUrl : String) : Retrofit{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
    }

    private fun getOkHttpClient() : OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                LogUtil.v(RetrofitClient::class.simpleName, message)
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                //添加日志拦截
                .addInterceptor(httpLoggingInterceptor)
                //连接超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //阅读超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
        return okHttpClient

    }

    fun <T> getAPIService(service: Class<T>?): T {
        return getInstance()!!.create(service)
    }

    /*fun getApiService(cls : Class){
        getInstance()?.create(DemoService::class.java)
    }*/


}