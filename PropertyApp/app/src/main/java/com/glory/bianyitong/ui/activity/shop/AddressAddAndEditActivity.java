package com.glory.bianyitong.ui.activity.shop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.glory.bianyitong.bean.entity.request.RequestDeleteAddress;
import com.glory.bianyitong.bean.entity.request.RequestEditAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.ContactListActivity;
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
    @BindView(R.id.rl_linkman)
    RelativeLayout rlLinkman;

    @Override
    protected int getContentId() {
        return R.layout.activity_address_add;
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right,R.id.rl_linkman,R.id.address_add_delete})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.iv_title_text_right://保存数据
                checkDataFormat();
                break;
            case R.id.rl_linkman:
                starttxl();
                break;
            case R.id.address_add_delete:
                deleteAddress(listShippingAddressBean.getAddressID());
                break;
        }
    }

    private void checkDataFormat() {
        String name = addressAddReceiver.getText().toString().trim();
        addressAddReceiver.setSelection(addressAddReceiver.getText().length());
        String phone = addressAddPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showShort("收货人名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showShort("收货人手机号不能为空");
            return;
        }

        if (name.equals(listShippingAddressBean.getHarvesterName()) && phone.equals(listShippingAddressBean.getHarvestePhone())) {
            finish();
        } else {
            editInfo(listShippingAddressBean.getAddressID(), name, phone);
        }

    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("修改地址", false, getResources().getString(R.string.carry_out));//修改授权   完成


        initView();
    }



    private void initView() {
        addressAddReceiver.setText(listShippingAddressBean.getHarvesterName());
        addressAddPhone.setText(listShippingAddressBean.getHarvestePhone().replace(" ",""));
        addressAddProvince.setText(listShippingAddressBean.getFreshCabinet().getCommunity().getProvinceName() + listShippingAddressBean.getFreshCabinet().getCommunity().getCityName() + listShippingAddressBean.getFreshCabinet().getCommunity().getDistrictName());
        addressAddArea.setText(listShippingAddressBean.getFreshCabinet().getCommunity().getStreet());

    }


    private void deleteAddress(int addressId){
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("shippingAddress", new RequestDeleteAddress(addressId));
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                    if (bean.getStatusCode() == 1) {
                        finish();
                    }
                    showShort(bean.getAlertMessage());
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
            }).getEntityData(this, HttpURL.HTTP_POST_DELETE_ADDRESS, json);
        }catch (Exception e){

        }
    }

    private void editInfo(int addressId, String name, String phone) {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress", new RequestEditAddress(addressId, name, phone));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
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
        }).getEntityData(this, HttpURL.HTTP_POST_INIT_ADDRESS, json);
        }catch (Exception e){

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!Database.contact_phone_name.equals("")) {
            String[] array = Database.contact_phone_name.split(",");
            addressAddPhone.setText(array[0].replace(" ",""));
//            addressAddPhone.setSelection(array[0].length());
            Database.contact_phone_name = "";
        }
    }
    private void starttxl() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(AddressAddAndEditActivity.this, Manifest.permission.READ_CONTACTS);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                return;
            } else {
                //有权限
                Intent intent = new Intent(AddressAddAndEditActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(AddressAddAndEditActivity.this, ContactListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(AddressAddAndEditActivity.this, ContactListActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AddressAddAndEditActivity.this, ContactListActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
