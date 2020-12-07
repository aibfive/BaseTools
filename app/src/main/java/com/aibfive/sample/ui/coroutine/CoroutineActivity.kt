package com.aibfive.sample.ui.coroutine

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.bean.Data
import com.aibfive.sample.network.ApiService
import com.aibfive.sample.network.RetrofitClient
import com.aibfive.sample.ui.refresh.RefreshActivity
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.http.Tag
import kotlin.concurrent.thread

class CoroutineActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CoroutineActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_coroutine
    }

    override fun initData() {
        super.initData()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                RetrofitClient.getApiService().getArticlesSuspend(1)
            } catch (e: Exception) {
                LogUtil.v(CoroutineActivity::class.simpleName, "e：${e.toString()}")
            }
            try {
                RetrofitClient.getApiService().getArticlesSuspend1(1)
            } catch (e: Exception) {
                LogUtil.v(CoroutineActivity::class.simpleName, "e1：${e.toString()}")
            }
        }


        RetrofitClient.launchUI(
                {
                    RetrofitClient.getApiService().getArticlesSuspend(1)
                },
                {
                    e: Exception -> Unit
                    LogUtil.v(CoroutineActivity::class.simpleName, "e：${e.toString()}")
                }
        )

    }

    override fun initView() {
        super.initView()
    }
}