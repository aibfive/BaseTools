package com.aibfive.basetools.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aibfive.basetools.ui.BaseLazyFragment
import com.aibfive.basetools.util.CreateUtil

/**
 * Date : 2020/12/14/014
 * Time : 16:06
 * author : Li
 */
abstract class MvpFragment<P : MvpPresenter<*, *>, M : MvpModel> : BaseLazyFragment(), MvpView{

    var mPresenter : P? = null
    var mModel : M? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = CreateUtil.getT(this, 0)
        mModel = CreateUtil.getT(this, 1)
        mPresenter?.attachView(this, mModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }



}