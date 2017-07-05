package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
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
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    public ShoppingCardAdapter(int layoutResId, int sectionHeadResId, List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> data, Context context, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
        this.onCheckedChangeListener=onCheckedChangeListener;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean> item) {
        helper.setText(R.id.item_title_shoppingcart_name,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean> item) {
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFreshTypeName());
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getPrice());
        AmountView amountView=helper.getView(R.id.amount_view);
        amountView.setAmount(item.getData().getQuantity());

        LinearLayout linearLayout=helper.getView(R.id.lay_list_item_goods);
        int width=ScreenUtil.getInstance(context).getScreenWidth();
        ViewGroup.LayoutParams layoutParams=linearLayout.getLayoutParams();
        layoutParams.width=width;
        linearLayout.setLayoutParams(layoutParams);

        CheckBox checkBox=helper.getView(R.id.iv_button);
        int position=helper.getAdapterPosition();
        checkBox.setTag(R.id.shopCard_check,position);
        helper.setOnCheckedChangeListener(R.id.iv_button,onCheckedChangeListener);
        helper.addOnClickListener(R.id.tv_shop_delete);

    }
}
