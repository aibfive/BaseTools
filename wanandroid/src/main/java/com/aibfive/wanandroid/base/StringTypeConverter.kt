package com.aibfive.wanandroid.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * @author: 小李
 * @date: 2021/5/14 22:22
 * room不支持对象中直接储存集合，在集合使用该类注解可解决
 */
class StringTypeConverter {

    @TypeConverter
    fun stringToSomeObjectList(data : String?) : List<String>{
        if(data == null){
            return Collections.emptyList()
        }
        val list = Gson().fromJson<List<String>>(data, object : TypeToken<List<String>>() {}.type)
        return list
    }


    @TypeConverter
    fun someObjectListToString(someObjects : List<String>) : String{
        return Gson().toJson(someObjects)
    }


}