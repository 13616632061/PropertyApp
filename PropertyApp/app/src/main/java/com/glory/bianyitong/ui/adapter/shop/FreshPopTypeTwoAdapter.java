package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryTwoType;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 * 二级分类
 */

public class FreshPopTypeTwoAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryTwoType.ListFreshTypeBean>,BaseViewHolder> {
    private Context context;
    public FreshPopTypeTwoAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryTwoType.ListFreshTypeBean>> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryTwoType.ListFreshTypeBean> item) {
//        if (freshLeftInfo.isFlag()){
//            helper.setTextColor(R.id.tv_fenlei,context.getResources().getColor( R.color.module_green_color));
//        }else {
//            helper.setTextColor(R.id.tv_fenlei,context.getResources().getColor( R.color.small_text_color_gray));
//        }

        helper.setText(R.id.tv_fenlei,item.getData().getFreshTypeName());
        helper.setText(R.id.tv_number,item.getData().getInverntoryQuantity()+"");
    }
}
