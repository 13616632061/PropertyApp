package com.glory.bianyitong.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucy on 2017/9/19.
 */
public class WeiXinInfo {

    /**
     * appId : wxd2ec5fc5fab63695
     * timeStamp : 1505799593
     * nonceStr : 4c36523814dc47faba17a99c4ca4c1db
     * package : Sign=WXPay
     * signType : null
     * paySign : 08725EEC2DA9280CB22C9385CF3CAE3C
     * partnerid : 1440193002
     * prepayid :
     * statusCode : 0
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private String appId;
    private String timeStamp;
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    private Object signType;
    private String paySign;
    private String partnerid;
    private String prepayid;
    private int statusCode;
    private int currentPageNumber;
    private int pageRowNumber;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public Object getSignType() {
        return signType;
    }

    public void setSignType(Object signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
}
