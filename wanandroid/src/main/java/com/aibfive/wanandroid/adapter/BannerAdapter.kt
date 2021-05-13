package com.aibfive.wanandroid.adapter

import com.aibfive.wanandroid.ui.home.BannerModel
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class BannerAdapter(mData: MutableList<BannerModel>?) : BannerImageAdapter<BannerModel>(mData) {

    constructor() : this(null)


    override fun onBindView(holder: BannerImageHolder?, data: BannerModel?, position: Int, size: Int) {
        //图片加载自己实现
        Glide.with(holder!!.itemView)
                .load(data!!.imagePath)
                .into(holder!!.imageView);
    }

}