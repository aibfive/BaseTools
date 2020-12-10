package com.aibfive.sample.ui.refresh

import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.sample.bean.ArticleBean

/**
 * Date : 2020/12/10/010
 * Time : 10:14
 * author : Li
 */
class RefreshPresenter : RefreshContract.Presenter() {

    override fun getData(page: Int) {
        mModel?.getData(page, object : ResponseCallback<ArticleBean>{
            override fun onSuccess(data: ArticleBean?) {
                mView?.getDataSuccess(data)
            }

            override fun onFail(code: String, msg: String) {
                mView?.getDataFail(code, msg)
            }
        })
    }
}