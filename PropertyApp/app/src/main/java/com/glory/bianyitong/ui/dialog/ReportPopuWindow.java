package com.glory.bianyitong.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.glory.bianyitong.R;

/**
 * Created by Administrator on 2016/11/22.
 * 举报选项
 */
public class ReportPopuWindow extends PopupWindow implements View.OnClickListener {
    TextView news_delete, new_cancel;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private int position = 0; //位置
    private Handler del_handler;
    private Bundle bundle;

    public ReportPopuWindow(final Context context,Handler del_handler, Bundle bundle) {//, int position,
        super(context);
        this.context = context;
//        this.position = position;
        this.del_handler = del_handler;
        this.bundle = bundle;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_news_delete, null);
        news_delete = (TextView) mMenuView.findViewById(R.id.news_delete);
        new_cancel = (TextView) mMenuView.findViewById(R.id.new_cancel);

        news_delete.setText(context.getString(R.string.report));//举报
        news_delete.setOnClickListener(this);
        new_cancel.setOnClickListener(this);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout2).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public ReportPopuWindow(final Context context, final Handler del_handler, final Bundle bundle, int delete) {//, int position,
        super(context);
        this.context = context;
//        this.position = position;
        this.del_handler = del_handler;
        this.bundle = bundle;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_news_delete, null);
        news_delete = (TextView) mMenuView.findViewById(R.id.news_delete);
        new_cancel = (TextView) mMenuView.findViewById(R.id.new_cancel);

        news_delete.setText(context.getString(R.string.delete));//删除
        if (delete==1){
            news_delete.setOnClickListener(this);
        }else {
            news_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 9;
                    msg.setData(bundle);
                    del_handler.sendMessage(msg);

                    ReportPopuWindow.this.dismiss();
                }
            });
        }
        new_cancel.setOnClickListener(this);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout2).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.news_delete: //举报
                Message msg = new Message();
                msg.what = 10;
                msg.setData(bundle);
                del_handler.sendMessage(msg);

                ReportPopuWindow.this.dismiss();
                break;
            case R.id.new_cancel: //取消
                ReportPopuWindow.this.dismiss();

                break;
            default:
                break;
        }
    }


}
