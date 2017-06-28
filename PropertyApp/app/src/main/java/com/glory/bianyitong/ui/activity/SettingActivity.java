package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.DataCleanManager;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.SharePreToolsKits;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 设置
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_SETTING)
public class SettingActivity extends BaseActivity {
    @BindView(R.id.btn_logout)
    Button btn_logout;

    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.check_remind)
    CheckBox check_remind;
    @BindView(R.id.tv_remind)
    TextView tv_remind;

    @BindView(R.id.tv_version)
    TextView tv_version;

    @BindView(R.id.lay_clean_cache)
    RelativeLayout lay_clean_cache;
    @BindView(R.id.tv_cacheSize)
    TextView tv_cacheSize;

    @BindView(R.id.tv_about)
    TextView tv_about;
    private ProgressDialog progressDialog;
    private Handler mhandler;

    @Override
    protected int getContentId() {
        return R.layout.ac_setting;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.setting), true, "");
        left_return_btn.setOnClickListener(this);
        lay_clean_cache.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

        tv_version.setText(getString(R.string.current_version) + "V" + ActivityUtils.getVersionName());
        tv_cacheSize.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this)); //计算缓存大小
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        progressDialog = ProgressDialog.show(SettingActivity.this, "", getString(R.string.cleaning_up), true);//正在清理
                        progressDialog.setCanceledOnTouchOutside(true);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                    tv_cacheSize.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                                }
                                ToastUtils.showToast(SettingActivity.this, getString(R.string.clean_up));//清理完成
                            }
                        }, 2000);
                        break;
                }
            }
        };
        check_remind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_remind.setText(getString(R.string.turned_on)); // 开启 推送接受 已开启
                    JPushInterface.resumePush(getApplicationContext());
                } else {
                    tv_remind.setText(getString(R.string.closed));//已关闭
                    JPushInterface.stopPush(getApplicationContext());
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Database.USER_MAP != null) {
            btn_logout.setVisibility(View.VISIBLE);
        } else {
            btn_logout.setVisibility(View.GONE);
        }
    }

    private void cleanCache() {//清理缓存
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage(getString(R.string.whether_to_clean_up_the_cache));//是否清理缓存
//        builder.setTitle("提示");
        builder.setPositiveButton(getString(R.string.cleaning), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//清理
                mhandler.sendEmptyMessage(1);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {//取消
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn://返回
                SettingActivity.this.finish();
                break;
            case R.id.lay_clean_cache://清理缓存
                String txt = tv_cacheSize.getText().toString();
                if (!txt.equals("0KB")) {
                    cleanCache();
                } else {
                    ToastUtils.showToast(SettingActivity.this, getString(R.string.no_cache_can_be_cleaned_up) + "~");//没有缓存可清理
                }
                break;
            case R.id.btn_logout://退出登录
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage(getString(R.string.whether_to_log_out)); //是否退出登录
                builder.setTitle(getString(R.string.prompt));//提示
                builder.setPositiveButton(getString(R.string.drop_out), new DialogInterface.OnClickListener() {//退出
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {//取消
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_about: //关于
                Intent intent_about = new Intent(SettingActivity.this, HtmlActivity.class);
                intent_about.putExtra("from", "about");
                startActivity(intent_about);
                break;
        }
    }

    private void logout() {
//        if (Database.USER_MAP != null && Database.USER_MAP.get("phoneNumber") != null) {
//            appExit(Database.USER_MAP.get("phoneNumber").toString());
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getPhoneNumber() != null) {
            appExit(Database.USER_MAP.getPhoneNumber());
        }
//        Database.USER_MAP.clear();
        Database.USER_MAP = null;
        Database.my_community_List = null;
        Database.my_community = null;
        Database.accessToken=null;
//                SharePreToolsKits.putJsonDataString(SettingActivity.this, Constant.user, "");//缓存用户登录后信息
//                SharePreToolsKits.putString(SettingActivity.this, Constant.bulletinID, ""); //缓存已读社区消息
//                SharePreToolsKits.putString(SettingActivity.this, Constant.messageID, ""); //缓存已读系统通知消息
//                SharePreToolsKits.putString(SettingActivity.this, Constant.communityID, ""); //缓存所选的小区id
//                SharePreToolsKits.putJsonDataString(SettingActivity.this, Constant.SEARCH, "");//缓存最近搜索数据
        SharePreToolsKits.clearJsonDataShare(SettingActivity.this);
        SharePreToolsKits.clearShare(SettingActivity.this);
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
        SettingActivity.this.finish();
    }

    //清除别名
    private void appExit(String phone) {

//        String url = "/Login/AppExit";
//        Map<String,String> map=new HashMap<>();
//        map.put("phoneNumber",phone);
//        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
//            @Override
//            public void onSuccess(String s) {
//                Log.i("resultString", "------------");
//                Log.i("resultString", s);
//                Log.i("resultString", "------------");
//            }
//            @Override
//            public void onError() {}
//            @Override
//            public void parseError() {}
//            @Override
//            public void onBefore() {}
//            @Override
//            public void onAfter() {}
//        }).getEntityData(url, map);

    }
}
