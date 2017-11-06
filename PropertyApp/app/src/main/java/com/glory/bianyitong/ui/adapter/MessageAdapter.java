package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.MessageInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.MessageDetailsActivity;
import com.glory.bianyitong.util.ACache;
import com.glory.bianyitong.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucy on 2016/11/11.
 */
public class MessageAdapter extends BaseAdapter {

    public static HashMap<Integer, Boolean> checkList;//这是头部那个chexkbox
    private Context context;
//    private List<LinkedTreeMap<String, Object>> qiList;
    private List<MessageInfo.ListSystemMsgBean> qiList;
    private boolean isDoMore; //是否进行编辑默认为false
    private List<String> messageRead;

    public MessageAdapter(Context context, List<MessageInfo.ListSystemMsgBean> qiList, HashMap<Integer, Boolean> checkList, boolean isDoMore) {//List<LinkedTreeMap<String, Object>>
        this.context = context;
        this.qiList = qiList;
        this.checkList = checkList;
        this.isDoMore = isDoMore;
        initData();
    }

    //暴露给外面使用这是checkbox
    public static HashMap<Integer, Boolean> getIsCheck() {
        return MessageAdapter.checkList;
    }

    public static void setIsCheck(HashMap<Integer, Boolean> isCheck) {
        MessageAdapter.checkList = isCheck;
    }

    public void setIsDoMore(Boolean isDoMore) {
        this.isDoMore = isDoMore;

    }

    public void updateList(List<MessageInfo.ListSystemMsgBean> qiList) {//List<LinkedTreeMap<String, Object>> qiList
        this.qiList = qiList;

    }

    public void upDateCheckList() {
        checkList.clear();
        for (int i = 0; i < qiList.size(); i++) {
            checkList.put(i, false);

        }
    }

    private void initData() {
        for (int i = 0; i < qiList.size(); i++) {
            //全部设为false 表示未点击和未读
            checkList.put(i, false);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_message, parent, false);
            holder.item_mes_lay = (LinearLayout) convertView.findViewById(R.id.item_mes_lay);
            holder.item_msg_read_lay = (LinearLayout) convertView.findViewById(R.id.item_msg_read_lay);
            holder.item_msg_read = (ImageView) convertView.findViewById(R.id.item_msg_read);
            holder.item_msg_checkbox = (CheckBox) convertView.findViewById(R.id.item_msg_checkbox);
            holder.item_msg_tv_title = (TextView) convertView.findViewById(R.id.item_msg_tv_title);
            holder.item_msg_tv_date = (TextView) convertView.findViewById(R.id.item_msg_tv_date);
            holder.view_item_message_line = (TextView) convertView.findViewById(R.id.view_item_message_line);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }  //是否编辑，编辑就显示checkbox
        if (!isDoMore) {
            holder.item_msg_checkbox.setVisibility(View.GONE);
            holder.item_msg_read_lay.setVisibility(View.VISIBLE);
        } else {
            holder.item_msg_checkbox.setVisibility(View.VISIBLE);
            holder.item_msg_read_lay.setVisibility(View.GONE);
        }
        if (position == qiList.size() - 1) {
            holder.view_item_message_line.setVisibility(View.GONE);
        }

        holder.item_msg_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIsCheck().get(position)) {
                    checkList.put(position, false);
                    setIsCheck(checkList);
                } else {
                    checkList.put(position, true);
                    setIsCheck(checkList);
                }
            }
        });
        String messageID = "";
        if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
            String bulletinTittle = "";
            String bulletinContent = "";
//            if (qiList.get(position).get("messageTitle") != null) { //
//                bulletinTittle = qiList.get(position).get("messageTitle").toString();
//            }
            if (qiList.get(position).getMessageTitle() != null) { //
                bulletinTittle = qiList.get(position).getMessageTitle();
            }
//            if (qiList.get(position).get("messageContext") != null) { //
//                bulletinContent = qiList.get(position).get("messageContext").toString();
//            }
            if (qiList.get(position).getMessageContext() != null) { //
                bulletinContent = qiList.get(position).getMessageContext();
            }
            holder.item_msg_tv_title.setText(bulletinTittle);

//            if (qiList.get(position).get("messageTime") != null) { //时间
//                String time = qiList.get(position).get("messageTime").toString().substring(0, 10);
//                holder.item_msg_tv_date.setText(time);
//            } else {
//                holder.item_msg_tv_date.setText("");
//            }
            if (qiList.get(position).getMessageTime() != null) { //时间
                String time = qiList.get(position).getMessageTime().substring(0, 10);
                holder.item_msg_tv_date.setText(time);
            } else {
                holder.item_msg_tv_date.setText("");
            }
//            if (qiList.get(position).get("messageID") != null) {
//                messageID = qiList.get(position).get("messageID").toString();
//            }
            if (qiList.get(position).getMessageID() != null) {
                messageID = qiList.get(position).getMessageID();
            }
        } else {
            holder.item_msg_tv_title.setText("");
            holder.item_msg_tv_date.setText("");
        }

//        boolean has = false;
//        String[] array = Database.readmessageid.split(",");
//        if (array != null && array.length > 0) {
//            for (int i = 0; i < array.length; i++) {
//                if (array[i].equals(messageID)) {
//                    has = true;
//                }
//            }
//        }
//        if (has) { //Database.readmessageid 里面的都是已读的,
//            holder.item_msg_read.setVisibility(View.INVISIBLE);
//        } else {
//            holder.item_msg_read.setVisibility(View.VISIBLE);
//        }

        //上面是队点击进行处理，这里是对显示进行处理
        if (getIsCheck().get(position)) {
            holder.item_msg_checkbox.setChecked(true);
        } else {
            holder.item_msg_checkbox.setChecked(false);
        }
        if (SharedUtil.getDataList("messageRead")!=null){
            messageRead = SharedUtil.getDataList("messageRead");
            for (String i: messageRead){
                if (i.equals(qiList.get(position).getMessageID())){
                    holder.item_msg_read.setVisibility(View.INVISIBLE);
                    break;
                }else {
                    holder.item_msg_read.setVisibility(View.VISIBLE);
                }
            }
        }
        holder.item_mes_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedUtil.getDataList("messageRead")!=null){
                    messageRead = SharedUtil.getDataList("messageRead");
                    messageRead.add(qiList.get(position).getMessageID());
                }else {
                    messageRead=new ArrayList<String>();
                    for (String s:messageRead){
                        if (s.equals(qiList.get(position).getMessageID())){
                            break;
                        }
                    }
                    messageRead.add(qiList.get(position).getMessageID());
                }
                SharedUtil.setDataList("messageRead",messageRead);

//                if (qiList.get(position).getMessageID() != null) {
//                    String messageID = qiList.get(position).getMessageID();
//                    Log.i("resultString", "messageID--------" + messageID);
//                    boolean isread = false; //默认未读
//                    String[] array = Database.readmessageid.split(",");
//                    if (array != null && array.length > 0) {
//                        for (int i = 0; i < array.length; i++) {
//                            if (array[i].equals(messageID)) {
//                                isread = true;
//                            } else {
//                                isread = false;
//                            }
//                        }
//                    }
//                    if (!isread) {
//                        Database.readmessageid = Database.readmessageid + messageID + ","; //id 拼接字符串 ,分隔 "id,id2,id3"
//                        Database.notreadmessageidSize--;
//                    }
//                }
                Intent intent = new Intent(context, MessageDetailsActivity.class);
//                if (qiList.get(position).get("messageContext") != null) {
//                    intent.putExtra("messageContext", qiList.get(position).get("messageContext").toString());
//                } else {
//                    intent.putExtra("messageContext", "");
//                }
                if (qiList.get(position).getMessageContext() != null) {
                    intent.putExtra("messageContext", qiList.get(position).getMessageContext().toString());
                } else {
                    intent.putExtra("messageContext", "");
                }
//                if (qiList.get(position).get("messageTitle") != null) {
//                    intent.putExtra("messageTitle", qiList.get(position).get("messageTitle").toString());
//                } else {
//                    intent.putExtra("messageTitle", "");
//                }
                if (qiList.get(position).getMessageTitle() != null) {
                    intent.putExtra("messageTitle", qiList.get(position).getMessageTitle());
                } else {
                    intent.putExtra("messageTitle", "");
                }
//                if (qiList.get(position).get("messageTime") != null) {
//                    intent.putExtra("messageTime", qiList.get(position).get("messageTime").toString().substring(0, 10));
//                } else {
//                    intent.putExtra("messageTime", "");
//                }
                if (qiList.get(position).getMessageTime() != null) {
                    intent.putExtra("messageTime", qiList.get(position).getMessageTime().substring(0, 10));
                } else {
                    intent.putExtra("messageTime", "");
                }
                intent.putExtra("PushID", Integer.parseInt(qiList.get(position).getMessageID()));
                context.startActivity(intent);
//                ACache cache=ACache.get(context);
//                cache.put(Constant.messageID, Database.readmessageid); //缓存已读消息
            }
        });
        return convertView;
    }

    class ViewHolder {
        LinearLayout item_mes_lay;
        LinearLayout item_msg_read_lay; //图片布局
        ImageView item_msg_read;// 图片 表示未读
        CheckBox item_msg_checkbox;//点击编辑的checkbox
        TextView item_msg_tv_title;//显示文本
        TextView item_msg_tv_date;//显示时间
        TextView view_item_message_line;//线
    }
}
