package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.http.BaseBean
import com.aibfive.basetools.http.RetrofitClient
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.bean.network.BarInfoBean
import com.aibfive.sample.network.ApiService
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.broadcast
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.EmptyCoroutineContext.plus

/**
 * Kotlin Demo
 */
class KotlinActivity : BaseActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, KotlinActivity::class.java)
            context.startActivity(starter)
        }
    }
    
    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun initData() {
        super.initData()
        LogUtil.v(KotlinActivity::class.simpleName, "Thread0-->"+Thread.currentThread().name)
        GlobalScope.launch(Dispatchers.Main) {
            suspendFun1()
            LogUtil.v(KotlinActivity::class.simpleName, "Thread2-->"+Thread.currentThread().name)
        }
        LogUtil.v(KotlinActivity::class.simpleName, "Thread3-->"+Thread.currentThread().name)
        /*GlobalScope.launch(Dispatchers.Main) {
            LogUtil.v(KotlinActivity::class.simpleName, "Thread1-->"+Thread.currentThread().name)
            val infoBean :BaseBean<BarInfoBean> = RetrofitClient.getAPIService(ApiService::class.java).getBarIndexDeferred(1).await()
            LogUtil.v(KotlinActivity::class.simpleName, "Thread2-->"+Thread.currentThread().name)
            tvContent.text = infoBean.data.address
            LogUtil.v(KotlinActivity::class.simpleName, "Thread3-->"+Thread.currentThread().name)
        }
        LogUtil.v(KotlinActivity::class.simpleName, "Thread4-->"+Thread.currentThread().name)
        CoroutineScope(Dispatchers.Main)

        CoroutineScope(Dispatchers.Main).launch {

        }*/
    }

    suspend fun suspendFun1(){
        //delay(2000L)
        LogUtil.v(KotlinActivity::class.simpleName, "Thread1-->"+Thread.currentThread().name)
    }

    suspend fun suspendFun2(){
        LogUtil.v(KotlinActivity::class.simpleName, "suspendFun2")
    }

    suspend fun suspendFun3(){
        delay(1000L)
        LogUtil.v(KotlinActivity::class.simpleName, "suspendFun3")
    }


    override fun initView() {
        super.initView()
    }
}