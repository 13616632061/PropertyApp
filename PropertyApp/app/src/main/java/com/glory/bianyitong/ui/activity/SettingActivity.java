package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.OpenDoorPopuWindow;
import com.glory.bianyitong.ui.fragment.IndexFragment;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.DataCleanManager;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

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
                exit();
                break;
            case R.id.tv_about: //关于
                Intent intent_about = new Intent(SettingActivity.this, HtmlActivity.class);
                intent_about.putExtra("from", "about");
                startActivity(intent_about);
                break;
        }
    }

    private void exit() { //退出
        try {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
            }
            @Override
            public void onAfter() {
            }
        }).getEntityData(this, HttpURL.HTTP_POST_LOGIN_EXIT, jsons);
        }catch (Exception e){

        }
    }

    private void logout() {
        SharedUtil.putBoolean("login", false);
        Database.USER_MAP = null;
        Database.my_community_List = null;
        Database.my_community = null;
        Database.accessToken=null;
        Database.login_return=null;
        SharedUtil.putString("jgPushID","");
        mCache.put(Constant.user,"");
        mCache.remove(Constant.user);
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
        SettingActivity.this.finish();
        JPushInterface.setAlias(this,"", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.d("123123","set Alias result is"+i);
            }
        });
        JPushInterface.setTags(getApplicationContext(), new HashSet<String>(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });
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
