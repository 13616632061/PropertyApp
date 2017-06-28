package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 楼宇查询
 */

public class ResponseQueryBuild extends BaseResponseBean implements Serializable{

    private List<ListCommunityBuildingBean> listCommunityBuilding;

    public List<ListCommunityBuildingBean> getListCommunityBuilding() {
        return listCommunityBuilding;
    }

    public void setListCommunityBuilding(List<ListCommunityBuildingBean> listCommunityBuilding) {
        this.listCommunityBuilding = listCommunityBuilding;
    }

    public static class ListCommunityBuildingBean implements Serializable{
        /**
         * buildingID : 1
         * communityID : 1
         * communityName : 西丽小区
         * buildingName : 1号楼
         * buildingStatus : E
         */

        private int buildingID;
        private int communityID;
        private String communityName;
        private String buildingName;
        private String buildingStatus;

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
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

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getBuildingStatus() {
            return buildingStatus;
        }

        public void setBuildingStatus(String buildingStatus) {
            this.buildingStatus = buildingStatus;
        }
    }
}
