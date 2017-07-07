package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/4.
 * 查询生鲜类型
 */

public class RequestFreshType {
    private int freshTypeLeaf	;//int	生鲜类型递归ID
    private int merchant_ID;//	int	商户ID

    public RequestFreshType(int freshTypeLeaf, int merchant_ID) {
        this.freshTypeLeaf = freshTypeLeaf;
        this.merchant_ID = merchant_ID;
    }

    public RequestFreshType(int merchant_ID) {
        this.merchant_ID = merchant_ID;
    }

    public int getFreshTypeLeaf() {
        return freshTypeLeaf;
    }

    public void setFreshTypeLeaf(int freshTypeLeaf) {
        this.freshTypeLeaf = freshTypeLeaf;
    }

    public int getMerchant_ID() {
        return merchant_ID;
    }

    public void setMerchant_ID(int merchant_ID) {
        this.merchant_ID = merchant_ID;
    }
}
