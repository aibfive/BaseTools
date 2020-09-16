package com.aibfive.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.aibfive.sample.adapter.MainItemAdapter;
import com.aibfive.sample.listener.OnItemClickLisenter;
import com.aibfive.sample.ui.tencentmap.TencentMapActivity;
import com.aibfive.sample.util.DataCreator;

public class MainActivity extends AppCompatActivity implements OnItemClickLisenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
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
        }
    }
}