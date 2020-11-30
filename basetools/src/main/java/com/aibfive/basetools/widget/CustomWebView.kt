package com.aibfive.basetools.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.aibfive.basetools.R
import com.aibfive.basetools.util.LogUtil

/**
 * Date : 2020/11/30/030
 * Time : 10:20
 * author : Li
 */
class CustomWebView : FrameLayout {

    var webView : WebView? = null
    private var progressBar : ProgressBar? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView(){
        initProgressBar()
        initWebView()
        addView(webView)
        addView(progressBar)
    }

    private fun initWebView(){
        webView = WebView(context)
        webView?.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        /**
         * WebView配置
         */
        val settings = webView?.settings
        settings?.javaScriptEnabled = true  //是否开启JS支持（重要）

        /**
         * 控件客户端
         */
        webView?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar?.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar?.visibility = View.GONE
            }
        }

        /**
         * 浏览器客户端
         */
        webView?.webChromeClient= object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                LogUtil.v(CustomWebView::class.simpleName, "newProgress：" + newProgress)
                progressBar?.progress = newProgress
            }
        }
    }

    private fun initProgressBar(){
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressBar?.progressDrawable = resources.getDrawable(R.drawable.progress_drawable)
        progressBar?.max = 100
        progressBar?.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 40)
    }

    fun loadUrl(url : String){
        webView?.loadUrl(url)
    }

    fun onResume(){
        webView?.onResume()
    }

    fun onPause(){
        webView?.onPause()
    }

    fun onDestroy(){
        webView?.destroy()
    }

    fun onBackPressed() : Boolean{
        if(webView?.canGoBack()!!){
            webView?.goBack()
            return false
        }else{
            return true
        }
    }

}