package com.glory.bianyitong.ui.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by billlamian on 17-8-10.
 * 订单列表多布局
 */

public class MultiItemView <T> implements MultiItemEntity {
    public static final int TITLE = 1;
    public static final int BODY = 2;
    public static final int FOOTER = 3;
    public static final int OPERATION = 4;
    private int itemType;
    private T data;
    private String msg1,msg2;//其他参数

    public MultiItemView(int itemType) {
        this.itemType = itemType;
    }

    public MultiItemView(int itemType,T data) {
        this.itemType = itemType;
        this.data=data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public T getData() {
        return data;
    }

    public MultiItemView(int itemType, String msg1, String msg2) {
        this.itemType = itemType;
        this.msg1 = msg1;
        this.msg2 = msg2;
    }

    public String getMsg1() {
        return msg1;
    }

    public String getMsg2() {
        return msg2;
    }
}
