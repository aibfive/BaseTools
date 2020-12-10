package com.aibfive.sample.ui.refresh

import com.aibfive.basetools.mvp.MvpModel
import com.aibfive.basetools.mvp.MvpPresenter
import com.aibfive.basetools.mvp.MvpView
import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.sample.bean.ArticleBean

/**
 * Date : 2020/12/10/010
 * Time : 10:04
 * author : Li
 */
interface RefreshContract {

    interface Model : MvpModel {
        fun getData(page : Int, callback: ResponseCallback<ArticleBean>)
    }

    interface View : MvpView {
        fun getDataSuccess(data : ArticleBean?)
        fun getDataFail(code : String, msg : String)
    }

    abstract class Presenter : MvpPresenter<View, Model>(){
        abstract fun getData(page : Int)
    }

}