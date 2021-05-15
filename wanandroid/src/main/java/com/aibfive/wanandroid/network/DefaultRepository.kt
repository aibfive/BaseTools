package com.aibfive.wanandroid.network

import com.aibfive.basetools.util.LogUtil
import com.aibfive.basetools.util.StringUtil
import com.aibfive.basetools.util.ToastUtil
import com.aibfive.basetools.util.loading.LoadingUtil
import com.aibfive.wanandroid.R
import com.aibfive.wanandroid.base.BaseModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object DefaultRepository {

    const val ERROR_CODE_SERVER = "-2"  //服务器执行错误
    const val ERROR_CODE_EXCEPTION = "-1"  //访问异常
    const val ERROR_CODE_OK = "0"  //访问成功

    fun <T> getData(showLoading : Boolean, block : suspend() -> BaseModel<T>, callback: Callback<T>){
        try {
            if(showLoading){
                LoadingUtil.show()
            }
            GlobalScope.launch(Dispatchers.Main) {
                val data = block.invoke()
                LogUtil.v("GG", "DefaultRepository")
                if (data == null) {
                    callback.onFailed(ERROR_CODE_SERVER, StringUtil.getString(R.string.error_code_server))
                    ToastUtil.show(StringUtil.getString(R.string.error_code_server))
                } else {
                    if (data.errorCode.equals(ERROR_CODE_OK)) {
                        callback.onSuccess(data.data!!)
                    } else {
                        callback.onFailed(data.errorCode!!, data.errorMsg!!)
                        ToastUtil.show(data.errorMsg!!)
                    }
                }
            }
        } catch (e: Exception) {
            callback.onFailed(ERROR_CODE_EXCEPTION, e.toString())
            ToastUtil.show(e.toString())
        }finally {
            if(showLoading){
                LoadingUtil.hide()
            }
        }

    }

    fun <T> getData(block: suspend() ->BaseModel<T>, callback: Callback<T>){
        getData(true, block, callback)
    }

}