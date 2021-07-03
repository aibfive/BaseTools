package com.aibfive.wanandroid.databinding

import androidx.databinding.BindingAdapter
import com.aibfive.wanandroid.ui.home.ArticleModel
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author: 小李
 * @date: 2021/7/1 18:42
 */
object SmartRefreshLayoutDataBinding {

    @BindingAdapter("data")
    @JvmStatic
    fun getData(refreshLayout: SmartRefreshLayout, data : ArticleModel){
        
    }

}