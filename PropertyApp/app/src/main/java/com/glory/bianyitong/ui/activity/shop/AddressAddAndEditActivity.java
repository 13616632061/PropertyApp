package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.router.RouterMapping;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/22.
 * 编辑添加收货地址
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_ADD, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddressAddAndEditActivity extends BaseActivity {

    // TODO: 2017/7/12 标识新增或编辑  0：新增  1：编辑
    @InjectParam(key = "type")
    int type;
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
    @BindView(R.id.address_add_receiver)
    EditText addressAddReceiver;
    @BindView(R.id.address_add_lin2)
    TextView addressAddLin2;
    @BindView(R.id.address_add_phone)
    EditText addressAddPhone;
    @BindView(R.id.address_add_province)
    TextView addressAddProvince;
    @BindView(R.id.address_add_area)
    TextView addressAddArea;
    @BindView(R.id.address_add_address)
    TextView addressAddAddress;
    @BindView(R.id.address_add_delete)
    RelativeLayout addressAddDelete;

    @Override
    protected int getContentId() {
        return R.layout.activity_address_add;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        if (type == 0) {
            inintTitle(getResources().getString(R.string.add_authorization), false, getResources().getString(R.string.carry_out));//添加授权   完成
        } else if (type == 1) {
            inintTitle(getResources().getString(R.string.modify_authorization), false, getResources().getString(R.string.carry_out));//修改授权   完成
        }




    }


}
