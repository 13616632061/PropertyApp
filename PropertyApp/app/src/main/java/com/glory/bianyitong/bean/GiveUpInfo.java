package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/10/27.
 */
public class GiveUpInfo  {

    /**
     * neighborhoodLikeID : 0
     * listNeighborhoodLike : [{"neighborhoodLikeID":4,"neighborhoodID":61,"loginName":"新用户747503","customerPhoto":"https://bianyitong.oss-cn-shenzhen.aliyuncs.com/201710/26/201710261610200465.jpg","likeDateTime":"2017-10-24T15:59:44"},{"neighborhoodLikeID":6,"neighborhoodID":61,"loginName":"新用户747503","customerPhoto":"https://bianyitong.oss-cn-shenzhen.aliyuncs.com/201710/26/201710261610200465.jpg","likeDateTime":"2017-10-25T13:35:06"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int neighborhoodLikeID;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListNeighborhoodLikeBean> listNeighborhoodLike;

    public int getNeighborhoodLikeID() {
        return neighborhoodLikeID;
    }

    public void setNeighborhoodLikeID(int neighborhoodLikeID) {
        this.neighborhoodLikeID = neighborhoodLikeID;
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

    public List<ListNeighborhoodLikeBean> getListNeighborhoodLike() {
        return listNeighborhoodLike;
    }

    public void setListNeighborhoodLike(List<ListNeighborhoodLikeBean> listNeighborhoodLike) {
        this.listNeighborhoodLike = listNeighborhoodLike;
    }

    public static class ListNeighborhoodLikeBean {
        /**
         * neighborhoodLikeID : 4
         * neighborhoodID : 61
         * loginName : 新用户747503
         * customerPhoto : https://bianyitong.oss-cn-shenzhen.aliyuncs.com/201710/26/201710261610200465.jpg
         * likeDateTime : 2017-10-24T15:59:44
         */

        private int neighborhoodLikeID;
        private int neighborhoodID;
        private String loginName;
        private String customerPhoto;
        private String likeDateTime;
        private String aesUserID;

        public String getAesUserID() {
            return aesUserID;
        }

        public void setAesUserID(String aesUserID) {
            this.aesUserID = aesUserID;
        }

        public int getNeighborhoodLikeID() {
            return neighborhoodLikeID;
        }

        public void setNeighborhoodLikeID(int neighborhoodLikeID) {
            this.neighborhoodLikeID = neighborhoodLikeID;
        }

        public int getNeighborhoodID() {
            return neighborhoodID;
        }

        public void setNeighborhoodID(int neighborhoodID) {
            this.neighborhoodID = neighborhoodID;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getCustomerPhoto() {
            return customerPhoto;
        }

        public void setCustomerPhoto(String customerPhoto) {
            this.customerPhoto = customerPhoto;
        }

        public String getLikeDateTime() {
            return likeDateTime;
        }

        public void setLikeDateTime(String likeDateTime) {
            this.likeDateTime = likeDateTime;
        }
    }
}
