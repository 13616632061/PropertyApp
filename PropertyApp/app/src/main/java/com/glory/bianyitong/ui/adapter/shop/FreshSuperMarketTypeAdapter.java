package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryShopInfo;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 * 生鲜首页类型列表
 */

public class FreshSuperMarketTypeAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean>,BaseViewHolder> {
    public FreshSuperMarketTypeAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean> item) {
        helper.setText(R.id.item_fresh_type_content,item.getData().getFreshTypeName());
        TextView textView=helper.getView(R.id.item_fresh_type_content);
        textView.setBackgroundResource(R.drawable.selector_fresh_type);
    }
}
