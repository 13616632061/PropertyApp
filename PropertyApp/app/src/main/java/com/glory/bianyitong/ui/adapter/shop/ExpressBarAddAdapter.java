package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryExpressBar;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ExpressBarAddAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean>,BaseViewHolder> {
    public ExpressBarAddAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean> item) {
        helper.setText(R.id.local_content_txt,item.getData().getCabinetName());
        helper.setText(R.id.address_content_txt,"收货地址:"+item.getData().getAddress());
        helper.setText(R.id.local_content_distance,"约 "+(int)item.getData().getDistance()+" 米");
    }
}
