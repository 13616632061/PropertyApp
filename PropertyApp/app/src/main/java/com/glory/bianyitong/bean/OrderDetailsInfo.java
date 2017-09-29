package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/21.
 */
public class OrderDetailsInfo {

    /**
     * orderID : 0
     * list_Order : [{"orderID":240,"orderCode":"1709220550579642260","orderTime":null,"orderPrice":58,"freight":0,"couponReceiveID":12,"appId":null,"orderPaidPrice":38,"address":"留仙大道1285号","realName":"里斯","mobileNumber":"14555554444","couponReceive":{"receiveID":12,"couponID":12,"coupon":{"couponID":12,"couponName":"商家1","merchantID":22,"startFee":50,"freeMoney":20,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}},"listOrderDetail":[{"detailID":320,"orderID":240,"freshID":3,"freshQuantity":1,"price":58,"totalPrice":58,"merchentID":22,"freshScanNum":0,"freshName":"澳洲牛肉","freshImgUrl":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","freshTypeName":"牛肉","fresh":{"freshID":3,"freshTypeLeaf":"牛肉","freshName":"澳洲牛肉","freshPrice":58,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","isDelete":null,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}}],"totalPageNumber":0,"nowPageNumber":0,"cartNum":1}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 0
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
         * orderID : 240
         * orderCode : 1709220550579642260
         * orderTime : null
         * orderPrice : 58
         * freight : 0
         * couponReceiveID : 12
         * appId : null
         * orderPaidPrice : 38
         * address : 留仙大道1285号
         * realName : 里斯
         * mobileNumber : 14555554444
         * couponReceive : {"receiveID":12,"couponID":12,"coupon":{"couponID":12,"couponName":"商家1","merchantID":22,"startFee":50,"freeMoney":20,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}}
         * listOrderDetail : [{"detailID":320,"orderID":240,"freshID":3,"freshQuantity":1,"price":58,"totalPrice":58,"merchentID":22,"freshScanNum":0,"freshName":"澳洲牛肉","freshImgUrl":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","freshTypeName":"牛肉","fresh":{"freshID":3,"freshTypeLeaf":"牛肉","freshName":"澳洲牛肉","freshPrice":58,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","isDelete":null,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}}]
         * totalPageNumber : 0
         * nowPageNumber : 0
         * cartNum : 1
         */

        private int orderID;
        private String orderCode;
        private Object orderTime;
        private int orderPrice;
        private int freight;
        private int couponReceiveID;
        private int appId;
        private int orderPaidPrice;
        private String address;
        private String realName;
        private String mobileNumber;
        private CouponReceiveBean couponReceive;
        private int totalPageNumber;
        private int nowPageNumber;
        private int cartNum;
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

        public int getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
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

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }

        public List<ListOrderDetailBean> getListOrderDetail() {
            return listOrderDetail;
        }

        public void setListOrderDetail(List<ListOrderDetailBean> listOrderDetail) {
            this.listOrderDetail = listOrderDetail;
        }

        public static class CouponReceiveBean {
            /**
             * receiveID : 12
             * couponID : 12
             * coupon : {"couponID":12,"couponName":"商家1","merchantID":22,"startFee":50,"freeMoney":20,"commodityRange":2,"platFormType":2,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}
             */

            private int receiveID;
            private int couponID;
            private CouponBean coupon;

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
                 * couponID : 12
                 * couponName : 商家1
                 * merchantID : 22
                 * startFee : 50
                 * freeMoney : 20
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
                private int freeMoney;
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
             * detailID : 320
             * orderID : 240
             * freshID : 3
             * freshQuantity : 1
             * price : 58
             * totalPrice : 58
             * merchentID : 22
             * freshScanNum : 0
             * freshName : 澳洲牛肉
             * freshImgUrl : http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg
             * freshTypeName : 牛肉
             * fresh : {"freshID":3,"freshTypeLeaf":"牛肉","freshName":"澳洲牛肉","freshPrice":58,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","isDelete":null,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}
             */

            private int detailID;
            private int orderID;
            private int freshID;
            private int freshQuantity;
            private int price;
            private int totalPrice;
            private int merchentID;
            private int freshScanNum;
            private String freshName;
            private String freshImgUrl;
            private String freshTypeName;
            private FreshBean fresh;
            private int qualityID;

            public int getQualityID() {
                return qualityID;
            }

            public void setQualityID(int qualityID) {
                this.qualityID = qualityID;
            }

            public int getDetailID() {
                return detailID;
            }

            public void setDetailID(int detailID) {
                this.detailID = detailID;
            }

            public int getOrderID() {
                return orderID;
            }

            public void setOrderID(int orderID) {
                this.orderID = orderID;
            }

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(int totalPrice) {
                this.totalPrice = totalPrice;
            }

            public int getMerchentID() {
                return merchentID;
            }

            public void setMerchentID(int merchentID) {
                this.merchentID = merchentID;
            }

            public int getFreshScanNum() {
                return freshScanNum;
            }

            public void setFreshScanNum(int freshScanNum) {
                this.freshScanNum = freshScanNum;
            }

            public String getFreshName() {
                return freshName;
            }

            public void setFreshName(String freshName) {
                this.freshName = freshName;
            }

            public String getFreshImgUrl() {
                return freshImgUrl;
            }

            public void setFreshImgUrl(String freshImgUrl) {
                this.freshImgUrl = freshImgUrl;
            }

            public String getFreshTypeName() {
                return freshTypeName;
            }

            public void setFreshTypeName(String freshTypeName) {
                this.freshTypeName = freshTypeName;
            }

            public FreshBean getFresh() {
                return fresh;
            }

            public void setFresh(FreshBean fresh) {
                this.fresh = fresh;
            }

            public static class FreshBean {
                /**
                 * freshID : 3
                 * freshTypeLeaf : 牛肉
                 * freshName : 澳洲牛肉
                 * freshPrice : 58
                 * freshPicture : http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg
                 * isDelete : null
                 * godownNumber : null
                 * list_FreshEvaluation : null
                 * freshEvaluation : null
                 * cartNum : 0
                 * freshContents : null
                 */

                private int freshID;
                private String freshTypeLeaf;
                private String freshName;
                private int freshPrice;
                private String freshPicture;
                private Object isDelete;
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

                public Object getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(Object isDelete) {
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
