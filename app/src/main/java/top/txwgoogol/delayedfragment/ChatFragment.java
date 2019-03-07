package top.txwgoogol.delayedfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 首页
 *
 * @author txw
 * @// TODO: 3/7/19
 */
public class ChatFragment extends BaseLazyFragment {

    private View viewRoot;

    public static ChatFragment instance() {
        return new ChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_chat, container, false);
        return viewRoot;
    }

}