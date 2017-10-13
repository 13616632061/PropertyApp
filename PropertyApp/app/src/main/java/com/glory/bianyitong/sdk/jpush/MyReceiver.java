package com.glory.bianyitong.sdk.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.glory.bianyitong.ui.activity.BillDetailsActivity;
import com.glory.bianyitong.ui.activity.BulletinDetailsActivity;
import com.glory.bianyitong.ui.activity.MainActivity;
import com.glory.bianyitong.ui.activity.MessageActivity;
import com.glory.bianyitong.ui.activity.MessageDetailsActivity;
import com.glory.bianyitong.ui.activity.PickupActivity;
import com.glory.bianyitong.ui.activity.WelcomeActivity;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.JsonHelper;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            //标题 内容
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    //标题
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

            processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");

            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//            String type = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String type2 = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Log.i("resultString","type----===="+type);
//            Log.i("resultString","type2----===="+type2);

            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            String type2 = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i("resultString", "type2----====" + type2);
            //公告(typeid为1) 系统消息(typeid为2)
            HashMap<String, String > hashMap2 = JsonHelper.fromJson(type2, new TypeToken<HashMap<String, String>>() {
            });
            Log.i("extmas",hashMap2.get("extMsg")+"--------");
            if (hashMap2.size()>0){
                switch (hashMap2.get("extMsg")){
                    case "1":
                        Intent intent1 = new Intent(context, BulletinDetailsActivity.class);
                        try {
                            intent1.putExtra("bulletinId",Integer.parseInt(hashMap2.get("extId")));
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent1);
                        }catch (Exception e){
                            Intent intent0= new Intent(context, WelcomeActivity.class);
                            intent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent0);
                        }

                        break;
                    case "2":
                        Intent intent2 = new Intent(context, MessageActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent2);
                        break;
                    case "3":
                        Intent intent3 = new Intent(context, MessageActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent3);
                        break;
                    case "4":
                        Intent intent4 = new Intent(context, PickupActivity.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent4);
                        break;
                    case "6":
                        Intent intent6 = new Intent(context, BillDetailsActivity.class);
                        try {
                        intent6.putExtra("userBillID",Integer.parseInt(hashMap2.get("extId")));
                        intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent6);
                        }catch (Exception e){
                            Intent intent0= new Intent(context, WelcomeActivity.class);
                            intent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent0);
                        }
                        break;

            }

            }else {
                Intent intent0= new Intent(context, WelcomeActivity.class);
                intent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent0);
            }



//            boolean isrun = ActivityUtils.isRunningForeground();
//            if (hashMap2.get("TypeID") != null && hashMap2.get("PushID") != null) {
//                int TypeID = Double.valueOf(hashMap2.get("TypeID").toString()).intValue();
//                int PushID = Double.valueOf(hashMap2.get("PushID").toString()).intValue();
//                //打开自定义的Activity
//                if(isrun){
//                    if (TypeID == 1) {
//                        Intent i = new Intent(context, BulletinDetailsActivity.class);
//                        i.putExtra("PushID", PushID);
//                        i.putExtras(bundle);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(i);
//                    } else if (TypeID == 2) {
//                        Intent i2 = new Intent(context, MessageDetailsActivity.class);
//                        i2.putExtra("PushID", PushID);
//                        i2.putExtras(bundle);
//                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(i2);
//                    }
//                }else {
//                    if (TypeID == 1) {
//                        Intent i3 = new Intent(context, WelcomeActivity.class);
//                        i3.putExtra("TypeID", 1);
//                        i3.putExtra("PushID", PushID);
//                        i3.putExtras(bundle);
//                        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(i3);
//                    } else if (TypeID == 2) {
//                        Intent i4 = new Intent(context, WelcomeActivity.class);
//                        i4.putExtra("TypeID", 2);
//                        i4.putExtra("PushID", PushID);
//                        i4.putExtras(bundle);
//                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(i4);
//                    }
//                }
//            }

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        if (MainActivity.isForeground) {
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
            if (!ExampleUtil.isEmpty(extras)) {
                try {
                    JSONObject extraJson = new JSONObject(extras);
                    if (null != extraJson && extraJson.length() > 0) {
                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
                    }
                } catch (JSONException e) {

                }

            }
            context.sendBroadcast(msgIntent);
        }
    }
}
