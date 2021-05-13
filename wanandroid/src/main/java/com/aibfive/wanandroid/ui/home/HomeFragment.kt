package com.aibfive.wanandroid.ui.home

import com.aibfive.basetools.mvvm.MvvmFragment
import com.aibfive.basetools.util.StatusBarUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.databinding.FragmentHomeBinding
import com.aibfive.wanandroid.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : MvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        banner.adapter = BannerAdapter()
        banner.addBannerLifecycleObserver(this)
        banner.indicator = CircleIndicator(context)
    }

    override fun handleData() {
        super.handleData()
        viewModel.getBanner()
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}