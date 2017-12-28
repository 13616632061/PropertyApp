package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    Context context;
    private List<AllEvaluationInfo.ListFreshEvaluationBean.ListEvaluationPicBean> listEvaluationPic;

    public AllEvaluationAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean>> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean> item) {
        helper.setText(R.id.tv_plnr,item.getData().getEvaluationContext());
        helper.setText(R.id.iv_name,item.getData().getUser().getLoginName());
        CircleImageView imageView=helper.getView(R.id.iv_head_pic);
        ServiceDialog.setPicture(item.getData().getUser().getCustomerPhoto(), imageView, null);
        RatingBar ratingBar=helper.getView(R.id.ratingba);
        ratingBar.setRating(item.getData().getEvaluationLevel());

        RecyclerView rec_pic = helper.getView(R.id.rec_pic);
        PicAdapter adapter = new PicAdapter(R.layout.item_pic,item.getData().getListEvaluationPic(),context);
        GridLayoutManager linearLayout = new GridLayoutManager(context,4);
        rec_pic.setAdapter(adapter);
        rec_pic.setLayoutManager(linearLayout);
        adapter.bindToRecyclerView(rec_pic);
    }
}
