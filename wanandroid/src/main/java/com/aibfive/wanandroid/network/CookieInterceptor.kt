package com.aibfive.wanandroid.network

import com.aibfive.basetools.util.LogUtil
import com.aibfive.wanandroid.app.Constants
import com.aibfive.wanandroid.base.CookieModel
import com.aibfive.wanandroid.dao.AppDataBase
import com.aibfive.wanandroid.util.UserUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author: 小李
 * @date: 2021/5/15 21:50
 */
class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request =  chain.request()
        var response = chain.proceed(request)
        if(UserUtil.isLogin()){//用户已登录
            //为需要登录才能访问的链接访问添加Cookie
            val builder = request.newBuilder()
            val headers = AppDataBase.db.cookieDao().getCookie().headers
            headers.forEach {
                builder.addHeader(Constants.HEADER_COOKIE, it)
                LogUtil.v(CookieInterceptor::class.simpleName, it)
            }
            response = chain.proceed(builder.build())
        }else{//用户未登录
            val url = response.request().url().toString()
            //若链接为登录/注册，提取头部为Set-Cookie的数据储存，为之后用户之后的登录才能访问的链接访问添加Cookie
            if(url.contains(HostUrl.LOGIN) || url.contains(HostUrl.REGISTER)) {
                val headers = response.headers(Constants.HEADER_SET_COOKIE)
                AppDataBase.db.cookieDao().insertCookie(CookieModel(headers))
            }
        }
        return response
    }
}