package com.aibfive.sample.ui.network

import android.content.Context
import android.content.Intent
import com.aibfive.basetools.http.RetrofitClient
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import com.aibfive.sample.bean.network.BarInfoBean
import com.aibfive.basetools.http.BaseBean
import com.aibfive.basetools.http.DefaultCallback
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.network.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkActivity : BaseActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, NetworkActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_network
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
    }

}