package com.aibfive.basetools.mvp

/**
 * Date : 2020/12/8/008
 * Time : 17:43
 * author : Li
 */
abstract class MvpPresenter<V : MvpView, M : MvpModel> {

    var mView : V? = null
    var mModel : M? = null

    fun attachView(view : MvpView?, model: MvpModel?){
        this.mView = view as V
        this.mModel = model as M
    }

    fun detachView(){
        this.mView = null
        this.mModel = null
    }

}