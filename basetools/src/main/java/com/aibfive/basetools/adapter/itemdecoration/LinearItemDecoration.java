package com.aibfive.basetools.adapter.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/1/2.
 */

/**
 *
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private int dividerHeight;
    private Paint colorPaint = new Paint();
    public static final int HORIZONTAL = 0x01;  //水平
    public static final int VERTICAL = 0x02;  //垂直
    private int orientation = VERTICAL;
    private int include;
    private int leftMargin = 0;
    private int rightMargin = 0;

    public static final int VERTICAL_INCLUDE_NULL = 0x001;
    public static final int VERTICAL_INCLUDE_TOP = 0x002;
    public static final int VERTICAL_INCLUDE_BOTTOM = 0x003;
    public static final int VERTICAL_INCLUDE_All = 0x004;

    public static final int HORIZONTAL_INCLUDE_NULL = 0x005;
    public static final int HORIZONTAL_INCLUDE_LEFT = 0x006;
    public static final int HORIZONTAL_INCLUDE_RIGHT = 0x007;
    public static final int HORIZONTAL_INCLUDE_ALL = 0x008;

    public LinearItemDecoration(int dividerHeight, int color){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.orientation = VERTICAL;
        if(orientation == HORIZONTAL){
            this.include = HORIZONTAL_INCLUDE_ALL;
        }else if(orientation == VERTICAL) {
            this.include = VERTICAL_INCLUDE_All;
        }
    }

    public LinearItemDecoration(int dividerHeight, int color, int orientation){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.orientation = orientation;
        if(orientation == HORIZONTAL){
            this.include = HORIZONTAL_INCLUDE_ALL;
        }else if(orientation == VERTICAL) {
            this.include = VERTICAL_INCLUDE_All;
        }
    }

    public LinearItemDecoration(int dividerHeight, int color, int orientation, int include){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.orientation = orientation;
        this.include = include;
    }

    public LinearItemDecoration(int dividerHeight, int color, int orientation, int include, int leftMargin){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.orientation = orientation;
        this.include = include;
        this.leftMargin = leftMargin;
    }

    public LinearItemDecoration(int dividerHeight, int color, int orientation, int include, int leftMargin, int rightMargin){
        this.dividerHeight = dividerHeight;
        colorPaint.setColor(color);
        this.orientation = orientation;
        this.include = include;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }

    public LinearItemDecoration setOrientation(int orientation){
        this.orientation = orientation;
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if(orientation == HORIZONTAL){
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();
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
        }else if(orientation == VERTICAL){
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
        if(orientation == HORIZONTAL){
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
        }else if(orientation == VERTICAL) {
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
}