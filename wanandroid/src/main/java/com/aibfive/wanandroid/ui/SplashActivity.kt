package com.aibfive.wanandroid.ui

import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.AppUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.databinding.ActivitySplashBinding

/**
 * @author: 小李
 * @date: 2021/6/8 22:22
 * 闪屏页
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView() {
        super.initView()
        val anim = AnimationUtils.loadAnimation(baseContext, R.anim.anim_hide_to_show)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

                AppUtil.startActivity(getActivity(), WanAndroidActivity::class.java)
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        binding.tvLogo.startAnimation(anim)
    }

}