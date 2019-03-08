package top.txwgoogol.delayedfragment.main.my;

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
public class MyFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.my)
    TextView my;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.constraint_content)
    ConstraintLayout constraintContent;
    private View viewRoot;
    Unbinder unbinder;

    public static MyFragment instance() {
        return new MyFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title.setText("我的");
        toolbar.inflateMenu(R.menu.my);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.my:
                        Toast.makeText(getActivity(), "点击了我的菜单", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_my, container, false);
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
                    my.setText("刷新后的数据MY");
                    swipeRefresh.setRefreshing(false);
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