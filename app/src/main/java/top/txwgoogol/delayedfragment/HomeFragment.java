package top.txwgoogol.delayedfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;

    private View viewRoot;

    public static HomeFragment instance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        title.setText("首页");

        return viewRoot;
    }

}