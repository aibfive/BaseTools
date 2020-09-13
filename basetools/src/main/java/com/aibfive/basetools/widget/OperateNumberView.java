package com.aibfive.basetools.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.aibfive.basetools.R;


/**
 * JituAndroid
 *
 * @author lizewu
 * @date 2018/10/29
 */
public class OperateNumberView extends LinearLayout implements View.OnClickListener, View.OnTouchListener {

    private AppCompatTextView reduceTv;
    private AppCompatEditText numberEt;
    private AppCompatTextView plusTv;

    private OnNumberChangeListener onNumberChangeListener;
    private OnOperateNumberListener onOperateNumberListener;

    private boolean isAutoFill = true;  //点击加减按钮是否自动修改数字视图文本
    private Drawable operateButtonReduceBackground, operateButtonPlusBackground, numberViewBackground;
    private float numberTextSize = 24;
    private float operateTextSize = 24;
    private int operateButtonWidth = LayoutParams.WRAP_CONTENT;
    private int operateButtonHeight = LayoutParams.WRAP_CONTENT;
    private int numberViewWidth = LayoutParams.WRAP_CONTENT;
    private int numberViewHeight = LayoutParams.WRAP_CONTENT;
    private ColorStateList operateTextColor;
    private int numberTextColor = Color.BLACK;

    private final int minNumber = 1;  //最小数值
    private int maxNumber = 999999999;  //最大数值
    private boolean inputable = false;  //是否可以编辑

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {  //如果删除所有数字或者开头为0时，默认显示最小数值
                refreshOperateViewEnabled(minNumber);
                if (isAutoFill && onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(minNumber);
                }
            } else {
                int number = Integer.parseInt(s.toString());
                /**
                 * 若输入数字小于等于最小数字值，设置文本为最小数字值
                 * 若输入数字大于最大数字值，设置文本为最大数字值
                 */
                if (number < minNumber) {
                    number = minNumber;
                    setNumber(String.valueOf(number));
                } else if (number > maxNumber) {
                    number = maxNumber;
                    setNumber(String.valueOf(number));
                } else {
                    refreshOperateViewEnabled(number);
                    if (isAutoFill && onNumberChangeListener != null) {
                        onNumberChangeListener.onNumberChange(number);
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public OperateNumberView(Context context) {
        super(context);
        initAttribute(null, 0);
        initData();
        initView();
    }

    public OperateNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs, 0);
        initData();
        initView();
    }

    public OperateNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs, defStyleAttr);
        initData();
        initView();
    }

    private void initAttribute(AttributeSet attrs, int defStyle){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.OperateNumberView, defStyle, 0);
        numberTextColor = array.getColor(R.styleable.OperateNumberView_numberTextColor, numberTextColor);
        numberTextSize = array.getDimension(R.styleable.OperateNumberView_numberTextSize, numberTextSize);
        operateTextSize = array.getDimension(R.styleable.OperateNumberView_operateTextSize, operateTextSize);
        operateTextColor = array.getColorStateList(R.styleable.OperateNumberView_operateTextColor);
        maxNumber = array.getInteger(R.styleable.OperateNumberView_maxNumber, maxNumber);
        inputable = array.getBoolean(R.styleable.OperateNumberView_inputable, inputable);
        isAutoFill = array.getBoolean(R.styleable.OperateNumberView_isAutoFill, isAutoFill);
        numberViewWidth = array.getDimensionPixelSize(R.styleable.OperateNumberView_numberViewWidth, numberViewWidth);
        numberViewHeight = array.getDimensionPixelSize(R.styleable.OperateNumberView_numberViewHeight, numberViewHeight);
        operateButtonWidth = array.getDimensionPixelSize(R.styleable.OperateNumberView_operateButtonWidth, operateButtonWidth);
        operateButtonHeight = array.getDimensionPixelSize(R.styleable.OperateNumberView_operateButtonHeight, operateButtonHeight);
        operateButtonReduceBackground = array.getDrawable(R.styleable.OperateNumberView_operateButtonReduceBackground);
        operateButtonPlusBackground = array.getDrawable(R.styleable.OperateNumberView_operateButtonPlusBackground);
        numberViewBackground = array.getDrawable(R.styleable.OperateNumberView_numberViewBackground);
        array.recycle();
    }

    /**
     * 初始化减视图
     */
    private void initReduceView(){
        reduceTv = new AppCompatTextView(getContext());
        reduceTv.setId(R.id.tv_reduce);
        if(operateButtonReduceBackground != null) {
            reduceTv.setBackground(operateButtonReduceBackground);
        }
        reduceTv.setLayoutParams(new LayoutParams(operateButtonWidth, operateButtonHeight));
        reduceTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, operateTextSize);
        reduceTv.setTextColor(operateTextColor);
        reduceTv.setOnClickListener(this);
    }

    /**
     * 初始化数字视图
     */
    private void initNumberView(){
        numberEt = new AppCompatEditText(getContext());
        numberEt.setId(R.id.et_number);
        if(numberViewBackground != null) {
            numberEt.setBackground(numberViewBackground);
        }
        numberEt.setLayoutParams(new LayoutParams(numberViewWidth, numberViewHeight));
        numberEt.setFocusable(inputable);
        numberEt.setFocusableInTouchMode(inputable);
        numberEt.setLongClickable(inputable);
        numberEt.setInputType(inputable ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_NULL);
        numberEt.addTextChangedListener(textWatcher);
        numberEt.setTextSize(TypedValue.COMPLEX_UNIT_PX, numberTextSize);
        numberEt.setTextColor(numberTextColor);
        numberEt.setHint(String.valueOf(minNumber));
        numberEt.setOnTouchListener(this);
    }

    /**
     * 初始化加视图
     */
    private void initPlusView(){
        plusTv = new AppCompatTextView(getContext());
        plusTv.setId(R.id.tv_plus);
        if(operateButtonPlusBackground != null) {
            plusTv.setBackground(operateButtonPlusBackground);
        }
        plusTv.setLayoutParams(new LayoutParams(operateButtonWidth, operateButtonHeight));
        plusTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, operateTextSize);
        plusTv.setTextColor(operateTextColor);
        plusTv.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        if(operateTextColor == null) {
            int[] colors = new int[] { Color.BLACK, Color.GRAY };
            int[][] states = new int[2][];
            states[0] = new int[] { android.R.attr.state_enabled };
            states[1] = new int[] {};
            operateTextColor = new ColorStateList(states,colors);
        }
        isAutoFill = inputable ? inputable : isAutoFill;   //若inputable为true，isAutoFill便为true，反之，则值为自己。
    }

    private void initView(){
        initReduceView();
        initNumberView();
        initPlusView();
        addView(reduceTv);
        addView(numberEt);
        addView(plusTv);

        setNumber(String.valueOf(minNumber));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(numberEt.getId() == v.getId()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                /**
                 * 点击输入全选，方便输入
                 */
                numberEt.setFocusable(false);
                numberEt.setFocusableInTouchMode(false);
                numberEt.requestFocus();
                numberEt.setFocusable(true);
                numberEt.setFocusableInTouchMode(true);
                numberEt.requestFocus();
                numberEt.setSelectAllOnFocus(true);
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_reduce) {  //减
            if (numberEt.length() == 0) {
                return;
            }
            int reduceNumber = Integer.parseInt(numberEt.getText().toString());
            if (reduceNumber > minNumber) {
                reduceNumber--;
                if (isAutoFill) {
                    setNumber(String.valueOf(reduceNumber));
                } else {
                    if (onNumberChangeListener != null) {
                        onNumberChangeListener.onNumberChange(reduceNumber);
                    }
                    if (onOperateNumberListener != null) {
                        onOperateNumberListener.onOperateNumber(reduceTv, reduceNumber);
                    }
                }
            }
        } else if (id == R.id.tv_plus) {  //加
            String number = numberEt.getText().toString();
            int plusNumber;
            if (TextUtils.isEmpty(number)) {
                plusNumber = minNumber;
            } else {
                plusNumber = Integer.parseInt(number);
            }
            if (plusNumber < maxNumber) {
                plusNumber++;
                if (isAutoFill) {
                    setNumber(String.valueOf(plusNumber));
                } else {
                    if (onNumberChangeListener != null) {
                        onNumberChangeListener.onNumberChange(plusNumber);
                    }
                    if (onOperateNumberListener != null) {
                        onOperateNumberListener.onOperateNumber(plusTv, plusNumber);
                    }
                }
            }
        }
    }

    /**
     * 获取数量
     * @return
     */
    public int getNumber(){
        if(numberEt.length() == 0){
            return minNumber;
        }
        return Integer.parseInt(numberEt.getText().toString());
    }

    /**
     * 设置数量
     * @param number
     */
    public void setNumber(String number){
        numberEt.setText(number);
        numberEt.setSelection(numberEt.getText().toString().length());
    }

    /**
     * 设置数量
     * @param number
     */
    public void setNumber(int number){
        numberEt.setText(String.valueOf(number));
        numberEt.setSelection(numberEt.getText().toString().length());
    }

    /**
     * 设置最大数量
     * @param maxNumber
     */
    public void setMaxNumber(int maxNumber){
        this.maxNumber = maxNumber;
    }

    /**
     * 刷新操作视图可操作性
     * @param number
     */
    private void refreshOperateViewEnabled(int number){
        if(number == minNumber && number == maxNumber) {
            reduceTv.setEnabled(false);
            plusTv.setEnabled(false);
        }else if(number <= minNumber) {
            reduceTv.setEnabled(false);
            plusTv.setEnabled(true);
        }else if(number >= maxNumber) {
            reduceTv.setEnabled(true);
            plusTv.setEnabled(false);
        }else{
            reduceTv.setEnabled(true);
            plusTv.setEnabled(true);
        }
    }

    public interface OnNumberChangeListener{
        void onNumberChange(int number);
    }

    public interface OnOperateNumberListener{
        void onOperateNumber(View view, int number);
    }

    public void setOnNumberChangeListener(OnNumberChangeListener listener){
        this.onNumberChangeListener = listener;
    }

    public void setOnOperateNumberListener(OnOperateNumberListener listener){
        this.onOperateNumberListener = listener;
    }


}
