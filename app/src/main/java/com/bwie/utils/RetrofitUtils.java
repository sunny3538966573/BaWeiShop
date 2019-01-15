package com.bwie.utils;

import com.bwie.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date: 2018/12/30.
 * Created by Administrator
 * function:
 */
public class RetrofitUtils {
    private Retrofit retrofit;

    //单例模式
    private static final class SINGLE_INSTANCE {
        public static final RetrofitUtils _INSTANCE = new RetrofitUtils();
    }

    //方法请求
    public static RetrofitUtils getInstance() {
        return SINGLE_INSTANCE._INSTANCE;
    }

    private RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.HOME_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkhttpClient())
                .build();
    }

    //设置读写超时
    private OkHttpClient buildOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数，请求结果
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
