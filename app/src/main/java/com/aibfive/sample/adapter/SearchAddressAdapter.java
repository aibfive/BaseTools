package com.aibfive.sample.adapter;

import android.support.annotation.NonNull;

import com.aibfive.basetools.adapter.SelectedAdapter;
import com.aibfive.sample.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.lbssearch.httpresponse.Poi;

/**
 * Date : 2020/9/17/017
 * Time : 13:45
 * author : Li
 */
public class SearchAddressAdapter extends SelectedAdapter<Poi> {

    public SearchAddressAdapter() {
        super(R.layout.item_search_address);
        setSelectMode(true, false);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Poi item) {
        holder.setText(R.id.tv_name, item.title);
        holder.setText(R.id.tv_detail, item.address);
        holder.setVisible(R.id.iv_select, isSelected(getData().indexOf(item)));
    }
}
