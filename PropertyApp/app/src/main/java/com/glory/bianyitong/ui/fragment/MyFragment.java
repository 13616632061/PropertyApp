package com.glory.bianyitong.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.sdk.share.ShareUtil;
import com.glory.bianyitong.ui.activity.AuthAreaActivity;
import com.glory.bianyitong.ui.activity.AwardManagerActivity;
import com.glory.bianyitong.ui.activity.FeedbackActivity;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.activity.MyReleaseActivity;
import com.glory.bianyitong.ui.activity.PersonalDataActivity;
import com.glory.bianyitong.ui.activity.SettingActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.ui.dialog.ShareSdkDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/10.
 * 我的
 */
public class MyFragment extends BaseFragment {
    Context context;
    @BindView(R.id.tv_auth_area) //认证小区
            TextView tv_auth_area;
    @BindView(R.id.tv_award_manager) //授权管理
            TextView tv_award_manager;
    @BindView(R.id.tv_feedback) //意见反馈
            TextView tvFeedback;
    @BindView(R.id.fg_tv_my_news) //我的发布
            TextView fg_tv_my_news;
    @BindView(R.id.tv_share_app) //推荐给其他朋友
            TextView tv_share_app;
    @BindView(R.id.tv_setting) //设置
            TextView tvSetting;
    @BindView(R.id.text_user_name)
    TextView text_user_name;
    @BindView(R.id.ll_describe)
    LinearLayout ll_describe;
    @BindView(R.id.tv_user_signature) //签名
            TextView tv_user_signature;
    private View view_my;
    private CircleImageView headPortraitCiv; //个人信息
    private String customerPhoto = "";

    private String tittle;
    private String subTittle;
    private String share_url = "";
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: //朋友圈
                    ShareUtil.showShare(context, msg.obj.toString(), true, tittle, subTittle, share_url, Constant.logo_path);
                    break;
                case 1://微信好友
                    Log.i("resultString", "tittle---------" + tittle);
                    Log.i("resultString", "subTittle---------" + subTittle);
                    Log.i("resultString", "share_url---------" + share_url);
                    Log.i("resultString", "Constant.logo_path---------" + Constant.logo_path);
                    ShareUtil.showShare(context, msg.obj.toString(), true, tittle, subTittle, share_url, Constant.logo_path);
                    break;
            }
        }
    };
    private ShareSdkDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_my = inflater.inflate(R.layout.fg_my, container, false);
        context = getActivity();
        ButterKnife.bind(this, view_my);
        return view_my;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headPortraitCiv = (CircleImageView) view.findViewById(R.id.cim_my_head_portrait);
        headPortraitCiv.setOnClickListener(this);
        text_user_name.setOnClickListener(this);
        ll_describe.setOnClickListener(this);

//        rl_myAddress.setOnClickListener(this);
        tv_award_manager.setOnClickListener(this);
        tv_auth_area.setOnClickListener(this);
        fg_tv_my_news.setOnClickListener(this);
        tv_share_app.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        getShareInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cim_my_head_portrait: //个人信息
                if (Database.USER_MAP != null) {//登录
                    Intent intent2 = new Intent(context, PersonalDataActivity.class);
                    context.startActivity(intent2);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.text_user_name://个人信息
                if (Database.USER_MAP != null) {//登录
                    Intent intent3 = new Intent(context, PersonalDataActivity.class);
                    context.startActivity(intent3);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.ll_describe://个人信息
                if (Database.USER_MAP != null) {//登录
                    Intent intent4 = new Intent(context, PersonalDataActivity.class);
                    context.startActivity(intent4);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.tv_auth_area: //认证小区
                if (Database.USER_MAP != null) {//登录
                    Intent intent7 = new Intent(context, AuthAreaActivity.class);
                    intent7.putExtra("from", "");
                    context.startActivity(intent7);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.tv_award_manager: //授权管理
                if (Database.USER_MAP != null) {//登录
                    if (Database.my_community != null) { //判断有没有小区
                        Intent intent6 = new Intent(context, AwardManagerActivity.class);
                        context.startActivity(intent6);
                    } else {
                        ToastUtils.showToast(context, getString(R.string.you_have_no_district_please_first_certification_district));//您还没有小区,请先认证小区
                    }
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.fg_tv_my_news: //我的发布 /登录
                if (Database.USER_MAP != null) {
                    Intent intent = new Intent(context, MyReleaseActivity.class);
                    context.startActivity(intent);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.tv_share_app: //推荐给其他朋友
                dialog = new ShareSdkDialog(context, mhandler);
                // 显示窗口
                dialog.showAtLocation(view_my.findViewById(R.id.lay_fg_my),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_feedback: //意见反馈 /登录
                if (Database.USER_MAP != null) {
                    Intent intent8 = new Intent(context, FeedbackActivity.class);
                    context.startActivity(intent8);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
                    login();
                }
                break;
            case R.id.tv_setting: //设置
                Intent intent9 = new Intent(context, SettingActivity.class);
                context.startActivity(intent9);
                break;
        }
    }

    private void login() {
        Intent intent_login = new Intent();
        intent_login.setClass(context, LoginActivity.class);
        startActivity(intent_login);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Database.USER_MAP != null) { //登录
            //用户名
            if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
                String name1 = text_user_name.getText().toString();
                String name2 = Database.USER_MAP.getLoginName();
                if (!name1.equals(name2)) {
                    text_user_name.setText(name2);
                }
            } else {
                text_user_name.setText("");
            }
            //头像
            if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
                String pic = Database.USER_MAP.getCustomerPhoto();
                if (!customerPhoto.equals(pic)) {
                    ServiceDialog.setPicture(pic, headPortraitCiv, null);
                    customerPhoto = pic;
                }
            } else {
                headPortraitCiv.setImageResource(R.drawable.head);
            }
            //签名
            if (Database.USER_MAP != null && Database.USER_MAP.getSignature() != null) {
                String signature = tv_user_signature.getText().toString();
//                String signature2 = Database.USER_MAP.getSignature();
                String signature2 = Database.USER_MAP.getSignature().toString();
                if (!signature.equals(signature2)) {
                    tv_user_signature.setText(signature2);
                }
            } else {
                tv_user_signature.setText("");
            }
        } else { //未登录
            text_user_name.setText(getResources().getString(R.string.not_logged_in));//未登录
            tv_user_signature.setText("");
            headPortraitCiv.setImageResource(R.drawable.head);
        }
    }

    private void getShareInfo() {
        String userID = RequestUtil.getuserid();
        String json = "{\"type\":1,\"controllerName\": \"\",\"actionName\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"" +
                ",\"userID\": \"" + userID + "\"}";
        Log.i("resultString", "json-------" + json);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                if (hashMap2 != null && hashMap2.get("shareURL") != null) {
                    share_url = hashMap2.get("shareURL").toString();
                }
                if (hashMap2 != null && hashMap2.get("shareTitle") != null) {
                    tittle = hashMap2.get("shareTitle").toString();
                }
                if (hashMap2 != null && hashMap2.get("shareContent") != null) {
                    subTittle = hashMap2.get("shareContent").toString();
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
        }).getEntityData("/Setting/GetShare", json);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
