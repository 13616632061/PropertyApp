package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 获取周边社区
 */

public class RequestLocalAreaBean {
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public RequestLocalAreaBean(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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

    private double latitude;
}
