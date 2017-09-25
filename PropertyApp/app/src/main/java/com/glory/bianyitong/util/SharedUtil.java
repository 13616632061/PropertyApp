package com.glory.bianyitong.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by lucy on 2017/9/25.
 */
public class SharedUtil {
    public static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

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
