package com.aibfive.basetools.mvp

/**
 * Date : 2020/12/8/008
 * Time : 18:01
 * author : Li
 */
interface ResponseCallback<T> {

    fun onSuccess(data : T?)

    fun onFail(code : String, msg : String)

}