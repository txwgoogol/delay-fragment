package com.colin.delay.main.data

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
import kotlinx.android.synthetic.main.fragment_data.*

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
class DataFragment : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		title.text = "数据"
		toolbar.inflateMenu(R.menu.data)

		toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
			when (item.itemId) {
				R.id.data -> {
					Toast.makeText(activity, "点击了数据菜单", Toast.LENGTH_SHORT).show()
					return@OnMenuItemClickListener true
				}
			}
			false
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_data, container, false)
	}

	override fun onFirstUserVisible() {
		super.onFirstUserVisible()

		swipe_refresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
		swipe_refresh.setOnRefreshListener(this)
		
		swipe_refresh.isRefreshing = true
		onNavigationClickListener(isRefreshing = true)
		
		toolbar.postDelayed({
			if (swipe_refresh != null && swipe_refresh.isRefreshing) {
				constraint_content.visibility = View.VISIBLE
				swipe_refresh.isRefreshing = false
				onNavigationClickListener(isRefreshing = false)
			}
		}, 2000)
	}


	override fun onRefresh() {
		onNavigationClickListener(isRefreshing = true)
		toolbar.postDelayed({
			if (swipe_refresh != null && swipe_refresh.isRefreshing) {
				data_fra.text = "刷新后的数据DATA"
				swipe_refresh.isRefreshing = false
				onNavigationClickListener(isRefreshing = false)
			}
		}, 500)
	}

	companion object {
		fun instance(): DataFragment {
			return DataFragment()
		}
	}

}