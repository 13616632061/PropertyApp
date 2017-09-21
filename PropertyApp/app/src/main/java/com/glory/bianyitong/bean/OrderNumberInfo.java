package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/9/21.
 */
public class OrderNumberInfo {

    /**
     * order : {"totalPageNumber":0,"nowPageNumber":0,"paid":1,"shipped":0,"received":0,"evaluation":0,"refund":0,"cartNum":2}
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 1
     */

    private OrderBean order;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
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

    public static class OrderBean {
        /**
         * totalPageNumber : 0
         * nowPageNumber : 0
         * paid : 1
         * shipped : 0
         * received : 0
         * evaluation : 0
         * refund : 0
         * cartNum : 2
         */

        private int totalPageNumber;
        private int nowPageNumber;
        private int paid;
        private int shipped;
        private int received;
        private int evaluation;
        private int refund;
        private int cartNum;

        public int getTotalPageNumber() {
            return totalPageNumber;
        }

        public void setTotalPageNumber(int totalPageNumber) {
            this.totalPageNumber = totalPageNumber;
        }

        public int getNowPageNumber() {
            return nowPageNumber;
        }

        public void setNowPageNumber(int nowPageNumber) {
            this.nowPageNumber = nowPageNumber;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

        public int getShipped() {
            return shipped;
        }

        public void setShipped(int shipped) {
            this.shipped = shipped;
        }

        public int getReceived() {
            return received;
        }

        public void setReceived(int received) {
            this.received = received;
        }

        public int getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(int evaluation) {
            this.evaluation = evaluation;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }
    }
}
