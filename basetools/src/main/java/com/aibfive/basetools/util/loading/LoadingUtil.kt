package com.aibfive.basetools.util.loading

import com.aibfive.basetools.BaseApplication
import com.aibfive.basetools.util.ActivityManager

object LoadingUtil {

    val dialog : LoadingDialog by lazy {
        LoadingDialog(ActivityManager.getCurrent()!!)
    }

    fun show(){
        if(dialog.isShowing){
            dialog.dismiss()
        }
        dialog.show()
    }

    fun show(hint : String){
        if(dialog.isShowing){
            dialog.dismiss()
        }
        dialog.setHint(hint)
        dialog.show()
    }

    fun hide(){
        dialog.dismiss()
    }

}