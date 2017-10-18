package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.GetSMSCheckInfo;
import com.glory.bianyitong.bean.GetSMSCodeInfo;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.bean.UserInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.sdk.jpush.ExampleUtil;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.SharedUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by lucy on 2016/11/10.
 * 登录
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity {
    //没用的注释 方法去掉
    // e.printStackTrace(); 去掉
    //Log.i  改为  Log.d
    private static final int MSG_SET_ALIAS = 1001;
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("JPush", logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i("JPush", logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i("JPush", "No network");
                    }
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e("JPush", logs);
            }

//	            ExampleUtil.showToast(logs, getApplicationContext());
        }
    };
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d("JPush", "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    Log.i("JPush", "Unhandled msg - " + msg.what);
            }
        }
    };
    @BindView(R.id.login_phone)
    EditText login_phone;
    @BindView(R.id.login_code)
    EditText login_code;
    @BindView(R.id.text_GetCode)
    TextView text_GetCode;
    @BindView(R.id.btn_Login)
    Button btn_Login;
    @BindView(R.id.ck_login_contract)
    CheckBox ck_login_contract;
    @BindView(R.id.tv_contract)
    TextView tv_contract;
    private TimeCount time; //倒计时
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.ac_login;
    }

    @Override
    protected void init() {
        super.init();
        text_GetCode.setOnClickListener(this);
        btn_Login.setOnClickListener(this);
        tv_contract.setOnClickListener(this);
        time = new TimeCount(60000, 1000);
        //15899647853
        login_phone.setText(mCache.getAsString("phone")); //上一个人的电话号码
    }



    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.text_GetCode: //获取验证码
                String phone = login_phone.getText().toString().trim();
                boolean isphone = ActivityUtils.isMobileNO(phone);
                if (phone.equals("")) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.the_phone_number_can_not_be_empty));//手机号不能为空
                } else if (!isphone) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.please_enter_a_valid_phone_number));//请输入正确的手机号
                } else {
                    time.start();// 开始计时
                    createCode(phone);
//                    getCode(phone);
                }
                break;
            case R.id.btn_Login: //登录
                String phone2 = login_phone.getText().toString().trim();
                String code = login_code.getText().toString().trim();
                boolean isphone2 = ActivityUtils.isMobileNO(phone2);
                if (code.equals("")) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.verification_code_must_be_filled));//验证码不能为空
                } else if (phone2.equals("")) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.the_phone_number_can_not_be_empty));//手机号不能为空
                } else if (!isphone2) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.please_enter_a_valid_phone_number));//请输入正确的手机号
                } else if (!ck_login_contract.isChecked()) {
                    ToastUtils.showToast(LoginActivity.this, getString(R.string.please_check_the_agreed_user_agreement));//请勾选同意用户协议
                } else {//登录
                    Login(code, phone2);
                }
                break;
            case R.id.tv_contract: //用户协议
                Intent intent = new Intent(LoginActivity.this, HtmlActivity.class);
                intent.putExtra("from", "contract");
                startActivity(intent);
                break;
        }
    }

    //生成验证码
    private void createCode(final String phone) {
        Map<String,String> map=new HashMap<>();
        map.put("phoneNum",phone);
        String url = "/SMSCode/GetSMSCheck";
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                try {
                    JSONObject jo = new JSONObject(s);
                    GetSMSCheckInfo smsinfo = new Gson().fromJson(jo.toString(), GetSMSCheckInfo.class);
                    if(smsinfo!=null && smsinfo.getStatusCode() == 1){
                        getCode(phone);
                        login_code.setFocusable(true);
                        login_code.setFocusableInTouchMode(true);
                        login_code.requestFocus();
                    }else if(smsinfo!=null && smsinfo.getAlertMessage()!=null){
                        ToastUtils.showToast(LoginActivity.this, smsinfo.getAlertMessage());
                    }else {
                        ToastUtils.showToast(LoginActivity.this, getString(R.string.failed_to_generate_verification_code));//生成验证码失败
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(url, map);
    }

    //获取验证码
    private void getCode(String phone) {
        String url="/SMSCode/GetVituralSMSCheckCode";
        Map<String,String> map=new HashMap<>();
        map.put("phoneNumber",phone);
        map.put("accessToken","-1");//此接口不需要token验证
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jo = new JSONObject(s);
                    GetSMSCodeInfo getcodeinfo = new Gson().fromJson(jo.toString(), GetSMSCodeInfo.class);
                    if(getcodeinfo!=null && getcodeinfo.getMsg()!=null){
                        String code = getcodeinfo.getMsg();
                        login_code.setText(code);
                    }else if (getcodeinfo.getAlertMessage() != null) { //返回消息
                        ToastUtils.showToast(LoginActivity.this, getcodeinfo.getAlertMessage());
                    } else {
                        ToastUtils.showToast(LoginActivity.this, getString(R.string.failed_to_generate_verification_code));//生成验证码失败
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        }).getEntityDataForGet(url,map);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGoRequest.openLogin=true;
    }

    //登录
    private void Login(String code, final String phone) {//DeviceType 1 ios,3 android
        Database.USER_MAP = null;
        Database.my_community = null;
        Database.my_community_List = null;
        mCache.put("phone",phone);
        if (Database.registrationId == null) {
            Database.registrationId = "";
        }
        Map<String,Object> map= new  BaseRequestBean().getBaseRequest();

        map.put("phoneNumber",phone);
        map.put("smsCheckCode",code);
        map.put("deviceType",3);
        map.put("accessToken","-1");//此接口不需要token验证
//        Log.i("resultString", "registrationId---------" + Database.registrationId);
//        String query = "\"phoneNumber\":\""+phone+"\",\"smsCheckCode\":\""+code+"\"";
//        String json = RequestUtil.getJson(LoginActivity.this, query);
        String json =new Gson().toJson(map);
        String url = "/ApiLogin/AppLogin";

//        String json = "{\"phoneNumber\":\"" + phone + "\",\"smsCheckCode\":\"" + code + "\",\"DeviceType\": \"3\",\"jGPushID\":\"" + Database.registrationId + "\"}";
//        String url = HttpURL.HTTP_LOGIN_AREA + "/Login/AppLogin";
        Log.i("resultString", "json------------" + json);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                    LoginUserInfo loginInfo = new Gson().fromJson(s, LoginUserInfo.class);
                showShort(loginInfo.getAlertMessage());
                if(loginInfo.getStatusCode()==1){
                    Database.islogin = true;
                    Database.USER_MAP = loginInfo.getUser();
                    Database.accessToken=loginInfo.getAccessToken();
//                    Database.jgPushID=loginInfo.getUser().getJgPushID();
                    mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, Database.USER_MAP.getJgPushName()));
                    if (loginInfo.getUserCommnunity() != null) {
//                            Database.my_community_List = userInfo.getUserCommnunity();
                        DataUtils.getUesrCommunity(loginInfo);//社区列表
                        DataUtils.my_community(LoginActivity.this); //获取我的社区
                    }
                    Database.login_return=s;
                    mCache.put(Constant.user,s);
//                    SharePreToolsKits.putJsonDataString(LoginActivity.this, Constant.user, s); //缓存登录后信息
                    //登录成功
                    LoginActivity.this.finish();
                    SharedUtil.putString("jgPushID", loginInfo.getUser().getJgPushID());
                    SharedUtil.putBoolean("login", true);
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
                progressDialog = ProgressDialog.show(LoginActivity.this, "", getString(R.string.login), true);//登录
                progressDialog.setCanceledOnTouchOutside(true);
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,url, json);
    }



    private void my_community() { //我的社区
        String communityID_str = mCache.getAsString(Constant.communityID);
        int communityID = 0;
        if (communityID_str != null) {
            communityID = Double.valueOf(communityID_str).intValue();
        }
        if (communityID != 0) { //有缓存
            if (Database.my_community_List != null && Database.my_community_List.size() > 0) {
                for (int i = 0; i < Database.my_community_List.size(); i++) {
//                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).get("communityID") != null
//                            && Database.my_community_List.get(i).get("communityID").toString().equals(communityID)) {
//                        Database.my_community = Database.my_community_List.get(i);
//                    }
                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getCommunityID() != 0
                            && Database.my_community_List.get(i).getCommunityID() != communityID) {
                        Database.my_community = Database.my_community_List.get(i);
                    }
                }
                if (Database.my_community == null) { //没找到 缓存失效
//                    if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).get("communityID") != null) {
//                        Database.my_community = Database.my_community_List.get(0);
//                    }
                    if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).getCommunityID() != 0) {
                        Database.my_community = Database.my_community_List.get(0);
                    }
                }
            }
        } else { //无缓存 获取第一个作为默认小区
            if (Database.my_community_List != null && Database.my_community_List.size() > 0) {
//                if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).get("communityID") != null) {
//                    Database.my_community = Database.my_community_List.get(0);
//                }
                if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).getCommunityID() != 0) {
                    Database.my_community = Database.my_community_List.get(0);
                }
            }
        }
    }

    /**
     * userID : 94FEcAbyfgAm6BRLR4iClg==
     * userName : 里斯
     * userName_en : null
     * gender : 1
     * loginName : 狼与羊
     * password :
     * phoneNumber : 15024070082
     * birthDay : null
     * openID : null
     * joinDate : 2016-12-30T16:43:27
     * status : 1
     * email : null
     * qQ : null
     * customerPhoto : https://bianyitong.oss-cn-shenzhen.aliyuncs.com/android/userHeader/94FEcAbyfgAm6BRLR4iClg==_1484127916629_logo.png
     * chinaCity_ID : 0
     * chinaCity_Name : null
     * signature : 红红火火恍恍惚惚
     * jGPushID : 18071adc030183a30dd
     * jGPushName : V6NV8H86
     * roles : null
     */
//    private void getUserMap(LinkedTreeMap<String, Object> map) {
////        Database.USER_MAP = new UserInfo.UserBean();
//        Database.USER_MAP = new LoginUserInfo.UserBean();
//        if (map.get("userID") != null) {
//            Database.USER_MAP.setUserID(map.get("userID").toString());
//        }
//        if (map.get("userName") != null) {
//            Database.USER_MAP.setUserName(map.get("userName").toString());
//        }
//        if (map.get("gender") != null) {
//            Database.USER_MAP.setGender(Double.valueOf(map.get("gender").toString()).intValue());
//        }
//        if (map.get("loginName") != null) {
//            Database.USER_MAP.setLoginName(map.get("loginName").toString());
//        }
//        if (map.get("phoneNumber") != null) {
//            Database.USER_MAP.setPhoneNumber(map.get("phoneNumber").toString());
//        }
//        if (map.get("joinDate") != null) {
//            Database.USER_MAP.setJoinDate(map.get("joinDate").toString());
//        }
//        if (map.get("status") != null) {
//            Database.USER_MAP.setStatus(Double.valueOf(map.get("status").toString()).intValue());
//        }
//        if (map.get("customerPhoto") != null) {
//            Database.USER_MAP.setCustomerPhoto(map.get("customerPhoto").toString());
//        }
//        if (map.get("signature") != null) {
//            Database.USER_MAP.setSignature(map.get("signature").toString());
//        }
//        if (map.get("jGPushID") != null) {
//            Database.USER_MAP.setJGPushID(map.get("jGPushID").toString());
//        }
//        if (map.get("jGPushName") != null) {
//            Database.USER_MAP.setJGPushName(map.get("jGPushName").toString());
//        }
//        if (Database.USER_MAP.getJGPushName() != null) {
//            //调用JPush API设置Alias
//            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, Database.USER_MAP.getJGPushName()));
//        }
//    }

    /**
     * userCommunityID : 1024
     * userID : 25
     * userName : 里斯
     * communityID : 1
     * communityName : 西丽小区
     * userIDentityID : 1
     * userIDentityName : 业主
     * provinceID : 440000
     * provinceName : 广东省
     * cityID : 440300
     * cityName : 深圳市
     * buildingID : 1
     * buildingName : 1号楼
     * roomID : 2
     * roomName : 102
     * approvalStatus : 1
     * unitID : 1
     * unitName : A单元
     * approvalDate : 2017-01-03T16:14:00
     */
    private void getUesrCommunity(ArrayList<LinkedTreeMap<String, Object>> list) {
        Database.my_community_List = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                LinkedTreeMap<String, Object> map = list.get(i);
                UserInfo.UserCommnunityBean userCommnunityBean = new UserInfo.UserCommnunityBean();
                if (map.get("userCommunityID") != null) {
                    userCommnunityBean.setUserCommunityID(Double.valueOf(map.get("userCommunityID").toString()).intValue());
                }
                if (map.get("userID") != null) {
                    userCommnunityBean.setUserID(Double.valueOf(map.get("userID").toString()).intValue());
                }
                if (map.get("userName") != null) {
                    userCommnunityBean.setUserName(map.get("userName").toString());
                }
                if (map.get("communityID") != null) {
                    userCommnunityBean.setCommunityID(Double.valueOf(map.get("communityID").toString()).intValue());
                }
                if (map.get("communityName") != null) {
                    userCommnunityBean.setCommunityName(map.get("communityName").toString());
                }
                if (map.get("userIDentityID") != null) {
                    userCommnunityBean.setUserIDentityID(Double.valueOf(map.get("userIDentityID").toString()).intValue());
                }
                if (map.get("userIDentityName") != null) {
                    userCommnunityBean.setUserIDentityName(map.get("userIDentityName").toString());
                }
                if (map.get("provinceID") != null) {
                    userCommnunityBean.setProvinceID(Double.valueOf(map.get("provinceID").toString()).intValue());
                }
                if (map.get("provinceName") != null) {
                    userCommnunityBean.setProvinceName(map.get("provinceName").toString());
                }
                if (map.get("cityID") != null) {
                    userCommnunityBean.setCityID(Double.valueOf(map.get("cityID").toString()).intValue());
                }
                if (map.get("cityName") != null) {
                    userCommnunityBean.setCityName(map.get("cityName").toString());
                }
                if (map.get("buildingID") != null) {
                    userCommnunityBean.setBuildingID(Double.valueOf(map.get("buildingID").toString()).intValue());
                }
                if (map.get("buildingName") != null) {
                    userCommnunityBean.setBuildingName(map.get("buildingName").toString());
                }
                if (map.get("roomID") != null) {
                    userCommnunityBean.setRoomID(Double.valueOf(map.get("roomID").toString()).intValue());
                }
                if (map.get("roomName") != null) {
                    userCommnunityBean.setRoomName(map.get("roomName").toString());
                }
                if (map.get("approvalStatus") != null) {
                    userCommnunityBean.setApprovalStatus(Double.valueOf(map.get("approvalStatus").toString()).intValue());
                }
                if (map.get("unitID") != null) {
                    userCommnunityBean.setUnitID(Double.valueOf(map.get("unitID").toString()).intValue());
                }
                if (map.get("unitName") != null) {
                    userCommnunityBean.setUnitName(map.get("unitName").toString());
                }
                if (map.get("approvalDate") != null) {
                    userCommnunityBean.setApprovalDate(map.get("approvalDate").toString());
                }
//                Database.my_community_List.add(userCommnunityBean);
            }
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            text_GetCode.setText(getString(R.string.obtain));//获取
            text_GetCode.setClickable(true);
            text_GetCode.setTextColor(getResources().getColor(R.color.text_color_somber));
            text_GetCode.setBackgroundResource(R.drawable.login_bg_yellow);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            text_GetCode.setClickable(false);//防止重复点击
            text_GetCode.setText(millisUntilFinished / 1000 + "S");
            text_GetCode.setTextColor(getResources().getColor(R.color.small_text_color_gray));
            text_GetCode.setBackgroundResource(R.drawable.login_bg_time);
        }
    }
}
