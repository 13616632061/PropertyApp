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


    public RequestCommitOrderByCart( double freight, int addressID, int cabinetID, String cabinetName, List<OrderDetail> listOrderDetail) {
        this.freight = freight;
        this.addressID = addressID;
        this.cabinetID = cabinetID;
        this.cabinetName = cabinetName;
        this.listOrderDetail = listOrderDetail;
    }

    public RequestCommitOrderByCart( double freight, int addressID, int cabinetID, String cabinetName) {
        this.freight = freight;
        this.addressID = addressID;
        this.cabinetID = cabinetID;
        this.cabinetName = cabinetName;
    }




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
        private int orderID;//	int	订单ID(默认0)
        private int freshID;//	int	生鲜ID
        private int freshQuantity;//	int	数量
        private double price;//	money	单价
        private double totalPrice;//	money	商品总价
        private int merchentID	;//int	商家ID

        public OrderDetail(int merchentID,int orderID, int freshID, int freshQuantity, double price, double totalPrice) {
            this.orderID = orderID;
            this.freshID = freshID;
            this.freshQuantity = freshQuantity;
            this.price = price;
            this.totalPrice = totalPrice;
            this.merchentID=merchentID;
        }

        public OrderDetail(int freshID, int freshQuantity, double price, double totalPrice) {
            this.freshID = freshID;
            this.freshQuantity = freshQuantity;
            this.price = price;
            this.totalPrice = totalPrice;
        }


        public int getMerchentID() {
            return merchentID;
        }

        public void setMerchentID(int merchentID) {
            this.merchentID = merchentID;
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
    }
}
