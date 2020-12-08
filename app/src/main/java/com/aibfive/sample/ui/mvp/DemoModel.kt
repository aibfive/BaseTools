package com.aibfive.sample.ui.mvp

import com.aibfive.basetools.mvp.ResponseCallback

/**
 * Date : 2020/12/8/008
 * Time : 17:55
 * author : Li
 */
class DemoModel : DemoContract.Model {

    override fun getDemo(callback: ResponseCallback<DemoBean>) {
        callback.onSuccess(null)
    }
}