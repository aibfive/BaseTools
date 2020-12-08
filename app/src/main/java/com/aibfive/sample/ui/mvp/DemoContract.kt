package com.aibfive.sample.ui.mvp

import com.aibfive.basetools.mvp.MvpModel
import com.aibfive.basetools.mvp.MvpPresenter
import com.aibfive.basetools.mvp.MvpView
import com.aibfive.basetools.mvp.ResponseCallback

/**
 * Date : 2020/12/8/008
 * Time : 17:53
 * author : Li
 */
interface DemoContract {

    interface View : MvpView {
        fun getDemoSuccess()
    }

    interface Model : MvpModel {
        fun getDemo(callback: ResponseCallback<DemoBean>)
    }

    abstract class Presenter : MvpPresenter<View, Model>(){
        abstract fun getDemo()
    }

}