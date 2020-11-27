package com.aibfive.basetools.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Date : 2020/11/27/027
 * Time : 16:07
 * author : Li
 */
object ScreenUtil {

    fun getScreenWidth(context : Context?) : Int{
        val windowManager : WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context : Context?) : Int{
        val windowManager : WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}