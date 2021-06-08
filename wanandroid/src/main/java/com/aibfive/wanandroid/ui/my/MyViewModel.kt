package com.aibfive.wanandroid.ui.my

import androidx.lifecycle.MutableLiveData
import com.aibfive.basetools.BaseApplication
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.basetools.util.NetworkUtil
import com.aibfive.basetools.util.StringUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.dao.AppDataBase
import com.aibfive.wanandroid.network.Callback
import com.aibfive.wanandroid.network.DefaultRepository
import com.aibfive.wanandroid.network.MyService
import com.aibfive.wanandroid.network.RetrofitClient
import com.aibfive.wanandroid.util.UserUtil

/**
 * @author: 小李
 * @date: 2021/5/11 21:37
 */
class MyViewModel : BaseViewModel {

    val userInfoLiveData : MutableLiveData<UserInfoModel>

    constructor() : super(){
        userInfoLiveData = MutableLiveData()
    }

    fun getUserInfo(){
        if(UserUtil.isLogin()){//用户已登录
            //在网络访问获取到数据前，先使用缓存的数据显示。
            userInfoLiveData.value = AppDataBase.db.userInfoDao().getUserInfoModel()
            if(NetworkUtil.isNetworkConnect(BaseApplication.instance)){//已连接网络
                DefaultRepository.getData(false, {
                    RetrofitClient.getService<MyService>().getUserInfo()
                }, object : Callback<UserInfoModel>{
                    override fun onSuccess(data: UserInfoModel) {
                        userInfoLiveData.value = UserInfoModel(
                                StringUtil.getString(R.string.format_integral, data.coinCount),
                                StringUtil.getString(R.string.format_ranking, data.rank),
                                data.userId, data.username)
                        AppDataBase.db.userInfoDao().insertUserInfoModel(userInfoLiveData.value!!)
                    }

                    override fun onFailed(errorCode: String, errorMsg: String) {
                        userInfoLiveData.value = AppDataBase.db.userInfoDao().getUserInfoModel()
                    }
                })
            }else{//未连接网络
                userInfoLiveData.value = AppDataBase.db.userInfoDao().getUserInfoModel()
            }
        }else{
            userInfoLiveData.value = UserInfoModel("", StringUtil.getString(R.string.default_ranking), 0, StringUtil.getString(R.string.please_login))
        }
    }

}