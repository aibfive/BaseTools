package com.aibfive.basetools.ui

import androidx.lifecycle.Lifecycle

/**
 * Date : 2020/12/11/011
 * Time : 14:30
 * author : Li
 */
abstract class BaseLazyFragment : BaseFragment() {

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun initView() {
        super.initView()
        parentFragmentManager.beginTransaction().setMaxLifecycle(this, Lifecycle.State.RESUMED)
    }

}