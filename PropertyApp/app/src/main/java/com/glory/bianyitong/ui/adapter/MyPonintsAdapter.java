package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.MyPointsListInfo;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by lucy on 2017/6/28.
 */
public class MyPonintsAdapter extends BaseQuickAdapter<ItemMenu<MyPointsListInfo.ListUserPointDetailBean>,BaseViewHolder> {

    private Context context;
    public MyPonintsAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<ItemMenu<MyPointsListInfo.ListUserPointDetailBean>> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<MyPointsListInfo.ListUserPointDetailBean> item) {
        helper.setText(R.id.item_ca_msg_tv_title,item.getData().getChangeReason());
        helper.setText(R.id.item_ca_msg_tv_date,item.getData().getCreateDate().replace("T"," "));
        if (item.getData().getChangeType()==10){
            helper.setTextColor(R.id.ponints_num,context.getResources().getColor(R.color.points_yellow_color));
        }else if (item.getData().getChangeType()==20){
            helper.setTextColor(R.id.ponints_num,context.getResources().getColor(R.color.color_gray_time));
        }
        helper.setText(R.id.ponints_num,item.getData().getChangePointsString());
    }
}
