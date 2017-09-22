package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.util.DateUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 * 购物车领取优惠券列表
 */

public class ShopCouponListAdapter extends BaseQuickAdapter<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListCouponBean>,BaseViewHolder> {
    public ShopCouponListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListCouponBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListCouponBean> item) {

        helper.setText(R.id.item_fr_couponlist_img,"￥ "+item.getData().getFreeMoney());

        String tip=item.getData().getStartFee()>0?"满"+item.getData().getStartFee()+"元可以使用":"不限额";
        helper.setText(R.id.item_fr_couponlist_tip,tip);
        helper.setText(R.id.item_fr_couponlist_name,item.getData().getCouponName());

        String startDate= DateUtil.format(DateUtil.parse(item.getData().getBeginDate()),DateUtil.DEFAULT_PATTERN_POINT);
        String endDate= DateUtil.format(DateUtil.parse(item.getData().getEndDate()),DateUtil.DEFAULT_PATTERN_POINT);
        helper.setText(R.id.item_fr_couponlist_date,startDate+"～"+endDate);


        Button btn=helper.getView(R.id.item_fr_couponlist_btn);
        btn.setText("立即领取");
        btn.setEnabled(true);
        btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo);


        helper.addOnClickListener(R.id.item_fr_couponlist_btn);

    }
}
