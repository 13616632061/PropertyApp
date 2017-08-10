package com.glory.bianyitong.ui.adapter.shop;

import android.widget.ImageView;

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
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderListAdapter(List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data) {
        super(data);
        addItemType(MultiItemView.TITLE, R.layout.item_order_list_title);
        addItemType(MultiItemView.BODY, R.layout.item_order_list_body);
        addItemType(MultiItemView.FOOTER, R.layout.item_order_list_footer);
        addItemType(MultiItemView.OPERATION, R.layout.item_order_list_operation);
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
                ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
                helper.setText(R.id.order_list_item_body_name,item.getData().getFresh().getFreshContent());
                helper.setText(R.id.order_list_item_body_type,item.getData().getFresh().getFreshTypeLeaf());
                helper.setText(R.id.order_list_item_body_price,"¥ "+item.getData().getFresh().getFreshPrice());
                helper.setText(R.id.order_list_item_body_number,"x "+item.getData().getFreshQuantity());
                break;
            case MultiItemView.FOOTER://脚步view
                break;
            case MultiItemView.OPERATION://操作view
                break;
        }
    }
}
