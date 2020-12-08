package com.aibfive.sample.network

import com.aibfive.basetools.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Date : 2020/12/7/007
 * Time : 15:37
 * author : Li
 */
object RetrofitClient {

    private val retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val httpLoggingInterceptor = HttpLoggingInterceptor{
            LogUtil.v(RetrofitClient::class.simpleName, it)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
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
        Retrofit.Builder()
                .baseUrl(HostUrl.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    fun getApiService() : ApiService{
        return retrofit.create(ApiService::class.java)
    }

}