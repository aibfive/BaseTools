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
        /*RetrofitClient.getAPIService(ApiService::class.java).getBanner().enqueue(object : Callback<BaseBean<BannerBean>>{
            override fun onFailure(call: Call<BaseBean<BannerBean>>, t: Throwable) {
                LogUtil.v(NetworkActivity::class.simpleName, "onFailure" + t.toString())
            }

            override fun onResponse(call: Call<BaseBean<BannerBean>>, response: Response<BaseBean<BannerBean>>) {
                if(response != null) {
                    val data: BaseBean<BannerBean>? = response.body()
                    if(data != null) {
                        LogUtil.v(NetworkActivity::class.simpleName, "onResponse" + data.toString())
                    }
                }
            }
        })*/
        /*RetrofitClient.getAPIService(ApiService::class.java).getBarIndex(1).enqueue(object : DefaultCallback<BarInfoBean>() {

            override fun onFail(code: Int, error: String) {
            }

            override fun onSuccess(data: BarInfoBean) {
            }
        })*/
        GlobalScope.launch {
            LogUtil.v(NetworkActivity::class.simpleName, "Thread.Name1-->"+Thread.currentThread().name)
            RetrofitClient.getAPIService(ApiService::class.java).getBarIndexDeferred(1).await()
            LogUtil.v(NetworkActivity::class.simpleName, "data-->")
            LogUtil.v(NetworkActivity::class.simpleName, "Thread.Name2-->"+Thread.currentThread().name)
        }
    }

}