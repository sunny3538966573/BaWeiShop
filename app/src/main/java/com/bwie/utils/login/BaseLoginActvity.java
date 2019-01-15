package com.bwie.utils.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * date: 2018/12/30.
 * Created by Administrator
 * function:
 */
public abstract class BaseLoginActvity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getcontentview());
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化布局
     * @return
     */
    public abstract int getcontentview();
}
