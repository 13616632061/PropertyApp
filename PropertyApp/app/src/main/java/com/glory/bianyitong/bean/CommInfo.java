package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/8/22.
 */
public class CommInfo {

    int position;
    String path;
    //记录上传照片执行次数
    int hand;

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
