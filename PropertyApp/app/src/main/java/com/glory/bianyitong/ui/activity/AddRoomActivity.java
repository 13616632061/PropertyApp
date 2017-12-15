package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestApplyArea;
import com.glory.bianyitong.bean.entity.request.RequestCommunityRoom;
import com.glory.bianyitong.bean.entity.request.RequestCommunityUnit;
import com.glory.bianyitong.bean.entity.request.RequestQueryUserInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryBuild;
import com.glory.bianyitong.bean.entity.response.ResponseQueryRoom;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUnit;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUserById;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.ActivityManager;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/14.
 * 添加小区
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AREA_ADD, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddRoomActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_building_no) //楼栋
            TextView tv_building_no;
    @BindView(R.id.tv_cell_no) //单元
            TextView tv_cell_no;
    @BindView(R.id.tv_room_no) //房号
            TextView tv_room_no;
    @BindView(R.id.tv_submit_addarea) //提交
            TextView tv_submit_addarea;
    @BindView(R.id.real_name)
    EditText realName;
    private String userID = "";
    private ProgressDialog progressDialog = null;
    private String userName;
    private String loginName;

    @Override
    protected int getContentId() {
        return R.layout.activity_add_room;
    }

    @Override
    protected void init() {
        super.init();
        //初始化标题栏
        inintTitle(getString(R.string.add_community), true, "");//添加小区
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                AddRoomActivity.this.finish();
            }
        });
        ActivityManager.addActivity(this, "addroomactivity");
        user_request();
        userID = RequestUtil.getuserid();
        tv_building_no.setOnClickListener(this);
        tv_cell_no.setOnClickListener(this);
        tv_room_no.setOnClickListener(this);
        tv_submit_addarea.setOnClickListener(this);


    }



    private void user_request() { //获取个人信息
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("user", new RequestQueryUserInfo((String) (map.get("userID"))));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryUserById responseQueryUserById = new Gson().fromJson(s, ResponseQueryUserById.class);
                if (responseQueryUserById.getStatusCode() == 1) {
                    userName = responseQueryUserById.getListUser().get(0).getUserName();
                    loginName = responseQueryUserById.getListUser().get(0).getLoginName();
                    if (userName!=null){
                        realName.setText(userName);
                        realName.setSelection(realName.getText().length());
                    }
                } else {
                }

            }

            @Override
            public void onError() {
            }

            @Override
            public void parseError() {
            }

            @Override
            public void onBefore() {
            }

            @Override
            public void onAfter() {
            }
        }).getEntityData(this, HttpURL.HTTP_POST_QUERY_USER_INFO, json);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_building_no: //楼栋
                if (Database.communityID != 0) {
                    request_building(Database.communityID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_cell_first));//请先选择小区
                }
                break;
            case R.id.tv_cell_no: //单元
                if (Database.buildingID != 0) {
                    request_cell(Database.buildingID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_choose_floor_first));//请先选择楼栋
                }
                break;
            case R.id.tv_room_no: //房号
                if (Database.unitID != 0) {
                    request_room(Database.unitID);
                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_unit_first));//请先选择单元
                }

                break;
            case R.id.tv_submit_addarea: //提交
                if (realName.getText().toString().trim().equals("")){
                    showShort("请填写真实姓名");
                }else if (Database.roomID != 0) {
                    if (realName.getText().toString().trim().equals(userName)){
                        progressDialog = ProgressDialog.show(this, "", "加载中", true);
                        progressDialog.setCanceledOnTouchOutside(true);
                        request_submit(Database.communityID, Database.communityName, Database.id_province, Database.str_province, Database.id_city, Database.str_city
                                , Database.buildingID, Database.buildingName, Database.roomID, Database.roomName, Database.unitID, Database.unitName);
                    }else {
                        save(realName.getText().toString().trim());
                    }

                } else {
                    ToastUtils.showToast(AddRoomActivity.this, getString(R.string.please_select_the_room_first));//请先选择房间
                }
                break;
        }
    }

    //保存
    private void save(final String desc) {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> map2=new HashMap<>();
        map2.put("userName",desc);
        map2.put("loginName",loginName);
        map.put("user",map2);
//        map.put("user",new RequestUserBean(desc));
        String json=new Gson().toJson(map);
        progressDialog = ProgressDialog.show(this, "","加载中", true);
        progressDialog.setCanceledOnTouchOutside(true);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if(TextUtil.isEmpty(s)){
                    showShort("系统异常");
                    return;
                }
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    request_submit(Database.communityID, Database.communityName, Database.id_province, Database.str_province, Database.id_city, Database.str_city
                            , Database.buildingID, Database.buildingName, Database.roomID, Database.roomName, Database.unitID, Database.unitName);
                    progressDialog.dismiss();
                }else {
                    progressDialog.dismiss();
                    ToastUtils.showToast(AddRoomActivity.this,bean.getAlertMessage());//修改失败
                }
            }

            @Override
            public void onError() {
            }

            @Override
            public void parseError() {
            }

            @Override
            public void onBefore() {

            }

            @Override
            public void onAfter() {
            }
        }).getEntityData(this,HttpURL.HTTP_POST_MY_EDITUSERINFO,json);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //楼栋名称
        if (Database.buildingName != null) {
            tv_building_no.setText(Database.buildingName);
        }
        //单元名称
        if (Database.unitName != null) {
            tv_cell_no.setText(Database.unitName);
        }
        //房号名称
        if (Database.roomName != null) {
            tv_room_no.setText(Database.roomName);
        }

    }

    /**
     * 获取楼宇
     *
     * @param communityID
     */
    private void request_building(int communityID) { //获取楼栋
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            Map<String, Object> maps = new HashMap<>();
            maps.put("communityID", communityID);
            map.put("communityBuilding", maps);
            String json = new Gson().toJson(map);
            progressDialog = ProgressDialog.show(this, "", "加载中", true);
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {

                    if (TextUtil.isEmpty(s)) {
                        showShort("系统异常");
                        return;
                    }
                    ResponseQueryBuild queryBuild = new Gson().fromJson(s, ResponseQueryBuild.class);
                    if (queryBuild.getStatusCode() == 1) {
                        Database.list_CommunityBuilding = queryBuild.getListCommunityBuilding();
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityBuildingActivity.class);
                        intent.putExtra("data", queryBuild);
                        startActivity(intent);
//                    if(queryBuild!=null)
//                    Router.build(RouterMapping.ROUTER_ACTIVITY_AREA_LIST)
//                            .with("list",queryBuild)
//                            .go(AddRoomActivity.this);
                    } else {
                        showShort(queryBuild.getAlertMessage());
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                }

                @Override
                public void parseError() {
                    progressDialog.dismiss();
                }

                @Override
                public void onBefore() {
                }

                @Override
                public void onAfter() {
                    progressDialog.dismiss();
                }
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_BUILD, json);
        } catch (Exception e) {

        }
    }

    /**
     * 获取单元
     *
     * @param buildingID
     */
    private void request_cell(int buildingID) { //获取单元
        try {


            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("communityUnit", new RequestCommunityUnit(buildingID));
            String json = new Gson().toJson(map);
            progressDialog = ProgressDialog.show(this, "", "加载中", true);//开锁中
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    if (TextUtil.isEmpty(s)) {
                        showShort("系统异常");
                        return;
                    }
                    ResponseQueryUnit queryUnit = new Gson().fromJson(s, ResponseQueryUnit.class);
                    if (queryUnit.getStatusCode() == 1) {
                        Database.listCommunityUnit = queryUnit.getListCommunityUnit();
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityUnitActivity.class);
                        startActivity(intent);
                    } else {
                        showShort(queryUnit.getAlertMessage());
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                }

                @Override
                public void parseError() {
                    progressDialog.dismiss();
                }

                @Override
                public void onBefore() {
                }

                @Override
                public void onAfter() {
                    progressDialog.dismiss();
                }
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_UNIT, json);
        } catch (Exception e) {

        }
    }

    /**
     * 查询房间号
     *
     * @param unitID
     */
    private void request_room(int unitID) { //获取房号
        try {


            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("communityRoom", new RequestCommunityRoom(unitID));
            String json = new Gson().toJson(map);
            progressDialog = ProgressDialog.show(this, "", "加载中", true);//开锁中
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    if (TextUtil.isEmpty(s)) {
                        showShort("系统异常");
                        return;
                    }
                    ResponseQueryRoom queryRoom = new Gson().fromJson(s, ResponseQueryRoom.class);
                    if (queryRoom.getStatusCode() == 1) {
                        Database.listCommunityRoom = queryRoom.getListCommunityRoom();
                        Intent intent = new Intent(AddRoomActivity.this, ListCommunityRoomActivity.class);
                        startActivity(intent);
                    } else {
                        showShort(queryRoom.getAlertMessage());
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                }

                @Override
                public void parseError() {
                    progressDialog.dismiss();
                }

                @Override
                public void onBefore() {
                }

                @Override
                public void onAfter() {
                    progressDialog.dismiss();
                }
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_ROOM, json);
        } catch (Exception e) {

        }
    }


    /**
     * 提交数据
     *
     * @param communityID
     * @param communityName
     * @param provinceID
     * @param provinceName
     * @param cityID
     * @param cItyName
     * @param buildingID
     * @param buildingName
     * @param roomID
     * @param roomName
     * @param unitID
     * @param unitName
     */
    private void request_submit(int communityID, String communityName, int provinceID, String provinceName, int cityID, String cItyName
            , int buildingID, String buildingName, int roomID, String roomName, int unitID, String unitName) { //提交
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping", new RequestApplyArea(provinceID, cityID, communityID, buildingID, unitID, roomID));
        String json = new Gson().toJson(map);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if (TextUtil.isEmpty(s)) {
                    showShort("系统异常");
                    return;
                }
                BaseResponseBean responseBean = new Gson().fromJson(s, BaseResponseBean.class);
                showShort(responseBean.getAlertMessage());
                if (responseBean.getStatusCode() == 1) {
                    Database.isAddarea = true;
//                    Router.build(RouterMapping.ROUTER_ACTIVITY_AUTHAREA)//认证
//                            .go(AddRoomActivity.this);
                    ActivityManager.removeAllActivity();
                    AddRoomActivity.this.finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onError() {
                progressDialog.dismiss();
            }

            @Override
            public void parseError() {
                progressDialog.dismiss();
            }

            @Override
            public void onBefore() {
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_ADD_COMMNUNITY, json);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.str_province = "";
        Database.str_city = "";
        Database.str_district = "";
        Database.id_province = 0;
        Database.id_city = 0;
        Database.id_district = 0;
        Database.district_id = 0;

        Database.communityName = "";
        Database.communityID = 0;

        Database.buildingName = "";
        Database.buildingID = 0;

        Database.unitName = "";
        Database.unitID = 0;

        Database.roomName = "";
        Database.roomID = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
