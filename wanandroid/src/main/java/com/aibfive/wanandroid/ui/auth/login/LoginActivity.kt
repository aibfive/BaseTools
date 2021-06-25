package com.aibfive.wanandroid.ui.auth.login

import com.aibfive.basetools.util.AppUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.databinding.ActivityLoginBinding
import com.aibfive.wanandroid.ui.auth.register.RegisterActivity
import com.aibfive.wanandroid.ui.base.StatusBarMvvmActivity

class LoginActivity : StatusBarMvvmActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initView() {
        super.initView()
        binding.tvRegister.setOnClickListener { AppUtil.startActivity(this, RegisterActivity::class.java) }
    }
}