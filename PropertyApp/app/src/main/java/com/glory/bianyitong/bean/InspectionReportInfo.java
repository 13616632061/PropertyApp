package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/28.
 */
public class InspectionReportInfo {

    /**
     * listQualityPic : [{"qualityPicID":2,"imgUrl":"https://gd2.alicdn.com/imgextra/i2/1657218493/TB2lNPueb1J.eBjSszcXXbFzVXa_!!1657218493.jpg","qualityID":1,"createDate":"2014-05-05T00:00:00"},{"qualityPicID":4,"imgUrl":"https://gd3.alicdn.com/imgextra/i2/205387014/TB2DfJla1IPyuJjSspcXXXiApXa_!!205387014.jpg","qualityID":1,"createDate":"2015-05-05T00:00:00"}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListQualityPicBean> listQualityPic;

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

    public List<ListQualityPicBean> getListQualityPic() {
        return listQualityPic;
    }

    public void setListQualityPic(List<ListQualityPicBean> listQualityPic) {
        this.listQualityPic = listQualityPic;
    }

    public static class ListQualityPicBean {
        /**
         * qualityPicID : 2
         * imgUrl : https://gd2.alicdn.com/imgextra/i2/1657218493/TB2lNPueb1J.eBjSszcXXbFzVXa_!!1657218493.jpg
         * qualityID : 1
         * createDate : 2014-05-05T00:00:00
         */

        private int qualityPicID;
        private String imgUrl;
        private int qualityID;
        private String createDate;

        public int getQualityPicID() {
            return qualityPicID;
        }

        public void setQualityPicID(int qualityPicID) {
            this.qualityPicID = qualityPicID;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getQualityID() {
            return qualityID;
        }

        public void setQualityID(int qualityID) {
            this.qualityID = qualityID;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
