package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.CollectionNiInfo;
import com.glory.bianyitong.ui.activity.CollectionNiActivity;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;

import java.util.List;

/**
 * Created by lucy on 2017/10/30.
 */
public class CollctionNiAdapter extends BaseQuickAdapter<ItemMenu<CollectionNiInfo.ListNeighborhoodCollectBean>,BaseViewHolder> {
    Context context;
    private HorizontalScrollView hor_scrollview;

    public CollctionNiAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<ItemMenu<CollectionNiInfo.ListNeighborhoodCollectBean>> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ItemMenu<CollectionNiInfo.ListNeighborhoodCollectBean> item) {
        TextView tv_shop_delete = helper.getView(R.id.tv_shop_delete);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout llear=helper.getView(R.id.llear);
        ViewGroup.LayoutParams layoutParams = llear.getLayoutParams();
        layoutParams.width=width;
        llear.setLayoutParams(layoutParams);
//        helper.addOnClickListener(R.id.tv_shop_delete);
        helper.addOnClickListener(R.id.llear);
        ImageView imageView=helper.getView(R.id.dynamic_user_head);
        Glide.with(context).load(item.getData().getCollectUserPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
        helper.setText(R.id.dynamic_user_name,item.getData().getCollectUserName());
        helper.setText(R.id.dynamic_right_data,item.getData().getCreateDate().replace("T"," "));
        if (item.getData().getCollectContent()!=null){
            if (item.getData().getCollectType()==1){//1文字，2图片，3视频
                helper.setText(R.id.dynamic_tv_content,item.getData().getCollectContent());
                helper.setVisible(R.id.collection_pic,false);
                helper.setVisible(R.id.dynamic_tv_content,true);
            }else if (item.getData().getCollectType()==2){
                helper.setVisible(R.id.dynamic_tv_content,false);
                helper.setVisible(R.id.collection_pic,true);
                ImageView imageView2=helper.getView(R.id.collection_pic);
                Glide.with(context).load(item.getData().getCollectContent()).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView2);
            }
        }
        tv_shop_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hor_scrollview = helper.getView(R.id.hor_scrollview);

                hor_scrollview.scrollTo(0,0);

                CollectionNiActivity.callBack.delCollection(helper.getPosition());
            }
        });
        helper.addOnClickListener(R.id.collection_pic);
    }


}
