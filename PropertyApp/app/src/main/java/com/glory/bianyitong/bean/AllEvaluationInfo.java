package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/11.
 */
public class AllEvaluationInfo {


    /**
     * listFreshEvaluation : [{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}]}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 4
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListFreshEvaluationBean> listFreshEvaluation;

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

    public List<ListFreshEvaluationBean> getListFreshEvaluation() {
        return listFreshEvaluation;
    }

    public void setListFreshEvaluation(List<ListFreshEvaluationBean> listFreshEvaluation) {
        this.listFreshEvaluation = listFreshEvaluation;
    }

    public static class ListFreshEvaluationBean {
        /**
         * evaluationID : 6
         * orderID : 1
         * freshID : 11
         * merchant_ID : 22
         * evaluationContext :
         * evaluationLevel : 4
         * evaluationDateTime : 2017-06-30T00:00:00
         * endEvaluationDateTime : null
         * user : {"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"}
         * listEvaluationPic : [{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}]
         */

        private int evaluationID;
        private int orderID;
        private int freshID;
        private int merchant_ID;
        private String evaluationContext;
        private int evaluationLevel;
        private String evaluationDateTime;
        private Object endEvaluationDateTime;
        private UserBean user;
        private List<ListEvaluationPicBean> listEvaluationPic;
        private String loginName;
        private String customerPhoto;

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

        public int getEvaluationID() {
            return evaluationID;
        }

        public void setEvaluationID(int evaluationID) {
            this.evaluationID = evaluationID;
        }

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public int getFreshID() {
            return freshID;
        }

        public void setFreshID(int freshID) {
            this.freshID = freshID;
        }

        public int getMerchant_ID() {
            return merchant_ID;
        }

        public void setMerchant_ID(int merchant_ID) {
            this.merchant_ID = merchant_ID;
        }

        public String getEvaluationContext() {
            return evaluationContext;
        }

        public void setEvaluationContext(String evaluationContext) {
            this.evaluationContext = evaluationContext;
        }

        public int getEvaluationLevel() {
            return evaluationLevel;
        }

        public void setEvaluationLevel(int evaluationLevel) {
            this.evaluationLevel = evaluationLevel;
        }

        public String getEvaluationDateTime() {
            return evaluationDateTime;
        }

        public void setEvaluationDateTime(String evaluationDateTime) {
            this.evaluationDateTime = evaluationDateTime;
        }

        public Object getEndEvaluationDateTime() {
            return endEvaluationDateTime;
        }

        public void setEndEvaluationDateTime(Object endEvaluationDateTime) {
            this.endEvaluationDateTime = endEvaluationDateTime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<ListEvaluationPicBean> getListEvaluationPic() {
            return listEvaluationPic;
        }

        public void setListEvaluationPic(List<ListEvaluationPicBean> listEvaluationPic) {
            this.listEvaluationPic = listEvaluationPic;
        }

        public static class UserBean {
            /**
             * jgPushID : null
             * jgPushName : null
             * status : null
             * loginName : A
             * customerPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
             */

            private Object jgPushID;
            private Object jgPushName;
            private Object status;
            private String loginName;
            private String customerPhoto;

            public Object getJgPushID() {
                return jgPushID;
            }

            public void setJgPushID(Object jgPushID) {
                this.jgPushID = jgPushID;
            }

            public Object getJgPushName() {
                return jgPushName;
            }

            public void setJgPushName(Object jgPushName) {
                this.jgPushName = jgPushName;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
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
        }

        public static class ListEvaluationPicBean {
            /**
             * pic_ID : 1
             * evaluationID : 6
             * picturePath : http
             */

            private int pic_ID;
            private int evaluationID;
            private String picturePath;

            public int getPic_ID() {
                return pic_ID;
            }

            public void setPic_ID(int pic_ID) {
                this.pic_ID = pic_ID;
            }

            public int getEvaluationID() {
                return evaluationID;
            }

            public void setEvaluationID(int evaluationID) {
                this.evaluationID = evaluationID;
            }

            public String getPicturePath() {
                return picturePath;
            }

            public void setPicturePath(String picturePath) {
                this.picturePath = picturePath;
            }
        }
    }
}
