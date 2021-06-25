package com.aibfive.basetools.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aibfive.basetools.ui.BaseFragment

abstract class MvvmFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    lateinit var binding : VDB
    lateinit var viewModel : VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding = DataBindingUtil.inflate<VDB>(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        binding.setVariable(viewModel.id, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initStatusBar()
        handleData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
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

    /**
     * 获取ViewModel的class
     */
    abstract fun getViewModelClass() : Class<VM>

    /**
     * 处理数据
     */
    open fun handleData(){

    }

}