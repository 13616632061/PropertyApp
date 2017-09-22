package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestOrderList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.MultiItemView;
import com.glory.bianyitong.ui.adapter.shop.OrderListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2017/9/21.
 * 退款售后
 */
public class RefundMoneyActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.order_list_fr_recycle)
    RecyclerView orderListFrRecycle;
    @BindView(R.id.order_list_fr_refresh)
    SwipeRefreshLayout orderListFrRefresh;
    private OrderListAdapter adapter;
    List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data=new ArrayList<>();
    private int currentPageNumber=1;

    @Override
    protected int getContentId() {
        return R.layout.activity_refundmoney;
    }


    @Override
    protected void init() {
        super.init();
        inintTitle("退款/售后", false, "");
        initView();

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new OrderListAdapter( data,this);
        orderListFrRecycle.setLayoutManager(layoutManager);
        adapter.setOnLoadMoreListener(this,orderListFrRecycle);
        orderListFrRecycle.setAdapter(adapter);
        orderListFrRefresh.setOnRefreshListener(this);
        requestOrderList();
    }

    private void requestOrderList(){
        String json;
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("currentPageNumber",currentPageNumber);
        map.put("entityOrder",new RequestOrderList(new RequestOrderList.OrderStatus(-1)));
        json=new Gson().toJson(map);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                orderListFrRefresh.setRefreshing(false);
                ResponseQueryOrderList entity=new Gson().fromJson(s,ResponseQueryOrderList.class);
                if(entity.getStatusCode()==1){
                    for (ResponseQueryOrderList.ListOrderBean listBean: entity.getList_Order()) {
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.TITLE,listBean.getMerchant_Name(),getStatusName(listBean.getOrderStatus())));
                        for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean bean:listBean.getListOrderDetail()
                                ) {
                            MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemView = new MultiItemView<>(MultiItemView.BODY, bean, listBean.getOrderStatus());
                            listOrderDetailBeanMultiItemView.getData().setOrderID(listBean.getOrderID());
                            data.add(listOrderDetailBeanMultiItemView);
                        }
                        MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemView = new MultiItemView<>(MultiItemView.FOOTER);
                        listOrderDetailBeanMultiItemView.setCartNum(listBean.getCartNum());
                        listOrderDetailBeanMultiItemView.setOrderPaidPrice(listBean.getOrderPaidPrice());
                        listOrderDetailBeanMultiItemView.setFreight(listBean.getFreight());
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.FOOTER));
                    }
                    adapter.notifyDataSetChanged();
                    if(currentPageNumber<entity.getPageRowNumber()){
                        adapter.setEnableLoadMore(true);
                        adapter.loadMoreComplete();
                    }else {
                        adapter.setEnableLoadMore(false);
                        adapter.loadMoreEnd();
                    }

                }else if(entity.getStatusCode()==2){
                    if(data.size()<=0)
                        adapter.setEmptyView(R.layout.layout_empty_orderlist);
                    else {
                        adapter.loadMoreEnd();
                    }

//                    ToastUtils.showToast(getActivity(),entity.getAlertMessage());
                }else{
                    ToastUtils.showToast(RefundMoneyActivity.this,entity.getAlertMessage());
                }

            }

            @Override
            public void onError() {
                orderListFrRefresh.setRefreshing(false);
                ToastUtils.showToast(RefundMoneyActivity.this,getString(R.string.system_error));
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
        }).getEntityData(RefundMoneyActivity.this, HttpURL.HTTP_POST_ORDER_QUERY,json);
    }

    /**
     * 获取状态描述
     * @param status
     * @return
     */
    private String getStatusName(int status){
        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                return "等待买家付款";
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                return "等待卖家发货";
            case Constant.ORDER_STATUS.STATUS_PAY_LOCAL:
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                return "卖家已发货";
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                return "交易成功";
            case Constant.ORDER_STATUS.STATUS_PAY_REFUNDING://退款中
                return "退款中";
            case Constant.ORDER_STATUS.STATUS_PAY_REFUNDED://退款成功
               return "退款成功";
            default:
                return "未知状态";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        requestOrderList();
    }

    @Override
    public void onRefresh() {
        orderListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        data.clear();
//        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        adapter.notifyDataSetChanged();
        requestOrderList();
    }
}
