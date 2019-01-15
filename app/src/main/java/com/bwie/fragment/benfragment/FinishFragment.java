package com.bwie.fragment.benfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.baweishop.R;
import com.bwie.bean.Result;
import com.bwie.bean.order.AllOrder;
import com.bwie.presenter.FinishPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;

import java.util.List;

import butterknife.BindView;

public class FinishFragment extends WDFragment {
    @BindView(R.id.finish_recy)
    RecyclerView finishRecy;
    private FinishPresenter finishPresenter;

    @Override
    public String getPageName() {
        return "已完成";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_accomplish;
    }

    @Override
    protected void initView() {
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        finishRecy.setLayoutManager(gridLayoutManager);
        //请求网络数据
        finishPresenter = new FinishPresenter(new FinishCall());
        finishPresenter.reqeust(true,LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
        //设置适配器

    }

    class FinishCall implements DataCall<Result<List<AllOrder>>>{

        @Override
        public void success(Result<List<AllOrder>> data) {

        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
