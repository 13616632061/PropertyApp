package com.glory.bianyitong.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.response.UserLockMapping;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.timeselector.TimeSelector;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/22.
 * 添加授权
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AddAWARD,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddAwardActivity extends BaseActivity {
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.rl_linkman) //联系人
            RelativeLayout rl_linkman;
    @BindView(R.id.et_linkman_phone)
    EditText et_linkman_phone;
    @BindView(R.id.et_linkman_name)
    EditText et_linkman_name;

    @BindView(R.id.radioGroup_identity) //单选组
            RadioGroup radioGroup_identity;
    @BindView(R.id.radioButton_folk)  //家人
            RadioButton radioButton_folk;
    @BindView(R.id.radioButton_tentant) //租客
            RadioButton radioButton_tentant;
    @BindView(R.id.radioButton_temporary_tentant) //临时租客
            RadioButton radioButton_temporary_tentant;
    int user_identity = 1; //默认家人

    @BindView(R.id.check_limit)
    CheckBox check_limit; //限制

    @BindView(R.id.ll_limit) //时间布局
            LinearLayout ll_limit;
    @BindView(R.id.ll_time_start)
    LinearLayout ll_time_start;
    @BindView(R.id.ll_time_end)
    LinearLayout ll_time_end;
    @BindView(R.id.tv_time_start) //限制时间  开始
            TextView tv_time_start;
    @BindView(R.id.tv_time_end)  //结束
            TextView tv_time_end;
    private TimeSelector timeSelector_start;
    private TimeSelector timeSelector_end;
    private int time_Limit = 0; //默认不限制
    private String user_type = "U";
    private String nowdate;

    private ProgressDialog progressDialog = null;
    private String str_time_start = "";

    @InjectParam(key = "from")
     String from ;
    @InjectParam(key = "authorizationUserID")
    int authorizationUserID = 0;

    @Override
    protected int getContentId() {
        return R.layout.ac_add_award;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        if (from.equals("add")) {
            inintTitle(getResources().getString(R.string.add_authorization), false, getResources().getString(R.string.carry_out));//添加授权   完成
        } else if (from.equals("edit")) {
            inintTitle(getResources().getString(R.string.modify_authorization), false, getResources().getString(R.string.carry_out));//修改授权   完成
        }

        left_return_btn.setOnClickListener(this);
        iv_title_text_right.setOnClickListener(this);
        rl_linkman.setOnClickListener(this);

        ll_time_start.setOnClickListener(this);
        ll_time_end.setOnClickListener(this);

        check_limit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ll_limit.setVisibility(View.VISIBLE); // 显示  限制时间选择
                    time_Limit = 1;
                    user_type = "U";
                } else {
                    ll_limit.setVisibility(View.GONE);
                    time_Limit = 0;
                    user_type = "T";
                }
            }
        });
        radioGroup_identity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButton_folk.getId()) { //家人
                    checkedIdentity(1);
                } else if (checkedId == radioButton_tentant.getId()) {//租客
                    checkedIdentity(2);
                } else if (checkedId == radioButton_temporary_tentant.getId()) {//临时租客
                    checkedIdentity(3);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                AddAwardActivity.this.finish();
                break;
            case R.id.ll_time_start: //开始时间
                if (timeSelector_start != null) {
                    timeSelector_start.show();
                } else {
                    if (nowdate == null) {
                        nowdate = DateUtil.formatDefaultDate(DateUtil.getCurrentDate());//获取当天时间
                    }
                    timeSelector_start = new TimeSelector(this, new TimeSelector.ResultHandler() {
                        @Override
                        public void handle(String time) {
                            tv_time_start.setText(time);
                            str_time_start = time;
                        }
                    }, nowdate, "2099-12-31");
                    timeSelector_start.show();
                }
                break;
            case R.id.ll_time_end: //结束时间
                if (str_time_start != null && str_time_start.length() > 0) {
                    if (timeSelector_end != null) {
                        timeSelector_end.show();
                    } else {
                        timeSelector_end = new TimeSelector(this, new TimeSelector.ResultHandler() {
                            @Override
                            public void handle(String time) {
//                                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                                tv_time_end.setText(time);
                            }
                        }, str_time_start, "2099-12-31");
                        timeSelector_end.show();
                    }
                } else {
                    ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.please_select_the_start_time_first));//请先选择开始时间
                }
                break;
            case R.id.rl_linkman: //联系人
                starttxl();

                break;
            case R.id.iv_title_text_right: //完成
                String name = et_linkman_name.getText().toString().trim();
                String phone = et_linkman_phone.getText().toString().trim();
//                boolean isphone = ActivityUtils.isMobileNO(phone);
                String startDate = tv_time_start.getText().toString().trim();
                String endDate = tv_time_end.getText().toString().trim();
                if (name.equals("")) {
                    ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.do_not_leave_your_name_blank));//姓名不能为空
                } else if (phone.equals("")) {
                    ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.the_phone_number_can_not_be_empty));//手机号不能为空
                }
//                else if (!isphone) {
//                    ToastUtils.showToast(AddAwardActivity.this, "请输入正确的手机号");
//                }
                else if (time_Limit == 1 && (startDate.equals("") || startDate.length() < 1)) {
                    ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.please_select_the_start_time_first));//请先选择开始时间
                } else if (time_Limit == 1 && (endDate.equals("") || endDate.length() < 1)) {
                    ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.please_select_the_end_time));//请选择结束时间
                    time_Limit = 0;

                } else {
                    request_add(user_type, name, phone, user_identity, time_Limit, startDate, endDate);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!Database.contact_phone_name.equals("")) {
            String[] array = Database.contact_phone_name.split(",");
            et_linkman_phone.setText(array[0]);
            et_linkman_phone.setSelection(array[0].length());
            et_linkman_name.setText(array[1]);
            et_linkman_name.setSelection(array[1].length());
            Database.contact_phone_name = "";
        }
        if (from.equals("edit") && Database.awardpeople != null) {//姓名
                et_linkman_name.setText(Database.awardpeople.getAuthorizationUserName());
                et_linkman_name.setFocusable(false);
                et_linkman_name.setEnabled(false);
                //电话
                et_linkman_phone.setText(Database.awardpeople.getAuthorizationUserPhone());
                et_linkman_phone.setFocusable(false);
                et_linkman_phone.setEnabled(false);
                //用户身份
                int userIdentity = Database.awardpeople.getUserIdentity();
                if (userIdentity == 1) { //家人
                    checkedIdentity(1);
                } else if (userIdentity == 2) {//租客
                    checkedIdentity(2);
                } else if (userIdentity == 3) {//临时租客
                    checkedIdentity(3);
                }

            if (Database.awardpeople.isTimeLimit()) {//时间限制
                boolean timeLimit =Database.awardpeople.isTimeLimit();
                if (timeLimit) { //限制
                    ll_limit.setVisibility(View.VISIBLE); // 显示  限制时间选择
                    time_Limit = 1;
                    check_limit.setChecked(true);
                    if (Database.awardpeople.getStartDate()!= null && Database.awardpeople.getEndDate()!= null) {
                        String startDate = Database.awardpeople.getStartDate().toString();
                        String endDate = Database.awardpeople.getEndDate().toString();
                        tv_time_start.setText(startDate.substring(0, 10));
                        tv_time_end.setText(endDate.substring(0, 10));
                    }
                } else {
                    check_limit.setChecked(false);
                    ll_limit.setVisibility(View.GONE);
                    time_Limit = 0;
                }
            }
            if (Database.awardpeople.getAuthorizationType() != null) { //授权类型
                user_type = Database.awardpeople.getAuthorizationType().toString();
            }

            }

        }


    private void checkedIdentity(int identity) {
        Drawable drawable = getResources().getDrawable(R.drawable.log_select_radio);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 这一步必须要做,否则不会显示.
        radioButton_folk.setCompoundDrawables(null, null, null, null);
        radioButton_tentant.setCompoundDrawables(null, null, null, null);
        radioButton_temporary_tentant.setCompoundDrawables(null, null, null, null);
        if (identity == 1) {//家人
            radioButton_folk.setCompoundDrawables(null, null, drawable, null);
        } else if (identity == 2) { //租客
            radioButton_tentant.setCompoundDrawables(null, null, drawable, null);
        } else if (identity == 3) { //临时租客
            radioButton_temporary_tentant.setCompoundDrawables(null, null, drawable, null);
        }
        user_identity = identity; //身份类型
    }

    //type U 用户授权,T 临时授权 userIdentity 1 家人 2租客 3临时客人 timeLimit 1 限制 0 不限制
    //添加授权人
    private void request_add(String type, String name, String phone, int userIdentity, int timeLimit, String startDate, String endDate) {
        String url = "";
        String json = "";
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        if (from.equals("add")) {
            url = HttpURL.HTTP_POST_LOCKMAPPING_ADD;//添加授权
            map.put("userLockMapping",new UserLockMapping(userIdentity,timeLimit,phone,name));
            map.put("communityID","1");
        } else if (from.equals("edit")) {
            url =HttpURL.HTTP_POST_LOCKMAPPING_EDIT;//修改授权
            map.put("userLockMapping",new UserLockMapping(authorizationUserID+"",userIdentity,timeLimit,startDate,endDate));
        }

        json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    if (from.equals("add")){
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.added_successfully));//添加成功
                    }else {
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.successfully_modified));//修改成功
                    }
                }else if(bean.getStatusCode()==-126){
                    if (from.equals("add")){
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.this_user_already_has_this_permission));//该用户已有此权限
                    }else {
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.fail_to_edit));//修改失败
                    }
                }else {
                    if (from.equals("add")){
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.add_failed));//添加失败
                    }else {
                        ToastUtils.showToast(AddAwardActivity.this, getResources().getString(R.string.fail_to_edit));//修改失败
                    }
                }
            }

            @Override
            public void onError() { }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AddAwardActivity.this, "", getResources().getString(R.string.load), true);
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url, json);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void starttxl() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(AddAwardActivity.this, Manifest.permission.READ_CONTACTS);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                return;
            } else {
                //有权限
                Intent intent = new Intent(AddAwardActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(AddAwardActivity.this, ContactListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(AddAwardActivity.this, ContactListActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AddAwardActivity.this, ContactListActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
