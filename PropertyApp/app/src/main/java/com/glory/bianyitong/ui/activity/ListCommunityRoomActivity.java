package com.glory.bianyitong.ui.activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.entity.response.ResponseQueryRoom;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.ActivityManager;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 房号 列表页
 */
public class ListCommunityRoomActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.province_list)
    LinearLayout communityBuilding_list;

    @Override
    protected int getContentId() {
        return R.layout.activity_province;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.select_room_number), true, "");//选择房号
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListCommunityRoomActivity.this.finish();
            }
        });

        ScrollViewLayout(ListCommunityRoomActivity.this, Database.listCommunityRoom, communityBuilding_list);

    }


    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<ResponseQueryRoom.ListCommunityRoomBean> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_province, lay_gallery, false);
                final TextView tv_unitName = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_community_line = (TextView) view.findViewById(R.id.view_province_line);
                tv_unitName.setText(list.get(i).getRoomName());  //房号名称

                if (i == list.size() - 1) { //最后一根线
                    view_community_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_unitName.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).getRoomID() >0) {
                            Database.roomName = list.get(j).getRoomName();
                            Database.roomID =  list.get(j).getRoomID();

                            ListCommunityRoomActivity.this.finish();
                            if (ActivityManager.getActivity("addroomactivity")!=null){
                                ActivityManager.removeActivity("addroomactivity");
                            }
                            startActivity(AddRoomActivity.class);
                        }

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }


}
