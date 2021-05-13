package com.aibfive.sample.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BannerViewModel : ViewModel() {

    var data : MutableLiveData<BannerBean> = MutableLiveData()

}