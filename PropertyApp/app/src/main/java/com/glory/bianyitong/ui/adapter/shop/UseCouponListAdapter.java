package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

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

public class UseCouponListAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>,BaseViewHolder> {
    private Context context;
    List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data;
    public UseCouponListAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data, Context context) {
        super(layoutResId, data);
        this.context=context;
        this.data=data;
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

        CheckBox checkBox=helper.getView(R.id.item_fr_couponlist_btn);

        if (item.getData().getClikcType()==1){
            checkBox.setChecked(false);
            helper.setBackgroundColor(R.id.lay_list_item_goods,context.getResources().getColor(R.color.gray));
        }else if (item.getData().getClikcType()==2){
            checkBox.setChecked(true);
            helper.setBackgroundColor(R.id.lay_list_item_goods,context.getResources().getColor(R.color.white));
        }

    }
}
