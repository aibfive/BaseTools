package com.aibfive.basetools.ui

import androidx.viewbinding.ViewBinding

/**
 * ViewPager的Adapter.behavior必须使用FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 * Date : 2020/12/11/011
 * Time : 14:30
 * author : Li
 */
abstract class BaseLazyFragment<VB : ViewBinding> : BaseFragment<VB>() {

    var isFirstResume = true//onResume()是否首次执行

    override fun initView() {
        super.initView()
    }

    override fun onResume() {
        super.onResume()
        /**
         * 如ViewPager的Adapter.behavior使用了FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
         * 是否懒加载在该方法判断。
         */
        if(isFirstResume){
            lazyLoad()
            isFirstResume = false
        }
        onUserVisible()
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    /**
     * 用户可见时执行
     */
    fun onUserVisible() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstResume = true
    }

}