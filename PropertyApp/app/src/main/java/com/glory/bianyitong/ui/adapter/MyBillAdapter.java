package com.glory.bianyitong.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.MyBillInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by lucy on 2017/10/13.
 */
public class MyBillAdapter extends BaseQuickAdapter<ItemMenu<MyBillInfo.ListUserBillBean>,BaseViewHolder> {

    public MyBillAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<MyBillInfo.ListUserBillBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<MyBillInfo.ListUserBillBean> item) {
        helper.setText(R.id.address_list_name,item.getData().getCommnuityName()+item.getData().getRoomName());
        helper.setText(R.id.address_list_address,item.getData().getDisplayName()+":"+item.getData().getAmount());
        helper.setText(R.id.my_bill_time,item.getData().getCreateDate().substring(0,10));
    }
}
