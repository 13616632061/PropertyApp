package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 * 查询用户信息
 */

public class ResponseQueryUserById extends BaseResponseBean {

    private List<ListUserBean> listUser;

    public List<ListUserBean> getListUser() {
        return listUser;
    }

    public void setListUser(List<ListUserBean> listUser) {
        this.listUser = listUser;
    }

    public static class ListUserBean {
        /**
         * jgPushID : null
         * jgPushName : null
         * status : null
         * loginName : 测试用户1234
         * customerPhoto : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/userHeader/_1498798151282_logo.jpg
         * signature : 测试
         */

        private Object jgPushID;
        private Object jgPushName;
        private Object status;
        private String loginName;
        private String customerPhoto;
        private String signature;
        private String userName;
        private String gender;// 性别：1男2女
        private String phoneNumber;

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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getCustomerPhoto() {
            return customerPhoto;
        }

        public void setCustomerPhoto(String customerPhoto) {
            this.customerPhoto = customerPhoto;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}
