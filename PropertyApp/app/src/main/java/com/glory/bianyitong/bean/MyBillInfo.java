package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/10/13.
 */
public class MyBillInfo {

    /**
     * userBill : null
     * listUserBill : [{"userBillID":5,"billType":6,"amount":88,"remark":"9月份电费","createDate":"2017-10-13T11:15:08","createUserID":1,"lastUpdateDate":"2017-10-13T11:15:08","lastUpdateUserID":1,"roomID":1,"commnuityName":"西丽小区","roomName":"101","displayName":"电费"},{"userBillID":4,"billType":1,"amount":900,"remark":"9月份房租","createDate":"2017-10-13T11:15:08","createUserID":1,"lastUpdateDate":"2017-10-13T11:15:08","lastUpdateUserID":1,"roomID":1,"commnuityName":"西丽小区","roomName":"101","displayName":"房租"},{"userBillID":1,"billType":1,"amount":120,"remark":"9月份水费","createDate":"2017-02-09T00:00:00","createUserID":1,"lastUpdateDate":"2017-02-09T00:00:00","lastUpdateUserID":1,"roomID":180,"commnuityName":"留仙小区","roomName":"201","displayName":"房租"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private Object userBill;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListUserBillBean> listUserBill;

    public Object getUserBill() {
        return userBill;
    }

    public void setUserBill(Object userBill) {
        this.userBill = userBill;
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

    public List<ListUserBillBean> getListUserBill() {
        return listUserBill;
    }

    public void setListUserBill(List<ListUserBillBean> listUserBill) {
        this.listUserBill = listUserBill;
    }

    public static class ListUserBillBean {
        /**
         * userBillID : 5
         * billType : 6
         * amount : 88
         * remark : 9月份电费
         * createDate : 2017-10-13T11:15:08
         * createUserID : 1
         * lastUpdateDate : 2017-10-13T11:15:08
         * lastUpdateUserID : 1
         * roomID : 1
         * commnuityName : 西丽小区
         * roomName : 101
         * displayName : 电费
         */

        private int userBillID;
        private int billType;
        private int amount;
        private String remark;
        private String createDate;
        private int createUserID;
        private String lastUpdateDate;
        private int lastUpdateUserID;
        private int roomID;
        private String commnuityName;
        private String roomName;
        private String displayName;

        public int getUserBillID() {
            return userBillID;
        }

        public void setUserBillID(int userBillID) {
            this.userBillID = userBillID;
        }

        public int getBillType() {
            return billType;
        }

        public void setBillType(int billType) {
            this.billType = billType;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getCreateUserID() {
            return createUserID;
        }

        public void setCreateUserID(int createUserID) {
            this.createUserID = createUserID;
        }

        public String getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(String lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

        public int getLastUpdateUserID() {
            return lastUpdateUserID;
        }

        public void setLastUpdateUserID(int lastUpdateUserID) {
            this.lastUpdateUserID = lastUpdateUserID;
        }

        public int getRoomID() {
            return roomID;
        }

        public void setRoomID(int roomID) {
            this.roomID = roomID;
        }

        public String getCommnuityName() {
            return commnuityName;
        }

        public void setCommnuityName(String commnuityName) {
            this.commnuityName = commnuityName;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }
}
