package com.glory.bianyitong.ui.activity.shop;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.router.RouterMapping;

/**
 * Created by Administrator on 2017/7/12.
 * 提交订单
 */

@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_FIRM,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class FirmOrderActivity extends BaseActivity {
    @Override
    protected int getContentId() {
        return R.layout.activity_firm_order;
    }

    @Override
    protected void init() {
        super.init();
    }
}
