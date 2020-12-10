package com.aibfive.basetools.ui

import com.aibfive.basetools.mvp.MvpActivity
import com.aibfive.basetools.mvp.MvpModel
import com.aibfive.basetools.mvp.MvpPresenter
import com.aibfive.basetools.util.EmptyUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * Date : 2020/12/10/010
 * Time : 14:23
 * author : Li
 */
abstract class RefreshLoadMoreActivity<P : MvpPresenter<*, *>, M : MvpModel> : MvpActivity<P, M>() {

    var mCurrentPage : Int = START_PAGE
    abstract val mRefreshLayout : SmartRefreshLayout

    companion object {
        const val START_PAGE = 1
    }

    override fun initView() {
        super.initView()
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mCurrentPage = START_PAGE
                loadData()
            }
        })
        mRefreshLayout.setEnableFooterFollowWhenNoMoreData(true)
    }

    fun isFirstPage() : Boolean {
        return mCurrentPage == START_PAGE
    }

    abstract fun loadData()

    /**
     * 结束刷新加载
     * success：是否成功
     * noMoreData: 是否有更多数据
     */
    fun finishRefreshLoadMore(success : Boolean, noMoreData : Boolean){
        when(mRefreshLayout.state){
            RefreshState.Refreshing ->
                mRefreshLayout.finishRefresh(300, success, noMoreData)
            RefreshState.Loading ->
                mRefreshLayout.finishLoadMore(300, success, noMoreData)
        }
    }

    /**
     * 加载数据成功
     */
    fun <T> loadDataSuccess(adapter: BaseQuickAdapter<T, out BaseViewHolder>, list : List<T>?){
        if(isFirstPage()){//首页数据，直接替换全部数据
            adapter.setList(list)
        }else{//非首页数据，与之前数据合并
            if(list != null) {
                adapter.addData(list)
            }
        }
        finishRefreshLoadMore(true, EmptyUtil.isEmpty(list))
        mCurrentPage++
    }

    /**
     * 加载数据成功
     */
    fun loadDataFail(){
        finishRefreshLoadMore(false, false)
    }

}