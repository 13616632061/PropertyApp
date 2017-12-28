package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.AllEvaluationInfo;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.CircleImageView;

import java.util.List;

/**
 * Created by lucy on 2017/9/18.
 */
public class PicAdapter extends BaseQuickAdapter<AllEvaluationInfo.ListFreshEvaluationBean.ListEvaluationPicBean,BaseViewHolder> {
    private Context context;

    public PicAdapter(@LayoutRes int layoutResId, @Nullable List<AllEvaluationInfo.ListFreshEvaluationBean.ListEvaluationPicBean> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AllEvaluationInfo.ListFreshEvaluationBean.ListEvaluationPicBean item) {
        ImageView imageView=helper.getView(R.id.iv_picture);
//        ServiceDialog.setPicture(item.getPicturePath(), imageView, null);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(item.getPicturePath()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
    }
}
