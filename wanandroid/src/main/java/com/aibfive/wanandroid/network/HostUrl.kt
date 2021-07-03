package com.aibfive.wanandroid.network

object HostUrl {

    const val BASE_URL = "https://www.wanandroid.com/"

    //HomeService
    const val GET_BANNER = "banner/json"  //获取Banner数据
    const val GET_HOME_ARTICLE = "article/list/{page}/json"  //获取首页文章列表

    //AuthService
    const val REGISTER = "user/register"  //注册
    const val LOGIN = "user/login"  //登录

    //MyService
    const val GET_USER_INFO = "lg/coin/userinfo/json"  //获取用户信息

}