package com.glory.bianyitong.ui.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.OrderDetailsInfo;
import com.glory.bianyitong.bean.OrderDetailsRequest;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.activity.InspectionReportActivity;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.OrderDetailsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/9/21.
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
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
    @BindView(R.id.firm_order_item_name)
    TextView firmOrderItemName;
    @BindView(R.id.firm_order_item_number)
    TextView firmOrderItemNumber;
    @BindView(R.id.address_list_address)
    TextView addressListAddress;
    @BindView(R.id.lay_list_item_goods)
    LinearLayout layListItemGoods;
    @BindView(R.id.tv_shop_edit)
    TextView tvShopEdit;
    @BindView(R.id.order_recycle)
    RecyclerView order_recycle;
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
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.pay_num)
    TextView payNum;
    @BindView(R.id.pay_data)
    TextView payData;
    @BindView(R.id.ok_data)
    TextView okData;
    private List<ItemMenu<OrderDetailsInfo.ListOrderBean.ListOrderDetailBean>> data = new ArrayList<>();

    private int orderID;
    private OrderDetailsAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_ordertails;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("订单详情", false, "");
        orderID = getIntent().getIntExtra("orderID", 0);
        initView();

    }

    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new OrderDetailsAdapter(R.layout.item_orderdetails, data, this);
        order_recycle.setLayoutManager(layoutManager);
        order_recycle.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        getOrderDetails();
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;

        }
    }

    /**
     * 提交订单
     */
    private void getOrderDetails() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        OrderDetailsRequest orderDetailsRequest = new OrderDetailsRequest();
        orderDetailsRequest.getOrder().setOrderID(orderID);
        map.put("entityOrder", orderDetailsRequest);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {


            @Override
            public void onSuccess(String s) {
                OrderDetailsInfo bean = new Gson().fromJson(s, OrderDetailsInfo.class);
                if (bean.getStatusCode() == 1) {
                    for (OrderDetailsInfo.ListOrderBean.ListOrderDetailBean enty : bean.getList_Order().get(0).getListOrderDetail()) {
                        data.add(new ItemMenu<OrderDetailsInfo.ListOrderBean.ListOrderDetailBean>(enty));
                    }
                    adapter.notifyDataSetChanged();
                    OrderDetailsInfo.ListOrderBean listOrderBean = bean.getList_Order().get(0);
                    firmOrderItemName.setText("收货人：" + listOrderBean.getRealName());
                    firmOrderItemNumber.setText(listOrderBean.getMobileNumber());
                    addressListAddress.setText("收货地址：" + listOrderBean.getAddress());
                    if (listOrderBean.getCouponReceive() != null&&listOrderBean.getCouponReceive().getCoupon()!=null) {
                        firmOrderCoupon.setText("-￥" + listOrderBean.getCouponReceive().getCoupon().getFreeMoney());
                        firmOrderCoupon.setTextColor(getResources().getColor(R.color.red1));
                    }else {
                        firmOrderCoupon.setVisibility(View.GONE);
                    }
                    firmOrderAllMoney.setText("￥" + listOrderBean.getOrderPaidPrice());
                    orderNum.setText("订单编号："+listOrderBean.getOrderCode());
                    if (listOrderBean.getAppId()!=0){
                        payNum.setText("支付交易号："+listOrderBean.getAppId());
                    }else {
                        payNum.setVisibility(View.GONE);
                    }
                    if (listOrderBean.getOrderTime()!=null){
                        payData.setText("下单时间:"+listOrderBean.getOrderTime().replace("T"," "));
                    }else {
                        payData.setVisibility(View.GONE);
                    }
                    if (listOrderBean.getEndOrderTime()!=null){
                        okData.setText("支付时间:"+listOrderBean.getEndOrderTime().replace("T"," "));
                    }else {
                        okData.setVisibility(View.GONE);
                    }
                } else {
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
        }).getEntityData(this, HttpURL.HTTP_POST_ORDER_QUERY, json);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.order_list_item_opera_btn2:
                Intent intent=new Intent(this, InspectionReportActivity.class);
                intent.putExtra("qualityID",data.get(position).getData().getQualityID());
                startActivity(intent);
                break;
        }
    }
}
