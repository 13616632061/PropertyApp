package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/11.
 * 查询附近生鲜柜
 */

public class RequestQueryExpressBarByKeyWord {

    private  KeyWord freshCabinet;

    public RequestQueryExpressBarByKeyWord(KeyWord freshCabinet) {
        this.freshCabinet = freshCabinet;
    }

    public KeyWord getFreshCabinet() {
        return freshCabinet;
    }

    public void setFreshCabinet(KeyWord freshCabinet) {
        this.freshCabinet = freshCabinet;
    }

    public static class KeyWord{
        private String streetAddress;

        public KeyWord(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }
    }
}
