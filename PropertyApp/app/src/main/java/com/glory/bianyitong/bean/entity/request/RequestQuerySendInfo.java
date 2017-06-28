package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 查询我的发布
 */

public class RequestQuerySendInfo {

    private String aesUserID;

    public RequestQuerySendInfo(String aesUserID) {
        this.aesUserID = aesUserID;
    }

    public String getAesUserID() {
        return aesUserID;
    }

    public void setAesUserID(String aesUserID) {
        this.aesUserID = aesUserID;
    }
}
