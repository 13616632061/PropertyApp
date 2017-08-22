package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/4.
 * 查询产品列表
 */

public class RequestProductList {
    private int freshTypeID;
    private String orderBy;
    private int freshLeafID;
    public RequestProductList(int freshTypeID, String orderBy, int freshLeafID) {
        this.freshTypeID = freshTypeID;
        this.orderBy = orderBy;
        this.freshLeafID = freshLeafID;
    }

    public int getFreshTypeID() {
        return freshTypeID;
    }

    public void setFreshTypeID(int freshTypeID) {
        this.freshTypeID = freshTypeID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getMerchant_ID() {
        return freshLeafID;
    }

    public void setMerchant_ID(int merchant_ID) {
        this.freshLeafID = freshLeafID;
    }
}
