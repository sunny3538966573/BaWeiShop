package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

/**
 * date: 2018/12/30.
 * Created by Administrator
 * function:
 */
public class HomePresenter extends BasePresenter{

    public HomePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        return apiService.gethomes();
    }
}
