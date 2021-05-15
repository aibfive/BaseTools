package com.aibfive.wanandroid.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * @author: 小李
 * @date: 2021/5/14 22:03
 */
@Entity(tableName = "cookie")
//room不支持对象中直接储存集合，在集合使用StringTypeConverter该类注解可解决
@TypeConverters(StringTypeConverter::class)
data class CookieModel(
        @PrimaryKey
        var headers : List<String>
) {
   constructor() : this(ArrayList<String>())
}