package com.aibfive.sample.ui.tencentmap

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_tencent_map.*

class TencentMapActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tencent_map)
        tv_send_location.setOnClickListener(this)
        tv_search_address.setOnClickListener(this)
    }
    
    companion object{

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TencentMapActivity::class.java)
            context.startActivity(starter)
        }

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