package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.util.ScreenUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 * 地址列表adapter
 */

public class AddressListAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>,BaseViewHolder> {
    private int position=-1;
    private Context context;
    public AddressListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryAddress.ListShippingAddressBean> item) {
        helper.setText(R.id.address_list_name,item.getData().getCabinetName());
        helper.setText(R.id.address_list_address,item.getData().getFreshCabinet().getProvinceName()+" "+item.getData().getFreshCabinet().getCityName()+" "+item.getData().getFreshCabinet().getDistrictName()+" "+item.getData().getFreshCabinet().getStreetAddress());

        LinearLayout linearLayout=helper.getView(R.id.lay_list_item_goods);
        int width= ScreenUtil.getInstance(context).getScreenWidth();
        ViewGroup.LayoutParams layoutParams=linearLayout.getLayoutParams();
        layoutParams.width=width;
        linearLayout.setLayoutParams(layoutParams);


        helper.addOnClickListener(R.id.tv_shop_edit);
        helper.addOnClickListener(R.id.address_list_delete);
        CheckBox checkBox=helper.getView(R.id.iv_button);
        helper.addOnClickListener(R.id.iv_button);

        if(item.getData().isDefaults())
            position=helper.getAdapterPosition();

        if(position==helper.getAdapterPosition()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
