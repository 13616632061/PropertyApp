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

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.router.RouterMapping;

/**
 * Created by Administrator on 2016/11/22.
 * 收藏文字
 */
public class ShouCangPopuWindow extends PopupWindow implements View.OnClickListener {
    TextView copy, shoucang;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private int position = 0; //位置
    private Handler del_handler;
    private Bundle bundle;

    public ShouCangPopuWindow(final Context context, Handler del_handler) {//, int position,
        super(context);
        this.context = context;
//        this.position = position;
        this.del_handler = del_handler;
        this.bundle = bundle;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popwindow_shoucang, null);
        copy = (TextView) mMenuView.findViewById(R.id.copy);
        shoucang = (TextView) mMenuView.findViewById(R.id.shoucang);
        copy.setOnClickListener(this);
        shoucang.setOnClickListener(this);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
//        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
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
            case R.id.shoucang:
                if (Database.USER_MAP==null){
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(context);
                }else {
                    Message msg = new Message();
                    msg.what = 11;
                    del_handler.sendMessage(msg);

                    ShouCangPopuWindow.this.dismiss();
                }
                break;
            case R.id.copy:
                Message msg2 = new Message();
                msg2.what = 12;
                del_handler.sendMessage(msg2);

                ShouCangPopuWindow.this.dismiss();
                break;
            default:
                break;
        }
    }


}
