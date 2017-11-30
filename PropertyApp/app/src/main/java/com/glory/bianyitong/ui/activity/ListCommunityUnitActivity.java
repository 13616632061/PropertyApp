package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestCommunityRoom;
import com.glory.bianyitong.bean.entity.response.ResponseQueryRoom;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUnit;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 单元 列表页
 */
public class ListCommunityUnitActivity extends BaseActivity {
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
        inintTitle(getString(R.string.select_the_unit), true, "");//选择单元
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListCommunityUnitActivity.this.finish();
            }
        });

        ScrollViewLayout(ListCommunityUnitActivity.this, Database.listCommunityUnit, communityBuilding_list);

    }


    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<ResponseQueryUnit.ListCommunityUnitBean> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_province, lay_gallery, false);
                final TextView tv_unitName = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_community_line = (TextView) view.findViewById(R.id.view_province_line);

                tv_unitName.setText(list.get(i).getUnitName()); //单元名称

                if (i == list.size() - 1) { //最后一根线
                    view_community_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_unitName.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).getUnitID()>0) {
                            Database.unitName = list.get(j).getUnitName();
                            Database.unitID =list.get(j).getUnitID();

                            Database.roomName = "";
                            Database.roomID = 0;
                            ListCommunityUnitActivity.this.finish();
                            request_room( Database.unitID);
                        }

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }
    private ProgressDialog progressDialog = null;
    /**
     * 查询房间号
     * @param unitID
     */
    private void request_room(int unitID) { //获取房号
        try {


            Map<String,Object> map=new BaseRequestBean().getBaseRequest();
            map.put("communityRoom",new RequestCommunityRoom(unitID));
            String json=new Gson().toJson(map);
            progressDialog = ProgressDialog.show(this, "","加载中", true);//开锁中
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    if(TextUtil.isEmpty(s)){
                        showShort("系统异常");
                        return;
                    }
                    ResponseQueryRoom queryRoom=new Gson().fromJson(s,ResponseQueryRoom.class);
                    if(queryRoom.getStatusCode()==1){
                        Database.listCommunityRoom=queryRoom.getListCommunityRoom();
                        Intent intent = new Intent(ListCommunityUnitActivity.this, ListCommunityRoomActivity.class);
                        startActivity(intent);
                    }else {
                        showShort(queryRoom.getAlertMessage());
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {                progressDialog.dismiss();
                }
                @Override
                public void parseError() {                progressDialog.dismiss();
                }
                @Override
                public void onBefore() {}
                @Override
                public void onAfter() {                progressDialog.dismiss();
                }
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_ROOM,json);
        }catch (Exception e){

        }
    }


}
