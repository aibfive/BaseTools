package com.aibfive.wanandroid.ui.home

import com.aibfive.basetools.mvvm.MvvmFragment
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.adapter.BannerAdapter
import com.aibfive.wanandroid.databinding.FragmentHomeBinding
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : MvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        binding.banner.adapter = BannerAdapter()
        binding.banner.addBannerLifecycleObserver(this)
        binding.banner.indicator = CircleIndicator(context)
    }

    override fun handleData() {
        super.handleData()
        viewModel.getBanner()
        viewModel.getHomeArticle(0)
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}