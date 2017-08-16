package com.glory.bianyitong.bean.entity.request;

/**
 * Created by billlamian on 17-8-9.
 * 查询订单列表请求
 */

public class RequestOrderOperation {

    private OrderStatus order;

    public RequestOrderOperation(OrderStatus order) {
        this.order = order;
    }

    public OrderStatus getOrder() {
        return order;
    }

    public void setOrder(OrderStatus order) {
        this.order = order;
    }

    public static class OrderStatus{
        private int orderStatus;
        private long orderID;
        public OrderStatus() {
        }

        public OrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public OrderStatus(int orderStatus, long orderID) {
            this.orderStatus = orderStatus;
            this.orderID = orderID;
        }

        public long getOrderID() {
            return orderID;
        }

        public void setOrderID(long orderID) {
            this.orderID = orderID;
        }
    }
}
