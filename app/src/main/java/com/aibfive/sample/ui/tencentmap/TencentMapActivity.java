package com.aibfive.sample.ui.tencentmap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aibfive.sample.R;

/**
 * 腾讯地图
 */
public class TencentMapActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TencentMapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_map);
    }

    /**
     * 发送定位
     * @param view
     */
    public void onSendLocationClick(View view){
        SendLocationActivity.start(this);
    }

    /**
     * 搜索地址
     * @param view
     */
    public void onSearchAddressClick(View view){
        SearchAddressActivity.Companion.start(this);
    }
}