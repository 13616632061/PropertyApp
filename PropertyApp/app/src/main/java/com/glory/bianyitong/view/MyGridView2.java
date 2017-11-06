package com.glory.bianyitong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by lucy on 2017/1/5.
 */
public class MyGridView2 extends GridView{


    //重新  ScrollView中嵌套GridView，ListView只显示一行的解决办法
    public MyGridView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView2(Context context) {
        super(context);
    }

    public MyGridView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
