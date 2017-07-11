package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/4.
 * 查询精品产品列表
 */

public class RequestProductListForGood {
    private String orderBy;
    private int merchant_ID;
    public RequestProductListForGood(String orderBy, int merchant_id) {
        this.orderBy = orderBy;
        merchant_ID = merchant_id;
    }



    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getMerchant_ID() {
        return merchant_ID;
    }

    public void setMerchant_ID(int merchant_ID) {
        this.merchant_ID = merchant_ID;
    }
}
