package com.aibfive.sample.ui.adapter.singlemultipleselect

import com.aibfive.basetools.adapter.select.SelectedAdapter
import com.aibfive.sample.R
import com.aibfive.sample.bean.base.CommonBean
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date : 2020/10/27/027
 * Time : 18:38
 * author : Li
 */
class SingleMultipleSelectAdapter : SelectedAdapter<CommonBean>(R.layout.item_select) {

    override fun convert(holder: BaseViewHolder, item: CommonBean) {
        super.convert(holder, item)
    }


}