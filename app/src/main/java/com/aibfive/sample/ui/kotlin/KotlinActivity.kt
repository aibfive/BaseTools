package com.aibfive.sample.ui.kotlin

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * Kotlin Demo
 */
class KotlinActivity : BaseActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, KotlinActivity::class.java)
            context.startActivity(starter)
        }
    }
    
    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        searchView.setOnEditorActionListener(
                {
                    v: TextView?, actionId: Int, event: KeyEvent? -> Boolean
                    if(actionId == EditorInfo.IME_ACTION_SEARCH){
                        LogUtil.v(KotlinActivity::class.simpleName, "search-->"+v?.text)
                    }
                    false
                }
        )
    }

}
