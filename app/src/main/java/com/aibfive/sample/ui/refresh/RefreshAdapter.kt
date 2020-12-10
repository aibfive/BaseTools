package com.aibfive.sample.ui.refresh

import com.aibfive.sample.R
import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.bean.Data
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date : 2020/12/10/010
 * Time : 10:19
 * author : Li
 */
class RefreshAdapter : BaseQuickAdapter<Data, BaseViewHolder>(R.layout.item_refresh) {

    override fun convert(holder: BaseViewHolder, item: Data) {
        holder.setText(R.id.tv_title, item.title)
    }
}