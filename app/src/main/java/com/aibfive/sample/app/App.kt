package com.aibfive.sample.app

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ProcessLifecycleOwner
import com.aibfive.basetools.BaseApplication
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.ui.lifecycle.ApplicationLifycycleObserver
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator

/**
 * Date : 2020/12/10/010
 * Time : 11:17
 * author : Li
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //监听app处于前后台状态--方式一
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifycycleObserver())
        /*//监听app处于前后台状态--方式二
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            var number = 0
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
                number++
                LogUtil.v("Lifecycle", "App-onActivityStarted:$number")
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
                number--
                LogUtil.v("Lifecycle", "App-onActivityStopped:$number")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })*/
    }

    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                val classicsHeader = ClassicsHeader(context)
                classicsHeader.setTextSizeTitle(14F)
                classicsHeader.setTextSizeTime(12F)
                classicsHeader.setAccentColorId(R.color.color999999)
                classicsHeader.setPrimaryColor(Color.TRANSPARENT)
                return classicsHeader
            }
        })
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                val classicsFooter = ClassicsFooter(context)
                classicsFooter.setTextSizeTitle(14F)
                classicsFooter.setAccentColorId(R.color.color999999)
                classicsFooter.setPrimaryColor(Color.TRANSPARENT)
                return classicsFooter
            }
        })
    }

}