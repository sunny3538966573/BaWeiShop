package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

/**
 * date: 2019/1/6.
 * Created by Administrator
 * function:
 */
public class MyAddressPresenter extends BasePresenter {
    int page = 1;
    public MyAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
       boolean refresh = (boolean) args[0];
       if (refresh){
           page = 1;
       }else {
           page++;
       }
        return apiService.getmyAddress((long)args[1],(String) args[2]);
    }
}
