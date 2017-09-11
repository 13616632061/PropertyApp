package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/11.
 */
public class AllEvaluationInfo {

    /**
     * listFreshEvaluation : [{"evaluationID":17,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:14:26","endEvaluationDateTime":null,"anonymous":"A**       ","listEvaluationPic":[]},{"evaluationID":14,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:11:52","endEvaluationDateTime":null,"anonymous":"A**       ","listEvaluationPic":[]},{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}]},{"evaluationID":13,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[]}]
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
         * evaluationID : 17
         * orderID : 35
         * freshID : 11
         * merchant_ID : 22
         * evaluationContext : 东西还可以，中评！
         * evaluationLevel : 0
         * evaluationDateTime : 2017-07-06T14:14:26
         * endEvaluationDateTime : null
         * anonymous : A**
         * listEvaluationPic : []
         * user : {"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"}
         */

        private int evaluationID;
        private int orderID;
        private int freshID;
        private int merchant_ID;
        private String evaluationContext;
        private int evaluationLevel;
        private String evaluationDateTime;
        private Object endEvaluationDateTime;
        private String anonymous;
        private UserBean user;
        private List<?> listEvaluationPic;

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

        public String getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(String anonymous) {
            this.anonymous = anonymous;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<?> getListEvaluationPic() {
            return listEvaluationPic;
        }

        public void setListEvaluationPic(List<?> listEvaluationPic) {
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
    }
}
