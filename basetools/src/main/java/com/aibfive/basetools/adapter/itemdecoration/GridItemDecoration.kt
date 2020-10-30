package com.aibfive.basetools.adapter.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.aibfive.basetools.util.LogUtil
import kotlin.properties.Delegates

/**
 * Date : 2020/10/28/028
 * Time : 11:02
 * author : Li
 */
class GridItemDecoration : RecyclerView.ItemDecoration {

    companion object {
        const val ORIENTATION_VERTICAL = 1//垂直方向
        const val ORIENTATION_HORIZONTAL = 2//水平方向
    }

    var orientation : Int = ORIENTATION_VERTICAL//方向
    var dividerSize : Int = 1//分割线尺寸
    var includeEdge : Boolean = true//是否包含边缘
    var dividerColor : Int = Color.WHITE//分割线颜色

    constructor(orientation : Int, dividerSize : Int, dividerColor : Int, includeEdge : Boolean){
        this.orientation = orientation
        this.dividerSize = dividerSize
        this.dividerColor = dividerColor
        this.includeEdge = includeEdge
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.adapter == null){
            return
        }
        val count = parent.adapter!!.itemCount
        val spanCount = getSpanCount(parent)
        val position = parent.getChildAdapterPosition(view)
        when(orientation){
            ORIENTATION_VERTICAL->{
                if(includeEdge){//包含边缘
                    if(isFristLeft(position, spanCount)){//若视图位置在最左测
                        outRect.left = dividerSize
                    }
                    if(isFristTop(position, spanCount)){//若视图位置在最顶部
                        outRect.top = dividerSize
                    }
                    outRect.bottom = dividerSize
                    outRect.right = dividerSize
                }else{//不包含边缘
                    if(!isLastRight(position, spanCount)){//视图位置不在最右测
                        LogUtil.v(GridItemDecoration::class.simpleName, "unincluderight.position-->"+position)
                        outRect.right = dividerSize
                    }
                    if(!isLastBottom(position, count, spanCount)){//视图位置不在最底部
                        LogUtil.v(GridItemDecoration::class.simpleName, "unincludebottom.position-->"+position)
                        outRect.bottom = dividerSize
                    }
                }
            }
            ORIENTATION_HORIZONTAL->{

            }
        }

    }

    /**
     * 判断视图位置是否在最左测
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristLeft(position : Int, spanCount : Int) : Boolean {
        when(orientation){
            ORIENTATION_VERTICAL->{//垂直方向
                return position % spanCount == 0
            }
            ORIENTATION_HORIZONTAL->{//水平方向
                return true
            }
            else-> return true
        }
    }

    /**
     * 判断视图位置是否在最顶部
     * position--位置
     * spanCount--列数、行数
     */
    fun isFristTop(position : Int, spanCount : Int) : Boolean {
        when(orientation){
            ORIENTATION_VERTICAL->{//垂直方向
                return position < spanCount
            }
            ORIENTATION_HORIZONTAL->{//水平方向
                return true
            }
            else-> return true
        }
    }


    /**
     * 判断视图位置是否在最右测
     * position--位置
     * spanCount--列数、行数
     */
    fun isLastRight(position : Int, spanCount : Int) : Boolean {
        when(orientation){
            ORIENTATION_VERTICAL->{//垂直方向
                return (position + 1) % spanCount == 0
            }
            ORIENTATION_HORIZONTAL->{//水平方向
                return true
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
    fun isLastBottom(position : Int, count : Int, spanCount: Int) : Boolean{
        when(orientation){
            ORIENTATION_VERTICAL->{//垂直方向
                val row = (count / spanCount) + (count % spanCount) //计算行数
                val leftBottomPosition = (row * spanCount) - spanCount//计算左下的位置
                return position >= leftBottomPosition//若所传位置大于左下的位置，则该视图位置在最底部
            }
            ORIENTATION_HORIZONTAL->{//水平方向
                return true
            }
            else-> return true
        }
    }

    /**
     * 获取列数、行数
     */
    fun getSpanCount(parent: RecyclerView) : Int{
        if(parent.layoutManager is GridLayoutManager){
            return (parent.layoutManager as GridLayoutManager).spanCount
        }else if(parent.layoutManager is StaggeredGridLayoutManager){
            return (parent.layoutManager as StaggeredGridLayoutManager).spanCount
        }else{
            return 0
        }
    }
}