package com.glory.bianyitong.exception;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteRequest;
import com.chenenyu.router.RouteTable;
import com.chenenyu.router.Router;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.AddAwardActivity;
import com.glory.bianyitong.ui.activity.AddCommentActivity;
import com.glory.bianyitong.ui.activity.AddRoomActivity;
import com.glory.bianyitong.ui.activity.AddressActivity;
import com.glory.bianyitong.ui.activity.AllEvaluationActivity;
import com.glory.bianyitong.ui.activity.AuthAreaActivity;
import com.glory.bianyitong.ui.activity.AwardManagerActivity;
import com.glory.bianyitong.ui.activity.CollectionActivity;
import com.glory.bianyitong.ui.activity.DynamicDetailsActivity;
import com.glory.bianyitong.ui.activity.FamilyManagementActivity;
import com.glory.bianyitong.ui.activity.FeedbackActivity;
import com.glory.bianyitong.ui.activity.GoodsDetailsActivity;
import com.glory.bianyitong.ui.activity.ListCommunityBuildingActivity;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.activity.MainActivity;
import com.glory.bianyitong.ui.activity.MyReleaseActivity;
import com.glory.bianyitong.ui.activity.PersonalDataActivity;
import com.glory.bianyitong.ui.activity.PersonalHomePageActivity;
import com.glory.bianyitong.ui.activity.PickupActivity;
import com.glory.bianyitong.ui.activity.SearchActivity;
import com.glory.bianyitong.ui.activity.SettingActivity;
import com.glory.bianyitong.ui.activity.UpdateDescribeActivity;
import com.glory.bianyitong.ui.activity.UpdateNameActivity;
import com.glory.bianyitong.ui.activity.shop.AddressAddAndEditActivity;
import com.glory.bianyitong.ui.activity.shop.CommentActivity;
import com.glory.bianyitong.ui.activity.shop.CouponListActivity;
import com.glory.bianyitong.ui.activity.shop.ExpressBarAddActivity;
import com.glory.bianyitong.ui.activity.shop.ExpressMapActivity;
import com.glory.bianyitong.ui.activity.shop.FirmOrderActivity;
import com.glory.bianyitong.ui.activity.shop.OrderListActivity;
import com.glory.bianyitong.ui.activity.shop.PayActivity;
import com.glory.bianyitong.ui.activity.shop.SelectLocalActivity;
import com.glory.bianyitong.ui.activity.shop.ShoppingCartActivity;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.imgloader.AuthImageDownloader;
import com.glory.bianyitong.util.imgloader.TilmImgLoaderUtil;
import com.lzy.okgo.BuildConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MyApplication extends Application {
    private static final String TAG = "JPush";
    public static MyApplication Instance;
    public ImageLoader imageLoader;
    public DisplayImageOptions options; // 显示图片的设置
//    public LocationService locationService; //百度地图定位服务
    public Vibrator mVibrator;

    public static MyApplication getInstance() {
        return Instance;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext(), this);
//        CrashReport.initCrashReport(getApplicationContext(),"350a43e596", false);//初始化bugly
        Bugly.init(getApplicationContext(), "4ac9a86c99", false);
        SDKInitializer.initialize(getApplicationContext());
        Router.initialize(this,true);
        Instance = this;
        SharedUtil.init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        // 动态添加路由表
        Router.handleRouteTable(new RouteTable() {
            @Override
            public void handle(Map<String, Class<?>> map) {
                map.put("ROUTER_ACTIVITY_LOGIN", LoginActivity.class);
                map.put("ROUTER_ACTIVITY_MAIN", MainActivity.class);
                map.put("ROUTER_ACTIVITY_PICKUP", PickupActivity.class);
                map.put("ROUTER_ACTIVITY_AddAWARD", AddAwardActivity.class);
                map.put("ROUTER_ACTIVITY_PERSION", PersonalDataActivity.class);
                map.put("ROUTER_ACTIVITY_AUTHAREA", AuthAreaActivity.class);
                map.put("ROUTER_ACTIVITY_AAWARD_MANAGER", AwardManagerActivity.class);
                map.put("ROUTER_ACTIVITY_AAWARD_MY_RELEASE", MyReleaseActivity.class);
                map.put("ROUTER_ACTIVITY_SETTING", SettingActivity.class);
                map.put("ROUTER_ACTIVITY_FEEDBACK", FeedbackActivity.class);
                map.put("ROUTER_ACTIVITY_FAMILYMANAGEMENT", FamilyManagementActivity.class);
                map.put("ROUTER_ACTIVITY_MY_UPDATE_NAME", UpdateNameActivity.class);
                map.put("ROUTER_ACTIVITY_MY_UPDATE_DESC", UpdateDescribeActivity.class);
                map.put("ROUTER_ACTIVITY_MY_ADDRESS_MANAGER", AddressActivity.class);
                map.put("ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_ADD", ExpressBarAddActivity.class);
                map.put("ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_MAP", ExpressMapActivity.class);
                map.put("ROUTER_ACTIVITY_MY_ADDRESS_ADD", AddressAddAndEditActivity.class);
                map.put("ROUTER_ACTIVITY_AREA_ADD", AddRoomActivity.class);
                map.put("ROUTER_ACTIVITY_AREA_LIST", ListCommunityBuildingActivity.class);
                map.put("ROUTER_ACTIVITY_FRIEND_DETAIL", DynamicDetailsActivity.class);
                map.put("ROUTER_ACTIVITY_FRIEND_COMMENT", AddCommentActivity.class);
                map.put("ROUTER_ACTIVITY_FRIEND_USER_INFO", PersonalHomePageActivity.class);
                map.put("ROUTER_ACTIVITY_ORDER", OrderListActivity.class);
                map.put("ROUTER_ACTIVITY_ORDER_FIRM", FirmOrderActivity.class);
                map.put("ROUTER_ACTIVITY_ORDER_PAY", PayActivity.class);
                map.put("ROUTER_ACTIVITY_ORDER_COMMENT", CommentActivity.class);
                map.put("ROUTER_ACTIVITY_ALLORDER_COMMENT", AllEvaluationActivity.class);
                map.put("ROUTER_ACTIVITY_PRODUCT_DETAIL", GoodsDetailsActivity.class);
                map.put("ROUTER_ACTIVITY_PRODUCT_SEARCH", SearchActivity.class);
                map.put("ROUTER_ACTIVITY_PRODUCT_SELECT_LOCAL", SelectLocalActivity.class);
                map.put("ROUTER_ACTIVITY_SHOPPINGCART", ShoppingCartActivity.class);
                map.put("ROUTER_ACTIVITY_COUPON_LIST", CouponListActivity.class);
                map.put("ROUTER_ACTIVITY_COLLECTION_LIST", CollectionActivity.class);
            }
        });
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL );
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,null);
        //极光推送
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);// 初始化 JPush
////        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//        SDKInitializer.initialize(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
//        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .imageDownloader(new AuthImageDownloader(this)) //https
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        options = TilmImgLoaderUtil.getDisplayImageOptions();
        // 显示图片的设置
        imageLoader = ImageLoader.getInstance();


        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")
                    //如果使用默认的 OkGo.DEFAULT_MILLISECONDS 60 秒,以下三行也不需要传
                    .setConnectTimeout(15000)  //全局的连接超时时间
                    .setReadTimeOut(15000)     //全局的读取超时时间
                    .setWriteTimeOut(15000)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())     //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
                    .setCertificates();               //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上,不需要就不要传
//                    .addCommonHeaders(headers)                                         //设置全局公共头
//                    .addCommonParams(params);                                          //设置全局公共参数

        } catch (Exception e) {
            e.printStackTrace();
        }
        //一键设置 全局字体
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("font/PingFang Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }

//    public void toast(String string, int lenth) {
//        if (string == null || string == "") {
//            return;
//        }
//        Toast.makeText(Database.currentActivity, string, lenth).show();
//    }


    @Override
    public void onTerminate() {
        super.onTerminate();
//        System.exit(0);
    }
}
