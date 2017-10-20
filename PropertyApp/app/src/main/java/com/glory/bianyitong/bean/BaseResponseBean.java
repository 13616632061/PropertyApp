package com.glory.bianyitong.bean;

/**
 * Created by Administrator on 2017/6/27.
 */

public class BaseResponseBean {
    protected int statusCode;
    protected String alertMessage;
    protected int currentPageNumber;
    private int neighborhoodLikeID;

    public int getNeighborhoodLikeID() {
        return neighborhoodLikeID;
    }

    public void setNeighborhoodLikeID(int neighborhoodLikeID) {
        this.neighborhoodLikeID = neighborhoodLikeID;
    }

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

    protected int pageRowNumber;
}
