package com.aibfive.basetools.dialog

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import com.aibfive.basetools.R
import kotlinx.android.synthetic.main.dialog_single_operate_hint.*

/**
 * Date : 2020/11/27/027
 * Time : 16:53
 * author : Li
 */
class SingleOperateHintDialog @SuppressLint("ValidFragment")
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
        if(!TextUtils.isEmpty(builder.operateText)) {
            tvOperate?.text = builder.operateText
        }
        tvOperate?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvOperate -> builder.operate?.invoke(this)
        }
    }

    class Builder {

        var layoutId : Int = R.layout.dialog_single_operate_hint
        var hint : String? = null
        var operateText : String? = null
        var operate : ((dialog : SingleOperateHintDialog) -> Unit)? = null

        fun setLayoutId(layoutId : Int) : Builder {
            this.layoutId = layoutId
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