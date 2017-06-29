package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/21.
 */
public class YellowPageInfo extends BaseResponseBean {

    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"yellowPageID","permissionValues":1,"columnDescription":"黄页ID"},{"columnID":0,"columnName":"yellowPageGroupID","permissionValues":2,"columnDescription":"黄页分组ID"},{"columnID":0,"columnName":"yellowPageGroupName","permissionValues":4,"columnDescription":"黄页分组名称"},{"columnID":0,"columnName":"yellowPageGroupLeaf","permissionValues":8,"columnDescription":"黄页分组递归节点"},{"columnID":0,"columnName":"yellowPageTEL","permissionValues":16,"columnDescription":"黄页电话号码"},{"columnID":0,"columnName":"communityID","permissionValues":32,"columnDescription":"社区ID"},{"columnID":0,"columnName":"communityName","permissionValues":64,"columnDescription":"社区名称"},{"columnID":0,"columnName":"yellowPageContext","permissionValues":128,"columnDescription":"黄页内容"}]
     * listYellowPage : [{"yellowPageID":1,"yellowPageGroupID":1,"yellowPageGroupName":"警务","yellowPageGroupLeaf":2,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"西丽派出所"},{"yellowPageID":5,"yellowPageGroupID":1,"yellowPageGroupName":"警务","yellowPageGroupLeaf":1,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"西丽社区工作站"},{"yellowPageID":7,"yellowPageGroupID":2,"yellowPageGroupName":"行政单位","yellowPageGroupLeaf":1,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"燃气公司"},{"yellowPageID":8,"yellowPageGroupID":2,"yellowPageGroupName":"行政单位","yellowPageGroupLeaf":2,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"供电公司"},{"yellowPageID":9,"yellowPageGroupID":2,"yellowPageGroupName":"行政单位","yellowPageGroupLeaf":1,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"物流公司"},{"yellowPageID":10,"yellowPageGroupID":2,"yellowPageGroupName":"行政单位","yellowPageGroupLeaf":2,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"服务中心电话"},{"yellowPageID":11,"yellowPageGroupID":3,"yellowPageGroupName":"小区物管","yellowPageGroupLeaf":3,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"快递中心"},{"yellowPageID":12,"yellowPageGroupID":3,"yellowPageGroupName":"小区物管","yellowPageGroupLeaf":3,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"物业中心"},{"yellowPageID":13,"yellowPageGroupID":1,"yellowPageGroupName":"警务","yellowPageGroupLeaf":3,"yellowPageTEL":"18800000000","communityID":1,"communityName":"西丽小区","yellowPageContext":"居委会"},{"yellowPageID":48,"yellowPageGroupID":1,"yellowPageGroupName":"警务","yellowPageGroupLeaf":1,"yellowPageTEL":"2","communityID":1,"communityName":"西丽小区","yellowPageContext":"2"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     */


    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListYellowPageBean> listYellowPage;

    public List<TableColumnPermissionBean> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<TableColumnPermissionBean> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListYellowPageBean> getListYellowPage() {
        return listYellowPage;
    }

    public void setListYellowPage(List<ListYellowPageBean> listYellowPage) {
        this.listYellowPage = listYellowPage;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : yellowPageID
         * permissionValues : 1
         * columnDescription : 黄页ID
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

    public static class ListYellowPageBean {
        /**
         * yellowPageID : 1
         * yellowPageGroupID : 1
         * yellowPageGroupName : 警务
         * yellowPageGroupLeaf : 2
         * yellowPageTEL : 18800000000
         * communityID : 1
         * communityName : 西丽小区
         * yellowPageContext : 西丽派出所
         */

        private int yellowPageID;
        private int yellowPageGroupID;
        private String yellowPageGroupName;
        private int yellowPageGroupLeaf;
        private String yellowPageTEL;
        private int communityID;
        private String communityName;
        private String yellowPageContext;

        public int getYellowPageID() {
            return yellowPageID;
        }

        public void setYellowPageID(int yellowPageID) {
            this.yellowPageID = yellowPageID;
        }

        public int getYellowPageGroupID() {
            return yellowPageGroupID;
        }

        public void setYellowPageGroupID(int yellowPageGroupID) {
            this.yellowPageGroupID = yellowPageGroupID;
        }

        public String getYellowPageGroupName() {
            return yellowPageGroupName;
        }

        public void setYellowPageGroupName(String yellowPageGroupName) {
            this.yellowPageGroupName = yellowPageGroupName;
        }

        public int getYellowPageGroupLeaf() {
            return yellowPageGroupLeaf;
        }

        public void setYellowPageGroupLeaf(int yellowPageGroupLeaf) {
            this.yellowPageGroupLeaf = yellowPageGroupLeaf;
        }

        public String getYellowPageTEL() {
            return yellowPageTEL;
        }

        public void setYellowPageTEL(String yellowPageTEL) {
            this.yellowPageTEL = yellowPageTEL;
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

        public String getYellowPageContext() {
            return yellowPageContext;
        }

        public void setYellowPageContext(String yellowPageContext) {
            this.yellowPageContext = yellowPageContext;
        }
    }
}
