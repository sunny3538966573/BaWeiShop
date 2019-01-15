package com.bwie.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.actvity.PayActivity;
import com.bwie.baweishop.R;
import com.bwie.adapter.car.CarAdapter;
import com.bwie.bean.Result;
import com.bwie.bean.cart.Cart;
import com.bwie.presenter.CartPresenter;
import com.bwie.utils.exception.ApiException;
import com.bwie.utils.http.DataCall;
import com.bwie.utils.http.WDFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CarFragment extends WDFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.fragment_cart_recy)
    XRecyclerView fragmentCartRecy;
    @BindView(R.id.all_cb)
    CheckBox allCb;
    @BindView(R.id.fragment_cart_txt_numprice)
    TextView fragmentCartTxtNumprice;
    @BindView(R.id.btn_buy)
    TextView btnBuy;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    private CarAdapter carAdapter;
    private CartPresenter cartPresenter;

    @Override
    public String getPageName() {
        return "购物车的fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    protected void initView() {
        //添加适配器
        carAdapter = new CarAdapter(getActivity());

        //布局管理器
        fragmentCartRecy.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        fragmentCartRecy.setAdapter(carAdapter);

        fragmentCartRecy.setLoadingListener(this);
        //请求数据
        cartPresenter = new CartPresenter(new CartCall());
        fragmentCartRecy.refresh();
        carAdapter.setOnCartListener(new CarAdapter.OnCartListener() {
            @Override
            public void GoodsChange() {
                boolean allshopselected = carAdapter.isAllshopselected();
                allCb.setChecked(allshopselected);
                /**
                 * 刷新适配器
                 */
                carAdapter.notifyDataSetChanged();

                changeprice();
            }


            @Override
            public void onCartNumchanged(int index, int num) {
                carAdapter.changeshopnum(index, num);
                changeprice();
            }
        });

    }

    @Override
    public void onRefresh() {
        if (cartPresenter.isRunning()) {
            fragmentCartRecy.refreshComplete();
            return;
        }
        cartPresenter.reqeust(true, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onLoadMore() {
        if (cartPresenter.isRunning()) {//如果正在运行
            fragmentCartRecy.loadMoreComplete();
            return;
        }
        cartPresenter.reqeust(false, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    class CartCall implements DataCall<Result<List<Cart>>> {

        @Override
        public void success(Result<List<Cart>> data) {
            fragmentCartRecy.refreshComplete();
            fragmentCartRecy.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                carAdapter.addAll(data.getResult());
                carAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            fragmentCartRecy.refreshComplete();
            fragmentCartRecy.loadMoreComplete();
        }
    }

    @OnClick({R.id.all_cb, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_cb:
                boolean checked = allCb.isChecked();
                carAdapter.setAllshopselected(checked);
                carAdapter.notifyDataSetChanged();
                changeprice();
                break;
            case R.id.btn_buy://去结算
                Intent intent = new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 计算总价
     */
    private void changeprice() {
        float setsumprice = carAdapter.setsumprice();
        fragmentCartTxtNumprice.setText("合计:¥" + setsumprice);
    }
}