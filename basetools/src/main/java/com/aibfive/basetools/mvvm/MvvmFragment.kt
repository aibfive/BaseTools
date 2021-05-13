package com.aibfive.basetools.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.aibfive.basetools.ui.BaseFragment

abstract class MvvmFragment<VDB : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {

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
        handleData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
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