package com.aibfive.basetools.mvp

import android.os.Bundle
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.CreateUtil

/**
 * Date : 2020/12/8/008
 * Time : 17:50
 * author : Li
 */
abstract class MvpActivity<P : MvpPresenter<*, *>, M : MvpModel> : BaseActivity(), MvpView{

    var mPresenter : P? = null
    var mModel : M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = CreateUtil.getT(this, 0)
        mModel = CreateUtil.getT(this, 1)
        mPresenter?.attachView(this, mModel)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }



}