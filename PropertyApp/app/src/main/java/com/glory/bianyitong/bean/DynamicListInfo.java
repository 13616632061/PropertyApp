package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/10/20.
 */
public class DynamicListInfo {

    /**
     * neighboCommentID : 0
     * listNeighborhoodComment : [{"neighboCommentID":101,"neighborhoodID":42,"userid":60,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/sx%2BWmkKeYyE%2BO5ILuQ7jEg%3D%3D%2FuserHeader%2F9443ab985cd229fbea379f4bbb336293.jpg","commentDatetime":"2017-10-18T11:43:33","commentContent":"好像不是","commentToID":0,"likeCount":1,"likeStatu":-1,"listComment":[{"neighboCommentID":115,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T17:14:57","commentContent":"不是说","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":114,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T16:55:12","commentContent":"灌输给公司","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":113,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T16:54:53","commentContent":"别等你多久","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":102,"neighborhoodID":42,"userid":60,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/sx%2BWmkKeYyE%2BO5ILuQ7jEg%3D%3D%2FuserHeader%2F9443ab985cd229fbea379f4bbb336293.jpg","commentDatetime":"2017-10-18T11:43:55","commentContent":"你确定？","commentToID":101,"commentToUserID":60,"likeCount":0}],"reportStatu":0}]
     * statusCode : 1
     * alertMessage : 消息处理成功
     * currentPageNumber : 0
     * pageRowNumber : 2
     */

    private int neighboCommentID;
    private int statusCode;
    private String alertMessage;
    private int currentPageNumber;
    private int pageRowNumber;
    private List<ListNeighborhoodCommentBean> listNeighborhoodComment;

    public int getNeighboCommentID() {
        return neighboCommentID;
    }

    public void setNeighboCommentID(int neighboCommentID) {
        this.neighboCommentID = neighboCommentID;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageRowNumber() {
        return pageRowNumber;
    }

    public void setPageRowNumber(int pageRowNumber) {
        this.pageRowNumber = pageRowNumber;
    }

    public List<ListNeighborhoodCommentBean> getListNeighborhoodComment() {
        return listNeighborhoodComment;
    }

    public void setListNeighborhoodComment(List<ListNeighborhoodCommentBean> listNeighborhoodComment) {
        this.listNeighborhoodComment = listNeighborhoodComment;
    }

    public static class ListNeighborhoodCommentBean {
        /**
         * neighboCommentID : 101
         * neighborhoodID : 42
         * userid : 60
         * userPhoto : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/sx%2BWmkKeYyE%2BO5ILuQ7jEg%3D%3D%2FuserHeader%2F9443ab985cd229fbea379f4bbb336293.jpg
         * commentDatetime : 2017-10-18T11:43:33
         * commentContent : 好像不是
         * commentToID : 0
         * likeCount : 1
         * likeStatu : -1
         * listComment : [{"neighboCommentID":115,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T17:14:57","commentContent":"不是说","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":114,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T16:55:12","commentContent":"灌输给公司","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":113,"neighborhoodID":42,"userid":425,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg","commentDatetime":"2017-10-19T16:54:53","commentContent":"别等你多久","commentToID":101,"commentToUserID":60,"likeCount":0},{"neighboCommentID":102,"neighborhoodID":42,"userid":60,"userPhoto":"https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/sx%2BWmkKeYyE%2BO5ILuQ7jEg%3D%3D%2FuserHeader%2F9443ab985cd229fbea379f4bbb336293.jpg","commentDatetime":"2017-10-18T11:43:55","commentContent":"你确定？","commentToID":101,"commentToUserID":60,"likeCount":0}]
         * reportStatu : 0
         */

        private int neighboCommentID;
        private int neighborhoodID;
        private int userid;
        private String userPhoto;
        private String commentDatetime;
        private String commentContent;
        private int commentToID;
        private int likeCount;
        private int likeStatu;
        private int reportStatu;
        private String aesUserID;
        private String userName;
        private List<ListCommentBean> listComment;

        public String getAesUserID() {
            return aesUserID;
        }

        public void setAesUserID(String aesUserID) {
            this.aesUserID = aesUserID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getNeighboCommentID() {
            return neighboCommentID;
        }

        public void setNeighboCommentID(int neighboCommentID) {
            this.neighboCommentID = neighboCommentID;
        }

        public int getNeighborhoodID() {
            return neighborhoodID;
        }

        public void setNeighborhoodID(int neighborhoodID) {
            this.neighborhoodID = neighborhoodID;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public List<ListCommentBean> getListComment() {
            return listComment;
        }

        public void setListComment(List<ListCommentBean> listComment) {
            this.listComment = listComment;
        }

        public static class ListCommentBean {
            /**
             * neighboCommentID : 115
             * neighborhoodID : 42
             * userid : 425
             * userPhoto : https://glorygolflife.oss-cn-shenzhen.aliyuncs.com/Uj5Dh1t1%2FZlSPm9OpQAGgA%3D%3D%2FuserHeader%2Fab98d0d8a85ff21e4210b4c7bc501834.jpg
             * commentDatetime : 2017-10-19T17:14:57
             * commentContent : 不是说
             * commentToID : 101
             * commentToUserID : 60
             * likeCount : 0
             */

            private int neighboCommentID;
            private int neighborhoodID;
            private int userid;
            private String userPhoto;
            private String commentDatetime;
            private String commentContent;
            private int commentToID;
            private int commentToUserID;
            private int likeCount;
            private String userName;
            private String commentToUserName;
            private String aesCommentToUserID;
            private String aesUserID;

            public String getAesUserID() {
                return aesUserID;
            }

            public void setAesUserID(String aesUserID) {
                this.aesUserID = aesUserID;
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getNeighboCommentID() {
                return neighboCommentID;
            }

            public void setNeighboCommentID(int neighboCommentID) {
                this.neighboCommentID = neighboCommentID;
            }

            public int getNeighborhoodID() {
                return neighborhoodID;
            }

            public void setNeighborhoodID(int neighborhoodID) {
                this.neighborhoodID = neighborhoodID;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
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

            public int getCommentToUserID() {
                return commentToUserID;
            }

            public void setCommentToUserID(int commentToUserID) {
                this.commentToUserID = commentToUserID;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }
        }
    }
}
