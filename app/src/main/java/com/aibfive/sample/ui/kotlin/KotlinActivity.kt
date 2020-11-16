package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import kotlinx.coroutines.*

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
        /*GlobalScope.launch {
            LogUtil.v(KotlinActivity::class.simpleName, "start--1")
            val job = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
                LogUtil.v(KotlinActivity::class.simpleName, "start--2")
                //delay(1000L)
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-1")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-2")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-3")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-4")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-5")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-6")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-7")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-8")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-9")
                LogUtil.v(KotlinActivity::class.simpleName, "start--3-10")
            }
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-1")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-2")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-3")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-4")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-5")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-6")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-7")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-8")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-9")
            LogUtil.v(KotlinActivity::class.simpleName, "start--4-10")
            //job.join()
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-1")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-2")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-3")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-4")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-5")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-6")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-7")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-8")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-9")
            LogUtil.v(KotlinActivity::class.simpleName, "start--5-10")
        }*/
        /*GlobalScope.launch {
            suspendJoin()
        }*/

        /*LogUtil.v(KotlinActivity::class.simpleName, "default--1")
        val job1 = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
            //delay(3000L)
            LogUtil.v(KotlinActivity::class.simpleName, "default--2")
        }
        LogUtil.v(KotlinActivity::class.simpleName, "default--3")
        job1.cancel()
        LogUtil.v(KotlinActivity::class.simpleName, "default--4")*/
        Unit
        /*GlobalScope.launch {
            LogUtil.v(KotlinActivity::class.simpleName, "join--1")
            val job = GlobalScope.launch {
                delay(5000L)
                LogUtil.v(KotlinActivity::class.simpleName, "join--2")
            }
            job.start()
            LogUtil.v(KotlinActivity::class.simpleName, "join--3")
        }*/

        /*runBlocking {
            launch {
                delay(5000L)
                LogUtil.v(KotlinActivity::class.simpleName, "runBlocking--1")
            }
        }
        LogUtil.v(KotlinActivity::class.simpleName, "main--1")
        runBlocking {
            coroutineScope {
                delay(5000L)
                LogUtil.v(KotlinActivity::class.simpleName, "coroutineScope--1")
            }
        }
        LogUtil.v(KotlinActivity::class.simpleName, "main--2")*/

        /*GlobalScope.launch {
            val job = launch {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // 延迟一段时间
            println("main: I'm tired of waiting!")
            job.cancel() // 取消该作业
            //job.join() // 等待作业执行结束
            println("main: Now I can quit.")
        }

        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) { // 可以被取消的计算循环
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // 等待一段时间
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // 取消该作业并等待它结束
            println("main: Now I can quit.")
        }*/

        //delay(1300L) // 在延迟后退出
        /*GlobalScope.launch {
            withTimeout(1300L){
                repeat(1000) {
                    println("I'm sleeping ...")
                    delay(500L)
                }
            }
        }*/


        val value = foo(5,  {
            intI : Int -> intI
        })
        LogUtil.v(KotlinActivity::class.simpleName, "value-->$value")
    }

    val lambda = {
        a : Int, b : Int -> a + b
    }

    fun foo(
            i : Int,
            f : (inI : Int) -> Int
    ) : Int {
        var valueI = i
        valueI += f(valueI)
        return valueI
    }

    suspend fun suspendJoin(){
        LogUtil.v(KotlinActivity::class.simpleName, "join--1")
        val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
            delay(1000L)
            LogUtil.v(KotlinActivity::class.simpleName, "join--2")
        }
        LogUtil.v(KotlinActivity::class.simpleName, "join--3")
        job1.join()
        LogUtil.v(KotlinActivity::class.simpleName, "join--4")
    }

    suspend fun suspendAtomic(){
        LogUtil.v(KotlinActivity::class.simpleName, "atomic--1")
        val job1 = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            delay(1000L)
            LogUtil.v(KotlinActivity::class.simpleName, "atomic--2")
        }
        LogUtil.v(KotlinActivity::class.simpleName, "atomic--3")
        job1.cancel()
        LogUtil.v(KotlinActivity::class.simpleName, "atomic--4")
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