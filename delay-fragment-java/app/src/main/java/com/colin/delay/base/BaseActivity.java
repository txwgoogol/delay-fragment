package com.colin.delay.base;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.colin.delay.R;
import com.colin.delay.weiget.LinearLayoutEx;

public class BaseActivity extends AppCompatActivity {


    //===================================双击退出应用=======================================
    private static final int TIME_EXIT = 500;
    private static long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_EXIT > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mBackPressed = System.currentTimeMillis();
        }
    }
    //===================================双击退出应用=======================================


    //=================================控制底面导航切换=====================================
    /**
     * 如果是正在刷新的 则不可切换页面 否则可以随意切换 提升用户交互体验
     * 暂时没有实现
     *
     * @param isRefreshing
     * @param activity
     */
    public static void onChangeListener(boolean isRefreshing, Activity activity) {
        ((LinearLayoutEx)activity.findViewById(R.id.ll_navigation)).setInterceptTouchEvent(isRefreshing);
//        if (isRefreshing) {
//            ((LinearLayoutEx)activity.findViewById(R.id.ll_navigation)).setInterceptTouchEvent(true);
//            //activity.findViewById(R.id.fl_view).setVisibility(View.VISIBLE);
//            Log.d("TAG", "底部导航按钮可以点击状态");
//        } else {
//            ((LinearLayoutEx)activity.findViewById(R.id.ll_navigation)).setInterceptTouchEvent(false);
//            //activity.findViewById(R.id.fl_view).setVisibility(View.INVISIBLE);
//            Log.d("TAG", "底部导航按钮=============不================可点击状态");
//        }
    }
    //=================================控制底面导航切换=====================================


}