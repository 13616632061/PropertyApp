package com.glory.bianyitong.ui.activity;

/**
 * Created by Smile on 2016/11/11.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.PhotoPopuWindow;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 个人资料
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_PERSION,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PersonalDataActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.rl_my_head_pic) //昵称
            RelativeLayout rl_my_head_pic;
    @BindView(R.id.rl_nickname) //昵称
            RelativeLayout rl_nickname;
    @BindView(R.id.text_nickname)
    TextView text_nickname;
    @BindView(R.id.rl_describe) //描述
            RelativeLayout rl_describe;
    @BindView(R.id.tv_personal_desc) //
            TextView tv_personal_desc;
    private CircleImageView my_head_pic; //头像
    private PhotoPopuWindow picPopuWindow; //选择框
    private String customerPhoto = "";

    @Override
    protected int getContentId() {
        return R.layout.activity_personal_data; //布局文件
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.personal_data), true, "");//个人资料
        my_head_pic = (CircleImageView) findViewById(R.id.my_head_pic);
        left_return_btn.setOnClickListener(this);
        rl_my_head_pic.setOnClickListener(this);
        rl_nickname.setOnClickListener(this);
        rl_describe.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Database.USER_MAP != null) {
            //头像
//            if (Database.USER_MAP.get("customerPhoto") != null) {
//                String pic = Database.USER_MAP.get("customerPhoto").toString();
//                if (!customerPhoto.equals(pic)) {
//                    ServiceDialog.setPicture(pic, my_head_pic, null);
////                    Glide.with(PersonalDataActivity.this).load(pic).error(R.drawable.wait).placeholder(R.drawable.wait).into(my_head_pic);
//                    customerPhoto = pic;
//                }
//            }
            if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
                String pic = Database.USER_MAP.getCustomerPhoto();
                if (!customerPhoto.equals(pic)) {
                    ServiceDialog.setPicture(pic, my_head_pic, null);
                    customerPhoto = pic;
                }
            }
            //用户名
//            if (Database.USER_MAP.get("loginName") != null) {
//                String name1 = text_nickname.getText().toString();
//                String name2 = Database.USER_MAP.get("loginName").toString();
//                if (!name1.equals(name2)) {
//                    text_nickname.setText(name2);
//                }
//            }
            if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
                String name1 = text_nickname.getText().toString();
                String name2 = Database.USER_MAP.getLoginName();
                if (!name1.equals(name2)) {
                    text_nickname.setText(name2);
                }
            }
            //签名
//            if (Database.USER_MAP.get("signature") != null) {
//                String signature = tv_personal_desc.getText().toString();
//                String signature2 = Database.USER_MAP.get("signature").toString();
//                if (!signature.equals(signature2)) {
//                    tv_personal_desc.setText(signature2);
//                }
//            }
            if (Database.USER_MAP != null && Database.USER_MAP.getSignature() != null) {
                String signature = tv_personal_desc.getText().toString();
//                String signature2 = Database.USER_MAP.getSignature();
                String signature2 = Database.USER_MAP.getSignature().toString();
                if (!signature.equals(signature2)) {
                    tv_personal_desc.setText(signature2);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                PersonalDataActivity.this.finish();
                break;
            case R.id.rl_my_head_pic:
                startLocation();
                break;
            case R.id.rl_nickname:
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_NAME)
                        .with("nick", text_nickname.getText())
                        .go(this);
//                Intent intent = new Intent(PersonalDataActivity.this, UpdateNameActivity.class);
//                intent.putExtra("nick", text_nickname.getText());
//                startActivity(intent);
                break;
            case R.id.rl_describe:

                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_DESC)
                        .with("desc", tv_personal_desc.getText())
                        .go(this);
//                Intent intent2 = new Intent(PersonalDataActivity.this, UpdateDescribeActivity.class);
//                intent2.putExtra("desc", tv_personal_desc.getText());
//                startActivity(intent2);
                break;
        }
    }

    // android 6.0d 权限管理变了，定位属于隐私权限，需要在运行时手动申请，参考如下代码
    private void startLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(PersonalDataActivity.this, Manifest.permission.CAMERA);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                return;
            } else {
                picPopuWindow = new PhotoPopuWindow(PersonalDataActivity.this, 1);
                // 显示窗口
                picPopuWindow.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
            }
        } else {
            picPopuWindow = new PhotoPopuWindow(PersonalDataActivity.this, 1);
            // 显示窗口
            picPopuWindow.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    picPopuWindow = new PhotoPopuWindow(PersonalDataActivity.this, 1);
                    // 显示窗口
                    picPopuWindow.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                } else {
                    picPopuWindow = new PhotoPopuWindow(PersonalDataActivity.this, 2);
                    // 显示窗口
                    picPopuWindow.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
