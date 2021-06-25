package com.aibfive.sample.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivityAdapterBinding
import com.aibfive.sample.ui.adapter.griditem.GridItemDecorationActivity
import com.aibfive.sample.ui.adapter.singlemultipleselect.SingleMultipleSelectActivity

/**
 * 适配器
 */
class AdapterActivity : BaseActivity<ActivityAdapterBinding>(), View.OnClickListener{

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, AdapterActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initView() {
        super.initView()
        binding.gridItemDecorationTv.setOnClickListener(this)
        binding.singleMultipleSelectTv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.gridItemDecorationTv->{//网格分割线
                    GridItemDecorationActivity.start(this)
                }
                R.id.singleMultipleSelectTv->{//单多选
                    SingleMultipleSelectActivity.start(this)
                }
            }
        }
    }
}