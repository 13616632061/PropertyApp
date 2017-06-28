package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.AddAwardActivity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 * Created by lucy on 2017/5/3.
 * 取件
 */
public class PickupAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LinkedTreeMap<String, Object>> list;

    private LayoutInflater mInflater = null;

    public PickupAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> list) {
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
            convertView = mInflater.inflate(R.layout.view_item_pickup, null);

            holder.rl_express = (RelativeLayout) convertView.findViewById(R.id.rl_express);
            holder.tv_express_name = (TextView) convertView.findViewById(R.id.tv_express_name);
            holder.tv_express_address = (TextView) convertView.findViewById(R.id.tv_express_address);
            holder.item_express_line = (TextView) convertView.findViewById(R.id.item_express_line);
//            holder.view_award_line = (TextView) convertView.findViewById(R.id.view_award_line);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
//            if (position == list.size() - 1) {
//                holder.view_award_line.setVisibility(View.GONE);
//            }
//            if (list.get(position).get("authorizationUserName") != null && !list.get(position).get("authorizationUserName").equals("")) {
//                holder.item_award_people_name.setText(list.get(position).get("authorizationUserName").toString()); //授权人姓名
//            }else {
//                holder.item_award_people_name.setText("");
//            }

        }

        final RelativeLayout layout = holder.rl_express;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Database.awardpeople = list.get(position);
                Intent intent = new Intent(context, AddAwardActivity.class);
                intent.putExtra("from","edit");
                context.startActivity(intent);
            }
        });


//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {

        public RelativeLayout rl_express;
        public TextView tv_express_name; //快件名称
        public TextView tv_express_address; //快件地址
        public TextView item_express_line;
//        public TextView view_award_line;
    }

}
