package com.aibfive.basetools.ui

import android.os.Bundle

/**
 * Date : 2020/12/3/003
 * Time : 11:06
 * author : Li
 */
abstract class MvpActivity<T : BasePresenter<?>> : BaseActivity() {

    lateinit var presenter : T

    init {
        presenter = BasePresenter>() as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}