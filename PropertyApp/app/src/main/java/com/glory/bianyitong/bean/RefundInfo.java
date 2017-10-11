package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/6/28.
 */
public class RefundInfo {

    /**
     * listFreshCollection : [{"freshID":3,"collectionDate":"2017-09-05T15:44:01","fresh":{"freshID":3,"freshTypeID":113,"freshTypeName":"牛肉","freshTypeLeaf":"精选肉类","freshName":"澳洲牛肉","freshPrice":58,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","originName":"","merchant_ID":22,"merchantName":"宜家生鲜店","enable":false,"isDelete":null,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"freshContents":null}}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 3
     */

    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListFreshCollectionBean> listFreshCollection;



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

    public List<ListFreshCollectionBean> getListFreshCollection() {
        return listFreshCollection;
    }

    public void setListFreshCollection(List<ListFreshCollectionBean> listFreshCollection) {
        this.listFreshCollection = listFreshCollection;
    }


    public static class ListFreshCollectionBean {
        /**
         * freshID : 3
         * collectionDate : 2017-09-05T15:44:01
         * fresh : {"freshID":3,"freshTypeID":113,"freshTypeName":"牛肉","freshTypeLeaf":"精选肉类","freshName":"澳洲牛肉","freshPrice":58,"freshPicture":"http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg","originName":"","merchant_ID":22,"merchantName":"宜家生鲜店","enable":false,"isDelete":null,"godownNumber":null,"list_FreshEvaluation":null,"freshEvaluation":null,"freshContents":null}
         */

        private int freshID;
        private String collectionDate;
        private FreshBean fresh;
        public boolean flag=false;
        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getFreshID() {
            return freshID;
        }

        public void setFreshID(int freshID) {
            this.freshID = freshID;
        }

        public String getCollectionDate() {
            return collectionDate;
        }

        public void setCollectionDate(String collectionDate) {
            this.collectionDate = collectionDate;
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
             * freshTypeID : 113
             * freshTypeName : 牛肉
             * freshTypeLeaf : 精选肉类
             * freshName : 澳洲牛肉
             * freshPrice : 58
             * freshPicture : http://byt.bytsz.com.cn/images/Fresh/shrimp2.jpg
             * originName :
             * merchant_ID : 22
             * merchantName : 宜家生鲜店
             * enable : false
             * isDelete : null
             * godownNumber : null
             * list_FreshEvaluation : null
             * freshEvaluation : null
             * freshContents : null
             */

            private int freshID;
            private int freshTypeID;
            private String freshTypeName;
            private String freshTypeLeaf;
            private String freshName;
            private float freshPrice;
            private String freshPicture;
            private String originName;
            private int merchant_ID;
            private String merchantName;
            private boolean enable;
            private boolean isDelete;
            private int godownNumber;
            private Object list_FreshEvaluation;
            private Object freshEvaluation;
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

            public float getFreshPrice() {
                return freshPrice;
            }

            public void setFreshPrice(float freshPrice) {
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
