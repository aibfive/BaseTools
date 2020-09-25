package com.aibfive.basetools.util

import android.widget.EditText
import kotlin.reflect.KClass

/**
 * 软键盘工具
 * Date : 2020/9/25/25
 * Time : 14:07
 * author : Li
 */
object KeyboardUtil {

    /**
     * 设置EditText获取焦点后，不弹出软键盘。
     *
     * 调用该方法的同时，需要同时在 Manifests文件中对该Activity设置
     * android:windowSoftInputMode="stateAlwaysHidden"
     */
    fun disableShowKeyboard(editText: EditText?){

        val cls : KClass<EditText> = EditText::class
    }
}