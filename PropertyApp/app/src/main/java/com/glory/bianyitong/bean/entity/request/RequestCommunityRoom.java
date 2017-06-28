package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 查询房间号
 */

public class RequestCommunityRoom {
    private int unitID;

    public RequestCommunityRoom(int unitID) {
        this.unitID = unitID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
