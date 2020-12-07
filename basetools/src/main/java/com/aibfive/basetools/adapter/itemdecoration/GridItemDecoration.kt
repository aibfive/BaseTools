package com.aibfive.basetools.adapter.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

/**
 * 网格分割线
 * Date : 2020/10/28/028
 * Time : 11:02
 * author : Li
 */
class GridItemDecoration : RecyclerView.ItemDecoration {

    var dividerWidth : Int = 1//分割线宽度
    var dividerHeight : Int = 1//分割线高度
    var includeEdge : Boolean = true//是否包含边缘
    var dividerColor : Int = Color.WHITE//分割线颜色
    var colorPaint : Paint = Paint()

    constructor(dividerSize : Int, dividerColor : Int, includeEdge : Boolean){
        this.dividerWidth = dividerSize
        this.dividerHeight = dividerSize
        this.dividerColor = dividerColor
        this.includeEdge = includeEdge
        init()
    }

    constructor(dividerWidth : Int, dividerHeight : Int, dividerColor : Int, includeEdge : Boolean){
        this.dividerWidth = dividerWidth
        this.dividerHeight = dividerHeight
        this.dividerColor = dividerColor
        this.includeEdge = includeEdge
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
        val spanCount = getSpanCount(parent)
        for (position in 0 until count) {
            val view = parent.getChildAt(position)
            if (includeEdge) {//包含边缘
                c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.left, view.bottom + dividerHeight), colorPaint)//左部
                c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.right + dividerWidth, view.top), colorPaint)//顶部
                c.drawRect(Rect(view.right, view.top - dividerHeight, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//右部
                c.drawRect(Rect(view.left - dividerWidth, view.bottom, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//底部
            } else {//不包含边缘
                if (!isFristLeft(parent, position, spanCount)) {//若视图位置不在最左边
                    c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.left, view.bottom + dividerHeight), colorPaint)//左部
                }
                if (!isFristTop(parent, position, spanCount)) {//若视图位置不在最顶部
                    c.drawRect(Rect(view.left - dividerWidth, view.top - dividerHeight, view.right + dividerWidth, view.top), colorPaint)//顶部
                }
                if (!isLastRight(parent, position, count, spanCount)) {//若视图位置不在最右边
                    c.drawRect(Rect(view.right, view.top - dividerHeight, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//右部
                }
                if (!isLastBottom(parent, position, count, spanCount)) {//若视图位置不在最底部
                    c.drawRect(Rect(view.left - dividerWidth, view.bottom, view.right + dividerWidth, view.bottom + dividerHeight), colorPaint)//底部
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.adapter == null){
            return
        }
        val count = parent.adapter!!.itemCount
        val spanCount = getSpanCount(parent)
        val position = parent.getChildAdapterPosition(view)
        when (getOrientation(parent)) {
            GridLayoutManager.VERTICAL -> {//垂直方向
                val column = calColumn(position, spanCount)
                if (includeEdge) {//包含边缘
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
                    if (isFristTop(parent, position, spanCount)) {//若视图位置在最顶部
                        outRect.top = dividerHeight
                    }
                    outRect.bottom = dividerHeight
                } else {//不包含边缘
                    /**
                     * https://blog.csdn.net/qq_27192795/article/details/80563487
                     * 当视图有两列时，分割线只有一条，位于它们两中间，位置分别取它们的右（第一列）左（第二列），
                     * 大小为该分割线的一半，即每个视图需要分出1/2*分割线大小的位置充当分割线，这样两个视图的宽度才会一样。
                     * 这是只有两列的情况，若是有多列呢？通过上述的1/2，我们可以推断出两种计算方式，1/n、(n-1)/n。（n--表示列总数）
                     * 分别对这两种进行验证，可以得出(n-1)/n才是正确的。通过(n-1)/n可以算出多列的情况下，每个视图需要分出多少的位置。
                     * 通过上述，我们可以举一个例子：
                     *        1             2            3             4
                     *    左      右    左     右    左      右    左       右
                     *   0/4     3/4   1/4    2/4   2/4     1/4   3/4     0/4
                     * 该例子里共四列，通过(n-1)/n可以计算出每个视图需要分出多少的位置，即3/4分割线大小的位置。
                     * 那么每个视图的左右位置大小又该怎么计算呢？通过上述例子可以看出他的规律性，
                     * 左边计算方式：(n - 1)/spanCount（n--列数，spanCount--列总数）
                     * 右边计算方式：(spanCount - n)/spanCount（n--列数，spanCount--列总数）
                     */
                    outRect.left = (column - 1) * dividerWidth / spanCount
                    outRect.right = (spanCount - column) * dividerWidth / spanCount
                    if (!isLastBottom(parent, position, count, spanCount)) {//若视图位置不在最底部
                        outRect.bottom = dividerHeight
                    }
                }
            }
            GridLayoutManager.HORIZONTAL -> {//水平方向
                val row = calRow(position, spanCount)//行数
                if (includeEdge) {//包含边缘
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
                    if (isFristLeft(parent, position, spanCount)) {//若视图位置在最左边
                        outRect.left = dividerWidth
                    }
                    outRect.right = dividerWidth
                } else {//不包含边缘
                    /**
                     * https://blog.csdn.net/qq_27192795/article/details/80563487
                     * 当视图有两行时，分割线只有一条，位于它们两中间，位置分别取它们的顶（第一行）底（第二行），
                     * 大小为该分割线的一半，即每个视图需要分出1/2*分割线大小的位置充当分割线，这样两个视图的高度才会一样。
                     * 这是只有两行的情况，若是有多行呢？通过上述的1/2，我们可以推断出两种计算方式，1/n、(n-1)/n。（n--表示行总数）
                     * 分别对这两种进行验证，可以得出(n-1)/n才是正确的。通过(n-1)/n可以算出多行的情况下，每个视图需要分出多少的位置。
                     * 通过上述，我们可以举一个例子：
                     *        1             2            3             4
                     *    顶      底    顶     底    顶      底    顶       底
                     *   0/4     3/4   1/4    2/4   2/4     1/4   3/4     0/4
                     * 该例子里共四行，通过(n-1)/n可以计算出每个视图需要分出多少的位置，即3/4分割线大小的位置。
                     * 那么每个视图的顶底位置大小又该怎么计算呢？通过上述例子可以看出他的规律性，
                     * 顶部计算方式：(n - 1)/spanCount（n--行数，spanCount--行总数）
                     * 底部计算方式：(spanCount - n)/spanCount（n--行数，spanCount--行总数）
                     */
                    outRect.top = (row - 1) * dividerHeight / spanCount
                    outRect.bottom = (spanCount - row) * dividerHeight / spanCount
                    if (!isLastRight(parent, position, count, spanCount)) {//若视图位置不在最右边
                        outRect.right = dividerWidth
                    }
                }
            }
        }
    }

    /**
     * 计算视图列数
     */
    fun calColumn(position: Int, spanCount: Int) : Int{
        return (position % spanCount) + 1
    }

    /**
     * 计算视图行数
     */
    fun calRow(position: Int, spanCount: Int) : Int{
        return (position % spanCount) + 1
    }

    /**
     * 判断视图位置是否在最左测
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristLeft(parent: RecyclerView, position : Int, spanCount : Int) : Boolean {
        when(getOrientation(parent)){
            GridLayoutManager.VERTICAL->{//垂直方向
                return position % spanCount == 0
            }
            GridLayoutManager.HORIZONTAL->{//水平方向
                return position < spanCount
            }
            else-> return true
        }
    }

    /**
     * 判断视图位置是否在最顶部
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristTop(parent: RecyclerView, position : Int, spanCount : Int) : Boolean {
        when(getOrientation(parent)){
            GridLayoutManager.VERTICAL->{//垂直方向
                return position < spanCount
            }
            GridLayoutManager.HORIZONTAL->{//水平方向
                return position % spanCount == 0
            }
            else-> return true
        }
    }


    /**
     * 判断视图位置是否在最右测
     * position--位置
     * spanCount--列数、行数
     */
    fun isLastRight(parent: RecyclerView, position : Int, count : Int, spanCount : Int) : Boolean {
        when(getOrientation(parent)){
            GridLayoutManager.VERTICAL->{//垂直方向
                return (position + 1) % spanCount == 0
            }
            GridLayoutManager.HORIZONTAL->{//水平方向
                val column = (count / spanCount) + if(count % spanCount > 0) 1 else 0 //计算列数
                val rightTopPosition = (column * spanCount) - spanCount//计算右上的位置
                return position >= rightTopPosition//若所传位置大于等于右上的位置，则该视图位置在最右部
            }
            else-> return true
        }
    }



    /**
     * 判断视图位置是否在最底部
     * position--位置
     * count--视图数量
     * spanCount--列数、行数
     */
    fun isLastBottom(parent: RecyclerView, position : Int, count : Int, spanCount: Int) : Boolean{
        when(getOrientation(parent)){
            GridLayoutManager.VERTICAL->{//垂直方向
                val row = (count / spanCount) + if(count % spanCount > 0) 1 else 0 //计算行数
                val leftBottomPosition = (row * spanCount) - spanCount//计算左下的位置
                return position >= leftBottomPosition//若所传位置大于等于左下的位置，则该视图位置在最底部
            }
            GridLayoutManager.HORIZONTAL->{//水平方向
                return (position + 1) % spanCount == 0
            }
            else-> return true
        }
    }

    /**
     * 获取总列数、总行数
     */
    fun getSpanCount(parent: RecyclerView) : Int{
        if(parent.layoutManager is GridLayoutManager){
            return (parent.layoutManager as GridLayoutManager).spanCount
        }else{
            return 0
        }
    }

    /**
     * 获取方向
     */
    fun getOrientation(parent: RecyclerView) : Int {
        if(parent.layoutManager is GridLayoutManager){
            return (parent.layoutManager as GridLayoutManager).orientation
        }else{
            return GridLayoutManager.VERTICAL
        }
    }
}