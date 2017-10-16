package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class FreshSearchAdapter extends BaseQuickAdapter<ItemMenu<ResponseSearchFresh.ListfreshBean>,BaseViewHolder> {
    private Context context;

    public FreshSearchAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseSearchFresh.ListfreshBean>> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseSearchFresh.ListfreshBean> item) {
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);

        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFreshName());//商品名称
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getFreshPrice());//价格
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFreshContent());//简介
        Glide.with(context).load(item.getData().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);

//        if(!TextUtils.isEmpty(item.getData().getFreshPicture())){
//            String [] picArr=item.getData().getFreshPicture().split(",");
//            ServiceDialog.setPicture(picArr[0], imageView, null);
//        }else {
//
//        }
    }
}
