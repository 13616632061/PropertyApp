package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 查询单元
 */

public class ResponseQueryUnit extends BaseResponseBean {

    private List<ListCommunityUnitBean> listCommunityUnit;

    public List<ListCommunityUnitBean> getListCommunityUnit() {
        return listCommunityUnit;
    }

    public void setListCommunityUnit(List<ListCommunityUnitBean> listCommunityUnit) {
        this.listCommunityUnit = listCommunityUnit;
    }

    public static class ListCommunityUnitBean {
        /**
         * unitID : 6
         * communityID : 1
         * buildingID : 8
         * unitName : A单元
         * communityName : 西丽小区
         * buildingName : 3号楼
         */

        private int unitID;
        private int communityID;
        private int buildingID;
        private String unitName;
        private String communityName;
        private String buildingName;

        public int getUnitID() {
            return unitID;
        }

        public void setUnitID(int unitID) {
            this.unitID = unitID;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
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
    }
}
