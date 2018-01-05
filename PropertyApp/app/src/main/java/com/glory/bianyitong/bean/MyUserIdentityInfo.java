package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2018/1/4.
 */
public class MyUserIdentityInfo {

    /**
     * listUserIdentity : [{"userIDentityID":1,"userIDentityName":"业主"},{"userIDentityID":2,"userIDentityName":"租客"},{"userIDentityID":9,"userIDentityName":"其他"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListUserIdentityBean> listUserIdentity;

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

    public List<ListUserIdentityBean> getListUserIdentity() {
        return listUserIdentity;
    }

    public void setListUserIdentity(List<ListUserIdentityBean> listUserIdentity) {
        this.listUserIdentity = listUserIdentity;
    }

    public static class ListUserIdentityBean {
        /**
         * userIDentityID : 1
         * userIDentityName : 业主
         */

        private int userIDentityID;
        private String userIDentityName;
        private boolean check=false;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public int getUserIDentityID() {
            return userIDentityID;
        }

        public void setUserIDentityID(int userIDentityID) {
            this.userIDentityID = userIDentityID;
        }

        public String getUserIDentityName() {
            return userIDentityName;
        }

        public void setUserIDentityName(String userIDentityName) {
            this.userIDentityName = userIDentityName;
        }
    }
}
