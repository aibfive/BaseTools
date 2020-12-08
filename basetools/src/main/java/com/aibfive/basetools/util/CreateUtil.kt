package com.aibfive.basetools.util

import java.lang.reflect.ParameterizedType

/**
 * Date : 2020/12/8/008
 * Time : 13:43
 * author : Li
 */
object CreateUtil {

    /**
     * 内部获取第i个类型参数的真实类型 ，反射new出对象.
     */
    fun <T> getT(obj : Any, i : Int) : T?{
        try {
            val parameterizedType = obj.javaClass.genericSuperclass as ParameterizedType
            val cls = parameterizedType.actualTypeArguments[i] as Class<T>
            return cls.newInstance()
        } catch (e: Exception) {
            return null
        }
    }

}