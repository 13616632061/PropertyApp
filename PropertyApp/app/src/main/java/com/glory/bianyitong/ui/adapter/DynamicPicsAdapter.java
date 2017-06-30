package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/14.
 * 动态图片
 */
public class DynamicPicsAdapter extends BaseAdapter {
    private Context context;
    private List<ResponseFriendDetail.ListNeighborhoodBean.ListNeighborhoodPicBean>  list;

    private LayoutInflater mInflater = null;

    public DynamicPicsAdapter(Context context, List<ResponseFriendDetail.ListNeighborhoodBean.ListNeighborhoodPicBean>  list) {
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
            convertView = mInflater.inflate(R.layout.view_item_pic, null);

            holder.iv_mynews_pic = (ImageView) convertView.findViewById(R.id.iv_mynews_pic);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
            if (list.get(position).getPicturePath()!= null && !list.get(position).getPicturePath().equals("")) {
                ServiceDialog.setPicture(list.get(position).getPicturePath(), holder.iv_mynews_pic, null);
            }else {
                holder.iv_mynews_pic.setImageResource(R.drawable.wait);
            }
        }else {
            holder.iv_mynews_pic.setImageResource(R.drawable.wait);
        }

//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {

        public ImageView iv_mynews_pic;  //图片
    }

}
