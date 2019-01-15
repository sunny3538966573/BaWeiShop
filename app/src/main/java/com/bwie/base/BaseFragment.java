package com.bwie.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date: 2018/12/30.
 * Created by Administrator
 * function:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentview(), container, false);
        initView(view);
        initData(view);
        return view;

    }

    /**
     * 初始化数据
     * @param view
     */
    public abstract void initData(View view);

    /**
     * 初始化控件
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 初始化布局
     * @return
     */
    public abstract int getContentview();
}
