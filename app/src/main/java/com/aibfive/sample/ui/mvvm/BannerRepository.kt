package com.aibfive.sample.ui.mvvm

import com.aibfive.sample.network.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BannerRepository(var viewModel : BannerViewModel) {

    fun getBannerData(){
        GlobalScope.launch {
            try {
                val data = RetrofitClient.getApiService().getBannerDataSuspend()
                viewModel.data.postValue(data.data)
            } catch (e: Exception) {

            }

        }
    }

}