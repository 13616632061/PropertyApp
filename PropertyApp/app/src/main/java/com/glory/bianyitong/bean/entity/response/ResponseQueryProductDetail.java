package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 * 商品详情
 */

public class ResponseQueryProductDetail extends BaseResponseBean implements Serializable{


    /**
     * listfresh : [{"freshID":11,"freshTypeID":118,"freshTypeName":"核果类","freshTypeLeaf":"四季鲜果","freshName":"石榴","freshPrice":11,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/Pomegranate.jpg","weight":"500.00","packingType":"保鲜膜包装","originID":15,"originName":"广东","shelfLife":"12个月","tag":"水果","nutritiveValue":"石榴中富含丰富的营养成分，能够很好补充人体所需营养元素，具有美容养颜抗衰老、还可以保护我们的眼睛的功效。另外常食用石榴还具有很好的抵抗细菌和病毒的强大作用，并且对一些皮肤病和癌症都有很好的治疗作用。","merchant_ID":29,"merchantName":"宝安44区生鲜店","isChoice":true,"choiceType":2,"enable":true,"freshContent":"千里山 云南蒙自甜石榴 6个 约2.5kg 特产","godownNumber":100,"listfreshPicture":[{"freshPictureID":299,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/1.jpg"},{"freshPictureID":300,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/2.jpg"},{"freshPictureID":301,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/3.jpg"},{"freshPictureID":302,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/4.jpg"},{"freshPictureID":303,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/5.jpg"}],"list_FreshEvaluation":[{"evaluationID":14,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":4,"evaluationDateTime":"2017-07-06T14:11:52","anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"freshPictureID":284,"freshID":8,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/西红柿/1.jpg"}],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0},{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":2,"evaluationDateTime":"2017-06-30T00:00:00","anonymous":null,"user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0},{"evaluationID":13,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","anonymous":null,"user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0}],"freshEvaluation":{"anonymous":null,"totalEvaluation":4,"praiseNum":3,"commentsNum":1,"badNum":0},"collectionStatu":false,"merchantTel":"4456654","freshContents":null}]
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
         * freshTypeID : 118
         * freshTypeName : 核果类
         * freshTypeLeaf : 四季鲜果
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
         * merchant_ID : 29
         * merchantName : 宝安44区生鲜店
         * isChoice : true
         * choiceType : 2
         * enable : true
         * freshContent : 千里山 云南蒙自甜石榴 6个 约2.5kg 特产
         * godownNumber : 100
         * listfreshPicture : [{"freshPictureID":299,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/1.jpg"},{"freshPictureID":300,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/2.jpg"},{"freshPictureID":301,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/3.jpg"},{"freshPictureID":302,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/4.jpg"},{"freshPictureID":303,"freshID":11,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/石榴/5.jpg"}]
         * list_FreshEvaluation : [{"evaluationID":14,"orderID":35,"freshID":11,"merchant_ID":22,"evaluationContext":"东西还可以，中评！","evaluationLevel":4,"evaluationDateTime":"2017-07-06T14:11:52","anonymous":"A**       ","user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"freshPictureID":284,"freshID":8,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/西红柿/1.jpg"}],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0},{"evaluationID":6,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":2,"evaluationDateTime":"2017-06-30T00:00:00","anonymous":null,"user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[{"pic_ID":1,"evaluationID":6,"picturePath":"http"},{"pic_ID":2,"evaluationID":6,"picturePath":"http"}],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0},{"evaluationID":13,"orderID":1,"freshID":11,"merchant_ID":22,"evaluationContext":" ","evaluationLevel":4,"evaluationDateTime":"2017-06-30T00:00:00","anonymous":null,"user":{"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"},"listEvaluationPic":[],"totalEvaluation":0,"praiseNum":0,"commentsNum":0,"badNum":0}]
         * freshEvaluation : {"anonymous":null,"totalEvaluation":4,"praiseNum":3,"commentsNum":1,"badNum":0}
         * collectionStatu : false
         * merchantTel : 4456654
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
        private String merchantName="";
        private boolean isChoice;
        private int choiceType;
        private boolean enable;
        private String freshContent;
        private int godownNumber;
        private FreshEvaluationBean freshEvaluation;
        private boolean collectionStatu;
        private String merchantTel;
        private Object freshContents;
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

        public String getFreshContent() {
            return freshContent;
        }

        public void setFreshContent(String freshContent) {
            this.freshContent = freshContent;
        }

        public int getGodownNumber() {
            return godownNumber;
        }

        public void setGodownNumber(int godownNumber) {
            this.godownNumber = godownNumber;
        }

        public FreshEvaluationBean getFreshEvaluation() {
            return freshEvaluation;
        }

        public void setFreshEvaluation(FreshEvaluationBean freshEvaluation) {
            this.freshEvaluation = freshEvaluation;
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

        public static class FreshEvaluationBean implements Serializable{
            /**
             * anonymous : null
             * totalEvaluation : 4
             * praiseNum : 3
             * commentsNum : 1
             * badNum : 0
             */

            private Object anonymous;
            private int totalEvaluation;
            private int praiseNum;
            private int commentsNum;
            private int badNum;

            public Object getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(Object anonymous) {
                this.anonymous = anonymous;
            }

            public int getTotalEvaluation() {
                return totalEvaluation;
            }

            public void setTotalEvaluation(int totalEvaluation) {
                this.totalEvaluation = totalEvaluation;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(int commentsNum) {
                this.commentsNum = commentsNum;
            }

            public int getBadNum() {
                return badNum;
            }

            public void setBadNum(int badNum) {
                this.badNum = badNum;
            }
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
             * evaluationID : 14
             * orderID : 35
             * freshID : 11
             * merchant_ID : 22
             * evaluationContext : 东西还可以，中评！
             * evaluationLevel : 4
             * evaluationDateTime : 2017-07-06T14:11:52
             * anonymous : A**
             * user : {"jgPushID":null,"jgPushName":null,"domain_UserID":null,"listUserRoleMapping":null,"status":null,"roles":null,"distribution":null,"loginName":"A","customerPhoto":"https://byt.bytsz.com.cn/images/head/Head.jpg"}
             * listEvaluationPic : [{"freshPictureID":284,"freshID":8,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/西红柿/1.jpg"}]
             * totalEvaluation : 0
             * praiseNum : 0
             * commentsNum : 0
             * badNum : 0
             */

            private int evaluationID;
            private int orderID;
            private int freshID;
            private int merchant_ID;
            private String evaluationContext="";
            private int evaluationLevel;
            private String evaluationDateTime;
            private String anonymous="";
            private UserBean user;
            private int totalEvaluation;
            private int praiseNum;
            private int commentsNum;
            private int badNum;
            private List<ListEvaluationPicBean> listEvaluationPic=new ArrayList<>();

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

            public int getTotalEvaluation() {
                return totalEvaluation;
            }

            public void setTotalEvaluation(int totalEvaluation) {
                this.totalEvaluation = totalEvaluation;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getCommentsNum() {
                return commentsNum;
            }

            public void setCommentsNum(int commentsNum) {
                this.commentsNum = commentsNum;
            }

            public int getBadNum() {
                return badNum;
            }

            public void setBadNum(int badNum) {
                this.badNum = badNum;
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
                 * domain_UserID : null
                 * listUserRoleMapping : null
                 * status : null
                 * roles : null
                 * distribution : null
                 * loginName : A
                 * customerPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
                 */

                private Object jgPushID;
                private Object jgPushName;
                private Object domain_UserID;
                private Object listUserRoleMapping;
                private Object status;
                private Object roles;
                private Object distribution;
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

                public Object getDomain_UserID() {
                    return domain_UserID;
                }

                public void setDomain_UserID(Object domain_UserID) {
                    this.domain_UserID = domain_UserID;
                }

                public Object getListUserRoleMapping() {
                    return listUserRoleMapping;
                }

                public void setListUserRoleMapping(Object listUserRoleMapping) {
                    this.listUserRoleMapping = listUserRoleMapping;
                }

                public Object getStatus() {
                    return status;
                }

                public void setStatus(Object status) {
                    this.status = status;
                }

                public Object getRoles() {
                    return roles;
                }

                public void setRoles(Object roles) {
                    this.roles = roles;
                }

                public Object getDistribution() {
                    return distribution;
                }

                public void setDistribution(Object distribution) {
                    this.distribution = distribution;
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
                 * freshPictureID : 284
                 * freshID : 8
                 * picturePath : https://byt.bytsz.com.cn/images/Fresh/西红柿/1.jpg
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
}
