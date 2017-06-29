package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.glory.bianyitong.bean.YellowPageAllInfo;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;

import java.util.List;

/**
 * Created by lucy on 2016/11/11.
 * 便民黄页
 */
public class ConveniencePhoneAdapter extends BaseAdapter {

    private Context context;
//    private List<LinkedTreeMap<String, Object>> qiList;
    private List<YellowPageAllInfo> qiList;
    private Handler mhandler;

    public ConveniencePhoneAdapter(Context context, List<YellowPageAllInfo> qiList, Handler mhandler) {//List<LinkedTreeMap<String, Object>>
        this.context = context;
        this.qiList = qiList;
        this.mhandler = mhandler;
        initData();
    }


    private void initData() {
        for (int i = 0; i < qiList.size(); i++) {
            //全部设为false 表示未点击和未读
//            checkList.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return qiList.size();
    }

    @Override
    public Object getItem(int i) {
        return qiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_callphone, parent, false);
            holder.iv_police_log = (ImageView) convertView.findViewById(R.id.iv_police_log);
            holder.tv_police_service = (TextView) convertView.findViewById(R.id.tv_police_service);
            holder.lay_more_down = (RelativeLayout) convertView.findViewById(R.id.lay_more_down);
//            holder.ck_more_down = (CheckBox) convertView.findViewById(R.id.ck_more_down);
            holder.iv_more_down = (ImageView) convertView.findViewById(R.id.iv_more_down);

            holder.scrollView_call_list = (ScrollView) convertView.findViewById(R.id.scrollView_call_list);
            holder.lay_callphone_list = (LinearLayout) convertView.findViewById(R.id.lay_callphone_list);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.scrollView_call_list.setVisibility(View.GONE);

        if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
//            if (qiList.get(position).get("yellowPageGroupName") != null) { //名称
//                holder.tv_police_service.setText(qiList.get(position).get("yellowPageGroupName").toString());
//            }else {
//                holder.tv_police_service.setText("");
//            }
            if (qiList.get(position).getPageInfo().getYellowPageGroupName() != null) { //名称
                holder.tv_police_service.setText(qiList.get(position).getPageInfo().getYellowPageGroupName());
            }else {
                holder.tv_police_service.setText("");
            }
//            if (qiList.get(position).get("list") != null) {
//                List<LinkedTreeMap<String, Object>> list = (List<LinkedTreeMap<String, Object>>) qiList.get(position).get("list");
//                if (list.size() > 0) {
//                    ScrollViewLayout(context, list, holder.lay_callphone_list);
//                }
//            }else {
//                holder.lay_callphone_list.setVisibility(View.GONE);
//            }
            if (qiList.get(position).getListYellowPage() != null) {
                List<YellowPageAllInfo.ListYellowPageBean> list =  qiList.get(position).getListYellowPage();
                if (list.size() > 0) {
                    ScrollViewLayout(context, list, holder.lay_callphone_list);
                }
            }else {
                holder.lay_callphone_list.setVisibility(View.GONE);
            }
//            if (qiList.get(position).get("yellowPageGroupPicture") != null) { //头像
//                ServiceDialog.setPicture(qiList.get(position).get("yellowPageGroupPicture").toString(), holder.iv_police_log, null);
//            }else {
//                holder.iv_police_log.setImageResource(R.drawable.wait_round);
//            }
            if (qiList.get(position).getPageInfo().getYellowPageGroupPicture()!= null) { //头像
                ServiceDialog.setPicture(qiList.get(position).getPageInfo().getYellowPageGroupPicture(), holder.iv_police_log, null);
            }else {
                holder.iv_police_log.setImageResource(R.drawable.wait_round);
            }
        }else {
            holder.tv_police_service.setText("");
            holder.lay_callphone_list.setVisibility(View.GONE);
            holder.iv_police_log.setImageResource(R.drawable.wait_round);
        }
        final ImageView icon = holder.iv_more_down;
        final ScrollView lay = holder.scrollView_call_list;
        icon.setBackgroundResource(R.drawable.icon_more_up);
        lay.setVisibility(View.VISIBLE);
        holder.lay_more_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isgone = lay.getVisibility();
                if (isgone == 0) {
                    icon.setBackgroundResource(R.drawable.icon_more_down);
                    lay.setVisibility(View.GONE);
                } else {
                    icon.setBackgroundResource(R.drawable.icon_more_up);
                    lay.setVisibility(View.VISIBLE);
                }
            }
        });
//        holder.ck_more_down.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//隐藏 显示电话列表
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    lay.setVisibility(View.GONE);
//                } else {
//                    lay.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        return convertView;
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<YellowPageAllInfo.ListYellowPageBean> list, LinearLayout lay_gallery) {//List<LinkedTreeMap<String, Object>>
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_phone, lay_gallery, false);

                final TextView item_tv_call_name = (TextView) view.findViewById(R.id.item_tv_call_name);
                final TextView item_tv_call_line = (TextView) view.findViewById(R.id.item_tv_call_line);
//            if (list != null && list.get(i).get("picture") != null && list.get(i).get("picture").toString().length() != 0 && !list.get(i).get("picture").toString().equals("")) {
//                setPicture(list.get(i).get("picture").toString(), type_pic, ImageView.ScaleType.FIT_CENTER);
//            }
//                if (list != null && list.get(i).get("yellowPageContext") != null && list.get(i).get("yellowPageContext").toString().length() != 0 && !list.get(i).get("yellowPageContext").toString().equals("")) {
//                    item_tv_call_name.setText(list.get(i).get("yellowPageContext").toString());
//                }else {
//                    item_tv_call_name.setText("");
//                }
                if (list != null && list.get(i).getYellowPageContext() != null && list.get(i).getYellowPageContext().length() != 0) {
                    item_tv_call_name.setText(list.get(i).getYellowPageContext());
                }else {
                    item_tv_call_name.setText("");
                }
                if (i == list.size() - 1) {
                    item_tv_call_line.setVisibility(View.GONE);
                }
                final int j = i;
                item_tv_call_name.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
//                        if (list.get(j).get("yellowPageTEL") != null) {
//                            Message msg = new Message();
//                            msg.what = 0;
//                            msg.obj = list.get(j).get("yellowPageTEL").toString();
//                            mhandler.sendMessage(msg);
//                        }
                        if (list.get(j).getYellowPageTEL() != null) {  //电话
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = list.get(j).getYellowPageTEL();
                            mhandler.sendMessage(msg);
                        }
                    }
                });

                lay_gallery.addView(view);
            }
        }
    }

    class ViewHolder {
        ImageView iv_police_log;
        TextView tv_police_service;//显示文本
        RelativeLayout lay_more_down;//作点击
        //        CheckBox ck_more_down;
        ImageView iv_more_down;
        ScrollView scrollView_call_list;
        LinearLayout lay_callphone_list;

    }

}
