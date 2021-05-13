package com.aibfive.sample.ui.lifecycle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R

class LifecycleActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LifecycleActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(ActivityLifecycleObserver())
        
    }

    override fun onStart() {
        super.onStart()
        LogUtil.v("Lifecycle", "LifecycleActivity--onStart")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.v("Lifecycle", "LifecycleActivity--onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.v("Lifecycle", "LifecycleActivity--onDestroy")
    }

}