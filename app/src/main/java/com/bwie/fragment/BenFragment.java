package com.bwie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.baweishop.R;
import com.bwie.fragment.benfragment.FinishFragment;
import com.bwie.fragment.benfragment.AllListFragment;
import com.bwie.fragment.benfragment.MoneyFragment;
import com.bwie.fragment.benfragment.CommonFragment;
import com.bwie.fragment.benfragment.ReciveFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BenFragment extends Fragment {

    @BindView(R.id.fragment_order_allorder)
    RadioButton fragmentOrderAllorder;
    @BindView(R.id.fragment_order_await)
    RadioButton fragmentOrderAwait;
    @BindView(R.id.fragment_order_evaluate)
    RadioButton fragmentOrderEvaluate;
    @BindView(R.id.fragment_order_tack)
    RadioButton fragmentOrderTack;
    @BindView(R.id.fragment_order_accomplish)
    RadioButton fragmentOrderAccomplish;
    @BindView(R.id.ben_rg)
    RadioGroup benRg;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private List<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ben, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new AllListFragment());
        fragments.add(new MoneyFragment());
        fragments.add(new CommonFragment());
        fragments.add(new ReciveFragment());
        fragments.add(new FinishFragment());
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_order_allorder, R.id.fragment_order_await, R.id.fragment_order_evaluate, R.id.fragment_order_tack, R.id.fragment_order_accomplish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_order_allorder:
                vp.setCurrentItem(0);
                break;
            case R.id.fragment_order_await:
                vp.setCurrentItem(1);
                break;
            case R.id.fragment_order_evaluate:
                vp.setCurrentItem(2);
                break;
            case R.id.fragment_order_tack:
                vp.setCurrentItem(3);
                break;
            case R.id.fragment_order_accomplish:
                vp.setCurrentItem(4);
                break;
        }
    }
}
