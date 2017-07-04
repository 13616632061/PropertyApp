package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/14.
 * 生鲜标签
 */
public class GridSearchTagAdapter extends BaseAdapter {
    private Context context;
    private Handler handler;
    private List<String> list;
    private LayoutInflater mInflater = null;
    private int type;

    public GridSearchTagAdapter(Context context, List<String> list, Handler handler, int type) {
        this.context = context;
        this.list = list;
        this.handler = handler;
        this.type = type;
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
            convertView = mInflater.inflate(R.layout.view_item_grid_search_tag, null);
            holder.item_lay_search_tag = (LinearLayout) convertView.findViewById(R.id.item_lay_search_tag);
            holder.tv_search_tag_name = (TextView) convertView.findViewById(R.id.tv_search_tag_name);
            holder.iv_more_search_tag = (ImageView) convertView.findViewById(R.id.iv_more_search_tag);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list != null && list.get(position) != null ) {
            String name = list.get(position);
            holder.tv_search_tag_name.setText(name);//  标签名称
        }else {
            holder.tv_search_tag_name.setText("");
        }

        final LinearLayout layout = holder.item_lay_search_tag;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.obj = list.get(position);
                msg.what = type;
                msg.arg1 = position;
                handler.sendMessage(msg);
            }
        });

//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {
        public TextView tv_search_tag_name; //标签名
        public ImageView iv_more_search_tag;
        private LinearLayout item_lay_search_tag;
    }
}
