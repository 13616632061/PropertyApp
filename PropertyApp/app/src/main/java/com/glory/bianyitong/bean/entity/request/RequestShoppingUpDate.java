package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/6.
 * 更新购物车
 */

public class RequestShoppingUpDate {
    private int cartID;//": 3,
    private int quantity;//": 2

    public RequestShoppingUpDate(int cartID, int quantity) {
        this.cartID = cartID;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
