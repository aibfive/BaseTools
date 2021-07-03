package com.aibfive.wanandroid.adapter

import com.aibfive.wanandroid.ui.home.ArticleModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author: 小李
 * @date: 2021/7/1 20:58
 */
class ArticleAdapter : BaseQuickAdapter<ArticleModel, BaseViewHolder> {


    constructor(layoutResId: Int, data: MutableList<ArticleModel>?) : super(layoutResId, data)

    override fun convert(holder: BaseViewHolder, item: ArticleModel) {

    }
}