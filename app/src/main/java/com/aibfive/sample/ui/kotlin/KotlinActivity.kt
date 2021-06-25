package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.databinding.ActivityKotlinBinding

/**
 * Kotlin Demo
 */
class KotlinActivity : BaseActivity<ActivityKotlinBinding>() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, KotlinActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        binding.customWebView.loadUrl("https://www.baidu.com")
    }

    override fun onResume() {
        super.onResume()
        binding.customWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.customWebView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.customWebView.onDestroy()
    }

}
