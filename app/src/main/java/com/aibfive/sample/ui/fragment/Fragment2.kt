package com.aibfive.sample.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aibfive.basetools.ui.BaseLazyFragment
import com.aibfive.basetools.ui.RefreshFragment
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.databinding.ActivityRefreshBinding
import com.aibfive.sample.ui.refresh.RefreshContract
import com.aibfive.sample.ui.refresh.RefreshModel
import com.aibfive.sample.ui.refresh.RefreshPresenter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * Date : 2020/12/11/011
 * Time : 15:24
 * author : Li
 */
class Fragment2 : RefreshFragment<ActivityRefreshBinding, RefreshPresenter, RefreshModel>(), RefreshContract.View {

    companion object {
        fun newInstance(): Fragment2 {
            val fragment = Fragment2()
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtil.v("Fragment", "Fragment2-->onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.v("Fragment", "Fragment2-->onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.v("Fragment", "Fragment2-->onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtil.v("Fragment", "Fragment2-->onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        LogUtil.v("Fragment", "Fragment2-->onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.v("Fragment", "Fragment2-->onResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtil.v("Fragment", "Fragment2-->onHiddenChanged = $hidden")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.v("Fragment", "Fragment2-->onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.v("Fragment", "Fragment2-->onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.v("Fragment", "Fragment2-->onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtil.v("Fragment", "Fragment2-->onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtil.v("Fragment", "Fragment2-->onDetach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        LogUtil.v("Fragment", "Fragment2-->setUserVisibleHint = $isVisibleToUser")
    }

    override fun lazyLoad() {
        LogUtil.v("Fragment", "Fragment2-->lazyLoad")
        mPresenter?.getData(1)
    }

    override val mRefreshLayout: SmartRefreshLayout
        get() = this.requireView().findViewById(R.id.mRefreshLayout)

    override fun getDataSuccess(data: ArticleBean?) {
    }

    override fun getDataFail(code: String, msg: String) {
    }
}