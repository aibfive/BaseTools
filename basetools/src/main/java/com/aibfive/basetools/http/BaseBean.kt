package com.aibfive.basetools.http

/**
 * Date : 2020/11/9/009
 * Time : 10:00
 * author : Li
 */
data class BaseBean<T> (
    val _t: String,
    val data: T,
    val info: String,
    val status: Int,
    val time: Int

)