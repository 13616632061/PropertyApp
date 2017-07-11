package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 * 查询快递柜
 */

public class ResponseQueryExpressBar extends BaseResponseBean {

    /**
     * addressID : 0
     * listShippingAddress : null
     * listFreshCabinet : [{"cabinetID":13,"cabinetName":"生鲜柜一号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1217.96954128948,"listFreshCabinetDetail":null,"listArea":null},{"cabinetID":20,"cabinetName":"生鲜柜二号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1194.84441126823,"listFreshCabinetDetail":null,"listArea":null},{"cabinetID":21,"cabinetName":"生鲜柜三号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1159.9948921839,"listFreshCabinetDetail":null,"listArea":null},{"cabinetID":22,"cabinetName":"生鲜柜四号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1246.2272346316,"listFreshCabinetDetail":null,"listArea":null},{"cabinetID":23,"cabinetName":"生鲜柜五号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1257.5277170153,"listFreshCabinetDetail":null,"listArea":null},{"cabinetID":24,"cabinetName":"生鲜柜六号","area_Name":null,"provinceName":null,"cityName":null,"districtName":null,"distance":1239.14113732729,"listFreshCabinetDetail":null,"listArea":null}]
     */

    private int addressID;
    private Object listShippingAddress;
    private List<ListFreshCabinetBean> listFreshCabinet;

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public Object getListShippingAddress() {
        return listShippingAddress;
    }

    public void setListShippingAddress(Object listShippingAddress) {
        this.listShippingAddress = listShippingAddress;
    }

    public List<ListFreshCabinetBean> getListFreshCabinet() {
        return listFreshCabinet;
    }

    public void setListFreshCabinet(List<ListFreshCabinetBean> listFreshCabinet) {
        this.listFreshCabinet = listFreshCabinet;
    }

    public static class ListFreshCabinetBean {
        /**
         * cabinetID : 13
         * cabinetName : 生鲜柜一号
         * area_Name : null
         * provinceName : null
         * cityName : null
         * districtName : null
         * distance : 1217.96954128948
         * listFreshCabinetDetail : null
         * listArea : null
         */

        private int cabinetID;
        private String cabinetName;
        private Object area_Name;
        private Object provinceName;
        private Object cityName;
        private Object districtName;
        private double distance;
        private Object listFreshCabinetDetail;
        private Object listArea;

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

        public Object getArea_Name() {
            return area_Name;
        }

        public void setArea_Name(Object area_Name) {
            this.area_Name = area_Name;
        }

        public Object getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(Object provinceName) {
            this.provinceName = provinceName;
        }

        public Object getCityName() {
            return cityName;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public Object getDistrictName() {
            return districtName;
        }

        public void setDistrictName(Object districtName) {
            this.districtName = districtName;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public Object getListFreshCabinetDetail() {
            return listFreshCabinetDetail;
        }

        public void setListFreshCabinetDetail(Object listFreshCabinetDetail) {
            this.listFreshCabinetDetail = listFreshCabinetDetail;
        }

        public Object getListArea() {
            return listArea;
        }

        public void setListArea(Object listArea) {
            this.listArea = listArea;
        }
    }
}
