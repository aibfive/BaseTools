package com.aibfive.sample.ui.temporary

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_temporary.*
import java.util.*

class TemporaryActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TemporaryActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_temporary
    }

    override fun initView() {
        super.initView()
        tvStart.setOnClickListener {
            getCodeView.start()
        }
        tvCancel.setOnClickListener {
            getCodeView.cancel()
        }
    }

}