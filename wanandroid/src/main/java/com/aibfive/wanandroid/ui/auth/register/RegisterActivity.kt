package com.aibfive.wanandroid.ui.auth.register

import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.databinding.ActivityRegisterBinding
import com.aibfive.wanandroid.ui.base.StatusBarMvvmActivity

class RegisterActivity : StatusBarMvvmActivity<ActivityRegisterBinding, RegisterViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }


}