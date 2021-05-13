package com.aibfive.basetools

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.aibfive.basetools.util.ActivityManager

/**
 * Date : 2020/12/11/011
 * Time : 10:51
 * author : Li
 */
open class BaseApplication : Application() {

    companion object{
        lateinit var instance : BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycle()
    }

    /**
     * 注册activity生命周期回调监听器
     * 在该回调监听器使用ActivityManager对activity统一管理
     */
    fun registerActivityLifecycle(){
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.remove(activity)//移除管理栈
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ActivityManager.add(activity)//加入管理栈
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }

}