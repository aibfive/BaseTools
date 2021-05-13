package com.aibfive.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.basetools.util.LogUtil
import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.network.Callback
import com.aibfive.wanandroid.network.DefaultRepository
import com.aibfive.wanandroid.network.HomeService
import com.aibfive.wanandroid.network.RetrofitClient
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel {

    var bannerList : MutableLiveData<List<BannerModel>>

    constructor() : super(){
        bannerList = MutableLiveData()
    }

    fun getBanner(){
        val f : suspend() -> BaseModel<List<BannerModel>> = {
           RetrofitClient.getService<HomeService>().getBanner()
        }
        DefaultRepository.getData(f, object : Callback<List<BannerModel>>{
                override fun onSuccess(data: List<BannerModel>) {
                    bannerList.postValue(data)
                }

                override fun onFailed(errorCode: String, errorMsg: String) {
                    LogUtil.v(HomeViewModel::class.simpleName, "errorMsg-->$errorMsg")
                }
            }
        )
    }

}