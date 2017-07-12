package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/12.
 * 编辑收货地址
 */

public class RequestEditAddress {
    private int addressID;//	int	收货地址ID
    private String harvesterName;//	string	收货人姓名
    private String harvestePhone;//	string	收货人联系方式

    public RequestEditAddress(int addressID, String harvesterName, String harvestePhone) {
        this.addressID = addressID;
        this.harvesterName = harvesterName;
        this.harvestePhone = harvestePhone;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getHarvesterName() {
        return harvesterName;
    }

    public void setHarvesterName(String harvesterName) {
        this.harvesterName = harvesterName;
    }

    public String getHarvestePhone() {
        return harvestePhone;
    }

    public void setHarvestePhone(String harvestePhone) {
        this.harvestePhone = harvestePhone;
    }
}
