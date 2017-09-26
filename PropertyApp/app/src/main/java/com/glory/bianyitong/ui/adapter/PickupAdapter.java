package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.PickupInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.AddAwardActivity;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2017/5/3.
 * 取件
 */
public class PickupAdapter extends BaseQuickAdapter<ItemMenu<PickupInfo.ListOrderBean>,BaseViewHolder> {
    public PickupAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<PickupInfo.ListOrderBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<PickupInfo.ListOrderBean> item) {
        helper.setText(R.id.tv_express_name,item.getData().getFreshCabinet().getCabinetName());
        helper.setText(R.id.tv_express_address,item.getData().getFreshCabinet().getAddress());

        helper.addOnClickListener(R.id.iv_share);
        helper.addOnClickListener(R.id.iv_open_the_cabinet);

    }
}
