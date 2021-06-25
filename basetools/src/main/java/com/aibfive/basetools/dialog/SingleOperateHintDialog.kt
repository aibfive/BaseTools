package com.aibfive.basetools.dialog

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aibfive.basetools.R
import com.aibfive.basetools.databinding.DialogSingleOperateHintBinding

/**
 * Date : 2020/11/27/027
 * Time : 16:53
 * author : Li
 */

class SingleOperateHintDialog @SuppressLint("ValidFragment")
private constructor () : BaseDialogFragment<DialogSingleOperateHintBinding>(), View.OnClickListener {

    companion object {

        private lateinit var builder : Builder

        fun getBuilder() : Builder {
            builder = Builder()
            return builder
        }

    }

    override fun init() {
        if(builder.background != -1) {
            binding.clContainer.setBackgroundResource(builder.background)
        }
        if(!TextUtils.isEmpty(builder.hint)) {
            binding.tvHint.text = builder.hint
        }
        if(!TextUtils.isEmpty(builder.operateText)) {
            binding.tvOperate.text = builder.operateText
        }
        binding.tvOperate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvOperate -> builder.operate?.invoke(this)
        }
    }

    class Builder {

        var background : Int = -1
        var hint : String? = null
        var operateText : String? = null
        var operate : ((dialog : SingleOperateHintDialog) -> Unit)? = null

        fun setBackground(background : Int) : Builder {
            this.background = background
            return this
        }

        fun setHint(hint : String) : Builder {
            this.hint = hint
            return this
        }

        fun setOperateText(operateText : String) : Builder {
            this.operateText = operateText
            return this
        }

        fun create() : SingleOperateHintDialog {
            return SingleOperateHintDialog()
        }

        fun setOnOperateListener(
                operate: ((dialog : SingleOperateHintDialog) -> Unit)?) : Builder {
            this.operate = operate
            return this
        }
    }

}
