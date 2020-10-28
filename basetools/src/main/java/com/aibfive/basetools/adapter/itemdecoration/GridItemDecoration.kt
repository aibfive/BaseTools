package com.aibfive.basetools.adapter.itemdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * Date : 2020/10/28/028
 * Time : 11:02
 * author : Li
 */
class GridItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        const val ORIENTATION_VERTICAL = 1
        const val ORIENTATION_HORIZONTAL = 2
    }

    var orientation : Int = ORIENTATION_VERTICAL



    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if(parent.adapter == null){
            return
        }
        val count = parent.adapter?.itemCount
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