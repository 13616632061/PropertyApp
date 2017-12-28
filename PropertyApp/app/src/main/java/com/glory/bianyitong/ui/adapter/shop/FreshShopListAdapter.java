package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 * 生鲜列表
 */

public class FreshShopListAdapter extends BaseQuickAdapter<ItemMenu<ResponseSearchFresh.ListfreshBean>,BaseViewHolder> {
    private Context context;
    public FreshShopListAdapter(@LayoutRes int layoutResId , @Nullable List<ItemMenu<ResponseSearchFresh.ListfreshBean>> data,Context context) {
            super(layoutResId, data);
            this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseSearchFresh.ListfreshBean> item) {
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFreshTypeLeaf());
        DecimalFormat df   = new DecimalFormat("######0.00");
        helper.setText(R.id.tv_list_item_goods_price,"¥"+df.format(item.getData().getFreshPrice()));
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
//        ServiceDialog.setPicture(item.getData().getFreshPicture(),imageView,null);
        Glide.with(context).load(item.getData().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
    }
}
