package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/14.
 * 查询优惠券列表
 */

public class RequestQueryCouponList {
    private int couponStatus;//优惠券状态（0未使用1已使用-1已过期）

    public RequestQueryCouponList(int couponStatus) {
        this.couponStatus = couponStatus;
    }

    public int getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(int couponStatus) {
        this.couponStatus = couponStatus;
    }
}
