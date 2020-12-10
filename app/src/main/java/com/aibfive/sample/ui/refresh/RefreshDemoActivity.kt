package com.aibfive.sample.ui.refresh

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.aibfive.basetools.adapter.itemdecoration.LinearItemDecoration
import com.aibfive.basetools.ui.RefreshActivity
import com.aibfive.basetools.ui.RefreshLoadMoreActivity
import com.aibfive.basetools.util.DisplayUtil
import com.aibfive.sample.R
import com.aibfive.sample.bean.ArticleBean
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.activity_refresh.*

class RefreshDemoActivity : RefreshLoadMoreActivity<RefreshPresenter, RefreshModel>(), RefreshContract.View {

    lateinit var mAdapter: RefreshAdapter
    override val mRefreshLayout: SmartRefreshLayout
        get() = refreshLayout

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, RefreshDemoActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_refresh
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        mAdapter = RefreshAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addItemDecoration(LinearItemDecoration(DisplayUtil.dip2px(this, 1f), ContextCompat.getColor(this, R.color.color333333), LinearItemDecoration.VERTICAL_INCLUDE_NULL))

        mRefreshLayout.autoRefresh()
    }

    override fun loadData() {
        mPresenter?.getData(mCurrentPage)
    }

    override fun getDataSuccess(data: ArticleBean?) {
        loadDataSuccess(mAdapter, data?.datas)
    }

    override fun getDataFail(code: String, msg: String) {
        loadDataFail()
    }
}