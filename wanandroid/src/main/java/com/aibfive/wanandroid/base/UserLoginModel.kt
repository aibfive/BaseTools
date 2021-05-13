package com.aibfive.wanandroid.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_login")
data class UserLoginModel(
        @ColumnInfo var admin: Boolean,
        @Ignore var chapterTops: List<Any>,
        @Ignore var coinCount: Int,
        @Ignore var collectIds: List<Any>,
        @Ignore var email: String,
        @Ignore var icon: String,
        @PrimaryKey var id: Int,
        @Ignore var nickname: String,
        @Ignore var password: String,
        @Ignore var publicName: String,
        @ColumnInfo var token: String,
        @Ignore var type: Int,
        @ColumnInfo var username: String
){
    constructor() : this(false, ArrayList(), 0, ArrayList(), "", "", 0, "", "", "", "", 0, "")
}