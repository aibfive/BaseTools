package com.aibfive.sample.ui.adapter.griditem

import android.content.Context
import android.content.Intent
import android.graphics.Color
import com.aibfive.basetools.adapter.itemdecoration.GridItemDecoration
import com.aibfive.basetools.adapter.itemdecoration.StaggeredGridItemDecoration
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
        recyclerView.addItemDecoration(StaggeredGridItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_10), resources.getDimensionPixelSize(R.dimen.dp_10),
                Color.WHITE))
        adapter.setList(DataCreator.buildCommonData(this))
    }
}