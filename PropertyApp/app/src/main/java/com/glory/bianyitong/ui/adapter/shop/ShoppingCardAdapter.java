package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.ScreenUtil;
import com.glory.bianyitong.widght.shop.AmountView;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ShoppingCardAdapter extends BaseSectionQuickAdapter<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    private  Context context;
    private boolean isAllSelected=false;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private Map<Integer, ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> commitData;
    private AmountView.OnAmountChangeListener amountChangeListener;
    public ShoppingCardAdapter(int layoutResId, int sectionHeadResId, List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> data, Context context, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, Map<Integer, ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> commitData, AmountView.OnAmountChangeListener amountChangeListener) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
        this.onCheckedChangeListener=onCheckedChangeListener;
        this.commitData=commitData;
        this.amountChangeListener=amountChangeListener;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean> item) {
        helper.setText(R.id.item_title_shoppingcart_name,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean> item) {
        int position=helper.getAdapterPosition();
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFreshTypeName());
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getPrice());
        AmountView amountView=helper.getView(R.id.amount_view);
        amountView.setGoods_storage(1000);
        amountView.setAmount(item.getData().getQuantity());
        amountView.setPosition(position);
        amountView.setOnAmountChangeListener(amountChangeListener);



        LinearLayout linearLayout=helper.getView(R.id.lay_list_item_goods);
        int width=ScreenUtil.getInstance(context).getScreenWidth();
        ViewGroup.LayoutParams layoutParams=linearLayout.getLayoutParams();
        layoutParams.width=width;
        linearLayout.setLayoutParams(layoutParams);


        CheckBox checkBox=helper.getView(R.id.iv_button);

        checkBox.setTag(R.id.shopCard_check,position);
        helper.addOnClickListener(R.id.tv_shop_delete);

        if(commitData.containsKey(position)){
            if(commitData.get(position).getData().getCartID()==item.getData().getCartID()){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
        }else {
            checkBox.setChecked(false);
        }
        helper.setOnCheckedChangeListener(R.id.iv_button,onCheckedChangeListener);


    }

    public void setAllSelected(boolean allSelected) {
        isAllSelected = allSelected;
    }

    public boolean isAllSelected() {
        return isAllSelected;
    }
}
