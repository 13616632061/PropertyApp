package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 * 查询优惠券列表
 */

public class ResponseQueryCouponList extends BaseResponseBean implements Serializable{

    /**
     * receiveID : 0
     * listCouponReceive : [{"receiveID":6,"couponID":8,"receiveDateTime":"2017-09-18T00:00:00","couponStatus":0,"isDelete":0,"beginDate":"2017-09-18T00:00:00","endDate":"2017-10-08T00:00:00","receiveNo":"0","coupon":{"couponID":8,"couponName":"平台1","merchantID":0,"startFee":0,"freeMoney":2,"commodityRange":1,"platFormType":1,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0},"dateNow":"2017-09-21T14:26:10"}]
     */

    private int receiveID;
    private List<ListCouponReceiveBean> listCouponReceive;

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public List<ListCouponReceiveBean> getListCouponReceive() {
        return listCouponReceive;
    }

    public void setListCouponReceive(List<ListCouponReceiveBean> listCouponReceive) {
        this.listCouponReceive = listCouponReceive;
    }

    public static class ListCouponReceiveBean {
        /**
         * receiveID : 6
         * couponID : 8
         * receiveDateTime : 2017-09-18T00:00:00
         * couponStatus : 0
         * isDelete : 0
         * beginDate : 2017-09-18T00:00:00
         * endDate : 2017-10-08T00:00:00
         * receiveNo : 0
         * coupon : {"couponID":8,"couponName":"平台1","merchantID":0,"startFee":0,"freeMoney":2,"commodityRange":1,"platFormType":1,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}
         * dateNow : 2017-09-21T14:26:10
         */

        private int receiveID;
        private int couponID;
        private String receiveDateTime;
        private int couponStatus;
        private int isDelete;
        private String beginDate;
        private String endDate;
        private String receiveNo;
        private CouponBean coupon;
        private String dateNow;

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

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
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

        public String getReceiveNo() {
            return receiveNo;
        }

        public void setReceiveNo(String receiveNo) {
            this.receiveNo = receiveNo;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public String getDateNow() {
            return dateNow;
        }

        public void setDateNow(String dateNow) {
            this.dateNow = dateNow;
        }

        public static class CouponBean {
            /**
             * couponID : 8
             * couponName : 平台1
             * merchantID : 0
             * startFee : 0
             * freeMoney : 2
             * commodityRange : 1
             * platFormType : 1
             * receivestatu : false
             * totalPageNumber : 0
             * nowPageNumber : 0
             * pageRowCount : 0
             */

            private int couponID;
            private String couponName;
            private int merchantID;
            private int startFee;
            private int freeMoney;
            private int commodityRange;
            private int platFormType;
            private boolean receivestatu;
            private int totalPageNumber;
            private int nowPageNumber;
            private int pageRowCount;

            public int getCouponID() {
                return couponID;
            }

            public void setCouponID(int couponID) {
                this.couponID = couponID;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public int getMerchantID() {
                return merchantID;
            }

            public void setMerchantID(int merchantID) {
                this.merchantID = merchantID;
            }

            public int getStartFee() {
                return startFee;
            }

            public void setStartFee(int startFee) {
                this.startFee = startFee;
            }

            public int getFreeMoney() {
                return freeMoney;
            }

            public void setFreeMoney(int freeMoney) {
                this.freeMoney = freeMoney;
            }

            public int getCommodityRange() {
                return commodityRange;
            }

            public void setCommodityRange(int commodityRange) {
                this.commodityRange = commodityRange;
            }

            public int getPlatFormType() {
                return platFormType;
            }

            public void setPlatFormType(int platFormType) {
                this.platFormType = platFormType;
            }

            public boolean isReceivestatu() {
                return receivestatu;
            }

            public void setReceivestatu(boolean receivestatu) {
                this.receivestatu = receivestatu;
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

            public int getPageRowCount() {
                return pageRowCount;
            }

            public void setPageRowCount(int pageRowCount) {
                this.pageRowCount = pageRowCount;
            }
        }
    }
}
