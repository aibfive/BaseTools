package com.aibfive.sample.ui.adapter.griditem

import com.aibfive.basetools.adapter.BaseRVAdapter
import com.aibfive.basetools.adapter.BaseRVHolder
import com.aibfive.sample.R
import com.aibfive.sample.bean.base.CommonBean

/**
 * Date : 2020/10/30/030
 * Time : 16:37
 * author : Li
 */
class GridItemDecorationAdapter : BaseRVAdapter<CommonBean>(R.layout.item_grid) {

    override fun onBindVH(holder: BaseRVHolder?, data: CommonBean?, position: Int) {
        holder?.setText(R.id.tv_name, data?.name)
    }
}