package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/22.
 */
public class LogisticsInfo {

    /**
     * orderID : 0
     * order : {"orderID":229,"orderCode":"1709211104301762266","orderTime":null,"orderStatus":0,"appId":null,"totalPageNumber":0,"nowPageNumber":0,"listStatusRecord":[{"recordID":102,"orderID":229,"recordDate":"2017-09-21T11:04:30","orderStatus":0,"remark":"提交订单","explain":"提交订单"}],"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/牛肉/1.jpg"}
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int orderID;
    private OrderBean order;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

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
         * orderID : 229
         * orderCode : 1709211104301762266
         * orderTime : null
         * orderStatus : 0
         * appId : null
         * totalPageNumber : 0
         * nowPageNumber : 0
         * listStatusRecord : [{"recordID":102,"orderID":229,"recordDate":"2017-09-21T11:04:30","orderStatus":0,"remark":"提交订单","explain":"提交订单"}]
         * freshPicture : https://byt.bytsz.com.cn/images/Fresh/牛肉/1.jpg
         */

        private int orderID;
        private String orderCode;
        private Object orderTime;
        private int orderStatus;
        private Object appId;
        private int totalPageNumber;
        private int nowPageNumber;
        private String freshPicture;
        private List<ListStatusRecordBean> listStatusRecord;

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public Object getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Object orderTime) {
            this.orderTime = orderTime;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Object getAppId() {
            return appId;
        }

        public void setAppId(Object appId) {
            this.appId = appId;
        }

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

        public String getFreshPicture() {
            return freshPicture;
        }

        public void setFreshPicture(String freshPicture) {
            this.freshPicture = freshPicture;
        }

        public List<ListStatusRecordBean> getListStatusRecord() {
            return listStatusRecord;
        }

        public void setListStatusRecord(List<ListStatusRecordBean> listStatusRecord) {
            this.listStatusRecord = listStatusRecord;
        }

        public static class ListStatusRecordBean {
            /**
             * recordID : 102
             * orderID : 229
             * recordDate : 2017-09-21T11:04:30
             * orderStatus : 0
             * remark : 提交订单
             * explain : 提交订单
             */

            private int recordID;
            private int orderID;
            private String recordDate;
            private int orderStatus;
            private String remark;
            private String explain;

            public int getRecordID() {
                return recordID;
            }

            public void setRecordID(int recordID) {
                this.recordID = recordID;
            }

            public int getOrderID() {
                return orderID;
            }

            public void setOrderID(int orderID) {
                this.orderID = orderID;
            }

            public String getRecordDate() {
                return recordDate;
            }

            public void setRecordDate(String recordDate) {
                this.recordDate = recordDate;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }
        }
    }
}
