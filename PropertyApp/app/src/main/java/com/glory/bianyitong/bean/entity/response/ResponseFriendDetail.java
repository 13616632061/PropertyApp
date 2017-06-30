package com.glory.bianyitong.bean.entity.response;

import com.glory.bianyitong.bean.BaseResponseBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 * 近邻详情
 */

public class ResponseFriendDetail extends BaseResponseBean {

    private List<ListNeighborhoodBean> listNeighborhood;

    public List<ListNeighborhoodBean> getListNeighborhood() {
        return listNeighborhood;
    }

    public void setListNeighborhood(List<ListNeighborhoodBean> listNeighborhood) {
        this.listNeighborhood = listNeighborhood;
    }

    public static class ListNeighborhoodBean {
        /**
         * neighborhoodID : 33
         * userPhoto : 测试描述
         * communityID : 1
         * neighborhoodContent : 测试朋友圈
         * datetime : 2017-06-30T12:32:02
         * aesUserID : ZmViYn5kgv0BlGmwaiog9A==
         * listNeighborhoodPic : [{"neighborhoodPictureID":52,"neighborhoodID":33,"picturePath":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/surrounding/_1498797149496ReleasePhoto.jpg"},{"neighborhoodPictureID":53,"neighborhoodID":33,"picturePath":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/surrounding/_1498797149890ReleasePhoto.jpg"}]
         * commentCount : 0
         * likeCount : 0
         * likeStatu : 65
         * listNeighborhoodComment : []
         * reportStatu : 0
         */
        private String userName;
        private int neighborhoodID;
        private String userPhoto;
        private int communityID;
        private String neighborhoodContent;
        private String datetime;
        private String aesUserID;
        private int commentCount;
        private int likeCount;
        private int likeStatu;
        private int reportStatu;
        private List<ListNeighborhoodPicBean> listNeighborhoodPic;
        private List<ListNeighborhoodCommentBean> listNeighborhoodComment;


        public String getUserName() {
            return userName;
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

        public int getLikeStatu() {
            return likeStatu;
        }

        public void setLikeStatu(int likeStatu) {
            this.likeStatu = likeStatu;
        }

        public int getReportStatu() {
            return reportStatu;
        }

        public void setReportStatu(int reportStatu) {
            this.reportStatu = reportStatu;
        }

        public List<ListNeighborhoodPicBean> getListNeighborhoodPic() {
            return listNeighborhoodPic;
        }

        public void setListNeighborhoodPic(List<ListNeighborhoodPicBean> listNeighborhoodPic) {
            this.listNeighborhoodPic = listNeighborhoodPic;
        }

        public List<ListNeighborhoodCommentBean> getListNeighborhoodComment() {
            return listNeighborhoodComment;
        }

        public void setListNeighborhoodComment(List<ListNeighborhoodCommentBean> listNeighborhoodComment) {
            this.listNeighborhoodComment = listNeighborhoodComment;
        }



        public static class ListNeighborhoodPicBean {
            /**
             * neighborhoodPictureID : 52
             * neighborhoodID : 33
             * picturePath : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/surrounding/_1498797149496ReleasePhoto.jpg
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

        public static class ListNeighborhoodCommentBean {
            /**
             * neighboCommentID : 70
             * neighborhoodID : 33
             * userName : 测试用户1234
             * userPhoto : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/ZmViYn5kgv0BlGmwaiog9A==/surrounding/_1498797149496ReleasePhoto.jpg
             * commentDatetime : 2017-06-30T14:48:25
             * commentContent : 评论一下测试
             * commentToID : 0
             * aesUserID : ZmViYn5kgv0BlGmwaiog9A==
             * commentToUserName :
             * likeCount : 0
             * likeStatu : -1
             * listComment : []
             * reportStatu : 0
             */

            private int neighboCommentID;
            @SerializedName("neighborhoodID")
            private int neighborhoodIDX;
            private String userName;
            @SerializedName("userPhoto")
            private String userPhoto;
            private String commentDatetime;
            private String commentContent;
            private int commentToID;
            @SerializedName("aesUserID")
            private String aesUserIDX;
            private String commentToUserName;
            @SerializedName("likeCount")
            private int likeCount;
            @SerializedName("likeStatu")
            private int likeStatuX;
            @SerializedName("reportStatu")
            private int reportStatuX;

            public List<ListCommentBean> getListComment() {
                return listComment;
            }

            public void setListComment(List<ListCommentBean> listComment) {
                this.listComment = listComment;
            }

            @SerializedName("listComment")
            private List<ListCommentBean> listComment;

            public int getNeighboCommentID() {
                return neighboCommentID;
            }

            public void setNeighboCommentID(int neighboCommentID) {
                this.neighboCommentID = neighboCommentID;
            }

            public int getNeighborhoodIDX() {
                return neighborhoodIDX;
            }

            public void setNeighborhoodIDX(int neighborhoodIDX) {
                this.neighborhoodIDX = neighborhoodIDX;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getCommentDatetime() {
                return commentDatetime;
            }

            public void setCommentDatetime(String commentDatetime) {
                this.commentDatetime = commentDatetime;
            }

            public String getCommentContent() {
                return commentContent;
            }

            public void setCommentContent(String commentContent) {
                this.commentContent = commentContent;
            }

            public int getCommentToID() {
                return commentToID;
            }

            public void setCommentToID(int commentToID) {
                this.commentToID = commentToID;
            }

            public String getAesUserIDX() {
                return aesUserIDX;
            }

            public void setAesUserIDX(String aesUserIDX) {
                this.aesUserIDX = aesUserIDX;
            }

            public String getCommentToUserName() {
                return commentToUserName;
            }

            public void setCommentToUserName(String commentToUserName) {
                this.commentToUserName = commentToUserName;
            }

            public int getLikeCountX() {
                return likeCount;
            }

            public void setLikeCountX(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getLikeStatuX() {
                return likeStatuX;
            }

            public void setLikeStatuX(int likeStatuX) {
                this.likeStatuX = likeStatuX;
            }

            public int getReportStatuX() {
                return reportStatuX;
            }

            public void setReportStatuX(int reportStatuX) {
                this.reportStatuX = reportStatuX;
            }

            public static class ListCommentBean {
                /**
                 * neighboCommentID : 52
                 * neighborhoodID : 23
                 * userName : 曲靖
                 * userPhoto : https://byt.bytsz.com.cn/images/head/Head.jpg
                 * commentDatetime : 2017-04-03T00:00:00
                 * commentContent : 神经
                 * commentToID : 47
                 * aesUserID : Hgbs7NKcvYJGMp9TQRLF3A==
                 * aesCommentToUserID : iJscwOsnF3uL94MFlE2A+g==
                 * commentToUserName : 严格
                 * likeCount : 0
                 */

                private int neighboCommentID;
                @SerializedName("neighborhoodID")
                private int neighborhoodIDX;
                @SerializedName("userName")
                private String userNameX;
                @SerializedName("userPhoto")
                private String userPhotoX;
                private String commentDatetime;
                private String commentContent;
                private int commentToID;
                @SerializedName("aesUserID")
                private String aesUserIDX;
                private String aesCommentToUserID;
                private String commentToUserName;
                @SerializedName("likeCount")
                private int likeCountX;

                public int getNeighboCommentID() {
                    return neighboCommentID;
                }

                public void setNeighboCommentID(int neighboCommentID) {
                    this.neighboCommentID = neighboCommentID;
                }

                public int getNeighborhoodIDX() {
                    return neighborhoodIDX;
                }

                public void setNeighborhoodIDX(int neighborhoodIDX) {
                    this.neighborhoodIDX = neighborhoodIDX;
                }

                public String getUserNameX() {
                    return userNameX;
                }

                public void setUserNameX(String userNameX) {
                    this.userNameX = userNameX;
                }

                public String getUserPhotoX() {
                    return userPhotoX;
                }

                public void setUserPhotoX(String userPhotoX) {
                    this.userPhotoX = userPhotoX;
                }

                public String getCommentDatetime() {
                    return commentDatetime;
                }

                public void setCommentDatetime(String commentDatetime) {
                    this.commentDatetime = commentDatetime;
                }

                public String getCommentContent() {
                    return commentContent;
                }

                public void setCommentContent(String commentContent) {
                    this.commentContent = commentContent;
                }

                public int getCommentToID() {
                    return commentToID;
                }

                public void setCommentToID(int commentToID) {
                    this.commentToID = commentToID;
                }

                public String getAesUserIDX() {
                    return aesUserIDX;
                }

                public void setAesUserIDX(String aesUserIDX) {
                    this.aesUserIDX = aesUserIDX;
                }

                public String getAesCommentToUserID() {
                    return aesCommentToUserID;
                }

                public void setAesCommentToUserID(String aesCommentToUserID) {
                    this.aesCommentToUserID = aesCommentToUserID;
                }

                public String getCommentToUserName() {
                    return commentToUserName;
                }

                public void setCommentToUserName(String commentToUserName) {
                    this.commentToUserName = commentToUserName;
                }

                public int getLikeCountX() {
                    return likeCountX;
                }

                public void setLikeCountX(int likeCountX) {
                    this.likeCountX = likeCountX;
                }
            }
        }

        }


}
