package com.aibfive.sample.ui.adapter.expandable

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.aibfive.basetools.adapter.itemdecoration.LinearItemDecoration
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.DisplayUtil
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ActivityExpandableBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

class ExpandableActivity : BaseActivity<ActivityExpandableBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ExpandableActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        val firstList = ArrayList<FirstBean>()
        for (i in 1..3){
            val firstBean = FirstBean()
            firstBean.title = "First $i"

            val secondList = ArrayList<FirstBean.SecondBean>()
            for(i in 1..4){
                val secondBean = FirstBean.SecondBean()
                secondBean.title = "Second $i"

                val thirdList = ArrayList<FirstBean.SecondBean.ThirdBean>()
                for(i in 1..5){
                    var third = FirstBean.SecondBean.ThirdBean()
                    third.title = "Third $i"
                    thirdList.add(third)
                }

                secondBean.list = thirdList

                secondList.add(secondBean)
            }
            firstBean.list = secondList
            firstList.add(firstBean)
        }
        val adapter1 = NodeTreeeAdapter(R.layout.item_first, R.layout.item_second, R.layout.item_third)
        adapter1.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                LogUtil.v(ExpandableActivity::class.simpleName, "data：${adapter1.getItem(position)}")
            }
        })
        binding.mRecyclerView.adapter = adapter1
        binding.mRecyclerView.addItemDecoration(LinearItemDecoration(DisplayUtil.dip2px(this, 1f), ContextCompat.getColor(this, R.color.color333333), LinearItemDecoration.VERTICAL_INCLUDE_NULL))
        adapter1.setList(firstList)
    }

}