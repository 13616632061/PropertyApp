package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 * 搜索框搜索生鲜数据
 */

public class ResponseSearchFresh extends BaseResponseBean implements Serializable{


    private List<ListfreshBean> listfresh;

    public List<ListfreshBean> getListfresh() {
        return listfresh;
    }

    public void setListfresh(List<ListfreshBean> listfresh) {
        this.listfresh = listfresh;
    }

    public static class ListfreshBean implements Serializable{
        /**
         * freshID : 4
         * freshTypeID : 12
         * freshTypeName : 苹果
         * freshTypeLeaf : 水果
         * freshName : 泰国椰青4粒
         * freshPrice : 49.0
         * freshPicture : https://byt.bytsz.com.cn/images/Fresh/Coconut.jpg
         * weight : 500.00
         * originName :
         * merchant_ID : 22
         * merchantName : 宜家生鲜店
         * choiceType : 1
         * sortingName :
         * freshUrl : http?id=4
         * freshDetail : null
         * freshContent : null
         */

        private int freshID;
        private int freshTypeID;
        private String freshTypeName;
        private String freshTypeLeaf;
        private String freshName;
        private double freshPrice;
        private String freshPicture;
        private String weight;
        private String originName;
        private int merchant_ID;
        private String merchantName;
        private int choiceType;
        private String sortingName;
        private String freshUrl;
        private Object freshDetail;
        private String freshContent;

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

        public String getFreshUrl() {
            return freshUrl;
        }

        public void setFreshUrl(String freshUrl) {
            this.freshUrl = freshUrl;
        }

        public Object getFreshDetail() {
            return freshDetail;
        }

        public void setFreshDetail(Object freshDetail) {
            this.freshDetail = freshDetail;
        }

        public String getFreshContent() {
            return freshContent;
        }

        public void setFreshContent(String freshContent) {
            this.freshContent = freshContent;
        }
    }
}
