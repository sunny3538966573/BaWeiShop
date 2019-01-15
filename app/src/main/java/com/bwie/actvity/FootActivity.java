package com.bwie.actvity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.bwie.baweishop.R;
import com.bwie.adapter.foot.FootAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.foot.Foot;
import com.bwie.presenter.FootPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootActivity extends WDActivity implements XRecyclerView.LoadingListener {


    @BindView(R.id.foot_xrv_list)
    XRecyclerView footXrvList;
    private FootAdapter footAdapter;
    private FootPresenter footPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foot;
    }

    @Override
    protected void initView() {
        footAdapter = new FootAdapter(this);

        footXrvList.setLayoutManager(new GridLayoutManager(this,2));

        footXrvList.setAdapter(footAdapter);
        footXrvList.setLoadingListener(this);

        footPresenter = new FootPresenter(new FootCall());

        footXrvList.refresh();

    }

    @Override
    protected void destoryData() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    class FootCall implements DataCall<Result<List<Foot>>> {

        @Override
        public void success(Result<List<Foot>> data) {
            footXrvList.refreshComplete();
            footXrvList.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                footAdapter.addAll(data.getResult());

                footAdapter.notifyDataSetChanged();

            }
        }

        @Override
        public void fail(ApiException e) {
            footXrvList.refreshComplete();
            footXrvList.loadMoreComplete();
        }
    }
    @Override
    public void onRefresh() {
        if (footPresenter.isRunning()){
            footXrvList.refreshComplete();
            return;
        }
        footPresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (footPresenter.isRunning()){
            footXrvList.loadMoreComplete();
            return;
        }
        footPresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }
}
