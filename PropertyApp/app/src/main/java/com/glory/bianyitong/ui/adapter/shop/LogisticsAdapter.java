package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.LogisticsInfo;

import java.util.List;

/**
 * Created by lucy on 2017/9/22.
 */
public class LogisticsAdapter extends BaseQuickAdapter<ItemMenu<LogisticsInfo.OrderBean.ListStatusRecordBean>,BaseViewHolder>{


    public LogisticsAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<LogisticsInfo.OrderBean.ListStatusRecordBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<LogisticsInfo.OrderBean.ListStatusRecordBean> item) {
        //订单状态  0 已提交订单1 备货中 2 配送中3 已配送 9 分拣中
        switch (item.getData().getOrderStatus()){
            case 0:
                helper.setText(R.id.tv_list_item_goods_name,"等待商家确认");
                break;
            case 1:
                helper.setText(R.id.tv_list_item_goods_name,"备货中");
                break;
            case 2:
                helper.setText(R.id.tv_list_item_goods_name,"正在派送");
                break;
            case 3:
                helper.setText(R.id.tv_list_item_goods_name,"已送达");
                break;
            case 9:
                helper.setText(R.id.tv_list_item_goods_name,"分拣中");
                break;
        }

        helper.setText(R.id.tv_time,item.getData().getRecordDate().replace("T"," "));
        if (item.getData().getExplain().trim().equals(item.getData().getRemark().trim())){
            helper.setText(R.id.tv_list_item_goods_price,item.getData().getExplain());
        }else {
            helper.setText(R.id.tv_list_item_goods_price,item.getData().getExplain()+item.getData().getRemark().trim());
        }
    }
}
