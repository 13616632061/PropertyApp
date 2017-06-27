package com.glory.bianyitong.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.glory.bianyitong.BuildConfig;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.LogUtils;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hyj on 2016/12/26.
 */
public class OkGoRequest {
    private OnOkGoUtilListener onOkGoUtilListener;


    public static OkGoRequest getRequest() {
        return new OkGoRequest();
    }

    public OkGoRequest setOnOkGoUtilListener(OnOkGoUtilListener onOkGoUtilListener) {
        this.onOkGoUtilListener = onOkGoUtilListener;
        return this;
    }


    public void getEntityData(String url, String request) {
        OkGo.post(BuildConfig.DEBUG?HttpURL.HTTP_NEW_URL+url:HttpURL.HTTP_LOGIN+url)
                .tag(this)
                .params("request", request)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (onOkGoUtilListener != null) {

                            LogUtils.d("OkGo","---------------------start----------------------");
                            LogUtils.d("OkGo","URL:   "+response.request().url());
                            LogUtils.d("OkGo","params:    "+response.request().toString());
                            LogUtils.d("OkGo","response:    "+s);
                            LogUtils.d("OkGo","----------------------end-----------------------");
                            onOkGoUtilListener.onSuccess(s);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(Database.currentActivity, "未能连接到服务器");
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onError();
                        }
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.parseError();
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onBefore();
                        }
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onAfter();
                        }
                    }
                });
    }

    public void getEntityData(String url, Map<String,String> map) {
        OkGo.post(BuildConfig.DEBUG?HttpURL.HTTP_NEW_URL+url:HttpURL.HTTP_LOGIN+url)
                .tag(this)
                .params(map,false)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (onOkGoUtilListener != null) {

                            LogUtils.d("OkGo","---------------------start----------------------");
                            LogUtils.d("OkGo","URL:   "+response.request().url());
                            LogUtils.d("OkGo","params:    "+response.request().method());
                            LogUtils.d("OkGo","response:    "+s);
                            LogUtils.d("OkGo","----------------------end-----------------------");
                            onOkGoUtilListener.onSuccess(s);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(Database.currentActivity, "未能连接到服务器");
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onError();
                        }
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.parseError();
                        }
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onBefore();
                        }
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (onOkGoUtilListener != null) {
                            onOkGoUtilListener.onAfter();
                        }
                    }
                });
    }


    public interface OnOkGoUtilListener {
        void onSuccess(String s);

        void onError();

        void parseError();

        void onBefore();

        void onAfter();
    }

}
