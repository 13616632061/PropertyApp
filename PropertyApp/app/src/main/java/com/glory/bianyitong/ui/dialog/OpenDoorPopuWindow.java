package com.glory.bianyitong.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.AdvertisingInfo2;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.bean.entity.request.RequestAdvertising;
import com.glory.bianyitong.bean.entity.response.ResponseOpenLock;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.HtmlActivity;
import com.glory.bianyitong.ui.activity.KeyManagerActivity;
import com.glory.bianyitong.ui.activity.SwitchAreaActivity;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/2.
 * 开门
 */
public class OpenDoorPopuWindow extends PopupWindow implements View.OnClickListener {
//    ArrayList<LinkedTreeMap<String, Object>> locklist;
private List<UserLockInfo.ListUserLockMappingBean> locklist;
    private ImageView iv_open_ad; //广告图
    private TextView tv_switch_area_od;//切换小区
    private TextView tv_key_manager; //钥匙管理
    private HorizontalScrollView hs_open_door_lay;
    private LinearLayout ll_open_the_door; //门
    private LinearLayout ll_open_door_lay; //2个以内
    private LinearLayout lay_door1;
    private TextView tv_door_name1;
    private LinearLayout lay_door2;
    private TextView tv_door_name2;
    private ImageView iv_opendoor_close; //关闭
    private ProgressDialog progressDialog;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private Handler handler;
    private List<AdvertisingInfo2.ListAdvertisingBean> ad_list;

    public OpenDoorPopuWindow(final Context context, Handler handler) {
        super(context);
        EventBus.getDefault().register(this);
        this.context = context;
        this.handler = handler;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.fg_openthedoor, null);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x90000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

        initView();
    }


    private void initView() {
        iv_open_ad = (ImageView) mMenuView.findViewById(R.id.iv_open_ad);
        tv_switch_area_od = (TextView) mMenuView.findViewById(R.id.tv_switch_area_od);
        tv_key_manager = (TextView) mMenuView.findViewById(R.id.tv_key_manager);
        ll_open_the_door = (LinearLayout) mMenuView.findViewById(R.id.ll_open_the_door);
        iv_opendoor_close = (ImageView) mMenuView.findViewById(R.id.iv_opendoor_close);
        hs_open_door_lay = (HorizontalScrollView) mMenuView.findViewById(R.id.hs_open_door_lay);
        ll_open_door_lay = (LinearLayout) mMenuView.findViewById(R.id.ll_open_door_lay);
        LinearLayout pop_main= (LinearLayout) mMenuView.findViewById(R.id.pop_main);

        lay_door1 = (LinearLayout) mMenuView.findViewById(R.id.lay_door1);
        tv_door_name1 = (TextView) mMenuView.findViewById(R.id.tv_door_name1);
        lay_door2 = (LinearLayout) mMenuView.findViewById(R.id.lay_door2);
        tv_door_name2 = (TextView) mMenuView.findViewById(R.id.tv_door_name2);

        tv_key_manager.setVisibility(View.GONE);

        iv_open_ad.setOnClickListener(this);
        tv_switch_area_od.setOnClickListener(this);
        tv_key_manager.setOnClickListener(this);
        iv_opendoor_close.setOnClickListener(this);
        lay_door1.setOnClickListener(this);
        lay_door2.setOnClickListener(this);
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("text" ,"阳光花园"+i+"号门");
//            list.add(map);
//        }
        request();
        ad_request();

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                OpenDoorPopuWindow.this.dismiss();
                handler.sendEmptyMessage(0);
                EventBus.getDefault().unregister(this);//反注册EventBus
            }
        });

    }



    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.tv_switch_area_od: //切换小区
                Intent intent = new Intent(context, SwitchAreaActivity.class);
                context.startActivity(intent);
                break;
            case R.id.tv_key_manager: // 钥匙管理
                Intent intent2 = new Intent(context, KeyManagerActivity.class);
                context.startActivity(intent2);
                break;
            case R.id.iv_opendoor_close: //关闭
                OpenDoorPopuWindow.this.dismiss();
                handler.sendEmptyMessage(0);
                EventBus.getDefault().unregister(this);//反注册EventBus
                break;
            case R.id.lay_door1: //门1
                ServiceDialog.ButtonClickZoomInAnimation(lay_door1, 0.95f);
//                if (locklist != null && locklist.get(0) != null && locklist.get(0).get("lockID") != null) {
//                    OpenLock(Double.valueOf(locklist.get(0).get("lockID").toString()).intValue());
//                }
                if (locklist != null && locklist.get(0) != null) {
                    OpenLock(locklist.get(0).getLockID());
                }
                break;
            case R.id.lay_door2: //门2
                ServiceDialog.ButtonClickZoomInAnimation(lay_door2, 0.95f);
//                if (locklist != null && locklist.get(1) != null && locklist.get(1).get("lockID") != null) {
//                    OpenLock(Double.valueOf(locklist.get(1).get("lockID").toString()).intValue());
//                }
                if (locklist != null && locklist.get(1) != null) {
                    OpenLock(locklist.get(1).getLockID());
                }
                break;
            case R.id.iv_open_ad://广告点击
                if (ad_list.get(0).getAdvertisingUrl()!=null){
//                    Intent intent4 = new Intent();
//                    intent4.setAction("android.intent.action.VIEW");
//                    Uri content_url = Uri.parse(ad_list.get(0).getAdvertisingUrl());
//                    intent4.setData(content_url);
//                    context.startActivity(intent4);
                    Intent intent4 = new Intent(context, HtmlActivity.class);
                    intent4.putExtra("from", "webview");
                    intent4.putExtra("url", ad_list.get(0).getAdvertisingUrl());
                    context.startActivity(intent4);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 横向滑动布局
     */
    public void horizontalScrollViewLayout(final Context context, final List<UserLockInfo.ListUserLockMappingBean> list, LinearLayout lay_gallery) {//List<LinkedTreeMap<String, Object>>
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.item_hs_door, lay_gallery, false);
                final LinearLayout door_lay = (LinearLayout) view.findViewById(R.id.lay_door);
//            ImageView door_pic = (ImageView) view.findViewById(R.id.iv_door_pic);
                final TextView door_name = (TextView) view.findViewById(R.id.tv_door_name);
//            if (list != null && list.get(i).get("picture") != null && list.get(i).get("picture").toString().length() != 0 && !list.get(i).get("picture").toString().equals("")) {
//                setPicture(list.get(i).get("picture").toString(), type_pic, ImageView.ScaleType.FIT_CENTER);
//            }

//                if (list != null && list.get(i).get("lockName") != null && list.get(i).get("lockName").toString().length() != 0 && !list.get(i).get("lockName").toString().equals("")) {
//                    door_name.setText(list.get(i).get("lockName").toString());
//                }
                if (list != null && list.get(i).getLockName() != null && list.get(i).getLockName().length() != 0) {
                    door_name.setText(list.get(i).getLockName());
                }

                final int j = i;
                door_lay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        ServiceDialog.ButtonClickZoomInAnimation(door_lay, 0.95f);
//                        OpenLock(Double.valueOf(list.get(j).get("lockID").toString()).intValue());
                        OpenLock(list.get(j).getLockID());
                    }
                });

                lay_gallery.addView(view);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switcharea(Boolean isSwitch) {
        if (isSwitch) {
//            ToastUtils.showToast(context, "刷新了...");
            hs_open_door_lay.setVisibility(View.GONE);
            ll_open_door_lay.setVisibility(View.GONE);
            lay_door2.setVisibility(View.GONE);
            tv_key_manager.setVisibility(View.GONE);
            ad_request();
            request();
        }
    }
    private void request() { //钥匙查询
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("userLockMapping", new Object());
            String json = new Gson().toJson(map);
            progressDialog = ProgressDialog.show(context, "","加载中", true);
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                        UserLockInfo uinfo = new Gson().fromJson(s.toString(), UserLockInfo.class);
                        if (uinfo == null) {

                            return;
                        }
                        if(uinfo.getStatusCode()==-105){
                            dismiss();
                            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(context);
                        }
                        if (uinfo != null && uinfo.getStatusCode() == 1) {
                            locklist = uinfo.getListUserLockMapping();
                            if (locklist != null && locklist.size() > 0 && locklist.size() == 1) {
                                hs_open_door_lay.setVisibility(View.GONE);
                                ll_open_door_lay.setVisibility(View.VISIBLE);
                                lay_door2.setVisibility(View.GONE);
                                if (locklist != null && locklist.get(0) != null && locklist.get(0).getLockName() != null) {
                                    tv_door_name1.setText(locklist.get(0).getLockName());
                                }
                                tv_key_manager.setVisibility(View.VISIBLE);
                            } else if (locklist != null && locklist.size() > 0 && locklist.size() == 2) {
                                hs_open_door_lay.setVisibility(View.GONE);
                                ll_open_door_lay.setVisibility(View.VISIBLE);
                                lay_door2.setVisibility(View.VISIBLE);
//                                    if (locklist != null && locklist.get(0) != null && locklist.get(0).get("lockName") != null) {
//                                        tv_door_name1.setText(locklist.get(0).get("lockName").toString());
//                                    }
                                if (locklist != null && locklist.get(0) != null && locklist.get(0).getLockName() != null) {
                                    tv_door_name1.setText(locklist.get(0).getLockName());
                                }
//                                    if (locklist != null && locklist.get(1) != null && locklist.get(1).get("lockName") != null) {
//                                        tv_door_name2.setText(locklist.get(1).get("lockName").toString());
//                                    }
                                if (locklist != null && locklist.get(1) != null && locklist.get(1).getLockName() != null) {
                                    tv_door_name2.setText(locklist.get(1).getLockName());
                                }
                                tv_key_manager.setVisibility(View.VISIBLE);
                            } else if (locklist != null && locklist.size() > 0 && locklist.size() >= 3) {
                                hs_open_door_lay.setVisibility(View.VISIBLE);
                                ll_open_door_lay.setVisibility(View.GONE);
                                horizontalScrollViewLayout(context, locklist, ll_open_the_door);
                                tv_key_manager.setVisibility(View.VISIBLE);
                            }
                        } else {
                            ll_open_the_door.removeAllViews();
                        }
                    progressDialog.dismiss();

                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                }

                @Override
                public void parseError() {
                    progressDialog.dismiss();
                }

                @Override
                public void onBefore() {

                }

                @Override
                public void onAfter() {
                    progressDialog.dismiss();
                }
            }).getEntityData(context, "/ApiUserKey/Query", json);
        }catch (Exception e){

        }
    }

    private void OpenLock(int lockID) { //开锁
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);//获取系统振动
        vibrator.vibrate(500);

        final Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("lockID",lockID);
        String json=new Gson().toJson(map);
        progressDialog = ProgressDialog.show(context, "", context.getString(R.string.unlocked), true);//开锁中
        progressDialog.setCanceledOnTouchOutside(true);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseOpenLock bean=new Gson().fromJson(s,ResponseOpenLock.class);
                if(bean.getStatusCode()==1) {
                    Database.accessToken = bean.getAccessToken();
                }
                if (bean.getAccessToken()!=null&&!bean.getAccessToken().equals(""))
                Database.accessToken = bean.getAccessToken();
                LoginUserInfo userInfo = new Gson().fromJson(Database.login_return, new TypeToken<LoginUserInfo>(){}.getType());
                userInfo.setAccessToken(Database.accessToken);
                Database.login_return=new Gson().toJson(userInfo);
                BaseActivity.mCache.put(Constant.user, Database.login_return);


                if(bean!=null)
                ToastUtils.showToast(context, bean.getAlertMessage());
                progressDialog.dismiss();
            }

            @Override
            public void onError() {
                progressDialog.dismiss();
                ToastUtils.showToast(context, context.getString(R.string.system_error));
            }

            @Override
            public void parseError() {
                progressDialog.dismiss();
            }

            @Override
            public void onBefore() {

            }

            @Override
            public void onAfter() {

            }
        }).getEntityData(context,HttpURL.HTTP_POST_OPEN_LOCK,json);
    }

    /**
     * 广告查询
     */
    private void ad_request() { //获取广告轮播
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("advertising",new RequestAdvertising(2));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {

                AdvertisingInfo2 adinfo = new Gson().fromJson(s, AdvertisingInfo2.class);

//                    Log.i("resultString", "adinfo.getListAdvertising()-------" + adinfo.getListAdvertising());
                if (adinfo != null && adinfo.getListAdvertising() != null) {
                    if(adinfo.getStatusCode()==1){
                        ad_list = adinfo.getListAdvertising();
                        if (ad_list != null && ad_list.get(0) != null && ad_list.get(0).getAdvertisingPicture() != null) {
                            String data = ad_list.get(0).getAdvertisingPicture();
//                                ServiceDialog.setPicture(ad_list.get(0).get("advertisingPicture").toString(), iv_open_ad, null);+
                            Glide.with(context).load(data).error(R.drawable.wait).placeholder(R.drawable.wait).into(iv_open_ad);
                        }
                    }

                }
                }catch (Exception e){

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
        }).getEntityData(context,HttpURL.HTTP_POST_GET_AD,json);
    }

    private boolean checkPermission() {
        String permission = "android.permission.CAMERA"; //你要判断的权限名字
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
