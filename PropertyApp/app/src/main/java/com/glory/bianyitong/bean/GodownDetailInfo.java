package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/9/14.
 */
public class GodownDetailInfo {

    private List<ListDetailBean> listDetail;

    public List<ListDetailBean> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<ListDetailBean> listDetail) {
        this.listDetail = listDetail;
    }

    public static class ListDetailBean {
        /**
         * freshID : 2
         * freshQuantity : 10
         */

        private int freshID;
        private int freshQuantity;

        public ListDetailBean(int freshID, int freshQuantity) {
            this.freshID = freshID;
            this.freshQuantity = freshQuantity;
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
    }
}
