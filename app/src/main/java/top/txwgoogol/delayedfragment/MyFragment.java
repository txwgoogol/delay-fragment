package top.txwgoogol.delayedfragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class MyFragment extends BaseLazyFragment {

    private View viewRoot;

    public static MyFragment instance() {
        return new MyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_my, container, false);
        return viewRoot;
    }

}