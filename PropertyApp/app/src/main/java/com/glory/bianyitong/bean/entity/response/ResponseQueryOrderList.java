package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by billlamian on 17-8-10.
 * 订单列表响应
 */

public class ResponseQueryOrderList extends BaseResponseBean {

    private List<ListOrderBean> list_Order;

    public List<ListOrderBean> getList_Order() {
        return list_Order;
    }

    public void setList_Order(List<ListOrderBean> list_Order) {
        this.list_Order = list_Order;
    }

    public static class ListOrderBean implements Serializable{
        /**
         * orderID : 111
         * orderCode : 4417080904221566209
         * orderTime : 2017-08-09T16:22:15
         * userID : 0
         * orderPrice : 49
         * orderStatus : 0
         * freight : 0
         * freePrice : 0
         * addressID : 13
         * cabinetID : 22
         * cabinetName : 生鲜柜四号
         * isEnable : true
         * channelType : 1
         * listOrderDetail : [{"detailID":171,"orderID":111,"freshID":4,"freshQuantity":1,"price":49,"totalPrice":49,"fresh":{"freshID":4,"freshTypeID":12,"freshTypeLeaf":"水果","freshName":"泰国椰青4粒%","freshPrice":49,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/Coconut.jpg","weight":"500.00","merchant_ID":22,"choiceType":1,"freshContent":"展卉 泰国进口 椰青2个装 约750g/个 自营","list_FreshEvaluation":null,"totalEvaluation":0}}]
         * totalPageNumber : 0
         * nowPageNumber : 0
         */

        private int orderID;
        private String orderCode;
        private String orderTime;
        private int userID;
        private float orderPrice;
        private int orderStatus;
        private int freight;
        private int freePrice;
        private int addressID;
        private int cabinetID;
        private String cabinetName;
        private boolean isEnable;
        private int channelType;
        private int totalPageNumber;
        private int nowPageNumber;
        private String merchant_Name;
        private float orderPaidPrice;//订单实付金额
        private int cartNum;//订单商品数量
        private List<ListOrderDetailBean> listOrderDetail;

        public float getOrderPaidPrice() {
            return orderPaidPrice;
        }

        public void setOrderPaidPrice(float orderPaidPrice) {
            this.orderPaidPrice = orderPaidPrice;
        }

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }

        public String getMerchant_Name() {
            return merchant_Name;
        }

        public void setMerchant_Name(String merchant_Name) {
            this.merchant_Name = merchant_Name;
        }

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

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public float getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(float orderPrice) {
            this.orderPrice = orderPrice;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getFreePrice() {
            return freePrice;
        }

        public void setFreePrice(int freePrice) {
            this.freePrice = freePrice;
        }

        public int getAddressID() {
            return addressID;
        }

        public void setAddressID(int addressID) {
            this.addressID = addressID;
        }

        public int getCabinetID() {
            return cabinetID;
        }

        public void setCabinetID(int cabinetID) {
            this.cabinetID = cabinetID;
        }

        public String getCabinetName() {
            return cabinetName;
        }

        public void setCabinetName(String cabinetName) {
            this.cabinetName = cabinetName;
        }

        public boolean isIsEnable() {
            return isEnable;
        }

        public void setIsEnable(boolean isEnable) {
            this.isEnable = isEnable;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
            this.channelType = channelType;
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

        public List<ListOrderDetailBean> getListOrderDetail() {
            return listOrderDetail;
        }

        public void setListOrderDetail(List<ListOrderDetailBean> listOrderDetail) {
            this.listOrderDetail = listOrderDetail;
        }

        public static class ListOrderDetailBean implements Serializable{
            /**
             * detailID : 171
             * orderID : 111
             * freshID : 4
             * freshQuantity : 1
             * price : 49
             * totalPrice : 49
             * fresh : {"freshID":4,"freshTypeID":12,"freshTypeLeaf":"水果","freshName":"泰国椰青4粒%","freshPrice":49,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/Coconut.jpg","weight":"500.00","merchant_ID":22,"choiceType":1,"freshContent":"展卉 泰国进口 椰青2个装 约750g/个 自营","list_FreshEvaluation":null,"totalEvaluation":0}
             */

            private int detailID;
            private int orderID;
            private int freshID;
            private int freshQuantity;
            private int price;
            private float totalPrice;
            private FreshBean fresh;


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

            public float getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(float totalPrice) {
                this.totalPrice = totalPrice;
            }

            public FreshBean getFresh() {
                return fresh;
            }

            public void setFresh(FreshBean fresh) {
                this.fresh = fresh;
            }

            public static class FreshBean implements Serializable{
                /**
                 * freshID : 4
                 * freshTypeID : 12
                 * freshTypeLeaf : 水果
                 * freshName : 泰国椰青4粒%
                 * freshPrice : 49
                 * freshPicture : https://byt.bytsz.com.cn/images/Fresh/Coconut.jpg
                 * weight : 500.00
                 * merchant_ID : 22
                 * choiceType : 1
                 * freshContent : 展卉 泰国进口 椰青2个装 约750g/个 自营
                 * list_FreshEvaluation : null
                 * totalEvaluation : 0
                 */

                private int freshID;
                private int freshTypeID;
                private String freshTypeLeaf;
                private String freshName;
                private float freshPrice;
                private String freshPicture;
                private String weight;
                private int merchant_ID;
                private int choiceType;
                private String freshContent;
                private Object list_FreshEvaluation;
                private int totalEvaluation;
                private String merchantName;

                public String getMerchantName() {
                    return merchantName;
                }

                public void setMerchantName(String merchantName) {
                    this.merchantName = merchantName;
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

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public int getMerchant_ID() {
                    return merchant_ID;
                }

                public void setMerchant_ID(int merchant_ID) {
                    this.merchant_ID = merchant_ID;
                }

                public int getChoiceType() {
                    return choiceType;
                }

                public void setChoiceType(int choiceType) {
                    this.choiceType = choiceType;
                }

                public String getFreshContent() {
                    return freshContent;
                }

                public void setFreshContent(String freshContent) {
                    this.freshContent = freshContent;
                }

                public Object getList_FreshEvaluation() {
                    return list_FreshEvaluation;
                }

                public void setList_FreshEvaluation(Object list_FreshEvaluation) {
                    this.list_FreshEvaluation = list_FreshEvaluation;
                }

                public int getTotalEvaluation() {
                    return totalEvaluation;
                }

                public void setTotalEvaluation(int totalEvaluation) {
                    this.totalEvaluation = totalEvaluation;
                }
            }
        }
    }
}
