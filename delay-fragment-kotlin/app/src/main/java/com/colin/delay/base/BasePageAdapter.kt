package com.colin.delay.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BasePageAdapter : FragmentStatePagerAdapter {

	private var fragments: List<Fragment>? = null
	private var titles =  mutableListOf<String>()

	constructor(fm: FragmentManager) : super(fm) {}

	constructor(fm: FragmentManager, fragments: List<Fragment>) : super(fm) {
		this.fragments = fragments
	}

	constructor(fm: FragmentManager, fragments: List<Fragment>, titles: MutableList<String>) : super(fm) {
		this.fragments = fragments
		this.titles = titles
	}

	override fun getItem(i: Int): Fragment {
		return fragments!![i]
	}

	override fun getCount(): Int {
		return if (fragments == null) 0 else fragments!!.size
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return titles[position]
	}

}