package com.bwie.actvity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.fragment.CarFragment;
import com.bwie.fragment.UfoFragment;
import com.bwie.fragment.ListFragment;
import com.bwie.fragment.UserFragment;
import com.bwie.fragment.BenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.btn_home)
    TextView btnHome;
    @BindView(R.id.btn_circle)
    TextView btnCircle;
    @BindView(R.id.btn_cart)
    TextView btnCart;
    @BindView(R.id.btn_order)
    TextView btnOrder;
    @BindView(R.id.btn_my)
    TextView btnMy;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new UfoFragment());
        fragments.add(new CarFragment());
        fragments.add(new BenFragment());
        fragments.add(new UserFragment());
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
            ChangeBackGround(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认显示第一个页面
        btnHome.setBackgroundResource(R.drawable.homes);
    }

    @OnClick({R.id.btn_home, R.id.btn_circle, R.id.btn_cart, R.id.btn_order, R.id.btn_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                vp.setCurrentItem(0);
                ChangeBackGround(0);
                break;
            case R.id.btn_circle:
                vp.setCurrentItem(1);
                ChangeBackGround(1);
                break;
            case R.id.btn_cart:
                vp.setCurrentItem(2);
                ChangeBackGround(2);
                break;
            case R.id.btn_order:
                vp.setCurrentItem(3);
                ChangeBackGround(3);
                break;
            case R.id.btn_my:
                vp.setCurrentItem(4);
                ChangeBackGround(4);
                break;
        }
    }
    public void ChangeBackGround(int index){
        btnHome.setBackgroundResource(index ==0?R.drawable.homes:R.drawable.home);
        btnCircle.setBackgroundResource(index ==1?R.drawable.circles:R.drawable.circle);
        btnCart.setBackgroundResource(index ==2?R.drawable.cart:R.drawable.cart);
        btnOrder.setBackgroundResource(index ==3?R.drawable.orders:R.drawable.order);
        btnMy.setBackgroundResource(index ==4?R.drawable.mys:R.drawable.my);
    }
}
