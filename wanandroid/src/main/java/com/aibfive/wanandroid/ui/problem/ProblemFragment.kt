package com.aibfive.wanandroid.ui.problem

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.aibfive.basetools.ui.BaseFragment
import com.aibfive.wanandroid.R
import com.aibfive.basetools.util.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_problem.*

class ProblemFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_problem
    }

    override fun initView() {
        super.initView()
    }

    override fun initStatusBar() {
       // tvTitle.setOnClickListener {
            StatusBarUtil.setParentStatusBarPadding(context, tvTitle)
       // }

    }
}