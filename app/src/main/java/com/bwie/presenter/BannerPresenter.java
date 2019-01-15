package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

/**
 * date: 2019/1/5.
 * Created by Administrator
 * function:
 */
public class BannerPresenter extends BasePresenter{

    public BannerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        return apiService.getbanners();
    }
}
