package com.aibfive.basetools.util.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Date : 2020/12/1/001
 * Time : 15:57
 * author : Li
 */
object SharedPreferencesUtils {

    var preferences : SharedPreferences? = null

    fun init(context: Context, name: String){
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    var INT by SharedPreferenceDelegates.int()
    var STRING by SharedPreferenceDelegates.string()
    var BOOLEAN by SharedPreferenceDelegates.boolean()
    var LONG by SharedPreferenceDelegates.long()
    var FLOAT by SharedPreferenceDelegates.float()
    var STRING_SET by SharedPreferenceDelegates.stringSet()

}
