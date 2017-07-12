package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 * 地址列表adapter
 */

public class AddressListAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>,BaseViewHolder> {
    private int position;
    public AddressListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryAddress.ListShippingAddressBean> item) {
        helper.setText(R.id.address_list_name,item.getData().getCabinetName());
        helper.setText(R.id.address_list_address,item.getData().getFreshCabinet().getProvinceName()+" "+item.getData().getFreshCabinet().getCityName()+" "+item.getData().getFreshCabinet().getStreetAddress());


        if(item.getData().isDefaults())
            position=helper.getAdapterPosition();

        if(position==helper.getAdapterPosition()){
            helper.setChecked(R.id.iv_button,true);
        }else {
            helper.setChecked(R.id.iv_button,false);
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
