package com.aibfive.sample.ui.mvp

import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.sample.bean.ArticleBean

/**
 * Date : 2020/12/8/008
 * Time : 17:56
 * author : Li
 */
class DemoPresenter : DemoContract.Presenter() {

    override fun getDemo() {
        view?.showLoading()
        model?.getDemo(object : ResponseCallback<ArticleBean>{
            override fun onSuccess(data: ArticleBean?) {
                view?.getDemoSuccess(data)
                view?.hideLoading()
            }

            override fun onFail(code: String, msg: String) {
                view?.hideLoading()
            }
        })
    }

}