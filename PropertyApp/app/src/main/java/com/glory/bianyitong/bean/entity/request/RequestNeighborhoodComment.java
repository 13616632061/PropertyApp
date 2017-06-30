package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/30.
 * 用户评论
 */

public class RequestNeighborhoodComment {
    private int neighborhoodID;//	jint	近邻ID
    private String userPhoto;//	String	用户头像地址
    private String commentContent;//	String	评论内容
    private int commentToID;//	int	递归字段 如果为0则说明评论近邻内容，否则是评论是评论的用户评论
    private String userPicture;
    private String aesCommentToUserID;//	String	评论用户ID（加密）
    private int neighborhoodLikeID;//点赞ID


    public RequestNeighborhoodComment(int neighborhoodID, String userPicture, int commentToID) {
        this.neighborhoodID = neighborhoodID;
        this.userPicture = userPicture;
        this.commentToID = commentToID;
    }

    public RequestNeighborhoodComment(int neighborhoodID, String userPhoto, String commentContent, int commentToID, String aesCommentToUserID) {
        this.neighborhoodID = neighborhoodID;
        this.userPhoto = userPhoto;
        this.commentContent = commentContent;
        this.commentToID = commentToID;
        this.aesCommentToUserID = aesCommentToUserID;
    }

    public RequestNeighborhoodComment(int neighborhoodLikeID) {
        this.neighborhoodLikeID=neighborhoodLikeID=neighborhoodLikeID;
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

    public String getAesCommentToUserID() {
        return aesCommentToUserID;
    }

    public void setAesCommentToUserID(String aesCommentToUserID) {
        this.aesCommentToUserID = aesCommentToUserID;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public int getNeighborhoodLikeID() {
        return neighborhoodLikeID;
    }

    public void setNeighborhoodLikeID(int neighborhoodLikeID) {
        this.neighborhoodLikeID = neighborhoodLikeID;
    }
}
