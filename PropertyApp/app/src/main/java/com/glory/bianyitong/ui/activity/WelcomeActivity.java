package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.SharePreToolsKits;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/6/29.
 */
public class WelcomeActivity extends BaseActivity {
    ViewPager viewPager;// 页卡内容
    List<View> pageViews;
    AdPageAdapter adapter;

    ImageView imageView;
    private boolean auto_login;
    private String versioncode = "";
    private Animation myAnimation;
    private int TypeID;
    private int PushID;

    @Override
    protected int getContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        super.init();
        TypeID = getIntent().getIntExtra("TypeID", 0);
        PushID = getIntent().getIntExtra("PushID", 0);
        imageView = (ImageView) findViewById(R.id.sbsbsb);
        viewPager = (ViewPager) findViewById(R.id.viewPager1);
        initsdk();

        versioncode = SharePreToolsKits.fetchVersionString(WelcomeActivity.this, "versioncode");//版本号
        if (versioncode == null) {
            versioncode = "";
        }
        if (versioncode != null && versioncode.equals(Constant.VERSIONCODE)) {//版本号
            viewPager.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            myAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gohome();
                }
            }, 1200);
        } else {
            initPageAdapter();
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(new AdPageChangeListener());
//            btn_begin.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    // TODO Auto-generated method stub
//                    SharePreToolsKits.putString(WelcomeActivity.this,"versioncode", Constant.VERSIONCODE);
//                    Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent2);
//                    WelcomeActivity.this.finish();
//                }
//            });
        }

    }

    private void initsdk() {
        JPushInterface.init(getApplicationContext());
//        AnalyticsConfig.enableEncrypt(true);//6.0.0版本以前
//        MobclickAgent.enableEncrypt(true);//设置日志加密
//        MobclickAgent.setCatchUncaughtExceptions(true); //错误统计
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
//        if (versioncode.equals(Constant.VERSIONCODE)) {
//            imageView.startAnimation(myAnimation);
//            auto_login = PreferenceHelper.getMyPreference().getSetting().getBoolean(Constant.CHECK_BOX_LOGIN, false);
//            Log.i("resultString", "isCheck--" + auto_login);
//            Database.USERPHONE = PreferenceHelper.getMyPreference().getSetting().getString(Constant.PHONE_LOGIN, "");
//            Log.i("resultString", "Database.USERPHONE--" + PreferenceHelper.getMyPreference().getSetting().getString(Constant.PHONE_LOGIN, ""));
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (auto_login && !Database.USERPHONE.equals("")) {//判断是否自动登录
////                String url = "http://mai.minghuan.info/pano/memberlogin/appAutoLogin.ihtml?loginName=" + Database.USERPHONE;
//                        String url = HttpURL.HTTP_LOGIN + "/pano/memberlogin/appAutoLogin.ihtml?loginName=" + Database.USERPHONE;
//                        Login(url, Database.USERPHONE);
//                    } else {
//                        gohome();
//                    }
//                }
//            }, 1200);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void initPageAdapter() {
        pageViews = new ArrayList<View>();
        int pic[] = {R.drawable.welcome1, R.drawable.welcome2, R.drawable.welcome3, R.drawable.welcome4};
        for (int i = 0; i < pic.length; i++) {

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundDrawable(getResources().getDrawable(pic[i]));
            pageViews.add(imageView);

        }

        adapter = new AdPageAdapter(pageViews);
    }

    private void gohome() {
//        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
//        i.putExtra("TypeID", TypeID);
//        i.putExtra("PushID", PushID);
//        startActivity(i);
        Router.build(RouterMapping.ROUTER_ACTIVITY_MAIN)
                .with("TypeID", TypeID)
                .with("PushID", PushID)
                .go(this);
        WelcomeActivity.this.finish();
//        if (code.equals(Constant.VERSIONCODE)) {//版本号
//            WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
//            WelcomeActivity.this.finish();
//        } else {
//            setContentView(R.layout.activity_welcome);
//            viewPager = (ViewPager) findViewById(R.id.viewPager1);
//            btn_begin = (Button) findViewById(R.id.btn_begin);
//            initPageAdapter();
//            viewPager.setAdapter(adapter);
//            viewPager.setOnPageChangeListener(new AdPageChangeListener());
//            btn_begin.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    // TODO Auto-generated method stub
//                    PreferenceHelper.getMyPreference().getEditor().putString("versioncode", Constant.VERSIONCODE).commit();
//                    Intent intent2 = new Intent(WelcomeActivity.this, HomeActivity.class);
//                    startActivity(intent2);
//                    WelcomeActivity.this.finish();
//                }
//            });
//        }
    }

    private final class AdPageAdapter extends PagerAdapter {
        private List<View> views = null;

        /**
         * 初始化数据源, 即View数组
         */
        public AdPageAdapter(List<View> views) {
            this.views = views;
        }

        /**
         * 从ViewPager中删除集合中对应索引的View对象
         */
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        /**
         * 获取ViewPager的个数
         */
        @Override
        public int getCount() {
            return views.size();
        }

        /**
         * 从View集合中获取对应索引的元素, 并添加到ViewPager中
         */
        @Override
        public Object instantiateItem(View container, final int position) {
            ((ViewPager) container).addView(views.get(position), 0);
            views.get(position).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (position == 3) {
                        SharePreToolsKits.putVersionString(WelcomeActivity.this, "versioncode", Constant.VERSIONCODE);
                        Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent2);
                        WelcomeActivity.this.finish();
                    }
                }
            });
            return views.get(position);
        }

        /**
         * 是否将显示的ViewPager页面与instantiateItem返回的对象进行关联 这个方法是必须实现的
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
    //登录
//    private void Login(String url, final String phone) {
//        Log.i("resultString", "login--" + url);
//        final AutoLoginTask loginTask = new AutoLoginTask("", this, null, url);
//        loginTask.execute(new TaskCallback() {
//            @Override
//            public void onSucceed() {
//                if (loginTask.code == 0) {
//                    gohome();
////                    new Handler().postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                            if (Database.USER_MAP != null && Database.USER_MAP.get("addIntegral") != null
////                                    && !Database.USER_MAP.get("addIntegral").equals("")) {
////                                int addIntegral = Double.valueOf(Database.USER_MAP.get("addIntegral").toString()).intValue();
////                                if(addIntegral > 0){ //签到积分
////                                    Database.addIntegral = addIntegral;
////                                }
////                            }
//////                            MobclickAgent.onProfileSignIn(phone);//友盟统计
////                        }
////                    }, 10);
//                } else {
//                    gohome();
//                }
//            }
//
//            @Override
//            public void onFailed() {
//                //请求失败
//                MyApplication.getInstance().toast(getResources().getString(R.string.request_failure), 1);
//                gohome();
//            }
//        });
//    }

    /**
     * ViewPager 页面改变监听器
     */
    private final class AdPageChangeListener implements OnPageChangeListener {

        /**
         * 页面滚动状态发生改变的时候触发
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        /**
         * 页面滚动的时候触发
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if (arg0 == viewPager.getAdapter().getCount() - 1) {// 滑动到最后一页
                SharePreToolsKits.putVersionString(WelcomeActivity.this, "versioncode", Constant.VERSIONCODE);
                Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent2);
                WelcomeActivity.this.finish();
            }
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
//            if (arg0 == 2) {
//                btn_begin.setVisibility(View.VISIBLE);
//            } else {
//                btn_begin.setVisibility(View.GONE);
//            }
        }
    }
//    Error:Error converting bytecode to dex:
//    Cause: com.android.dex.DexException: Multiple dex files define Lcom/ta/utdid2/android/utils/AESUtils;

}
