package com.aibfive.basetools.adapter.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 瀑布流分割线
 * 该类仅适合包含边缘的。
 * 若需要适配不包含边缘，需要先解决几个问题：
 * 垂直方向上：需要解决判断view是否是最底部，但瀑布流使得通过position判断这一方式变得无效，目前不知道怎么判断，
 *            而最左、最右、最顶的判断没有问题。
 *            最左、最右可以通过calColumn()来进行判断
 *            最顶则是通过isFristTopInVertical()
 *  水平方向上：需要解决判断View是否是最顶部、最右边、最底部，目前不知道怎么判断,
 *             而最左判断没有问题。
 *              最左可以通过isFristLeftHorizontal()来进行判断
 * Date : 2020/11/4/004
 * Time : 16:58
 * author : Li
 */
class StaggeredGridItemDecoration : RecyclerView.ItemDecoration {

    var dividerWidth : Int = 1//分割线宽度
    var dividerHeight : Int = 1//分割线高度
    var dividerColor : Int = Color.WHITE//分割线颜色
    var colorPaint : Paint = Paint()

    constructor(dividerSize : Int, dividerColor : Int){
        this.dividerWidth = dividerSize
        this.dividerHeight = dividerSize
        this.dividerColor = dividerColor
        init()
    }

    constructor(dividerWidth : Int, dividerHeight : Int, dividerColor : Int){
        this.dividerWidth = dividerWidth
        this.dividerHeight = dividerHeight
        this.dividerColor = dividerColor
        init()
    }

    /**
     * 初始化
     */
    fun init(){
        colorPaint.setColor(dividerColor)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val count = parent.childCount
        for (position in 0 until count) {
            val view = parent.getChildAt(position)
            c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.left, view.bottom + dividerHeight), colorPaint)//左部
            c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.right + dividerWidth, view.top), colorPaint)//顶部
            c.drawRect(Rect(view.right, view.top - dividerHeight, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//右部
            c.drawRect(Rect(view.left - dividerWidth, view.bottom, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//底部
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.adapter == null){
            return
        }
        val spanCount = getSpanCount(parent)
        val position = parent.getChildAdapterPosition(view)
        when (getOrientation(parent)) {
            StaggeredGridLayoutManager.VERTICAL -> {//垂直方向
                val column = calColumn(view)
                /**
                 * 当视图只有两列时，分割线共有三条，位于两侧和它们两中间，位置分别取它们的左右两侧，
                 * 其中第一个视图的左侧是需要腾出分割线大小位置，右侧需要腾出分割线大小一半的位置，
                 * 第二个视图的右侧是需要腾出分割线大小位置，左侧需要腾出分割线大小一半的位置，
                 * 这样两个视图的宽度才会一样，根据不包含边缘的(n-1)/n算法，可以推算出包含边缘计算每个视图需要分出多少位置的算法为(n+1)/n。（n--表示列总数）
                 * 通过上述，我们可以举一个例子：
                 *         1             2            3             4
                 *    左      右    左     右    左      右    左       右
                 *    4/4     1/4   3/4    2/4   2/4     3/4   1/4     4/4
                 * 该例子里共四列，通过(n+1)/n可以计算出每个视图需要分出多少的位置，即5/4分割线大小的位置。
                 * 那么每个视图的左右位置大小又该怎么计算呢？通过上述例子可以看出他的规律性，
                 * 左边计算方式：(spanCount - n + 1)/spanCount（n--列数，spanCount--列总数）
                 * 右边计算方式：n/spanCount（n--列数，spanCount--列总数）
                 */
                outRect.left = (spanCount - column + 1) * dividerWidth / spanCount
                outRect.right = column * dividerWidth / spanCount
                if (isFristTopInVertical(position, spanCount)) {//若视图位置在最顶部
                    outRect.top = dividerHeight
                }
                outRect.bottom = dividerHeight
            }
            StaggeredGridLayoutManager.HORIZONTAL -> {//水平方向
                val row = calRow(view)//行数
                /**
                 * 当视图只有两行时，分割线共有三条，位于两侧和它们两中间，位置分别取它们的顶底两侧，
                 * 其中第一个视图的顶部是需要腾出分割线大小位置，底部需要腾出分割线大小一半的位置，
                 * 第二个视图的底部是需要腾出分割线大小位置，顶部需要腾出分割线大小一半的位置，
                 * 这样两个视图的高度才会一样，根据不包含边缘的(n-1)/n算法，可以推算出包含边缘计算每个视图需要分出多少位置的算法为(n+1)/n。（n--表示行总数）
                 * 通过上述，我们可以举一个例子：
                 *         1             2            3             4
                 *    顶      底    顶     底    顶      底    顶       底
                 *    4/4     1/4   3/4    2/4   2/4     3/4   1/4     4/4
                 * 该例子里共四行，通过(n+1)/n可以计算出每个视图需要分出多少的位置，即5/4分割线大小的位置。
                 * 那么每个视图的顶底位置大小又该怎么计算呢？通过上述例子可以看出他的规律性，
                 * 顶部计算方式：(spanCount - n + 1)/spanCount（n--行数，spanCount--行总数）
                 * 底部计算方式：n/spanCount（n--行数，spanCount--行总数）
                 */
                outRect.top = (spanCount - row + 1) * dividerHeight / spanCount
                outRect.bottom = row * dividerHeight / spanCount
                if (isFristLeftHorizontal(position, spanCount)) {//若视图位置在最左边
                    outRect.left = dividerWidth
                }
                outRect.right = dividerWidth
            }
        }
    }

    /**
     * 计算视图列数
     */
    fun calColumn(view : View) : Int{
        if(view.layoutParams is StaggeredGridLayoutManager.LayoutParams){
            return (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
        }else{
            return 0
        }
    }

    /**
     * 计算视图行数
     */
    fun calRow(view : View) : Int{
        if(view.layoutParams is StaggeredGridLayoutManager.LayoutParams){
            return (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
        }else{
            return 0
        }
    }

    /**
     * 判断视图位置是否在最左测--在水平方向上
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristLeftHorizontal(position : Int, spanCount : Int) : Boolean {
        return position < spanCount
    }

    /**
     * 判断视图位置是否在最顶部--在垂直方向上
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristTopInVertical(position : Int, spanCount : Int) : Boolean {
        return position < spanCount
    }

    /**
     * 获取总列数、总行数
     */
    fun getSpanCount(parent: RecyclerView) : Int{
        if(parent.layoutManager is StaggeredGridLayoutManager){
            return (parent.layoutManager as StaggeredGridLayoutManager).spanCount
        }else{
            return 0
        }
    }

    /**
     * 获取方向
     */
    fun getOrientation(parent: RecyclerView) : Int {
        if(parent.layoutManager is StaggeredGridLayoutManager){
            return (parent.layoutManager as StaggeredGridLayoutManager).orientation
        }else{
            return StaggeredGridLayoutManager.VERTICAL
        }
    }
}