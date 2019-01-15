package com.bwie.bean.home.homeshop;

import java.util.List;

/**
 * date: 2019/1/5.
 * Created by Administrator
 * function:
 */
public class HomeList {

    private List<commodityList> rxxp;
    private List<commodityList> pzsh;
    private List<commodityList> mlss;

    public List<commodityList> getRxxp() {
        return rxxp;
    }

    public void setRxxp(List<commodityList> rxxp) {
        this.rxxp = rxxp;
    }

    public List<commodityList> getPzsh() {
        return pzsh;
    }

    public void setPzsh(List<commodityList> pzsh) {
        this.pzsh = pzsh;
    }

    public List<commodityList> getMlss() {
        return mlss;
    }

    public void setMlss(List<commodityList> mlss) {
        this.mlss = mlss;
    }
}
