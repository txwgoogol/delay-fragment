package com.colin.delay.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.colin.delay.R;
import com.colin.delay.base.BaseActivity;
import com.colin.delay.base.BasePageAdapter;
import com.colin.delay.main.chat.ChatFragment;
import com.colin.delay.main.data.DataFragment;
import com.colin.delay.main.home.HomeFragment;
import com.colin.delay.main.my.MyFragment;
import com.colin.delay.main.school.SchoolFragment;
import com.colin.delay.weiget.ViewPagerEx;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;

/**
 * 主页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPagerEx viewPager;
    @BindView(R.id.navigation_view)
    BottomNavigationViewEx navigationView;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLayout();
    }

    private void initLayout() {
        fragments.add(HomeFragment.instance());
        fragments.add(DataFragment.instance());
        fragments.add(ChatFragment.instance());
        fragments.add(SchoolFragment.instance());
        fragments.add(MyFragment.instance());

        BasePageAdapter basePageAdapter = new BasePageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(basePageAdapter);
        viewPager.setOffscreenPageLimit(5);  //这个是延时加载的关键
        navigationView.setupWithViewPager(viewPager);

        navigationView.setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int position = menuItem.getItemId();
                if (position != previousPosition) {
                    previousPosition = position;
                    switch (previousPosition) {
                        case R.id.navigation_home:
                            viewPager.setCurrentItem(0);
                            return true;
                        case R.id.navigation_data:
                            viewPager.setCurrentItem(1);
                            return true;
                        case R.id.navigation_chat:
                            viewPager.setCurrentItem(2);
                            return true;
                        case R.id.navigation_school:
                            viewPager.setCurrentItem(3);
                            return true;
                        case R.id.navigation_my:
                            viewPager.setCurrentItem(4);
                            return true;
                    }
                }
                return false;
            }
        });
    }

}