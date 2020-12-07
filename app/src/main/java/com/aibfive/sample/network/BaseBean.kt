package com.aibfive.sample.network

/**
 * Date : 2020/12/7/007
 * Time : 15:56
 * author : Li
 */
data class BaseBean<T>(
        val data : T,
        val errorCode : Int,
        val errorMsg : String
)