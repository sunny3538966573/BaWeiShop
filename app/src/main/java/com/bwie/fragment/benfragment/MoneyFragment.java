package com.bwie.fragment.benfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.adapter.money.MoneyAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.order.AllOrder;
import com.bwie.presenter.MoneyPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;

import java.util.List;

import butterknife.BindView;

public class MoneyFragment extends WDFragment {
    @BindView(R.id.awaitfragemnt_item_RecyclerView)
    RecyclerView recy;
    private MoneyAdapter moneyAdapter;

    /**
     * 得到包名
     *
     * @return
     */
    @Override
    public String getPageName() {
        return "带付款的fragment";
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money;
    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        //适配器
        moneyAdapter = new MoneyAdapter(getActivity());
        recy.setAdapter(moneyAdapter);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(linearLayoutManager);
        //请求网络
        MoneyPresenter moneyPresenter = new MoneyPresenter(new MoneyCall());
        moneyPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());


    }

    class MoneyCall implements DataCall<Result<List<AllOrder>>> {

        @Override
        public void success(Result<List<AllOrder>> data) {
            if (data.getStatus().equals("0000")){
                moneyAdapter.addAll(data.getOrderList());
                moneyAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(),"请求异常",Toast.LENGTH_LONG).show();
        }
    }

}
