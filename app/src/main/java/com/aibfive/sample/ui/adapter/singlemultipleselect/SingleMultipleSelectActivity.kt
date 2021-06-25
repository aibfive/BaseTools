package com.aibfive.sample.ui.adapter.singlemultipleselect

import android.content.Context
import android.content.Intent
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivitySingleMultipleSelectBinding

/**
 * 单多选适配器
 */
class SingleMultipleSelectActivity : BaseActivity<ActivitySingleMultipleSelectBinding>(), View.OnClickListener {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SingleMultipleSelectActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.ivBack -> finish()
            }
        }
    }
}