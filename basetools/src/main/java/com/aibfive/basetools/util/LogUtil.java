package com.aibfive.basetools.util;

import android.util.Log;

import com.aibfive.basetools.BuildConfig;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static void json(String tag, String json) {
        if (BuildConfig.DEBUG) printJson(tag, json);
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.v(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.v(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printJson(String tag, String msg) {
        try {

            String message;
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
            printLine(tag, true);
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                Log.v(tag, "║ " + line);
            }
            printLine(tag, false);
        }
        catch (Exception e) {
            Log.v(tag, msg);
        }

    }

}
