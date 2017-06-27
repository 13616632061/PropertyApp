package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/5/9.
 *  ResponseBaseInfo 包头
 */
public class ResponseBaseInfo {

    /**
     * version : “版本号”
     * timeStemp : “2014-4-3”
     * accessToken : “sdf55465898ds”
     * statusCode : 1
     * statusMessage : “操作成功”
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    protected String version;    //版本号
    protected String timeStemp;  //时间戳
    protected String accessToken;//账户令牌
    protected int statusCode;    //状态码
    protected String statusMessage; //状态说明
    protected String alertMessage;  //提示信息
    protected int currentPageNumber;//当前页
    protected int pageRowNumber;    //每页记录数

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimeStemp() {
        return timeStemp;
    }

    public void setTimeStemp(String timeStemp) {
        this.timeStemp = timeStemp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
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
}
