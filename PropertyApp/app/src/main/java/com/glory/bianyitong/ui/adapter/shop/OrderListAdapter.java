package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.adapter.MultiItemView;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.List;

/**
 * Created by billlamian on 17-8-10.
 */

public class OrderListAdapter extends BaseMultiItemQuickAdapter<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>,BaseViewHolder>{
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderListAdapter(List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data,Context context) {
        super(data);
        addItemType(MultiItemView.TITLE, R.layout.item_order_list_title);
        addItemType(MultiItemView.BODY, R.layout.item_order_list_body);
        addItemType(MultiItemView.FOOTER, R.layout.item_order_list_footer);
        addItemType(MultiItemView.OPERATION, R.layout.item_order_list_operation);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> item) {
        switch (helper.getItemViewType()){
            case MultiItemView.TITLE://标题view
                helper.setText(R.id.order_list_item_title_title,item.getMsg1());
                helper.setText(R.id.order_list_item_title_status,item.getMsg2());
                break;
            case MultiItemView.BODY://内容view
                ImageView imageView=helper.getView(R.id.order_list_item_body_img);
//                ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
                Glide.with(context).load(item.getData().getFresh().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
                helper.setText(R.id.order_list_item_body_name,item.getData().getFresh().getFreshName());
                helper.setText(R.id.order_list_item_body_type,item.getData().getFresh().getFreshTypeLeaf());
                helper.setText(R.id.order_list_item_body_price,"¥ "+item.getData().getFresh().getFreshPrice());
                helper.setText(R.id.order_list_item_body_number,"x "+item.getData().getFreshQuantity());
                helper.addOnClickListener(R.id.item_order);
                break;
            case MultiItemView.FOOTER://脚步view
                if (item.getFreight()==0){
                    helper.setText(R.id.tv_yunfei,"(不含运费)");
                }else {
                    helper.setText(R.id.tv_yunfei,"(含运费"+item.getFreight()+"元");
                }
                helper.setText(R.id.tv_in_total,"共"+item.getCartNum()+"件商品");
                helper.setText(R.id.tv_money,item.getOrderPaidPrice()+"");


                break;
            case MultiItemView.OPERATION://操作view
                helper.setText(R.id.order_list_item_opera_btn1,item.getMsg1());
                helper.setText(R.id.order_list_item_opera_btn2,item.getMsg2());
                if(TextUtils.isEmpty(item.getMsg1())){
                    helper.setVisible(R.id.order_list_item_opera_btn1,false);
                }else {
                    helper.setVisible(R.id.order_list_item_opera_btn1,true);
                }

                if(TextUtils.isEmpty(item.getMsg2())){
                    helper.setVisible(R.id.order_list_item_opera_btn2,false);

                }else {
                    helper.setVisible(R.id.order_list_item_opera_btn2,true);

                }

                helper.addOnClickListener(R.id.order_list_item_opera_btn1);
                helper.addOnClickListener(R.id.order_list_item_opera_btn2);
                break;
        }
    }
}
