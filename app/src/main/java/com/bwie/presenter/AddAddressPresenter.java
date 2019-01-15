package com.bwie.presenter;

import com.bwie.utils.core.IRequest;
import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

/**
 *
 */
public class AddAddressPresenter extends BasePresenter {

    public AddAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest apiService = RetrofitUtils.getInstance().create(IRequest.class);
        return apiService.getaddress((long)args[1],(String)args[2],"realName","phone","address","zipCode");

    }
}
