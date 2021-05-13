package com.aibfive.sample.ui.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.aibfive.basetools.util.LogUtil

class ActivityLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onStart(){
        LogUtil.v("Lifecycle", "onStart")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        LogUtil.v("Lifecycle", "onPause")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onDestory(){
        LogUtil.v("Lifecycle", "onDestory")
    }

}