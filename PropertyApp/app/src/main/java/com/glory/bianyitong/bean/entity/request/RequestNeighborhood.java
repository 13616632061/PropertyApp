package com.glory.bianyitong.bean.entity.request;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 * 近邻查询
 */

public class RequestNeighborhood {
    private String aesUserID;//用户ID
    private int neighborhoodID;//近邻ID

    private String userPhoto;//	String	用户头像地址
    private String neighborhoodContent;//	String	近邻详细内容
    private List<ListNeighborhoodPic> listNeighborhoodPic;//近邻图片集合

    public RequestNeighborhood(String aesUserID) {
        this.aesUserID = aesUserID;
    }
    public RequestNeighborhood(int neighborhoodID) {
        this.neighborhoodID = neighborhoodID;
    }

    public RequestNeighborhood( String userPhoto, String neighborhoodContent, List<ListNeighborhoodPic> listNeighborhoodPic) {

        this.userPhoto = userPhoto;
        this.neighborhoodContent = neighborhoodContent;
        this.listNeighborhoodPic = listNeighborhoodPic;
    }

    public static class  ListNeighborhoodPic{
        private int neighborhoodID;// 0,
        private String picturePath;//"http:/dgf.jpg"

        public int getNeighborhoodID() {
            return neighborhoodID;
        }

        public void setNeighborhoodID(int neighborhoodID) {
            this.neighborhoodID = neighborhoodID;
        }

        public String getPicturePath() {
            return picturePath;
        }

        public void setPicturePath(String picturePath) {
            this.picturePath = picturePath;
        }
    }

    public String getAesUserID() {
        return aesUserID;
    }

    public void setAesUserID(String aesUserID) {
        this.aesUserID = aesUserID;
    }

    public int getNeighborhoodID() {
        return neighborhoodID;
    }

    public void setNeighborhoodID(int neighborhoodID) {
        this.neighborhoodID = neighborhoodID;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getNeighborhoodContent() {
        return neighborhoodContent;
    }

    public void setNeighborhoodContent(String neighborhoodContent) {
        this.neighborhoodContent = neighborhoodContent;
    }


    public List<ListNeighborhoodPic> getListNeighborhoodPic() {
        return listNeighborhoodPic;
    }

    public void setListNeighborhoodPic(List<ListNeighborhoodPic> listNeighborhoodPic) {
        this.listNeighborhoodPic = listNeighborhoodPic;
    }
}
