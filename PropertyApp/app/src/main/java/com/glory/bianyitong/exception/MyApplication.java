package com.glory.bianyitong.exception;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteRequest;
import com.chenenyu.router.RouteTable;
import com.chenenyu.router.Router;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.shop.OrderListActivity;
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
import com.tencent.bugly.crashreport.CrashReport;

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

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext(), this);
        CrashReport.initCrashReport(getApplicationContext(),"350a43e596", false);//初始化bugly
        SDKInitializer.initialize(getApplicationContext());
        Router.initialize(this, BuildConfig.DEBUG);
        Instance = this;
        SharedUtil.init(this);
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
