package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/30.
 * 取消点赞
 */

public class RequestNeighborhoodLike {
    private int neighborhoodLikeID;//赞ID

    public RequestNeighborhoodLike(int neighborhoodLikeID) {
        this.neighborhoodLikeID = neighborhoodLikeID;
    }

    public int getNeighborhoodLikeID() {
        return neighborhoodLikeID;
    }

    public void setNeighborhoodLikeID(int neighborhoodLikeID) {
        this.neighborhoodLikeID = neighborhoodLikeID;
    }
}
