package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/21.
 */
public class OrderDetailsInfo {

    /**
     * orderID : 0
     * list_Order : [{"orderID":226,"orderCode":"1709200853312472960","orderTime":null,"freight":0,"couponReceiveID":13,"addressID":13,"appId":null,"orderPaidPrice":30,"shippingAddress":{"addressID":13,"cabinetID":22,"cabinetName":null,"defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":22,"cabinetName":"生鲜柜三号","longitude":113.957114,"latitude":22.58243,"communityID":3,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"123","lockPassword":"123","num":48,"cabinetNo":500004,"address":"留仙大道1281号","used":0,"community":{"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"留仙大道","addressNumber":"145号","postCode":123456}}},"couponReceive":{"receiveID":13,"couponID":13,"coupon":{"couponID":13,"couponName":"商家2","merchantID":29,"startFee":20,"freeMoney":10,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}},"listOrderDetail":[{"freshID":41,"freshQuantity":2,"fresh":{"freshID":41,"freshTypeID":106,"freshTypeName":"葱姜蒜椒","freshTypeLeaf":"蔬菜蛋类","freshName":"海蜇","freshPrice":20,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/Jellyfish.jpg","originName":"","merchant_ID":29,"merchantName":"宝安44区生鲜店","enable":true,"isDelete":false,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}}],"totalPageNumber":0,"nowPageNumber":0}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 20
     */

    private int orderID;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListOrderBean> list_Order;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public List<ListOrderBean> getList_Order() {
        return list_Order;
    }

    public void setList_Order(List<ListOrderBean> list_Order) {
        this.list_Order = list_Order;
    }

    public static class ListOrderBean {
        /**
         * orderID : 226
         * orderCode : 1709200853312472960
         * orderTime : null
         * freight : 0
         * couponReceiveID : 13
         * addressID : 13
         * appId : null
         * orderPaidPrice : 30
         * shippingAddress : {"addressID":13,"cabinetID":22,"cabinetName":null,"defaults":false,"harvesterName":"测试用户","harvestePhone":"13510012206","freshCabinet":{"cabinetID":22,"cabinetName":"生鲜柜三号","longitude":113.957114,"latitude":22.58243,"communityID":3,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"123","lockPassword":"123","num":48,"cabinetNo":500004,"address":"留仙大道1281号","used":0,"community":{"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"留仙大道","addressNumber":"145号","postCode":123456}}}
         * couponReceive : {"receiveID":13,"couponID":13,"coupon":{"couponID":13,"couponName":"商家2","merchantID":29,"startFee":20,"freeMoney":10,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}}
         * listOrderDetail : [{"freshID":41,"freshQuantity":2,"fresh":{"freshID":41,"freshTypeID":106,"freshTypeName":"葱姜蒜椒","freshTypeLeaf":"蔬菜蛋类","freshName":"海蜇","freshPrice":20,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/Jellyfish.jpg","originName":"","merchant_ID":29,"merchantName":"宝安44区生鲜店","enable":true,"isDelete":false,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}}]
         * totalPageNumber : 0
         * nowPageNumber : 0
         */

        private int orderID;
        private String orderCode;
        private Object orderTime;
        private int freight;
        private int couponReceiveID;
        private int addressID;
        private int appId=0;
        private int orderPaidPrice;
        private ShippingAddressBean shippingAddress;
        private CouponReceiveBean couponReceive=null;
        private int totalPageNumber;
        private int nowPageNumber;
        private List<ListOrderDetailBean> listOrderDetail;

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public Object getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Object orderTime) {
            this.orderTime = orderTime;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getCouponReceiveID() {
            return couponReceiveID;
        }

        public void setCouponReceiveID(int couponReceiveID) {
            this.couponReceiveID = couponReceiveID;
        }

        public int getAddressID() {
            return addressID;
        }

        public void setAddressID(int addressID) {
            this.addressID = addressID;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }

        public int getOrderPaidPrice() {
            return orderPaidPrice;
        }

        public void setOrderPaidPrice(int orderPaidPrice) {
            this.orderPaidPrice = orderPaidPrice;
        }

        public ShippingAddressBean getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(ShippingAddressBean shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public CouponReceiveBean getCouponReceive() {
            return couponReceive;
        }

        public void setCouponReceive(CouponReceiveBean couponReceive) {
            this.couponReceive = couponReceive;
        }

        public int getTotalPageNumber() {
            return totalPageNumber;
        }

        public void setTotalPageNumber(int totalPageNumber) {
            this.totalPageNumber = totalPageNumber;
        }

        public int getNowPageNumber() {
            return nowPageNumber;
        }

        public void setNowPageNumber(int nowPageNumber) {
            this.nowPageNumber = nowPageNumber;
        }

        public List<ListOrderDetailBean> getListOrderDetail() {
            return listOrderDetail;
        }

        public void setListOrderDetail(List<ListOrderDetailBean> listOrderDetail) {
            this.listOrderDetail = listOrderDetail;
        }

        public static class ShippingAddressBean {
            /**
             * addressID : 13
             * cabinetID : 22
             * cabinetName : null
             * defaults : false
             * harvesterName : 测试用户
             * harvestePhone : 13510012206
             * freshCabinet : {"cabinetID":22,"cabinetName":"生鲜柜三号","longitude":113.957114,"latitude":22.58243,"communityID":3,"createDate":"2017-08-17T00:00:00","createUserID":1,"lockAccount":"123","lockPassword":"123","num":48,"cabinetNo":500004,"address":"留仙大道1281号","used":0,"community":{"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"留仙大道","addressNumber":"145号","postCode":123456}}
             */

            private int addressID;
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
                 * cabinetID : 22
                 * cabinetName : 生鲜柜三号
                 * longitude : 113.957114
                 * latitude : 22.58243
                 * communityID : 3
                 * createDate : 2017-08-17T00:00:00
                 * createUserID : 1
                 * lockAccount : 123
                 * lockPassword : 123
                 * num : 48
                 * cabinetNo : 500004
                 * address : 留仙大道1281号
                 * used : 0
                 * community : {"communityID":3,"communityName":"留仙小区","provinceID":440000,"cityID":440300,"districtID":440305,"longitude":113.957663,"latitude":22.582366,"provinceName":"广东省","cityName":"深圳市","districtName":"南山区","street":"留仙大道","addressNumber":"145号","postCode":123456}
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
                     * communityID : 3
                     * communityName : 留仙小区
                     * provinceID : 440000
                     * cityID : 440300
                     * districtID : 440305
                     * longitude : 113.957663
                     * latitude : 22.582366
                     * provinceName : 广东省
                     * cityName : 深圳市
                     * districtName : 南山区
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

        public static class CouponReceiveBean {
            /**
             * receiveID : 13
             * couponID : 13
             * coupon : {"couponID":13,"couponName":"商家2","merchantID":29,"startFee":20,"freeMoney":10,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}
             */

            private int receiveID;
            private int couponID;
            private CouponBean coupon=null;

            public int getReceiveID() {
                return receiveID;
            }

            public void setReceiveID(int receiveID) {
                this.receiveID = receiveID;
            }

            public int getCouponID() {
                return couponID;
            }

            public void setCouponID(int couponID) {
                this.couponID = couponID;
            }

            public CouponBean getCoupon() {
                return coupon;
            }

            public void setCoupon(CouponBean coupon) {
                this.coupon = coupon;
            }

            public static class CouponBean {
                /**
                 * couponID : 13
                 * couponName : 商家2
                 * merchantID : 29
                 * startFee : 20
                 * freeMoney : 10
                 * commodityRange : 2
                 * platFormType : 2
                 * receivestatu : false
                 * totalPageNumber : 0
                 * nowPageNumber : 0
                 * pageRowCount : 0
                 */

                private int couponID;
                private String couponName;
                private int merchantID;
                private int startFee;
                private int freeMoney=0;
                private int commodityRange;
                private int platFormType;
                private boolean receivestatu;
                private int totalPageNumber;
                private int nowPageNumber;
                private int pageRowCount;

                public int getCouponID() {
                    return couponID;
                }

                public void setCouponID(int couponID) {
                    this.couponID = couponID;
                }

                public String getCouponName() {
                    return couponName;
                }

                public void setCouponName(String couponName) {
                    this.couponName = couponName;
                }

                public int getMerchantID() {
                    return merchantID;
                }

                public void setMerchantID(int merchantID) {
                    this.merchantID = merchantID;
                }

                public int getStartFee() {
                    return startFee;
                }

                public void setStartFee(int startFee) {
                    this.startFee = startFee;
                }

                public int getFreeMoney() {
                    return freeMoney;
                }

                public void setFreeMoney(int freeMoney) {
                    this.freeMoney = freeMoney;
                }

                public int getCommodityRange() {
                    return commodityRange;
                }

                public void setCommodityRange(int commodityRange) {
                    this.commodityRange = commodityRange;
                }

                public int getPlatFormType() {
                    return platFormType;
                }

                public void setPlatFormType(int platFormType) {
                    this.platFormType = platFormType;
                }

                public boolean isReceivestatu() {
                    return receivestatu;
                }

                public void setReceivestatu(boolean receivestatu) {
                    this.receivestatu = receivestatu;
                }

                public int getTotalPageNumber() {
                    return totalPageNumber;
                }

                public void setTotalPageNumber(int totalPageNumber) {
                    this.totalPageNumber = totalPageNumber;
                }

                public int getNowPageNumber() {
                    return nowPageNumber;
                }

                public void setNowPageNumber(int nowPageNumber) {
                    this.nowPageNumber = nowPageNumber;
                }

                public int getPageRowCount() {
                    return pageRowCount;
                }

                public void setPageRowCount(int pageRowCount) {
                    this.pageRowCount = pageRowCount;
                }
            }
        }

        public static class ListOrderDetailBean {
            /**
             * freshID : 41
             * freshQuantity : 2
             * fresh : {"freshID":41,"freshTypeID":106,"freshTypeName":"葱姜蒜椒","freshTypeLeaf":"蔬菜蛋类","freshName":"海蜇","freshPrice":20,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/Jellyfish.jpg","originName":"","merchant_ID":29,"merchantName":"宝安44区生鲜店","enable":true,"isDelete":false,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}
             */

            private int freshID;
            private int freshQuantity;
            private FreshBean fresh;

            public int getFreshID() {
                return freshID;
            }

            public void setFreshID(int freshID) {
                this.freshID = freshID;
            }

            public int getFreshQuantity() {
                return freshQuantity;
            }

            public void setFreshQuantity(int freshQuantity) {
                this.freshQuantity = freshQuantity;
            }

            public FreshBean getFresh() {
                return fresh;
            }

            public void setFresh(FreshBean fresh) {
                this.fresh = fresh;
            }

            public static class FreshBean {
                /**
                 * freshID : 41
                 * freshTypeID : 106
                 * freshTypeName : 葱姜蒜椒
                 * freshTypeLeaf : 蔬菜蛋类
                 * freshName : 海蜇
                 * freshPrice : 20
                 * freshPicture : http://byt.bytsz.com.cn/images/Fresh/Jellyfish.jpg
                 * originName :
                 * merchant_ID : 29
                 * merchantName : 宝安44区生鲜店
                 * enable : true
                 * isDelete : false
                 * godownNumber : null
                 * list_FreshEvaluation : null
                 * freshEvaluation : null
                 * cartNum : 0
                 * freshContents : null
                 */

                private int freshID;
                private int freshTypeID;
                private String freshTypeName;
                private String freshTypeLeaf;
                private String freshName;
                private int freshPrice;
                private String freshPicture;
                private String originName;
                private int merchant_ID;
                private String merchantName;
                private boolean enable;
                private boolean isDelete;
                private Object godownNumber;
                private Object list_FreshEvaluation;
                private Object freshEvaluation;
                private int cartNum;
                private Object freshContents;

                public int getFreshID() {
                    return freshID;
                }

                public void setFreshID(int freshID) {
                    this.freshID = freshID;
                }

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

                public String getFreshTypeLeaf() {
                    return freshTypeLeaf;
                }

                public void setFreshTypeLeaf(String freshTypeLeaf) {
                    this.freshTypeLeaf = freshTypeLeaf;
                }

                public String getFreshName() {
                    return freshName;
                }

                public void setFreshName(String freshName) {
                    this.freshName = freshName;
                }

                public int getFreshPrice() {
                    return freshPrice;
                }

                public void setFreshPrice(int freshPrice) {
                    this.freshPrice = freshPrice;
                }

                public String getFreshPicture() {
                    return freshPicture;
                }

                public void setFreshPicture(String freshPicture) {
                    this.freshPicture = freshPicture;
                }

                public String getOriginName() {
                    return originName;
                }

                public void setOriginName(String originName) {
                    this.originName = originName;
                }

                public int getMerchant_ID() {
                    return merchant_ID;
                }

                public void setMerchant_ID(int merchant_ID) {
                    this.merchant_ID = merchant_ID;
                }

                public String getMerchantName() {
                    return merchantName;
                }

                public void setMerchantName(String merchantName) {
                    this.merchantName = merchantName;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }

                public boolean isIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(boolean isDelete) {
                    this.isDelete = isDelete;
                }

                public Object getGodownNumber() {
                    return godownNumber;
                }

                public void setGodownNumber(Object godownNumber) {
                    this.godownNumber = godownNumber;
                }

                public Object getList_FreshEvaluation() {
                    return list_FreshEvaluation;
                }

                public void setList_FreshEvaluation(Object list_FreshEvaluation) {
                    this.list_FreshEvaluation = list_FreshEvaluation;
                }

                public Object getFreshEvaluation() {
                    return freshEvaluation;
                }

                public void setFreshEvaluation(Object freshEvaluation) {
                    this.freshEvaluation = freshEvaluation;
                }

                public int getCartNum() {
                    return cartNum;
                }

                public void setCartNum(int cartNum) {
                    this.cartNum = cartNum;
                }

                public Object getFreshContents() {
                    return freshContents;
                }

                public void setFreshContents(Object freshContents) {
                    this.freshContents = freshContents;
                }
            }
        }
    }
}
