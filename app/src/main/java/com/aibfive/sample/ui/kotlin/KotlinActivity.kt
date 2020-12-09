package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.basetools.util.sharedpreferences.SharedPreferencesUtils
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_kotlin.*

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
    }

    override fun initView() {
        super.initView()
        customWebView.loadUrl("https://www.baidu.com")
    }

    override fun onResume() {
        super.onResume()
        customWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        customWebView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        customWebView.onDestroy()
    }

}
