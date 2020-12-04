package com.aibfive.basetools.ui;

import android.app.Activity;

/**
 * Date : 2020/12/3/003
 * Time : 10:58
 * author : Li
 */
public class BasePresenter<V extends IBaseDiaplay> {

    public V view;

    public  void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        view = null;
    }
}
