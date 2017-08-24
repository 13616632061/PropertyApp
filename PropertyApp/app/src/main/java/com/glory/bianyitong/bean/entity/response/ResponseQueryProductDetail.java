package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 * 商品详情
 */

public class ResponseQueryProductDetail extends BaseResponseBean implements Serializable{


    /**
     * listfresh : [{"freshID":11,"freshTypeID":14,"freshTypeName":"石榴","freshTypeLeaf":"水果","freshName":"石榴","freshPrice":11,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg","weight":"500.00","packingType":"保鲜膜包装","originID":15,"originName":"广东","shelfLife":"12个月","tag":"水果","nutritiveValue":"石榴中富含丰富的营养成分，能够很好补充人体所需营养元素，具有美容养颜抗衰老、还可以保护我们的眼睛的功效。另外常食用石榴还具有很好的抵抗细菌和病毒的强大作用，并且对一些皮肤病和癌症都有很好的治疗作用。","merchant_ID":22,"merchantName":"宜家生鲜店","isChoice":true,"choiceType":2,"enable":true,"sortingName":"","freshContent":"千里山 云南蒙自甜石榴 6个 约2.5kg 特产","listfreshPicture":[{"freshPictureID":299,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/1.jpg"},{"freshPictureID":300,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/2.jpg"},{"freshPictureID":301,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/3.jpg"},{"freshPictureID":302,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/4.jpg"},{"freshPictureID":303,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/5.jpg"}],"list_FreshEvaluation":[{"evaluationID":17,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:14:26","endEvaluationDateTime":null,"anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":9,"evaluationID":20,"picturePath":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/suggestion/_1503372877474_SuggestPhoto.jpg"}]},{"evaluationID":14,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:11:52","endEvaluationDateTime":null,"anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[]},{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}]},{"evaluationID":13,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[]}],"totalEvaluation":4,"praiseEvaluation":"50%","collectionStatu":false,"merchantTel":"135011","number":4,"freshDetail":null,"freshContents":null}]
     * tags : null
     */

    private Object tags;
    private List<ListfreshBean> listfresh;

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public List<ListfreshBean> getListfresh() {
        return listfresh;
    }

    public void setListfresh(List<ListfreshBean> listfresh) {
        this.listfresh = listfresh;
    }

    public static class ListfreshBean {
        /**
         * freshID : 11
         * freshTypeID : 14
         * freshTypeName : 石榴
         * freshTypeLeaf : 水果
         * freshName : 石榴
         * freshPrice : 11
         * freshPicture : https://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg
         * weight : 500.00
         * packingType : 保鲜膜包装
         * originID : 15
         * originName : 广东
         * shelfLife : 12个月
         * tag : 水果
         * nutritiveValue : 石榴中富含丰富的营养成分，能够很好补充人体所需营养元素，具有美容养颜抗衰老、还可以保护我们的眼睛的功效。另外常食用石榴还具有很好的抵抗细菌和病毒的强大作用，并且对一些皮肤病和癌症都有很好的治疗作用。
         * merchant_ID : 22
         * merchantName : 宜家生鲜店
         * isChoice : true
         * choiceType : 2
         * enable : true
         * sortingName :
         * freshContent : 千里山 云南蒙自甜石榴 6个 约2.5kg 特产
         * listfreshPicture : [{"freshPictureID":299,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/1.jpg"},{"freshPictureID":300,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/2.jpg"},{"freshPictureID":301,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/3.jpg"},{"freshPictureID":302,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/4.jpg"},{"freshPictureID":303,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/5.jpg"}]
         * list_FreshEvaluation : [{"evaluationID":17,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:14:26","endEvaluationDateTime":null,"anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":9,"evaluationID":20,"picturePath":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/suggestion/_1503372877474_SuggestPhoto.jpg"}]},{"evaluationID":14,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":0,"evaluationDateTime":"2017-07-06T14:11:52","endEvaluationDateTime":null,"anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[]},{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}]},{"evaluationID":13,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","endEvaluationDateTime":null,"user":{"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[]}]
         * totalEvaluation : 4
         * praiseEvaluation : 50%
         * collectionStatu : false
         * merchantTel : 135011
         * number : 4
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
        private int totalEvaluation;
        private String praiseEvaluation;
        private boolean collectionStatu;
        private String merchantTel;
        private int number;
        private Object freshDetail;
        private Object freshContents="";
        private List<ListfreshPictureBean> listfreshPicture;
        private List<ListFreshEvaluationBean> list_FreshEvaluation;

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

        public int getTotalEvaluation() {
            return totalEvaluation;
        }

        public void setTotalEvaluation(int totalEvaluation) {
            this.totalEvaluation = totalEvaluation;
        }

        public String getPraiseEvaluation() {
            return praiseEvaluation;
        }

        public void setPraiseEvaluation(String praiseEvaluation) {
            this.praiseEvaluation = praiseEvaluation;
        }

        public boolean isCollectionStatu() {
            return collectionStatu;
        }

        public void setCollectionStatu(boolean collectionStatu) {
            this.collectionStatu = collectionStatu;
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

        public List<ListFreshEvaluationBean> getList_FreshEvaluation() {
            return list_FreshEvaluation;
        }

        public void setList_FreshEvaluation(List<ListFreshEvaluationBean> list_FreshEvaluation) {
            this.list_FreshEvaluation = list_FreshEvaluation;
        }

        public static class ListfreshPictureBean {
            /**
             * freshPictureID : 299
             * freshID : 11
             * picturePath : https://byt.bytsz.com.cn/images/Fresh/石榴/1.jpg
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

        public static class ListFreshEvaluationBean {
            /**
             * evaluationID : 17
             * orderID : 35
             * freshID : 11
             * merchant_ID : 22
             * evaluationContext : 东西还可以，中评！
             * evaluationLevel : 0
             * evaluationDateTime : 2017-07-06T14:14:26
             * endEvaluationDateTime : null
             * anonymous : A**
             * user : {"jgPushID":null,"jgPushName":null,"status":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"}
             * listEvaluationPic : [{"pic_ID":9,"evaluationID":20,"picturePath":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/suggestion/_1503372877474_SuggestPhoto.jpg"}]
             */

            private int evaluationID;
            private int orderID;
            private int freshID;
            private int merchant_ID;
            private String evaluationContext;
            private int evaluationLevel;
            private String evaluationDateTime;
            private Object endEvaluationDateTime;
            private String anonymous="";
            private UserBean user;
            private List<ListEvaluationPicBean> listEvaluationPic;

            public int getEvaluationID() {
                return evaluationID;
            }

            public void setEvaluationID(int evaluationID) {
                this.evaluationID = evaluationID;
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

            public int getMerchant_ID() {
                return merchant_ID;
            }

            public void setMerchant_ID(int merchant_ID) {
                this.merchant_ID = merchant_ID;
            }

            public String getEvaluationContext() {
                return evaluationContext;
            }

            public void setEvaluationContext(String evaluationContext) {
                this.evaluationContext = evaluationContext;
            }

            public int getEvaluationLevel() {
                return evaluationLevel;
            }

            public void setEvaluationLevel(int evaluationLevel) {
                this.evaluationLevel = evaluationLevel;
            }

            public String getEvaluationDateTime() {
                return evaluationDateTime;
            }

            public void setEvaluationDateTime(String evaluationDateTime) {
                this.evaluationDateTime = evaluationDateTime;
            }

            public Object getEndEvaluationDateTime() {
                return endEvaluationDateTime;
            }

            public void setEndEvaluationDateTime(Object endEvaluationDateTime) {
                this.endEvaluationDateTime = endEvaluationDateTime;
            }

            public String getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(String anonymous) {
                this.anonymous = anonymous;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<ListEvaluationPicBean> getListEvaluationPic() {
                return listEvaluationPic;
            }

            public void setListEvaluationPic(List<ListEvaluationPicBean> listEvaluationPic) {
                this.listEvaluationPic = listEvaluationPic;
            }

            public static class UserBean {
                /**
                 * jgPushID : null
                 * jgPushName : null
                 * status : null
                 * loginName : A
                 * customerPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
                 */

                private Object jgPushID;
                private Object jgPushName;
                private Object status;
                private String loginName;
                private String customerPhoto;

                public Object getJgPushID() {
                    return jgPushID;
                }

                public void setJgPushID(Object jgPushID) {
                    this.jgPushID = jgPushID;
                }

                public Object getJgPushName() {
                    return jgPushName;
                }

                public void setJgPushName(Object jgPushName) {
                    this.jgPushName = jgPushName;
                }

                public Object getStatus() {
                    return status;
                }

                public void setStatus(Object status) {
                    this.status = status;
                }

                public String getLoginName() {
                    return loginName;
                }

                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getCustomerPhoto() {
                    return customerPhoto;
                }

                public void setCustomerPhoto(String customerPhoto) {
                    this.customerPhoto = customerPhoto;
                }
            }

            public static class ListEvaluationPicBean {
                /**
                 * pic_ID : 9
                 * evaluationID : 20
                 * picturePath : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/suggestion/_1503372877474_SuggestPhoto.jpg
                 */

                private int pic_ID;
                private int evaluationID;
                private String picturePath;

                public int getPic_ID() {
                    return pic_ID;
                }

                public void setPic_ID(int pic_ID) {
                    this.pic_ID = pic_ID;
                }

                public int getEvaluationID() {
                    return evaluationID;
                }

                public void setEvaluationID(int evaluationID) {
                    this.evaluationID = evaluationID;
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
}
