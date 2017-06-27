package com.glory.bianyitong.bean.entity;

/**
 * Created by Administrator on 2017/6/27.
 */

public class UserLockMapping {

    public UserLockMapping(String authorizationUserID, int userIdentity, int timeLimit, String startDate, String endDate) {
        this.authorizationUserID = authorizationUserID;
        this.userIdentity = userIdentity;
        this.timeLimit = timeLimit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * authorizationUserID : 2
     * userIdentity : 1

     * timeLimit : 0
     * startDate :
     * endDate :
     */

    private String authorizationUserID;
    private int userIdentity;
    private int timeLimit;
    private String startDate;
    private String endDate;

    /**
     * 新增
     * @param userIdentity
     * @param timeLimit
     * @param authorizationUserPhone
     * @param authorizationUserName
     */
    public UserLockMapping(int userIdentity, int timeLimit, String authorizationUserPhone, String authorizationUserName) {
        this.userIdentity = userIdentity;
        this.timeLimit = timeLimit;
        this.authorizationUserPhone = authorizationUserPhone;
        AuthorizationUserName = authorizationUserName;
    }

    public String getAuthorizationUserPhone() {
        return authorizationUserPhone;
    }

    public void setAuthorizationUserPhone(String authorizationUserPhone) {
        this.authorizationUserPhone = authorizationUserPhone;
    }

    public String getAuthorizationUserName() {
        return AuthorizationUserName;
    }

    public void setAuthorizationUserName(String authorizationUserName) {
        AuthorizationUserName = authorizationUserName;
    }

    private String authorizationUserPhone;
    private String  AuthorizationUserName;

    public String getAuthorizationUserID() {
        return authorizationUserID;
    }

    public void setAuthorizationUserID(String authorizationUserID) {
        this.authorizationUserID = authorizationUserID;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
