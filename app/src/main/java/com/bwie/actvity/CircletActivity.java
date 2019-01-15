package com.bwie.actvity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.bwie.baweishop.R;
import com.bwie.adapter.circle.MyCircle_adapter;
import com.bwie.bean.Result;
import com.bwie.bean.circle.MyCircle;
import com.bwie.presenter.MyCirclePresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircletActivity extends WDActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.actvity_mycircle_recy)
    XRecyclerView actvityMycircleRecy;
    private MyCircle_adapter myCircle_adapter;
    private MyCirclePresenter myCirclePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle;
    }

    @Override
    protected void initView() {
        myCircle_adapter = new MyCircle_adapter(this);
       actvityMycircleRecy.setLayoutManager(new GridLayoutManager(this,1));
//        int space = getResources().getDimensionPixelSize(R.dimen.dp_20);
//        actvityMycircleRecy.addItemDecoration(new SpacingItemDecoration(space));
        actvityMycircleRecy.setAdapter(myCircle_adapter);
        actvityMycircleRecy.setLoadingListener(this);

        //设置数据
        myCirclePresenter = new MyCirclePresenter(new myCircleCall());
        actvityMycircleRecy.refresh();
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {
        if (myCirclePresenter.isRunning()){
            actvityMycircleRecy.refreshComplete();
            return;
        }
        myCirclePresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (myCirclePresenter.isRunning()){//如果正在运行
            actvityMycircleRecy.loadMoreComplete();
            return;
        }
        myCirclePresenter.reqeust(false,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }
    class myCircleCall implements DataCall<Result<List<MyCircle>>>{

        @Override
        public void success(Result<List<MyCircle>> data) {
            actvityMycircleRecy.refreshComplete();
            actvityMycircleRecy.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                Log.i("ssss",data.toString());
               myCircle_adapter.addAll(data.getResult());
                myCircle_adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            actvityMycircleRecy.refreshComplete();
            actvityMycircleRecy.loadMoreComplete();
        }
    }
}
