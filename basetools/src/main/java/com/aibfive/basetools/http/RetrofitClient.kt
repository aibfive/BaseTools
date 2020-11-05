package com.aibfive.basetools.http

import com.aibfive.basetools.util.LogUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

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
                .addInterceptor(httpLoggingInterceptor)
                .build()
        return okHttpClient

    }

    /*fun getApiService(cls : Class){
        getInstance()?.create(DemoService::class.java)
    }*/


}