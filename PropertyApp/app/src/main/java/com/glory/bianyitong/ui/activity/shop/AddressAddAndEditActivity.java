package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestEditAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2016/11/22.
 * 编辑添加收货地址
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_ADD, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddressAddAndEditActivity extends BaseActivity {

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


    @InjectParam(key = "data")
    ResponseQueryAddress.ListShippingAddressBean listShippingAddressBean;

    @Override
    protected int getContentId() {
        return R.layout.activity_address_add;
    }


    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.iv_title_text_right})
    void onClickBtn(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.iv_title_text_right://保存数据
                checkDataFormat();
                break;
        }
    }

    private void checkDataFormat() {
        String name=addressAddReceiver.getText().toString().trim();
        String phone=addressAddPhone.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            showShort("收货人名称不能为空");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            showShort("收货人手机号不能为空");
            return;
        }

        if(name.equals(listShippingAddressBean.getHarvesterName()) && phone.equals(listShippingAddressBean.getHarvestePhone())){
            finish();
        }else {
            editInfo(listShippingAddressBean.getAddressID(),name,phone);
        }

    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle(getResources().getString(R.string.modify_authorization), false, getResources().getString(R.string.carry_out));//修改授权   完成


        initView();
    }

    private void initView() {
        addressAddReceiver.setText(listShippingAddressBean.getHarvesterName());
        addressAddPhone.setText(listShippingAddressBean.getHarvestePhone());
        addressAddProvince.setText(listShippingAddressBean.getFreshCabinet().getProvinceName()+listShippingAddressBean.getFreshCabinet().getCityName()+listShippingAddressBean.getFreshCabinet().getDistrictName());
        addressAddArea.setText(listShippingAddressBean.getFreshCabinet().getStreetAddress());

    }

    private void editInfo(int addressId,String name,String phone){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress",new RequestEditAddress(addressId,name,phone));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    finish();
                }
                showShort(bean.getAlertMessage());
            }

            @Override
            public void onError() {
                showShort(getString(R.string.system_error));
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
        }).getEntityData(HttpURL.HTTP_POST_INIT_ADDRESS,json);
    }


}
