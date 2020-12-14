package com.aibfive.sample.ui.fragment

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.ui.BaseLazyFragment
import com.aibfive.sample.R
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : BaseActivity() {

    lateinit var fragment1 : Fragment1
    lateinit var fragment2 : Fragment2
    lateinit var fragment3 : Fragment3

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, FragmentActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_fragment
    }

    override fun initView() {
        super.initView()
        fragment1 = Fragment1.newInstance()
        fragment2 = Fragment2.newInstance()
        fragment3 = Fragment3.newInstance()

        /*val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mFrameLayout, fragment1)
        transaction.add(R.id.mFrameLayout, fragment2)
        transaction.add(R.id.mFrameLayout, fragment3)
        transaction.commit()*/

        val list = ArrayList<BaseLazyFragment>()
        list.add(fragment1)
        list.add(fragment2)
        list.add(fragment3)


        mViewPager.offscreenPageLimit = list.size
        mViewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
            override fun getItem(position: Int): Fragment {
                return list[position]
            }

            override fun getCount(): Int {
                return list.size
            }
        }

        tvFragment1.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.show(fragment1)
            transaction.hide(fragment2)
            transaction.hide(fragment3)
            transaction.commit()
        }
        tvFragment2.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.hide(fragment1)
            transaction.show(fragment2)
            transaction.hide(fragment3)
            transaction.commit()
        }
        tvFragment3.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.hide(fragment1)
            transaction.hide(fragment2)
            transaction.show(fragment3)
            transaction.commit()
        }
    }

}