package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/30.
 * 广告查询
 */

public class RequestAdvertising {
    public RequestAdvertising(int advertisingLocation) {
        this.advertisingLocation = advertisingLocation;
    }

    private int advertisingLocation;//广告位置

    public int getAdvertisingLocation() {
        return advertisingLocation;
    }

    public void setAdvertisingLocation(int advertisingLocation) {
        this.advertisingLocation = advertisingLocation;
    }
}
