package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 修改用户信息
 */

public class RequestUserBean {
    private String loginName;//登录名
    private String customerPhoto;//用户头像
    private String signature;//描述

    public RequestUserBean(String loginName, String customerPhoto, String signature) {
        this.loginName = loginName;
        this.customerPhoto = customerPhoto;
        this.signature = signature;
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
