package com.aibfive.sample.ui.mvp

import com.aibfive.basetools.mvp.ResponseCallback
import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.network.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Date : 2020/12/8/008
 * Time : 17:55
 * author : Li
 */
class DemoModel : DemoContract.Model {

    override fun getDemo(callback: ResponseCallback<ArticleBean>) {
        GlobalScope.launch {
            try {
                val data = RetrofitClient.getApiService().getArticlesSuspend(1)
                if(data.errorCode == -1){
                    callback.onFail(data.errorCode.toString(), data.errorMsg)
                }else{
                    callback.onSuccess(data.data)
                }
            } catch (e: Exception) {
                callback.onFail("-1", e.toString())
            }

        }
    }

}