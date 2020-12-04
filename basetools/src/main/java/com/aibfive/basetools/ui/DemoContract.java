package com.aibfive.basetools.ui;

/**
 * Date : 2020/12/3/003
 * Time : 11:13
 * author : Li
 */
public interface DemoContract {

    interface DemoDisplay extends IBaseDiaplay {
        void demo();
    }

    abstract class DemoPresenter extends BasePresenter<DemoDisplay>{
        abstract void demo();
    }

}
