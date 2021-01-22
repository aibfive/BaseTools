package com.aibfive.basetools.widget

import android.content.Context
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.aibfive.basetools.R

/**
 * 获取验证码视图
 */
class GetCodeView : AppCompatTextView {

    private var initDesc = "获取验证码"  //初始描述文本
    private var countdownDesc = "获取验证码(%d)"  //倒计时描述文本
    private var millisInFuture = 60000L  //倒计时总耗时
    private var countDownInterval = 1000L  //倒计时CountDownTimer--onTick响应时间间隔
    private lateinit var timer: CountDownTimer  //倒计时

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    /**
     * 初始化
     */
    private fun init(attrs: AttributeSet?, defStyleAttr: Int){

        val typedValue = context.obtainStyledAttributes(attrs, R.styleable.GetCodeView, defStyleAttr, 0)
        val tempInitDesc = typedValue.getString(R.styleable.GetCodeView_init_desc)
        if(tempInitDesc != null){
            initDesc = tempInitDesc
        }
        val tempCountdownDesc = typedValue.getString(R.styleable.GetCodeView_countdown_desc)
        if(tempCountdownDesc != null){
            countdownDesc = tempCountdownDesc
        }
        millisInFuture = typedValue.getInteger(R.styleable.GetCodeView_millis_in_future, millisInFuture.toInt()).toLong()
        typedValue.recycle()

        text = initDesc
        timer = object : CountDownTimer(millisInFuture, countDownInterval){

            override fun onFinish() {
                resetView()
            }

            override fun onTick(millisUntilFinished: Long) {
                //倒计时中，显示倒计时秒数（使用Math.round四舍五入，优化系统误差）
                text = String.format(countdownDesc, Math.round(millisUntilFinished.toDouble() / 1000))
            }
        }
    }

    /**
     * 开始倒计时
     */
    fun start(){
        isEnabled = false
        timer.start()
    }

    /**
     * 取消倒计时
     */
    fun cancel(){
        timer.cancel()
        resetView()
    }

    /**
     * 用于销毁视图时取消倒计时
     */
    private fun detach(){
        //取消倒计时
        timer.cancel()
    }

    /**
     * 重置视图，恢复倒计时前状态
     */
    private fun resetView(){
        isEnabled = true
        text = initDesc
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        detach()
    }

}