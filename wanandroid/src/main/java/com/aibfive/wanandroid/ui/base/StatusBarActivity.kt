package com.aibfive.wanandroid.ui.base

import android.graphics.Color
import android.os.Bundle
import com.aibfive.basetools.R
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.StatusBarUtil

/**
 * @author: 小李
 * @date: 2021/5/3 20:49
 */
abstract class StatusBarActivity : BaseActivity() {

    /**
     * 初始化状态栏
     */
    override fun initStatusBar(){
        StatusBarUtil.setStatusBarColor(this, Color.WHITE)
        StatusBarUtil.setStatusBarLightMode(this)
    }

}