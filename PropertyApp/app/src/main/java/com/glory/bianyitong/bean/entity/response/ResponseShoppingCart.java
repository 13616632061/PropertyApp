package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 * 查询购物车
 */

public class ResponseShoppingCart extends BaseResponseBean implements Serializable{

    private List<ListShoppingCartBean> listShoppingCart;

    public List<ListShoppingCartBean> getListShoppingCart() {
        return listShoppingCart;
    }

    public void setListShoppingCart(List<ListShoppingCartBean> listShoppingCart) {
        this.listShoppingCart = listShoppingCart;
    }

    public static class ListShoppingCartBean {
        /**
         * merchantID : 22
         * merchantName : 宜家生鲜店
         * listShopping : [{"cartID":94,"freshID":10,"quantity":2,"price":22,"fresh":{"freshID":10,"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":"四季鲜果","freshName":"奇异果","freshPrice":22,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/奇异果/1.jpg","originName":"","merchant_ID":22,"merchantName":"宜家生鲜店","enable":true,"isDelete":false,"godownNumber":100,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null},"isvalid":true}]
         * listCoupon : [{"couponID":12,"couponName":"商家1","merchantID":22,"merchantName":"宜家生鲜店","startFee":50,"minimumAmount":0,"beginDate":"2017-09-18T11:38:47","endDate":"2017-10-08T11:38:47","freeMoney":20,"useDateType":1,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0},{"couponID":15,"couponName":"商家1","merchantID":22,"merchantName":"宜家生鲜店","startFee":0,"minimumAmount":0,"beginDate":"2017-09-12T00:00:00","endDate":"2017-09-30T00:00:00","freeMoney":2,"useDateType":1,"receivestatu":false,"totalPageNumber":0,"nowPageNumber":0,"pageRowCount":0}]
         * isHave : true
         */

        private int merchantID;
        private String merchantName;
        private boolean isHave;
        private List<ListShoppingBean> listShopping;
        private List<ListCouponBean> listCoupon;

        public int getMerchantID() {
            return merchantID;
        }

        public void setMerchantID(int merchantID) {
            this.merchantID = merchantID;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public boolean isIsHave() {
            return isHave;
        }

        public void setIsHave(boolean isHave) {
            this.isHave = isHave;
        }

        public List<ListShoppingBean> getListShopping() {
            return listShopping;
        }

        public void setListShopping(List<ListShoppingBean> listShopping) {
            this.listShopping = listShopping;
        }

        public List<ListCouponBean> getListCoupon() {
            return listCoupon;
        }

        public void setListCoupon(List<ListCouponBean> listCoupon) {
            this.listCoupon = listCoupon;
        }

        public static class ListShoppingBean {
            /**
             * cartID : 94
             * freshID : 10
             * quantity : 2
             * price : 22.0
             * fresh : {"freshID":10,"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":"四季鲜果","freshName":"奇异果","freshPrice":22,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/奇异果/1.jpg","originName":"","merchant_ID":22,"merchantName":"宜家生鲜店","enable":true,"isDelete":false,"godownNumber":100,"list_FreshEvaluation":null,"freshEvaluation":null,"cartNum":0,"freshContents":null}
             * isvalid : true
             */

            private int cartID;
            private int freshID;
            private int quantity;
            private double price;
            private FreshBean fresh;
            private boolean isvalid;

            public int getCartID() {
                return cartID;
            }

            public void setCartID(int cartID) {
                this.cartID = cartID;
            }

            public int getFreshID() {
                return freshID;
            }

            public void setFreshID(int freshID) {
                this.freshID = freshID;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public FreshBean getFresh() {
                return fresh;
            }

            public void setFresh(FreshBean fresh) {
                this.fresh = fresh;
            }

            public boolean isIsvalid() {
                return isvalid;
            }

            public void setIsvalid(boolean isvalid) {
                this.isvalid = isvalid;
            }

            public static class FreshBean {
                /**
                 * freshID : 10
                 * freshTypeID : 118
                 * freshTypeName : 核果类
                 * freshTypeLeaf : 四季鲜果
                 * freshName : 奇异果
                 * freshPrice : 22.0
                 * freshPicture : https://byt.bytsz.com.cn/images/Fresh/奇异果/1.jpg
                 * originName :
                 * merchant_ID : 22
                 * merchantName : 宜家生鲜店
                 * enable : true
                 * isDelete : false
                 * godownNumber : 100
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
                private double freshPrice;
                private String freshPicture;
                private String originName;
                private int merchant_ID;
                private String merchantName;
                private boolean enable;
                private boolean isDelete;
                private int godownNumber;
                private Object list_FreshEvaluation;
                private Object freshEvaluation;
                private int cartNum;
                private Object freshContents;
                public FreshBean(String freshPicture,int merchant_ID) {
                    this.freshPicture = freshPicture;
                    this.merchant_ID=merchant_ID;
                }
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

                public double getFreshPrice() {
                    return freshPrice;
                }

                public void setFreshPrice(double freshPrice) {
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

                public int getGodownNumber() {
                    return godownNumber;
                }

                public void setGodownNumber(int godownNumber) {
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

        public static class ListCouponBean {
            /**
             * couponID : 12
             * couponName : 商家1
             * merchantID : 22
             * merchantName : 宜家生鲜店
             * startFee : 50
             * minimumAmount : 0.0
             * beginDate : 2017-09-18T11:38:47
             * endDate : 2017-10-08T11:38:47
             * freeMoney : 20.0
             * useDateType : 1
             * receivestatu : false
             * totalPageNumber : 0
             * nowPageNumber : 0
             * pageRowCount : 0
             */

            private int couponID;
            private String couponName;
            private int merchantID;
            private String merchantName;
            private int startFee;
            private double minimumAmount;
            private String beginDate;
            private String endDate;
            private double freeMoney;
            private int useDateType;
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

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
            }

            public int getStartFee() {
                return startFee;
            }

            public void setStartFee(int startFee) {
                this.startFee = startFee;
            }

            public double getMinimumAmount() {
                return minimumAmount;
            }

            public void setMinimumAmount(double minimumAmount) {
                this.minimumAmount = minimumAmount;
            }

            public String getBeginDate() {
                return beginDate;
            }

            public void setBeginDate(String beginDate) {
                this.beginDate = beginDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public double getFreeMoney() {
                return freeMoney;
            }

            public void setFreeMoney(double freeMoney) {
                this.freeMoney = freeMoney;
            }

            public int getUseDateType() {
                return useDateType;
            }

            public void setUseDateType(int useDateType) {
                this.useDateType = useDateType;
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
}