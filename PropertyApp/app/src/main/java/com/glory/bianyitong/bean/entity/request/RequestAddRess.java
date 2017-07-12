package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/12.
 * 新增收货地址
 */

public class RequestAddRess {
    private int cabinetID;//	int	生鲜柜ID
    private String harvesterName;//	string	收货人姓名
    private String harvestePhone;//	string	收货人联系方式

    public RequestAddRess(int cabinetID, String harvesterName, String harvestePhone) {
        this.cabinetID = cabinetID;
        this.harvesterName = harvesterName;
        this.harvestePhone = harvestePhone;
    }

    public int getCabinetID() {
        return cabinetID;
    }

    public void setCabinetID(int cabinetID) {
        this.cabinetID = cabinetID;
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
