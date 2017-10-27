package com.glory.bianyitong.ui.activity;

import java.util.ArrayList;
import java.util.List;


import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.view.ViewPagerFixed;
import com.glory.bianyitong.widght.photoViewUtil.PhotoView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

public class ImagePagerActivity extends BaseActivity {

    private RelativeLayout left_return_btn;
    private ViewPagerFixed adViewPager; //自定义 viewpager 防止放大缩小闪退
    private List<View> pageViews;
    private ImageView[] imageViews;
    private ImageView imageView2;
    private AdPageAdapter adapter;
    private ArrayList<String> pictureList = null;

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

        left_return_btn = (RelativeLayout) findViewById(R.id.left_return_btn);
        adViewPager = (ViewPagerFixed) findViewById(R.id.viewpager2);

        initPageAdapter();
        initCirclePoint();
        adViewPager.setAdapter(adapter);
        adViewPager.setOnPageChangeListener(new AdPageChangeListener());


        left_return_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//				ServiceDialog.ButtonClickZoomInAnimation(left_return_btn, 0.95f);
                ImagePagerActivity.this.finish();
            }
        });
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

}
