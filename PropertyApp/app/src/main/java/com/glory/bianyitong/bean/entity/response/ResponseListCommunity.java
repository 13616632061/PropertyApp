package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ResponseListCommunity extends BaseResponseBean {


    private List<ListCommunityBean> listCommunity;

    public List<ListCommunityBean> getListCommunity() {
        return listCommunity;
    }

    public void setListCommunity(List<ListCommunityBean> listCommunity) {
        this.listCommunity = listCommunity;
    }

    public static class ListCommunityBean {
        /**
         * communityID : 1
         * communityName : 西丽小区
         * provinceID : 440000
         * cityID : 440300
         * districtID : 440305
         * longitude : 113.957228
         * latitude : 22.582978
         * provinceName : 广东省
         * cityName : 深圳市
         * districtName : 南山区
         * street : 西丽大街
         * addressNumber : 15号
         * postCode : 546987
         */

        private int communityID;
        private String communityName;
        private int provinceID;
        private int cityID;
        private int districtID;
        private double longitude;
        private double latitude;
        private String provinceName;
        private String cityName;
        private String districtName;
        private String street;
        private String addressNumber;
        private int postCode;

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public int getDistrictID() {
            return districtID;
        }

        public void setDistrictID(int districtID) {
            this.districtID = districtID;
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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getAddressNumber() {
            return addressNumber;
        }

        public void setAddressNumber(String addressNumber) {
            this.addressNumber = addressNumber;
        }

        public int getPostCode() {
            return postCode;
        }

        public void setPostCode(int postCode) {
            this.postCode = postCode;
        }
    }
}
