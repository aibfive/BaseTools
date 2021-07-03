package com.aibfive.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.aibfive.basetools.mvvm.BaseViewModel
import com.aibfive.basetools.util.LogUtil
import com.aibfive.wanandroid.base.BaseModel
import com.aibfive.wanandroid.network.Callback
import com.aibfive.wanandroid.network.DefaultRepository
import com.aibfive.wanandroid.network.HomeService
import com.aibfive.wanandroid.network.RetrofitClient
import com.aibfive.wanandroid.ui.base.PageModel
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel {

    var bannerList : MutableLiveData<List<BannerModel>>
    val article : MutableLiveData<PageModel<ArticleModel>>

    constructor() : super(){
        bannerList = MutableLiveData()
        article = MutableLiveData()
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

    fun getHomeArticle(page : Int){
        DefaultRepository.getData(false, {
            RetrofitClient.getService<HomeService>().getHomeArticle(page)
        }, object : Callback<PageModel<ArticleModel>>{
            override fun onSuccess(data: PageModel<ArticleModel>) {
                article.postValue(data)
            }

            override fun onFailed(errorCode: String, errorMsg: String) {
                LogUtil.v(HomeViewModel::class.simpleName, "errorMsg-->$errorMsg")
            }
        })
    }


}