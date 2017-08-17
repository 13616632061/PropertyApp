package com.glory.bianyitong.bean.entity.request;

/**
 * Created by billlamian on 2017/8/17.
 * 登陆请求
 */

public class RequestLoginBean {
    private String phoneNumber,smsCheckCode;
    private int deviceType=3;

    public RequestLoginBean(String phoneNumber, String smsCheckCode) {
        this.phoneNumber = phoneNumber;
        this.smsCheckCode = smsCheckCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSmsCheckCode() {
        return smsCheckCode;
    }

    public void setSmsCheckCode(String smsCheckCode) {
        this.smsCheckCode = smsCheckCode;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
