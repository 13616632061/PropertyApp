package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/9/22.
 */
public class GetCouponInfo {

    /**
     * couponReceive : {"couponID":8}
     */

    private CouponReceiveBean couponReceive=new CouponReceiveBean();

    public CouponReceiveBean getCouponReceive() {
        return couponReceive;
    }

    public void setCouponReceive(CouponReceiveBean couponReceive) {
        this.couponReceive = couponReceive;
    }

    public static class CouponReceiveBean {
        /**
         * couponID : 8
         */

        private int couponID;

        public int getCouponID() {
            return couponID;
        }

        public void setCouponID(int couponID) {
            this.couponID = couponID;
        }
    }
}
