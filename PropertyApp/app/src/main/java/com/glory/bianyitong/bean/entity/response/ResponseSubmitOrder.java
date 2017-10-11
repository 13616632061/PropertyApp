package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;

/**
 * Created by billlamian on 17-8-9.
 * 生成订单成功
 */

public class ResponseSubmitOrder extends BaseResponseBean implements Serializable{

    /**
     * orderID : 109
     * OrderPrice : 63.0
     */

    private int orderID;
    private int parentOrderID;
    private float OrderPrice;
    private long orderCode;

    public int getParentOrderID() {
        return parentOrderID;
    }

    public void setParentOrderID(int parentOrderID) {
        this.parentOrderID = parentOrderID;
    }

    public long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(long orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(float OrderPrice) {
        this.OrderPrice = OrderPrice;
    }
}
