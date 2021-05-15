package com.aibfive.wanandroid.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aibfive.wanandroid.ui.my.UserInfoModel

/**
 * @author: 小李
 * @date: 2021/5/13 18:32
 */
@Dao
interface UserInfoModelDao {

    /**
     * 获取用户信息
     * @return UserInfoModel
     */
    @Query("SELECT * FROM user_info")
    fun getUserInfoModel() : UserInfoModel

    /**
     * 储存用户信息
     * @param model UserInfoModel
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfoModel(model : UserInfoModel)

}