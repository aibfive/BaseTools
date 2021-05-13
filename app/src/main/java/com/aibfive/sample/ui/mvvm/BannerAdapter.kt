package com.aibfive.sample.ui.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.aibfive.sample.R
import com.aibfive.sample.databinding.ItemBannerBinding

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.MyViewHolder> {

    var data : ArrayList<BannerBeanItem> = ArrayList()

    constructor()

    constructor(data : ArrayList<BannerBeanItem>){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBannerBinding!!.banner = data.get(position)
        holder.itemBannerBinding!!.executePendingBindings()
    }

    class MyViewHolder : RecyclerView.ViewHolder{
        var itemBannerBinding : ItemBannerBinding? = null
        constructor(itemView: View) : super(itemView){
            itemBannerBinding = DataBindingUtil.bind<ItemBannerBinding>(itemView)
        }
    }

    fun addData(data : ArrayList<BannerBeanItem>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}