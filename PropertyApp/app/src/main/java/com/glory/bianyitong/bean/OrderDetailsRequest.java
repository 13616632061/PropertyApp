package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/9/21.
 */
public class OrderDetailsRequest {

    /**
     * order : {"orderID":148}
     */

    private OrderBean order=new OrderBean();
    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * orderID : 148
         */

        private int orderID;

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }
    }
}
