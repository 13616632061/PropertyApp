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
 * Created by Administrator on 2017/10/22.
 * 收藏图片
 */
public class CollectionPopuWindow extends PopupWindow implements View.OnClickListener {
    TextView collection_pic, save_pic,new_cancel;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private int position = 0; //位置
    private Handler del_handler;
    private Bundle bundle;

    public CollectionPopuWindow(final Context context, Handler del_handler) {//, int position,
        super(context);
        this.context = context;
//        this.position = position;
        this.del_handler = del_handler;
        this.bundle = bundle;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_collection_pic, null);
        collection_pic = (TextView) mMenuView.findViewById(R.id.collection_pic);
        save_pic = (TextView) mMenuView.findViewById(R.id.save_pic);
        new_cancel = (TextView) mMenuView.findViewById(R.id.new_cancel);

        collection_pic.setOnClickListener(this);
        save_pic.setOnClickListener(this);
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
            case R.id.collection_pic: //收藏
                Message msg = new Message();
                msg.what = 10;
                del_handler.sendMessage(msg);
                this.dismiss();
                break;
            case R.id.save_pic: //保存图片
                Message msg1 = new Message();
                msg1.what = 11;
                del_handler.sendMessage(msg1);
                this.dismiss();
                break;
            case R.id.new_cancel: //取消
                CollectionPopuWindow.this.dismiss();

                break;
            default:
                break;
        }
    }


}
