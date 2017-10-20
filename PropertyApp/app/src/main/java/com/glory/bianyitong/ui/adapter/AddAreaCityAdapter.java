package com.glory.bianyitong.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseListCommunity;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by lucy on 2017/10/20.
 */
public class AddAreaCityAdapter extends BaseQuickAdapter<ResponseListCommunity.ListCommunityBean,BaseViewHolder> {
    public AddAreaCityAdapter(@LayoutRes int layoutResId, @Nullable List<ResponseListCommunity.ListCommunityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResponseListCommunity.ListCommunityBean item) {

        helper.setText(R.id.auth_area_name,item.getCommunityName());
        helper.setText(R.id.tv_city_name,item.getStreet());
    }
}
