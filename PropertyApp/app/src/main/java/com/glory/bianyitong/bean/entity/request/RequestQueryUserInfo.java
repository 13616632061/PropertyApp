package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/3.
 * 查询用户信息
 */

public class RequestQueryUserInfo {
    private String userID;

    public RequestQueryUserInfo(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
