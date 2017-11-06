package com.glory.bianyitong.ui.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.dialog.CollectionPopuWindow;
import com.glory.bianyitong.ui.dialog.ReportPopuWindow;
import com.glory.bianyitong.view.ViewPagerFixed;
import com.glory.bianyitong.widght.photoViewUtil.PhotoView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class ImagePagerActivity extends BaseActivity {

    private TextView left_return_btn;
    private ViewPagerFixed adViewPager; //自定义 viewpager 防止放大缩小闪退
    private List<View> pageViews;
    private ImageView[] imageViews;
    private ImageView imageView2;
    private AdPageAdapter adapter;
    private ArrayList<String> pictureList = null;

    Handler rhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                shoucang();
            }
            if (msg.what==11){
                Glide.with(ImagePagerActivity.this).load(pictureList.get(mPosition)).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        saveImageToGallery(ImagePagerActivity.this,resource);
                    }
                });
//                download(pictureList.get(mPosition));
            }
        }
    };
    private ImageView more;
    private int mPosition;


    @Override
    protected int getContentId() {
        return R.layout.ac_image_pager;
    }

    @Override
    protected void init() {
        super.init();
        if (getIntent().getStringArrayListExtra("pictureList") != null && !getIntent().getStringArrayListExtra("pictureList").equals("")) {
            pictureList = getIntent().getStringArrayListExtra("pictureList");
        }


        left_return_btn = (TextView) findViewById(R.id.left_return_btn);
        more = (ImageView) findViewById(R.id.more);
        adViewPager = (ViewPagerFixed) findViewById(R.id.viewpager2);
        if (getIntent().getStringExtra("aesUserID")==null){
            more.setVisibility(View.GONE);
        }
        initPageAdapter();
//        initCirclePoint();
        adViewPager.setAdapter(adapter);
        adViewPager.setOnPageChangeListener(new OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionPopuWindow collectionPopuWindow = new CollectionPopuWindow(ImagePagerActivity.this, rhandler);//, msg.arg1, del_handler
                // 显示窗口
                collectionPopuWindow.showAtLocation(ImagePagerActivity.this.findViewById(R.id.lay_dynamic_commment),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
            }
        });

        left_return_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//				ServiceDialog.ButtonClickZoomInAnimation(left_return_btn, 0.95f);
                ImagePagerActivity.this.finish();
            }
        });
    }
    /**
     * 收藏
     */
    private void shoucang() { //收藏
        try {
            Map<String,Object> map=new BaseRequestBean().getBaseRequest();
            Map<String,Object> map2=new HashMap<>();
            map2.put("collectType",2);
            map2.put("aesUserID",getIntent().getStringExtra("aesUserID"));
            map2.put("collectContent",pictureList.get(mPosition));
            map.put("neighborhoodCollect",map2);
            String json=new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    ResponseFriendDetail detail=new Gson().fromJson(s,ResponseFriendDetail.class);
                    showShort(detail.getAlertMessage());

                }

                @Override
                public void onError() {

                }
                @Override
                public void parseError() {}
                @Override
                public void onBefore() { }
                @Override
                public void onAfter() {
                }
            }).getEntityData(this, HttpURL.HTTP_POST_FRIEND_APINEIGHCOLLECTION_ADD,json);
        }catch (Exception e){

        }
    }
    private void initPageAdapter() {
        pageViews = new ArrayList<View>();
        if (pictureList != null && pictureList.size() != 0 && !pictureList.equals("")) {
            for (int i = 0; i < pictureList.size(); i++) {
                PhotoView imageView = new PhotoView(this);
                imageView.setScaleType(ScaleType.FIT_XY);// 按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示,但是没截图原因不清楚
                setPicture(pictureList.get(i), imageView, ScaleType.FIT_CENTER);// 设置显示图片
                pageViews.add(imageView);
            }
        }
        adapter = new AdPageAdapter(pageViews);
    }

    private void initCirclePoint() {
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup_lay);
        imageViews = new ImageView[pageViews.size()];
        // 广告栏的小圆点图标
        for (int i = 0; i < pageViews.size(); i++) {
            // 创建一个ImageView, 并设置宽高. 将该对象放入到数组中
            imageView2 = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LayoutParams(20, 20));
            layoutParams.setMargins(10, 0, 10, 0);
            imageView2.setLayoutParams(layoutParams);
            imageViews[i] = imageView2;

            // 初始值, 默认第0个选中
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.point_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
            }
            // 将小圆点放入到布局中
            group.addView(imageViews[i]);
        }
    }

    /**
     * 设置显示图片
     */
    private void setPicture(final String pic, final ImageView imageView, final ScaleType scaleType) {
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
            views.get(position).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.v("sadas","sadawwww");

                    // intent.setClass(GoodsDetailActivity.this, ImagePagerActivity.class);
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
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
            // 重新设置原点布局集合
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0].setBackgroundResource(R.drawable.point_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
                }
            }
        }
    }

    // 保存图片到手机指定目录
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
        Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
    }

}
