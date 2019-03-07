package top.txwgoogol.delayedfragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPagerEx viewPager;
    @BindView(R.id.navigation_view)
    BottomNavigationViewEx navigationView;

    private BasePageAdapter basePageAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragments.add(HomeFragment.instance());
        fragments.add(DataFragment.instance());
        fragments.add(ChatFragment.instance());
        fragments.add(SchoolFragment.instance());
        fragments.add(MyFragment.instance());

        basePageAdapter = new BasePageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(basePageAdapter);
        navigationView.setupWithViewPager(viewPager);

        navigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
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