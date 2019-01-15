package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

public class AllOrderPresenter extends BasePresenter {
    int page = 1;
    public AllOrderPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        return apiService.getallorder((long)args[1],(String) args[2],0,page,5);
    }
}
