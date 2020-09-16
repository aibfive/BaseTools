package com.aibfive.sample.util;

import android.content.Context;

import com.aibfive.sample.bean.MainItemBean;
import com.aibfive.sample.R;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {

    //数据类型
    public static final int DATA_TYPE_TENCENT_MAP = 0;//腾讯地图

    /**
     * 创建主页数据
     * @param context
     * @return
     */
    public static List<MainItemBean> buildMainItemData(Context context){
        List<MainItemBean> data = new ArrayList<>();
        data.add(new MainItemBean(DATA_TYPE_TENCENT_MAP, context.getString(R.string.tencent_map)));
        return data;
    }

}
