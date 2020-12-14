package com.aibfive.basetools.ui

import com.aibfive.basetools.mvp.MvpActivity
import com.aibfive.basetools.mvp.MvpFragment
import com.aibfive.basetools.mvp.MvpModel
import com.aibfive.basetools.mvp.MvpPresenter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * Date : 2020/12/14/014
 * Time : 16:10
 * author : Li
 */
abstract class RefreshFragment<P : MvpPresenter<*, *>, M : MvpModel> : MvpFragment<P, M>() {

    /**
     * 实现该mRefreshLayout，有两种方式，
     * 1、继承SmartRefreshLayout所用的属性变量名与mRefreshLayout不同，如：
     *  override val mRefreshLayout: SmartRefreshLayout
     *      get() = refreshLayout
     * 2、使用findViewById获取属性值，这样属性变量名一致也可以。
     *  override val mRefreshLayout: SmartRefreshLayout
     *      get() = this.findViewById(R.id.mRefreshLayout)
     *
     *  不然如果实现时属性变量名一致，如：
     *  override val mRefreshLayout: SmartRefreshLayout
     *      get() = mRefreshLayout
     *  会抛出异常java.lang.StackOverflowError: stack size 8192KB
     */
    abstract val mRefreshLayout : SmartRefreshLayout

    override fun initView() {
        super.initView()
        mRefreshLayout.setOnRefreshListener{
            lazyLoad()
        }
        mRefreshLayout.setEnableLoadMore(false)
    }

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