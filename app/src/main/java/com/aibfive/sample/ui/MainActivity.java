package com.aibfive.sample.ui;

import android.graphics.Color;

import androidx.recyclerview.widget.RecyclerView;

import com.aibfive.basetools.adapter.itemdecoration.GridItemDecoration;
import com.aibfive.basetools.ui.BaseActivity;
import com.aibfive.sample.R;
import com.aibfive.sample.adapter.MainItemAdapter;
import com.aibfive.sample.listener.OnItemClickLisenter;
import com.aibfive.sample.ui.adapter.AdapterActivity;
import com.aibfive.sample.ui.fragment.FragmentActivity;
import com.aibfive.sample.ui.kotlin.KotlinActivity;
import com.aibfive.sample.ui.lifecycle.LifecycleActivity;
import com.aibfive.sample.ui.mvvm.MVVMActivity;
import com.aibfive.sample.ui.network.NetworkActivity;
import com.aibfive.sample.ui.refresh.RefreshDemoActivity;
import com.aibfive.sample.ui.socket.SocketActivity;
import com.aibfive.sample.ui.tencentmap.TencentMapActivity;
import com.aibfive.sample.ui.webview.WebViewActivity;
import com.aibfive.sample.util.DataCreator;

public class MainActivity extends BaseActivity implements OnItemClickLisenter {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new GridItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.dp_10),
                Color.WHITE, true
        ));
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
            case DataCreator.DATA_TYPE_KOTLIN://kotlin
                KotlinActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_NETWORK://network
                NetworkActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_WEBVIEW://webview
                WebViewActivity.start(this, "https://www.baidu.com");
                break;
            case DataCreator.DATA_TYPE_REFRESH://refresh
                RefreshDemoActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_COROUTINE://coroutine
                FragmentActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_TEMPORARY://temporary
                SocketActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_MVVM://mvvm
                MVVMActivity.start(this);
                break;
            case DataCreator.DATA_TYPE_LIFECYCLE://lifecycle
                LifecycleActivity.start(this);
                break;
        }
    }

}