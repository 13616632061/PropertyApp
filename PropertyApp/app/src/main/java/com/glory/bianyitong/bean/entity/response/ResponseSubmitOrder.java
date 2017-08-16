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
    private float OrderPrice;

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
