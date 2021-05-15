package com.aibfive.wanandroid.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aibfive.basetools.BaseApplication
import com.aibfive.wanandroid.app.App
import com.aibfive.wanandroid.app.Constants
import com.aibfive.wanandroid.base.CookieModel
import com.aibfive.wanandroid.base.UserLoginModel
import com.aibfive.wanandroid.ui.my.UserInfoModel

/**
 * @author: 小李
 * @date: 2021/5/11 22:44
 */
@Database(entities = [
    UserLoginModel::class,
    UserInfoModel::class,
    CookieModel::class
], version = Constants.DB_VERSION)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userLoginDao() : UserLoginModelDao
    abstract fun userInfoDao() : UserInfoModelDao
    abstract fun cookieDao() : CookieModelDao

    companion object{
        val db : AppDataBase by lazy {
            Room.databaseBuilder(BaseApplication.instance, AppDataBase::class.java, Constants.DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }

}