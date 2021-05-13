package com.aibfive.basetools.mvvm

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import java.io.Serializable

abstract class MvvmActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding = DataBindingUtil.setContentView<B>(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.setVariable(viewModel.id, viewModel)
        initData()
        initView()
        initStatusBar()
        handleData()
    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId() : Int

    /**
     * 获取ViewModel的class
     */
    abstract fun getViewModelClass() : Class<VM>

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

    /**
     * 处理数据
     */
    open fun handleData(){

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}