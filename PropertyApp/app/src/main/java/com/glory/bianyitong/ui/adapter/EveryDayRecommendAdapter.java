package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.BulletinDetailsActivity;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.RelativeDateFormat;
import com.glory.bianyitong.util.SharedUtil;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.activity.EveryDayDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/10.
 */
public class EveryDayRecommendAdapter extends BaseAdapter {

    private Context context;

    private List<listCommunityBulletinInfo.ListCommunityBulletinBean> qiList;
    private List<String> communityRead;
    private LayoutInflater mInflater = null;

    public EveryDayRecommendAdapter(Context context, List<listCommunityBulletinInfo.ListCommunityBulletinBean> qiList) {
        this.context = context;
        this.qiList = qiList;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (qiList != null) {
            return qiList.size();
        } else {
            return 0;
        }
    }

    public Object getItem(int position) {
        return qiList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_commutiy, parent, false);
            holder.item_ca_lay = (LinearLayout) convertView.findViewById(R.id.item_ca_lay);
            holder.item_communitybulletin = (LinearLayout) convertView.findViewById(R.id.item_communitybulletin);
            holder.item_ca_msg_read_lay = (LinearLayout) convertView.findViewById(R.id.item_ca_msg_read_lay);
            holder.item_ca_msg_read = (ImageView) convertView.findViewById(R.id.item_ca_msg_read);
            holder.item_ca_msg_checkbox = (CheckBox) convertView.findViewById(R.id.item_ca_msg_checkbox);
            holder.item_ca_msg_tv_title = (TextView) convertView.findViewById(R.id.item_ca_msg_tv_title);
            holder.item_ca_msg_tv_date = (TextView) convertView.findViewById(R.id.item_ca_msg_tv_date);
            holder.view_item_commutiy_line = (TextView) convertView.findViewById(R.id.view_item_commutiy_line);
            holder.main = (LinearLayout) convertView.findViewById(R.id.main);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String bulletinID = "";
        if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
            String bulletinTittle = "";
            String bulletinContent = "";
//            if (qiList.get(position).get("bulletinTittle") != null) { //
//                bulletinTittle = qiList.get(position).get("bulletinTittle").toString();
//            }
            if (qiList.get(position).getBulletinTittle() != null) { //
                bulletinTittle = qiList.get(position).getBulletinTittle();
            }
//            if (qiList.get(position).get("bulletinContent") != null) { //
//                bulletinContent = qiList.get(position).get("bulletinContent").toString();
//            }
            if (qiList.get(position).getBulletinContent() != null) { //
                bulletinContent = qiList.get(position).getBulletinContent();
            }
            holder.item_ca_msg_tv_title.setText(bulletinTittle);
            holder.view_item_commutiy_line.setVisibility(View.GONE);
//            if (qiList.get(position).get("bulletinDatetime") != null) { //时间
//                String time = qiList.get(position).get("bulletinDatetime").toString().substring(0, 10);
//                holder.item_ca_msg_tv_date.setText(time);
//            } else {
//                holder.item_ca_msg_tv_date.setText("");
//            }
            if (qiList.get(position).getBulletinDatetime() != null) { //时间
                String time = qiList.get(position).getBulletinDatetime().substring(0, 10);
                holder.item_ca_msg_tv_date.setText(time);
            } else {
                holder.item_ca_msg_tv_date.setText("");
            }
//            if (qiList.get(position).get("bulletinID") != null) {
//                bulletinID = qiList.get(position).get("bulletinID").toString();
//            }
//            if (qiList.get(position).getBulletinID() != null) {
//                bulletinID = qiList.get(position).getBulletinID();
//            }
            if (qiList.get(position).getBulletinID() != 0) {
                bulletinID = qiList.get(position).getBulletinID()+"";
            }
        } else {
            holder.item_ca_msg_tv_title.setText("");
            holder.item_ca_msg_tv_date.setText("");
        }
        if (SharedUtil.getDataList("communityRead")!=null){
            communityRead = SharedUtil.getDataList("communityRead");
            for (String i: communityRead){
                if (i.equals(qiList.get(position).getBulletinID()+"")){
                    holder.item_ca_msg_read.setVisibility(View.INVISIBLE);
                    break;
                }else {
                    holder.item_ca_msg_read.setVisibility(View.VISIBLE);
                }
            }
        }
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, BulletinDetailsActivity.class);
                intent1.putExtra("bulletinId",qiList.get(position).getBulletinID());
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent1);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        LinearLayout item_communitybulletin;
        LinearLayout item_ca_lay;
        LinearLayout item_ca_msg_read_lay; //图片布局
        ImageView item_ca_msg_read;// 图片 表示未读
        CheckBox item_ca_msg_checkbox;//点击编辑的checkbox
        TextView item_ca_msg_tv_title;//显示文本
        TextView item_ca_msg_tv_date;//显示时间
        TextView view_item_commutiy_line;//线
        LinearLayout main;

    }
}
