package com.colin.delay.main.data;

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

import com.colin.delay.R;
import com.colin.delay.base.BaseActivity;
import com.colin.delay.base.BaseLazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class DataFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_data)
    TextView fragmentData;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.constraint_content)
    ConstraintLayout constraintContent;
    Unbinder unbinder;

    private View viewRoot;

    public static DataFragment instance() {
        return new DataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title.setText("数据");
        toolbar.inflateMenu(R.menu.data);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.data:
                        Toast.makeText(getActivity(), "点击了数据菜单", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_data, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);
        return viewRoot;
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();

        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(this);

        BaseActivity.onChangeListener(isRefreshing = true, getActivity());
        swipeRefresh.setRefreshing(true);
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                    constraintContent.setVisibility(View.VISIBLE);
                    swipeRefresh.setRefreshing(false);
                    BaseActivity.onChangeListener(isRefreshing = false, getActivity());
                }
            }
        }, 2000);
    }


    @Override
    public void onRefresh() {
        BaseActivity.onChangeListener(isRefreshing = true, getActivity());
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                    fragmentData.setText("刷新后的数据DATA");
                    swipeRefresh.setRefreshing(false);
                    BaseActivity.onChangeListener(isRefreshing = false, getActivity());
                }
            }
        }, 500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}