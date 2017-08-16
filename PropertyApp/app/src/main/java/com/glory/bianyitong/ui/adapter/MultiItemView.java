package com.glory.bianyitong.ui.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;

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
    private int status;
    private long ordeId;
    private float totalMoney;//订单总金额
    private ResponseQueryOrderList.ListOrderBean  bean;//评论需要数据

    public MultiItemView(int itemType) {
        this.itemType = itemType;
    }

    public MultiItemView(int itemType,T data,int status) {
        this.itemType = itemType;
        this.data=data;
        this.status=status;
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


    public MultiItemView(int itemType, String msg1, String msg2, int status) {
        this.itemType = itemType;
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.status = status;
    }

    /**
     *
     * @param itemType
     * @param msg1
     * @param msg2
     * @param status
     * @param ordeId 订单ID
     */
    public MultiItemView(int itemType, String msg1, String msg2, int status,long ordeId,float totalMoney,ResponseQueryOrderList.ListOrderBean  bean) {
        this.itemType = itemType;
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.status = status;
        this.ordeId=ordeId;
        this.totalMoney=totalMoney;
        this.bean=bean;
    }

    public String getMsg1() {
        return msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public int getStatus() {
        return status;
    }

    public long getOrdeId() {
        return ordeId;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public ResponseQueryOrderList.ListOrderBean   getBean() {
        return bean;
    }
}
