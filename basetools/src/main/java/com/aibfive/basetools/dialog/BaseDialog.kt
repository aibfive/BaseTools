package com.aibfive.basetools.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author: 小李
 * @date: 2021/6/25 16:21
 */
abstract class BaseDialog<VB : ViewBinding> : Dialog {

    var binding: VB

    constructor(context: Context) : super(context){
        binding = getViewBinding()
        setContentView(binding.root)
    }
    constructor(context: Context, themeResId: Int) : super(context, themeResId){
        binding = getViewBinding()
        setContentView(binding.root)
    }

    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener){
        binding = getViewBinding()
        setContentView(binding.root)
    }

    /**
     * 获取视图绑定器
     * @return VB
     */
    fun getViewBinding() : VB {
        /**
         * 使用反射获取VB
         * 正常情况下获取方式为
         * VB.inflate(LayoutInflater.from(this))
         */
        val type = this.javaClass.genericSuperclass
        val cls = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = cls.getMethod("inflate", LayoutInflater::class.java)
        return method.invoke(null, LayoutInflater.from(context)) as VB
    }
}