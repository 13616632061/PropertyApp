package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/29.
 * 公告详情查询
 */

public class RequestCommunityBulletin {
    private int bulletinId;//公告ID

    public RequestCommunityBulletin(int bulletinId) {
        this.bulletinId = bulletinId;
    }

    public int getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(int bulletinId) {
        this.bulletinId = bulletinId;
    }
}
