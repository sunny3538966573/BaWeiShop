package com.bwie.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.baweishop.R;
import com.bwie.actvity.PostActivity;
import com.bwie.adapter.circle.Fragment_Circle_Adapter;
import com.bwie.bean.Result;
import com.bwie.bean.circle.Circle;
import com.bwie.presenter.CirclePresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1607c王晴
 * date 2019/1/10
 * Describe:圈子
 */
public class UfoFragment extends WDFragment implements XRecyclerView.LoadingListener {

    @BindView(R.id.fragment_circle_recy)
    XRecyclerView fragmentCircleRecy;
    @BindView(R.id.ufo_post)
    ImageView ufoPost;
    private Fragment_Circle_Adapter fragment_circle_adapter;
    private CirclePresenter circlePresenter;

    @Override
    public String getPageName() {
        return "圈子的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ufo;
    }

    @Override
    protected void initView() {
        fragment_circle_adapter = new Fragment_Circle_Adapter(getActivity());
        fragmentCircleRecy.setLayoutManager(new GridLayoutManager(getActivity(),1));

        fragmentCircleRecy.setAdapter(fragment_circle_adapter);
        fragmentCircleRecy.setLoadingListener(this);

        //设置数据
        circlePresenter = new CirclePresenter(new CircleCall());
        fragmentCircleRecy.refresh();
    }
    @Override
    public void onRefresh() {
        if (circlePresenter.isRunning()){
            fragmentCircleRecy.refreshComplete();
            return;
        }
        circlePresenter.reqeust(true,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (circlePresenter.isRunning()){//如果正在运行
            fragmentCircleRecy.loadMoreComplete();
            return;
        }
        circlePresenter.reqeust(false,LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @OnClick(R.id.ufo_post)
    public void ufopost(){
        Intent intent = new Intent(getActivity(), PostActivity.class);
        startActivity(intent);
    }
    class CircleCall implements DataCall<Result<List<Circle>>>{

        @Override
        public void success(Result<List<Circle>> data) {
            fragmentCircleRecy.refreshComplete();
            fragmentCircleRecy.loadMoreComplete();
            if (data.getStatus().equals("0000")){
                Log.i("ssss",data.toString());
                fragment_circle_adapter.addAll(data.getResult());
                fragment_circle_adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            fragmentCircleRecy.refreshComplete();
            fragmentCircleRecy.loadMoreComplete();

        }
    }
}
