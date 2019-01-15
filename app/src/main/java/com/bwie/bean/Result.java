package com.bwie.bean;

/**
 * date: 2019/1/4.
 * Created by Administrator
 * function:
 */
public class Result<T> {
    private String status;
    private String message;
    private T result;
    private T orderList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }
}
