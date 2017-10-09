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
    private int orderId;
    private double totalMoney;//订单总金额
    private float freight;//运费
    private float orderPaidPrice;//订单实付金额
    private int cartNum;//订单商品数量
    private long orderCode;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setBean(ResponseQueryOrderList.ListOrderBean bean) {
        this.bean = bean;
    }

    public long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(long orderCode) {
        this.orderCode = orderCode;
    }

    public static int getTITLE() {
        return TITLE;
    }

    public float getOrderPaidPrice() {
        return orderPaidPrice;
    }

    public void setOrderPaidPrice(float orderPaidPrice) {
        this.orderPaidPrice = orderPaidPrice;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

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
     * @param orderId 订单ID
     */
    public MultiItemView(int itemType, String msg1, String msg2, int status,int orderId,double totalMoney,ResponseQueryOrderList.ListOrderBean  bean) {
        this.itemType = itemType;
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.status = status;
        this.orderId=orderId;
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


    public double getTotalMoney() {
        return totalMoney;
    }

    public ResponseQueryOrderList.ListOrderBean   getBean() {
        return bean;
    }
}
