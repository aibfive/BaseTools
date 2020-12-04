package com.aibfive.basetools.ui

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.R
import com.aibfive.basetools.util.LogUtil

class DemoActivity : MvpActivity<DemoPresenter>(), DemoContract.DemoDisplay {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DemoActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_demo
    }


    override fun initView() {
        super.initView()
        presenter.demo()
    }

    override fun demo() {
        LogUtil.v(DemoActivity::class.simpleName, "Mvp-demo")
    }
}