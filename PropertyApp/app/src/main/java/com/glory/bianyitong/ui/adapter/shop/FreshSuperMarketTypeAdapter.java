package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.FreshTypeInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryShopInfo;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 * 生鲜首页类型列表
 */

public class FreshSuperMarketTypeAdapter extends BaseQuickAdapter<ItemMenu<FreshTypeInfo.ListFreshTypeBean>,BaseViewHolder> {
    private Context context;
    public FreshSuperMarketTypeAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<FreshTypeInfo.ListFreshTypeBean>> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }
    private int position=0;

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<FreshTypeInfo.ListFreshTypeBean> item) {
        helper.setText(R.id.item_fresh_type_content,item.getData().getFreshTypeName());
        TextView textView=helper.getView(R.id.item_fresh_type_content);
        if(helper.getAdapterPosition()==position)
        {
            textView.setTextColor(ContextCompat.getColor(context,R.color.green1));
            textView.setBackgroundResource(R.drawable.shape_fresh_type_bg_press);
        }else {
            textView.setTextColor(ContextCompat.getColor(context,R.color.gray1));
            textView.setBackgroundResource(R.drawable.shape_fresh_type_bg_normal);
        }

    }

    public void setPosition(int position) {
        this.position = position;
    }
}
