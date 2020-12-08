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

    var presenter : P? = null
    var model : M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = CreateUtil.getT(this, 0)
        model = CreateUtil.getT(this, 1)
        presenter?.attachView(this, model)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}