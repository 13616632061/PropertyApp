package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.entity.response.ResponseSubmitOrder;
import com.glory.bianyitong.router.RouterMapping;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 * 支付订单页面
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_PAY, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PayActivity extends BaseActivity {
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
    @BindView(R.id.order_pay_number)
    TextView orderPayNumber;
    @BindView(R.id.order_pay_money)
    TextView orderPayMoney;
    @BindView(R.id.order_pay_group_wx)
    RadioButton orderPayGroupWx;
    @BindView(R.id.order_pay_group_apay)
    RadioButton orderPayGroupApay;
    @BindView(R.id.order_pay_group)
    RadioGroup orderPayGroup;


    @InjectParam(key = "orderId")
    long orderId;

    @InjectParam(key = "price")
    float price;

    @Override
    protected int getContentId() {
        return R.layout.activity_order_play;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("收银台",false,"");
        if(orderId>0)
            initView();

    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickGroup(View view){
        switch (view.getId()){
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
        }
    }
    private void initView(){
        orderPayNumber.setText(orderId+"");
        orderPayMoney.setText("¥ "+price);
    }


}
