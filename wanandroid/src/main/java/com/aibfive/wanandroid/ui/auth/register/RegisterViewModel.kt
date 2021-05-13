package com.aibfive.wanandroid.ui.auth.register

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.basetools.util.ToastUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.base.UserLoginModel
import com.aibfive.wanandroid.dao.AppDataBase
import com.aibfive.wanandroid.network.AuthService
import com.aibfive.wanandroid.network.Callback
import com.aibfive.wanandroid.network.DefaultRepository
import com.aibfive.wanandroid.network.RetrofitClient
import com.aibfive.wanandroid.util.UserUtil

class RegisterViewModel : BaseViewModel {

    val usernameLiveData : MutableLiveData<String>  //用户名
    val passwordLiveData : MutableLiveData<String>  //密码
    val repasswordLiveData : MutableLiveData<String>  //确认密码

    constructor(){
        usernameLiveData = MutableLiveData()
        passwordLiveData = MutableLiveData()
        repasswordLiveData = MutableLiveData()
    }

    /**
     * 注册
     */
    fun register(view : View){
        if(TextUtils.isEmpty(usernameLiveData.value)){
            ToastUtil.show(R.string.hint_input_nickname)
        }else if(TextUtils.isEmpty(passwordLiveData.value)){
            ToastUtil.show(R.string.hint_input_password)
        }else if(TextUtils.isEmpty(repasswordLiveData.value)){
            ToastUtil.show(R.string.hint_input_repassword)
        }else if(!passwordLiveData.value.equals(repasswordLiveData.value)){
            ToastUtil.show(R.string.hint_please_input_equal_password)
        }else {
            DefaultRepository.getData({
                RetrofitClient.getService<AuthService>().register(usernameLiveData.value!!, passwordLiveData.value!!, repasswordLiveData.value!!)
            }, object : Callback<UserLoginModel> {
                override fun onSuccess(data: UserLoginModel) {
                    //UserUtil.login(data)
                    AppDataBase.db.userLoginDao().insertUserLoginModel(data)
                }

                override fun onFailed(errorCode: String, errorMsg: String) {

                }
            })
        }
    }

}