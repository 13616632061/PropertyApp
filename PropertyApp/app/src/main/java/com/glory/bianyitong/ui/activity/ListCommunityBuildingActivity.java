package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestCommunityUnit;
import com.glory.bianyitong.bean.entity.response.ResponseQueryBuild;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUnit;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 楼栋列表页
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AREA_LIST,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class ListCommunityBuildingActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.province_list)
    LinearLayout communityBuilding_list;

//    @InjectParam(key="list")
    ResponseQueryBuild queryBuild;

    @Override
    protected int getContentId() {
        return R.layout.activity_province;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.choose_floor), true, "");//选择楼栋
        queryBuild= (ResponseQueryBuild) getIntent().getSerializableExtra("data");

        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ListCommunityBuildingActivity.this.finish();
            }
        });

        ScrollViewLayout(ListCommunityBuildingActivity.this, queryBuild.getListCommunityBuilding(), communityBuilding_list);

    }


    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<ResponseQueryBuild.ListCommunityBuildingBean> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_province, lay_gallery, false);
                final TextView tv_community_name = (TextView) view.findViewById(R.id.tv_province_name);
                final TextView view_community_line = (TextView) view.findViewById(R.id.view_province_line);
                tv_community_name.setText(list.get(i).getBuildingName()); //小区名称

                if (i == list.size() - 1) { //最后一根线
                    view_community_line.setVisibility(View.GONE);
                }
                final int j = i;
                tv_community_name.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).getBuildingID() >0) {
                            Database.buildingName = list.get(j).getBuildingName();
                            Database.buildingID =list.get(j).getBuildingID();

                            Database.unitName = "";
                            Database.unitID = 0;
                            Database.roomName = "";
                            Database.roomID = 0;
                            ListCommunityBuildingActivity.this.finish();
                            request_cell(Database.buildingID);
                        }

                    }
                });

                lay_gallery.addView(view);
            }
        }
    }
    private ProgressDialog progressDialog = null;
    /**
     * 获取单元
     * @param buildingID
     */
    private void request_cell(int buildingID) { //获取单元
        try {


            Map<String,Object> map=new BaseRequestBean().getBaseRequest();
            map.put("communityUnit",new RequestCommunityUnit(buildingID));
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
                    ResponseQueryUnit queryUnit=new Gson().fromJson(s,ResponseQueryUnit.class);
                    if(queryUnit.getStatusCode()==1){
                        Database.listCommunityUnit=queryUnit.getListCommunityUnit();
                        Intent intent = new Intent(ListCommunityBuildingActivity.this, ListCommunityUnitActivity.class);
                        startActivity(intent);
                    }else {
                        showShort(queryUnit.getAlertMessage());
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
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_UNIT,json);
        }catch (Exception e){

        }
    }

}
