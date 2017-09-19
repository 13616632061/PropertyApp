package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 */
public class FreshTypeInfo extends BaseResponseBean{

    /**
     * listFreshType : [{"freshTypeID":3,"freshTypeName":"四季鲜果","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":6,"freshTypeName":"蔬菜蛋类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":7,"freshTypeName":"海鲜水产","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":8,"freshTypeName":"精选肉类","freshTypeLeaf":8,"merchant_ID":22},{"freshTypeID":96,"freshTypeName":"蛋类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":97,"freshTypeName":"叶菜类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":100,"freshTypeName":"根茎类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":102,"freshTypeName":"茄果瓜类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":103,"freshTypeName":"菌类","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":106,"freshTypeName":"葱姜蒜椒","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":107,"freshTypeName":"鱼类","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":108,"freshTypeName":"虾类","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":109,"freshTypeName":"蟹类","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":111,"freshTypeName":"贝类","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":112,"freshTypeName":"猪肉","freshTypeLeaf":8,"merchant_ID":22},{"freshTypeID":113,"freshTypeName":"牛肉","freshTypeLeaf":8,"merchant_ID":22},{"freshTypeID":114,"freshTypeName":"羊肉","freshTypeLeaf":8,"merchant_ID":22},{"freshTypeID":115,"freshTypeName":"禽类","freshTypeLeaf":8,"merchant_ID":22},{"freshTypeID":116,"freshTypeName":"浆果类","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":117,"freshTypeName":"柑橘类","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":119,"freshTypeName":"瓜类","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":120,"freshTypeName":"其它","freshTypeLeaf":3,"merchant_ID":22}]
     * freshCabinet : {"cabinetID":24,"cabinetName":"生鲜柜六号","area_ID":null,"area_Name":null,"longitude":113.957143,"latitude":22.582559,"provinceID":null,"provinceName":null,"cityID":null,"cityName":null,"districtID":null,"districtName":null,"streetAddress":null,"row":null,"columns":null,"communityID":3,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"123","lockPassword":"123","num":48,"used":0,"listFreshCabinetDetail":null,"listArea":null,"community":{"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"street":"留仙大道","addressNumber":"145号","postCode":123456}}
     * shoppingCartCount : 0
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private FreshCabinetBean freshCabinet;
    private int shoppingCartCount;
    private List<ListFreshTypeBean> listFreshType;

    public FreshCabinetBean getFreshCabinet() {
        return freshCabinet;
    }

    public void setFreshCabinet(FreshCabinetBean freshCabinet) {
        this.freshCabinet = freshCabinet;
    }

    public int getShoppingCartCount() {
        return shoppingCartCount;
    }

    public void setShoppingCartCount(int shoppingCartCount) {
        this.shoppingCartCount = shoppingCartCount;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageRowNumber() {
        return pageRowNumber;
    }

    public void setPageRowNumber(int pageRowNumber) {
        this.pageRowNumber = pageRowNumber;
    }

    public List<ListFreshTypeBean> getListFreshType() {
        return listFreshType;
    }

    public void setListFreshType(List<ListFreshTypeBean> listFreshType) {
        this.listFreshType = listFreshType;
    }

    public static class FreshCabinetBean {
        /**
         * cabinetID : 24
         * cabinetName : 生鲜柜六号
         * area_ID : null
         * area_Name : null
         * longitude : 113.957143
         * latitude : 22.582559
         * provinceID : null
         * provinceName : null
         * cityID : null
         * cityName : null
         * districtID : null
         * districtName : null
         * streetAddress : null
         * row : null
         * columns : null
         * communityID : 3
         * createDate : 2017-08-17T00:00:00
         * createUserID : 1
         * lockAccount : 123
         * lockPassword : 123
         * num : 48
         * used : 0
         * listFreshCabinetDetail : null
         * listArea : null
         * community : {"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"street":"留仙大道","addressNumber":"145号","postCode":123456}
         */

        private int cabinetID;
        private String cabinetName;
        private Object area_ID;
        private Object area_Name;
        private double longitude;
        private double latitude;
        private Object provinceID;
        private Object provinceName;
        private Object cityID;
        private Object cityName;
        private Object districtID;
        private Object districtName;
        private Object streetAddress;
        private Object row;
        private Object columns;
        private int communityID;
        private String createDate;
        private int createUserID;
        private String lockAccount;
        private String lockPassword;
        private int num;
        private int used;
        private Object listFreshCabinetDetail;
        private Object listArea;
        private CommunityBean community;
        private String communityName;

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
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

        public Object getArea_ID() {
            return area_ID;
        }

        public void setArea_ID(Object area_ID) {
            this.area_ID = area_ID;
        }

        public Object getArea_Name() {
            return area_Name;
        }

        public void setArea_Name(Object area_Name) {
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

        public Object getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(Object provinceID) {
            this.provinceID = provinceID;
        }

        public Object getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(Object provinceName) {
            this.provinceName = provinceName;
        }

        public Object getCityID() {
            return cityID;
        }

        public void setCityID(Object cityID) {
            this.cityID = cityID;
        }

        public Object getCityName() {
            return cityName;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public Object getDistrictID() {
            return districtID;
        }

        public void setDistrictID(Object districtID) {
            this.districtID = districtID;
        }

        public Object getDistrictName() {
            return districtName;
        }

        public void setDistrictName(Object districtName) {
            this.districtName = districtName;
        }

        public Object getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(Object streetAddress) {
            this.streetAddress = streetAddress;
        }

        public Object getRow() {
            return row;
        }

        public void setRow(Object row) {
            this.row = row;
        }

        public Object getColumns() {
            return columns;
        }

        public void setColumns(Object columns) {
            this.columns = columns;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getCreateUserID() {
            return createUserID;
        }

        public void setCreateUserID(int createUserID) {
            this.createUserID = createUserID;
        }

        public String getLockAccount() {
            return lockAccount;
        }

        public void setLockAccount(String lockAccount) {
            this.lockAccount = lockAccount;
        }

        public String getLockPassword() {
            return lockPassword;
        }

        public void setLockPassword(String lockPassword) {
            this.lockPassword = lockPassword;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
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

        public CommunityBean getCommunity() {
            return community;
        }

        public void setCommunity(CommunityBean community) {
            this.community = community;
        }

        public static class CommunityBean {
            /**
             * communityID : 3
             * communityName : 留仙小区
             * provinceID : 440000
             * cityID : 440300
             * districtID : 440305
             * longitude : 113.957663
             * latitude : 22.582366
             * street : 留仙大道
             * addressNumber : 145号
             * postCode : 123456
             */

            private int communityID;
            private String communityName;
            private int provinceID;
            private int cityID;
            private int districtID;
            private double longitude;
            private double latitude;
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

    public static class ListFreshTypeBean {
        /**
         * freshTypeID : 3
         * freshTypeName : 四季鲜果
         * freshTypeLeaf : 3
         * merchant_ID : 22
         */

        private int freshTypeID;
        private String freshTypeName;
        private int freshTypeLeaf;
        private int merchant_ID;

        public int getFreshTypeID() {
            return freshTypeID;
        }

        public void setFreshTypeID(int freshTypeID) {
            this.freshTypeID = freshTypeID;
        }

        public String getFreshTypeName() {
            return freshTypeName;
        }

        public void setFreshTypeName(String freshTypeName) {
            this.freshTypeName = freshTypeName;
        }

        public int getFreshTypeLeaf() {
            return freshTypeLeaf;
        }

        public void setFreshTypeLeaf(int freshTypeLeaf) {
            this.freshTypeLeaf = freshTypeLeaf;
        }

        public int getMerchant_ID() {
            return merchant_ID;
        }

        public void setMerchant_ID(int merchant_ID) {
            this.merchant_ID = merchant_ID;
        }
    }
}
