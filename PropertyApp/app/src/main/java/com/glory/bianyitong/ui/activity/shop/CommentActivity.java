package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.router.RouterMapping;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 * 订单评论页面
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_COMMENT, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class CommentActivity extends BaseActivity  {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;


    @InjectParam(key = "data")
    ResponseQueryOrderList.ListOrderBean bean;
    @BindView(R.id.comment_list)
    RecyclerView commentList;


    @Override
    protected int getContentId() {
        return R.layout.activity_order_comment;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("评价", false, "发布");
        ivTitleTextRight.setTextColor(ContextCompat.getColor(this, R.color.golden));
        if (bean != null) {
            initView();
        }

    }

    private void initView() {
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right})
    void onClickGroup(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_text_right://发布
                break;
        }
    }


}
