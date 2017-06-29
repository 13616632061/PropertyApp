package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 * 物业管家查询
 */

public class ResponseQuerySteWard extends BaseResponseBean {

    private List<ListHousekeeperBean> listHousekeeper;

    public List<ListHousekeeperBean> getListHousekeeper() {
        return listHousekeeper;
    }

    public void setListHousekeeper(List<ListHousekeeperBean> listHousekeeper) {
        this.listHousekeeper = listHousekeeper;
    }

    public static class ListHousekeeperBean {
        /**
         * houseKepperID : 1
         * communityID : 1
         * communityName : 西丽小区
         * buildingID : 1
         * unitID : 1
         * unitName : A单元
         * buildingName : 1号楼
         * houseKeeperName : 物业中心
         * houseKepperPhoto : https://byt.bytsz.com.cn/images/head/index.jpg
         * workWeek : 2
         * workTime : 1
         * workPhoneNum : 13011112222
         */

        private int houseKepperID;
        private int communityID;
        private String communityName;
        private int buildingID;
        private int unitID;
        private String unitName;
        private String buildingName;
        private String houseKeeperName;
        private String houseKepperPhoto;
        private String workWeek;
        private String workTime;
        private String workPhoneNum;

        public int getHouseKepperID() {
            return houseKepperID;
        }

        public void setHouseKepperID(int houseKepperID) {
            this.houseKepperID = houseKepperID;
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

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
        }

        public int getUnitID() {
            return unitID;
        }

        public void setUnitID(int unitID) {
            this.unitID = unitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getHouseKeeperName() {
            return houseKeeperName;
        }

        public void setHouseKeeperName(String houseKeeperName) {
            this.houseKeeperName = houseKeeperName;
        }

        public String getHouseKepperPhoto() {
            return houseKepperPhoto;
        }

        public void setHouseKepperPhoto(String houseKepperPhoto) {
            this.houseKepperPhoto = houseKepperPhoto;
        }

        public String getWorkWeek() {
            return workWeek;
        }

        public void setWorkWeek(String workWeek) {
            this.workWeek = workWeek;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getWorkPhoneNum() {
            return workPhoneNum;
        }

        public void setWorkPhoneNum(String workPhoneNum) {
            this.workPhoneNum = workPhoneNum;
        }
    }
}
