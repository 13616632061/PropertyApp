package com.glory.bianyitong.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.activity.PickupActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/11/22.
 * 分享
 */
public class ShareSdkDialog extends PopupWindow implements View.OnClickListener {
    TextView tv_friendscycle, tv_wxfriend, tv_share_cancel;
    private Context context;
    private View mMenuView;
    private LayoutInflater inflater;
    private Handler handler;

    public ShareSdkDialog(final Context context, Handler handler) {
        super(context);
        this.context = context;
        this.handler = handler;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_sharesdk, null);
        tv_friendscycle = (TextView) mMenuView.findViewById(R.id.tv_friendscycle);
        tv_wxfriend = (TextView) mMenuView.findViewById(R.id.tv_wxfriend);
        tv_share_cancel = (TextView) mMenuView.findViewById(R.id.tv_share_cancel);

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow_Share);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x70000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.share_lay).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        Platform[] platforms = ShareSDK.getPlatformList();
//        tv_friendscycle.setText(platforms[1].getName());
        tv_friendscycle.setText(context.getString(R.string.wechat_friends_circle));//微信朋友圈
        tv_friendscycle.setTag(platforms[1]);

//        tv_wxfriend.setText(platforms[0].getName());
        tv_wxfriend.setText(context.getString(R.string.wechat_friends));//微信好友
        tv_wxfriend.setTag(platforms[0]);
        tv_friendscycle.setOnClickListener(this);
        tv_wxfriend.setOnClickListener(this);
        tv_share_cancel.setOnClickListener(this);
    }
    //只显示分享到朋友
    public ShareSdkDialog(final Context context, Handler handler,boolean onlyOne) {
        super(context);
        this.context = context;
        this.handler = handler;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.dialog_sharesdk, null);
        tv_friendscycle = (TextView) mMenuView.findViewById(R.id.tv_friendscycle);
        tv_wxfriend = (TextView) mMenuView.findViewById(R.id.tv_wxfriend);
        tv_share_cancel = (TextView) mMenuView.findViewById(R.id.tv_share_cancel);
        if (onlyOne){
            tv_friendscycle.setVisibility(View.GONE);
        }

        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationWindow_Share);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x70000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.share_lay).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        Platform[] platforms = ShareSDK.getPlatformList();
//        tv_friendscycle.setText(platforms[1].getName());
        tv_friendscycle.setText(context.getString(R.string.wechat_friends_circle));//微信朋友圈
        tv_friendscycle.setTag(platforms[1]);

//        tv_wxfriend.setText(platforms[0].getName());
        tv_wxfriend.setText(context.getString(R.string.wechat_friends));//微信好友
        tv_wxfriend.setTag(platforms[0]);
        tv_friendscycle.setOnClickListener(this);
        tv_wxfriend.setOnClickListener(this);
        tv_share_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tv_friendscycle: //微信朋友圈
                Object tag = v.getTag();
                if (tag != null) {
                    ShareSdkDialog.this.dismiss();
                    Platform platform = (Platform) tag;
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = platform.getName();
                    handler.sendMessage(msg);
                }
                break;
            case R.id.tv_wxfriend: //微信好友
                Object tag2 = v.getTag();
                if (tag2 != null) {
                    ShareSdkDialog.this.dismiss();
                    Platform platform = (Platform) tag2;
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = platform.getName();
                    handler.sendMessage(msg);
                }
                break;
            case R.id.tv_share_cancel: //
                ShareSdkDialog.this.dismiss();
                break;
            default:
                break;
        }
    }


}
