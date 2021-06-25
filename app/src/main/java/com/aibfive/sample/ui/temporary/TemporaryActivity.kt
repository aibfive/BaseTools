package com.aibfive.sample.ui.temporary

import android.content.Context
import android.content.Intent
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.basetools.widget.ClickRecyclerView
import com.aibfive.sample.databinding.ActivityTemporaryBinding

class TemporaryActivity : BaseActivity<ActivityTemporaryBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TemporaryActivity::class.java)
            context.startActivity(starter)
        }
    }


    override fun initView() {
        super.initView()
        binding.clickRecyclerView.setOnSingleClickListener(object : ClickRecyclerView.OnSingleClickListener{
            override fun onSingleClick(v: View?) {
                LogUtil.v(TemporaryActivity::class.simpleName, "点击了")
            }
        })
    }

}