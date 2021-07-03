package com.aibfive.wanandroid.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aibfive.wanandroid.adapter.ArticleAdapter
import com.aibfive.wanandroid.ui.base.PageModel
import com.aibfive.wanandroid.ui.home.ArticleModel

/**
 * @author: 小李
 * @date: 2021/7/1 18:54
 */
object RecyclerViewDataBinding {

    @BindingAdapter("refreshLoadData")
    @JvmStatic
    fun getArticleData(recyclerView: RecyclerView, data : PageModel<ArticleModel>){
        if(data.curPage == 1){
            (recyclerView.adapter as ArticleAdapter).setList(data.datas)
        }else{
            (recyclerView.adapter as ArticleAdapter).addData(data.datas!!)
        }

    }

}