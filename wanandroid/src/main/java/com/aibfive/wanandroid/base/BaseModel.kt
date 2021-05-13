package com.aibfive.wanandroid.base

data class BaseModel<T>(
        var data : T?,
        var errorCode : String?,
        var errorMsg : String?
)