package com.aibfive.basetools.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aibfive.basetools.util.StatusBarUtil

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initView()
        initStatusBar()

    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId() : Int



    /**
     * 初始化数据
     */
    open fun initData(){

    }

    /**
     * 初始化视图
     */
    open fun initView(){

    }


    /**
     * 初始化状态栏
     */
    open fun initStatusBar(){

    }

    fun getActivity() : Activity {
        return this
    }

}