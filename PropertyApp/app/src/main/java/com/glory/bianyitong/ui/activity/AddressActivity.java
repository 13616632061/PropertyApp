package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestAddRess;
import com.glory.bianyitong.bean.entity.request.RequestDeleteAddress;
import com.glory.bianyitong.bean.entity.request.RequestInitAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryExpressBar;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.shop.ExpressBarAddActivity;
import com.glory.bianyitong.ui.adapter.shop.AddressListAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddressActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener{

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
    @BindView(R.id.address_list)
    RecyclerView recycleList;

    private AddressListAdapter adapter;
    private List<ItemMenu<ResponseQueryAddress.ListShippingAddressBean>> data=new ArrayList<>();
    @InjectParam(key = "source")
    boolean isFirmOrder=false;
    @Override
    protected int getContentId() {
        return R.layout.activity_address;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("管理收货地址",false,"");
//        isFirmOrder=getIntent().getBooleanExtra("source",false);



        initView();
    }

    private void initView() {
        if(isFirmOrder){
            adapter=new AddressListAdapter(R.layout.item_addresslist2,data,this,isFirmOrder);
            adapter.setOnItemClickListener(onItemClickListener);
        }else {
            adapter=new AddressListAdapter(R.layout.item_addresslist,data,this,isFirmOrder);
            adapter.setOnItemChildClickListener(this);

        }
        LinearLayoutManager layout=new LinearLayoutManager(this);
        recycleList.setLayoutManager(layout);
        recycleList.setAdapter(adapter);
        adapter.bindToRecyclerView(recycleList);
    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.btn_add})
    void onClickBtn(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.btn_add:
                Intent intent=new Intent(this, ExpressBarAddActivity.class);
                startActivityForResult(intent,200);
                break;
        }
    }
    private void queryAddress() {
        data.clear();
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress", new Object());
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryAddress queryAddress=new Gson().fromJson(s,ResponseQueryAddress.class);
                if(queryAddress.getStatusCode()==1){
                    if(queryAddress.getListShippingAddress()!=null && queryAddress.getListShippingAddress().size()>0){
                        for (ResponseQueryAddress.ListShippingAddressBean bean:queryAddress.getListShippingAddress()
                             ) {
                                data.add(new ItemMenu<ResponseQueryAddress.ListShippingAddressBean>(bean));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
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
        }).getEntityData(this,HttpURL.HTTP_POST_QUERY_ADDRESS, json);

    }


    private void addAddress(int cabinetID){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress",new RequestAddRess(cabinetID, Database.USER_MAP.getLoginName(),Database.USER_MAP.getPhoneNumber()));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    queryAddress();
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
        }).getEntityData(this,HttpURL.HTTP_POST_ADD_ADDRESS,json);
    }


    private void deleteAddress(int addressId){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress",new RequestDeleteAddress(addressId));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    queryAddress();
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
        }).getEntityData(this,HttpURL.HTTP_POST_DELETE_ADDRESS,json);
    }

    private void setInitAddress(int addressId,boolean isChecked){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress",new RequestInitAddress(addressId,isChecked));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    queryAddress();
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
        }).getEntityData(this,HttpURL.HTTP_POST_INIT_ADDRESS,json);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==200){
                ResponseQueryExpressBar.ListFreshCabinetBean bean= (ResponseQueryExpressBar.ListFreshCabinetBean) data.getSerializableExtra("data");
                if(bean!=null){
                    addAddress(bean.getCabinetID());
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        queryAddress();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ResponseQueryAddress.ListShippingAddressBean bean=data.get(position).getData();
        switch (view.getId()){
            case R.id.address_list_delete://删除
                if(bean!=null){
                    deleteAddress(bean.getAddressID());
                }
                break;
            case R.id.tv_shop_edit://编辑
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_ADD)
                        .with("data",bean)
                        .go(AddressActivity.this);
                break;

            case R.id.iv_button://设置默认地址
                this.adapter.setPosition(position);
                showShort(bean.getCabinetName()+"设置默认地址");
                setInitAddress(bean.getAddressID(),true);
                break;
        }


    }

    BaseQuickAdapter.OnItemClickListener onItemClickListener=new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ResponseQueryAddress.ListShippingAddressBean bean=data.get(position).getData();
            if(bean!=null){
                Intent intent=new Intent();
                intent.putExtra("data",bean);
                String json = new Gson().toJson(bean);
                Log.i("jsoss",json);
                setResult(RESULT_OK,intent);
                AddressActivity.this.finish();
            }
        }
    };

}
