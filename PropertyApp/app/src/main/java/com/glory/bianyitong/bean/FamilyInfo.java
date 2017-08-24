package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/8/24.
 */
public class FamilyInfo {

    /**
     * familyID : 0
     * listFamily : [{"familyID":2,"userID":66,"familyUserName":"夏一","identityType":1,"phoneNumber":"18612565666","addDateTime":"2017-06-26T16:36:11"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int familyID;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListFamilyBean> listFamily;

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
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

    public List<ListFamilyBean> getListFamily() {
        return listFamily;
    }

    public void setListFamily(List<ListFamilyBean> listFamily) {
        this.listFamily = listFamily;
    }

    public static class ListFamilyBean {
        /**
         * familyID : 2
         * userID : 66
         * familyUserName : 夏一
         * identityType : 1
         * phoneNumber : 18612565666
         * addDateTime : 2017-06-26T16:36:11
         */

        private int familyID;
        private int userID;
        private String familyUserName;
        private int identityType;
        private String phoneNumber;
        private String addDateTime;

        public int getFamilyID() {
            return familyID;
        }

        public void setFamilyID(int familyID) {
            this.familyID = familyID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getFamilyUserName() {
            return familyUserName;
        }

        public void setFamilyUserName(String familyUserName) {
            this.familyUserName = familyUserName;
        }

        public int getIdentityType() {
            return identityType;
        }

        public void setIdentityType(int identityType) {
            this.identityType = identityType;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddDateTime() {
            return addDateTime;
        }

        public void setAddDateTime(String addDateTime) {
            this.addDateTime = addDateTime;
        }
    }
}
