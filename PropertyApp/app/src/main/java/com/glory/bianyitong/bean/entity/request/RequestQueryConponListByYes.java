package com.glory.bianyitong.bean.entity.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 * 查询可用优惠券
 */

public class RequestQueryConponListByYes implements Serializable{
    private List<Integer> shoppingCarts;//购物车
    private List<OrderDetail> listOrderDetail;//商品信息
    private CouponReceive couponReceive;//优惠实体对象

    public RequestQueryConponListByYes(CouponReceive couponReceive) {
        this.couponReceive = couponReceive;
    }

    public List<Integer> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<Integer> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public List<OrderDetail>   getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail>  listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public CouponReceive getCouponReceive() {
        return couponReceive;
    }

    public void setCouponReceive(CouponReceive couponReceive) {
        this.couponReceive = couponReceive;
    }

    public static class CouponReceive implements Serializable{
        private int couponStatus;//优惠券状态（0未使用1已使用-1已过期）

        public CouponReceive(int couponStatus) {
            this.couponStatus = couponStatus;
        }

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }
    }

    public static class  OrderDetail implements Serializable{
        private int orderID;//	int	订单ID(默认0)
        private int freshID;//	int	生鲜ID
        private int freshQuantity;//	int	数量
        private double price;//	money	单价
        private double totalPrice;//	money	商品总价
        private RequestCommitOrderByCart.OrderDetail.Fresh fresh;//生鲜信息

        public OrderDetail(RequestCommitOrderByCart.OrderDetail.Fresh fresh, int orderID, int freshID, int freshQuantity, double price, double totalPrice) {
            this.orderID = orderID;
            this.freshID = freshID;
            this.freshQuantity = freshQuantity;
            this.price = price;
            this.totalPrice = totalPrice;
            this.fresh=fresh;
        }

        public OrderDetail(int freshID, int freshQuantity, double price, double totalPrice) {
            this.freshID = freshID;
            this.freshQuantity = freshQuantity;
            this.price = price;
            this.totalPrice = totalPrice;
        }


        public RequestCommitOrderByCart.OrderDetail.Fresh getFresh() {
            return fresh;
        }

        public void setFresh(RequestCommitOrderByCart.OrderDetail.Fresh fresh) {
            this.fresh = fresh;
        }

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public int getFreshID() {
            return freshID;
        }

        public void setFreshID(int freshID) {
            this.freshID = freshID;
        }

        public int getFreshQuantity() {
            return freshQuantity;
        }

        public void setFreshQuantity(int freshQuantity) {
            this.freshQuantity = freshQuantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }


        public static class Fresh{
            private int freshTypeID;//生鲜类型ID
            private int mer_chentID;//商户ID

            public Fresh(int freshTypeID, int mer_chentID) {
                this.freshTypeID = freshTypeID;
                this.mer_chentID =mer_chentID;
            }

            public int getFreshTypeID() {
                return freshTypeID;
            }

            public void setFreshTypeID(int freshTypeID) {
                this.freshTypeID = freshTypeID;
            }

            public int getMerchentID() {
                return mer_chentID;
            }

            public void setMerchentID(int mer_chentID) {
                this.mer_chentID = mer_chentID;
            }
        }
    }
}
