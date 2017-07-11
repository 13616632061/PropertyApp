package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddressActivity extends BaseActivity {

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
    @BindView(R.id.address_lin2)
    LinearLayout addressLin2;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.lay_shopping_car)
    LinearLayout layShoppingCar;

    @Override
    protected int getContentId() {
        return R.layout.activity_address;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("管理收货地址",false,"");
        queryAddress();
    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.btn_add})
    void onClickBtn(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.btn_add:
                showShort("添加地址");
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_ADD)
                        .go(this);
                break;
        }
    }
    private void queryAddress() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress", new Object());
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void parseError() {

            }

            @Override
            public void onBefore() {

            }

            @Override
            public void onAfter() {

            }
        }).getEntityData(HttpURL.HTTP_POST_QUERY_ADDRESS, json);

    }

}
