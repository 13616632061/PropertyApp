package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.OrderDetailsInfo;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.widght.shop.AmountView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 * 订单详情
 */

public class OrderDetailsAdapter extends BaseQuickAdapter<ItemMenu<OrderDetailsInfo.ListOrderBean.ListOrderDetailBean>,BaseViewHolder> {
    private Context context;
    public OrderDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<OrderDetailsInfo.ListOrderBean.ListOrderDetailBean>> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<OrderDetailsInfo.ListOrderBean.ListOrderDetailBean> item) {
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
//        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        Glide.with(context).load(item.getData().getFresh().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFresh().getFreshName());
        helper.setText(R.id.tv_details_money,"￥"+item.getData().getFresh().getFreshPrice());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFresh().getFreshTypeLeaf());
        helper.setText(R.id.tv_details_num,"×"+item.getData().getFreshQuantity());

        helper.addOnClickListener(R.id.order_list_item_opera_btn2);
    }
}
