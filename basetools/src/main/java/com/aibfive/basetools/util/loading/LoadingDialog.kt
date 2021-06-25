package com.aibfive.basetools.util.loading

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import com.aibfive.basetools.R
import com.aibfive.basetools.databinding.DialogLoadingBinding
import com.aibfive.basetools.dialog.BaseDialog


class LoadingDialog : BaseDialog<DialogLoadingBinding> {

    constructor(context: Context) : super(context, R.style.StyleDialog){
        setCanceledOnTouchOutside(false)
    }

    fun setHint(hint : String?) {
        if (TextUtils.isEmpty(hint)) {
            binding.tvHint.text = context.getString(R.string.loading)
        } else {
            binding.tvHint.text = hint
        }
    }

}