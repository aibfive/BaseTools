package com.aibfive.sample.ui.refresh

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.aibfive.basetools.adapter.itemdecoration.LinearItemDecoration
import com.aibfive.basetools.ui.RefreshLoadMoreActivity
import com.aibfive.basetools.util.ActivityManager
import com.aibfive.basetools.util.DisplayUtil
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.ui.MainActivity
import com.aibfive.sample.R
import com.aibfive.sample.bean.ArticleBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.activity_refresh.*

class RefreshDemoActivity : RefreshLoadMoreActivity<RefreshPresenter, RefreshModel>(), RefreshContract.View {

    lateinit var mAdapter: RefreshAdapter
    override val mRefreshLayout: SmartRefreshLayout
        get() = this.findViewById(R.id.mRefreshLayout)

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
        mAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                when(position){
                    0 ->
                        ActivityManager.finishAll()
                    1 ->
                        ActivityManager.finish(MainActivity::class.java)
                    2 ->
                        ActivityManager.finish(this@RefreshDemoActivity)
                }
            }
        })
        mRefreshLayout.autoRefresh()
        LogUtil.v(RefreshDemoActivity::class.simpleName, "${ActivityManager.contains(MainActivity::class.java)}")
        LogUtil.v(RefreshDemoActivity::class.simpleName, "${ActivityManager.find(MainActivity::class.java)}")
        LogUtil.v(RefreshDemoActivity::class.simpleName, "${ActivityManager.getCurrent()}")

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