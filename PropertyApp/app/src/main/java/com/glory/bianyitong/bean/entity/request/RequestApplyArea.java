package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 申请社区
 */

public class RequestApplyArea {
    private int provinceID	;//	省ID
    private int cityID	;//	市ID
    private int communityID	;//	社区ID

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public RequestApplyArea(int provinceID, int cityID, int communityID, int buildingID, int unitID, int roomID) {
        this.provinceID = provinceID;
        this.cityID = cityID;
        this.communityID = communityID;
        this.buildingID = buildingID;
        this.unitID = unitID;
        this.roomID = roomID;

    }

    private int buildingID	;//	楼栋ID
    private int unitID	;//	单元ID
    private int roomID;//
}
