package com.aibfive.wanandroid.ui

import android.app.Activity
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.AppUtil
import com.aibfive.basetools.util.LogUtil
import com.aibfive.wanandroid.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author: 小李
 * @date: 2021/6/8 22:22
 * 闪屏页
 */
class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        super.initView()
        val anim = AnimationUtils.loadAnimation(baseContext, R.anim.anim_hide_to_show)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                AppUtil.startActivity(getActivity(), WanAndroidActivity::class.java, true)
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        tvLogo.startAnimation(anim)
    }
}