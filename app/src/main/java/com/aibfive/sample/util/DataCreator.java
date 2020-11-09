package com.aibfive.sample.util;

import android.content.Context;

import com.aibfive.sample.R;
import com.aibfive.sample.bean.MainItemBean;
import com.aibfive.sample.bean.base.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {

    //数据类型
    public static final int DATA_TYPE_TENCENT_MAP = 0;//腾讯地图
    public static final int DATA_TYPE_ADAPTER = 1;//适配器
    public static final int DATA_TYPE_KOTLIN = 2;//kotlin
    public static final int DATA_TYPE_NETWORK = 3;//network

    /**
     * 创建主页数据
     * @param context
     * @return
     */
    public static List<MainItemBean> buildMainItemData(Context context){
        List<MainItemBean> data = new ArrayList<>();
        data.add(new MainItemBean(DATA_TYPE_TENCENT_MAP, context.getString(R.string.tencent_map)));
        data.add(new MainItemBean(DATA_TYPE_ADAPTER, context.getString(R.string.adapter)));
        data.add(new MainItemBean(DATA_TYPE_KOTLIN, context.getString(R.string.kotlin)));
        data.add(new MainItemBean(DATA_TYPE_NETWORK, context.getString(R.string.network)));
        return data;
    }

    public static List<CommonBean> buildCommonData(Context context){
        List<CommonBean> data = new ArrayList<>();
        data.add(new CommonBean("数据0",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据1",1));
        data.add(new CommonBean("数据2",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据3",1));
        data.add(new CommonBean("数据4",1));
        data.add(new CommonBean("数据数据数据数据数据5",1));
        data.add(new CommonBean("数据6",1));
        data.add(new CommonBean("数据7",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据8",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据9",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数据数数据数据数据10",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据11",1));
        data.add(new CommonBean("数据数据数据数据数据数据12",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据13",1));
        data.add(new CommonBean("数据数据数据数据14",1));
        data.add(new CommonBean("数据数据数据数据数据数据数据数据数据15",1));
        data.add(new CommonBean("数据据数据数数据数据据数据数数据数据据数据数数据数据据数据数数据16",1));
        /*int size = 20;
        for(int i = 0; i < size; i++){
            data.add(new CommonBean(context.getString(R.string.format_data, i), i));
        }*/
        return data;
    }

}
