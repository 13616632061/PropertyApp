package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/12/12.
 */
public class MyPointsListInfo {

    /**
     * listUserPointDetail : [{"userPointDetailID":4,"changeReason":"积分兑换","changeType":10,"pointType":30,"createDate":"2017-05-08T00:00:00","changePointsString":"+20"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 1
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListUserPointDetailBean> listUserPointDetail;

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

    public List<ListUserPointDetailBean> getListUserPointDetail() {
        return listUserPointDetail;
    }

    public void setListUserPointDetail(List<ListUserPointDetailBean> listUserPointDetail) {
        this.listUserPointDetail = listUserPointDetail;
    }

    public static class ListUserPointDetailBean {
        /**
         * userPointDetailID : 4
         * changeReason : 积分兑换
         * changeType : 10
         * pointType : 30
         * createDate : 2017-05-08T00:00:00
         * changePointsString : +20
         */

        private int userPointDetailID;
        private String changeReason;
        private int changeType;
        private int pointType;
        private String createDate;
        private String changePointsString;

        public int getUserPointDetailID() {
            return userPointDetailID;
        }

        public void setUserPointDetailID(int userPointDetailID) {
            this.userPointDetailID = userPointDetailID;
        }

        public String getChangeReason() {
            return changeReason;
        }

        public void setChangeReason(String changeReason) {
            this.changeReason = changeReason;
        }

        public int getChangeType() {
            return changeType;
        }

        public void setChangeType(int changeType) {
            this.changeType = changeType;
        }

        public int getPointType() {
            return pointType;
        }

        public void setPointType(int pointType) {
            this.pointType = pointType;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getChangePointsString() {
            return changePointsString;
        }

        public void setChangePointsString(String changePointsString) {
            this.changePointsString = changePointsString;
        }
    }
}
