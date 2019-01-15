package com.bwie.presenter;

import com.bwie.utils.RetrofitUtils;
import com.bwie.utils.core.IRequest;
import com.bwie.utils.http.DataCall;

import io.reactivex.Observable;

/**
 * Created by 1607c王晴
 * date 2019/1/10
 * Describe:请求网络
 */

public class ReceivePresenter extends BasePresenter{
    private int page=1;
    public ReceivePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);
        return iRequest.getallorder((long)args[1],(String) args[2],2,page,5);
    }
}
