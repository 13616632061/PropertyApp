package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/7.
 */
public class AuthAreaInfo {

    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"userCommunityID","permissionValues":1,"columnDescription":"用户社区对照表ID"},{"columnID":0,"columnName":"userID","permissionValues":2,"columnDescription":"用户ID"},{"columnID":0,"columnName":"userName","permissionValues":4,"columnDescription":"用户姓名"},{"columnID":0,"columnName":"communityID","permissionValues":8,"columnDescription":"社区ID"},{"columnID":0,"columnName":"communityName","permissionValues":16,"columnDescription":"社区名称"},{"columnID":0,"columnName":"userIDentityID","permissionValues":32,"columnDescription":"用户身份ID"},{"columnID":0,"columnName":"userIDentityName","permissionValues":64,"columnDescription":"用户身份名称"},{"columnID":0,"columnName":"provinceID","permissionValues":128,"columnDescription":"省ID"},{"columnID":0,"columnName":"provinceName","permissionValues":256,"columnDescription":"省名称"},{"columnID":0,"columnName":"cityID","permissionValues":512,"columnDescription":"市ID"},{"columnID":0,"columnName":"cityName","permissionValues":1024,"columnDescription":"市名称"},{"columnID":0,"columnName":"buildingID","permissionValues":2048,"columnDescription":"楼栋ID"},{"columnID":0,"columnName":"buildingName","permissionValues":4096,"columnDescription":"楼栋名称"},{"columnID":0,"columnName":"roomID","permissionValues":8192,"columnDescription":"房间ID"},{"columnID":0,"columnName":"roomName","permissionValues":16384,"columnDescription":"房间名称"},{"columnID":0,"columnName":"approvalStatus","permissionValues":32768,"columnDescription":"审核状态 "},{"columnID":0,"columnName":"unitID","permissionValues":65536,"columnDescription":"单元ID"},{"columnID":0,"columnName":"unitName","permissionValues":131072,"columnDescription":"单元名称"},{"columnID":0,"columnName":"approvalDate","permissionValues":262144,"columnDescription":"申请日期"}]
     * listUserCommnunityMapping : [{"userCommunityID":1024,"userID":25,"userName":"里斯","communityID":1,"communityName":"西丽小区","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":1,"buildingName":"1号楼","roomID":2,"roomName":"102","approvalStatus":1,"unitID":1,"unitName":"A单元","approvalDate":"2017-01-03T16:14:00"},{"userCommunityID":1025,"userID":25,"userName":"里斯","communityID":4,"communityName":"阳光花园","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":10,"buildingName":"1号楼","roomID":42,"roomName":"303","approvalStatus":1,"unitID":18,"unitName":"A单元","approvalDate":"2017-01-03T16:14:39"},{"userCommunityID":1039,"userID":25,"userName":"里斯","communityID":4,"communityName":"阳光花园","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":10,"buildingName":"1号楼","roomID":45,"roomName":"606","approvalStatus":2,"unitID":18,"unitName":"A单元","approvalDate":"2017-01-11T15:17:50"},{"userCommunityID":1050,"userID":25,"userName":"里斯","communityID":33,"communityName":"便易通社区","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":18,"buildingName":"1号楼","roomID":26,"roomName":"505","approvalStatus":1,"unitID":35,"unitName":"A单元","approvalDate":"2017-01-13T17:00:21"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     * totalpagenum : 5
     * nowpagenum : 1
     * pagerownum : 10
     */

    private String version;
    private int statuscode;
    private String statusmessage;
    private String totalpagenum;
    private String nowpagenum;
    private String pagerownum;
    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListUserCommnunityMappingBean> listUserCommnunityMapping;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getTotalpagenum() {
        return totalpagenum;
    }

    public void setTotalpagenum(String totalpagenum) {
        this.totalpagenum = totalpagenum;
    }

    public String getNowpagenum() {
        return nowpagenum;
    }

    public void setNowpagenum(String nowpagenum) {
        this.nowpagenum = nowpagenum;
    }

    public String getPagerownum() {
        return pagerownum;
    }

    public void setPagerownum(String pagerownum) {
        this.pagerownum = pagerownum;
    }

    public List<TableColumnPermissionBean> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<TableColumnPermissionBean> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListUserCommnunityMappingBean> getListUserCommnunityMapping() {
        return listUserCommnunityMapping;
    }

    public void setListUserCommnunityMapping(List<ListUserCommnunityMappingBean> listUserCommnunityMapping) {
        this.listUserCommnunityMapping = listUserCommnunityMapping;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : userCommunityID
         * permissionValues : 1
         * columnDescription : 用户社区对照表ID
         */

        private int columnID;
        private String columnName;
        private int permissionValues;
        private String columnDescription;

        public int getColumnID() {
            return columnID;
        }

        public void setColumnID(int columnID) {
            this.columnID = columnID;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public int getPermissionValues() {
            return permissionValues;
        }

        public void setPermissionValues(int permissionValues) {
            this.permissionValues = permissionValues;
        }

        public String getColumnDescription() {
            return columnDescription;
        }

        public void setColumnDescription(String columnDescription) {
            this.columnDescription = columnDescription;
        }
    }

    public static class ListUserCommnunityMappingBean {
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
        private String userID;
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
        private int approvalStatus;
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
    }
}
