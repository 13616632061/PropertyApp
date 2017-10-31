package com.glory.bianyitong.widght.update.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.update.utils.SPUtils;
import com.glory.bianyitong.widght.update.utils.UPVersion;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 时间: 2016/11/18
 * 功能描述: OkHttp + notification 下载的服务类
 */
public class DownloadService extends Service {
//    private static final String DOWN_APK_URL = UPVersion.url;
    private static final String DOWN_APK_URL = "http://szbianyitong.oss-cn-shenzhen.aliyuncs.com/Download/apk/byt1.0.0.apk";

    private static final String TAG = DownloadService.class.getSimpleName();
    private static final String APK_NAME = "bianyitong.apk";
    private Boolean autoDownLoad;
    private int currentProgress = 0;

    private NotificationManager manager;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //显示 Notification
                    showNotificationProgress(msg.arg1);
                    break;
                case 2:
                    //安装apk
                    installApk();
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //hongyang大神封装好的okHttp网络请求
        //启动分线程下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                okHttpDownLoadApk(Database.DOWN_APK_URL);
            }
        }).start();
    }


    /**
     * 联网下载最新版本apk
     */
    private void okHttpDownLoadApk(final String url) {
        OkGo.get(url).tag(this)
                .execute(new FileCallback(Environment.getExternalStorageDirectory().getAbsolutePath(), APK_NAME) {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        Log.i("resultString", "请求错误------");
                        ToastUtils.showToast(Database.currentActivity, getString(R.string.failed_to_connect_to_server));
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                        //Log.e(TAG, "inProgress() 当前线程 == " + Thread.currentThread().getName());
                        autoDownLoad = (Boolean) SPUtils.get(DownloadService.this, SPUtils.WIFI_DOWNLOAD_SWITCH, false);
                        //判断开关状态 开 则静默下载
                        if (autoDownLoad) {
                            //说明自动更新 这里服务在后台默默运行下载 只能看日志了
                            //Log.e(TAG, "自动安装的进度 == " + (100 * progress));

                            if ((100 * progress) == 100.0) {
                                //Log.e(TAG, "网络请求 自动安装 当前线程 == " + Thread.currentThread().getName());

                                handler.sendEmptyMessage(2);
                            }

                        } else {//否则 进度条下载
                            int pro = (int) (100 * progress);
                            //解决pro进度重复传递 progress的问题 这里解决UI界面卡顿问题
                            if (currentProgress < pro && pro <= 100) {
                                //Log.e(TAG, "进入");

                                currentProgress = pro;

                                Message msg = new Message();
                                msg.what = 1;
                                msg.arg1 = currentProgress;
                                handler.sendMessage(msg);
                            }

                        }
                    }
                });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotificationProgress(int progress) {
        //Log.e(TAG, "进度 == " + progress);
        String message = getString(R.string.current_download_progress)+": " + progress + "%";
        int AllProgress = 100;


        Intent intent = null;
        Notification notification=null;
        Notification.Builder builder = new Notification.Builder(this);

        builder.setSmallIcon(R.drawable.logo_5);//App小的图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_5));//App大图标
        builder.setContentTitle(getString(R.string.bianyitong_to_update));//设置通知的信息
        builder.setWhen(System.currentTimeMillis());
        builder.setContentText(message);
        builder.setAutoCancel(false);//用户点击后自动删除
                //.setDefaults(Notification.DEFAULT_LIGHTS)//灯光
        builder.setPriority(Notification.PRIORITY_DEFAULT);//设置优先级
        builder.setOngoing(true);
        builder.setProgress(AllProgress, progress, false); //AllProgress最大进度 //progress 当前进度
        notification=builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);
        if (progress == 100) {
            message = getString(R.string.after_downloading_click_install);
            //Log.e(TAG, "下载完成 " + progress);

            //安装apk
            installApk();
//            if (manager != null) {
//                manager.cancel(0);//下载完毕 移除通知栏
//            }

            //当进度为100%时 传入安装apk的intent
            File fileLocation = new File(Environment.getExternalStorageDirectory(), APK_NAME);
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");


            //表示返回的PendingIntent仅能执行一次，执行完后自动取消
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pendingIntent);
            notification=builder.build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            manager.notify(0, notification);

        }


    }

    /**
     * 安装apk
     */
    private void installApk() {
        //Environment.getExternalStorageDirectory() 保存的路径
        Log.e(TAG, "installApk运行了");

        File fileLocation = new File(Environment.getExternalStorageDirectory(), APK_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
        startActivity(intent);

        //停止服务
        stopSelf();
    }

    @Override
    public void onDestroy() {
        //Log.e(TAG, "onDestroy()");
        super.onDestroy();

        /*if (manager != null) {
            manager.cancel(0);//下载完毕 移除通知栏
        }*/
    }
}
