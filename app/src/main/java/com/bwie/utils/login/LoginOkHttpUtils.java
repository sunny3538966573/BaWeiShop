package com.bwie.utils.login;

import com.bwie.config.HttpConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date: 2018/12/29.
 * Created by Administrator
 * function:
 */
public class LoginOkHttpUtils {
    private static LoginOkHttpUtils instance;
    private Retrofit retrofit;
    public LoginOkHttpUtils(){
        init();
    }
    public static LoginOkHttpUtils getInstance(){
        if (instance ==null){
            instance = new LoginOkHttpUtils();
        }
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HttpConfig.LOGIN_URL)
                .client(client)
                .build();
    }
    //把接口的注解翻译为OKhttp请求
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
