package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 查询房间号
 */

public class ResponseQueryRoom extends BaseResponseBean {

    private List<ListCommunityRoomBean> listCommunityRoom;

    public List<ListCommunityRoomBean> getListCommunityRoom() {
        return listCommunityRoom;
    }

    public void setListCommunityRoom(List<ListCommunityRoomBean> listCommunityRoom) {
        this.listCommunityRoom = listCommunityRoom;
    }

    public static class ListCommunityRoomBean {
        /**
         * roomID : 147
         * communityID : 1
         * buildingID : 8
         * buildingName : 3号楼
         * communityName : 西丽小区
         * unitID : 7
         * unitName : B单元
         * roomName : 101
         */

        private int roomID;
        private int communityID;
        private int buildingID;
        private String buildingName;
        private String communityName;
        private int unitID;
        private String unitName;
        private String roomName;

        public int getRoomID() {
            return roomID;
        }

        public void setRoomID(int roomID) {
            this.roomID = roomID;
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

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
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

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }
    }
}
