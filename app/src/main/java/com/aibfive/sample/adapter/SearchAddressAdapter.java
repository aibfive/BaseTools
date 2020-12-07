package com.aibfive.sample.adapter;

import androidx.annotation.NonNull;

import com.aibfive.basetools.adapter.SelectedAdapter;
import com.aibfive.sample.R;
import com.aibfive.sample.bean.tencentmap.AddressBean;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Date : 2020/9/17/017
 * Time : 13:45
 * author : Li
 */
public class SearchAddressAdapter extends SelectedAdapter<AddressBean> {

    public SearchAddressAdapter() {
        super(R.layout.item_search_address);
        setSelectMode(true, false);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, AddressBean item) {
        holder.setText(R.id.tv_name, item.getTitle());
        holder.setText(R.id.tv_detail, item.getAddress());
        holder.setVisible(R.id.iv_select, isSelected(getData().indexOf(item)));
    }
}
