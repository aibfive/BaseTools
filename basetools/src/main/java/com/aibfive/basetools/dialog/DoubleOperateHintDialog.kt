package com.aibfive.basetools.dialog

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aibfive.basetools.R
import com.aibfive.basetools.databinding.DialogDoubleOperateHintBinding


/**
 * Date : 2020/11/27/027
 * Time : 11:31
 * author : Li
 */

class DoubleOperateHintDialog @SuppressLint("ValidFragment")
private constructor () : BaseDialogFragment<DialogDoubleOperateHintBinding>(), View.OnClickListener {

    companion object {

        private lateinit var builder : Builder

        fun getBuilder() : Builder {
            builder = Builder()
            return builder
        }

    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogDoubleOperateHintBinding {
        return DialogDoubleOperateHintBinding.inflate(inflater, container, false)
    }

    override fun init() {
        if(builder.background != -1) {
            binding.clContainer.setBackgroundResource(builder.background)
        }
        if(!TextUtils.isEmpty(builder.hint)) {
           binding.tvHint.text = builder.hint
        }
        if(!TextUtils.isEmpty(builder.operate1Text)) {
            binding.tvOperate1.text = builder.operate1Text
        }
        if(!TextUtils.isEmpty(builder.operate2Text)) {
            binding.tvOperate2.text = builder.operate2Text
        }
        binding.tvOperate1.setOnClickListener(this)
        binding.tvOperate2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvOperate1 -> builder.operate1?.invoke(this)
            R.id.tvOperate2 -> builder.operate2?.invoke(this)
        }
    }

    class Builder {

        var background : Int = -1
        var hint : String? = null
        var operate1Text : String? = null
        var operate2Text : String? = null
        var operate1 : ((dialog : DoubleOperateHintDialog) -> Unit)? = null
        var operate2 : ((dialog : DoubleOperateHintDialog) -> Unit)? = null

        fun setBackground(background : Int) : Builder {
            this.background = background
            return this
        }

        fun setHint(hint : String) : Builder {
            this.hint = hint
            return this
        }

        fun setOperate1Text(operate1Text : String) : Builder {
            this.operate1Text = operate1Text
            return this
        }

        fun setOperate2Text(operate2Text : String) : Builder {
            this.operate2Text = operate2Text
            return this
        }

        fun create() : DoubleOperateHintDialog {
            return DoubleOperateHintDialog()
        }

        fun setOnOperateListener(
                operate1: ((dialog : DoubleOperateHintDialog) -> Unit)?,
                operate2: ((dialog : DoubleOperateHintDialog) -> Unit)?) : Builder {
            this.operate1 = operate1
            this.operate2 = operate2
            return this
        }
    }

}
