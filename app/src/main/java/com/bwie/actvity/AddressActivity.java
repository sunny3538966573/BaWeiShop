package com.bwie.actvity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.baweishop.R;
import com.bwie.adapter.address.MyAddressAdaper;
import com.bwie.bean.Result;
import com.bwie.bean.address.MyAddress;
import com.bwie.presenter.MyAddressPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends WDActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.btn_wancheng)
    TextView btnWancheng;
    @BindView(R.id.actvity_address_xrecy)
    XRecyclerView actvityAddressXrecy;
    @BindView(R.id.address_btn_address)
    Button addressBtnAddress;
    private MyAddressAdaper myAddressAdaper;
    private MyAddressPresenter myAddressPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        myAddressAdaper = new MyAddressAdaper(this);
        actvityAddressXrecy.setLayoutManager(new GridLayoutManager(this, 1));
        actvityAddressXrecy.setAdapter(myAddressAdaper);
        myAddressPresenter = new MyAddressPresenter(new MyAddressCall());
        actvityAddressXrecy.setLoadingListener(this);
        actvityAddressXrecy.refresh();
        myAddressAdaper.setOnItemClickListener(new MyAddressAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                myAddressAdaper.remove(position);
            }
        });
    }

    @Override
    public void onRefresh() {
        if (myAddressPresenter.isRunning()) {
            actvityAddressXrecy.refreshComplete();
            return;
        }
        myAddressPresenter.reqeust(true, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (myAddressPresenter.isRunning()) {
            actvityAddressXrecy.loadMoreComplete();
            return;
        }
        myAddressPresenter.reqeust(true, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    class MyAddressCall implements DataCall<Result<List<MyAddress>>> {

        @Override
        public void success(Result<List<MyAddress>> data) {
            actvityAddressXrecy.refreshComplete();
            actvityAddressXrecy.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                myAddressAdaper.addAll(data.getResult());
                myAddressAdaper.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            actvityAddressXrecy.refreshComplete();
            actvityAddressXrecy.loadMoreComplete();
        }
    }

    @Override
    protected void destoryData() {

    }

    @OnClick({R.id.address_btn_address, R.id.btn_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_btn_address:
                Intent intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wancheng:
                finish();
                break;
        }

    }
}
