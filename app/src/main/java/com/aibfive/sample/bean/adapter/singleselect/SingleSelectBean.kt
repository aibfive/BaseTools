package com.aibfive.sample.bean.adapter.singleselect

import com.aibfive.basetools.adapter.select.SelectItemEntity

/**
 * Date : 2020/10/27/027
 * Time : 14:33
 * author : Li
 */
data class SingleSelectBean(var id : Int, var name: String) : SelectItemEntity{

    override var select: Boolean
        get() = false
        set(value) {
            select = value
        }

    override var tag: Any?
        get() = id
        set(value) {
            tag = value
        }

}