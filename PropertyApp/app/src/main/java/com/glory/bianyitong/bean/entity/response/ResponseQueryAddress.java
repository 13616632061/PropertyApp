package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 * 查询地址列表
 */

public class ResponseQueryAddress extends BaseResponseBean {

    /**
     * addressID : 0
     * listShippingAddress : [{"addressID":9,"cabinetID":13,"cabinetName":"生鲜柜一号","defaults":true,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":13,"cabinetName":"生鲜柜一号","area_ID":18,"area_Name":"龙岗区","longitude":113.957228,"latitude":22.582978,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440305,"districtName":"南山区","streetAddress":"what?","row":2,"columns":2,"listFreshCabinetDetail":null,"listArea":null}},{"addressID":10,"cabinetID":20,"cabinetName":"生鲜柜二号","defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":20,"cabinetName":"生鲜柜二号","area_ID":18,"area_Name":"龙岗区","longitude":113.957663,"latitude":22.582366,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440305,"districtName":"南山区","streetAddress":"waht?","row":2,"columns":2,"listFreshCabinetDetail":null,"listArea":null}},{"addressID":11,"cabinetID":21,"cabinetName":"生鲜柜三号","defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":21,"cabinetName":"生鲜柜三号","area_ID":18,"area_Name":"龙岗区","longitude":113.957822,"latitude":22.58295,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440306,"districtName":"宝安区","streetAddress":"2","row":1,"columns":2,"listFreshCabinetDetail":null,"listArea":null}},{"addressID":12,"cabinetID":24,"cabinetName":"生鲜柜六号","defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":24,"cabinetName":"生鲜柜六号","area_ID":18,"area_Name":"龙岗区","longitude":113.957143,"latitude":22.582559,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440307,"districtName":"龙岗区","streetAddress":"what？","row":1,"columns":2,"listFreshCabinetDetail":null,"listArea":null}},{"addressID":13,"cabinetID":22,"cabinetName":"生鲜柜四号","defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":22,"cabinetName":"生鲜柜四号","area_ID":18,"area_Name":"龙岗区","longitude":113.957114,"latitude":22.58243,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440307,"districtName":"龙岗区","streetAddress":"what?","row":10,"columns":1,"listFreshCabinetDetail":null,"listArea":null}}]
     */

    private int addressID;
    private List<ListShippingAddressBean> listShippingAddress;

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public List<ListShippingAddressBean> getListShippingAddress() {
        return listShippingAddress;
    }

    public void setListShippingAddress(List<ListShippingAddressBean> listShippingAddress) {
        this.listShippingAddress = listShippingAddress;
    }

    public static class ListShippingAddressBean {
        /**
         * addressID : 9
         * cabinetID : 13
         * cabinetName : 生鲜柜一号
         * defaults : true
         * harvesterName : 测试用户
         * harvestePhone : 13510012206
         * freshCabinet : {"cabinetID":13,"cabinetName":"生鲜柜一号","area_ID":18,"area_Name":"龙岗区","longitude":113.957228,"latitude":22.582978,"provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440305,"districtName":"南山区","streetAddress":"what?","row":2,"columns":2,"listFreshCabinetDetail":null,"listArea":null}
         */

        private int addressID;
        private int cabinetID;
        private String cabinetName;
        private boolean defaults;
        private String harvesterName;
        private String harvestePhone;
        private FreshCabinetBean freshCabinet;

        public int getAddressID() {
            return addressID;
        }

        public void setAddressID(int addressID) {
            this.addressID = addressID;
        }

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

        public boolean isDefaults() {
            return defaults;
        }

        public void setDefaults(boolean defaults) {
            this.defaults = defaults;
        }

        public String getHarvesterName() {
            return harvesterName;
        }

        public void setHarvesterName(String harvesterName) {
            this.harvesterName = harvesterName;
        }

        public String getHarvestePhone() {
            return harvestePhone;
        }

        public void setHarvestePhone(String harvestePhone) {
            this.harvestePhone = harvestePhone;
        }

        public FreshCabinetBean getFreshCabinet() {
            return freshCabinet;
        }

        public void setFreshCabinet(FreshCabinetBean freshCabinet) {
            this.freshCabinet = freshCabinet;
        }

        public static class FreshCabinetBean {
            /**
             * cabinetID : 13
             * cabinetName : 生鲜柜一号
             * area_ID : 18
             * area_Name : 龙岗区
             * longitude : 113.957228
             * latitude : 22.582978
             * provinceID : 440000
             * provinceName : 广东省
             * cityID : 440300
             * cityName : 深圳市
             * districtID : 440305
             * districtName : 南山区
             * streetAddress : what?
             * row : 2
             * columns : 2
             * listFreshCabinetDetail : null
             * listArea : null
             */

            private int cabinetID;
            private String cabinetName;
            private int area_ID;
            private String area_Name;
            private double longitude;
            private double latitude;
            private int provinceID;
            private String provinceName;
            private int cityID;
            private String cityName;
            private int districtID;
            private String districtName;
            private String streetAddress;
            private int row;
            private int columns;
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

            public int getArea_ID() {
                return area_ID;
            }

            public void setArea_ID(int area_ID) {
                this.area_ID = area_ID;
            }

            public String getArea_Name() {
                return area_Name;
            }

            public void setArea_Name(String area_Name) {
                this.area_Name = area_Name;
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

            public int getProvinceID() {
                return provinceID;
            }

            public void setProvinceID(int provinceID) {
                this.provinceID = provinceID;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public int getDistrictID() {
                return districtID;
            }

            public void setDistrictID(int districtID) {
                this.districtID = districtID;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public String getStreetAddress() {
                return streetAddress;
            }

            public void setStreetAddress(String streetAddress) {
                this.streetAddress = streetAddress;
            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public int getColumns() {
                return columns;
            }

            public void setColumns(int columns) {
                this.columns = columns;
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
}
