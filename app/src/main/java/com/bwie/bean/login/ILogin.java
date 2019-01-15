package com.bwie.bean.login;

/**
 * date: 2018/12/29.
 * Created by Administrator
 * function:
 */
public class ILogin<T> {
    private String message;
    private String status;
    private T result;
    private String headPath;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
