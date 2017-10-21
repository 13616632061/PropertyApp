package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/5/9.
 * 获取验证码
 */
public class GetSMSCodeInfo extends ResponseBaseInfo{

    /**
     * msg : 051724
     */

    private String msg;
    private String alertMessage;

    @Override
    public String getAlertMessage() {
        return alertMessage;
    }

    @Override
    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
