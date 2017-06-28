package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 查询单元
 */

public class RequestCommunityUnit {
    private int buildingID;

    public RequestCommunityUnit(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }
}
