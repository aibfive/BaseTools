package com.aibfive.basetools.util;

import android.util.Log;

import com.aibfive.basetools.BuildConfig;

/**
 * Created by MirkoWu on 2017/4/20 0020.
 */

public class LogUtil {

    /**
     * 以下是系统自带的
     * 打印简单的log
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.w(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) Log.v(tag, msg);
    }


}
