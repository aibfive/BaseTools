package com.aibfive.wanandroid.ui.problem

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aibfive.basetools.ui.BaseFragment
import com.aibfive.basetools.util.StatusBarUtil
import com.aibfive.wanandroid.databinding.FragmentProblemBinding

class ProblemFragment : BaseFragment<FragmentProblemBinding>() {

    override fun initView() {
        super.initView()
    }

    override fun initStatusBar() {
       // tvTitle.setOnClickListener {
            StatusBarUtil.setParentStatusBarPadding(context, binding.tvTitle)
       // }

    }
}