package com.bwie.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.adapter.home.Fragment_Home_Adapter;
import com.bwie.bean.Result;
import com.bwie.bean.home.Banners;
import com.bwie.bean.home.homeshop.HomeList;
import com.bwie.actvity.DetailsActivity;
import com.bwie.actvity.SearchShopActivity;
import com.bwie.presenter.BannerPresenter;
import com.bwie.presenter.HomePresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ListFragment extends WDFragment {

    @BindView(R.id.fragment_home_dialog)
    ImageView fragmentHomeDialog;
    //@BindView(R.id.fragment_home_search)
    //EditText fragmentHomeSearch;
    @BindView(R.id.fragment_home_sou)
    ImageView fragmentHomeSou;
    @BindView(R.id.fragment_home_banner)
    MZBannerView fragmentHomeBanner;
    @BindView(R.id.fragment_home_rxxp_d)
    ImageView fragmentHomeRxxpD;
    @BindView(R.id.fragment_home_xxxp_recy)
    RecyclerView fragmentHomeRxxpRecy;
    @BindView(R.id.fragment_home_mlss_d)
    ImageView fragmentHomeMlssD;
    @BindView(R.id.fragment_home_mlss_recy)
    RecyclerView fragmentHomeMlssRecy;
    @BindView(R.id.fragment_home_pzsh_d)
    ImageView fragmentHomePzshD;
    @BindView(R.id.fragment_home_pzsh_recy)
    RecyclerView fragmentHomePzshRecy;
    private Fragment_Home_Adapter fragmentHomeHotAdapter;
    private Fragment_Home_Adapter fragmentHomeFashionAdapter;
    private Fragment_Home_Adapter fragmentHomeLiveAdapter;
    private RadioGroup popupwindowItemClass;
    private RadioGroup popupwindowItemTitle;
    private BannerPresenter bannerPresenter;
    private HomePresenter homePresenter;
   // private List<String> bannersList = new ArrayList<>();

    @Override
    public String getPageName() {
        return "首页的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        //获取首页适配器
        fragmentHomeHotAdapter = new Fragment_Home_Adapter(getContext(),Fragment_Home_Adapter.HOT_TYPE);
        fragmentHomeFashionAdapter = new Fragment_Home_Adapter(getContext(),Fragment_Home_Adapter.FASHION_TYPE);
        fragmentHomeLiveAdapter = new Fragment_Home_Adapter(getContext(),Fragment_Home_Adapter.LIVE_TYPE);

        //给数据设置布局管理器
        fragmentHomeRxxpRecy.setLayoutManager(new GridLayoutManager(getContext(),3));
        fragmentHomeMlssRecy.setLayoutManager(new GridLayoutManager(getContext(),1));
        fragmentHomePzshRecy.setLayoutManager(new GridLayoutManager(getContext(),2));

        fragmentHomeRxxpRecy.setAdapter(fragmentHomeHotAdapter);
        fragmentHomeMlssRecy.setAdapter(fragmentHomeFashionAdapter);
        fragmentHomePzshRecy.setAdapter(fragmentHomeLiveAdapter);

        //创建presenter类
        bannerPresenter = new BannerPresenter(new MyBanner());
        bannerPresenter.reqeust();

        homePresenter = new HomePresenter(new HomeCall());
        homePresenter.reqeust();

        fragmentHomeHotAdapter.setOnitemClickListener(new Fragment_Home_Adapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", position);
                startActivity(intent);
            }
        });
        fragmentHomeFashionAdapter.setOnitemClickListener(new Fragment_Home_Adapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", position);
                startActivity(intent);
            }
        });
        fragmentHomeLiveAdapter.setOnitemClickListener(new Fragment_Home_Adapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("commodityId", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentHomeBanner.pause();
    }
    //轮播图
    class BannerViewHolder implements MZViewHolder<Banners>{

        private SimpleDraweeView bannerImage;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_banner,null);
            bannerImage = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, Banners banners) {
            bannerImage.setImageURI(Uri.parse(banners.getImageUrl()));
        }
    }

    class MyBanner implements DataCall<Result<List<Banners>>>{

        @Override
        public void success(Result<List<Banners>> data) {
            if (data.getStatus().equals("0000")){
                fragmentHomeBanner.setIndicatorVisible(false);
//                List<Banners> result = data.getResult();
//                for (int i = 0; i < result.size(); i++) {
//                    String imageUrl = result.get(i).getImageUrl();
//                    bannersList.add(imageUrl);
//                }
                fragmentHomeBanner.setPages(data.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                fragmentHomeBanner.start();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }



    //首页数据
    class HomeCall implements DataCall<Result<HomeList>>{

        @Override
        public void success(Result<HomeList> data) {
            if (data.getStatus().equals("0000")){
                //添加列表刷新
                fragmentHomeHotAdapter.addAll(data.getResult().getRxxp().get(0).getCommodityList());
                fragmentHomeFashionAdapter.addAll(data.getResult().getMlss().get(0).getCommodityList());
                fragmentHomeLiveAdapter.addAll(data.getResult().getPzsh().get(0).getCommodityList());
                fragmentHomeHotAdapter.notifyDataSetChanged();
                fragmentHomeFashionAdapter.notifyDataSetChanged();
                fragmentHomeLiveAdapter.notifyDataSetChanged();

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
   // @OnClick({R.id.fragment_home_dialog, R.id.fragment_home_sou})
    @OnClick({R.id.fragment_home_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_dialog:
                final View view1 = View.inflate(getActivity(), R.layout.popwindow_item, null);
                final PopupWindow Window = new PopupWindow(view1, 1000, 250, true);
//                Window.showAtLocation(view1,0,0,250);
                Window.setBackgroundDrawable(new ColorDrawable());
                Window.setOutsideTouchable(true);
                Window.setTouchable(true);
                Window.showAsDropDown(fragmentHomeDialog);
                popupwindowItemClass = view1.findViewById(R.id.popupwindow_item_class);
                popupwindowItemTitle = view1.findViewById(R.id.popupwindow_item_title);
                popupwindowItemClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = view1.findViewById(popupwindowItemClass.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), SearchShopActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                        Window.dismiss();
                    }
                });
                popupwindowItemTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = view1.findViewById(popupwindowItemTitle.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), SearchShopActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                        Window.dismiss();
                    }
                });
                break;
       /*     case R.id.fragment_home_sou:
                String name = fragmentHomeSearch.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "输入内容不可以为空", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), SearchShopActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }

                break;*/
        }
    }
}
