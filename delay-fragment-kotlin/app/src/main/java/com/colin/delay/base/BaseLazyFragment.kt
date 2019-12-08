package com.colin.delay.base

import android.os.Bundle

/**
 * 懒加载
 *
 * @author txw
 * @// TODO: 3/7/19
 */
open class BaseLazyFragment : BaseFragment() {

	private var isPrepared: Boolean = false

	/**
	 * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
	 */
	private var isFirstResume = true

	private var isFirstVisible = true
	private var isFirstInvisible = true

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initPrepare()
	}

	override fun onResume() {
		super.onResume()
		if (isFirstResume) {
			isFirstResume = false
			return
		}
		if (userVisibleHint) {
			onUserVisible()
		}
	}

	override fun onPause() {
		super.onPause()
		if (userVisibleHint) {
			onUserInvisible()
		}
	}

	override fun setUserVisibleHint(isVisibleToUser: Boolean) {
		super.setUserVisibleHint(isVisibleToUser)
		if (isVisibleToUser) {
			if (isFirstVisible) {
				isFirstVisible = false
				initPrepare()
			} else {
				onUserVisible()
			}
		} else {
			if (isFirstInvisible) {
				isFirstInvisible = false
				onFirstUserInvisible()
			} else {
				onUserInvisible()
			}
		}
	}

	@Synchronized
	fun initPrepare() {
		if (isPrepared) {
			onFirstUserVisible()
		} else {
			isPrepared = true
		}
	}

	/**
	 * 第一次fragment可见（进行初始化工作）
	 */
	open fun onFirstUserVisible() {

	}

	/**
	 * fragment可见（切换回来或者onResume）
	 */
	private fun onUserVisible() {

	}

	/**
	 * 第一次fragment不可见（不建议在此处理事件）
	 */
	private fun onFirstUserInvisible() {

	}

	/**
	 * fragment不可见（切换掉或者onPause）
	 */
	private fun onUserInvisible() {

	}

}