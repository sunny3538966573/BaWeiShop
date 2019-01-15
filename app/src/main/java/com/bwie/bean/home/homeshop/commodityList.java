package com.bwie.bean.home.homeshop;

import java.util.List;

/**
 * date: 2019/1/5.
 * Created by Administrator
 * function:
 */
public class commodityList {

    private int id;
    private String name;
    private List<Commodity> commodityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}
