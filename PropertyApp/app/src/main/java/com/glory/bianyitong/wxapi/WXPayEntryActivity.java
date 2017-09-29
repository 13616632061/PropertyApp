package com.glory.bianyitong.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.PickupInfo;
import com.glory.bianyitong.bean.WeiXinInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.activity.shop.PayActivity;
import com.glory.bianyitong.ui.dialog.ShareSdkDialog;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lucy on 2017/9/19.
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_pay)
    ImageView ivPay;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inintTitle("支付结果", true, ""); //支付结果
//        setContentView(R.layout.activity_main);
        api = WXAPIFactory.createWXAPI(this, PayActivity.appId);
        api.handleIntent(getIntent(), this);


    }

    @Override
    protected int getContentId() {
        return R.layout.activity_wxpay;
    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickBtn(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    /**
     * 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
     * arg0。errCode  0成功 -1支付失败 -2取消
     */
    @Override
    public void onResp(BaseResp resp) {
//        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        Log.v("onPayFinish", resp.errCode + " + resp.errCode");
//        Toast.makeText(WXPayEntryActivity.this, resp.errCode, Toast.LENGTH_SHORT).show();
        if (resp.errCode ==0){
            ivPay.setImageResource(R.drawable.dagou);
            tvPay.setText("支付成功");
//            wxPayByApp(resp.errCode);
        }else {
            ivPay.setImageResource(R.drawable.dacha);
            tvPay.setText("支付失败");
//            wxPayByApp(resp.errCode);
        }
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("提示");
//            builder.setMessage("微信支付结果" + String.valueOf(resp.errCode));
//            builder.show();
//        }
    }

//    private void wxPayByApp(int errCode) {
//        Map<String,Object> map=new HashMap<>();
//        map.put("OrderID",23);
//        map.put("DeviceType",2);//,1、ios 2、android
//        String json=new Gson().toJson(map);
//        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
//            @Override
//            public void onSuccess(String s) {
//
//            }
//
//            @Override
//            public void onError() {
//            }
//
//            @Override
//            public void parseError() {
//
//            }
//
//            @Override
//            public void onBefore() {
//
//            }
//
//            @Override
//            public void onAfter() {
//
//            }
//        }).getEntityData(this, HttpURL.HTTP_POST_COUPON_PAY,json);
//    }
}
