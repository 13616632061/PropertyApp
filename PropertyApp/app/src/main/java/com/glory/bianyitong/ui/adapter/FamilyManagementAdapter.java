/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.glory.bianyitong.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.FamilyInfo;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by YOLANDA on 2016/8/24.
 */
public class FamilyManagementAdapter extends SwipeMenuAdapter<FamilyManagementAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_SINGLE = 2;

    private List<FamilyInfo.ListFamilyBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public FamilyManagementAdapter(List<FamilyInfo.ListFamilyBean> viewTypeBeanList) {
        this.mViewTypeBeanList = viewTypeBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }



    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_MENU_SINGLE;
    }

    @Override
    public int getItemCount() {
        return mViewTypeBeanList == null ? 0 : mViewTypeBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_familymanagement, parent, false);
    }

    @Override
    public FamilyManagementAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

//    @Override
//    public void onBindViewHolder(FamilyManagementAdapter.DefaultViewHolder holder, int position) {
//        if (mViewTypeBeanList != null && mViewTypeBeanList.size() > 0) {
//            holder.setData(mViewTypeBeanList.get(position));
//            holder.setOnItemClickListener(mOnItemClickListener);
//        }
//    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        if (mViewTypeBeanList != null && mViewTypeBeanList.size() > 0) {
            holder.setData(mViewTypeBeanList.get(position));
            holder.setOnItemClickListener(mOnItemClickListener);
        }
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout lay_list_item_award;
        TextView item_award_people_name;
        TextView item_award_people_role;
        TextView item_award_people_limit;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            lay_list_item_award = (RelativeLayout) itemView.findViewById(R.id.lay_list_item_award);
            item_award_people_name = (TextView) itemView.findViewById(R.id.item_award_people_name);
            item_award_people_role = (TextView) itemView.findViewById(R.id.item_award_people_role);
            item_award_people_limit = (TextView) itemView.findViewById(R.id.item_award_people_limit);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(FamilyInfo.ListFamilyBean map) {
            if (map.getFamilyUserName()!= null && !map.getFamilyUserName().equals("")) {
                item_award_people_name.setText(map.getFamilyUserName().toString()); //授权人姓名
            }else {
                item_award_people_name.setText("");
            }
            int identity = map.getIdentityType();
            if (identity == 1) {//1 家人 2租客 3临时客人
                item_award_people_role.setText("家人");
            } else if (identity == 2) {
                item_award_people_role.setText("亲戚");
            } else if (identity == 3) {
                item_award_people_role.setText("朋友");
            }else {
                item_award_people_role.setText("");
            }
            item_award_people_limit.setText("无限制");
//                boolean timeLimit = map.isTimeLimit();
//                if (timeLimit) { //限制
//                    if (map.getStartDate() != null && map.getEndDate() != null) {
//                        String startDate = map.getStartDate();
//                        String endDate = map.getEndDate();
//                        //startDate.substring(0, 4); //年 2016
//                        //startDate.substring(5, 7); //月 12
//                        //startDate.substring(8, 10);//日 20
//                        //目标格式 2016.12.20-2017.01.20
//                        String time = startDate.substring(0, 4) + "." + startDate.substring(5, 7) + "." + startDate.substring(8, 10) + "－" +
//                                endDate.substring(0, 4) + "." + endDate.substring(5, 7) + "." + endDate.substring(8, 10);
//                        item_award_people_limit.setText(time);
//                    }
//                } else {
//                    item_award_people_limit.setText("无限制");
//                }
            }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }

    }

}
