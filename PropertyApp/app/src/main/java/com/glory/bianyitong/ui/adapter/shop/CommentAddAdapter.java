package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.litao.android.lib.entity.PhotoEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billlamian on 2017/8/17.
 */

public class CommentAddAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>,BaseViewHolder> {
    private Context context;
    private List<EventEntry> imageList;
    public CommentAddAdapter(@LayoutRes int layoutResId, Context context,List<EventEntry> imageList) {
        super(layoutResId);
        this.context=context;
        this.imageList=imageList;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> item) {
        ImageView imageView=helper.getView(R.id.item_comment_add_img);
        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        LinearLayout layout=helper.getView(R.id.item_comment_add_photo);
        ImageView insertImage=new ImageView(context);
        insertImage.setId(R.id.comment_photo_select);
        insertImage.setImageResource(R.drawable.icon_photo_add);
        insertImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        layout.addView(insertImage);
        helper.addOnClickListener(R.id.comment_photo_select);

        ;
        if(imageList.size()>0 && imageList.get(helper.getLayoutPosition()).photos.size()>0){
            insertPhoto(layout,imageList.get(helper.getLayoutPosition()).photos);
        }
    }

    private void insertPhoto(LinearLayout layout,List<PhotoEntry> photos){
        if(photos!=null && photos.size()>0){

            for (int i=0;i<photos.size();i++){
                ImageView image=new ImageView(context);
                ServiceDialog.setPicture(photos.get(i).getPath(),image,ImageView.ScaleType.CENTER_INSIDE);
                layout.addView(image,0);
            }

        }

    }
}
