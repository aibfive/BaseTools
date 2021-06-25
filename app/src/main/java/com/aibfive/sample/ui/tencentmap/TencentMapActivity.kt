package com.aibfive.sample.ui.tencentmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivityTencentMapBinding

class TencentMapActivity : BaseActivity<ActivityTencentMapBinding>(), View.OnClickListener {
    
    companion object{

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TencentMapActivity::class.java)
            context.startActivity(starter)
        }

    }

    override fun initView() {
        super.initView()
        binding.tvSendLocation.setOnClickListener(this)
        binding.tvSearchAddress.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.tv_send_location -> SendLocationActivity.start(this)
                R.id.tv_search_address -> SearchAddressActivity.start(this)
            }
        }
    }
}