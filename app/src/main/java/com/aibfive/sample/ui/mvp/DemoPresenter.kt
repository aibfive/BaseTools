package com.aibfive.sample.ui.mvp

import com.aibfive.basetools.mvp.ResponseCallback

/**
 * Date : 2020/12/8/008
 * Time : 17:56
 * author : Li
 */
class DemoPresenter : DemoContract.Presenter() {

    override fun getDemo() {
        view?.showLoading()
        model?.getDemo(object : ResponseCallback<DemoBean>{
            override fun onSuccess(data: DemoBean?) {
                view?.getDemoSuccess()
                view?.hideLoading()
            }

            override fun onFail(code: String, msg: String) {
                view?.hideLoading()
            }
        })
    }
}