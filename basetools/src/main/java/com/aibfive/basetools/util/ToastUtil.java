package com.aibfive.basetools.util;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @author zhangdeming
 * @date 创建时间 2016/9/23
 * @description 显示Toast信息
 */
public class ToastUtil {

    private static Toast mInstance = null;
    private static Activity mCurrentActivity = null;

    /**
     * @param content
     */
    public static void show(String content) {
        Activity temp = ActivityManager.INSTANCE.getCurrent();
        if (temp == null) {
            return;
        }
        if (temp != mCurrentActivity) {
            mCurrentActivity = temp;
            mInstance = null;
        }
        if (mInstance == null) {
            mInstance = Toast.makeText(mCurrentActivity, content, Toast.LENGTH_SHORT);
        } else {
            mInstance.setText(content);
        }
        mInstance.show();
        mCurrentActivity = null;
        mInstance = null;
    }

    public static void show(int code, String message) {
        show(code + "    " + message);
    }

    /**
     * @param resId
     */
    public static void show(@StringRes int resId) {
        Activity temp = ActivityManager.INSTANCE.getCurrent();
        if (temp == null) {
            return;
        }
        if (temp != mCurrentActivity) {
            mCurrentActivity = temp;
            mInstance = null;
        }
        if (mInstance == null) {
            mInstance = Toast.makeText(mCurrentActivity, resId, Toast.LENGTH_SHORT);
        } else {
            mInstance.setText(resId);
        }
        mInstance.show();
        mCurrentActivity = null;
        mInstance = null;
    }
}
