package com.aibfive.basetools.http

import android.util.Log
import com.aibfive.basetools.util.LogUtil
import com.aibfive.basetools.util.MD5Util
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

/**
 * 头文件的数据字段拦截器
 * Date : 2020/11/9/009
 * Time : 13:43
 * author : Li
 */
class RequestParameterInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val jsonObject = JSONObject()
        val request = chain.request()
        val requestBody = request.body()
        val requestBuilder = request.newBuilder()
        if(requestBody is FormBody){
            for(i in 0 until requestBody.size()){
                jsonObject.put(requestBody.name(i), requestBody.value(i))
            }
        }
        jsonObject.put("user_id", "4")
        jsonObject.put("token", "cb40dc4bee52acd0f3fbe28d66c24cb7")

        LogUtil.json(RetrofitClient.TAG, request.url().toString() + "\n" + jsonObject.toString())

        val formBodyBuilder = FormBody.Builder()
        formBodyBuilder.add("data", jsonObject.toString())
        formBodyBuilder.add("apisign", MD5Util.ToMD5(
                "1c8f9b0e0c128d29166b7aeb08a1ed79", jsonObject.toString()))
        val formBody = formBodyBuilder.build()
        val newRequest = requestBuilder.post(formBody).build()
        return chain.proceed(newRequest)
    }

}