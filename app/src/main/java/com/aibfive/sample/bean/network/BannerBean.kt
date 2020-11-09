package com.aibfive.sample.bean.network

/**
 * Date : 2020/11/9/009
 * Time : 10:02
 * author : Li
 */
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)