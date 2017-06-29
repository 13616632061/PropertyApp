package com.glory.bianyitong.bean;

import android.text.TextUtils;

import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26.
 */

public class BaseRequestBean{
    private String timeStemp;
    private String accessToken=TextUtils.isEmpty(Database.accessToken)?"0":Database.accessToken;
    private String version;
    private int deviceType=3;
    private int currentPageNumber=0;

    private int pageRowNumber=0;
    private String controllerName;
    private String actionName;
    private String userID= TextUtils.isEmpty(RequestUtil.getuserid())?"0":RequestUtil.getuserid();
    private String communityID=TextUtils.isEmpty(RequestUtil.getcommunityid()+"")?"0":RequestUtil.getcommunityid()+"";

    public String getTimeStemp() {
        timeStemp=DateUtil.format(new Date(),DateUtil.TIMESTAMP_PATTERN);
        return timeStemp;
    }

    public void setTimeStemp(String timeStemp) {
        this.timeStemp = timeStemp;
    }

    public String getAccessToken() {
        accessToken=TextUtils.isEmpty(Database.accessToken)?"0":Database.accessToken;
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageRowNumber() {
        return pageRowNumber;
    }

    public void setPageRowNumber(int pageRowNumber) {
        this.pageRowNumber = pageRowNumber;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCommunityID() {
        return communityID;
    }

    public void setCommunityID(String communityID) {
        this.communityID = communityID;
    }


    public Map<String,Object> getBaseRequest(){
        Map<String,Object> map=new HashMap<>();
        map.put("timeStemp",getTimeStemp());
        map.put("accessToken",TextUtils.isEmpty(Database.accessToken)?"0":Database.accessToken);
        map.put("version",getVersion());
        map.put("deviceType",getDeviceType());
        map.put("currentPageNumber",getCurrentPageNumber());
        map.put("pageRowNumber",getPageRowNumber());
        map.put("controllerName",getControllerName());
        map.put("actionName",getActionName());
        map.put("userID",getUserID());
        map.put("communityID",getCommunityID());


        return map;
    }
}
