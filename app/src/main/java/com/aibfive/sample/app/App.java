package com.aibfive.sample.app;

import android.app.Application;

import com.aibfive.basetools.http.RetrofitClient;
import com.aibfive.basetools.util.sharedpreferences.SharedPreferencesUtils;
import com.aibfive.sample.network.HostUrl;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Date : 2020/11/9/009
 * Time : 10:41
 * author : Li
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.INSTANCE.init(HostUrl.HOST_URL);
        Logger.addLogAdapter(new AndroidLogAdapter());
        //SharedPreferencesUtils.INSTANCE.init(getApplicationContext(), getPackageName());
    }
}
