package com.aibfive.basetools.http

import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.basetools.util.LogUtil
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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

    const val TAG = "RetrofitClient"

    var client : Retrofit? = null
    var baseUrl : String? = null

    private fun getInstance() : Retrofit? {
        if(client == null){
            synchronized(RetrofitClient::class.java){
                if(client == null){
                    client = getRetrofit(baseUrl!!)
                }
            }
        }
        return client
    }

    /**
     * 初始化
     */
    fun init(baseUrl: String){
        this.baseUrl = baseUrl
    }

    /**
     * 获取Retrofit实例
     *
     */
    private fun getRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(getOkHttpClient())
                .build()
    }

    private fun getOkHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                LogUtil.json(TAG, message)
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                //添加头文件的数据字段拦截器
                .addInterceptor(RequestParameterInterceptor())
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                //连接超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //阅读超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 获取接口类
     */
    fun <T> getAPIService(service : Class<T>) : T {
        return getInstance()!!.create(service)
    }

    fun <T> launchUI(callback: ResponseCallback<T>){

    }

}