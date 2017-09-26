package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/9/26.
 */
public class GetOrderCommitAddress {

    /**
     * addressID : 0
     * shippingAddress : {"addressID":16,"userID":0,"cabinetID":21,"cabinetName":null,"defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":21,"cabinetName":"生鲜柜三号","communityID":1,"communityName":"西丽小区","num":48,"cabinetNo":500003,"address":"桃源街道龙联社区","used":0,"community":{"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}}}
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
     */

    private int addressID;
    private ShippingAddressBean shippingAddress;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public ShippingAddressBean getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressBean shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public static class ShippingAddressBean {
        /**
         * addressID : 16
         * userID : 0
         * cabinetID : 21
         * cabinetName : null
         * defaults : false
         * harvesterName : 测试用户
         * harvestePhone : 13510012206
         * freshCabinet : {"cabinetID":21,"cabinetName":"生鲜柜三号","communityID":1,"communityName":"西丽小区","num":48,"cabinetNo":500003,"address":"桃源街道龙联社区","used":0,"community":{"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}}
         */

        private int addressID;
        private int userID;
        private int cabinetID;
        private Object cabinetName;
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

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getCabinetID() {
            return cabinetID;
        }

        public void setCabinetID(int cabinetID) {
            this.cabinetID = cabinetID;
        }

        public Object getCabinetName() {
            return cabinetName;
        }

        public void setCabinetName(Object cabinetName) {
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
             * cabinetID : 21
             * cabinetName : 生鲜柜三号
             * communityID : 1
             * communityName : 西丽小区
             * num : 48
             * cabinetNo : 500003
             * address : 桃源街道龙联社区
             * used : 0
             * community : {"communityID":1,"communityName":"西丽小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957228,"latitude":22.582978,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"西丽大街","addressNumber":"15号","postCode":546987}
             */

            private int cabinetID;
            private String cabinetName;
            private int communityID;
            private String communityName;
            private int num;
            private int cabinetNo;
            private String address;
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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getCabinetNo() {
                return cabinetNo;
            }

            public void setCabinetNo(int cabinetNo) {
                this.cabinetNo = cabinetNo;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public static class CommunityBean {
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
