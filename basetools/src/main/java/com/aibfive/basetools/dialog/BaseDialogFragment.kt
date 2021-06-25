package com.aibfive.basetools.dialog

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.aibfive.basetools.R

/**
 * Date : 2020/11/27/027
 * Time : 13:41
 * author : Li
 */
abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment(){

    private var _binding : VB? = null
    val binding : VB get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDialog()
        init()
    }

    /**
     * 获取视图绑定器
     * @return VB
     */
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) : VB

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
        val window = dialog?.window
        val lp = window?.attributes
        lp?.width = getDialogWidth()
        lp?.height = getDialogHeight()
        lp?.gravity = getDialogGravity()
        lp?.windowAnimations = getDialogAnim()
        window?.setBackgroundDrawable(getDialogBackgroundDrawable())
    }

    fun show(manager: FragmentManager) {
        super.show(manager, null)
    }

}