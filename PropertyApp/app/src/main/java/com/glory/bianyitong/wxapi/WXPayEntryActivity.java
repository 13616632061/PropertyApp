package com.glory.bianyitong.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.glory.bianyitong.R;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * Created by lucy on 2017/9/19.
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = WXAPIFactory.createWXAPI(this,"wxd2ec5fc5fab63695");
        api.handleIntent(getIntent(), this);
//        PayReq req = new PayReq();
//        req.appId = APP_ID;
//        req.partnerId = "";
//        req.prepayId = "";
//        req.packageValue = "";
//        req.nonceStr = "";
//        req.timeStamp = "";
//        req.sign = "";
//        api.sendReq(req);
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
        Log.v("onPayFinish",resp.errCode + " + resp.errCode");
//        Toast.makeText(WXPayEntryActivity.this, resp.errCode, Toast.LENGTH_SHORT).show();
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("微信支付结果" +String.valueOf(resp.errCode));
            builder.show();
        }
    }
}
