package com.bwie.fragment.benfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.adapter.receive.ReceiverAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.order.AllOrder;
import com.bwie.presenter.ReceivePresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;

import java.util.List;

import butterknife.BindView;

public class CommonFragment extends WDFragment {
    @BindView(R.id.receive_recy)
    RecyclerView receiveRecy;
    private ReceiverAdapter receiverAdapter;
    private ReceivePresenter receivePresenter;

    @Override
    public String getPageName() {
        return "待收获的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_receive;
    }

    @Override
    protected void initView() {
//设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        receiveRecy.setLayoutManager(gridLayoutManager);
        //请求网络数据
        receivePresenter = new ReceivePresenter(new ReceiveCall());
        receivePresenter.reqeust(true, LOGIN_USER.getUserId(), LOGIN_USER.getSessionId());
        //适配器
        receiverAdapter = new ReceiverAdapter(getActivity());
        receiveRecy.setAdapter(receiverAdapter);
    }

    class ReceiveCall implements DataCall<Result<List<AllOrder>>> {

        @Override
        public void success(Result<List<AllOrder>> data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                receiverAdapter.addAll(data.getOrderList());
                receiverAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(), "请求异常", Toast.LENGTH_SHORT).show();
        }
    }
}
