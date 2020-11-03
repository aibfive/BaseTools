package com.aibfive.sample.ui.adapter.griditem

import android.graphics.Color
import com.aibfive.basetools.adapter.BaseRVAdapter
import com.aibfive.basetools.adapter.BaseRVHolder
import com.aibfive.sample.R
import com.aibfive.sample.bean.base.CommonBean

/**
 * Date : 2020/10/30/030
 * Time : 16:37
 * author : Li
 */
class GridItemDecorationAdapter : BaseRVAdapter<CommonBean>(R.layout.item_grid) {

    override fun onBindVH(holder: BaseRVHolder?, data: CommonBean?, position: Int) {
        holder?.setText(R.id.tv_name, data?.name)
        when(position){
            0-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.RED)
            }
            1-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
            2-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            3-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLUE)
            }
            4-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            5-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
            6-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.RED)
            }
            7-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
            8-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            9-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLUE)
            }
            10-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            11-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
            12-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.RED)
            }
            13-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
            14-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            15-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLUE)
            }
            16-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.BLACK)
            }
            17-> if (holder != null) {
                holder.itemView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}