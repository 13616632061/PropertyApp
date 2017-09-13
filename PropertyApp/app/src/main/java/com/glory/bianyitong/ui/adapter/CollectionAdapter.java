package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ServiceDialog;


import java.util.List;

/**
 * Created by lucy on 2017/6/28.
 */
public class CollectionAdapter extends BaseQuickAdapter<ItemMenu<RefundInfo.ListFreshCollectionBean>,BaseViewHolder> {

    private Context context;
    //判断按钮显示隐藏
    private boolean EDIT=true;

    public boolean isEDIT() {
        return EDIT;
    }

    public void setEDIT(boolean EDIT) {
        this.EDIT = EDIT;
    }

    public CollectionAdapter(Context context, int layoutResId, List data) {
        super(layoutResId, data);
        this.context=context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final ItemMenu<RefundInfo.ListFreshCollectionBean> item) {
        if (item.getData().isFlag()){
            helper.setChecked(R.id.iv_button,true);
        }else {
            helper.setChecked(R.id.iv_button,false);
        }

        if (isEDIT()){
            helper.setVisible(R.id.iv_button,false);
        }else {
            helper.setVisible(R.id.iv_button,true);
        }
        CheckBox checkBox= helper.getView(R.id.iv_button);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    item.getData().setFlag(true);
                }else{
                    item.getData().setFlag(false);
                }
            }
        });



        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
//        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        Glide.with(context).load(item.getData().getFresh().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
        helper.setText(R.id.tv_list_item_goods_name,item.getData().getFresh().getFreshName());
        helper.setText(R.id.tv_list_item_goods_content,item.getData().getFresh().getFreshTypeLeaf());
        helper.setText(R.id.tv_money,"￥"+item.getData().getFresh().getFreshPrice());


        if (!item.getData().getFresh().isEnable()){//是否上架

            helper.setText(R.id.tv_money,"商品未上架");
            helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_color));
        }else if(item.getData().getFresh().getIsDelete()){//是否删除

            helper.setText(R.id.tv_money,"商品已删除");
            helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_color));
        }else

        helper.addOnClickListener(R.id.add_gouwuche);
    }


}
