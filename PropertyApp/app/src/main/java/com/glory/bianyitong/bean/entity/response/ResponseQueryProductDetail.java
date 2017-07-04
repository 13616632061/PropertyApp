package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 * 商品详情
 */

public class ResponseQueryProductDetail extends BaseResponseBean {

    private List<ListfreshBean> listfresh;

    public List<ListfreshBean> getListfresh() {
        return listfresh;
    }

    public void setListfresh(List<ListfreshBean> listfresh) {
        this.listfresh = listfresh;
    }

    public static class ListfreshBean {
        /**
         * freshID : 4
         * freshTypeID : 12
         * freshTypeName : 苹果
         * freshTypeLeaf : 水果
         * freshName : 泰国椰青4粒
         * freshPrice : 49.0
         * freshPicture : https://byt.bytsz.com.cn/images/Fresh/Coconut.jpg
         * weight : 500.00
         * packingType : 保鲜膜包装
         * originID : 15
         * originName : 广东
         * shelfLife : 12个月
         * tag : 精选,水果
         * nutritiveValue : 椰青水含有丰富的电解质，钾、钠、钙、镁及糖类和蛋白质，可用来防治剧烈运动后呕吐、腹泻或脱水，当人剧烈运动后，喝些椰青水，可以生津解渴、迅速缓解疲劳、补充体力，非常适合运动大量出汗后，迅速恢复体力
         * merchant_ID : 22
         * merchantName : 宜家生鲜店
         * isChoice : true
         * choiceType : 1
         * enable : true
         * sortingName :
         * freshContent : 展卉 泰国进口 椰青2个装 约750g/个 自营
         * listfreshPicture : [{"freshPictureID":279,"freshID":4,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/椰青/1.jpg"},{"freshPictureID":280,"freshID":4,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/椰青/2.jpg"},{"freshPictureID":281,"freshID":4,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/椰青/3.jpg"},{"freshPictureID":282,"freshID":4,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/椰青/4.jpg"},{"freshPictureID":283,"freshID":4,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/椰青/5.jpg"}]
         * merchantTel : 135011
         * number : 10
         * freshDetail : null
         * freshContents : null
         */

        private int freshID;
        private int freshTypeID;
        private String freshTypeName;
        private String freshTypeLeaf;
        private String freshName;
        private double freshPrice;
        private String freshPicture;
        private String weight;
        private String packingType;
        private int originID;
        private String originName;
        private String shelfLife;
        private String tag;
        private String nutritiveValue;
        private int merchant_ID;
        private String merchantName;
        private boolean isChoice;
        private int choiceType;
        private boolean enable;
        private String sortingName;
        private String freshContent;
        private String merchantTel;
        private int number;
        private Object freshDetail;
        private Object freshContents;
        private List<ListfreshPictureBean> listfreshPicture;
        private boolean collectionStatu=false;//是否已收藏

        public boolean isChoice() {
            return isChoice;
        }

        public void setChoice(boolean choice) {
            isChoice = choice;
        }

        public boolean isCollectionStatu() {
            return collectionStatu;
        }

        public void setCollectionStatu(boolean collectionStatu) {
            this.collectionStatu = collectionStatu;
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPackingType() {
            return packingType;
        }

        public void setPackingType(String packingType) {
            this.packingType = packingType;
        }

        public int getOriginID() {
            return originID;
        }

        public void setOriginID(int originID) {
            this.originID = originID;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getShelfLife() {
            return shelfLife;
        }

        public void setShelfLife(String shelfLife) {
            this.shelfLife = shelfLife;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getNutritiveValue() {
            return nutritiveValue;
        }

        public void setNutritiveValue(String nutritiveValue) {
            this.nutritiveValue = nutritiveValue;
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

        public boolean isIsChoice() {
            return isChoice;
        }

        public void setIsChoice(boolean isChoice) {
            this.isChoice = isChoice;
        }

        public int getChoiceType() {
            return choiceType;
        }

        public void setChoiceType(int choiceType) {
            this.choiceType = choiceType;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
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

        public String getMerchantTel() {
            return merchantTel;
        }

        public void setMerchantTel(String merchantTel) {
            this.merchantTel = merchantTel;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
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

        public List<ListfreshPictureBean> getListfreshPicture() {
            return listfreshPicture;
        }

        public void setListfreshPicture(List<ListfreshPictureBean> listfreshPicture) {
            this.listfreshPicture = listfreshPicture;
        }

        public static class ListfreshPictureBean {
            /**
             * freshPictureID : 279
             * freshID : 4
             * picturePath : https://byt.bytsz.com.cn/images/Fresh/椰青/1.jpg
             */

            private int freshPictureID;
            private int freshID;
            private String picturePath;

            public int getFreshPictureID() {
                return freshPictureID;
            }

            public void setFreshPictureID(int freshPictureID) {
                this.freshPictureID = freshPictureID;
            }

            public int getFreshID() {
                return freshID;
            }

            public void setFreshID(int freshID) {
                this.freshID = freshID;
            }

            public String getPicturePath() {
                return picturePath;
            }

            public void setPicturePath(String picturePath) {
                this.picturePath = picturePath;
            }
        }
    }
}
