package com.colin.delay.weiget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 禁止滚动
 *
 * @author txw
 * @// TODO: 3/7/19
 */
class ViewPagerEx : ViewPager {

	constructor(context: Context) : super(context) {}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

	override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
		return false
	}

	override fun onTouchEvent(ev: MotionEvent): Boolean {
		return false
	}

	override fun setCurrentItem(item: Int) {
		super.setCurrentItem(item, false)
	}

}