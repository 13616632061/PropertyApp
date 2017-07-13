package com.glory.bianyitong.ui.activity.shop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestCommitOrderByCart;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.FirmOrderAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.widght.shop.AmountView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/12.
 * 提交订单
 */

@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_FIRM, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class FirmOrderActivity extends BaseActivity implements AmountView.OnAmountChangeListener{

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
    @BindView(R.id.firm_order_address_lin)
    LinearLayout firmOrderAddressLin;
    @BindView(R.id.firm_order_recycle)
    RecyclerView firmOrderList;
    @BindView(R.id.firm_order_bill)
    TextView firmOrderBill;
    @BindView(R.id.firm_order_lin_bill)
    LinearLayout firmOrderLinBill;
    @BindView(R.id.firm_order_coupon)
    TextView firmOrderCoupon;
    @BindView(R.id.firm_order_lin_coupon)
    LinearLayout firmOrderLinCoupon;
    @BindView(R.id.firm_order_carry_money)
    TextView firmOrderCarryMoney;
    @BindView(R.id.firm_order_)
    LinearLayout firmOrder;
    @BindView(R.id.firm_order_all_money)
    TextView firmOrderAllMoney;
    @BindView(R.id.firm_order_commit)
    Button firmOrderCommit;
    @BindView(R.id.firm_order_price)
    TextView firmOrderPrice;
    private View addressInitView, addressNormalView;
    private boolean isHaveAddress=false;//标识是否选择地址
    private final int REQUEST_CODE_ADDRESS=100;//选择地址
    private ResponseQueryAddress.ListShippingAddressBean addressBean;


    // TODO: 2017/7/13 购物车商品数据
    @InjectParam(key = "shops")
     String shoppingCartData;

    // TODO: 2017/7/13 数据来源，1：直接购买  2：购物车
    @InjectParam(key = "type")
    int type;

    // TODO: 2017/7/13 直接购买商品数据
    @InjectParam(key = "shop")
    String listfreshBean;

    // TODO: 2017/7/13 商品列表
    private FirmOrderAdapter adapter;
    private List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> data=new ArrayList<>();

    // TODO: 2017/7/13 订单准备数据
    private double allPrice;//订单总金额

    @Override
    protected int getContentId() {
        return R.layout.activity_firm_order;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("提交订单", false, "");
        initView();
        initData();

    }

    private void initView() {
        addressInitView = LayoutInflater.from(this).inflate(R.layout.item_firm_order_address_init, null);
        addressNormalView = LayoutInflater.from(this).inflate(R.layout.item_firm_order_address_normal, null);
        firmOrderAddressLin.removeAllViews();
        firmOrderAddressLin.addView(addressNormalView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        adapter=new FirmOrderAdapter(R.layout.item_firm_order_list,data,this);
        firmOrderList.setLayoutManager(layoutManager);
        firmOrderList.setAdapter(adapter);

    }
    private void initData(){

        if(type==2){//购物车下单
            if(!TextUtils.isEmpty(shoppingCartData)){
                List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> datas=new Gson().fromJson(shoppingCartData,new TypeToken<List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>>>(){}.getType());
                data.addAll(datas);
            }
        }else if(type==1){//直接下单

            if(!TextUtils.isEmpty(listfreshBean)){
                ResponseQueryProductDetail.ListfreshBean freshBean=new Gson().fromJson(listfreshBean,new TypeToken<ResponseQueryProductDetail.ListfreshBean>(){}.getType());
                ResponseShoppingCart.ListShoppingCartBean bean=new ResponseShoppingCart.ListShoppingCartBean();
                bean.setQuantity(1);
                bean.setFreshTypeName(freshBean.getFreshTypeName());
                bean.setFreshName(freshBean.getFreshName());
                bean.setFreshID(freshBean.getFreshID());
                bean.setFresh(new ResponseShoppingCart.ListShoppingCartBean.FreshBean(freshBean.getFreshPicture(),freshBean.getMerchant_ID()));
                bean.setPrice(freshBean.getFreshPrice());
                data.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean>(bean));
            }
        }
        updateShoppingCardPrice();

    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.firm_order_lin_bill,R.id.firm_order_lin_coupon,R.id.firm_order_commit,R.id.firm_order_address_lin})
    void onClickBtn(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.firm_order_lin_bill://发票
                break;
            case R.id.firm_order_lin_coupon://优惠券
                break;
            case R.id.firm_order_commit://提交订单
                orderCommit();
                break;
            case R.id.firm_order_address_lin://选择地址
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER)
                        .with("source",true)
                        .requestCode(REQUEST_CODE_ADDRESS)
                        .go(this);




//                    Intent intent=new Intent(this, AddressActivity.class);
//                    intent.putExtra("source",true);
//                    startActivityForResult(intent,REQUEST_CODE_ADDRESS);
                break;
        }
    }


    /**
     * 组装提交订单数据
     * @return
     */
    private RequestCommitOrderByCart  dataFormat(){

        RequestCommitOrderByCart orderByCart=new RequestCommitOrderByCart(0f,addressBean.getAddressID(),addressBean.getCabinetID(),addressBean.getCabinetName());
        List<RequestCommitOrderByCart.OrderDetail> orderDetails=new ArrayList<>();
        for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean> bean:data
             ) {
            orderDetails.add(new RequestCommitOrderByCart.OrderDetail(0,0,bean.getData().getFreshID(),bean.getData().getQuantity(),bean.getData().getPrice(),bean.getData().getPrice()*bean.getData().getQuantity()));
        }
        orderByCart.setListOrderDetail(orderDetails);
        return orderByCart;
    }

    /**
     * 提交订单
     */
    private void orderCommit(){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        RequestCommitOrderByCart orderByCart=new RequestCommitOrderByCart(0f,addressBean.getAddressID(),addressBean.getCabinetID(),addressBean.getCabinetName());
        List<RequestCommitOrderByCart.OrderDetail> orderDetails=new ArrayList<>();

        map.put("order",dataFormat());
        String json=new Gson().toJson(map);
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
        }).getEntityData(HttpURL.HTTP_POST_ORDER_COMMIT,json);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_CODE_ADDRESS){
                showShort("测试");
                ResponseQueryAddress.ListShippingAddressBean addressBean= (ResponseQueryAddress.ListShippingAddressBean) data.getSerializableExtra("data");
                if(addressBean!=null){
                    this.addressBean=addressBean;
                    if(isHaveAddress){
                        TextView txtName=ButterKnife.findById(addressInitView,R.id.firm_order_item_name);
                        TextView txtNumber=ButterKnife.findById(addressInitView,R.id.firm_order_item_number);
                        TextView txtAddress=ButterKnife.findById(addressInitView,R.id.address_list_address);
                        txtName.setText(this.addressBean.getCabinetName());
                        txtAddress.setText(this.addressBean.getFreshCabinet().getProvinceName()+this.addressBean.getFreshCabinet().getCityName()+this.addressBean.getFreshCabinet().getDistrictName()+this.addressBean.getFreshCabinet().getStreetAddress());
                        txtNumber.setText("11/16");
                    }else {
                        firmOrderAddressLin.removeAllViews();
                        firmOrderAddressLin.addView(addressInitView);
                        isHaveAddress=true;
                    }
                }
            }
        }
    }

    @Override
    public void onAmountChange(View view, int amount, int position) {
        if(amount>0)
            upDateShoppingCart(position,amount);
    }

    private void upDateShoppingCart(int position, int amount) {
        data.get(position).getData().setQuantity(amount);
        adapter.notifyDataSetChanged();
        updateShoppingCardPrice();
    }

    private void updateShoppingCardPrice() {
        double allPrice = 0;
        for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean> bean : data
                ) {
            ResponseShoppingCart.ListShoppingCartBean entity = bean.getData();
            allPrice += entity.getPrice() * entity.getQuantity();
        }

        firmOrderAllMoney.setText("￥" + allPrice);
        firmOrderPrice.setText("￥" + allPrice);
        this.allPrice=allPrice;
    }
}
