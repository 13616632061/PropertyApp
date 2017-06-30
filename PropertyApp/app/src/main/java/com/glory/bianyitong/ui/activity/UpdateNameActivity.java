package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestUserBean;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.ContainsEmojiEditText;
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
 * 修改昵称
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_UPDATE_NAME,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class UpdateNameActivity extends BaseActivity {
    @BindView(R.id.et_nickname)
    ContainsEmojiEditText et_nickname;

    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @InjectParam(key ="nick" )
    String nick ;
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_update_nickname;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle(getString(R.string.change_username), false, getString(R.string.save));//修改昵称  保存
        et_nickname.setText(nick);

        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                UpdateNameActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//保存
            @Override
            public void onClick(View v) {
                String name = et_nickname.getText().toString();
                if (name.equals("")) {
                    ToastUtils.showToast(UpdateNameActivity.this, getString(R.string.name_is_required));//名称不能为空
                } else {
                    save(name);
                }
            }
        });

    }

    //保存
    private void save(final String name) {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("user",new RequestUserBean(name,Database.USER_MAP.getCustomerPhoto(),Database.USER_MAP.getSignature()));
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
                    Database.USER_MAP.setLoginName(name);
                    showShort( getString(R.string.successfully_modified));
                    DataUtils.saveSharePreToolsKits(UpdateNameActivity.this);
                    UpdateNameActivity.this.finish();
                }else {
                    ToastUtils.showToast(UpdateNameActivity.this,bean.getAlertMessage());//修改失败
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
