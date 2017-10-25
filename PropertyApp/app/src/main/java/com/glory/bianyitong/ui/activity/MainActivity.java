package com.glory.bianyitong.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.CommnunityInfo;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.bean.UPVersionInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.sdk.jpush.ExampleUtil;
import com.glory.bianyitong.sdk.jpush.MyReceiver;
import com.glory.bianyitong.ui.adapter.FragmentTabAdapter;
import com.glory.bianyitong.ui.dialog.OpenDoorPopuWindow;
import com.glory.bianyitong.ui.fragment.FreshSupermarketFragment;
import com.glory.bianyitong.ui.fragment.IndexFragment;
import com.glory.bianyitong.ui.fragment.MyFragment;
import com.glory.bianyitong.ui.fragment.NeighbourFragment;
import com.glory.bianyitong.ui.fragment.OpenTheDoorFragment;
import com.glory.bianyitong.util.ACache;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.FormatNowDate;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.LogUtils;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.update.service.DownloadService;
import com.glory.bianyitong.widght.update.utils.UPVersion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.sharesdk.framework.ShareSDK;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@Route(value = RouterMapping.ROUTER_ACTIVITY_MAIN)
public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground;
    private final int default_index = 0;
    public List<BaseFragment> fragments = new ArrayList<>();
    NeighbourFragment neighbourFragment; //近邻
    IndexFragment indexFragment;          //首页
    MyFragment myFragment;                //我的
    FreshSupermarketFragment freshSupermarketFragment;
    OpenTheDoorFragment openTheDoorFragment;
    FragmentTabAdapter tabAdapter;
    @BindView(R.id.rb_tab_home)
    RadioButton rb_tab_home;
    @BindView(R.id.rb_tab_near)
    RadioButton rb_tab_near;
    @BindView(R.id.rb_tab_open)
    RadioButton rb_tab_open;
    @BindView(R.id.rb_tab_fresh)
    RadioButton rb_tab_fresh;
    @BindView(R.id.rb_tab_my)
    RadioButton rb_tab_my;

    @BindView(R.id.iv_open_the_door)
    ImageView iv_open_the_door;
    @BindView(R.id.iv_pickup)
    RelativeLayout iv_pickup;

    private int lastIndex = default_index;
    private int currentIndex = default_index;
    private OpenDoorPopuWindow picPopuWindow;//开门框
    private Handler mhandler;
    @InjectParam(key = "TypeID" )
     int TypeID; //推送来的
    @InjectParam(key = "PushID" )
     int PushID;

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    private RadioGroup rgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
        Router.injectParams(this);
        ButterKnife.bind(this);
        request();
        //7.0相机权限
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        FormatNowDate formatNowDate=new FormatNowDate();
        Log.v("sadawwwwasd",formatNowDate.refFormatNowDate());

        Database.registrationId = JPushInterface.getRegistrationID(getApplicationContext());
        JPushInterface.setDebugMode(true);//测试版为true
        JPushInterface.init(this);
        if (SharedUtil.getString("jgPushID")!=null){
            Log.i("jgpushid",SharedUtil.getString("jgPushID"));
            //设置别名Alia
            JPushInterface.setAlias(this, SharedUtil.getString("jgPushID"), new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                    Log.d("123123","set Alias result is"+i);
                }
            });
        }
        Intent intent=new Intent(this,MyReceiver.class);
        startService(intent);

        //显示标题  内容的了
        ExampleUtil.customPushNotification(this, 1,
                R.layout.customer_notitfication_layout_one,
                R.drawable.logo_5, R.drawable.logo_5);
        registerMessageReceiver();//初始化从Receiver接受自定义消息

        // 初始化ShareSDK
        ShareSDK.initSDK(this, "1a11d52921447");
        getUser();
        initFragments();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        picPopuWindow = null;
                        break;
                }
            }
        };
        copyImage2Data(R.drawable.logo_5);
//        TypeID = getIntent().getIntExtra("TypeID", 0);
//        PushID = getIntent().getIntExtra("PushID", 0);
        if (TypeID == 1) {
            Intent i = new Intent();
            i.setClass(MainActivity.this, BulletinDetailsActivity.class);
            i.putExtra("bulletinContent", "");
            i.putExtra("communityName", "");
            i.putExtra("bulletinTittle", "");
            i.putExtra("bulletinDatetime", "");
            i.putExtra("PushID", PushID);
            startActivity(i);
        } else if (TypeID == 2) {
            Intent i = new Intent();
            i.setClass(MainActivity.this, MessageDetailsActivity.class);
            i.putExtra("messageContent", "");
            i.putExtra("messageTitle", "");
            i.putExtra("messageTime", "");
            i.putExtra("PushID", PushID);
            startActivity(i);
        }
        requestUpdate();
    }


    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void onResume() {

        isForeground = true;
        super.onResume();
        if (SharedUtil.getString("jgPushID")!=null){
            Log.i("jgpushid",SharedUtil.getString("jgPushID"));
            //设置别名Alia
            JPushInterface.setAlias(this, SharedUtil.getString("jgPushID"), new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                    Log.d("123123","set Alias result is"+i);
                }
            });
        }
        if (getIntent().getIntExtra("tabId", -1) != -1) {
            showFragment(getIntent().getIntExtra("tabId", -1));
        }
        super.onResume();
        if (ActivityUtils.isNetworkAvailable()) {
//            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.now_no_network), Toast.LENGTH_LONG).show();
        }
    }
    private void request() { //注册极光推送标签
        final Set<String> set=new HashSet<>();
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jo = new JSONObject(s);
                    AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);

                    if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                        for (int i=0;i<areaInfo.getListUserCommnunityMapping().size();i++){
                            if (areaInfo.getListUserCommnunityMapping().get(i).getApprovalStatus()==1){
                                set.add("CommunityID"+areaInfo.getListUserCommnunityMapping().get(i).getCommunityID());
                            }

                        }
                        JPushInterface.setTags(getApplicationContext(), set, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {

                            }
                        });
                    } else {
                        JPushInterface.setTags(MainActivity.this, set, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
            }
            @Override
            public void onAfter() {
            }
        }).getEntityData(this,"/ApiUserCommnunity/Query", jsons);

    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    public void initFragments() {
        indexFragment = new IndexFragment();
        neighbourFragment = new NeighbourFragment();
        openTheDoorFragment = new OpenTheDoorFragment();
        freshSupermarketFragment = new FreshSupermarketFragment();
        myFragment = new MyFragment();
        fragments.add(indexFragment);
        fragments.add(neighbourFragment);
        fragments.add(openTheDoorFragment);
        fragments.add(freshSupermarketFragment);
        fragments.add(myFragment);
        rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setCheckedChangedListener(new FragmentTabAdapter.CheckedChangedListener() {
            @Override
            public void onCheckChange(RadioGroup radioGroup, int checkedId, int index) {
//                rgs.getChildAt(checkedId).setBackgroundColor(getResources().getColor(R.color.app_base_red));
                lastIndex = currentIndex;
                currentIndex = index;
//                ToastUtils.showShort(MainActivity.this, index + "");

                setDrawable(rb_tab_home, R.drawable.icon_home);
                setDrawable(rb_tab_near, R.drawable.icon_neighour);
                setDrawable(rb_tab_fresh, R.drawable.icon_fresh);
                setDrawable(rb_tab_my, R.drawable.icon_my);
                switch (checkedId) {
                    case R.id.rb_tab_home:
                        setDrawable(rb_tab_home, R.drawable.icon_home2);
                        break;
                    case R.id.rb_tab_near:
                        setDrawable(rb_tab_near, R.drawable.icon_neighour2);
                        break;
                    case R.id.rb_tab_fresh:
                        setDrawable(rb_tab_fresh, R.drawable.icon_fresh2);
                        break;
                    case R.id.rb_tab_my:
                        setDrawable(rb_tab_my, R.drawable.icon_my2);
                        break;
                }

            }
        });

        setDrawable(rb_tab_home, R.drawable.icon_home2); //加载方式不一样 所以用代码实现第一次drawableTop
        setDrawable(rb_tab_near, R.drawable.icon_neighour);
        setDrawable(rb_tab_fresh, R.drawable.icon_fresh);
        setDrawable(rb_tab_my, R.drawable.icon_my);

        iv_open_the_door.setOnClickListener(this);

            iv_pickup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (Database.USER_MAP==null){
            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(this);
        }else {
        switch (view.getId()) {
            case R.id.iv_open_the_door: //开门
                if (Database.USER_MAP != null) {
                    if (Database.my_community != null && Database.my_community_List != null) {
//                        if (Database.my_community.get("approvalStatus") != null
//                                && Double.valueOf(Database.my_community.get("approvalStatus").toString()).intValue() == 1) {
//                            if (picPopuWindow == null) {
//                                picPopuWindow = new OpenDoorPopuWindow(MainActivity.this, mhandler);
//                            }
//                            // 显示窗口
//                            picPopuWindow.showAtLocation(MainActivity.this.findViewById(R.id.lay_activity_main),
//                                    Gravity.NO_GRAVITY, 0, 0); // 设置layout在PopupWindow中显示的位置
//                        } else {
//                            requestlist();
//                        }

                        if (!TextUtils.isEmpty(RequestUtil.getcommunityid()+"")){
                            if (picPopuWindow == null) {
                                picPopuWindow = new OpenDoorPopuWindow(MainActivity.this, mhandler);
                            }
                            // 显示窗口
                            picPopuWindow.showAtLocation(MainActivity.this.findViewById(R.id.lay_activity_main),
                                    Gravity.NO_GRAVITY, 0, 0); // 设置layout在PopupWindow中显示的位置
                        }

//                        if (Database.my_community.getApprovalStatus() == 1) {//已审核
//                            if (picPopuWindow == null) {
//                                picPopuWindow = new OpenDoorPopuWindow(MainActivity.this, mhandler);
//                            }
//                            // 显示窗口
//                            picPopuWindow.showAtLocation(MainActivity.this.findViewById(R.id.lay_activity_main),
//                                    Gravity.NO_GRAVITY, 0, 0); // 设置layout在PopupWindow中显示的位置
//                        } else {
//                            requestlist();
//                        }
                    } else {//没有小区
                        requestSQ();
//                        showShort("请添加或等待小区审核通过后才可开锁");
//                        Intent intent = new Intent(MainActivity.this, AuthAreaActivity.class); //
//                        intent.putExtra("from", "");
//                        startActivity(intent);
                    }

                } else {//登录
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN)
                            .go(this);
//                    Intent intent_login = new Intent();
//                    intent_login.setClass(MainActivity.this, LoginActivity.class);
//                    startActivity(intent_login);
                }
                break;
            case R.id.iv_pickup: //取件
                Router.build(RouterMapping.ROUTER_ACTIVITY_PICKUP)
                        .go(this);
//                Intent intent = new Intent(this, PickupActivity.class);
//                startActivity(intent);
                break;
        }
        }
    }

    private void requestSQ() { //社区
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jo = new JSONObject(s);
                    AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);

                    if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                        DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
//                        DataUtils.saveSharePreToolsKits(getActivity());
                        if (Database.my_community==null){
                            for (int i=0;i<Database.my_community_List.size();i++){
                                if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getCommunityID() != 0) {
                                    if (Database.my_community_List.get(i).getApprovalStatus()==1){
                                        Database.my_community = Database.my_community_List.get(i);
                                    }
                                }
                            }
                        }else {
                            for (int i = 0; i < Database.my_community_List.size(); i++) {
                                if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getUserCommunityID()
                                        == Database.my_community.getUserCommunityID()) {
                                    Database.my_community = Database.my_community_List.get(i);
                                }
                            }
                        }



                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
            }
            @Override
            public void onAfter() {
                if (Database.my_community == null ) {
                    showShort("请添加或等待小区审核通过后才可开锁");
                    Intent intent = new Intent(MainActivity.this, AuthAreaActivity.class); //
                    intent.putExtra("from", "");
                    startActivity(intent);
                }else {
                    IndexFragment.callBack.notifyVillName(Database.my_community.getCommunityName()+"("+getString(R.string.audited)+")");
                    if (!TextUtils.isEmpty(RequestUtil.getcommunityid()+"")){
                        if (picPopuWindow == null) {
                            picPopuWindow = new OpenDoorPopuWindow(MainActivity.this, mhandler);
                        }
                        // 显示窗口
                        picPopuWindow.showAtLocation(MainActivity.this.findViewById(R.id.lay_activity_main),
                                Gravity.NO_GRAVITY, 0, 0); // 设置layout在PopupWindow中显示的位置
                    }
                }
            }
        }).getEntityData(this,"/ApiUserCommnunity/Query", jsons);

    }

    public void showFragment(int tabId) {
        tabAdapter.showFragment(tabId);
    }

    private void getUser() {
        if(TextUtil.isEmpty(Database.login_return)){
//            ACache cache=ACache.get(this);
            Database.login_return = mCache.getAsString(Constant.user);
        }
        if(!TextUtil.isEmpty(Database.login_return)){
            LoginUserInfo userInfo = new Gson().fromJson(Database.login_return, new TypeToken<LoginUserInfo>(){}.getType());
            Database.USER_MAP = userInfo.getUser();
            Database.accessToken=userInfo.getAccessToken();

            if(!(userInfo.getUserCommnunity()==null)){
                DataUtils.getUesrCommunity(userInfo);//社区列表
                DataUtils.my_community(MainActivity.this);
            }
        }
        if (!TextUtil.isEmpty(mCache.getAsString(Constant.community))){
            Database.my_community=new Gson().fromJson(mCache.getAsString(Constant.community),new TypeToken<CommnunityInfo>(){}.getType());
        }
//        getWeiXin();
    }

    private void getWeiXin() {
        String userID = RequestUtil.getuserid();
        String json = "{\"settingkey\": \"WeiXinAppSecret\",\"controllerName\": \"\",\"actionName\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"userID\": \"" + userID + "\"}";
        String url ="/Setting/SelectByKey";

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("settingvalue") != null) {  //listYellowPageGroup
//                    Constant.AppSecret = hashMap2.get("settingvalue").toString();
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
        }).getEntityData(this,url, json);
    }

    private void requestlist() { //获取社区
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jo = new JSONObject(s);
                    AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);
                    if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                        DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
                        DataUtils.saveSharePreToolsKits(MainActivity.this);
                        for (int i = 0; i < Database.my_community_List.size(); i++) {
                            if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getUserCommunityID()
                                    == Database.my_community.getUserCommunityID()) {
                                Database.my_community = Database.my_community_List.get(i);

                                if (Database.my_community.getApprovalStatus() == 1) {
                                    if (picPopuWindow == null) {
                                        picPopuWindow = new OpenDoorPopuWindow(MainActivity.this, mhandler);
                                    }
                                    // 显示窗口
                                    picPopuWindow.showAtLocation(MainActivity.this.findViewById(R.id.lay_activity_main),
                                            Gravity.NO_GRAVITY, 0, 0); // 设置layout在PopupWindow中显示的位置
                                } else {
                                    ToastUtils.showToast(MainActivity.this, getResources().getString(R.string.the_district_is_still_under_review));
                                }
                            }
                        }
                    } else {
                        ToastUtils.showToast(MainActivity.this, getResources().getString(R.string.the_district_is_still_under_review));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        }).getEntityData(this,HttpURL.HTTP_POST_LOCAL_AREA_QUERY_AREA,jsons);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int state = intent.getIntExtra("State", -1);
        LogUtils.d("CQ", "===STATE==" + state);
        if (state == -1) {

        } else if (state == 1) {
            finish();
        }
    }

    public void copyImage2Data(Integer PicID) //logo保存到本地
    {
        Log.d("FileBoyMap", "mythou copyImage2Data----->Enter PicID=" + PicID);
        String cachePath = Environment.getExternalStorageDirectory().getPath() + "/glory_bianyitong/cache/";
        try {
            //计算图片存放全路径
            String LogoFilePath = cachePath + "logo_5.png";
            File dir = new File(cachePath);
            //如果文件夹不存在，创建一个（只能在应用包下面的目录，其他目录需要申请权限 OWL）
            if (!dir.exists()) {
                Log.d("FileBoyMap", "mythou copyImage2Data----->dir not exist");
            }
            boolean result = dir.mkdirs();
            Log.d("FileBoyMap", "dir.mkdirs()----->result = " + result);
            // 获得封装  文件的InputStream对象
            InputStream is = getResources().openRawResource(PicID);
            Log.d("FileBoyMap", "copyImage2Data----->InputStream open");
            FileOutputStream fos = new FileOutputStream(LogoFilePath);

            byte[] buffer = new byte[8192];
            int count = 0;
            // 开始复制Logo图片文件
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            Constant.logo_path = LogoFilePath;
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置标题栏图片的颜色和字体的颜色。
    private void setDrawable(RadioButton rb, int picture) {
        Drawable drawable = getResources().getDrawable(picture);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 这一步必须要做,否则不会显示.
        rb.setCompoundDrawables(null, drawable, null, null);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    /**
     * 版本更新
     */
    private void requestUpdate() {
//        String json = "{\"version\":{},\"controllerName\": \"Version\",\"actionName\": \"StructureQuery\"," +
//                "\"userID\": \"" + RequestUtil.getuserid() + "\",\"datetime\": \"" + RequestUtil.getCurrentTime() + "\"}";
//        String url = "/Version/StructureQuery";
        String jsons=new Gson().toJson(new BaseRequestBean());
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                try {
                    JSONObject jo = new JSONObject(s);
                    final UPVersionInfo upVersionInfo = new Gson().fromJson(jo.toString(), UPVersionInfo.class);
                    Log.i("resultString", "upVersionInfo.getListVersion()-------" + upVersionInfo.getVersion());
                    if (upVersionInfo.getVersion() != null ) {
                        if (upVersionInfo.getVersion().getUpdatePath() != null) {
                            UPVersion.url = upVersionInfo.getVersion().getUpdatePath();
                        }
                        if (upVersionInfo.getVersion().getImprint() != null) {
                            UPVersion.info = upVersionInfo.getVersion().getImprint();
                        }
                            UPVersion.versionCode = Integer.valueOf(upVersionInfo.getVersion().getVersionCode());

                        if (upVersionInfo.getVersion().getUpdatePath()!=null){
                            Database.DOWN_APK_URL=upVersionInfo.getVersion().getUpdatePath();
                        }

                        if (UPVersion.versionCode > Integer.valueOf(Constant.VERSIONCODE)) {
                            //下面是自定义dialog
                            View view = View.inflate(MainActivity.this, R.layout.download_layout, null);
                            final Dialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                            dialog.show();

                            dialog.setContentView(view);
                            TextView content = (TextView) view.findViewById(R.id.tv_content);
                            content.setText(upVersionInfo.getVersion().getImprint()); //内容
                            //取消
                            TextView cancel = (TextView) view.findViewById(R.id.btn_cancel);
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //当true时 保存版本信息
//                                    if (isCheck) {
//                                        SPUtils.put(MainActivity.this, SPUtils.APK_VERSION, "1.2.0");
//                                    }
                                    //Log.e("TAG","isCheck == " + isCheck);
                                    if (upVersionInfo.getVersion().isPrerequisite()){
                                        showShort("请下载最新版本!");
                                        finish();
                                    }else {
                                        dialog.dismiss();
                                    }
                                }
                            });
                            //确定
                            TextView Sure = (TextView) view.findViewById(R.id.btn_ok);
                            Sure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Database.DOWN_APK_URL!=null){
                                        startService(new Intent(MainActivity.this, DownloadService.class));
                                        //startService(new Intent(MainActivity.this, DownloadService2.class));
                                        //当true时 保存版本信息
//                                    if (isCheck) {
//                                        SPUtils.put(MainActivity.this, SPUtils.APK_VERSION, "1.2.0");
//                                    }
                                        dialog.dismiss();
                                    }else {
                                        showShort("服务器下载地址出现错误，请到应用商店下载！");
                                    }

                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        }).getEntityData(this,HttpURL.HTTP_POST_APIVERSION_QUERY, jsons);

    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                if (!ExampleUtil.isEmpty(extras)) {
//                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                }
//                setCostomMsg(showMsg.toString());
            }
        }
    }


/*
 *  ┏┓　 ┏┓
 * ┏┛┻━━━┛┻━━━━━━┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　 ┃
 * ┃　┳┛　┗┳　    ┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　 ┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　┣┓
 *     ┃　　　　 ┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */

}
