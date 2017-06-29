package com.glory.bianyitong.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.response.ResponseQuerySteWard;
import com.glory.bianyitong.bean.listHousekeeperInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.dialog.CallPhoneDialog;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/11.
 * 物业管家
 */
public class StewardActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.steward_name) //管家名称
            TextView steward_name;
    @BindView(R.id.steward_phone) //电话
            TextView steward_phone;
    @BindView(R.id.steward_cert) //认证
            TextView steward_cert;
    @BindView(R.id.steward_call) //一键呼叫
            TextView steward_call;
    @BindView(R.id.steward_pic) //管家头像
            ImageView steward_pic;
    ArrayList<LinkedTreeMap<String, Object>> housekeeper_list;//物业管家
    ResponseQuerySteWard.ListHousekeeperBean housekeeper;
    private String phone_str = "";//要拨打的电话

    @Override
    protected int getContentId() {
        return R.layout.ac_steward;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getResources().getString(R.string.property_steward), true, "");//物业管家
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                StewardActivity.this.finish();
            }
        });
        steward_call.setOnClickListener(new View.OnClickListener() { //电话图标
            @Override
            public void onClick(View view) {
                if (phone_str != null && !phone_str.equals("") && phone_str.length() > 0) {
                    startLocation();
                }
            }
        });
        steward_phone.setOnClickListener(new View.OnClickListener() { //电话
            @Override
            public void onClick(View view) {
                if (phone_str != null && !phone_str.equals("") && phone_str.length() > 0) {
                    startLocation();
                }
            }
        });
        if (Database.my_community != null && Database.my_community_List != null) {
        } else {
            Intent intent = new Intent(StewardActivity.this, AddAreaCityActivity.class); //
            intent.putExtra("from", "index");
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        request();
    }

    private void request() {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("housekeeper",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

                if(TextUtil.isEmpty(s)){
                    showShort("系统异常");
                    return;
                }
                ResponseQuerySteWard querySteWard=new Gson().fromJson(s,ResponseQuerySteWard.class);
                if(querySteWard.getStatusCode()==1){
                    if (querySteWard != null && querySteWard.getListHousekeeper() != null) {
                        List<ResponseQuerySteWard.ListHousekeeperBean> hlist = querySteWard.getListHousekeeper();
                        if (hlist.get(0) != null) {
                            housekeeper = hlist.get(0);
                            if (housekeeper != null && housekeeper.getHouseKeeperName() != null) {
                                steward_name.setText(housekeeper.getHouseKeeperName());
                            }
                            if (housekeeper != null && housekeeper.getWorkPhoneNum() != null) {
                                steward_phone.setText(housekeeper.getWorkPhoneNum());
                                phone_str = housekeeper.getWorkPhoneNum();
                            }
                            steward_cert.setText(getResources().getString(R.string.certification) + ":" + housekeeper.getCommunityName() + housekeeper.getUnitName() + housekeeper.getBuildingName());
                            if (housekeeper != null && housekeeper.getHouseKepperPhoto() != null) {
                                ServiceDialog.setPicture(housekeeper.getHouseKepperPhoto(), steward_pic, null);//管家 头像
                            }
                        }
                    }
                }else {
                    showShort(querySteWard.getAlertMessage());
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
        }).getEntityData(HttpURL.HTTP_POST_OWNER_MANAGER,json);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }


    // android 6.0d 权限管理变了，需要在运行时手动申请，参考如下代码
    private void startLocation() {
        Log.i("resultString", "----------msg");
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(StewardActivity.this, Manifest.permission.CALL_PHONE);//拨打电话权限
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            } else {
                Log.i("resultString", "----------msg");
                CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                calldialog.show();
            }
        } else {
            Log.i("resultString", "----------msg");
            CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
            calldialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                    calldialog.show();
                } else {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(StewardActivity.this, phone_str);
                    calldialog.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
