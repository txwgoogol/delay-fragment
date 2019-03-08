package top.txwgoogol.delayedfragment.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.txwgoogol.delayedfragment.R;
import top.txwgoogol.delayedfragment.base.BaseLazyFragment;

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class HomeFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.constraint_content)
    ConstraintLayout constraintContent;
    Unbinder unbinder;

    private View viewRoot;

    public static HomeFragment instance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText("首页");
        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.scan:
                        Toast.makeText(getActivity(), "点击了SCAN菜单", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.vip:
                        Toast.makeText(getActivity(), "点击了VIP菜单", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.msg:
                        Toast.makeText(getActivity(), "点击了MSG菜单", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);
        return viewRoot;
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();

        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(this);

        swipeRefresh.setRefreshing(true);
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                    constraintContent.setVisibility(View.VISIBLE);
                    swipeRefresh.setRefreshing(false);
                    isRefreshing = false;
                }
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                    home.setText("刷新后的数据HOME");
                    swipeRefresh.setRefreshing(false);
                    isRefreshing = false;
                }
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}