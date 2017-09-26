package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.view.ContainsEmojiEditText;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.litao.android.lib.entity.PhotoEntry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billlamian on 2017/8/17.
 */

public class CommentAddAdapter extends BaseQuickAdapter<ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>,BaseViewHolder> {
    private Context context;
    private List<EventEntry> imageList;
    private int tag=-1;

    public void setImageList(List<EventEntry> imageList,int tag) {
        this.tag=tag;
        this.imageList = imageList;
    }

    public CommentAddAdapter(@LayoutRes int layoutResId, Context context, List<EventEntry> imageList) {
        super(layoutResId);
        this.context=context;
        this.imageList=imageList;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> item) {
        ImageView imageView=helper.getView(R.id.item_comment_add_img);
        ServiceDialog.setPicture(item.getData().getFresh().getFreshPicture(),imageView,null);
        LinearLayout layout= helper.getView(R.id.item_comment_add_photo);
        ContainsEmojiEditText editText=helper.getView(R.id.item_comment_add_content);
        RatingBar ratingBar=helper.getView(R.id.ratingba);
        float rating = ratingBar.getRating();
        layout.setId(helper.getLayoutPosition());
        ImageView insertImage= new ImageView(context);
        insertImage.setId(R.id.comment_photo_select);
        insertImage.setImageResource(R.drawable.icon_photo_add);
        insertImage.setScaleType(ImageView.ScaleType.FIT_XY);
        insertImage.setLayoutParams((new ViewGroup.LayoutParams(130,130)));
        if (layout.findViewById(R.id.comment_photo_select)==null){
            layout.addView(insertImage);
        }
        CheckBox checkBox= helper.getView(R.id.item_comment_add_check);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageList.get(helper.getLayoutPosition()).anonymous="1";
                }else{
                    imageList.get(helper.getLayoutPosition()).anonymous="0";
                }
            }
        });

        imageList.get(helper.getLayoutPosition()).comment=editText.getText().toString().trim();
        imageList.get(helper.getLayoutPosition()).ratingBar=(int)rating;
        helper.addOnClickListener(R.id.comment_photo_select);

        if (tag!=-1&&tag==helper.getLayoutPosition()){
            Log.v("layout.getChildCount()",layout.getChildCount()+"");
            for (int i = 0; i < layout.getChildCount(); i++){
                if (layout.getChildCount()!=1){
                    layout.removeViewAt(0);
                }
            }
            if(imageList.size()>0 &&imageList.size()> helper.getLayoutPosition()&&imageList.get(helper.getLayoutPosition()).photos.size()>0){

                insertPhoto(layout,imageList.get(helper.getLayoutPosition()).photos);
            }
        }
    }

    private void insertPhoto(LinearLayout layout,List<PhotoEntry> photos){
        if(photos!=null && photos.size()>0){
            for (int i=0;i<photos.size();i++){
                ImageView image=new ImageView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(130, 130);
                layoutParams.setMargins(10,0,10,0);
                image.setLayoutParams(layoutParams);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
//                ServiceDialog.setPicture(photos.get(i).getPath(),image,ImageView.ScaleType.CENTER_INSIDE);
                Bitmap bitmap = getLoacalBitmap(photos.get(i).getPath()); //从本地取图片(在cdcard中获取)  //
                image .setImageBitmap(bitmap); //设置Bitmap
//                imageList.get(helper.getLayoutPosition()).position==helper.getLayoutPosition()
                layout.addView(image,0);

            }

        }

    }
    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory
                    .decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
