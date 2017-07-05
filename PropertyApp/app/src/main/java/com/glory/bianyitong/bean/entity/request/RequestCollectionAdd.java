package com.glory.bianyitong.bean.entity.request;

/**
 * Created by Administrator on 2017/7/5.
 * 添加收藏
 */

public class RequestCollectionAdd {
    private int freshID;//	int	生鲜ID
    private int FreshTypeID;//	int	生鲜类型ID

    public RequestCollectionAdd(int freshID, int freshTypeID) {
        this.freshID = freshID;
        FreshTypeID = freshTypeID;
    }

    public int getFreshID() {
        return freshID;
    }

    public void setFreshID(int freshID) {
        this.freshID = freshID;
    }

    public int getFreshTypeID() {
        return FreshTypeID;
    }

    public void setFreshTypeID(int freshTypeID) {
        FreshTypeID = freshTypeID;
    }
}
