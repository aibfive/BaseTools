package com.aibfive.sample.ui.adapter.griditem

import android.graphics.Color
import com.aibfive.sample.R
import com.aibfive.sample.bean.base.CommonBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date : 2020/10/30/030
 * Time : 16:37
 * author : Li
 */
class GridItemDecorationAdapter : BaseQuickAdapter<CommonBean, BaseViewHolder>(R.layout.item_grid) {


    override fun convert(holder: BaseViewHolder, item: CommonBean) {
        holder?.setText(R.id.tv_name, item?.name)
        when(getItemPosition(item)){
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