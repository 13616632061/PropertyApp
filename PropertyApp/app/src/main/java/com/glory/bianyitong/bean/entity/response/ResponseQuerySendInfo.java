package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 * 查询发布
 */

public class ResponseQuerySendInfo extends BaseResponseBean {

    private List<ListNeighborhoodBean> listNeighborhood;

    public List<ListNeighborhoodBean> getListNeighborhood() {
        return listNeighborhood;
    }

    public void setListNeighborhood(List<ListNeighborhoodBean> listNeighborhood) {
        this.listNeighborhood = listNeighborhood;
    }

    public static class ListNeighborhoodBean {
        /**
         * neighborhoodID : 31
         * userPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
         * communityID : 1
         * neighborhoodContent : 发布测试
         * datetime : 2017-06-28T00:00:00
         * aesUserID : ZmViYn5kgv0BlGmwaiog9A==
         * listNeighborhoodPic : [{"neighborhoodPictureID":49,"neighborhoodID":31,"picturePath":"https://byt.bytsz.com.cn/images/Neighborhood/5/3.jpg"},{"neighborhoodPictureID":50,"neighborhoodID":31,"picturePath":"https://byt.bytsz.com.cn/images/Neighborhood/5/4.jpg"}]
         * commentCount : 0
         * likeCount : 0
         */

        private int neighborhoodID;
        private String userPhoto;
        private int communityID;
        private String neighborhoodContent;
        private String datetime;
        private String aesUserID;
        private int commentCount;
        private int likeCount;
        private List<ListNeighborhoodPicBean> listNeighborhoodPic;

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

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getNeighborhoodContent() {
            return neighborhoodContent;
        }

        public void setNeighborhoodContent(String neighborhoodContent) {
            this.neighborhoodContent = neighborhoodContent;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getAesUserID() {
            return aesUserID;
        }

        public void setAesUserID(String aesUserID) {
            this.aesUserID = aesUserID;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public List<ListNeighborhoodPicBean> getListNeighborhoodPic() {
            return listNeighborhoodPic;
        }

        public void setListNeighborhoodPic(List<ListNeighborhoodPicBean> listNeighborhoodPic) {
            this.listNeighborhoodPic = listNeighborhoodPic;
        }

        public static class ListNeighborhoodPicBean {
            /**
             * neighborhoodPictureID : 49
             * neighborhoodID : 31
             * picturePath : https://byt.bytsz.com.cn/images/Neighborhood/5/3.jpg
             */

            private int neighborhoodPictureID;
            private int neighborhoodID;
            private String picturePath;

            public int getNeighborhoodPictureID() {
                return neighborhoodPictureID;
            }

            public void setNeighborhoodPictureID(int neighborhoodPictureID) {
                this.neighborhoodPictureID = neighborhoodPictureID;
            }

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
    }
}
