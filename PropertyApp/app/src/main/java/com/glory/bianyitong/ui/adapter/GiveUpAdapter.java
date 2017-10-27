package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.DynamicListInfo;
import com.glory.bianyitong.bean.GiveUpInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.widght.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2017/10/27.
 */
public class GiveUpAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GiveUpInfo.ListNeighborhoodLikeBean> list;

    private LayoutInflater mInflater = null;

    public GiveUpAdapter(Context context, ArrayList<GiveUpInfo.ListNeighborhoodLikeBean> list) {
        this.context = context;
        this.list = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_giveup, null);

            holder.comment_user_head = (CircleImageView) convertView.findViewById(R.id.comment_user_head);
            holder.comment_user_name = (TextView) convertView.findViewById(R.id.comment_user_name);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position).getCustomerPhoto() != null ) {
//                ServiceDialog.setPicture(list.get(position).get("userPhoto").toString(), holder.comment_user_head, null); //头像
            String pic = list.get(position).getCustomerPhoto().toString();
            Glide.with(context).load(pic).error(R.drawable.wait).placeholder(R.drawable.wait).into(holder.comment_user_head);
        } else {
            holder.comment_user_head.setImageResource(R.drawable.wait_round);
        }
        if (list.get(position).getLoginName() != null ) {
            holder.comment_user_name.setText(list.get(position).getLoginName());//  评论人名称
        } else {
            holder.comment_user_name.setText("");
        }

        return convertView;
    }


    public static class ViewHolder {
        CircleImageView comment_user_head; //评论人头像
        TextView comment_user_name; //名称
    }

}
