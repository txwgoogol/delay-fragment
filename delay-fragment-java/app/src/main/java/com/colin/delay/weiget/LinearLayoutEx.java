package com.colin.delay.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.colin.delay.R;

/**
 * 重写LinearLayoutCompat 实现控制子类控件的拦截 从而控制主页 刷新的时候 导航的点击状态
 */
public class LinearLayoutEx extends LinearLayoutCompat {

    private boolean interceptTouchEvent = false;

    public LinearLayoutEx(Context context) {
        super(context);
    }

    public LinearLayoutEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutEx);
        try {
            interceptTouchEvent = typedArray.getBoolean(R.styleable.LinearLayoutEx_intercept_touch_event, false);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isInterceptTouchEvent();
    }

    public boolean isInterceptTouchEvent() {
        return interceptTouchEvent;
    }

    public void setInterceptTouchEvent(boolean interceptTouchEvent) {
        this.interceptTouchEvent = interceptTouchEvent;
    }

}