package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.view.ContainsEmojiEditText;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.litao.android.lib.entity.PhotoEntry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by billlamian on 2017/8/17.
 */

public class CommentPicAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context context;

    public CommentPicAdapter(@LayoutRes int layoutResId, Context context) {
        super(layoutResId);
        this.context=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView=helper.getView(R.id.iv_list_item_goods_pic);
        ServiceDialog.setPicture(item,imageView,null);
    }
}
