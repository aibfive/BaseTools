package com.aibfive.basetools.mvp

/**
 * Date : 2020/12/8/008
 * Time : 17:43
 * author : Li
 */
abstract class MvpPresenter<V : MvpView, M : MvpModel> {

    var view : V? = null
    var model : M? = null

    fun attachView(view : MvpView?, model: MvpModel?){
        this.view = view as V
        this.model = model as M
    }

    fun detachView(){
        this.view = null
        this.model = null
    }

}