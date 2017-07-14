package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 * 查询优惠券列表
 */

public class ResponseQueryCouponList extends BaseResponseBean {

    /**
     * receiveID : 0
     * couponReceive : {"notused":2,"used":1,"timeout":1}
     * listCouponReceive : [{"receiveID":2,"couponID":4,"receiveDateTime":"2017-06-27T17:31:25","couponStatus":0,"coupon":{"couponID":4,"couponType":3,"quota":2,"minimumAmount":4,"beginDate":"2017-06-20T00:00:00","endDate":"2017-07-30T00:00:00","freeType":2,"freeMoney":2,"freeHighestPrice":10,"publishDateTime":"2017-06-10T00:00:00","couponNumber":10}},{"receiveID":3,"couponID":5,"receiveDateTime":"2017-06-27T17:30:46","couponStatus":0,"coupon":{"couponID":5,"couponType":1,"quota":2,"minimumAmount":4,"beginDate":"2017-06-20T00:00:00","endDate":"2017-07-30T00:00:00","freeType":2,"freeMoney":2,"freeHighestPrice":10,"publishDateTime":"2017-06-10T00:00:00","couponNumber":10}}]
     */

    private int receiveID;
    private CouponReceiveBean couponReceive;
    private List<ListCouponReceiveBean> listCouponReceive;

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public CouponReceiveBean getCouponReceive() {
        return couponReceive;
    }

    public void setCouponReceive(CouponReceiveBean couponReceive) {
        this.couponReceive = couponReceive;
    }

    public List<ListCouponReceiveBean> getListCouponReceive() {
        return listCouponReceive;
    }

    public void setListCouponReceive(List<ListCouponReceiveBean> listCouponReceive) {
        this.listCouponReceive = listCouponReceive;
    }

    public static class CouponReceiveBean {
        /**
         * notused : 2
         * used : 1
         * timeout : 1
         */

        private int notused;
        private int used;
        private int timeout;

        public int getNotused() {
            return notused;
        }

        public void setNotused(int notused) {
            this.notused = notused;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }

    public static class ListCouponReceiveBean {
        /**
         * receiveID : 2
         * couponID : 4
         * receiveDateTime : 2017-06-27T17:31:25
         * couponStatus : 0
         * coupon : {"couponID":4,"couponType":3,"quota":2,"minimumAmount":4,"beginDate":"2017-06-20T00:00:00","endDate":"2017-07-30T00:00:00","freeType":2,"freeMoney":2,"freeHighestPrice":10,"publishDateTime":"2017-06-10T00:00:00","couponNumber":10}
         */

        private int receiveID;
        private int couponID;
        private String receiveDateTime;
        private int couponStatus;
        private CouponBean coupon;

        public int getReceiveID() {
            return receiveID;
        }

        public void setReceiveID(int receiveID) {
            this.receiveID = receiveID;
        }

        public int getCouponID() {
            return couponID;
        }

        public void setCouponID(int couponID) {
            this.couponID = couponID;
        }

        public String getReceiveDateTime() {
            return receiveDateTime;
        }

        public void setReceiveDateTime(String receiveDateTime) {
            this.receiveDateTime = receiveDateTime;
        }

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * couponID : 4
             * couponType : 3
             * quota : 2
             * minimumAmount : 4.0
             * beginDate : 2017-06-20T00:00:00
             * endDate : 2017-07-30T00:00:00
             * freeType : 2
             * freeMoney : 2.0
             * freeHighestPrice : 10.0
             * publishDateTime : 2017-06-10T00:00:00
             * couponNumber : 10
             */

            private int couponID;
            private int couponType;
            private int quota;
            private double minimumAmount;
            private String beginDate;
            private String endDate;
            private int freeType;
            private double freeMoney;
            private double freeHighestPrice;
            private String publishDateTime;
            private int couponNumber;
            private String couponName;//优惠券名称

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public int getCouponID() {
                return couponID;
            }

            public void setCouponID(int couponID) {
                this.couponID = couponID;
            }

            public int getCouponType() {
                return couponType;
            }

            public void setCouponType(int couponType) {
                this.couponType = couponType;
            }

            public int getQuota() {
                return quota;
            }

            public void setQuota(int quota) {
                this.quota = quota;
            }

            public double getMinimumAmount() {
                return minimumAmount;
            }

            public void setMinimumAmount(double minimumAmount) {
                this.minimumAmount = minimumAmount;
            }

            public String getBeginDate() {
                return beginDate;
            }

            public void setBeginDate(String beginDate) {
                this.beginDate = beginDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public int getFreeType() {
                return freeType;
            }

            public void setFreeType(int freeType) {
                this.freeType = freeType;
            }

            public double getFreeMoney() {
                return freeMoney;
            }

            public void setFreeMoney(double freeMoney) {
                this.freeMoney = freeMoney;
            }

            public double getFreeHighestPrice() {
                return freeHighestPrice;
            }

            public void setFreeHighestPrice(double freeHighestPrice) {
                this.freeHighestPrice = freeHighestPrice;
            }

            public String getPublishDateTime() {
                return publishDateTime;
            }

            public void setPublishDateTime(String publishDateTime) {
                this.publishDateTime = publishDateTime;
            }

            public int getCouponNumber() {
                return couponNumber;
            }

            public void setCouponNumber(int couponNumber) {
                this.couponNumber = couponNumber;
            }
        }
    }
}
