package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/12.
 * 设置默认地址
 */

public class RequestInitAddress {
    private int addressID;//	int	收货地址ID
    private boolean defaults;//	Bool	是否设为默认

    public RequestInitAddress(int addressID, boolean defaults) {
        this.addressID = addressID;
        this.defaults = defaults;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public boolean isDefaults() {
        return defaults;
    }

    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }
}
