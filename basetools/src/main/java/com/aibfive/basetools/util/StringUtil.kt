package com.aibfive.basetools.util

import com.aibfive.basetools.BaseApplication

object StringUtil {

    fun getString(resId : Int) : String{
        return BaseApplication.instance.getString(resId)
    }

}