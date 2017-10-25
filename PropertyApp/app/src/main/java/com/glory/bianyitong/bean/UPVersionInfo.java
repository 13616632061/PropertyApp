package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/2/9.
 */
public class UPVersionInfo {

    /**
     * version : {"versionID":106,"versionDate":"2017-10-17T00:00:00","versionNumber":"1.0.0","versionCode":"29","deviceTypeID":3,"imprint":"测试版","appTypeID":0,"prerequisite":false,"updatePath":"http://www.baidu.com"}
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private VersionBean version;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
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

    public static class VersionBean {
        /**
         * versionID : 106
         * versionDate : 2017-10-17T00:00:00
         * versionNumber : 1.0.0
         * versionCode : 29
         * deviceTypeID : 3
         * imprint : 测试版
         * appTypeID : 0
         * prerequisite : false
         * updatePath : http://www.baidu.com
         */

        private int versionID;
        private String versionDate;
        private String versionNumber;
        private String versionCode;
        private int deviceTypeID;
        private String imprint;
        private int appTypeID;
        private boolean prerequisite;
        private String updatePath;

        public int getVersionID() {
            return versionID;
        }

        public void setVersionID(int versionID) {
            this.versionID = versionID;
        }

        public String getVersionDate() {
            return versionDate;
        }

        public void setVersionDate(String versionDate) {
            this.versionDate = versionDate;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public int getDeviceTypeID() {
            return deviceTypeID;
        }

        public void setDeviceTypeID(int deviceTypeID) {
            this.deviceTypeID = deviceTypeID;
        }

        public String getImprint() {
            return imprint;
        }

        public void setImprint(String imprint) {
            this.imprint = imprint;
        }

        public int getAppTypeID() {
            return appTypeID;
        }

        public void setAppTypeID(int appTypeID) {
            this.appTypeID = appTypeID;
        }

        public boolean isPrerequisite() {
            return prerequisite;
        }

        public void setPrerequisite(boolean prerequisite) {
            this.prerequisite = prerequisite;
        }

        public String getUpdatePath() {
            return updatePath;
        }

        public void setUpdatePath(String updatePath) {
            this.updatePath = updatePath;
        }
    }
}
