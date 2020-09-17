package com.aibfive.sample.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aibfive.sample.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.lbssearch.object.result.SearchResultObject;

import java.util.List;

/**
 * Date : 2020/9/17/017
 * Time : 13:45
 * author : Li
 */
public class SearchAddressAdapter extends BaseQuickAdapter<SearchResultObject.SearchResultData, BaseViewHolder> {

    public SearchAddressAdapter() {
        super(R.layout.item_search_address);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchResultObject.SearchResultData item) {
        helper.setText(R.id.tv_name, item.title);
        helper.setText(R.id.tv_detail, item.address);
    }
}
