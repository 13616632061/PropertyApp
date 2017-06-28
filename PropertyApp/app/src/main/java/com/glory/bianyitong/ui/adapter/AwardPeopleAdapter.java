package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.AddAwardActivity;
import com.glory.bianyitong.ui.activity.GoodsDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 * Created by lucy on 2016/11/14.
 * 被授权人列表
 */
public class AwardPeopleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserLockInfo.ListUserLockMappingBean> list;

    private LayoutInflater mInflater = null;

    public AwardPeopleAdapter(Context context, ArrayList<UserLockInfo.ListUserLockMappingBean> list) {
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
            convertView = mInflater.inflate(R.layout.view_item_award_people, null);

            holder.lay_list_item_award = (RelativeLayout) convertView.findViewById(R.id.lay_list_item_award);
            holder.item_award_people_name = (TextView) convertView.findViewById(R.id.item_award_people_name);
            holder.item_award_people_role = (TextView) convertView.findViewById(R.id.item_award_people_role);
            holder.item_award_people_limit = (TextView) convertView.findViewById(R.id.item_award_people_limit);
//            holder.view_award_line = (TextView) convertView.findViewById(R.id.view_award_line);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
//            if (position == list.size() - 1) {
//                holder.view_award_line.setVisibility(View.GONE);
//            }
            if (list.get(position).getAuthorizationUserName() != null && !list.get(position).getAuthorizationUserName().equals("")) {
                holder.item_award_people_name.setText(list.get(position).getAuthorizationUserName()); //授权人姓名
            }else {
                holder.item_award_people_name.setText("");
            }
            //用户身份
            int identity = list.get(position).getUserIdentity();
            if (identity == 1) {//1 家人 2租客 3临时客人
                holder.item_award_people_role.setText(context.getString(R.string.family));//家人
            } else if (identity == 2) {
                holder.item_award_people_role.setText(context.getString(R.string.tenant));//租客
            } else if (identity == 3) {
                holder.item_award_people_role.setText(context.getString(R.string.temporary_guest));//临时客人
            }else {
                holder.item_award_people_role.setText("");
            }

            //时间限制
                boolean timeLimit = list.get(position).isTimeLimit();
                if (timeLimit) { //限制
                    if (list.get(position).getStartDate() != null && list.get(position).getEndDate() != null) {
                        String startDate = list.get(position).getStartDate().toString();
                        String endDate = list.get(position).getEndDate().toString();
                        //startDate.substring(0, 4); //年 2016
                        //startDate.substring(5, 7); //月 12
                        //startDate.substring(8, 10);//日 20
                        //目标格式 2016.12.20-2017.01.20
                        String time = startDate.substring(0, 4) + "." + startDate.substring(5, 7) + "." + startDate.substring(8, 10) + "－" +
                                endDate.substring(0, 4) + "." + endDate.substring(5, 7) + "." + endDate.substring(8, 10);
                        holder.item_award_people_limit.setText(time);
                    }
                } else {
                    holder.item_award_people_limit.setText(context.getString(R.string.unlimited));//无限制
                }

        }

        final RelativeLayout layout = holder.lay_list_item_award;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.awardpeople = list.get(position);
                Intent intent = new Intent(context, AddAwardActivity.class);
                intent.putExtra("from","edit");
                context.startActivity(intent);
            }
        });


//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {

        public RelativeLayout lay_list_item_award;
        public TextView item_award_people_name;
        public TextView item_award_people_role;
        public TextView item_award_people_limit;
//        public TextView view_award_line;
    }

}
