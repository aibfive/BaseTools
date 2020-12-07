package com.aibfive.basetools.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 正方体文本视图
 */
public class SquareTextView extends AppCompatTextView {

    public SquareTextView(Context context) {
        this(context, null);
    }

    public SquareTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, widthSize);
    }
}
