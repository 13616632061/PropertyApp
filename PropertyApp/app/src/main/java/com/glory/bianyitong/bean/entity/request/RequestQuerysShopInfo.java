package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/7.
 * 商户信息查询
 */

public class RequestQuerysShopInfo {
    private int area_ID;//区域ID

    public RequestQuerysShopInfo(int area_ID) {
        this.area_ID = area_ID;
    }

    public int getArea_ID() {
        return area_ID;
    }

    public void setArea_ID(int area_ID) {
        this.area_ID = area_ID;
    }
}
