package com.aibfive.basetools.dialog

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.aibfive.basetools.R

/**
 * Date : 2020/11/27/027
 * Time : 13:41
 * author : Li
 */
open abstract class BaseDialogFragment : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDialog()
        init()
    }

    open abstract fun getLayoutId() : Int

    open abstract fun init()

    open fun getDialogWidth() : Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    open fun getDialogHeight() : Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    open fun getDialogGravity() : Int {
        return Gravity.CENTER
    }

    open fun getDialogAnim() : Int {
        return R.style.StyleCenterDialogAnim
    }

    open fun getDialogBackgroundDrawable() : Drawable {
        return ColorDrawable()
    }

    fun initDialog(){
        val window = dialog.window
        val lp = window?.attributes
        lp?.width = getDialogWidth()
        lp?.height = getDialogHeight()
        lp?.gravity = getDialogGravity()
        lp?.windowAnimations = getDialogAnim()
        window?.setBackgroundDrawable(getDialogBackgroundDrawable())
    }

    fun show(manager: FragmentManager?) {
        super.show(manager, null)
    }

}