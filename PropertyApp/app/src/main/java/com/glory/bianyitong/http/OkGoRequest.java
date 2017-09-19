package com.glory.bianyitong.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chenenyu.router.Router;
import com.glory.bianyitong.BuildConfig;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.LogUtils;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

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


    public void getEntityData(Context context,String url, String request) {
        LogUtils.d("OkGoGo","---------------------start----------------------");
        LogUtils.d("OkGoGo","URL:   "+HttpURL.HTTP_NEW_URL+url);
        LogUtils.d("OkGoGo","params:    "+request);

//        final Gson gson=new Gson();
//        BaseRequestBean requestBean=gson.fromJson(request, BaseRequestBean.class);
//        if((!TextUtil.isEmpty(requestBean.accessToken)) && requestBean.accessToken.equals("0")){
//            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(context);
//            if(onOkGoUtilListener!=null)
//                onOkGoUtilListener.onError();
//            return;
//        }
//        else  if(requestBean.accessToken.equals("-1")){
//            Map<String,Object> map=new Gson().fromJson(request,new TypeToken<Map<String,Object>>(){}.getType());
//            map.put("accessToken","0");
//            request=new Gson().toJson(map);
//        }




//        OkGo.post(BuildConfig.DEBUG?HttpURL.HTTP_NEW_URL+url:HttpURL.HTTP_LOGIN+url)
        OkGo.post(HttpURL.HTTP_URl+url)
                .tag(this)
                .params("request", request)
//                .headers(new HttpHeaders("Content-Type","application/x-www-form-urlencoded"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        LogUtils.d("OkGoGo","response:    "+s);
                        LogUtils.d("OkGoGo","----------------------end-----------------------");

                        if(TextUtil.isEmpty(s)){
                            onOkGoUtilListener.onError();
                        }else {
                            BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                            if(bean.getStatusCode()==-105){
                                Database.accessToken=null;
                                Database.USER_MAP=null;
                            }
                            if (onOkGoUtilListener != null) {
                                onOkGoUtilListener.onSuccess(s);
                            }
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.i("resultString", "请求错误------");
//                        com.github.lazylibrary.util.ToastUtils.showToast(Database.currentActivity, "未能连接到服务器");

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
        JSONObject request=new JSONObject(map);

        OkGo.post(BuildConfig.DEBUG?HttpURL.HTTP_NEW_URL+url:HttpURL.HTTP_LOGIN+url)
                .tag(this)
                .params("request",request.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.d("OkGoGo","---------------------start----------------------");
                        LogUtils.d("OkGoGo","URL:   "+response.request().url());
//                        LogUtils.d("OkGoGo","params:    "+request);
                        LogUtils.d("OkGoGo","response:    "+s);
                        LogUtils.d("OkGoGo","----------------------end-----------------------");

                        if(TextUtil.isEmpty(s)){
                            onOkGoUtilListener.onError();
                        }else {
                            BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                            if(bean.getStatusCode()==-105){
                                Database.accessToken=null;
                            }
                            if (onOkGoUtilListener != null) {
                                onOkGoUtilListener.onSuccess(s);
                            }
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

    public void getEntityDataForGet(String url, Map<String,String> map) {
        OkGo.get(BuildConfig.DEBUG?HttpURL.HTTP_NEW_URL+url:HttpURL.HTTP_LOGIN+url)
                .tag(this)
                .params(map,false)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.d("OkGoGo","---------------------start----------------------");
                        LogUtils.d("OkGoGo","URL:   "+response.request().url());
//                        LogUtils.d("OkGoGo","params:    "+request);
                        LogUtils.d("OkGoGo","response:    "+s);
                        LogUtils.d("OkGoGo","----------------------end-----------------------");

                        if(TextUtil.isEmpty(s)){
                            onOkGoUtilListener.onError();
                        }else {
                            if (onOkGoUtilListener != null) {
                                onOkGoUtilListener.onSuccess(s);
                            }
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
