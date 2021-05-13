package com.aibfive.wanandroid.ui.base

import android.graphics.Color
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.basetools.mvvm.MvvmActivity
import com.aibfive.basetools.util.StatusBarUtil

//import com.aibfive.basetools.util.StatusBarUtil

/**
 * @author: 小李
 * @date: 2021/5/3 20:51
 */
abstract class StatusBarMvvmActivity<B : ViewDataBinding, VM : BaseViewModel> : MvvmActivity<B, VM>() {


    /**
     * 初始化状态栏
     */
    override fun initStatusBar(){
        StatusBarUtil.setStatusBarColor(this, Color.WHITE)
        StatusBarUtil.setStatusBarLightMode(this)
    }

}