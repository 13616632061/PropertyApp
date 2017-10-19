package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.BulletinDetailsActivity;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.util.ACache;
import com.glory.bianyitong.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucy on 2016/11/11.
 * 社区公共
 */
public class CommunityAnnouceAdapter extends BaseQuickAdapter<ItemMenu<listCommunityBulletinInfo.ListCommunityBulletinBean>,BaseViewHolder> {
    List<String > communityRead ;


    public CommunityAnnouceAdapter(@LayoutRes int layoutResId, @Nullable List<ItemMenu<listCommunityBulletinInfo.ListCommunityBulletinBean>> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMenu<listCommunityBulletinInfo.ListCommunityBulletinBean> item) {
        ImageView imageView= helper.getView(R.id.item_ca_msg_read);
        helper.setText(R.id.item_ca_msg_tv_title, item.getData().getBulletinTittle());
        if (item.getData().getBulletinDatetime() != null) { //时间
            String time = item.getData().getBulletinDatetime().substring(0, 10);
            helper.setText(R.id.item_ca_msg_tv_date, time);
        } else {
            helper.setText(R.id.item_ca_msg_tv_date, "");
        }
        communityRead= SharedUtil.getDataList("communityRead");
        if (communityRead != null) {
            for (String i : communityRead) {
                if (i.equals(item.getData().getBulletinID() + "")) {
                    imageView.setVisibility(View.INVISIBLE);
                    break;
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        }

    }






//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_commutiy, parent, false);
//            holder.item_ca_lay = (LinearLayout) convertView.findViewById(R.id.item_ca_lay);
//            holder.item_communitybulletin = (LinearLayout) convertView.findViewById(R.id.item_communitybulletin);
//            holder.item_ca_msg_read_lay = (LinearLayout) convertView.findViewById(R.id.item_ca_msg_read_lay);
//            holder.item_ca_msg_read = (ImageView) convertView.findViewById(R.id.item_ca_msg_read);
//            holder.item_ca_msg_checkbox = (CheckBox) convertView.findViewById(R.id.item_ca_msg_checkbox);
//            holder.item_ca_msg_tv_title = (TextView) convertView.findViewById(R.id.item_ca_msg_tv_title);
//            holder.item_ca_msg_tv_date = (TextView) convertView.findViewById(R.id.item_ca_msg_tv_date);
//            holder.view_item_commutiy_line = (TextView) convertView.findViewById(R.id.view_item_commutiy_line);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }  //是否编辑，编辑就显示checkbox
//        if (!isDoMore) {
//            holder.item_ca_msg_checkbox.setVisibility(View.GONE);
//            holder.item_ca_msg_read_lay.setVisibility(View.VISIBLE);
//        } else {
//            holder.item_ca_msg_checkbox.setVisibility(View.VISIBLE);
//            holder.item_ca_msg_read_lay.setVisibility(View.GONE);
//        }
//        if (position == qiList.size() - 1) {
//            holder.view_item_commutiy_line.setVisibility(View.GONE);
//        } else {
//            holder.view_item_commutiy_line.setVisibility(View.VISIBLE);
//        }
//
//        holder.item_ca_msg_checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getIsCheck().get(position)) {
//                    checkList.put(position, false);
//                    setIsCheck(checkList);
//                } else {
//                    checkList.put(position, true);
//                    setIsCheck(checkList);
//                }
//            }
//        });
//        String bulletinID = "";
//        if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
//            String bulletinTittle = "";
//            String bulletinContent = "";
////            if (qiList.get(position).get("bulletinTittle") != null) { //
////                bulletinTittle = qiList.get(position).get("bulletinTittle").toString();
////            }
//            if (qiList.get(position).getBulletinTittle() != null) { //
//                bulletinTittle = qiList.get(position).getBulletinTittle();
//            }
////            if (qiList.get(position).get("bulletinContent") != null) { //
////                bulletinContent = qiList.get(position).get("bulletinContent").toString();
////            }
//            if (qiList.get(position).getBulletinContent() != null) { //
//                bulletinContent = qiList.get(position).getBulletinContent();
//            }
//            holder.item_ca_msg_tv_title.setText(bulletinTittle);
//
////            if (qiList.get(position).get("bulletinDatetime") != null) { //时间
////                String time = qiList.get(position).get("bulletinDatetime").toString().substring(0, 10);
////                holder.item_ca_msg_tv_date.setText(time);
////            } else {
////                holder.item_ca_msg_tv_date.setText("");
////            }
//            if (qiList.get(position).getBulletinDatetime() != null) { //时间
//                String time = qiList.get(position).getBulletinDatetime().substring(0, 10);
//                holder.item_ca_msg_tv_date.setText(time);
//            } else {
//                holder.item_ca_msg_tv_date.setText("");
//            }
////            if (qiList.get(position).get("bulletinID") != null) {
////                bulletinID = qiList.get(position).get("bulletinID").toString();
////            }
////            if (qiList.get(position).getBulletinID() != null) {
////                bulletinID = qiList.get(position).getBulletinID();
////            }
//            if (qiList.get(position).getBulletinID() != 0) {
//                bulletinID = qiList.get(position).getBulletinID()+"";
//            }
//        } else {
//            holder.item_ca_msg_tv_title.setText("");
//            holder.item_ca_msg_tv_date.setText("");
//        }
////        boolean has = false;
////        String[] array = Database.readbulletinid.split(",");
////        if (array != null && array.length > 0) {
////            for (int i = 0; i < array.length; i++) {
////                if (array[i].equals(bulletinID)) {
////                    has = true;
////                }
////            }
////        }
////        if (has) { //Database.readbulletinid 里面的都是已读的,
////            holder.item_ca_msg_read.setVisibility(View.INVISIBLE);
////        } else {
////            holder.item_ca_msg_read.setVisibility(View.VISIBLE);
////        }
//
//        //上面是队点击进行处理，这里是对显示进行处理
//        if (getIsCheck().get(position)) {
//            holder.item_ca_msg_checkbox.setChecked(true);
//        } else {
//            holder.item_ca_msg_checkbox.setChecked(false);
//        }
//
//        if (SharedUtil.getDataList("communityRead")!=null){
//            communityRead = SharedUtil.getDataList("communityRead");
//            for (String i: communityRead){
//                if (i.equals(qiList.get(position).getBulletinID()+"")){
//                    holder.item_ca_msg_read.setVisibility(View.INVISIBLE);
//                    break;
//                }else {
//                    holder.item_ca_msg_read.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//
//        holder.item_communitybulletin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
//
////                    if (qiList.get(position).get("bulletinID") != null) {
////                        String bulletinId = qiList.get(position).get("bulletinID").toString();
////                        Log.i("resultString", "bulletinId--------" + bulletinId);
////                        boolean isread = false; //默认未读
////                        String[] array = Database.readbulletinid.split(",");
////                        if (array != null && array.length > 0) {
////                            for (int i = 0; i < array.length; i++) {
////                                if (array[i].equals(bulletinId)) {
////                                    isread = true;
////                                } else {
////                                    isread = false;
////                                }
////                            }
////                        }
////                        if (!isread) {
////                            Database.readbulletinid = Database.readbulletinid + bulletinId + ","; //id 拼接字符串 ,分隔 "id,id2,id3"
////                            Database.notreadbulletinSize --;
////                        }
////                    }
////                    if (qiList.get(position).getBulletinID() != 0) {//qiList.get(position).getBulletinID() != null
////                        String bulletinId = qiList.get(position).getBulletinID()+"";
////                        Log.i("resultString", "bulletinId--------" + bulletinId);
////                        boolean isread = false; //默认未读
////                        String[] array = Database.readbulletinid.split(",");
////                        if (array != null && array.length > 0) {
////                            for (int i = 0; i < array.length; i++) {
////                                if (array[i].equals(bulletinId)) {
////                                    isread = true;
////                                } else {
////                                    isread = false;
////                                }
////                            }
////                        }
////                        if (!isread) {
////                            Database.readbulletinid = Database.readbulletinid + bulletinId + ","; //id 拼接字符串 ,分隔 "id,id2,id3"
////                            Database.notreadbulletinSize --;
////                        }
////                    }
//                    Intent intent = new Intent(context, BulletinDetailsActivity.class);
//                    intent.putExtra("bulletinId",qiList.get(position).getBulletinID());
//                    if (qiList.get(position).getBulletinContent() != null) {
//
//                        intent.putExtra("bulletinContent", qiList.get(position).getBulletinContent());
//                    }else {
//                        intent.putExtra("bulletinContent", "");
//                    }
//
////                    if (qiList.get(position).getCommunityName() != null) {
////                        intent.putExtra("communityName", qiList.get(position).getCommunityName());
////                    } else {
////                        intent.putExtra("communityName", "");
////                    }
//
//
//                    if (qiList.get(position).getBulletinTittle() != null) {
//                        intent.putExtra("bulletinTittle", qiList.get(position).getBulletinTittle());
//                    } else {
//                        intent.putExtra("bulletinTittle", "");
//                    }
//
//                    if (qiList.get(position).getBulletinDatetime() != null) {
//                        intent.putExtra("bulletinDatetime", qiList.get(position).getBulletinDatetime().substring(0, 10));
//                    } else {
//                        intent.putExtra("bulletinDatetime", "");
//                    }
//                    intent.putExtra("PushID", 0);
//                    context.startActivity(intent);
//                    ACache cache=ACache.get(context);
//                    cache.put(Constant.bulletinID, Database.readbulletinid); //缓存已读消息
//                }
//
//            }
//        });
//        return convertView;
//    }
//
//    class ViewHolder {
//        LinearLayout item_communitybulletin;
//        LinearLayout item_ca_lay;
//        LinearLayout item_ca_msg_read_lay; //图片布局
//        ImageView item_ca_msg_read;// 图片 表示未读
//        CheckBox item_ca_msg_checkbox;//点击编辑的checkbox
//        TextView item_ca_msg_tv_title;//显示文本
//        TextView item_ca_msg_tv_date;//显示时间
//        TextView view_item_commutiy_line;//线
//    }
}
