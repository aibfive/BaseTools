package com.aibfive.wanandroid.ui.my

import com.aibfive.basetools.mvvm.MvvmFragment
import com.aibfive.basetools.util.AppUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.databinding.FragmentMyBinding
import com.aibfive.wanandroid.ui.auth.login.LoginActivity

class MyFragment : MvvmFragment<FragmentMyBinding, MyViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initView() {
        super.initView()
        binding.userIv.setOnClickListener { AppUtil.startActivity(requireContext(), LoginActivity::class.java) }
    }

    override fun getViewModelClass(): Class<MyViewModel> {
        return MyViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserInfo()
    }

}