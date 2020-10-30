package com.aibfive.sample;

import android.support.v7.widget.RecyclerView;

import com.aibfive.basetools.ui.BaseActivity;
import com.aibfive.sample.adapter.MainItemAdapter;
import com.aibfive.sample.listener.OnItemClickLisenter;
import com.aibfive.sample.ui.adapter.AdapterActivity;
import com.aibfive.sample.ui.tencentmap.TencentMapActivity;
import com.aibfive.sample.util.DataCreator;

public class MainActivity extends BaseActivity implements OnItemClickLisenter {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MainItemAdapter adapter = new MainItemAdapter(this, DataCreator.buildMainItemData(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLisenter(this);
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case DataCreator.DATA_TYPE_TENCENT_MAP://腾讯地图
                TencentMapActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_ADAPTER://适配器
                AdapterActivity.start(this);
                break;
        }
    }
}