package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/22.
 * 查询投诉类型
 */
public class ComplaintsTypeInfo extends BaseResponseBean{
    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"complaintsTypeID","permissionValues":1,"columnDescription":"投诉类型ID"},{"columnID":0,"columnName":"complaintsTypeName","permissionValues":2,"columnDescription":"投诉类型名称"},{"columnID":0,"columnName":"status","permissionValues":4,"columnDescription":"投诉类型启用状态 "},{"columnID":0,"columnName":"communityID","permissionValues":8,"columnDescription":"社区ID"},{"columnID":0,"columnName":"communityName","permissionValues":16,"columnDescription":"社区名称"}]
     * listComplaintsType : [{"complaintsTypeID":1,"complaintsTypeName":"公共设施","status":"E","communityID":1,"communityName":"西丽小区"},{"complaintsTypeID":3,"complaintsTypeName":"邻里纠纷","status":"E","communityID":1,"communityName":"西丽小区"},{"complaintsTypeID":4,"complaintsTypeName":"噪音扰民","status":"E","communityID":1,"communityName":"西丽小区"},{"complaintsTypeID":6,"complaintsTypeName":"车辆管理","status":"E","communityID":1,"communityName":"西丽小区"},{"complaintsTypeID":7,"complaintsTypeName":"服务态度","status":"E","communityID":1,"communityName":"西丽小区"},{"complaintsTypeID":8,"complaintsTypeName":"其他","status":"E","communityID":1,"communityName":"西丽小区"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     */

    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListComplaintsTypeBean> listComplaintsType;



    public List<TableColumnPermissionBean> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<TableColumnPermissionBean> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListComplaintsTypeBean> getListComplaintsType() {
        return listComplaintsType;
    }

    public void setListComplaintsType(List<ListComplaintsTypeBean> listComplaintsType) {
        this.listComplaintsType = listComplaintsType;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : complaintsTypeID
         * permissionValues : 1
         * columnDescription : 投诉类型ID
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

    public static class ListComplaintsTypeBean {
        /**
         * complaintsTypeID : 1
         * complaintsTypeName : 公共设施
         * status : E
         * communityID : 1
         * communityName : 西丽小区
         */

        private int complaintsTypeID;
        private String complaintsTypeName;
        private String status;
        private int communityID;
        private String communityName;

        public int getComplaintsTypeID() {
            return complaintsTypeID;
        }

        public void setComplaintsTypeID(int complaintsTypeID) {
            this.complaintsTypeID = complaintsTypeID;
        }

        public String getComplaintsTypeName() {
            return complaintsTypeName;
        }

        public void setComplaintsTypeName(String complaintsTypeName) {
            this.complaintsTypeName = complaintsTypeName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }
}
