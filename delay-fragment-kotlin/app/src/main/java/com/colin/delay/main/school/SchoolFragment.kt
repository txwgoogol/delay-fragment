package com.colin.delay.main.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.colin.delay.R
import com.colin.delay.base.BaseLazyFragment
import com.colin.delay.main.MainActivity.Companion.onNavigationClickListener
import kotlinx.android.synthetic.main.fragment_school.*

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
class SchoolFragment : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		title.text = "商学院"
		toolbar.inflateMenu(R.menu.school)
		toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
			when (item.itemId) {
				R.id.school -> {
					Toast.makeText(activity, "点击了School菜单", Toast.LENGTH_SHORT).show()
					return@OnMenuItemClickListener true
				}
			}
			false
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_school, container, false)
	}

	override fun onFirstUserVisible() {
		super.onFirstUserVisible()

		swipe_refresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
		swipe_refresh.setOnRefreshListener(this)
		
		swipe_refresh.isRefreshing = true
		onNavigationClickListener(isRefreshing = true)
		
		toolbar.postDelayed( {
			if (swipe_refresh != null && swipe_refresh.isRefreshing) {
				constraint_content.visibility = View.VISIBLE
				swipe_refresh.isRefreshing = false
				onNavigationClickListener(isRefreshing = false)
			}
		}, 2000)
	}


	override fun onRefresh() {
		onNavigationClickListener(isRefreshing = true)
		toolbar.postDelayed( {
			if (swipe_refresh != null && swipe_refresh.isRefreshing) {
				school_fra.text = "刷新后的数据SCHOOL"
				swipe_refresh.isRefreshing = false
				onNavigationClickListener(isRefreshing = false)
			}
		}, 1000)
	}
	
	companion object {
		fun instance(): SchoolFragment {
			return SchoolFragment()
		}
	}

}