package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/12.
 * 删除收货地址
 */

public class RequestDeleteAddress {
    private int addressID;

    public RequestDeleteAddress(int addressID) {
        this.addressID = addressID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
}
