package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/29.
 */
public class Welcome2Activity extends BaseActivity {
    ViewPager viewPager;// 页卡内容
    List<View> pageViews;
    AdPageAdapter adapter;
    ImageView imageView;


    @Override
    protected int getContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        super.init();

        imageView = (ImageView) findViewById(R.id.sbsbsb);
        viewPager = (ViewPager) findViewById(R.id.viewPager1);
        initPageAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new AdPageChangeListener());
    }





    private void initPageAdapter() {
        pageViews = new ArrayList<View>();
        int pic[] = {R.drawable.no_1, R.drawable.no_2, R.drawable.no_3, R.drawable.no_4,R.drawable.no_5};
        for (int i = 0; i < pic.length; i++) {

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundDrawable(getResources().getDrawable(pic[i]));
            pageViews.add(imageView);

        }

        adapter = new AdPageAdapter(pageViews);
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
            views.get(position).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (position<views.size()-1){
                        viewPager.setCurrentItem(position+1);
                    }else {
                        finish();
                    }
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
            if (arg0 == viewPager.getAdapter().getCount()-1) {// 滑动到最后一页
            }
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {

        }
    }
}
