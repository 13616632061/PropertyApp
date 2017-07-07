package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 * 查询商户信息
 */

public class ResponseQueryShopInfo extends BaseResponseBean {

    private List<ListFreshMerchantBean> listFreshMerchant;

    public List<ListFreshMerchantBean> getListFreshMerchant() {
        return listFreshMerchant;
    }

    public void setListFreshMerchant(List<ListFreshMerchantBean> listFreshMerchant) {
        this.listFreshMerchant = listFreshMerchant;
    }

    public static class ListFreshMerchantBean {
        /**
         * merchant_ID : 22
         * merchant_Name : 宜家生鲜店
         * merchant_TEL : 135011
         * area_ID : 39
         * area_Name : null
         * listUser : null
         * domain_ID : 79
         * domain_Name : null
         * listDomain : null
         * listType : [{"freshTypeID":3,"freshTypeName":"水果","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":6,"freshTypeName":"蔬菜","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":7,"freshTypeName":"水产","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":8,"freshTypeName":"肉类","freshTypeLeaf":8,"merchant_ID":22}]
         */

        private int merchant_ID;
        private String merchant_Name;
        private String merchant_TEL;
        private int area_ID;
        private Object area_Name;
        private Object listUser;
        private int domain_ID;
        private Object domain_Name;
        private Object listDomain;
        private List<ListTypeBean> listType;

        public int getMerchant_ID() {
            return merchant_ID;
        }

        public void setMerchant_ID(int merchant_ID) {
            this.merchant_ID = merchant_ID;
        }

        public String getMerchant_Name() {
            return merchant_Name;
        }

        public void setMerchant_Name(String merchant_Name) {
            this.merchant_Name = merchant_Name;
        }

        public String getMerchant_TEL() {
            return merchant_TEL;
        }

        public void setMerchant_TEL(String merchant_TEL) {
            this.merchant_TEL = merchant_TEL;
        }

        public int getArea_ID() {
            return area_ID;
        }

        public void setArea_ID(int area_ID) {
            this.area_ID = area_ID;
        }

        public Object getArea_Name() {
            return area_Name;
        }

        public void setArea_Name(Object area_Name) {
            this.area_Name = area_Name;
        }

        public Object getListUser() {
            return listUser;
        }

        public void setListUser(Object listUser) {
            this.listUser = listUser;
        }

        public int getDomain_ID() {
            return domain_ID;
        }

        public void setDomain_ID(int domain_ID) {
            this.domain_ID = domain_ID;
        }

        public Object getDomain_Name() {
            return domain_Name;
        }

        public void setDomain_Name(Object domain_Name) {
            this.domain_Name = domain_Name;
        }

        public Object getListDomain() {
            return listDomain;
        }

        public void setListDomain(Object listDomain) {
            this.listDomain = listDomain;
        }

        public List<ListTypeBean> getListType() {
            return listType;
        }

        public void setListType(List<ListTypeBean> listType) {
            this.listType = listType;
        }

        public static class ListTypeBean {
            /**
             * freshTypeID : 3
             * freshTypeName : 水果
             * freshTypeLeaf : 3
             * merchant_ID : 22
             */

            private int freshTypeID;
            private String freshTypeName;
            private int freshTypeLeaf;
            private int merchant_ID;

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
        }
    }
}
