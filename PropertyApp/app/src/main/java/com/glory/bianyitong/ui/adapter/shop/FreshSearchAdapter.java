package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

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


    public FreshSearchAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseSearchFresh.ListfreshBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseSearchFresh.ListfreshBean> item) {
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);

        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFreshName());//商品名称
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getFreshPrice());//价格
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFreshContent());//简介

        if(!TextUtils.isEmpty(item.getData().getFreshPicture())){
            String [] picArr=item.getData().getFreshPicture().split(",");
            ServiceDialog.setPicture(picArr[0], imageView, null);
        }else {

        }
    }
}
