package com.aibfive.wanandroid.ui.my

import androidx.lifecycle.MutableLiveData
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.wanandroid.network.Callback
import com.aibfive.wanandroid.network.DefaultRepository
import com.aibfive.wanandroid.network.MyService
import com.aibfive.wanandroid.network.RetrofitClient

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

        DefaultRepository.getData(false, {
            RetrofitClient.getService<MyService>().getUserInfo()
        }, object : Callback<UserInfoModel>{
            override fun onSuccess(data: UserInfoModel) {
                userInfoLiveData.value = data
            }

            override fun onFailed(errorCode: String, errorMsg: String) {

            }
        })

    }

}