package com.glory.bianyitong.bean;

import com.glory.bianyitong.bean.entity.response.ResponseQueryYelloPage;

import java.util.List;

/**
 * Created by lucy on 2017/2/21.
 */
public class YellowPageAllInfo extends BaseResponseBean{
    private int yellowPageGroupID;
    private String yellowPageGroupName;
    private String yellowPageGroupLeaf;
    private String yellowPageGroupPicture;
    private List<ListYellowPageBean> listYellowPage;

    public ResponseQueryYelloPage.ListYellowPageGroupBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ResponseQueryYelloPage.ListYellowPageGroupBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    private ResponseQueryYelloPage.ListYellowPageGroupBean pageInfo;

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

    public String getYellowPageGroupLeaf() {
        return yellowPageGroupLeaf;
    }

    public void setYellowPageGroupLeaf(String yellowPageGroupLeaf) {
        this.yellowPageGroupLeaf = yellowPageGroupLeaf;
    }

    public String getYellowPageGroupPicture() {
        return yellowPageGroupPicture;
    }

    public void setYellowPageGroupPicture(String yellowPageGroupPicture) {
        this.yellowPageGroupPicture = yellowPageGroupPicture;
    }
    public List<ListYellowPageBean> getListYellowPage() {
        return listYellowPage;
    }

    public void setListYellowPage(List<ListYellowPageBean> listYellowPage) {
        this.listYellowPage = listYellowPage;
    }
    public static class ListYellowPageBean {
        /**
         * yellowPageID : 1
         * yellowPageGroupID : 1
         * yellowPageGroupName : 警务
         * yellowPageGroupLeaf : 2
         * yellowPageTEL : 18800000000
         * communityID : 1
         * communityName : 西丽小区
         * yellowPageContext : 西丽派出所
         */

        private int yellowPageID;
        private int yellowPageGroupID;
        private String yellowPageGroupName;
        private int yellowPageGroupLeaf;
        private String yellowPageTEL;
        private int communityID;
        private String communityName;
        private String yellowPageContext;

        public String getYellowPageGroupPicture() {
            return yellowPageGroupPicture;
        }

        public void setYellowPageGroupPicture(String yellowPageGroupPicture) {
            this.yellowPageGroupPicture = yellowPageGroupPicture;
        }

        private String yellowPageGroupPicture;

        public int getYellowPageID() {
            return yellowPageID;
        }

        public void setYellowPageID(int yellowPageID) {
            this.yellowPageID = yellowPageID;
        }

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

        public String getYellowPageTEL() {
            return yellowPageTEL;
        }

        public void setYellowPageTEL(String yellowPageTEL) {
            this.yellowPageTEL = yellowPageTEL;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getYellowPageContext() {
            return yellowPageContext;
        }

        public void setYellowPageContext(String yellowPageContext) {
            this.yellowPageContext = yellowPageContext;
        }
    }

}
