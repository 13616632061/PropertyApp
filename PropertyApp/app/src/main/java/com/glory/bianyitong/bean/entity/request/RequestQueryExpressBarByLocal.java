package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/11.
 * 查询附近生鲜柜
 */

public class RequestQueryExpressBarByLocal {

    private  Local freshCabinet;

    public RequestQueryExpressBarByLocal(Local freshCabinet) {
        this.freshCabinet = freshCabinet;
    }

    public Local getFreshCabinet() {
        return freshCabinet;
    }

    public void setFreshCabinet(Local freshCabinet) {
        this.freshCabinet = freshCabinet;
    }

    public static class Local{
        private double longitude;//经度
        private double latitude;//维度

        public Local(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
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
    }
}
