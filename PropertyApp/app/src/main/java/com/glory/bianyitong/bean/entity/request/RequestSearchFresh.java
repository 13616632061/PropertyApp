package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/4.
 * 输入框查询商品列表
 */

public class RequestSearchFresh {
    private String freshName;//关键字，标签

    public RequestSearchFresh(String freshName) {
        this.freshName = freshName;
    }

    public String getFreshName() {
        return freshName;
    }

    public void setFreshName(String freshName) {
        this.freshName = freshName;
    }
}
