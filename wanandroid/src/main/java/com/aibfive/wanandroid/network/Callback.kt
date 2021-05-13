package com.aibfive.wanandroid.network

interface Callback<T> {

    fun onSuccess(data : T)

    fun onFailed(errorCode : String, errorMsg : String)

}