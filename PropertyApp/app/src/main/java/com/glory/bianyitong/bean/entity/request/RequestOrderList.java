package com.glory.bianyitong.bean.entity.request;

/**
 * Created by billlamian on 17-8-9.
 * 查询订单列表请求
 */

public class RequestOrderList {

    private OrderStatus order;

    public RequestOrderList(OrderStatus order) {
        this.order = order;
    }

    public RequestOrderList() {
    }

    public OrderStatus getOrder() {
        return order;
    }

    public void setOrder(OrderStatus order) {
        this.order = order;
    }

    public static class OrderStatus{
        private int orderStatus;

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
    }
}
