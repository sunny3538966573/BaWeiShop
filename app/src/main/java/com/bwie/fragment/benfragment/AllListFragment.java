package com.bwie.fragment.benfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.adapter.list.AllListAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.order.AllOrder;
import com.bwie.presenter.AllOrderPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;

import java.util.List;

import butterknife.BindView;

public class AllListFragment extends WDFragment{
    @BindView(R.id.allorderfragemnt_item_RecyclerView)
    RecyclerView allorderfragemntItemRecyclerView;
    private AllListAdapter allOrderAdapter;
    private AllOrderPresenter allOrderPresenter;
    @Override
    public String getPageName() {
        return "全部订单的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_list;
    }

    @Override
    protected void initView() {
        //新建xrecyclerview的布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        allorderfragemntItemRecyclerView.setLayoutManager(gridLayoutManager);
        //请求网络数据
        allOrderPresenter = new AllOrderPresenter(new AllorderCall());
        allOrderPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
        //allorderfragemntItemRecyclerView.request();
        //创建适配器
        allOrderAdapter = new AllListAdapter(getActivity());
        //设置适配器
        allorderfragemntItemRecyclerView.setAdapter(allOrderAdapter);
        //给recyclerview设置加载监听事件
    }

    class AllorderCall implements DataCall<Result<List<AllOrder>>>{
        @Override
        public void success(Result<List<AllOrder>> data) {
            if (data.getStatus().equals("0000")){
                //Toast.makeText(getActivity(), "成功获取订单", Toast.LENGTH_SHORT).show();
                allOrderAdapter.AddAll(data.getOrderList());
                allOrderAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
        }
    }

}
