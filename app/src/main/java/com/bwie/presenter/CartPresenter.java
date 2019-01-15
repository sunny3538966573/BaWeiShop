package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;


public class CartPresenter extends BasePresenter {
    int page = 1;
    public CartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        boolean refresh = (boolean)args[0];
        if (refresh){
            page = 1;
        }else{
            page++;
        }
        return apiService.getcart((long) args[1],(String) args[2]);
    }
}
