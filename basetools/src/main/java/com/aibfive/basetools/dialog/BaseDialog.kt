package com.aibfive.basetools.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.viewbinding.ViewBinding

/**
 * @author: 小李
 * @date: 2021/6/25 16:21
 */
abstract class BaseDialog<VB : ViewBinding> : Dialog {

    lateinit var binding: VB

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

    abstract fun getViewBinding() : VB
}