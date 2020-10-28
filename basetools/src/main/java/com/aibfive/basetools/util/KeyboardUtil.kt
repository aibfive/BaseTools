package com.aibfive.basetools.util

import android.view.View
import android.widget.EditText

/**
 * 软键盘工具
 * Date : 2020/9/25/25
 * Time : 14:07
 * author : Li
 */
object KeyboardUtil {

    /**
     * 设置EditText获取焦点后，不弹出软键盘。
     * 该方法可用于页面使用自定义软键盘，无需系统软键盘，
     * 如：自定义数字软键盘输入金额。
     *
     * 调用该方法的同时，需要同时在 Manifests文件中对该Activity设置
     * android:windowSoftInputMode="stateAlwaysHidden"
     */
    fun disableShowKeyboard(editText: View?){
        if(editText == null){
            return
        }
        val cls : Class<EditText> = EditText::class.java
        try {
            val method = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.java)
            method.isAccessible = true
            method.invoke(editText, false)
        } catch (e: Exception) {
            LogUtil.v(KeyboardUtil::class.simpleName, "disableShowKeyboard().Exception-->" + e.toString())
        }
    }
}