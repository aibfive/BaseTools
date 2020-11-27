package com.aibfive.basetools.dialog

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import com.aibfive.basetools.R
import kotlinx.android.synthetic.main.dialog_double_operate_hint.*

/**
 * Date : 2020/11/27/027
 * Time : 11:31
 * author : Li
 */
class DoubleOperateHintDialog @SuppressLint("ValidFragment")
private constructor () : BaseDialogFragment(), View.OnClickListener {

    companion object {

        private lateinit var builder : Builder

        fun getBuilder() : Builder {
            builder = Builder()
            return builder
        }

    }

    override fun getLayoutId(): Int {
        return builder.layoutId
    }

    override fun init() {
        if(!TextUtils.isEmpty(builder.hint)) {
            tvHint?.text = builder.hint
        }
        if(!TextUtils.isEmpty(builder.operate1Text)) {
            tvOperate1?.text = builder.operate1Text
        }
        if(!TextUtils.isEmpty(builder.operate2Text)) {
            tvOperate2?.text = builder.operate2Text
        }
        tvOperate1?.setOnClickListener(this)
        tvOperate2?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvOperate1 -> builder.operate1?.invoke(this)
            R.id.tvOperate2 -> builder.operate2?.invoke(this)
        }
    }

    class Builder {

        var layoutId : Int = R.layout.dialog_double_operate_hint
        var hint : String? = null
        var operate1Text : String? = null
        var operate2Text : String? = null
        var operate1 : ((dialog : DoubleOperateHintDialog) -> Unit)? = null
        var operate2 : ((dialog : DoubleOperateHintDialog) -> Unit)? = null

        fun setLayoutId(layoutId : Int) : Builder {
            this.layoutId = layoutId
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