package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestUserBean;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.SharePreToolsKits;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/21.
 * 个人描述
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_DESC,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class UpdateDescribeActivity extends BaseActivity {

    @BindView(R.id.et_describe)
    EditText et_describe;
    //标题
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_left)
    TextView iv_title_text_left;

    @InjectParam(key = "desc")
    String describe;
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_personal_describe;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle(getString(R.string.personal_description), false, getString(R.string.determine));//个人描述  确定
        left_return_btn.setVisibility(View.GONE);

        et_describe.setText(describe);

        iv_title_text_left.setVisibility(View.VISIBLE);
        iv_title_text_left.setText(getString(R.string.cancel));//取消
        iv_title_text_left.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                UpdateDescribeActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//保存
            @Override
            public void onClick(View v) {
                String desc = et_describe.getText().toString();
                if (desc.equals("")) {
                    ToastUtils.showToast(UpdateDescribeActivity.this, getString(R.string.description_can_not_be_empty));//描述不能为空
                } else {
                    save(desc);
                }
            }
        });

    }

    //保存
    private void save(final String desc) {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("user",new RequestUserBean(Database.USER_MAP.getLoginName(),Database.USER_MAP.getCustomerPhoto(),desc));
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
                    Database.USER_MAP.setSignature(desc);
                    showShort( getString(R.string.successfully_modified));
                    DataUtils.saveSharePreToolsKits(UpdateDescribeActivity.this);
                    UpdateDescribeActivity.this.finish();
                }else {
                    ToastUtils.showToast(UpdateDescribeActivity.this,bean.getAlertMessage());//修改失败
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
        }).getEntityData(HttpURL.HTTP_POST_MY_EDITUSERINFO,json);

    }

}
