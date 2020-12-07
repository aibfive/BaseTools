package com.aibfive.basetools.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

/**
 * Date : 2020/11/27/027
 * Time : 17:20
 * author : Li
 */
class SearchView : AppCompatEditText {

    var clearIcon : Drawable? = null

    constructor(context: Context) : super(context){
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView()
    }

    fun initView(){
        clearIcon = compoundDrawables[2]
        inputType = InputType.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_NORMAL
        imeOptions = EditorInfo.IME_ACTION_SEARCH
        addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(TextUtils.isEmpty(s)){
                    hideClearIcon()
                }else{
                    showClearIcon()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        clearText()
    }

    /**
     * 显示清除按钮
     */
    fun showClearIcon(){
        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], null, clearIcon, null)
    }

    /**
     * 隐藏清除按钮
     */
    fun hideClearIcon(){
        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], null, null, null)
    }

    /**
     * 清空文字
     */
    fun clearText(){
        setText("")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val clearIcon = compoundDrawables[2]//清除按钮
        if(clearIcon == null){
            return super.onTouchEvent(event)
        }else{
            if(event?.action == MotionEvent.ACTION_UP){
                /**
                 * 是否点击到清除按钮的位置
                 */
                if(event.getX() > width - paddingLeft - paddingRight - clearIcon.intrinsicWidth){
                    clearText()
                    return true
                }else{
                    return super.onTouchEvent(event)
                }
            }else{
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }

}