package com.glory.bianyitong.ui.fragment.shop;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.RouteCallback;
import com.chenenyu.router.RouteResult;
import com.chenenyu.router.Router;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestOrderList;
import com.glory.bianyitong.bean.entity.request.RequestOrderOperation;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.shop.FirmOrderActivity;
import com.glory.bianyitong.ui.activity.shop.LogisticsActivity;
import com.glory.bianyitong.ui.activity.shop.OrderDetailsActivity;
import com.glory.bianyitong.ui.adapter.MultiItemView;
import com.glory.bianyitong.ui.adapter.shop.OrderListAdapter;
import com.glory.bianyitong.ui.dialog.CallPhoneDialog;
import com.glory.bianyitong.ui.dialog.OkDialog;
import com.glory.bianyitong.ui.fragment.RootFragment;
import com.glory.bianyitong.util.FilterExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/3.
 */

public class OrderListFragment extends RootFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.order_list_fr_recycle)
    RecyclerView orderListFrRecycle;
    @BindView(R.id.order_list_fr_refresh)
    SwipeRefreshLayout orderListFrRefresh;


    private int orderType=0;
    private int currentPageNumber=1;

    private List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data=new ArrayList<>();
    private OrderListAdapter adapter;
    private ResponseQueryOrderList entity;

    @Override
    protected void initView() {
        orderType=getArguments().getInt("type");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        orderListFrRecycle.setLayoutManager(layoutManager);
        adapter=new OrderListAdapter(data,getActivity());
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
        adapter.setOnLoadMoreListener(this,orderListFrRecycle);
        orderListFrRecycle.setAdapter(adapter);
        orderListFrRefresh.setOnRefreshListener(this);

    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_orderlist;
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if(isVisible){
            onAutoRefresh();
        }
    }


    private void requestOrderList(int orderStatus){

        String json;
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("currentPageNumber",currentPageNumber);
        map.put("entityOrder",new RequestOrderList(new RequestOrderList.OrderStatus(orderStatus)));
//        if(orderStatus== Constant.ORDER_STATUS.STATUS_PAY_ALL){//全部订单
//            json=new GsonBuilder().addSerializationExclusionStrategy(new FilterExclusionStrategy("orderStatus")).create().toJson(map);
//        }else {
            json=new Gson().toJson(map);
//        }

        try {
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    orderListFrRefresh.setRefreshing(false);
                    entity = new Gson().fromJson(s,ResponseQueryOrderList.class);
                    if(entity.getStatusCode()==1){
                        for (ResponseQueryOrderList.ListOrderBean listBean: entity.getList_Order()) {
                            MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemViewTitle = new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.TITLE,listBean.getMerchant_Name(),listBean.getOrderStatusExplain());
                            listOrderDetailBeanMultiItemViewTitle.setOrderId(listBean.getOrderID());
                            listOrderDetailBeanMultiItemViewTitle.setParentOrderID(listBean.getParentOrderID());
                            listOrderDetailBeanMultiItemViewTitle.setOrderPaidPrice( listBean.getOrderPaidPrice());
                            listOrderDetailBeanMultiItemViewTitle.setOrderCode(listBean.getOrderCode());
                            data.add(listOrderDetailBeanMultiItemViewTitle);
                            for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean bean:listBean.getListOrderDetail()
                                    ) {
                                MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemView = new MultiItemView<>(MultiItemView.BODY, bean, listBean.getOrderStatus());
                                listOrderDetailBeanMultiItemView.getData().setOrderID(listBean.getOrderID());
                                listOrderDetailBeanMultiItemView.getData().setOrderPaidPrice((double) listBean.getOrderPaidPrice());
                                listOrderDetailBeanMultiItemView.getData().setOrderCode(listBean.getOrderCode());
                                listOrderDetailBeanMultiItemView.setOrderId(listBean.getOrderID());
                                listOrderDetailBeanMultiItemView.setParentOrderID(listBean.getParentOrderID());
                                listOrderDetailBeanMultiItemView.setOrderPaidPrice(listBean.getOrderPaidPrice());
                                listOrderDetailBeanMultiItemView.setOrderCode(listBean.getOrderCode());
                                data.add(listOrderDetailBeanMultiItemView);
                            }
                            MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemView = new MultiItemView<>(MultiItemView.FOOTER);
                            listOrderDetailBeanMultiItemView.setCartNum(listBean.getCartNum());
                            listOrderDetailBeanMultiItemView.setOrderPaidPrice(listBean.getOrderPaidPrice());
                            listOrderDetailBeanMultiItemView.setFreight(listBean.getFreight());
                            listOrderDetailBeanMultiItemView.setOrderId(listBean.getOrderID());
                            listOrderDetailBeanMultiItemView.setParentOrderID(listBean.getParentOrderID());
                            listOrderDetailBeanMultiItemView.setOrderPaidPrice(listBean.getOrderPaidPrice());
                            listOrderDetailBeanMultiItemView.setOrderCode(listBean.getOrderCode());
                            data.add(listOrderDetailBeanMultiItemView);

                            setOperationMenu(MultiItemView.OPERATION,listBean.getOrderStatus(),listBean.getOrderID(),listBean.getOrderPrice(),listBean);//添加操作菜单
                        }
                        adapter.notifyDataSetChanged();
                        if(currentPageNumber< entity.getPageRowNumber()){
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
                        ToastUtils.showToast(getActivity(), entity.getAlertMessage());
                    }

                }

                @Override
                public void onError() {
                    orderListFrRefresh.setRefreshing(false);
                    ToastUtils.showToast(getActivity(),getString(R.string.system_error));
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
            }).getEntityData(getActivity(),HttpURL.HTTP_POST_ORDER_QUERY,json);
        }catch (Exception e){

        }

    }

    /**
     * 设置操作menu
     * @param viewType
     * @param status
     * @param orderId
     * @param price
     */
    private void setOperationMenu(int viewType,int status,int orderId,float price,ResponseQueryOrderList.ListOrderBean  bean){
        String msg1,msg2;
        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                msg1="取消订单";
                msg2="付款";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                msg1="";
                msg2="";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_LOCAL:
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                msg1="查看物流";
                msg2="";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                msg1="删除订单";
                msg2="评价";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_COMMENT://已评价
            case Constant.ORDER_STATUS.STATUS_PAY_OUT://逾期未取
            case Constant.ORDER_STATUS.STATUS_PAY_EXIT://取消成功
            case Constant.ORDER_STATUS.STATUS_PAY_REFUNDED://退款成功
                msg1="删除订单";
                msg2="";
                break;
            default:
                msg1="";
                msg2="";
                break;
        }
        MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean> listOrderDetailBeanMultiItemViewFoot= new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(viewType,msg1,msg2,status,orderId,price,bean);
        listOrderDetailBeanMultiItemViewFoot.setOrderId(bean.getOrderID());
        listOrderDetailBeanMultiItemViewFoot.setParentOrderID(bean.getParentOrderID());
        listOrderDetailBeanMultiItemViewFoot.setOrderPaidPrice((float) bean.getOrderPaidPrice());
        listOrderDetailBeanMultiItemViewFoot.setOrderCode(bean.getOrderCode());
        data.add(listOrderDetailBeanMultiItemViewFoot);
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
            case Constant.ORDER_STATUS.STATUS_PAY_COMMENT://已评价
                return "已评价";
            case Constant.ORDER_STATUS.STATUS_PAY_OUT://逾期未取
                return "逾期未取";
            case Constant.ORDER_STATUS.STATUS_PAY_EXIT://取消成功
                return "取消成功";
            default:
                return "未知状态";
        }
    }

    @Override
    public void onRefresh() {
        orderListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        data.clear();
//        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        adapter.notifyDataSetChanged();
        requestOrderList(orderType);
    }

    /**
     * 自动刷新列表
     */
    public void onAutoRefresh(){
        orderListFrRefresh.post(new Runnable() {
            @Override
            public void run() {
                orderListFrRefresh.setRefreshing(true);
                currentPageNumber=1;
                data.clear();
//                adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
                adapter.notifyDataSetChanged();
                requestOrderList(orderType);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        requestOrderList(orderType);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(data.size()<=0){
            onAutoRefresh();
            return;
        }
        switch (view.getId()){
            case R.id.order_list_item_opera_btn1://第一个按钮
                checkOperation(0,position);
                break;
            case R.id.order_list_item_opera_btn2://第二个按钮
                checkOperation(1,position);
                break;


        }

    }

    /**
     * 订单操作
     * @param btnType
     * @param position
     */
    private void checkOperation(int btnType,int position){
        int status= data.get(position).getStatus();

        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                if (btnType==1){//
//                    if(showDialog("确认付款 "+data.get(position).getOrdeId()))
                    Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_PAY)
                            .with("OrderID",data.get(position).getParentOrderID())
                            .with("orderCode",data.get(position).getOrderCode()+"")
                            .with("price",(float)data.get(position).getOrderPaidPrice())
                            .go(getActivity());
//                    Log.i("orderCode",entity.getList_Order().get(position).getOrderCode()+"---"+entity.getList_Order().get(position).getOrderPrice());
                }else {
//                    if(showDialog("确认取消订单 "+data.get(position).getOrdeId()))
                    operationProduct(data.get(position).getOrderId(),-3);
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                if (btnType==1){//
//                    ToastUtils.showToast(getActivity(),"提醒发货");
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_LOCAL:
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                if (btnType==1){//
                    ToastUtils.showToast(getActivity(),"确认收货");
//                    if(showDialog("确认收货 "+data.get(position).getOrdeId()))
                    operationProduct(data.get(position).getOrderId(),4);
                }else {
//                    ToastUtils.showToast(getActivity(),"查看物流");
                    Intent intent=new Intent(getActivity(), LogisticsActivity.class);
                    intent.putExtra("orderID",data.get(position).getOrderId());
                    Log.i("sodoasoda",data.get(position).getOrderId()+"");
                    startActivity(intent);
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                if (btnType==1){//
                    ResponseQueryOrderList.ListOrderBean   bean=data.get(position).getBean();
                    if(bean!=null){
                        Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_COMMENT)
                                .with("data",bean)
                                .go(getActivity());
                    }

                }else {
//                    if(showDialog("确认删除订单 "+data.get(position).getOrdeId()))
                    deleteOrder(data.get(position).getOrderId());
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_COMMENT://已评价
            case Constant.ORDER_STATUS.STATUS_PAY_OUT://逾期未取
            case Constant.ORDER_STATUS.STATUS_PAY_EXIT://取消成功
            case Constant.ORDER_STATUS.STATUS_PAY_REFUNDED://退款成功
                if (btnType==1){//
                    ResponseQueryOrderList.ListOrderBean   bean=data.get(position).getBean();
                    if(bean!=null){
                        Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_COMMENT)
                                .with("data",bean)
                                .go(getActivity());
                    }

                }else {
                    ToastUtils.showToast(getActivity(),"删除订单");
//                    if(showDialog("确认删除订单 "+data.get(position).getOrdeId()))
                    deleteOrder(data.get(position).getOrderId());
                }
                break;
        }
    }

    /**
     * 改变订单状态  收货，取消
     * @param orderId
     * @param status
     */
    private void operationProduct(long orderId,int status){
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("entityOrder",new RequestOrderOperation(new RequestOrderOperation.OrderStatus(status,orderId)));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean entity=new Gson().fromJson(s,BaseResponseBean.class);
                showToast(entity.getAlertMessage());
                if(entity.getStatusCode()==1){
                    onAutoRefresh();
                }else {
                    showToast(entity.getAlertMessage());
                }
            }

            @Override
            public void onError() {
                showToast(getString(R.string.system_error));
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
        }).getEntityData(getActivity(),HttpURL.HTTP_POST_ORDER_EDIT,json);
        }catch (Exception e){

        }
    }

    /**
     * 删除订单
     * @param orderId
     */
    private void deleteOrder(long orderId){
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("entityOrder",new RequestOrderOperation(new RequestOrderOperation.OrderStatus(orderType,orderId)));
        String json=new GsonBuilder().addSerializationExclusionStrategy(new FilterExclusionStrategy("orderStatus")).create().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean entity=new Gson().fromJson(s,BaseResponseBean.class);
                if(entity.getStatusCode()==1){
                    onAutoRefresh();
                }else {
                    showToast(entity.getAlertMessage());
                }
            }

            @Override
            public void onError() {
                showToast(getString(R.string.system_error));
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
        }).getEntityData(getActivity(),HttpURL.HTTP_POST_ORDER_DELETE,json);
        }catch (Exception e){

        }
    }

    private void showDialog(String msg,long orderId,int status){
         OkDialog dialog=new OkDialog(getActivity()).createDialog(msg, new OkDialog.OnDialogClickListener() {
            @Override
            public void onClickOk(OkDialog okDialog) {
                okDialog.dismiss();
            }

            @Override
            public void onClickCancel() {
            }
        });
        dialog.show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(getActivity(), OrderDetailsActivity.class);
        Log.i("orderid",data.get(position).getOrderId()+"-----------"+position);
        intent.putExtra("orderID",data.get(position).getOrderId());
        startActivity(intent);
    }
}
