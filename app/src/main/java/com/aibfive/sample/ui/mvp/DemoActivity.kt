package com.aibfive.sample.ui.mvp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.aibfive.basetools.mvp.MvpActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.bean.ArticleBean
import com.aibfive.sample.databinding.ActivityDemoBinding

class DemoActivity : MvpActivity<ActivityDemoBinding, DemoPresenter, DemoModel>(), DemoContract.View {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DemoActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        mPresenter?.getDemo()
    }

    override fun getDemoSuccess(data : ArticleBean?) {
        LogUtil.v(DemoActivity::class.simpleName, "获取数据成功：${data!!.datas[0]!!.desc}")
    }

    override fun showLoading() {
        super.showLoading()
        LogUtil.v(DemoActivity::class.simpleName, "显示加载")
    }

    override fun hideLoading() {
        super.hideLoading()
        LogUtil.v(DemoActivity::class.simpleName, "隐藏加载")
    }
}
