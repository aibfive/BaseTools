package com.aibfive.basetools.adapter.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/1/2.
 */

/**
 * 线性分割线
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private int dividerHeight;
    private Paint colorPaint = new Paint();
    private int include;
    private int leftMargin = 0;
    private int rightMargin = 0;
    private int topMargin = 0;
    private int bottomMargin = 0;

    public static final int VERTICAL_INCLUDE_NULL = 0x001;
    public static final int VERTICAL_INCLUDE_TOP = 0x002;
    public static final int VERTICAL_INCLUDE_BOTTOM = 0x003;
    public static final int VERTICAL_INCLUDE_All = 0x004;

    public static final int HORIZONTAL_INCLUDE_NULL = 0x005;
    public static final int HORIZONTAL_INCLUDE_LEFT = 0x006;
    public static final int HORIZONTAL_INCLUDE_RIGHT = 0x007;
    public static final int HORIZONTAL_INCLUDE_ALL = 0x008;

    public LinearItemDecoration(int dividerHeight, int color, int include){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.include = include;
    }

    public void setVerticalMargin(int topMargin, int bottomMargin){
        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
    }

    public void setHorizontalMargin(int leftMargin, int rightMargin){
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int orientation = getOrientation(parent);
        if(orientation == RecyclerView.HORIZONTAL){
            int top = parent.getPaddingTop() + topMargin;
            int bottom = parent.getHeight() - parent.getPaddingBottom() - bottomMargin;
            int count = parent.getChildCount();    //屏幕中所能显示itemView数量。
            for(int i = 0; i < count; i++){
                View view = parent.getChildAt(i);
                int left, right;
                switch (include){
                    case HORIZONTAL_INCLUDE_NULL:  //不包含左、右边
                        if(i != 0 || i != count - 1){
                            left = view.getRight();
                            right = view.getRight() + dividerHeight;
                            c.drawRect(left, top, right, bottom, colorPaint);
                        }
                        break;
                    case HORIZONTAL_INCLUDE_LEFT:  //包含左边
                        left = view.getLeft() - dividerHeight;
                        right = view.getLeft();
                        c.drawRect(left, top, right, bottom, colorPaint);
                        break;
                    case HORIZONTAL_INCLUDE_RIGHT:  //包含右边
                        left = view.getRight();
                        right = view.getRight() + dividerHeight;
                        c.drawRect(left, top, right, bottom, colorPaint);
                        break;
                    case HORIZONTAL_INCLUDE_ALL:  //包含左、右边
                        if(i == 0){
                            left = view.getLeft()- dividerHeight;
                            right = view.getLeft();
                            c.drawRect(left, top, right, bottom, colorPaint);
                        }
                        left = view.getRight();
                        right = view.getRight() + dividerHeight;
                        c.drawRect(left, top, right, bottom, colorPaint);
                        break;
                    default:
                        break;
                }
            }
        }else if(orientation == RecyclerView.VERTICAL){
            int left = parent.getPaddingLeft() + leftMargin;
            int right = parent.getWidth() - parent.getPaddingRight() - rightMargin;
            int count = parent.getChildCount();    //屏幕中所能显示itemView数量。
            for(int i = 0; i < count; i++){
                View view = parent.getChildAt(i);
                int top, bottom;
                switch (include){
                    case VERTICAL_INCLUDE_NULL:  //不包含顶、底部
                        if(i != count - 1){
                            top = view.getBottom();
                            bottom = view.getBottom() + dividerHeight;
                            c.drawRect(left, top, right, bottom, colorPaint);
                        }
                        break;
                    case VERTICAL_INCLUDE_TOP:  //包含顶
                        top = view.getTop() - dividerHeight;
                        bottom = view.getTop();
                        c.drawRect(left, top, right, bottom, colorPaint);
                        break;
                    case VERTICAL_INCLUDE_BOTTOM:  //包含底部
                        top = view.getBottom();
                        bottom = view.getBottom() + dividerHeight;
                        c.drawRect(left, top, right, bottom, colorPaint);
                        break;
                    case VERTICAL_INCLUDE_All:  //包含顶、底部
                        if(i == 0){
                            top = view.getTop() - dividerHeight;
                            bottom = view.getTop();
                            c.drawRect(left, top, right, bottom, colorPaint);
                        }
                        top = view.getBottom();
                        bottom = view.getBottom() + dividerHeight;
                        c.drawRect(left, top, right, bottom, colorPaint);

                        break;
                    default:
                        break;
                }


            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int count = parent.getAdapter().getItemCount();
        int orientation = getOrientation(parent);
        if(orientation == RecyclerView.HORIZONTAL){
            switch (include){
                case HORIZONTAL_INCLUDE_NULL:  //不包含左、右边
                    if(parent.getChildAdapterPosition(view) != count - 1){
                        outRect.right = dividerHeight;
                    }
                    break;
                case HORIZONTAL_INCLUDE_LEFT:  //包含左边
                    outRect.left = dividerHeight;
                    break;
                case HORIZONTAL_INCLUDE_RIGHT:  //包含右边
                    outRect.right = dividerHeight;
                    break;
                case HORIZONTAL_INCLUDE_ALL:  //包含左、右边
                    if(parent.getChildAdapterPosition(view) == 0){
                        outRect.left = dividerHeight;
                    }
                    outRect.right = dividerHeight;
                    break;
                default:
                    break;
            }
        }else if(orientation == RecyclerView.VERTICAL) {
            switch (include) {
                case VERTICAL_INCLUDE_NULL:  //不包含顶、底部
                    if(parent.getChildAdapterPosition(view) != count - 1){
                        outRect.bottom = dividerHeight;
                    }
                    break;
                case VERTICAL_INCLUDE_TOP:  //包含顶
                    outRect.top = dividerHeight;
                    break;
                case VERTICAL_INCLUDE_BOTTOM:  //包含底部
                    outRect.bottom = dividerHeight;
                    break;
                case VERTICAL_INCLUDE_All:  //包含顶、底部
                    if(parent.getChildAdapterPosition(view) == 0){
                        outRect.top = dividerHeight;
                    }
                    outRect.bottom = dividerHeight;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 获取方向
     * @param parent
     * @return
     */
    private int getOrientation(RecyclerView parent){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager){
            return ((LinearLayoutManager)layoutManager).getOrientation();
        }else{
            return RecyclerView.VERTICAL;
        }
    }
}