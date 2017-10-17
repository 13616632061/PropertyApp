package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/2/7.
 */
public class CommnunityInfo {
    /**
     * userCommunityID : 1024
     * userID : 25
     * userName : 里斯
     * communityID : 1
     * communityName : 西丽小区
     * userIDentityID : 1
     * userIDentityName : 业主
     * provinceID : 440000
     * provinceName : 广东省
     * cityID : 440300
     * cityName : 深圳市
     * buildingID : 1
     * buildingName : 1号楼
     * roomID : 2
     * roomName : 102
     * approvalStatus : 1
     * unitID : 1
     * unitName : A单元
     * approvalDate : 2017-01-03T16:14:00
     */
    private int userCommunityID;
    private Object userID;
    private String userName;
    private int communityID;
    private String communityName;
    private int userIDentityID;
    private String userIDentityName;
    private int provinceID;
    private String provinceName;
    private int cityID;
    private String cityName;
    private int buildingID;
    private String buildingName;
    private int roomID;
    private String roomName;
    private int approvalStatus=-4;
    private int unitID;
    private String unitName;
    private String approvalDate;
    private String approvalStatusName;

    public String getApprovalStatusName() {
        return approvalStatusName;
    }

    public void setApprovalStatusName(String approvalStatusName) {
        this.approvalStatusName = approvalStatusName;
    }

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

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
    @Override
    public String toString() {
        return "{" +
                "\"userCommunityID\":" + userCommunityID +
                ",\"userID\":" + userID +
                ",\"userName\":\"" + userName + "\"" +
                ",\"communityID\":" + communityID +
                ",\"communityName\":\"" + communityName + "\"" +
                ",\"userIDentityID\":" + userIDentityID +
                ",\"userIDentityName\":\"" + userIDentityName + "\"" +
                ",\"provinceID\":" + provinceID +
                ",\"provinceName\":\"" + provinceName + "\"" +
                ",\"cityID\":" + cityID +
                ",\"cityName\":\"" + cityName + "\"" +
                ",\"buildingID\":" + buildingID +
                ",\"buildingName\":\"" + buildingName + "\"" +
                ",\"roomID\":" + roomID +
                ",\"roomName\":\"" + roomName + "\"" +
                ",\"approvalStatus\":" + approvalStatus +
                ",\"unitID\":" + unitID +
                ",\"unitName\":\"" + unitName + "\"" +
                ",\"approvalDate\":\"" + approvalDate + "\"" +
                "}";
    }
}
