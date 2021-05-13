package com.aibfive.sample.ui.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.aibfive.basetools.util.LogUtil

class ApplicationLifycycleObserver : LifecycleObserver {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onAppForeground(){
        LogUtil.v("Lifecycle", "前台")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    fun onAppBackground(){
        LogUtil.v("Lifecycle", "后台")
    }

}