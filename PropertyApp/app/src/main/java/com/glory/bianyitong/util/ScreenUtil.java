package com.glory.bianyitong.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕工具类
 */
public class ScreenUtil {

    public static int height;
    public static int width;
    private static ScreenUtil instance;
    private Context context;

    private ScreenUtil(Context context) {
        this.context = context;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public static ScreenUtil getInstance(Context context) {
        if (instance == null) {
            instance = new ScreenUtil(context);
        }
        return instance;
    }



    /**
     * 得到手机屏幕的宽度, pix单位
     */

    /**
     * 获得通知栏的高度
     * @return
     */
    public static int getStatusHeight(Context context){
        int resid = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resid > 0){
            return context.getResources().getDimensionPixelSize(resid);
        }
        return -1;
    }

    /**
     * 得到手机屏幕的宽度, pix单位
     */
    public int getScreenWidth() {
        return width;
    }
}
