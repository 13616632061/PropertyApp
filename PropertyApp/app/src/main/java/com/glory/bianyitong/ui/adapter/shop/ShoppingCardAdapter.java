package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.ShopcartInfo;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.ui.activity.shop.ShoppingCartActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.ScreenUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.shop.AmountView;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ShoppingCardAdapter extends BaseSectionQuickAdapter<ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>,BaseViewHolder> {
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
    private Map<Integer, ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> commitData;
    private AmountView.OnAmountChangeListener amountChangeListener;
    public ShoppingCardAdapter(int layoutResId, int sectionHeadResId, List<ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> data, Context context, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, Map<Integer, ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> commitData, AmountView.OnAmountChangeListener amountChangeListener) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
        this.onCheckedChangeListener=onCheckedChangeListener;
        this.commitData=commitData;
        this.amountChangeListener=amountChangeListener;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> item) {
        helper.setText(R.id.item_title_shoppingcart_name,item.header);
        helper.setVisible(R.id.tv_coupon,item.isMore());

        helper.addOnClickListener(R.id.tv_coupon);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> item) {
        int position=helper.getAdapterPosition();
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
//        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        Glide.with(context).load(item.getData().getFresh().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFresh().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFresh().getFreshTypeName());
        helper.setText(R.id.tv_list_item_goods_price,"￥"+item.getData().getPrice());
        AmountView amountView=helper.getView(R.id.amount_view);
        amountView.setGoods_storage(item.getData().getFresh().getGodownNumber());
        amountView.setAmount(item.getData().getQuantity());
        amountView.setPosition(position);
        amountView.setOnAmountChangeListener(amountChangeListener);


        LinearLayout linearLayout=helper.getView(R.id.lay_list_item_goods);
        int width=ScreenUtil.getInstance(context).getScreenWidth();
        ViewGroup.LayoutParams layoutParams=linearLayout.getLayoutParams();
        layoutParams.width=width;
        linearLayout.setLayoutParams(layoutParams);


        final CheckBox checkBox=helper.getView(R.id.iv_button);

        checkBox.setTag(R.id.shopCard_check,position);

        helper.addOnClickListener(R.id.tv_shop_delete);
        helper.addOnClickListener(R.id.iv_list_item_goods_pic);
        helper.addOnClickListener(R.id.all_money_and_other);

        if (!item.getData().getFresh().isEnable()){//是否上架
            item.getData().setOK(false);
            helper.setVisible(R.id.shixiao,true);
            helper.setVisible(R.id.iv_button,false);
            amountView.setVisibility(View.GONE);
            helper.setText(R.id.tv_list_item_goods_price,"商品未上架");
            helper.setTextColor(R.id.tv_list_item_goods_price,context.getResources().getColor(R.color.text_color));
        }else if(item.getData().getFresh().isIsDelete()){//是否删除
            item.getData().setOK(false);
            helper.setVisible(R.id.shixiao,true);
            amountView.setVisibility(View.GONE);
            helper.setVisible(R.id.iv_button,false);
            helper.setText(R.id.tv_list_item_goods_price,"商品已删除");
            helper.setTextColor(R.id.tv_list_item_goods_price,context.getResources().getColor(R.color.text_color));
        }else if (item.getData().getFresh().getGodownNumber()<=0){//是否有库存
            item.getData().setOK(false);
            helper.setVisible(R.id.shixiao,true);
            helper.setVisible(R.id.iv_button,false);
            amountView.setVisibility(View.GONE);
            helper.setText(R.id.tv_list_item_goods_price,"商品没有库存");
            helper.setTextColor(R.id.tv_list_item_goods_price,context.getResources().getColor(R.color.text_color));
        }
        if (!item.getData().isIsvalid()){
            item.getData().setOK(false);
            helper.setVisible(R.id.shixiao,true);
            helper.setVisible(R.id.iv_button,false);
            amountView.setVisibility(View.GONE);
            helper.setText(R.id.tv_list_item_goods_price,"当前生鲜柜无法配送");
            helper.setTextColor(R.id.tv_list_item_goods_price,context.getResources().getColor(R.color.text_color));
        }else {
            item.getData().setOK(true);
            helper.setVisible(R.id.shixiao,false);
            helper.setVisible(R.id.iv_button,true);
            amountView.setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.tv_list_item_goods_price,context.getResources().getColor(R.color.color_red_del));
        }
        if(commitData.containsKey(position)){
            if (item.getData().isIsvalid()){
                if(commitData.get(position).getData().getCartID()==item.getData().getCartID()){
                    checkBox.setChecked(true);
                }else {
                    checkBox.setChecked(false);
                }
            }else {
                checkBox.setChecked(false);
                ToastUtils.showShort(context, "无法配送到当前生鲜柜");
            }

        }else {
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.getData().isIsvalid()){
                    checkBox.setChecked(false);
                    ToastUtils.showShort(context, "无法配送到当前生鲜柜");
                }
            }
        });

        helper.setOnCheckedChangeListener(R.id.iv_button,onCheckedChangeListener);


    }

    public void setAllSelected(boolean allSelected) {
        isAllSelected = allSelected;
    }

    public boolean isAllSelected() {
        return isAllSelected;
    }
}
