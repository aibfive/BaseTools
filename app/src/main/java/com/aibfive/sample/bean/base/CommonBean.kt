package com.aibfive.sample.bean.base

import com.aibfive.basetools.adapter.select.SelectItemEntity

/**
 * Date : 2020/10/27/027
 * Time : 18:41
 * author : Li
 */
data class CommonBean constructor(var name : String, var id : Int) : SelectItemEntity {

    override var select: Boolean
        get() = false
        set(value) {}

    override var tag: Any?
        get() = id
        set(value) {}
}