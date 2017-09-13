package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 * 查询地址列表
 */

public class ResponseQueryAddress extends BaseResponseBean implements Serializable{


    /**
     * addressID : 0
     * listShippingAddress : [{"addressID":1,"cabinetID":13,"cabinetName":"生鲜柜一号","defaults":true,"harvesterName":"帆","harvestePhone":"18255155899","freshCabinet":{"cabinetID":13,"cabinetName":"生鲜柜一号","longitude":113.957228,"latitude":22.582978,"communityID":1,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"12345","lockPassword":"123","num":48,"used":0,"community":{"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}}}]
     * listFreshCabinet : null
     */

    private int addressID;
    private Object listFreshCabinet;
    private List<ListShippingAddressBean> listShippingAddress;

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public Object getListFreshCabinet() {
        return listFreshCabinet;
    }

    public void setListFreshCabinet(Object listFreshCabinet) {
        this.listFreshCabinet = listFreshCabinet;
    }

    public List<ListShippingAddressBean> getListShippingAddress() {
        return listShippingAddress;
    }

    public void setListShippingAddress(List<ListShippingAddressBean> listShippingAddress) {
        this.listShippingAddress = listShippingAddress;
    }

    public static class ListShippingAddressBean implements Serializable{
        /**
         * addressID : 1
         * cabinetID : 13
         * cabinetName : 生鲜柜一号
         * defaults : true
         * harvesterName : 帆
         * harvestePhone : 18255155899
         * freshCabinet : {"cabinetID":13,"cabinetName":"生鲜柜一号","longitude":113.957228,"latitude":22.582978,"communityID":1,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"12345","lockPassword":"123","num":48,"used":0,"community":{"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}}
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

        public static class FreshCabinetBean implements Serializable{
            /**
             * cabinetID : 13
             * cabinetName : 生鲜柜一号
             * longitude : 113.957228
             * latitude : 22.582978
             * communityID : 1
             * createDate : 2017-08-17T00:00:00
             * createUserID : 1
             * lockAccount : 12345
             * lockPassword : 123
             * num : 48
             * used : 0
             * community : {"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}
             */

            private int cabinetID;
            private String cabinetName;
            private double longitude;
            private double latitude;
            private int communityID;
            private String createDate;
            private int createUserID;
            private String lockAccount;
            private String lockPassword;
            private int num;
            private int used;
            private CommunityBean community;

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

            public CommunityBean getCommunity() {
                return community;
            }

            public void setCommunity(CommunityBean community) {
                this.community = community;
            }

            public static class CommunityBean implements Serializable{
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
    }
}
