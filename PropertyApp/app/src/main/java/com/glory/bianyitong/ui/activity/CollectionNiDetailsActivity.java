package com.glory.bianyitong.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.CollectionNiInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ShouCangPopuWindow;
import com.glory.bianyitong.widght.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/10/30.
 * 我的近邻收藏详情
 */
public class CollectionNiDetailsActivity extends BaseActivity implements View.OnLongClickListener {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @BindView(R.id.iv_title_text_left)
    TextView ivTitleTextLeft;
    @BindView(R.id.title_ac_text)
    TextView titleAcText;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @BindView(R.id.dynamic_user_head)
    CircleImageView dynamicUserHead;
    @BindView(R.id.dynamic_user_name)
    TextView dynamicUserName;
    @BindView(R.id.dynamic_right_data)
    TextView dynamicRightData;
    @BindView(R.id.dynamic_tv_content)
    TextView dynamicTvContent;
    @BindView(R.id.collection_pic)
    ImageView collectionPic;
    @BindView(R.id.llear)
    LinearLayout llear;
    private int x;
    private int y;

    Handler rhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if (msg.what == 12) {//复制
                ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(dynamicTvContent.getText().toString().trim()); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_collectionnidateils;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("详情", true, "");//详情
        initview();

        dynamicTvContent.setOnLongClickListener(this);

    }

    private void initview() {

        Glide.with(this).load(getIntent().getStringExtra("collectUserPicture")).error(R.drawable.wait).placeholder(R.drawable.wait).into(dynamicUserHead);
        dynamicUserName.setText(getIntent().getStringExtra("collectUserName"));
        dynamicRightData.setText(getIntent().getStringExtra("createDate").replace("T"," "));
        if (getIntent().getIntExtra("collectType",0)==1){//1文字，2图片，3视频
            dynamicTvContent.setText(getIntent().getStringExtra("collectContent"));
            collectionPic.setVisibility(View.GONE);
        }else if (getIntent().getIntExtra("collectType",0)==2){
            dynamicTvContent.setVisibility(View.GONE);
            Glide.with(this).load(getIntent().getStringExtra("collectContent")).error(R.drawable.wait).placeholder(R.drawable.wait).into(collectionPic);
        }
        dynamicTvContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getX();
                y = (int) event.getY();
                return false;
            }
        });

    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2,R.id.collection_pic})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.collection_pic:
                ArrayList<String> pictureList=new ArrayList<>();
                pictureList.add(getIntent().getStringExtra("collectContent"));
                Intent intent = new Intent();
                intent.setClass(CollectionNiDetailsActivity.this, ImagePagerActivity.class);
                intent.putExtra("pictureList", pictureList);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onLongClick(View v) {
        dynamicTvContent.setBackgroundColor(getResources().getColor(R.color.gray));
        int[] location = new int[2];
        dynamicTvContent.getLocationOnScreen(location);
        ShouCangPopuWindow shouCangPopuWindow = new ShouCangPopuWindow(this, rhandler,false);//, msg.arg1, del_handler
        //获取PopWindow宽和高

        // 显示窗口
        shouCangPopuWindow.showAtLocation(dynamicTvContent,
                Gravity.NO_GRAVITY, x - 50, y - getScrollY() +80); // 设置layout在PopupWindow中显示的位置
        shouCangPopuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dynamicTvContent.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        return false;
    }

    public int getScrollY() {
        View c = dynamicTvContent;
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = dynamicTvContent.getVerticalScrollbarPosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }
}
