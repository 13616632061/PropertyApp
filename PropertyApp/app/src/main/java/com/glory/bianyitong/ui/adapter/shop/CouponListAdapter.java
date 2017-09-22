package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Button;

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
    int type;
    public CouponListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data,int type) {
        super(layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean> item) {

        helper.setText(R.id.item_fr_couponlist_img,"￥ "+item.getData().getCoupon().getFreeMoney());

        String tip=item.getData().getCoupon().getStartFee()>0?"满"+item.getData().getCoupon().getStartFee()+"元可以使用":"不限额";
        helper.setText(R.id.item_fr_couponlist_tip,tip);
        helper.setText(R.id.item_fr_couponlist_name,item.getData().getCoupon().getCouponName());

        String startDate= DateUtil.format(DateUtil.parse(item.getData().getBeginDate()),DateUtil.DEFAULT_PATTERN_POINT);
        String endDate= DateUtil.format(DateUtil.parse(item.getData().getEndDate()),DateUtil.DEFAULT_PATTERN_POINT);
        helper.setText(R.id.item_fr_couponlist_date,startDate+"～"+endDate);


        int conponType=item.getData().getCouponStatus();
        Button btn=helper.getView(R.id.item_fr_couponlist_btn);


        switch (conponType){
            case 0://我的优惠券列表未使用
                if(type==-1){
                    btn.setText("已过期");
                    btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo_normal);
                }
                else{
                    btn.setText("未使用");
                    btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo);
                }

                btn.setEnabled(false);

                break;
            case 1://我的优惠券列表已使用
                btn.setText("已使用");
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo_normal);
                break;
            case -1://我的优惠券列表已过期
                btn.setText("已过期");
                btn.setEnabled(false);
                btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo_normal);
                break;
            case 9://可用优惠券列表可用
                btn.setText("立即使用");
                btn.setEnabled(true);
                btn.setBackgroundResource(R.drawable.shape_item_couponlist_logo);
                break;
        }
        helper.addOnClickListener(R.id.item_fr_couponlist_btn);

    }
}
