package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/25.
 */
public class PickupInfo {

    /**
     * listOrder : [{"orderID":235,"orderCode":"170922050357092260","orderTime":null,"cabinetID":20,"appId":null,"freshCabinet":{"cabinetID":20,"cabinetName":"生鲜柜二号","longitude":113.957663,"latitude":22.582366,"address":"广东省深圳市南山区阳光大道146号生鲜柜二号","used":0},"totalPageNumber":0,"nowPageNumber":0}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private String shareURL;
    private List<ListOrderBean> listOrder;

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
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

    public List<ListOrderBean> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<ListOrderBean> listOrder) {
        this.listOrder = listOrder;
    }

    public static class ListOrderBean {
        /**
         * orderID : 235
         * orderCode : 170922050357092260
         * orderTime : null
         * cabinetID : 20
         * appId : null
         * freshCabinet : {"cabinetID":20,"cabinetName":"生鲜柜二号","longitude":113.957663,"latitude":22.582366,"address":"广东省深圳市南山区阳光大道146号生鲜柜二号","used":0}
         * totalPageNumber : 0
         * nowPageNumber : 0
         */

        private int orderID;
        private String orderCode;
        private Object orderTime;
        private int cabinetID;
        private Object appId;
        private FreshCabinetBean freshCabinet;
        private int totalPageNumber;
        private int nowPageNumber;

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

        public int getCabinetID() {
            return cabinetID;
        }

        public void setCabinetID(int cabinetID) {
            this.cabinetID = cabinetID;
        }

        public Object getAppId() {
            return appId;
        }

        public void setAppId(Object appId) {
            this.appId = appId;
        }

        public FreshCabinetBean getFreshCabinet() {
            return freshCabinet;
        }

        public void setFreshCabinet(FreshCabinetBean freshCabinet) {
            this.freshCabinet = freshCabinet;
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

        public static class FreshCabinetBean {
            /**
             * cabinetID : 20
             * cabinetName : 生鲜柜二号
             * longitude : 113.957663
             * latitude : 22.582366
             * address : 广东省深圳市南山区阳光大道146号生鲜柜二号
             * used : 0
             */

            private int cabinetID;
            private String cabinetName;
            private double longitude;
            private double latitude;
            private String address;
            private int used;

            public int getCabinetID() {
                return cabinetID;
            }

            public void setCabinetID(int cabinetID) {
                this.cabinetID = cabinetID;
            }

            public String getCabinetName() {
                return cabinetName;
            }

            public void setCabinetName(String cabinetName) {
                this.cabinetName = cabinetName;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getUsed() {
                return used;
            }

            public void setUsed(int used) {
                this.used = used;
            }
        }
    }
}
