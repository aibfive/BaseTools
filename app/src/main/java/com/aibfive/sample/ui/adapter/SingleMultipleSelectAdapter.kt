package com.aibfive.sample.ui.adapter

import com.aibfive.basetools.adapter.BaseRVHolder
import com.aibfive.basetools.adapter.select.SelectedAdapter
import com.aibfive.sample.R
import com.aibfive.sample.bean.base.CommonBean

/**
 * Date : 2020/10/27/027
 * Time : 18:38
 * author : Li
 */
class SingleMultipleSelectAdapter : SelectedAdapter<CommonBean>(R.layout.item_select) {

    override fun onBindVH(holder: BaseRVHolder, item: CommonBean, position: Int) {
        super.onBindVH(holder, item, position)
    }

}