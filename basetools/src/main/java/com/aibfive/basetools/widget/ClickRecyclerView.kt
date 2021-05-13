package com.aibfive.basetools.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.aibfive.basetools.util.LogUtil

class ClickRecyclerView : RecyclerView {

    private var singleClickListener : OnSingleClickListener? = null
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop  //系统认定为滑动的最小距离
    private var downX = 0.0F  //手指按下时的位置X
    private var downY = 0.0F  //手指按下时的位置Y
    private var isMove = false  //是否移动

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(e)
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        when(e?.action){
            MotionEvent.ACTION_DOWN -> {
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_DOWN")
                downX = e.x
                downY = e.y
            }
            MotionEvent.ACTION_MOVE ->{
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_MOVE")
                //若移动手指动作在x、y方向与按下手指位置的距离差其中一个大于、等于系统认定为滑动的最小距离，认定为移动
                val touchSlopX = Math.abs(e.x - downX)
                val touchSlopY = Math.abs(e.y - downY)
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_MOVE--X-->$touchSlopX")
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_MOVE--Y-->$touchSlopY")
                if(touchSlopX >= touchSlop ||
                        touchSlopY >= touchSlop){
                    isMove = true
                    LogUtil.v(ClickRecyclerView::class.simpleName, "移动了")
                }
            }
            MotionEvent.ACTION_UP -> {
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_UP")
                //若移动（ACTION_MOVE）和抬起（ACTION_UP）手指动作在x、y方向与按下手指位置的的距离差都小于系统认定为滑动的最小距离， 则认定为单击操作
                val touchSlopX = Math.abs(e.x - downX)
                val touchSlopY = Math.abs(e.y - downY)
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_UP--X-->$touchSlopX")
                LogUtil.v(ClickRecyclerView::class.simpleName, "ACTION_UP--Y-->$touchSlopY")
                if(touchSlopX < touchSlop &&
                        touchSlopY < touchSlop &&
                        !isMove){
                    LogUtil.v(ClickRecyclerView::class.simpleName, "点击了")
                    //点击事件
                    if(singleClickListener != null){
                        singleClickListener?.onSingleClick(this)
                    }
                    return true
                }
            }
        }
        return super.onTouchEvent(e)
        //return true
    }

    /**
     * 设置点击监听器
     */
    fun setOnSingleClickListener(listener: OnSingleClickListener){
        this.singleClickListener = listener
    }

    /**
     * 单击监听器
     */
    interface OnSingleClickListener {

        fun onSingleClick(v : View?)

    }

}
