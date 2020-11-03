package com.aibfive.sample.ui.adapter.griditem

import android.content.Context
import android.content.Intent
import android.graphics.Color
import com.aibfive.basetools.adapter.itemdecoration.GridItemDecoration
import com.aibfive.basetools.adapter.itemdecoration.GridSpacingItemDecoration
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.util.DataCreator
import kotlinx.android.synthetic.main.activity_grid_item_decoration.*

/**
 * 网格分割线
 */
class GridItemDecorationActivity : BaseActivity() {
    
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, GridItemDecorationActivity::class.java)
            context.startActivity(starter)
        }
    }
    
    override fun getLayoutId(): Int {
        return R.layout.activity_grid_item_decoration
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        val adapter = GridItemDecorationAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(GridItemDecoration(
                GridItemDecoration.ORIENTATION_HORIZONTAL, resources.getDimensionPixelSize(R.dimen.dp_80), resources.getDimensionPixelSize(R.dimen.dp_40),
                Color.WHITE, false))
        /*recyclerView.addItemDecoration(GridSpacingItemDecoration(
                4, resources.getDimensionPixelSize(R.dimen.dp_40),
                false))*/
        adapter.setNewData(DataCreator.buildCommonData(this))
    }
}