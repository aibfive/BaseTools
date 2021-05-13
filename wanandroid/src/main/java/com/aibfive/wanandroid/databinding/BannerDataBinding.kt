package com.aibfive.wanandroid.databinding

import androidx.databinding.BindingAdapter
import com.aibfive.wanandroid.adapter.BannerAdapter
import com.aibfive.wanandroid.ui.home.BannerModel
import com.youth.banner.Banner

object BannerDataBinding {

    @BindingAdapter("data")
    @JvmStatic
    fun setBannerData(banner : Banner<BannerModel, BannerAdapter>, data : List<BannerModel>?){
        banner.setDatas(data)
    }

}