package com.glory.bianyitong.bean.entity;

/**
 * Created by lucy on 2017/9/22.
 */
public class CouponEntry {

    /**
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
