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
         * merchantID : 29
         * merchantName : 宝安44区生鲜店
         * listShopping : [{"cartID":70,"freshID":11,"quantity":1,"price":11,"fresh":{"freshID":11,"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":"四季鲜果","freshName":"石榴","freshPrice":11,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg","originName":"","merchant_ID":29,"merchantName":"宝安44区生鲜店","enable":false,"isDelete":null,"godownNumber":0,"list_FreshEvaluation":null,"freshEvaluation":null,"freshContents":null}}]
         * isHave : false
         */

        private int merchantID;
        private String merchantName;
        private boolean isHave;
        private List<ListShoppingBean> listShopping;

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

        public static class ListShoppingBean {
            /**
             * cartID : 70
             * freshID : 11
             * quantity : 1
             * price : 11.0
             * fresh : {"freshID":11,"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":"四季鲜果","freshName":"石榴","freshPrice":11,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg","originName":"","merchant_ID":29,"merchantName":"宝安44区生鲜店","enable":false,"isDelete":null,"godownNumber":0,"list_FreshEvaluation":null,"freshEvaluation":null,"freshContents":null}
             */

            private int cartID;
            private int freshID;
            private int quantity=0;
            private double price=0;
            private FreshBean fresh;
            private boolean isvalid;

            public boolean isvalid() {

                return isvalid;
            }

            public void setIsvalid(boolean isvalid) {
                this.isvalid = isvalid;
            }

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

            public static class FreshBean {
                /**
                 * freshID : 11
                 * freshTypeID : 118
                 * freshTypeName : 核果类
                 * freshTypeLeaf : 四季鲜果
                 * freshName : 石榴
                 * freshPrice : 11.0
                 * freshPicture : http://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg
                 * originName :
                 * merchant_ID : 29
                 * merchantName : 宝安44区生鲜店
                 * enable : false
                 * isDelete : null
                 * godownNumber : 0
                 * list_FreshEvaluation : null
                 * freshEvaluation : null
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
                private boolean isDelete=false;
                private int godownNumber;
                private Object list_FreshEvaluation;
                private Object freshEvaluation;
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

                public boolean getIsDelete() {
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