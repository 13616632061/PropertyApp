package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.shop.AmountView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 * 提交订单
 */

public class FirmOrderAdapter extends BaseQuickAdapter<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>,BaseViewHolder> {
    private AmountView.OnAmountChangeListener amountChangeListener;
    public FirmOrderAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> data, AmountView.OnAmountChangeListener amountChangeListener) {
        super(layoutResId, data);
        this.amountChangeListener=amountChangeListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> item) {
        int position=helper.getAdapterPosition();
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFresh().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFresh().getFreshTypeName());
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getPrice());
        AmountView amountView=helper.getView(R.id.amount_view);
        amountView.setGoods_storage(1000);
        amountView.setAmount(item.getData().getQuantity());
        amountView.setPosition(position);
        amountView.setOnAmountChangeListener(amountChangeListener);
    }
}
