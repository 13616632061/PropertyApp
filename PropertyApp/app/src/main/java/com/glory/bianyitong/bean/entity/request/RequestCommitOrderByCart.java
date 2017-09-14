package com.glory.bianyitong.bean.entity.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 * 提交订单
 */

public class RequestCommitOrderByCart implements Serializable{

    private double freight	;//money	运费
    private int addressID;//	int	地址ID
    private int cabinetID;//	int	生鲜柜ID
    private String cabinetName;//	string	生鲜柜名称
    private List<OrderDetail> listOrderDetail;
    private int couponReceiveID;//优惠券ID
    private double freePrice;//减免金额
    private double orderPrice;//订单总额

//    private List<Integer> shoppingCarts;//购物车ID集合


    private CouponReceive couponReceive;//可用优惠券查询通用




    public RequestCommitOrderByCart(double freight, int addressID, int cabinetID, String cabinetName, List<OrderDetail> listOrderDetail) {
        this.freight = freight;
        this.addressID = addressID;
        this.cabinetID = cabinetID;
        this.cabinetName = cabinetName;
        this.listOrderDetail = listOrderDetail;
    }

    public RequestCommitOrderByCart(int couponReceiveID,double freight, int addressID, int cabinetID, String cabinetName, double freePrice, double orderPrice) {
        this.freight = freight;
        this.addressID = addressID;
        this.cabinetID = cabinetID;
        this.cabinetName = cabinetName;
        this.couponReceiveID = couponReceiveID;
        this.freePrice = freePrice;
        this.orderPrice = orderPrice;
    }


    public CouponReceive getCouponReceive() {
        return couponReceive;
    }

    public void setCouponReceive(CouponReceive couponReceive) {
        this.couponReceive = couponReceive;
    }

    public int getCouponReceiveID() {
        return couponReceiveID;
    }

    public void setCouponReceiveID(int couponReceiveID) {
        this.couponReceiveID = couponReceiveID;
    }

    public double getFreePrice() {
        return freePrice;
    }

    public void setFreePrice(double freePrice) {
        this.freePrice = freePrice;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

//    public List<Integer> getShoppingCarts() {
//        return shoppingCarts;
//    }

//    public void setShoppingCarts(List<Integer> shoppingCarts) {
//        this.shoppingCarts = shoppingCarts;
//    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getCabinetID() {
        return cabinetID;
    }

    public void setCabinetID(int cabinetID) {
        this.cabinetID = cabinetID;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public static class  OrderDetail implements Serializable{
//        private int orderID;//	int	订单ID(默认0)
//        private int freshID;//	int	生鲜ID
//        private int freshQuantity;//	int	数量
//        private double price;//	money	单价
//        private double totalPrice;//	money	商品总价

//        private List<Fresh> fresh;//生鲜信息
        private int freshID;//生鲜类型ID
        private int freshQuantity;//数量
        public OrderDetail(int freshID, int freshQuantity) {
            this.freshID = freshID;
            this.freshQuantity =freshQuantity;
        }


    }
    // TODO: 2017/7/14 查询可用优惠券
    public static class CouponReceive{
        private int couponStatus=0;

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }
    }
}
