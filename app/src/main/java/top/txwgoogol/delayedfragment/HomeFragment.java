package top.txwgoogol.delayedfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class HomeFragment extends BaseLazyFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.home)
    TextView home;
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

//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        return viewRoot;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        toolbar.getMenu().clear();
//        inflater.inflate(R.menu.home, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.scan:
//                Toast.makeText(getActivity(), "点击了扫描菜单", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.vip:
//                Toast.makeText(getActivity(), "点击了会员菜单", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.msg:
//                Toast.makeText(getActivity(), "点击了消息菜单", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}