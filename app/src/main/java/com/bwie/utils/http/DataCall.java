package com.bwie.utils.http;

import com.bwie.utils.exception.ApiException;

/**
 * date: 2019/1/4.
 * Created by Administrator
 * function:
 */
public interface DataCall<T> {
    void success(T data);
    void fail(ApiException e);
}
