package com.bwie.utils.http;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.LoginDao;
import com.bwie.utils.login.WDApplication;
import com.bwie.utils.recyclerview.LogUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment的基类
 */
public abstract class WDFragment extends Fragment {
    public Gson mGson = new Gson();
    public SharedPreferences mShare = WDApplication.getShare();

    private Unbinder unbinder;
    public Login LOGIN_USER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //查询数据库，并且判断登录的状态
        LoginDao loginDao = DaoMaster.newDevSession(getActivity(), LoginDao.TABLENAME).getLoginDao();
        List<Login> list = loginDao.queryBuilder().where(LoginDao.Properties.Statues.eq(1)).list();
        LOGIN_USER = list.get(0);//读取第一项

        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        View view = inflater.inflate(getLayoutId(),container,false);//加载布局的方法getLayoutId
        unbinder = ButterKnife.bind(this,view);
        initView();//初始化视图
        LogUtils.e(this.toString()+"页面加载使用："+(System.currentTimeMillis()-time));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //	@Override
//	public void onResume() {
//		super.onResume();
//		if (!MTStringUtils.isEmpty(getPageName()))
//			MobclickAgent.onPageStart(getPageName()); // 统计页面
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		if (!MTStringUtils.isEmpty(getPageName()))
//			MobclickAgent.onPageEnd(getPageName());// 统计页面
//	}

    /**
     * 设置页面名字 用于友盟统计
     */
    public abstract String getPageName();
    /**
     * 设置layoutId
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     */
    protected abstract void initView();
}

