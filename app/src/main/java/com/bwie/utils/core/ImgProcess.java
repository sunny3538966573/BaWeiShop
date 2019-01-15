package com.bwie.utils.core;

/**
 * date: 2019/1/3.
 * Created by Administrator
 * function:
 */
public class ImgProcess {
    public static String getImg(String images) {
        String[] a = images.split("\\|");
        return a[0];
    }
    public static String setImg(String images) {
        String a = images.replace("https","http");
        return a;
    }
}
