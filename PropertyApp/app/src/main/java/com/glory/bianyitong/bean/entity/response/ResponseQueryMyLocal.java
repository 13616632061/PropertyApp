package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 * 查询我的位置信息
 */

public class ResponseQueryMyLocal extends BaseResponseBean {

    /**
     * area : {"area_ID":39,"area_Name":"南山区","domain_ID":79,"domain_Name":"广东地区","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440305,"districtName":"南山区","user":null,"listUser":null,"listDomain":null,"listArea":null,"listMerchant":[{"merchant_ID":22,"merchant_Name":"宜家生鲜店","merchant_TEL":"135011","area_ID":39,"area_Name":null,"listUser":null,"domain_ID":79,"domain_Name":null,"listDomain":null,"listType":[{"freshTypeID":3,"freshTypeName":"水果","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":6,"freshTypeName":"蔬菜","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":7,"freshTypeName":"水产","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":8,"freshTypeName":"肉类","freshTypeLeaf":8,"merchant_ID":22}]}]}
     * listArea : [{"area_ID":18,"area_Name":"龙岗区","domain_ID":79,"domain_Name":"广东地区","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440307,"districtName":"龙岗区","user":null,"listUser":null,"listDomain":null,"listArea":null,"listMerchant":null},{"area_ID":36,"area_Name":"宝安区","domain_ID":79,"domain_Name":"广东地区","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440306,"districtName":"宝安区","user":null,"listUser":null,"listDomain":null,"listArea":null,"listMerchant":null},{"area_ID":40,"area_Name":"福田区","domain_ID":79,"domain_Name":"广东地区","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","districtID":440304,"districtName":"福田区","user":null,"listUser":null,"listDomain":null,"listArea":null,"listMerchant":null}]
     */

    private AreaBean area;
    private List<ListAreaBean> listArea;

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public List<ListAreaBean> getListArea() {
        return listArea;
    }

    public void setListArea(List<ListAreaBean> listArea) {
        this.listArea = listArea;
    }

    public static class AreaBean {
        /**
         * area_ID : 39
         * area_Name : 南山区
         * domain_ID : 79
         * domain_Name : 广东地区
         * provinceID : 440000
         * provinceName : 广东省
         * cityID : 440300
         * cityName : 深圳市
         * districtID : 440305
         * districtName : 南山区
         * user : null
         * listUser : null
         * listDomain : null
         * listArea : null
         * listMerchant : [{"merchant_ID":22,"merchant_Name":"宜家生鲜店","merchant_TEL":"135011","area_ID":39,"area_Name":null,"listUser":null,"domain_ID":79,"domain_Name":null,"listDomain":null,"listType":[{"freshTypeID":3,"freshTypeName":"水果","freshTypeLeaf":3,"merchant_ID":22},{"freshTypeID":6,"freshTypeName":"蔬菜","freshTypeLeaf":6,"merchant_ID":22},{"freshTypeID":7,"freshTypeName":"水产","freshTypeLeaf":7,"merchant_ID":22},{"freshTypeID":8,"freshTypeName":"肉类","freshTypeLeaf":8,"merchant_ID":22}]}]
         */

        private int area_ID;
        private String area_Name;
        private int domain_ID;
        private String domain_Name;
        private int provinceID;
        private String provinceName;
        private int cityID;
        private String cityName;
        private int districtID;
        private String districtName;
        private Object user;
        private Object listUser;
        private Object listDomain;
        private Object listArea;
        private List<ListMerchantBean> listMerchant;

        public int getArea_ID() {
            return area_ID;
        }

        public void setArea_ID(int area_ID) {
            this.area_ID = area_ID;
        }

        public String getArea_Name() {
            return area_Name;
        }

        public void setArea_Name(String area_Name) {
            this.area_Name = area_Name;
        }

        public int getDomain_ID() {
            return domain_ID;
        }

        public void setDomain_ID(int domain_ID) {
            this.domain_ID = domain_ID;
        }

        public String getDomain_Name() {
            return domain_Name;
        }

        public void setDomain_Name(String domain_Name) {
            this.domain_Name = domain_Name;
        }

        public int getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getDistrictID() {
            return districtID;
        }

        public void setDistrictID(int districtID) {
            this.districtID = districtID;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public Object getListUser() {
            return listUser;
        }

        public void setListUser(Object listUser) {
            this.listUser = listUser;
        }

        public Object getListDomain() {
            return listDomain;
        }

        public void setListDomain(Object listDomain) {
            this.listDomain = listDomain;
        }

        public Object getListArea() {
            return listArea;
        }

        public void setListArea(Object listArea) {
            this.listArea = listArea;
        }

        public List<ListMerchantBean> getListMerchant() {
            return listMerchant;
        }

        public void setListMerchant(List<ListMerchantBean> listMerchant) {
            this.listMerchant = listMerchant;
        }

        public static class ListMerchantBean {
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

    public static class ListAreaBean {
        /**
         * area_ID : 18
         * area_Name : 龙岗区
         * domain_ID : 79
         * domain_Name : 广东地区
         * provinceID : 440000
         * provinceName : 广东省
         * cityID : 440300
         * cityName : 深圳市
         * districtID : 440307
         * districtName : 龙岗区
         * user : null
         * listUser : null
         * listDomain : null
         * listArea : null
         * listMerchant : null
         */

        private int area_ID;
        private String area_Name;
        private int domain_ID;
        private String domain_Name;
        private int provinceID;
        private String provinceName;
        private int cityID;
        private String cityName;
        private int districtID;
        private String districtName;
        private Object user;
        private Object listUser;
        private Object listDomain;
        private Object listArea;
        private Object listMerchant;

        public int getArea_ID() {
            return area_ID;
        }

        public void setArea_ID(int area_ID) {
            this.area_ID = area_ID;
        }

        public String getArea_Name() {
            return area_Name;
        }

        public void setArea_Name(String area_Name) {
            this.area_Name = area_Name;
        }

        public int getDomain_ID() {
            return domain_ID;
        }

        public void setDomain_ID(int domain_ID) {
            this.domain_ID = domain_ID;
        }

        public String getDomain_Name() {
            return domain_Name;
        }

        public void setDomain_Name(String domain_Name) {
            this.domain_Name = domain_Name;
        }

        public int getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getDistrictID() {
            return districtID;
        }

        public void setDistrictID(int districtID) {
            this.districtID = districtID;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public Object getListUser() {
            return listUser;
        }

        public void setListUser(Object listUser) {
            this.listUser = listUser;
        }

        public Object getListDomain() {
            return listDomain;
        }

        public void setListDomain(Object listDomain) {
            this.listDomain = listDomain;
        }

        public Object getListArea() {
            return listArea;
        }

        public void setListArea(Object listArea) {
            this.listArea = listArea;
        }

        public Object getListMerchant() {
            return listMerchant;
        }

        public void setListMerchant(Object listMerchant) {
            this.listMerchant = listMerchant;
        }
    }
}
