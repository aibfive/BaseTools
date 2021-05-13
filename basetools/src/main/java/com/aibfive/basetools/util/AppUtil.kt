package com.aibfive.basetools.util

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

/**
 * App工具
 * @author: 小李
 * @date: 2021/5/2 22:30
 */
object AppUtil {

    fun startActivity(context: Context, cls : Class<*>, vararg data : Pair<String, Any>){
        val intent = Intent()
        intent.setClass(context, cls)
        data.forEach {
            when(it.second){
                is String -> intent.putExtra(it.first, it.second as String)
                is Int -> intent.putExtra(it.first, it.second as Int)
                is Float -> intent.putExtra(it.first, it.second as Float)
                is Double -> intent.putExtra(it.first, it.second as Double)
                is Char -> intent.putExtra(it.first, it.second as Char)
                is Byte -> intent.putExtra(it.first, it.second as Byte)
                is Boolean -> intent.putExtra(it.first, it.second as Boolean)
                is Short -> intent.putExtra(it.first, it.second as Short)
                is Long -> intent.putExtra(it.first, it.second as Long)
                is Parcelable -> intent.putExtra(it.first, it.second as Parcelable)
                is Serializable -> intent.putExtra(it.first, it.second as Serializable)
            }
        }
        context.startActivity(intent)
    }

}