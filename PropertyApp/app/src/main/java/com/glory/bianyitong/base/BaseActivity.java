package com.glory.bianyitong.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.ScreenUtil;
import com.glory.bianyitong.util.ToastUtils;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by lucy on 2016/11/21.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//    InputMethodManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentId());
        Database.currentActivity = this;
        ButterKnife.bind(this);  //自动化声明控件
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(getResources().getColor(R.color.line_color));
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //获得状态栏的高度
        int height = ScreenUtil.getStatusHeight(this);
        if (height != -1) {
            //设置Padding
            View view = findViewById(R.id.actionbar);
            if (view != null) {
                view.setPadding(0, height, 0, 0);

                if (view instanceof Toolbar) {
                    setSupportActionBar((Toolbar) view);
                    //隐藏标题
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                }
            }
        }
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