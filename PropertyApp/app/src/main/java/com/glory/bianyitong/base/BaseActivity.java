package com.glory.bianyitong.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.ACache;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.ScreenUtil;
import com.glory.bianyitong.util.StatusBarUtils;
import com.glory.bianyitong.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by lucy on 2016/11/21.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//    InputMethodManager manager;
    public static ACache mCache;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentId());
        mCache=ACache.get(this);
        Database.currentActivity = this;
        ButterKnife.bind(this);  //自动化声明控件
        Database.ip=getIPAddress(this);


//       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
//               window.setNavigationBarColor(Color.TRANSPARENT);
//        }
//        getResources().getColor(R.color.bggray);

        //透明导航栏
//              getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        if (isOpenStatus()) {
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtils.setWindowStatusBarColor(this,R.color.white);
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////                window.setStatusBarColor(getResources().getColor(R.color.line_color));
//            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        //透明状态栏  加上他有色差
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //获得状态栏的高度
//        int height = ScreenUtil.getStatusHeight(this);
//        if (height != -1) {
//            //设置Padding
//            View view = findViewById(R.id.actionbar);
//            if (view != null) {
//                view.setPadding(0, height, 0, 0);
//
//                if (view instanceof Toolbar) {
//                    setSupportActionBar((Toolbar) view);
//                    //隐藏标题
//                    getSupportActionBar().setDisplayShowTitleEnabled(false);
//                }
//            }
//        }
        init();
        bindListener();
        loadDatas();
//        }
        if (ActivityUtils.isNetworkAvailable()) {
//            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.now_no_network), Toast.LENGTH_LONG).show();
        }
    }


    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    //初始化标题栏   标题  右边是否隐藏  右边
    public void inintTitle(String title, boolean isGone, String rightText) {

        if (title != null) {
            ((TextView) findViewById(R.id.title_ac_text)).setText(title); //
        }
        if (isGone) {
            findViewById(R.id.iv_title_text_right).setVisibility(View.GONE);
        } else {
            findViewById(R.id.iv_title_text_right).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.iv_title_text_right)).setText(rightText);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 初始化
     */
    protected void init() {}
    /**
     * 绑定监听
     */
    protected void bindListener() {}
    /**
     * 加载数据
     */
    protected void loadDatas() {}
    /**
     * 以动画的方式启动activity
     *
     * @param intent
     * @param animinid
     * @param animoutid
     */
    public void startActivityForAnimation(Intent intent, int animinid, int animoutid) {
        startActivity(intent);
        overridePendingTransition(animinid, animoutid);
    }

    /**
     * 获得activity显示的布局ID
     *
     * @return
     */
    protected abstract int getContentId();

    /**
     * 是否打开沉浸式状态栏
     *
     * @return
     */
    public boolean isOpenStatus() {
        return true;
    }

    protected void showShort(String text) {
        ToastUtils.showShort(this, text);
    }

    protected void showLong(String text) {
        ToastUtils.showLong(this, text);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

    //    //任意点击其他隐藏输入法
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}