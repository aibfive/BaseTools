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
        @ColumnInfo val coinCount: Int,
        @ColumnInfo val rank: Int,
        @PrimaryKey val userId: Int,
        @ColumnInfo val username: String
)