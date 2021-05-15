package com.aibfive.wanandroid.ui.my

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: 小李
 * @date: 2021/5/11 21:34
 */
@Entity(tableName = "user_info")
data class UserInfoModel(
        @ColumnInfo var coinCount: String,
        @ColumnInfo var rank: String,
        @PrimaryKey var userId: Int,
        @ColumnInfo var username: String
){
    constructor() : this("", "", 0, "")
}