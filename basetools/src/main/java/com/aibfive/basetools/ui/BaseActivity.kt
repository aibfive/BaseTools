package com.aibfive.basetools.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.aibfive.basetools.util.StatusBarUtil
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding : VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initData()
        initView()
        initStatusBar()
    }

    /**
     * 获取视图绑定器
     * @return VB
     */
    fun getViewBinding() : VB {
        /**
         * 使用反射获取VB
         * 正常情况下获取方式为
         * VB.inflate(LayoutInflater.from(this))
         */
        val type = this.javaClass.genericSuperclass
        val cls = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = cls.getMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, LayoutInflater.from(this)) as VB
    }

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