package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/5.
 * 添加购物车
 */

public class RequestShoppingCartAdd {
    private int freshID	;//int	生鲜ID
    private int freshTypeID	;//int	生鲜类型ID
    private int quantity	;//int	数量
    private float price	;//money	单价

    public RequestShoppingCartAdd(int freshID, int freshTypeID, int quantity, float price) {
        this.freshID = freshID;
        this.freshTypeID = freshTypeID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getFreshID() {
        return freshID;
    }

    public void setFreshID(int freshID) {
        this.freshID = freshID;
    }

    public int getFreshTypeID() {
        return freshTypeID;
    }

    public void setFreshTypeID(int freshTypeID) {
        this.freshTypeID = freshTypeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
