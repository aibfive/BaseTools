package com.aibfive.sample.ui.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_mvvm.*

class MVVMActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MVVMActivity::class.java)
            context.startActivity(starter)
        }
    }

    //lateinit var binding : ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
      
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm)
        var viewModel :BannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        viewModel.data.observe(this, object  : Observer<BannerBean>{
            override fun onChanged(t: BannerBean?) {
                LogUtil.v(MVVMActivity::class.simpleName, "收到了")
                if (t != null) {
                    (recyclerView.adapter as BannerAdapter).addData(t)
                }
            }
        })
        recyclerView.adapter = BannerAdapter()
        BannerRepository(viewModel).getBannerData()
    }

    override fun onDestroy() {
        super.onDestroy()
        //binding.unbind()
    }

}