package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/11.
 * 查询生鲜商家行政区
 */

public class RequestQueryFreshLocal {
    private String area_Name;

    public RequestQueryFreshLocal(String area_Name) {
        this.area_Name = area_Name;
    }

    public String getArea_Name() {
        return area_Name;
    }

    public void setArea_Name(String area_Name) {
        this.area_Name = area_Name;
    }
}
