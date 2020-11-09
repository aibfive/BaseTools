package com.aibfive.basetools.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Date : 2020/11/9/009
 * Time : 16:40
 * author : Li
 */
abstract class DefaultCallback<T> : Callback<BaseBean<T>> {

    companion object {
        const val TYPE_SERVER_ERROR = -1//服务器错误
        const val TYPE_NETWORK_ERROR = -2;//网络错误
        const val TYPE_OTHER_ERROR = -3;//其他错误
    }

    override fun onFailure(call: Call<BaseBean<T>>, t: Throwable) {
        try {
            if (t is ConnectException || t is UnknownHostException) {
                onFail(TYPE_NETWORK_ERROR, "网络错误，请检查本地网络")
            } else {
                onFail(TYPE_OTHER_ERROR, t.toString())
            }
        } catch (e: Exception) {
            onFail(TYPE_OTHER_ERROR, e.toString())
        }
    }

    override fun onResponse(call: Call<BaseBean<T>>, response: Response<BaseBean<T>>) {
        val data = response.body()
        try {
            if(data == null){
                onFail(TYPE_SERVER_ERROR, "服务器执行错误，请重试")
            }else{
                if(data.status == 1) {
                    onSuccess(data.data)
                }else{
                    onFail(data.status, data.info)
                }
            }
        } catch (e: Exception) {
            onFail(TYPE_OTHER_ERROR, e.toString())
        }
    }

    open abstract fun onFail(code : Int, error : String)

    open abstract fun onSuccess(data : T)
}