package com.aibfive.wanandroid.dao

import androidx.room.*
import com.aibfive.wanandroid.base.UserLoginModel

/**
 * @author: 小李
 * @date: 2021/5/11 22:22
 */
@Dao
interface UserLoginModelDao {

    /**
     * 获取登录成功时用户数据
     * @return UserLoginModel
     */
    @Query("SELECT * FROM user_login")
    fun getUserLoginModel() : UserLoginModel

    /**
     * 储存登录成功时用户数据
     * @param model UserLoginModel
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLoginModel(model: UserLoginModel)

    /**
     * 删除登录成功时用户数据
     * @param model UserLoginModel
     */
    @Query("DELETE FROM user_login")
    fun deleteUserLoginModel()

}