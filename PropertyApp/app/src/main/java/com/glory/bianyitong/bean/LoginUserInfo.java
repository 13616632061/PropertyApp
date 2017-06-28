package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/5/10.
 * 登录用户 信息
 */
public class LoginUserInfo extends ResponseBaseInfo{

    /**
     * user : {"jgPushID":null,"jgPushName":null,"userID":"gPigLPD6EEbljpM0M0rxew==","userName":"Admin","userName_en":"Admin","gender":"2","loginName":"Admin","password":"21232F297A57A5A743894A0E4A801FC3","phoneNumber":"18612565664","birthDay":"2016-12-05T00:00:00","endBirthday":null,"openID":"15899647853","joinDate":"2016-12-20T00:00:00","endJoinDate":null,"status":"1","email":"78863622@qq.com","qq":"78863622","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg","chinaCity_ID":440300,"chinaCity_Name":"深圳市","signature":null}
     * userCommnunity : [{"userCommunityID":32,"userID":null,"userName":"","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"cityID":440300,"communityID":1,"communityName":"西丽小区","buildingID":1,"buildingName":"1号楼","unitID":1,"unitName":"A单元","roomID":1,"roomName":"101","approvalStatus":1,"approvalDate":"2016-12-21T00:00:00","endApprovalDate":null,"phoneNumber":null}]
     */

    private UserBean user;
    private List<UserCommnunityBean> userCommnunity;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<UserCommnunityBean> getUserCommnunity() {
        return userCommnunity;
    }

    public void setUserCommnunity(List<UserCommnunityBean> userCommnunity) {
        this.userCommnunity = userCommnunity;
    }

    public static class UserBean {
        /**
         * jgPushID : null
         * jgPushName : null
         * userID : gPigLPD6EEbljpM0M0rxew==
         * userName : Admin
         * userName_en : Admin
         * gender : 2
         * loginName : Admin
         * password : 21232F297A57A5A743894A0E4A801FC3
         * phoneNumber : 18612565664
         * birthDay : 2016-12-05T00:00:00
         * endBirthday : null
         * openID : 15899647853
         * joinDate : 2016-12-20T00:00:00
         * endJoinDate : null
         * status : 1
         * email : 78863622@qq.com
         * qq : 78863622
         * customerPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
         * chinaCity_ID : 440300
         * chinaCity_Name : 深圳市
         * signature : null
         */

        private Object jgPushID;
        private Object jgPushName;
        private String userID;
        private String userName;
        private String userName_en;
        private String gender;
        private String loginName;
        private String password;
        private String phoneNumber;
        private String birthDay;
        private Object endBirthday;
        private String openID;
        private String joinDate;
        private Object endJoinDate;
        private String status;
        private String email;
        private String qq;
        private String customerPhoto;
        private int chinaCity_ID;
        private String chinaCity_Name;
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

        public String getUserName_en() {
            return userName_en;
        }

        public void setUserName_en(String userName_en) {
            this.userName_en = userName_en;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public Object getEndBirthday() {
            return endBirthday;
        }

        public void setEndBirthday(Object endBirthday) {
            this.endBirthday = endBirthday;
        }

        public String getOpenID() {
            return openID;
        }

        public void setOpenID(String openID) {
            this.openID = openID;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public Object getEndJoinDate() {
            return endJoinDate;
        }

        public void setEndJoinDate(Object endJoinDate) {
            this.endJoinDate = endJoinDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public String getChinaCity_Name() {
            return chinaCity_Name;
        }

        public void setChinaCity_Name(String chinaCity_Name) {
            this.chinaCity_Name = chinaCity_Name;
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
         * userCommunityID : 32
         * userID : null
         * userName :
         * userIDentityID : 1
         * userIDentityName : 业主
         * provinceID : 440000
         * cityID : 440300
         * communityID : 1
         * communityName : 西丽小区
         * buildingID : 1
         * buildingName : 1号楼
         * unitID : 1
         * unitName : A单元
         * roomID : 1
         * roomName : 101
         * approvalStatus : 1
         * approvalDate : 2016-12-21T00:00:00
         * endApprovalDate : null
         * phoneNumber : null
         */

        private int userCommunityID;
        private Object userID;
        private String userName;
        private int userIDentityID;
        private String userIDentityName;
        private int provinceID;
        private int cityID;
        private int communityID;
        private String communityName;
        private int buildingID;
        private String buildingName;
        private int unitID;
        private String unitName;
        private int roomID;
        private String roomName;
        private int approvalStatus;
        private String approvalDate;
        private Object endApprovalDate;
        private Object phoneNumber;

        public int getUserCommunityID() {
            return userCommunityID;
        }

        public void setUserCommunityID(int userCommunityID) {
            this.userCommunityID = userCommunityID;
        }

        public Object getUserID() {
            return userID;
        }

        public void setUserID(Object userID) {
            this.userID = userID;
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

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
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

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }

        public Object getEndApprovalDate() {
            return endApprovalDate;
        }

        public void setEndApprovalDate(Object endApprovalDate) {
            this.endApprovalDate = endApprovalDate;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
