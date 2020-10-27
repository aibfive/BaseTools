package com.aibfive.sample.ui.adapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R

/**
 * 单多选适配器
 */
class SingleMultipleSelectActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_single_multiple_select
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.iv_back -> finish()
            }
        }
    }
}