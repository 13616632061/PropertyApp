package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/12/12.
 */
public class MyPointsInfo {

    /**
     * userPoint : {"userPointID":1,"realtimePoints":90,"convertiblePoints":60,"convertibilityPoints":50,"createDate":"2017-12-11T10:40:06"}
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private UserPointBean userPoint;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public UserPointBean getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(UserPointBean userPoint) {
        this.userPoint = userPoint;
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

    public static class UserPointBean {
        /**
         * userPointID : 1
         * realtimePoints : 90
         * convertiblePoints : 60
         * convertibilityPoints : 50
         * createDate : 2017-12-11T10:40:06
         */

        private int userPointID;
        private int realtimePoints;
        private int convertiblePoints;
        private int convertibilityPoints;
        private String createDate;

        public int getUserPointID() {
            return userPointID;
        }

        public void setUserPointID(int userPointID) {
            this.userPointID = userPointID;
        }

        public int getRealtimePoints() {
            return realtimePoints;
        }

        public void setRealtimePoints(int realtimePoints) {
            this.realtimePoints = realtimePoints;
        }

        public int getConvertiblePoints() {
            return convertiblePoints;
        }

        public void setConvertiblePoints(int convertiblePoints) {
            this.convertiblePoints = convertiblePoints;
        }

        public int getConvertibilityPoints() {
            return convertibilityPoints;
        }

        public void setConvertibilityPoints(int convertibilityPoints) {
            this.convertibilityPoints = convertibilityPoints;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
