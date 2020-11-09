package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

/**
 * Kotlin Demo
 */
class KotlinActivity : BaseActivity(), CoroutineScope {

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
        GlobalScope.launch {
            delay(1000L)
            LogUtil.v(KotlinActivity::class.simpleName, "World!")
        }
        LogUtil.v(KotlinActivity::class.simpleName, "Hello，")
        thread {
            Thread.sleep(1000L)
        }
        repeat(1000){

        }
        val join = GlobalScope.launch {

        }
        GlobalScope.async(Dispatchers.IO){

        }
    }

    override fun initView() {
        super.initView()
    }
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}