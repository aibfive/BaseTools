package com.aibfive.sample.ui.adapter.griditem

import android.content.Context
import android.content.Intent
import android.graphics.Color
import com.aibfive.basetools.adapter.itemdecoration.StaggeredGridItemDecoration
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivityGridItemDecorationBinding
import com.aibfive.sample.util.DataCreator

/**
 * 网格分割线
 */
class GridItemDecorationActivity : BaseActivity<ActivityGridItemDecorationBinding>() {
    
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, GridItemDecorationActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        val adapter = GridItemDecorationAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(StaggeredGridItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_10), resources.getDimensionPixelSize(R.dimen.dp_10),
                Color.WHITE))
        adapter.setList(DataCreator.buildCommonData(this))
    }
}