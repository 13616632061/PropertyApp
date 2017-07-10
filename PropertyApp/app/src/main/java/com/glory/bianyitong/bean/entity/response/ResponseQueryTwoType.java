package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 * 查询二级分类
 */

public class ResponseQueryTwoType extends BaseResponseBean {

    private List<ListFreshTypeBean> listFreshType;

    public List<ListFreshTypeBean> getListFreshType() {
        return listFreshType;
    }

    public void setListFreshType(List<ListFreshTypeBean> listFreshType) {
        this.listFreshType = listFreshType;
    }

    public static class ListFreshTypeBean {
        /**
         * freshTypeName : 全部
         * freshTypeLeaf : 3
         * merchant_ID : 22
         * merchantName : 宜家生鲜店
         * freshTypeGroup : 水果
         * inverntoryQuantity : 494
         * freshTypeID : 12
         */

        private String freshTypeName;
        private int freshTypeLeaf;
        private int merchant_ID;
        private String merchantName;
        private String freshTypeGroup;
        private int inverntoryQuantity;
        private int freshTypeID;

        public String getFreshTypeName() {
            return freshTypeName;
        }

        public void setFreshTypeName(String freshTypeName) {
            this.freshTypeName = freshTypeName;
        }

        public int getFreshTypeLeaf() {
            return freshTypeLeaf;
        }

        public void setFreshTypeLeaf(int freshTypeLeaf) {
            this.freshTypeLeaf = freshTypeLeaf;
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

        public String getFreshTypeGroup() {
            return freshTypeGroup;
        }

        public void setFreshTypeGroup(String freshTypeGroup) {
            this.freshTypeGroup = freshTypeGroup;
        }

        public int getInverntoryQuantity() {
            return inverntoryQuantity;
        }

        public void setInverntoryQuantity(int inverntoryQuantity) {
            this.inverntoryQuantity = inverntoryQuantity;
        }

        public int getFreshTypeID() {
            return freshTypeID;
        }

        public void setFreshTypeID(int freshTypeID) {
            this.freshTypeID = freshTypeID;
        }
    }
}
