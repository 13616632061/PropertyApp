package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/4.
 * 查询产品详情
 */

public class RequestProductDetail {
    private int freshID;

    public RequestProductDetail(int freshID) {
        this.freshID = freshID;
    }

    public int getFreshID() {
        return freshID;
    }

    public void setFreshID(int freshID) {
        this.freshID = freshID;
    }
}
