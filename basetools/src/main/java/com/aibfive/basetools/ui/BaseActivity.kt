package com.aibfive.basetools.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initView()
    }

    abstract fun getLayoutId() : Int

    open fun initData(){

    }

    open fun initView(){

    }

}