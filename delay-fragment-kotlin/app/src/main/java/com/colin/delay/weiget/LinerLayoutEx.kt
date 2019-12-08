package com.colin.delay.weiget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.LinearLayoutCompat
import com.colin.delay.R

class LinerLayoutEx : LinearLayoutCompat {
	
	var interceptTouchEvent = false
	
	constructor(context: Context) : super(context)
	
	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinerLayoutEx)
		try {
			interceptTouchEvent = typedArray.getBoolean(R.styleable.LinerLayoutEx_intercept_touch_event, false)
		} finally {
			typedArray.recycle()
		}
	}
	
	override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
		return interceptTouchEvent
	}
	
}