package com.aibfive.sample.ui.webview

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    companion object {
        const val URL = "url"

        @JvmStatic
        fun start(context: Context, url : String) {
            val starter = Intent(context, WebViewActivity::class.java)
            starter.putExtra(URL, url)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        customWebView.loadUrl(intent.getStringExtra(URL))
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

    override fun onBackPressed() {
        if(customWebView.onBackPressed()) {
            super.onBackPressed()
        }
    }

}