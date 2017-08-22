package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/8/21.
 */
public class CommentInfo {

    /**
     * orderID : 35
     * freshID : 11
     * merchant_ID : 22
     * anonymous : d
     * evaluationLevel : 0
     * evaluationContext : 还好
     * listEvaluationPic : [{"evaluationID":0,"picturePath":"http"},{"evaluationID":0,"picturePath":"http"}]
     */

    private int orderID;
    private int freshID;
    private int merchant_ID;
    private String anonymous;
    private String evaluationLevel;
    private String evaluationContext;
    private List<ListEvaluationPicBean> listEvaluationPic;

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

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getEvaluationLevel() {
        return evaluationLevel;
    }

    public void setEvaluationLevel(String evaluationLevel) {
        this.evaluationLevel = evaluationLevel;
    }

    public String getEvaluationContext() {
        return evaluationContext;
    }

    public void setEvaluationContext(String evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    public List<ListEvaluationPicBean> getListEvaluationPic() {
        return listEvaluationPic;
    }

    public void setListEvaluationPic(List<ListEvaluationPicBean> listEvaluationPic) {
        this.listEvaluationPic = listEvaluationPic;
    }

    public static class ListEvaluationPicBean {
        /**
         * evaluationID : 0
         * picturePath : http
         */

        private int evaluationID=0;
        private String picturePath;

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
