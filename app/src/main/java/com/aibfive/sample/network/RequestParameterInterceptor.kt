package com.aibfive.sample.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Date : 2020/12/7/007
 * Time : 15:52
 * author : Li
 */
class RequestParameterInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}