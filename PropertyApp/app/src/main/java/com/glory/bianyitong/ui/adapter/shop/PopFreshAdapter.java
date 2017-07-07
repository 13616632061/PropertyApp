
package com.glory.bianyitong.ui.adapter.shop;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.entity.FreshLeftInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucy on 2017/7/3.
 */
public class PopFreshAdapter extends BaseQuickAdapter<FreshLeftInfo,BaseViewHolder> {

    List<FreshLeftInfo> list=new ArrayList<>();
    Context context;
    public static PopFreshCallBack popFreshCallBack;

    public PopFreshAdapter(Context context, int layoutResId, List<FreshLeftInfo> data) {
        super(layoutResId, data);
        list=data;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final FreshLeftInfo freshLeftInfo) {
        if (freshLeftInfo.isFlag()){
            baseViewHolder.setTextColor(R.id.tv_fenlei,context.getResources().getColor( R.color.module_green_color));
        }else {
            baseViewHolder.setTextColor(R.id.tv_fenlei,context.getResources().getColor( R.color.small_text_color_gray));
        }
        //左侧点击
        popFreshCallBack = new PopFreshCallBack() {
            @Override
            public void popTextColorCall(int possition) {
                for (int i=0;i<list.size();i++){
                    list.get(i).setFlag(false);
                }
                list.get(possition).setFlag(true);
                notifyDataSetChanged();
            }
        };
    }



    public interface PopFreshCallBack {
        /**
         *点击时切换背景
         */
        void popTextColorCall(int possition);
    }

}
