package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.util.DateUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 * 我的优惠券列表
 */

public class CouponListAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>,BaseViewHolder> {
    public CouponListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean> item) {

        helper.setText(R.id.item_fr_couponlist_img,"￥ "+item.getData().getCoupon().getFreeMoney());

        String tip=item.getData().getCoupon().getMinimumAmount()>0?"满"+item.getData().getCoupon().getMinimumAmount()+"元可以使用":"不限额";
        helper.setText(R.id.item_fr_couponlist_tip,tip);
        helper.setText(R.id.item_fr_couponlist_name,item.getData().getCoupon().getCouponName());

        String startDate= DateUtil.format(DateUtil.parse(item.getData().getCoupon().getBeginDate()),DateUtil.DEFAULT_PATTERN_POINT);
        String endDate= DateUtil.format(DateUtil.parse(item.getData().getCoupon().getEndDate()),DateUtil.DEFAULT_PATTERN_POINT);
        helper.setText(R.id.item_fr_couponlist_date,startDate+"～"+endDate);


        int conponType=item.getData().getCouponStatus();
        if(conponType==9){//可使用优惠券

        }else {//一般优惠券

        }
    }
}
