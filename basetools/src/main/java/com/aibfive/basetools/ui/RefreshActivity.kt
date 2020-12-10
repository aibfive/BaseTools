package com.aibfive.basetools.ui

import com.aibfive.basetools.mvp.MvpActivity
import com.aibfive.basetools.mvp.MvpModel
import com.aibfive.basetools.mvp.MvpPresenter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * Date : 2020/12/10/010
 * Time : 16:32
 * author : Li
 */
abstract class RefreshActivity<P : MvpPresenter<*, *>, M : MvpModel> : MvpActivity<P, M>() {

    abstract val mRefreshLayout : SmartRefreshLayout

    override fun initView() {
        super.initView()
        mRefreshLayout.setOnRefreshListener{
            loadData()
        }
        mRefreshLayout.setEnableLoadMore(false)
    }

    abstract fun loadData()

    /**
     * 加载数据成功
     */
    fun <T> loadDataSuccess(adapter: BaseQuickAdapter<T, out BaseViewHolder>, list : List<T>?){
        adapter.setList(list)
        mRefreshLayout.finishRefresh(true)
    }

    /**
     * 加载数据成功
     */
    fun loadDataFail(){
        mRefreshLayout.finishRefresh(false)
    }

}