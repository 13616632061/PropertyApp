package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/10/30.
 */
public class CollectionNiInfo {

    /**
     * listNeighborhoodCollect : [{"neighborhoodCollectID":1,"collectUserPicture":"https://bianyitong.oss-cn-shenzhen.aliyuncs.com/JxXBMMkm6qSwkySUmquWaw==/userHeader/_1508830759593_logo.jpg","collectUserName":"简简单单","collectType":1,"collectContent":"77878","createDate":"2017-10-27T13:28:37"},{"neighborhoodCollectID":3,"collectUserPicture":"https://bianyitong.oss-cn-shenzhen.aliyuncs.com/JxXBMMkm6qSwkySUmquWaw==/userHeader/_1508830759593_logo.jpg","collectUserName":"简简单单","collectType":1,"collectContent":"7758781","createDate":"2017-10-27T13:32:52"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 2
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListNeighborhoodCollectBean> listNeighborhoodCollect;

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

    public List<ListNeighborhoodCollectBean> getListNeighborhoodCollect() {
        return listNeighborhoodCollect;
    }

    public void setListNeighborhoodCollect(List<ListNeighborhoodCollectBean> listNeighborhoodCollect) {
        this.listNeighborhoodCollect = listNeighborhoodCollect;
    }

    public static class ListNeighborhoodCollectBean {
        /**
         * neighborhoodCollectID : 1
         * collectUserPicture : https://bianyitong.oss-cn-shenzhen.aliyuncs.com/JxXBMMkm6qSwkySUmquWaw==/userHeader/_1508830759593_logo.jpg
         * collectUserName : 简简单单
         * collectType : 1
         * collectContent : 77878
         * createDate : 2017-10-27T13:28:37
         */

        private int neighborhoodCollectID;
        private String collectUserPicture;
        private String collectUserName;
        private int collectType;
        private String collectContent;
        private String createDate;

        public int getNeighborhoodCollectID() {
            return neighborhoodCollectID;
        }

        public void setNeighborhoodCollectID(int neighborhoodCollectID) {
            this.neighborhoodCollectID = neighborhoodCollectID;
        }

        public String getCollectUserPicture() {
            return collectUserPicture;
        }

        public void setCollectUserPicture(String collectUserPicture) {
            this.collectUserPicture = collectUserPicture;
        }

        public String getCollectUserName() {
            return collectUserName;
        }

        public void setCollectUserName(String collectUserName) {
            this.collectUserName = collectUserName;
        }

        public int getCollectType() {
            return collectType;
        }

        public void setCollectType(int collectType) {
            this.collectType = collectType;
        }

        public String getCollectContent() {
            return collectContent;
        }

        public void setCollectContent(String collectContent) {
            this.collectContent = collectContent;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
