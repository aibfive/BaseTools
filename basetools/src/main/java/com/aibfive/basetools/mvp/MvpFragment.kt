package com.aibfive.basetools.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.aibfive.basetools.ui.BaseLazyFragment
import com.aibfive.basetools.util.CreateUtil

/**
 * Date : 2020/12/14/014
 * Time : 16:06
 * author : Li
 */
abstract class MvpFragment<VB : ViewBinding, P : MvpPresenter<*, *>, M : MvpModel> : BaseLazyFragment<VB>(), MvpView{

    var mPresenter : P? = null
    var mModel : M? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = CreateUtil.getT(this, 1)
        mModel = CreateUtil.getT(this, 2)
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