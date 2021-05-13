package com.aibfive.wanandroid.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aibfive.basetools.BaseApplication
import com.aibfive.wanandroid.app.App
import com.aibfive.wanandroid.base.UserLoginModel
import com.aibfive.wanandroid.ui.my.UserInfoModel

/**
 * @author: 小李
 * @date: 2021/5/11 22:44
 */
@Database(entities = [
    UserLoginModel::class,
    UserInfoModel::class
], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userLoginDao() : UserLoginModelDao

    companion object{
        val db : AppDataBase by lazy {
            Room.databaseBuilder(BaseApplication.instance, AppDataBase::class.java, "wanandroid.db").allowMainThreadQueries().build()
        }
    }

}