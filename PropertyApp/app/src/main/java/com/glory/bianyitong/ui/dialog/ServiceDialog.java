package com.glory.bianyitong.ui.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.util.ToastUtils;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


import java.io.File;

@SuppressLint("SdCardPath")
public class ServiceDialog {

    /**
     * @功能描述 : 判断是否安装了应用
     */
    public static boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * @功能描述 : 调用按钮点击放大缩小动画效果Button click zoom in animation effects
     */
    public static void ButtonClickZoomInAnimation(View view_lay, float numerical) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view_lay, "scaleX", numerical, 1f),
                ObjectAnimator.ofFloat(view_lay, "scaleY", numerical, 1f));
        set.setDuration(1 * 100).start();
    }

    /**
     * 设置显示图片
     */
    public static void setPicture(final String pic, final ImageView imageView, final ScaleType scaleType) {
        try {
            MyApplication.getInstance().imageLoader.displayImage(pic, imageView,
                    MyApplication.getInstance().options, new ImageLoadingListener() {

                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            // TODO Auto-generated method stub
                            ImageView imageView = (ImageView) view;
                            if (scaleType != null && imageView != null) {
                                imageView.setScaleType(scaleType);
                            }
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            // TODO Auto-generated method stub
                            ImageView imageView = (ImageView) view;
                            if (scaleType != null && imageView != null) {
                                imageView.setScaleType(scaleType);
                            }
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            // TODO Auto-generated method stub
                            ImageView imageView = (ImageView) view;
                            if (scaleType != null && imageView != null) {
                                imageView.setAdjustViewBounds(true);
                                imageView.setScaleType(scaleType);
                            }
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            // TODO Auto-generated method stub
                            ImageView imageView = (ImageView) view;
                            if (scaleType != null && imageView != null) {
                                imageView.setAdjustViewBounds(false);
                                imageView.setScaleType(scaleType);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showRequestFailed(){
        ToastUtils.showToast(Database.currentActivity, "未能连接到服务器");
    }

}
