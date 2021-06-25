package com.aibfive.sample.ui.coroutine

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivityCoroutineBinding

class CoroutineActivity : BaseActivity<ActivityCoroutineBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, CoroutineActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
    }
}