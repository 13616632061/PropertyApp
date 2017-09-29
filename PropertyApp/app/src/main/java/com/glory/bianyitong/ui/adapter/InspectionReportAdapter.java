package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.InspectionReportInfo;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by lucy on 2017/9/28.
 */
public class InspectionReportAdapter extends BaseQuickAdapter<ItemMenu<InspectionReportInfo.ListQualityPicBean>,BaseViewHolder> {
    private Context context;
    public InspectionReportAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<InspectionReportInfo.ListQualityPicBean>> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<InspectionReportInfo.ListQualityPicBean> item) {
        ImageView imageView=helper.getView(R.id.image);
//        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        Glide.with(context).load(item.getData().getImgUrl()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
    }
}
