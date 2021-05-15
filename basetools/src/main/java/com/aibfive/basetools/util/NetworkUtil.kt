package com.aibfive.basetools.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * @author: 小李
 * @date: 2021/5/13 17:31
 */
object NetworkUtil {

    /**
     * 是否连接Wifi或移动网络
     * @param context Context
     * @return Boolean
     */
    fun isNetworkConnect(context: Context) : Boolean{
        val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            val networkInfo = connectivityManager.activeNetworkInfo
            if(networkInfo == null){  //没有连接Wifi或移动网络
                return false
            }else if(networkInfo.type == ConnectivityManager.TYPE_WIFI){  //连接了Wifi
                return true
            }else if(networkInfo.type == ConnectivityManager.TYPE_MOBILE){  //连接了移动网络
                return true
            }
        }else{
            val network = connectivityManager.activeNetwork
            if(network == null){  //没有连接Wifi或移动网络
                return false
            }else{
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if(networkCapabilities == null){  //没有连接Wifi或移动网络
                    return false
                }else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){  //连接了Wifi
                    return true
                }else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){  //连接了移动网络
                    return true
                }
            }
        }
        return false
    }


}