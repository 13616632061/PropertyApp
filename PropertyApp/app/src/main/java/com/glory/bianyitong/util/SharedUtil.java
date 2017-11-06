package com.glory.bianyitong.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.glory.bianyitong.bean.LoginUserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2017/9/25.
 */
public class SharedUtil {
    public static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    /**
     * 保存实体类
     */
    public static  void putBean(LoginUserInfo.UserBean model){//需要实体类继承一个基类
        String key=model.getClass().getName();
        String value=new Gson().toJson(model);
        putString(key, value);
    }
    /**
     * 获取实体类
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> model){
        String key=model.getName();
        String value=getString(key);
        T t= new Gson().fromJson(value,model);
        return t;
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(String tag) {
        List<T> datalist=new ArrayList<T>();
        String strJson = sharedPreferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void putString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public static void putInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key){
        return sharedPreferences.getInt(key, -1);
    }

    public static void putBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public static void clear() {
        editor.clear();
    }

}
