package com.colin.delay.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.colin.delay.R
import com.colin.delay.base.BasePageAdapter
import com.colin.delay.main.chat.ChatFragment
import com.colin.delay.main.data.DataFragment
import com.colin.delay.main.home.HomeFragment
import com.colin.delay.main.my.MyFragment
import com.colin.delay.main.school.SchoolFragment
import com.colin.delay.weiget.LinerLayoutEx
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * 主页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
class MainActivity : AppCompatActivity() {
	
	lateinit var basePageAdapter: BasePageAdapter
	private val fragments = ArrayList<Fragment>()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		llNavigation = findViewById(R.id.ll_navigation)
		llNavigation.visibility = View.VISIBLE
		
		fragments.add(HomeFragment.instance())
		fragments.add(DataFragment.instance())
		fragments.add(ChatFragment.instance())
		fragments.add(SchoolFragment.instance())
		fragments.add(MyFragment.instance())

		basePageAdapter = BasePageAdapter(supportFragmentManager, fragments)
		view_pager.adapter = basePageAdapter
		view_pager.offscreenPageLimit = 5  //这个是延时加载的关键
		navigation_view.setupWithViewPager(view_pager)
		
		navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
		navigation_view.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {

			private var previousPosition = -1

			override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
				val position = menuItem.itemId
				if (position != previousPosition) {
					previousPosition = position
					when (previousPosition) {
						R.id.navigation_home -> {
							view_pager.currentItem = 0
							return true
						}
						R.id.navigation_data -> {
							view_pager.currentItem = 1
							return true
						}
						R.id.navigation_chat -> {
							view_pager.currentItem = 2
							return true
						}
						R.id.navigation_school -> {
							view_pager.currentItem = 3
							return true
						}
						R.id.navigation_my -> {
							view_pager.currentItem = 4
							return true
						}
					}
				}
				return false
			}
		})
	}
	
	// ============================= 主页导航切换控制 =============================
	companion object {
		lateinit var llNavigation:LinerLayoutEx
		/**
		 * 如果是正在刷新的 则不可切换页面 否则可以随意切换 提升用户交互体验
		 */
		fun onNavigationClickListener(isRefreshing: Boolean) {
			llNavigation.interceptTouchEvent = isRefreshing
		}
	}
	// ============================= 主页导航切换控制 =============================
	
}