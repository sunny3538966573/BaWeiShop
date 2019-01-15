package com.bwie.actvity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.adapter.home.ViewPageDetailsBannerAdapter;
import com.bwie.utils.core.IRequest;
import com.bwie.bean.home.Details;
import com.bwie.bean.login.Login;
import com.bwie.greendao.DaoMaster;
import com.bwie.greendao.DaoSession;
import com.bwie.greendao.LoginDao;
import com.bwie.view.MyScrollView;
import com.bwie.utils.RetrofitUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetailsActivity extends AppCompatActivity{

    @BindView(R.id.details_viewpager_show)
    ViewPager detailsViewpagerShow;
    @BindView(R.id.details_textview_shownum)
    TextView detailsTextviewShownum;
    @BindView(R.id.details_textview_sprice)
    TextView detailsTextviewSprice;
    @BindView(R.id.details_textview_sold)
    TextView detailsTextviewSold;
    @BindView(R.id.details_textview_title)
    TextView detailsTextviewTitle;
    @BindView(R.id.details_textview_Weight)
    TextView detailsTextviewWeight;
    @BindView(R.id.details_Image_details)
    SimpleDraweeView detailsImageDetails;
    @BindView(R.id.details_textview_describe)
    TextView detailsTextviewDescribe;
    @BindView(R.id.details_Image_describe)
    SimpleDraweeView detailsImageDescribe;
    @BindView(R.id.details_recview_comments)
    RecyclerView detailsRecviewComments;
    @BindView(R.id.details_textview_comments)
    TextView detailsTextviewComments;
    @BindView(R.id.details_scroll_changecolor)
    MyScrollView detailsScrollChangecolor;
    @BindView(R.id.details_image_return)
    ImageView detailsImageReturn;
    @BindView(R.id.details_text_goodsT)
    TextView detailsTextGoodsT;
    @BindView(R.id.details_text_detailsT)
    TextView detailsTextDetailsT;
    @BindView(R.id.details_text_commentsT)
    TextView detailsTextCommentsT;
    @BindView(R.id.details_text_goods)
    TextView detailsTextGoods;
    @BindView(R.id.details_text_details)
    TextView detailsTextDetails;
    @BindView(R.id.details_text_comments)
    TextView detailsTextComments;
    @BindView(R.id.details_relative_changer)
    RelativeLayout detailsRelativeChanger;
    @BindView(R.id.details_relat_changecolor)
    RelativeLayout detailsRelatChangecolor;
    @BindView(R.id.btn_cart)
    ImageView btnCart;
    @BindView(R.id.details_relative_addshoppingcar)
    RelativeLayout detailsRelativeAddshoppingcar;
    @BindView(R.id.btn_buy)
    ImageView btnBuy;
    @BindView(R.id.details_relative_pay)
    RelativeLayout detailsRelativePay;
    private ViewPageDetailsBannerAdapter adapter;
    private int count;
    private LoginDao loginDao;
    private long userId;
    private String sessionId;
    private int commodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoSession daoSession = DaoMaster.newDevSession(DetailsActivity.this, LoginDao.TABLENAME);
        loginDao = daoSession.getLoginDao();
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("commodityId", 0);
        Log.i("xq",""+ commodityId);
        ButterKnife.bind(this);
        //获取数据
        initData();
        //滑动监听
        initListener();
    }



    private void initData() {
        List<Login> logins = loginDao.loadAll();
        for (int i = 0; i < logins.size(); i++) {
            userId = logins.get(i).getUserId();
            sessionId = logins.get(i).getSessionId();
        }
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        //商品详情数据
        apiService.getdetails(""+userId,sessionId,commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Details>() {
                    @Override
                    public void accept(Details details) throws Exception {
                        detailsTextviewSprice.setText("￥："+details.getResult().getPrice());
                        detailsTextviewSold.setText("已售" + details.getResult().getSaleNum() + "件");
                        detailsTextviewTitle.setText(details.getResult().getCommodityName());
                        detailsTextviewWeight.setText(details.getResult().getWeight() + "kg");
                        detailsTextviewDescribe.setText(details.getResult().getDescribe());
                        String Pictures = details.getResult().getPicture().trim();
                        String[] split = Pictures.split(",");

                        detailsImageDetails.setImageURI(split[0]);
                        detailsImageDescribe.setImageURI(split[1]);


                        adapter = new ViewPageDetailsBannerAdapter(DetailsActivity.this, split);
                        count = adapter.getCount();
                        detailsViewpagerShow.setAdapter(adapter);
                    }
                });
        //商品评论数据
//        apiService.getdetailsshop(10,1,5)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<DetailsCommentsBeans>() {
//                    @Override
//                    public void accept(DetailsCommentsBeans detailsCommentsBeans) throws Exception {
//                        if (detailsCommentsBeans !=null){
//                            detailsRecviewComments.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
//                            DetailsRecyAdapter commentsAdapter = new DetailsRecyAdapter(DetailsActivity.this,detailsCommentsBeans.getResult());
//                            detailsRecviewComments.setAdapter(commentsAdapter);
//                            if (detailsCommentsBeans.getResult().size() != 0) {
//                                detailsRecviewComments.setVisibility(View.VISIBLE);
//                                detailsTextviewComments.setVisibility(View.GONE);
//
//                                detailsRecviewComments.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
//                                DetailsRecyAdapter commentsAdapter1 = new DetailsRecyAdapter(DetailsActivity.this,detailsCommentsBeans.getResult());
//                                detailsRecviewComments.setAdapter(commentsAdapter1);
//                            } else {
//                                detailsRecviewComments.setVisibility(View.GONE);
//                                detailsTextviewComments.setVisibility(View.VISIBLE);
//                            }
//                        }
//                    }
//                });
    }
    private void initListener() {
        //商品banner的下标展示
        detailsViewpagerShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                detailsTextviewShownum.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //点击商品添加到足迹
        detailsViewpagerShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("userid", String.valueOf(userId));
                bundle.putString("sessionid",sessionId);

                Intent intent = new Intent();
                intent.putExtras(bundle);
//                intent.putExtra("hello","world");
                intent.setClass(DetailsActivity.this,FootActivity.class);
                startActivity(intent);
            }
        });
        //滑动页面变色显示标题栏
        detailsScrollChangecolor.setScrollviewListener(new MyScrollView.ScrollviewListener() {
            @Override
            public void OnScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    //标题显示
                    detailsRelativeChanger.setVisibility(View.GONE);
                    //设置标题所在的背景颜色为透明
                    detailsRelatChangecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    detailsRelativeChanger.setVisibility(View.VISIBLE);
                    //获取ScrollView想下滑动,图片消失部分的比例
                    float scale = (float) t / 200;
                    //根据比例,让标题的颜色由浅入深
                    float alpha = 0 * scale;
                    //设置标题布局的颜色
                    detailsRelatChangecolor.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));

                }
                if (0 < t && t < 700) {
                    detailsTextGoods.setBackgroundColor(Color.RED);
                    detailsTextDetails.setBackgroundColor(Color.WHITE);
                    detailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    detailsTextGoods.setBackgroundColor(Color.WHITE);
                    detailsTextDetails.setBackgroundColor(Color.RED);
                    detailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    detailsTextGoods.setBackgroundColor(Color.WHITE);
                    detailsTextDetails.setBackgroundColor(Color.WHITE);
                    detailsTextComments.setBackgroundColor(Color.RED);
                }
            }
        });


        detailsTextGoodsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsScrollChangecolor.setScrollY(0);
            }
        });
        detailsTextDetailsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsScrollChangecolor.setScrollY(702);
            }
        });
        detailsTextCommentsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsScrollChangecolor.setScrollY(1501);
            }
        });
    }

    @OnClick({R.id.btn_cart, R.id.btn_buy,R.id.details_image_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cart:
                Toast.makeText(this, "成功加入购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_buy:

                break;
            case R.id.details_image_return:
                finish();
                break;
        }
    }

}


