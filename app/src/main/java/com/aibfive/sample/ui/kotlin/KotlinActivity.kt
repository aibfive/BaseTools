package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import kotlinx.coroutines.*
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
        GlobalScope.launch(Dispatchers.Main) {
            async { suspendFun1() }
            async { suspendFun3() }
            async { suspendFun2() }
        }
    }

    suspend fun suspendFun1(){
        delay(2000L)
        LogUtil.v(KotlinActivity::class.simpleName, "suspendFun1")
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