package com.aibfive.basetools.util.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.view.WindowManager
import com.aibfive.basetools.R
import kotlinx.android.synthetic.main.dialog_loading.*
import java.lang.reflect.Field


class LoadingDialog : Dialog {

    constructor(context: Context) : super(context, R.style.StyleDialog){
        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)
    }

    fun setHint(hint : String?) {
        if (TextUtils.isEmpty(hint)) {
            tvHint.text = context.getString(R.string.loading)
        } else {
            tvHint.text = hint
        }
    }

}