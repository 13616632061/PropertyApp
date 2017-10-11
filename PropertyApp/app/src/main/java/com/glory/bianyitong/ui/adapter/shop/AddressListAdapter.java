package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.lazylibrary.util.ToastUtils;
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
    private boolean isFirmOrder=false;


    public AddressListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>> data, Context context, boolean isFirmOrder) {
        super(layoutResId, data);
        this.context=context;
        this.isFirmOrder=isFirmOrder;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryAddress.ListShippingAddressBean> item) {
        helper.setText(R.id.address_list_name,item.getData().getFreshCabinet().getCommunityName()+item.getData().getFreshCabinet().getCabinetName());
        helper.setText(R.id.address_list_address,item.getData().getFreshCabinet().getCommunity().getProvinceName()+" "+item.getData().getFreshCabinet().getCommunity().getCityName()+" "+item.getData().getFreshCabinet().getCommunity().getDistrictName()+" "+item.getData().getFreshCabinet().getCommunity().getStreet()+" "+item.getData().getFreshCabinet().getCommunity().getAddressNumber());
            helper.setText(R.id.address_been_used,item.getData().getFreshCabinet().getUsed()+"");
        helper.setText(R.id.address_total,"/"+item.getData().getFreshCabinet().getNum());
        helper.setText(R.id.name_and_phone,item.getData().getHarvesterName()+"  "+item.getData().getHarvestePhone());


        if(isFirmOrder){//来源:提交订单  不做任何监听

        }else {
            CheckBox checkBox=helper.getView(R.id.iv_button);
            LinearLayout linearLayout=helper.getView(R.id.lay_list_item_goods);
            int width= ScreenUtil.getInstance(context).getScreenWidth();
            ViewGroup.LayoutParams layoutParams=linearLayout.getLayoutParams();
            layoutParams.width=width;
            linearLayout.setLayoutParams(layoutParams);
            helper.addOnClickListener(R.id.tv_shop_edit);
            helper.addOnClickListener(R.id.address_list_delete);
            helper.addOnClickListener(R.id.lay_list_item_goods);
            helper.addOnClickListener(R.id.iv_button);
            if(item.getData().isDefaults())
                position=helper.getAdapterPosition();

            if(position==helper.getAdapterPosition()){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
        }


    }

    public void setPosition(int position) {
        this.position = position;
    }
}
