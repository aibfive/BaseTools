package com.aibfive.sample.ui.webview

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    companion object {
        const val URL = "url"

        @JvmStatic
        fun start(context: Context, url : String) {
            val starter = Intent(context, WebViewActivity::class.java)
            starter.putExtra(URL, url)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        binding.customWebView.loadUrl(intent.getStringExtra(URL))
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

    override fun onBackPressed() {
        if(binding.customWebView.onBackPressed()) {
            super.onBackPressed()
        }
    }

}