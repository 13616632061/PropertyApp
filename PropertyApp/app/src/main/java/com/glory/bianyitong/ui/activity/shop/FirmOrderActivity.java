package com.glory.bianyitong.ui.activity.shop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.github.lazylibrary.util.StringUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.GetOrderCommitAddress;
import com.glory.bianyitong.bean.OrderOhterOne;
import com.glory.bianyitong.bean.ShopcartInfo;
import com.glory.bianyitong.bean.entity.request.RequestCommitOrderByCart;
import com.glory.bianyitong.bean.entity.request.RequestQueryConponListByYes;
import com.glory.bianyitong.bean.entity.request.RequestQueryCouponList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.bean.entity.response.ResponseSubmitOrder;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.UseCouponActivity;
import com.glory.bianyitong.ui.adapter.shop.FirmOrderAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.util.ActivityManager;
import com.glory.bianyitong.widght.shop.AmountView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/12.
 * 确认订单
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
    private final int REQUEST_CODE_ADDRESS=100,REQUEST_CODE_COUPON=101;//选择地址,选择优惠券
    private ResponseQueryAddress.ListShippingAddressBean addressBean;
    private boolean isPay=true;

    // TODO: 2017/7/13 购物车商品数据
    @InjectParam(key = "shops")
     String shoppingCartData;

    // TODO: 2017/7/13 收货地址数据
    @InjectParam(key = "addressBean")
    String addressBeabean;

    // TODO: 2017/7/13 数据来源，1：直接购买  2：购物车
    @InjectParam(key = "type")
    int type;

    // TODO: 2017/7/13 直接购买商品数据
    @InjectParam(key = "shop")
    String listfreshBean;

    // TODO: 2017/7/13 商品列表
    private FirmOrderAdapter adapter;
    private List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> data=new ArrayList<>();

    // TODO: 2017/7/13 订单准备数据
    private float allPrice;//订单总金额
    private float backPrice;//优惠券后订单金额
    private float allFreePrice;//减免金额


    // TODO: 2017/7/18 查询优惠券数据
    private String couponJson="";
    private ResponseQueryCouponList.ListCouponReceiveBean couponBean=new ResponseQueryCouponList.ListCouponReceiveBean();//选择优惠券实体
    private ResponseQueryCouponList queryCouponListbean;
    private String json;
    private ArrayList<Integer> requestList=new ArrayList<>();
    private float freeMoney=0;

    @Override
    protected int getContentId() {
        return R.layout.activity_firm_order;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("提交订单", false, "");
        ActivityManager.addActivity(this,"firmorderactivty");
        initView();
        initData();

    }

    private void initView() {
        addressInitView = LayoutInflater.from(this).inflate(R.layout.item_firm_order_address_init, null);
        addressNormalView = LayoutInflater.from(this).inflate(R.layout.item_firm_order_address_normal, null);
        firmOrderAddressLin.removeAllViews();
        firmOrderAddressLin.addView(addressNormalView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        adapter=new FirmOrderAdapter(R.layout.item_firm_order_list,data,this,this);
        firmOrderList.setLayoutManager(layoutManager);
        firmOrderList.setAdapter(adapter);

    }
    private void initData(){
        try {


        addressBean= new Gson().fromJson(addressBeabean,ResponseQueryAddress.ListShippingAddressBean.class);
        if(type==2){//购物车下单
            if(!TextUtils.isEmpty(shoppingCartData)){
                List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> datas=new Gson().fromJson(shoppingCartData,new TypeToken<List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>>>(){}.getType());
                for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:datas){
                    if (!bean.isHeader)
                        data.add(bean);
                }
//                data.addAll(datas);
            }
        }else if(type==1){//直接下单
            if(!TextUtils.isEmpty(listfreshBean)){
                ResponseQueryProductDetail.ListfreshBean freshBean=new Gson().fromJson(listfreshBean,new TypeToken<ResponseQueryProductDetail.ListfreshBean>(){}.getType());
                ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean bean=new ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean();
                Log.v("123",freshBean.getFreshPrice()+"---");
                bean.setQuantity(1);
                bean.setFresh(new ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean.FreshBean(freshBean.getFreshPicture(),freshBean.getMerchant_ID()));
                bean.setPrice(freshBean.getFreshPrice());
                bean.getFresh().setFreshName(freshBean.getFreshName());
                try {
                    bean.getFresh().setFreshPicture(freshBean.getFreshPicture());
                }catch (Exception e){

                }
                bean.getFresh().setFreshTypeName(freshBean.getFreshTypeName());
                bean.getFresh().setFreshTypeLeaf(freshBean.getFreshTypeLeaf());
                bean.getFresh().setFreshTypeID(freshBean.getFreshTypeID());
                bean.setFreshID(freshBean.getFreshID());
                bean.getFresh().setGodownNumber(freshBean.getGodownNumber());
                data.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>(bean));
            }
        }
        initAddress();
        updateShoppingCardPrice();
        queryCouponForYES();
        }catch (Exception e){

        }
    }

    private void initAddress() {
        if(isHaveAddress==false){
            firmOrderAddressLin.removeAllViews();
            firmOrderAddressLin.addView(addressInitView);
            isHaveAddress=true;
        }
//        TextView txtName=ButterKnife.findById(addressInitView,R.id.firm_order_item_name);
//        TextView txtNumber=ButterKnife.findById(addressInitView,R.id.firm_order_item_number);
//        TextView txtAddress=ButterKnife.findById(addressInitView,R.id.address_list_address);
//        txtName.setText(this.addressBean.getFreshCabinet().getCommunityName()+this.addressBean.getFreshCabinet().getCabinetName());
//        txtAddress.setText(this.addressBean.getFreshCabinet().getCommunity().getProvinceName()+this.addressBean.getFreshCabinet().getCommunity().getCityName()+this.addressBean.getFreshCabinet().getCommunity().getDistrictName()+this.addressBean.getFreshCabinet().getCommunity().getStreet());
//
//        SpannableString spannable=new SpannableString(addressBean.getFreshCabinet().getUsed()+"/"+addressBean.getFreshCabinet().getNum());
//        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        txtNumber.setText(spannable);
//        getOrderCommit();
        queryAddress();
    }

    /**
     * //默认收货地址
     *
     */
    private void queryAddress() {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("shippingAddress", new Object());
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    ResponseQueryAddress queryAddress = new Gson().fromJson(s, ResponseQueryAddress.class);
                    if (queryAddress.getStatusCode() == 1) {
                        if (queryAddress.getListShippingAddress() != null && queryAddress.getListShippingAddress().size() > 0) {
                            for (ResponseQueryAddress.ListShippingAddressBean bean : queryAddress.getListShippingAddress()) {
                                if (bean.isDefaults()) {
                                    TextView txtName=ButterKnife.findById(addressInitView,R.id.firm_order_item_name);
                                    TextView txtNumber=ButterKnife.findById(addressInitView,R.id.firm_order_item_number);
                                    TextView txtAddress=ButterKnife.findById(addressInitView,R.id.address_list_address);
                                    TextView name_and_phone = ButterKnife.findById(addressInitView, R.id.name_and_phone);
                                    name_and_phone.setText(addressBean.getHarvesterName()+"  "+addressBean.getHarvestePhone());
                                    txtName.setText(bean.getFreshCabinet().getCommunityName()+bean.getFreshCabinet().getCabinetName());
                                    txtAddress.setText(bean.getFreshCabinet().getCommunity().getProvinceName()+bean.getFreshCabinet().getCommunity().getCityName()+bean.getFreshCabinet().getCommunity().getDistrictName()+bean.getFreshCabinet().getCommunity().getStreet());
                                    SpannableString spannable=new SpannableString(bean.getFreshCabinet().getUsed()+"/"+bean.getFreshCabinet().getNum());
                                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    txtNumber.setText(spannable);
                                }
                            }

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
            }).getEntityData(this, HttpURL.HTTP_POST_QUERY_ADDRESS, json);
        } catch (Exception e) {

        }
    }
    /**
     * 获取地址信息
     */
    private void getOrderCommit(){
        try {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> maps=new HashMap<>();
        maps.put("cabinetID",addressBean.getCabinetID());
        map.put("shippingAddress",maps);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                GetOrderCommitAddress bean=new Gson().fromJson(s,GetOrderCommitAddress.class);
                if(bean.getStatusCode()==1){
                    TextView txtName=ButterKnife.findById(addressInitView,R.id.firm_order_item_name);
                    TextView txtNumber=ButterKnife.findById(addressInitView,R.id.firm_order_item_number);
                    TextView txtAddress=ButterKnife.findById(addressInitView,R.id.address_list_address);
                    TextView name_and_phone = ButterKnife.findById(addressInitView, R.id.name_and_phone);
                    name_and_phone.setText(addressBean.getHarvesterName()+"  "+addressBean.getHarvestePhone());
                    txtName.setText(bean.getShippingAddress().getFreshCabinet().getCommunityName()+bean.getShippingAddress().getFreshCabinet().getCabinetName());
                    txtAddress.setText(bean.getShippingAddress().getFreshCabinet().getCommunity().getProvinceName()+bean.getShippingAddress().getFreshCabinet().getCommunity().getCityName()+bean.getShippingAddress().getFreshCabinet().getCommunity().getDistrictName()+bean.getShippingAddress().getFreshCabinet().getCommunity().getStreet());
                    SpannableString spannable=new SpannableString(bean.getShippingAddress().getFreshCabinet().getUsed()+"/"+bean.getShippingAddress().getFreshCabinet().getNum());
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtNumber.setText(spannable);
                }else {
                    showShort(bean.getAlertMessage());
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
        }).getEntityData(this,HttpURL.HTTP_POST_ORDER_QUERYCABINET, json);
        }catch (Exception e){

        }
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
                if(queryCouponListbean.getListCouponReceive()!=null && queryCouponListbean.getListCouponReceive().size()>0){//可使用
                    Intent intent=new Intent(this, UseCouponActivity.class);
                    intent.putExtra("json",json);
                    intent.putIntegerArrayListExtra("requestList",requestList);
                    startActivityForResult(intent,REQUEST_CODE_COUPON);
                }else {//无可使用
                    showShort("暂无优惠券可用");
                }
//                if(StringUtils.isEmpty(couponJson)){
//                    showShort("暂无优惠券可用");
//                    return;
//                }
//                Router.build(RouterMapping.ROUTER_ACTIVITY_COUPON_LIST)
//                        .with("source",2)
//                        .with("formValue",couponJson)
//                        .requestCode(REQUEST_CODE_COUPON)
//                        .go(this);
                break;
            case R.id.firm_order_commit://提交订单

                if(addressBean==null){
                    showShort("请选择收货地址");
                    return;
                }
                if (!isPay){
                    showShort("该地址无法配送");
                    return;
                }
                orderCommit();
                break;
            case R.id.firm_order_address_lin://选择地址

                    Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER)
                            .with("source", true)
                            .requestCode(REQUEST_CODE_ADDRESS)
                            .go(this);


                break;

        }
    }


    /**
     * 组装提交订单数据
     * @return
     */
    private RequestCommitOrderByCart  dataFormat(){

        /**
         * 订单组装数据
         * 运费，收货ID，收货柜ID，收货柜名称，优惠券ID，减免金额，总金额
         */
        RequestCommitOrderByCart orderByCart=new RequestCommitOrderByCart(couponBean.getCouponID(),0f,addressBean.getAddressID(),addressBean.getCabinetID(),addressBean.getFreshCabinet().getCommunityName()+addressBean.getFreshCabinet().getCabinetName(),allFreePrice,backPrice);
        List<RequestCommitOrderByCart.OrderDetail> orderDetails=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        if(type==1){//直接下单
            for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:data) {
                orderDetails.add(new RequestCommitOrderByCart.OrderDetail(bean.getData().getFreshID(),bean.getData().getQuantity()));
            }

        }else if(type==2){//购物车下单
            for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:data) {
                orderDetails.add(new RequestCommitOrderByCart.OrderDetail(bean.getData().getFresh().getFreshID(),bean.getData().getQuantity()));
            }

        }
        String s = new Gson().toJson(list);
//        orderByCart.setShoppingCarts(list);
        orderByCart.setListOrderDetail(orderDetails);
        orderByCart.setOrderPrice(backPrice);
        orderByCart.setFreePrice(freeMoney);

        return orderByCart;
    }


    private List<Integer> shoppingCart(){
        List<Integer> list=new ArrayList<>();
        if(type==2){
            for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:data
                    ) {
                if(bean.getData().getCartID()!=0){
                    list.add(bean.getData().getCartID());
                }
            }
        }
        return  list;
    }


    /**
     * 提交订单
     */
    private void orderCommit(){
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> maps=new HashMap<>();
        RequestCommitOrderByCart requestCommitOrderByCart= dataFormat();
//        requestCommitOrderByCart.setOrderPrice(requestCommitOrderByCart.getOrderPrice()-freeMoney);
        maps.put("order",dataFormat());
        maps.put("SubmitType",type);//,1、生鲜详情页面立即购买 2、购物车结算）
        maps.put("receiveIDs",requestList);
        map.put("entityOrder",maps);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseSubmitOrder bean=new Gson().fromJson(s,ResponseSubmitOrder.class);
                if(bean.getStatusCode()==1){
                    Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_PAY)
//                            .with("data",bean)
                            .with("OrderID",bean.getParentOrderID())
                            .with("orderCode",bean.getOrderCode())
                            .with("price",dataFormat().getOrderPrice())
                            .go(FirmOrderActivity.this);
                }else {
                    showShort(bean.getAlertMessage());
                }
            }

            @Override
            public void onError() {
                showShort("提交订单失败");
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
        }).getEntityData(this,HttpURL.HTTP_POST_ORDER_COMMIT, json);
        }catch (Exception e){

        }
    }


//    private RequestQueryConponListByYes dataFormatByYES(){
//        RequestQueryConponListByYes list=new RequestQueryConponListByYes(new RequestQueryConponListByYes.CouponReceive(0));
//        List<RequestQueryConponListByYes.OrderDetail> orderDetails=new ArrayList<>();
//        if(type==1){//直接下单
//
//            for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:data ) {
//                orderDetails.add(new RequestQueryConponListByYes.OrderDetail(new RequestCommitOrderByCart.OrderDetail.Fresh(bean.getData().getFresh().getFreshTypeID(),bean.getData().getFresh().getMerchant_ID()),0,bean.getData().getFreshID(),bean.getData().getQuantity(),bean.getData().getPrice(),bean.getData().getPrice()*bean.getData().getQuantity()));
//            }
//            list.setListOrderDetail(orderDetails);
//        }else if(type==2){//购物车下单
//            List<Integer> lists=new ArrayList<>();
//            for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean:data) {
//                if (!bean.isHeader)
//                    if(bean.getData().getCartID()!=0){
//                    lists.add(bean.getData().getCartID());
//                }
//            }
//            list.setShoppingCarts(lists);
//        }
//        list.setListOrderDetail(orderDetails);
//        return list;
//    }

    private void queryCouponForYES(){//获取优惠券数量
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> map2=new HashMap<>();
        List<OrderOhterOne.ListDetailBean> listDetailBeen=new ArrayList<>();
        for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> enty:data){
            OrderOhterOne.ListDetailBean orderOhterOne=new OrderOhterOne.ListDetailBean();
            orderOhterOne.setFreshID(enty.getData().getFreshID());
            orderOhterOne.setFreshQuantity(enty.getData().getQuantity());
            listDetailBeen.add(orderOhterOne);
        }
        map2.put("listDetail",listDetailBeen);
        map.put("entityCouponReceive",map2);
        json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {

            @Override
            public void onSuccess(String s) {
                couponJson=s;
                queryCouponListbean = new Gson().fromJson(s,ResponseQueryCouponList.class);
                if(queryCouponListbean.getStatusCode()==1){

                    if(queryCouponListbean.getListCouponReceive()!=null && queryCouponListbean.getListCouponReceive().size()>0){//可使用
                        firmOrderLinCoupon.setEnabled(true);
                        firmOrderCoupon.setText("可用优惠券"+ queryCouponListbean.getListCouponReceive().size()+"张");
                        firmOrderCoupon.setTextColor(getResources().getColor(R.color.red1));
                    }else {//无可使用
                        firmOrderLinCoupon.setEnabled(false);
                        firmOrderCoupon.setText("无可用");
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
        }).getEntityData(this,HttpURL.HTTP_POST_COUPON_QUERY_OTHERONE,json);
        }catch (Exception e){

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_CODE_ADDRESS){
                ResponseQueryAddress.ListShippingAddressBean addressBean= (ResponseQueryAddress.ListShippingAddressBean) data.getSerializableExtra("data");
                if(addressBean!=null){
                    this.addressBean=addressBean;
                    if(isHaveAddress==false){
                        firmOrderAddressLin.removeAllViews();
                        firmOrderAddressLin.addView(addressInitView);
                        isHaveAddress=true;
                    }
                    TextView txtName=ButterKnife.findById(addressInitView,R.id.firm_order_item_name);
                    TextView txtNumber=ButterKnife.findById(addressInitView,R.id.firm_order_item_number);
                    TextView txtAddress=ButterKnife.findById(addressInitView,R.id.address_list_address);
                    TextView name_and_phone = ButterKnife.findById(addressInitView, R.id.name_and_phone);
                    name_and_phone.setText(addressBean.getHarvesterName()+"  "+addressBean.getHarvestePhone());
                    txtName.setText(this.addressBean.getFreshCabinet().getCommunityName()+this.addressBean.getFreshCabinet().getCabinetName());
                    txtAddress.setText(this.addressBean.getFreshCabinet().getCommunity().getProvinceName()+this.addressBean.getFreshCabinet().getCommunity().getCityName()+this.addressBean.getFreshCabinet().getCommunity().getDistrictName()+this.addressBean.getFreshCabinet().getCommunity().getStreet());

                    SpannableString spannable = new SpannableString(addressBean.getFreshCabinet().getUsed() + "/" + addressBean.getFreshCabinet().getNum());
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtNumber.setText(spannable);

                    queryShoppingCart();

                }
            }else if(requestCode==REQUEST_CODE_COUPON){//优惠券列表
                requestList = data.getIntegerArrayListExtra("data");
                freeMoney=data.getIntExtra("money",0);
                backPrice = allPrice-freeMoney;
                BigDecimal b   =   new   BigDecimal(backPrice);
                backPrice  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
//                if (freeMoney>0.0001){
//
//                }else {
//                    backPrice = allPrice;
//
//                }
//                backPrice=allPrice-freeMoney;
                firmOrderAllMoney.setText("￥" + backPrice);
                firmOrderPrice.setText("￥" + backPrice);

            }
        }
    }
    /**
     * 查询购物车
     */
    private void queryShoppingCart() {
        try {
            isPay=true;
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            List<Integer> list=new ArrayList<>();
            for (int i=0;i<data.size();i++){
                list.add(data.get(i).getData().getFreshID());
            }
            map.put("freshIDS",list);
            map.put("cabinetID", addressBean.getCabinetID());
            map.put("commitType", type);
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    ResponseShoppingCart shoppingCartbean =     new Gson().fromJson(s, ResponseShoppingCart.class);
                    if (shoppingCartbean.getStatusCode() == 1) {
                        List<ResponseShoppingCart.ListShoppingCartBean> list = shoppingCartbean.getListShoppingCart();
                        if (!(list == null || list.size() <= 0)) {
                            for (int i = 0; i < list.size(); i++) {
                                for (int j = 0; j < list.get(i).getListShopping().size(); j++) {
                                    Log.v("isvalidaa",list.get(i).getListShopping().get(j).isIsvalid()+"");
                                   if (!list.get(i).getListShopping().get(j).isIsvalid()){
                                       isPay=false;
                                   }
                                }
                            }
                        }
                    } else {
                    }
                }
                @Override
                public void onError() {
                    adapter.setEmptyView(R.layout.layout_empty);
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
            }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_QUERY, json);
        }catch (Exception e){

        }
    }
    @Override
    public void onAmountChange(View view, int amount, int position) {
        if(amount>0){
            upDateShoppingCart(position,amount);
        }
        if (amount>=data.get(position).getData().getFresh().getGodownNumber()){
            showShort("亲，不能购买更多哦！");
        }
    }

    private void upDateShoppingCart(int position, int amount) {
        data.get(position).getData().setQuantity(amount);
        adapter.notifyDataSetChanged();
        updateShoppingCardPrice();
        queryCouponForYES();
    }

    private void updateShoppingCardPrice() {
        float allPrice =   0;
        for (ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean : data) {
            ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean entity = bean.getData();
            if (!bean.isHeader)

                allPrice += entity.getPrice() * entity.getQuantity();
        }
        backPrice = allPrice-freeMoney;
        BigDecimal b2   =   new   BigDecimal(backPrice);
        backPrice  =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
//        if (freeMoney>0.0001){
//
//        }else {
//            backPrice = allPrice;
//
//        }
        Log.v("asdasd",allPrice+"-----"+freeMoney+"----"+backPrice);

        firmOrderAllMoney.setText("￥" + backPrice);
        firmOrderPrice.setText("￥" + backPrice);
        BigDecimal b   =   new   BigDecimal(allPrice);
        allPrice  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        this.allPrice=allPrice;
    }
}
