package com.aibfive.basetools.mvp

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.CreateUtil

/**
 * Date : 2020/12/8/008
 * Time : 17:50
 * author : Li
 */
abstract class MvpActivity<VB : ViewBinding, P : MvpPresenter<*, *>, M : MvpModel> : BaseActivity<VB>(), MvpView{

    var mPresenter : P? = null
    var mModel : M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = CreateUtil.getT(this, 1)
        mModel = CreateUtil.getT(this, 2)
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