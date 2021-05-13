package com.aibfive.wanandroid.ui

import android.content.res.TypedArray
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aibfive.basetools.util.StatusBarUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.ui.base.StatusBarActivity
import com.aibfive.wanandroid.ui.collection.CollectionFragment
import com.aibfive.wanandroid.ui.find.FindFragment
import com.aibfive.wanandroid.ui.home.HomeFragment
import com.aibfive.wanandroid.ui.my.MyFragment
import com.aibfive.wanandroid.ui.problem.ProblemFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_wan_android.*

class WanAndroidActivity : StatusBarActivity() {

    lateinit var fragments : List<Fragment>
    lateinit var tabTexts : Array<String>
    lateinit var tabIcons : TypedArray

    override fun getLayoutId(): Int {
        return R.layout.activity_wan_android
    }

    override fun initStatusBar() {
        StatusBarUtil.setImmersiveTransparentStatusBar(this)
        StatusBarUtil.setStatusBarLightMode(this)
    }

    override fun initData() {
        super.initData()
        fragments = arrayListOf(HomeFragment(), ProblemFragment(), CollectionFragment(), FindFragment(), MyFragment())
        tabTexts = resources.getStringArray(R.array.main_tab_texts)
        //获取xml的图片array的id时，需要使用TypedArray
        tabIcons = resources.obtainTypedArray(R.array.main_tab_icons)
    }

    override fun initView() {
        super.initView()
        viewPager2.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        TabLayoutMediator(tabLayout, viewPager2, true, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = tabTexts[position]
                tab.setIcon(tabIcons.getResourceId(position, 0))
            }
        }).attach()
    }
}