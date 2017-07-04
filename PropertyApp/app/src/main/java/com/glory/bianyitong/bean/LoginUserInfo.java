package com.glory.bianyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucy on 2017/5/10.
 * 登录用户 信息
 */
public class LoginUserInfo{

    /**
     * user : {"jgPushID":null,"jgPushName":null,"status":1,"userID":"ZmViYn5kgv0BlGmwaiog9A==","userName":"黄悦","gender":"1","loginName":"测试用户123","phoneNumber":"13510012206","joinDate":"2017-05-18T00:00:00","customerPhoto":"安卓哈哈","chinaCity_ID":0,"signature":"安卓"}
     * userCommnunity : [{"userCommunityID":47,"userName":"","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","communityID":1,"communityName":"西丽小区","buildingID":1,"buildingName":"1号楼","unitID":3,"unitName":"B单元","roomID":1,"roomName":"101","approvalStatus":1,"approvalStatusName":"已审核","approvalDate":"2017-06-26T00:00:00"}]
     * accessToken : IzAGEXo4K/6QFEhT7Z8lG4KDhBSiiJOqrV082FBdbVHGCzQmY6UkWHci6JMc2j2sO/ep4oYHDCtOfRf5JyU7eMBmzoD8HGuk32A0mb18fG2Ye4fbn7VmcHQiLH83AAXxgCCfZfK4+HV9tqFOrKK4GO6+1qoi+g3+wbFjmp3QpOiIF2CmbprDJUpNYK7l8Jiqpb/9ZXg6t7+4V3n0uQ4EVX3EomjJihW0ThOHAkWwx6jXnl3VMIzb7aOPiLYNKEkBrBzTI7iZZfqcV2W7bsJRYSQ7zG8vimc1OMPgDbNUMm3n+k5/K9xIcQW7ErPcu0ZD
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private UserBean user;
    private String accessToken;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<UserCommnunityBean> userCommnunity;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public List<UserCommnunityBean> getUserCommnunity() {
        return userCommnunity;
    }

    public void setUserCommnunity(List<UserCommnunityBean> userCommnunity) {
        this.userCommnunity = userCommnunity;
    }

    public static class UserBean  {
        /**
         * jgPushID : null
         * jgPushName : null
         * status : 1
         * userID : ZmViYn5kgv0BlGmwaiog9A==
         * userName :
         * gender : 1
         * loginName : 测试用户123
         * phoneNumber : 13510012206
         * joinDate : 2017-05-18T00:00:00
         * customerPhoto : 安卓哈哈
         * chinaCity_ID : 0
         * signature : 安卓
         */

        private Object jgPushID;
        private Object jgPushName;
        private int status;
        private String userID;
        private String userName;
        private String gender;
        private String loginName;
        private String phoneNumber;
        private String joinDate;
        private String customerPhoto;
        private int chinaCity_ID;
        private String signature;

        public Object getJgPushID() {
            return jgPushID;
        }

        public void setJgPushID(Object jgPushID) {
            this.jgPushID = jgPushID;
        }

        public Object getJgPushName() {
            return jgPushName;
        }

        public void setJgPushName(Object jgPushName) {
            this.jgPushName = jgPushName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public String getCustomerPhoto() {
            return customerPhoto;
        }

        public void setCustomerPhoto(String customerPhoto) {
            this.customerPhoto = customerPhoto;
        }

        public int getChinaCity_ID() {
            return chinaCity_ID;
        }

        public void setChinaCity_ID(int chinaCity_ID) {
            this.chinaCity_ID = chinaCity_ID;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    public static class UserCommnunityBean {
        /**
         * userCommunityID : 47
         * userName :
         * userIDentityID : 1
         * userIDentityName : 业主
         * provinceID : 440000
         * provinceName : 广东省
         * cityID : 440300
         * cityName : 深圳市
         * communityID : 1
         * communityName : 西丽小区
         * buildingID : 1
         * buildingName : 1号楼
         * unitID : 3
         * unitName : B单元
         * roomID : 1
         * roomName : 101
         * approvalStatus : 1
         * approvalStatusName : 已审核
         * approvalDate : 2017-06-26T00:00:00
         */

        private int userCommunityID;
        private String userName;
        private int userIDentityID;
        private String userIDentityName;
        private int provinceID;
        private String provinceName;
        private int cityID;
        private String cityName;
        private int communityID;
        private String communityName;
        private int buildingID;
        private String buildingName;
        private int unitID;
        private String unitName;
        private int roomID;
        private String roomName;
        private int approvalStatus;
        private String approvalStatusName;
        private String approvalDate;

        public int getUserCommunityID() {
            return userCommunityID;
        }

        public void setUserCommunityID(int userCommunityID) {
            this.userCommunityID = userCommunityID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public int getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public int getUnitID() {
            return unitID;
        }

        public void setUnitID(int unitID) {
            this.unitID = unitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getRoomID() {
            return roomID;
        }

        public void setRoomID(int roomID) {
            this.roomID = roomID;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getApprovalStatusName() {
            return approvalStatusName;
        }

        public void setApprovalStatusName(String approvalStatusName) {
            this.approvalStatusName = approvalStatusName;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }
    }
}
