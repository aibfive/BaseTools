package com.aibfive.sample.ui.refresh

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R

class RefreshActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, RefreshActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_refresh
    }
}