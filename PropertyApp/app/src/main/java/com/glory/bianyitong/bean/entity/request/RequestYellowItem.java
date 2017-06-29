package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/29.
 * 根据黄页查询数据列表
 */

public class RequestYellowItem {
    private int yellowPageGroupID;

    public RequestYellowItem(int yellowPageGroupID) {
        this.yellowPageGroupID = yellowPageGroupID;
    }

    public int getYellowPageGroupID() {
        return yellowPageGroupID;
    }

    public void setYellowPageGroupID(int yellowPageGroupID) {
        this.yellowPageGroupID = yellowPageGroupID;
    }
}
