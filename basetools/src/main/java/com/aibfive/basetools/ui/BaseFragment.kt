package com.aibfive.basetools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * Date : 2020/12/11/011
 * Time : 14:15
 * author : Li
 */
abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    private var _binding : VB? = null
    val binding : VB get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initStatusBar()
    }

    /**
     * 获取视图绑定器
     * @return VB
     */
    fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) : VB{
        /**
         * 使用反射获取VB
         * 正常情况下获取方式为
         * VB.inflate(inflater, container, false)
         */
        val type = this.javaClass.genericSuperclass
        val cls = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = cls.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        return method.invoke(null, inflater, container, false) as VB
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


}