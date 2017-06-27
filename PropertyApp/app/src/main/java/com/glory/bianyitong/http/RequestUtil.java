package com.glory.bianyitong.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyj on 2016/12/15.
 */
public class RequestUtil {

    public static String getToken(){
        String token="";
        if (Database.USER_MAP != null && Database.accessToken != null) {
            token = Database.accessToken;
        }
        return token;
    }
    /**
     * TODO 获取userID
     * 获取userID
     *
     * @return
     */
    public static String getuserid() {
        String userID = "";
//        if (Database.USER_MAP != null) {
//            userID = Database.USER_MAP.get("userID").toString();
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getUserID() != null) {
            userID = Database.USER_MAP.getUserID();
        }
        return userID;
    }

    /**
     * TODO 获取communityID
     * 获取communityID
     *
     * @return
     */
    public static int getcommunityid() {
        int communityID = 0;
//        String communityName = "";//
//        if (Database.my_community != null && Database.my_community.get("communityName") != null) {
//            communityName = Database.my_community.get("communityName").toString();
//        }
        if (Database.my_community != null && Database.my_community.getCommunityID() != 0) {
            communityID = Database.my_community.getCommunityID();
        }
        return communityID;
    }

    /**
     * TODO 获取当前时间
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间
        return nowdate;
    }

    /**
     * TODO 获取应用版本号
     * 获取当前应用的版本号
     *
     * @return
     */
    public static String getVersion(Context context) {
        try {
            if (context != null) {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                String version = info.versionName;
                return version;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1 + "";
    }

    /**
     * TODO 请求数据包头封装
     * 请求数据包头封装
     *
     * @param context
     * @param query
     * @return
     */
    public static String getJson(Context context, String query) {
        String json = "{\"timeStemp\": \"" + getCurrentTime() + "\"," +
                "\"accessToken\": \"5488945897fdg89\"," +
                "\"version\": \"" + getVersion(context) + "\"," +
                "\"deviceType\": 3," +
                "\"currentPageNumber\": 0," +
                "\"pageRowNumber\": 0," +
                "\"controllerName\": \"ApiIndex\"," +
                "\"actionName\": \"Query\"," +
                "\"userID\":\"" + getuserid() + "\"," +
                "\"communityID\": 0," + query + "}";
        return json;
    }

    /**
     * TODO 仅含包头
     *
     * @param context
     * @return
     */
    public static String getEmptyParameter(Context context) {
        String json = "{" + "\"userid\":" + "\"" + getuserid() + "\","
                + "\"groupid\":" + "\"\","
                + "\"accesstoken\":" + "\"\","
                + "\"version\":" + "\"" + getVersion(context) + "\","
                + "\"datetime\":" + "\"" + getCurrentTime() + "\","
                + "\"messagetoken\":" + "\"\","
                + "\"DeviceType\":" + "\"4\""
                + "}";
        return json;
    }

    /**
     * TODO 意见反馈
     *
     * @param context
     * @param feedbackcontext
     * @return
     */
    public static String getOpinion(Context context, String feedbackcontext) {
        String json = "\"feedback\":" + "{" + "\"feedbackcontext\":" + "\"" + feedbackcontext + "\","
                + "\"feedbackdatetime\":" + "\"" + getCurrentTime() + "\"" + "}";
        return getJson(context, json);
    }

    /**
     * TODO 万能请求url
     */
    public static String getRequestJson(Context context, String json) {
        String requestJson = json.substring(1, json.length() - 1);
        return getJson(context, requestJson);
    }
}
