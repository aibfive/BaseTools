package com.aibfive.wanandroid.ui.auth.login

import android.text.TextUtils
import android.util.Log
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

class LoginViewModel : BaseViewModel() {

    val usernameLiveData : MutableLiveData<String>  = MutableLiveData() //用户名
    val passwordLiveData : MutableLiveData<String> = MutableLiveData()  //密码

    fun login(view : View){
        if (TextUtils.isEmpty(usernameLiveData.value)){
            ToastUtil.show(R.string.hint_input_nickname)
        }else if(TextUtils.isEmpty(passwordLiveData.value)){
            ToastUtil.show(R.string.hint_input_password)
        }else{
            DefaultRepository.getData(
                    { RetrofitClient.getService<AuthService>().login(usernameLiveData.value!!, passwordLiveData.value!!)},
                    object : Callback<UserLoginModel>{
                        override fun onSuccess(data: UserLoginModel) {
                            UserUtil.login(data)
                        }

                        override fun onFailed(errorCode: String, errorMsg: String) {
                            TODO("Not yet implemented")
                        }
                    }
            )
        }
    }

}