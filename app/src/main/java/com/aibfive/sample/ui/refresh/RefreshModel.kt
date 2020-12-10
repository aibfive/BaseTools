package com.aibfive.sample.ui.refresh

import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Date : 2020/12/10/010
 * Time : 10:09
 * author : Li
 */
class RefreshModel : RefreshContract.Model {

    override fun getData(page : Int, callback: ResponseCallback<ArticleBean>) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val data = RetrofitClient.getApiService().getArticlesSuspend(page)
                if (data.errorCode == 0) {
                    callback.onSuccess(data.data)
                } else {
                    callback.onFail("-1", data.errorMsg)
                }
            } catch (e: Exception) {
                callback.onFail("-1", e.toString())
            }
        }
    }
}