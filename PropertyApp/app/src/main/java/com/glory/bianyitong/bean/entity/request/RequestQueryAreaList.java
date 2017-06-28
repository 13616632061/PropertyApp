package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/6/28.
 * 搜索小区
 */

public class RequestQueryAreaList {
    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    private String communityName;

    public RequestQueryAreaList(String communityName) {
        this.communityName = communityName;
    }
}
