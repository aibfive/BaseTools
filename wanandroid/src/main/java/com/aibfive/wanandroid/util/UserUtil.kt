package com.aibfive.wanandroid.util

import com.aibfive.basetools.util.ActivityManager
import com.aibfive.basetools.util.AppUtil
import com.aibfive.wanandroid.base.UserLoginModel
import com.aibfive.wanandroid.dao.AppDataBase
import com.aibfive.wanandroid.ui.WanAndroidActivity

/**
 * @author: 小李
 * @date: 2021/5/5 0:47
 */
object UserUtil {

    /**
     * 登录
     * @param data UserModel
     */
    fun login(data : UserLoginModel){
        AppDataBase.db.userLoginDao().insertUserLoginModel(data)
        AppUtil.startActivity(ActivityManager.getCurrent()!!, WanAndroidActivity::class.java)
    }

    /**
     * 用户是否登录
     * @return Boolean
     */
    fun isLogin() : Boolean{
        return getUserInfo() != null
    }

    /**
     * 退出登录
     */
    fun logOut(){
        AppDataBase.db.userLoginDao().deleteUserLoginModel()
    }

    /**
     * 获取用户信息
     * @return UserModel?
     */
    fun getUserInfo() : UserLoginModel?{
        return AppDataBase.db.userLoginDao().getUserLoginModel()
    }

}