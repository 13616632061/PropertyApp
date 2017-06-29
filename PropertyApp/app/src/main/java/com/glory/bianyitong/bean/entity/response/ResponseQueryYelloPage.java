package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 * 查询黄页分组
 */

public class ResponseQueryYelloPage extends BaseResponseBean {

    private List<ListYellowPageGroupBean> listYellowPageGroup;

    public List<ListYellowPageGroupBean> getListYellowPageGroup() {
        return listYellowPageGroup;
    }

    public void setListYellowPageGroup(List<ListYellowPageGroupBean> listYellowPageGroup) {
        this.listYellowPageGroup = listYellowPageGroup;
    }

    public static class ListYellowPageGroupBean {
        /**
         * yellowPageGroupID : 1
         * yellowPageGroupName : 警务
         * yellowPageGroupLeaf : 0
         * yellowPageGroupPicture : https://byt.bytsz.com.cn/images/YellowPageGroup/iconpoliceservice.png
         */

        private int yellowPageGroupID;
        private String yellowPageGroupName;
        private int yellowPageGroupLeaf;
        private String yellowPageGroupPicture;

        public int getYellowPageGroupID() {
            return yellowPageGroupID;
        }

        public void setYellowPageGroupID(int yellowPageGroupID) {
            this.yellowPageGroupID = yellowPageGroupID;
        }

        public String getYellowPageGroupName() {
            return yellowPageGroupName;
        }

        public void setYellowPageGroupName(String yellowPageGroupName) {
            this.yellowPageGroupName = yellowPageGroupName;
        }

        public int getYellowPageGroupLeaf() {
            return yellowPageGroupLeaf;
        }

        public void setYellowPageGroupLeaf(int yellowPageGroupLeaf) {
            this.yellowPageGroupLeaf = yellowPageGroupLeaf;
        }

        public String getYellowPageGroupPicture() {
            return yellowPageGroupPicture;
        }

        public void setYellowPageGroupPicture(String yellowPageGroupPicture) {
            this.yellowPageGroupPicture = yellowPageGroupPicture;
        }
    }
}
