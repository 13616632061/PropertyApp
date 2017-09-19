package com.glory.bianyitong.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * Created by lucy on 2017/9/19.
 */
public class AppRegister extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, "wxd2ec5fc5fab63695");

        // 将该app注册到微信
        msgApi.registerApp("wxd2ec5fc5fab63695");
    }

}

