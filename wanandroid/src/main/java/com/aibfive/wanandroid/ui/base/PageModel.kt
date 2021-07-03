package com.aibfive.wanandroid.ui.base

/**
 * @author: 小李
 * @date: 2021/7/1 21:00
 */
data class PageModel<T>(
    val curPage: Int,
    val datas: List<T>?,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
