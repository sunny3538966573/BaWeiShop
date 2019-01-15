package com.bwie.fragment.benfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.bwie.baweishop.R;
import com.bwie.adapter.common.CommonAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.order.AllOrder;
import com.bwie.presenter.CommonPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;
import java.util.List;
import butterknife.BindView;


public class ReciveFragment extends WDFragment {
    @BindView(R.id.common_recy)
    RecyclerView commonRecy;
    private CommonAdapter commonAdapter;
   private CommonPresenter commonPresenter;

    @Override
    public String getPageName() {
        return "待评价的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView() {
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        commonRecy.setLayoutManager(gridLayoutManager);
        //请求网络数据
        commonPresenter = new CommonPresenter(new CommonCall());
        commonPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
        //适配器
        commonAdapter = new CommonAdapter(getActivity());
        commonRecy.setAdapter(commonAdapter);
    }

    class CommonCall implements DataCall<Result<List<AllOrder>>>{

        @Override
        public void success(Result<List<AllOrder>> data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                commonAdapter.addAll(data.getOrderList());
                commonAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(), "请求异常", Toast.LENGTH_SHORT).show();
        }
    }
}
