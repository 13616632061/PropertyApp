package com.glory.bianyitong.ui.adapter.shop;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.AllEvaluationInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.CircleImageView;

import java.util.List;

/**
 * Created by lucy on 2017/9/11.
 */
public class AllEvaluationAdapter extends BaseQuickAdapter<ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean>,BaseViewHolder> {
    public AllEvaluationAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean> item) {
        helper.setText(R.id.tv_plnr,item.getData().getEvaluationContext());
        helper.setText(R.id.iv_name,item.getData().getUser().getLoginName());
        CircleImageView imageView=helper.getView(R.id.iv_head_pic);
        ServiceDialog.setPicture(item.getData().getUser().getCustomerPhoto(), imageView, null);
        RatingBar ratingBar=helper.getView(R.id.ratingba);
        ratingBar.setRating(item.getData().getEvaluationLevel());

    }
}
