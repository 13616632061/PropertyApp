package com.glory.bianyitong.ui.activity;

/**
 * Created by Smile on 2016/11/11.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryUserInfo;
import com.glory.bianyitong.bean.entity.request.RequestUserBean;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUserById;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.PhotoPopuWindow;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 个人资料
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_PERSION, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
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
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_title_text_left)
    TextView ivTitleTextLeft;
    @BindView(R.id.title_ac_text)
    TextView titleAcText;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @BindView(R.id.my_head_pic)
    CircleImageView myHeadPic;
    @BindView(R.id.im_personal_data_photo_next)
    ImageView imPersonalDataPhotoNext;
    @BindView(R.id.tv_personal_name_left)
    TextView tvPersonalNameLeft;
    @BindView(R.id.im_personal_data_nickname_next)
    ImageView imPersonalDataNicknameNext;
    @BindView(R.id.tv_personal_desc_left)
    TextView tvPersonalDescLeft;
    @BindView(R.id.im_personal_data_desc_next)
    ImageView imPersonalDataDescNext;
    @BindView(R.id.tv_personal_rl_gender)
    TextView tvPersonalRlGender;
    @BindView(R.id.im_personal_data_rl_gender)
    ImageView imPersonalDataRlGender;
    @BindView(R.id.tv_personal_gender)
    TextView tvPersonalGender;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.lay_personal)
    LinearLayout layPersonal;
    @BindView(R.id.tv_personal_rl_name)
    TextView tvPersonalRlName;
    @BindView(R.id.im_personal_data_rl_name)
    ImageView imPersonalDataRlName;
    @BindView(R.id.tv_personal_name)
    TextView tvPersonalName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    private CircleImageView my_head_pic; //头像
    private PhotoPopuWindow picPopuWindow; //选择框
    private String customerPhoto = "";
    private PopupWindow popupWindowGender;//性别

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
        rlGender.setOnClickListener(this);
        rlName.setOnClickListener(this);
        initPopupWindowSort();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user_request();

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
                    ServiceDialog.setPicture(responseQueryUserById.getListUser().get(0).getCustomerPhoto(), my_head_pic, null);
                    text_nickname.setText(responseQueryUserById.getListUser().get(0).getLoginName());
                    tv_personal_desc.setText(responseQueryUserById.getListUser().get(0).getPhoneNumber());
                    if (responseQueryUserById.getListUser().get(0).getGender().equals("1")) {
                        tvPersonalGender.setText("男");
                    } else {
                        tvPersonalGender.setText("女");
                    }
                    tvPersonalName.setText(responseQueryUserById.getListUser().get(0).getUserName());
                } else {
                    showShort(responseQueryUserById.getAlertMessage());
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (Database.USER_MAP != null) {
//            //头像
////            if (Database.USER_MAP.get("customerPhoto") != null) {
////                String pic = Database.USER_MAP.get("customerPhoto").toString();
////                if (!customerPhoto.equals(pic)) {
////                    ServiceDialog.setPicture(pic, my_head_pic, null);
//////                    Glide.with(PersonalDataActivity.this).load(pic).error(R.drawable.wait).placeholder(R.drawable.wait).into(my_head_pic);
////                    customerPhoto = pic;
////                }
////            }
//            if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
//                String pic = Database.USER_MAP.getCustomerPhoto();
//                if (!customerPhoto.equals(pic)) {
//                    ServiceDialog.setPicture(pic, my_head_pic, null);
//                    customerPhoto = pic;
//                }
//            }
//            //用户名
////            if (Database.USER_MAP.get("loginName") != null) {
////                String name1 = text_nickname.getText().toString();
////                String name2 = Database.USER_MAP.get("loginName").toString();
////                if (!name1.equals(name2)) {
////                    text_nickname.setText(name2);
////                }
////            }
//            if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
//                String name1 = text_nickname.getText().toString();
//                String name2 = Database.USER_MAP.getLoginName();
//                if (!name1.equals(name2)) {
//                    text_nickname.setText(name2);
//                }
//            }
//            //签名
////            if (Database.USER_MAP.get("signature") != null) {
////                String signature = tv_personal_desc.getText().toString();
////                String signature2 = Database.USER_MAP.get("signature").toString();
////                if (!signature.equals(signature2)) {
////                    tv_personal_desc.setText(signature2);
////                }
////            }
//            if (Database.USER_MAP != null && Database.USER_MAP.getSignature() != null) {
//                String signature = tv_personal_desc.getText().toString();
////                String signature2 = Database.USER_MAP.getSignature();
//                String signature2 = Database.USER_MAP.getSignature().toString();
//                if (!signature.equals(signature2)) {
//                    tv_personal_desc.setText(signature2);
//                }
//            }
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                PersonalDataActivity.this.finish();
                break;
            case R.id.rl_my_head_pic://头像
                startLocation();
                break;
            case R.id.rl_nickname://昵称
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_NAME)
                        .with("nick", text_nickname.getText())
                        .go(this);
//                Intent intent = new Intent(PersonalDataActivity.this, UpdateNameActivity.class);
//                intent.putExtra("nick", text_nickname.getText());
//                startActivity(intent);
                break;
            case R.id.rl_gender://性别
                initPopupWindowSort();
//                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_DESC)
//                        .with("desc", tv_personal_desc.getText())
//                        .go(this);
//                Intent intent2 = new Intent(PersonalDataActivity.this, UpdateDescribeActivity.class);
//                intent2.putExtra("desc", tv_personal_desc.getText());
//                startActivity(intent2);
                break;
            case R.id.rl_name://真实姓名
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_DESC)
                        .with("desc", tvPersonalName.getText())
                        .with("loginname",text_nickname.getText())
                        .go(this);
//                Intent intent2 = new Intent(PersonalDataActivity.this, UpdateDescribeActivity.class);
//                intent2.putExtra("desc", tv_personal_desc.getText());
//                startActivity(intent2);
                break;
        }
    }

    //综合排序弹出窗口
    private void initPopupWindowSort() {
        if (popupWindowGender == null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            final View pView = inflater.inflate(R.layout.dialog_photo, null);
            popupWindowGender = new PopupWindow(pView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            popupWindowGender.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
//                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
            popupWindowGender.setAnimationStyle(R.style.AnimationWindow);
            popupWindowGender.setFocusable(true);
            popupWindowGender.setOutsideTouchable(true);
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0x80000000);
            // 设置SelectPicPopupWindow弹出窗体的背景
            popupWindowGender.setBackgroundDrawable(dw);
            TextView man= (TextView) pView.findViewById(R.id.b1);
            TextView woman= (TextView) pView.findViewById(R.id.b2);
            TextView quxiao= (TextView) pView.findViewById(R.id.b3);
            man.setText("男");
            woman.setText("女");
            man.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveGender("1");
                }
            });
            woman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveGender("2");
                }
            });
            quxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindowGender.dismiss();
                }
            });


        } else {
            if (!popupWindowGender.isShowing()) {
//                popupWindowSort.showAtLocation(rlZonghe, Gravity.LEFT|Gravity.RIGHT,0,0);
                popupWindowGender.showAtLocation(PersonalDataActivity.this.findViewById(R.id.lay_personal),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        }
    }


    //男女
    private void saveGender(final String gender) {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("user",new RequestUserBean(Database.USER_MAP.getLoginName(),gender));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if(TextUtil.isEmpty(s)){
                    showShort("系统异常");
                    return;
                }
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    ToastUtils.showToast(PersonalDataActivity.this,bean.getAlertMessage());//修改成功
                    popupWindowGender.dismiss();
                    user_request();
                }else {
                    ToastUtils.showToast(PersonalDataActivity.this,bean.getAlertMessage());//修改失败
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
