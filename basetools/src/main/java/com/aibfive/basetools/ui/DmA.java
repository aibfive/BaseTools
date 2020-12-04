package com.aibfive.basetools.ui;

import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

/**
 * Date : 2020/12/4/004
 * Time : 17:37
 * author : Li
 */
public abstract class DmA<T extends BasePresenter>  extends BaseActivity implements IBaseDiaplay {


    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (T) new BasePresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
