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
    private double OrderPrice;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(double OrderPrice) {
        this.OrderPrice = OrderPrice;
    }
}
