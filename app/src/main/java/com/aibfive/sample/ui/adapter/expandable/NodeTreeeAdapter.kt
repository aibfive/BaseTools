package com.aibfive.sample.ui.adapter.expandable

import com.aibfive.basetools.adapter.node.ExpandableNodeAdapter
import com.aibfive.sample.R
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date : 2020/12/9/009
 * Time : 14:12
 * author : Li
 */
class NodeTreeeAdapter : ExpandableNodeAdapter<FirstBean>{

    constructor(firstLayoutId: Int, secondLayoutId: Int) : super(firstLayoutId, secondLayoutId)

    constructor(firstLayoutId: Int, secondLayoutId: Int, thirdLayoutId: Int) : super(firstLayoutId, secondLayoutId, thirdLayoutId)

    override fun convertFirst(helper: BaseViewHolder, item: BaseNode) {
        super.convertFirst(helper, item)
        helper.setText(R.id.tv_title, (item as FirstBean).title)
    }

    override fun convertSecond(helper: BaseViewHolder, item: BaseNode) {
        super.convertSecond(helper, item)
        helper.setText(R.id.tv_title, (item as FirstBean.SecondBean).title)
    }

    override fun convertThird(helper: BaseViewHolder, item: BaseNode) {
        super.convertThird(helper, item)
        helper.setText(R.id.tv_title, (item as FirstBean.SecondBean.ThirdBean).title)
    }

}