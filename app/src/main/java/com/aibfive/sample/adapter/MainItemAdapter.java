package com.aibfive.sample.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aibfive.sample.bean.MainItemBean;
import com.aibfive.sample.listener.OnItemClickLisenter;
import com.aibfive.sample.R;

import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyHolder> {

    private Context context;
    private List<MainItemBean> list;
    private OnItemClickLisenter onItemClickLisenter;

    public MainItemAdapter(Context context, List<MainItemBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        this.onItemClickLisenter = onItemClickLisenter;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.nameTv.setText(list.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                if (null != onItemClickLisenter) {
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        AppCompatTextView nameTv;
        public MyHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_name);
        }
    }

}
