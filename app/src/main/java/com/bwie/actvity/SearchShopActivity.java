package com.bwie.actvity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.baweishop.R;
import com.bwie.adapter.home.Query_Goods_Adapter;
import com.bwie.utils.core.IRequest;
import com.bwie.bean.home.Querys;
import com.bwie.utils.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 搜素页
 */
public class SearchShopActivity extends AppCompatActivity {


    private ImageView query_goods_image;
    private EditText query_goods_et;
    private ImageView query_goods_search;
    private XRecyclerView query_goods__xrecy;
    private String name;
    int page = 1;
    private Query_Goods_Adapter query_goods_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_goods);
        initView();
        initData();
        //刷新加载
        resreshDate();

        initListener();

        //接口回调
        initinterface();
    }




    private void initData() {
        query_goods_et.setText(name);
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        apiService.getquery(name,""+page,"5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Querys>() {
                    @Override
                    public void accept(Querys query) throws Exception {
                        if (query.getStatus().equals("0000")){
                        }
                        List<Querys.ResultBean> result = query.getResult();
                        query_goods_adapter = new Query_Goods_Adapter(SearchShopActivity.this,result);
                        //布局管理器
                        query_goods__xrecy.setLayoutManager(new GridLayoutManager(SearchShopActivity.this, 2));
                        //绑定构造器
                        query_goods__xrecy.setAdapter(query_goods_adapter);
                    }
                });


    }
    private void resreshDate() {
        query_goods__xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;//起初page为1
                        initData();//加载数据
                        query_goods__xrecy.refreshComplete();//提示刷新完成
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                page++;//起初page为1
                initData();//加载数据
                query_goods__xrecy.refreshComplete();//提示刷新完成
            }
        });
    }

    private void initView() {
        //获取传递过来的值
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.i("aaaaaa",name);
        query_goods_image = (ImageView) findViewById(R.id.query_goods_image);
        query_goods_et = (EditText) findViewById(R.id.query_goods_et);
        query_goods_search = (ImageView) findViewById(R.id.query_goods_search);
        query_goods__xrecy = (XRecyclerView) findViewById(R.id.query_goods__xrecy);
    }

    private void initListener() {

        query_goods_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        query_goods_search.setOnClickListener(new View.OnClickListener() {

            private String s;

            @Override
            public void onClick(View v) {
                s = query_goods_et.getText().toString();
                IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
                apiService.getquery(s,""+page,"5")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Querys>() {
                            @Override
                            public void accept(final Querys query) throws Exception {
                                if (query !=null){
                                    final List<Querys.ResultBean> result = query.getResult();
                                    query_goods_adapter = new Query_Goods_Adapter(SearchShopActivity.this,result);
                                    //布局管理器
                                    query_goods__xrecy.setLayoutManager(new GridLayoutManager(SearchShopActivity.this, 2));
                                    //绑定构造器
                                    query_goods__xrecy.setAdapter(query_goods_adapter);
                                }

                            }
                        });
            }
        });
    }
    private void initinterface() {

    }
}
