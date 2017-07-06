package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 * 查询购物车
 */

public class ResponseShoppingCart extends BaseResponseBean {

    private List<ListShoppingCartBean> listShoppingCart;

    public List<ListShoppingCartBean> getListShoppingCart() {
        return listShoppingCart;
    }

    public void setListShoppingCart(List<ListShoppingCartBean> listShoppingCart) {
        this.listShoppingCart = listShoppingCart;
    }

    public static class ListShoppingCartBean {
        /**
         * cartID : 8
         * freshID : 1
         * freshName : 进口甜虾一斤
         * freshTypeID : 28
         * freshTypeName : 虾
         * addTime : 2017-07-05T11:38:48
         * endAddTime : null
         * quantity : 1
         * price : 58
         * fresh : {"freshID":1,"freshTypeID":28,"freshTypeName":"虾","freshTypeLeaf":"水产","freshName":"进口甜虾一斤","freshPrice":58,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/shrimp.jpg","weight":"500.00","originName":"","merchant_ID":22,"merchantName":"宜家生鲜店","choiceType":1,"sortingName":"","freshContent":"御鲜轩 加拿大熟冻北极甜虾 大规格80+ 1kg 北极冰虾 即食海鲜","freshDetail":null,"freshContents":null}
         */

        private int cartID;
        private int freshID;
        private String freshName;
        private int freshTypeID;
        private String freshTypeName;
        private String addTime;
        private Object endAddTime;
        private int quantity;
        private float price;
        private FreshBean fresh;

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

        public String getFreshName() {
            return freshName;
        }

        public void setFreshName(String freshName) {
            this.freshName = freshName;
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

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public Object getEndAddTime() {
            return endAddTime;
        }

        public void setEndAddTime(Object endAddTime) {
            this.endAddTime = endAddTime;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
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
             * freshID : 1
             * freshTypeID : 28
             * freshTypeName : 虾
             * freshTypeLeaf : 水产
             * freshName : 进口甜虾一斤
             * freshPrice : 58
             * freshPicture : https://byt.bytsz.com.cn/images/Fresh/shrimp.jpg
             * weight : 500.00
             * originName :
             * merchant_ID : 22
             * merchantName : 宜家生鲜店
             * choiceType : 1
             * sortingName :
             * freshContent : 御鲜轩 加拿大熟冻北极甜虾 大规格80+ 1kg 北极冰虾 即食海鲜
             * freshDetail : null
             * freshContents : null
             */

            private int freshID;
            private int freshTypeID;
            private String freshTypeName;
            private String freshTypeLeaf;
            private String freshName;
            private int freshPrice;
            private String freshPicture;
            private String weight;
            private String originName;
            private int merchant_ID;
            private String merchantName;
            private int choiceType;
            private String sortingName;
            private String freshContent;
            private Object freshDetail;
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

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
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

            public int getChoiceType() {
                return choiceType;
            }

            public void setChoiceType(int choiceType) {
                this.choiceType = choiceType;
            }

            public String getSortingName() {
                return sortingName;
            }

            public void setSortingName(String sortingName) {
                this.sortingName = sortingName;
            }

            public String getFreshContent() {
                return freshContent;
            }

            public void setFreshContent(String freshContent) {
                this.freshContent = freshContent;
            }

            public Object getFreshDetail() {
                return freshDetail;
            }

            public void setFreshDetail(Object freshDetail) {
                this.freshDetail = freshDetail;
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
