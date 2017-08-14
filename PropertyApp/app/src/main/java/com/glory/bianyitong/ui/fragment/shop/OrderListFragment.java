package com.glory.bianyitong.ui.fragment.shop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestOrderList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.MultiItemView;
import com.glory.bianyitong.ui.adapter.shop.OrderListAdapter;
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

public class OrderListFragment extends RootFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemChildClickListener{


    @BindView(R.id.order_list_fr_recycle)
    RecyclerView orderListFrRecycle;
    @BindView(R.id.order_list_fr_refresh)
    SwipeRefreshLayout orderListFrRefresh;


    private int orderType=0;
    private int currentPageNumber=1;

    private List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data=new ArrayList<>();
    private OrderListAdapter adapter;
    @Override
    protected void initView() {
        orderType=getArguments().getInt("type");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        orderListFrRecycle.setLayoutManager(layoutManager);
        adapter=new OrderListAdapter(data);
        adapter.setOnItemChildClickListener(this);
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
        if(orderStatus== Constant.ORDER_STATUS.STATUS_PAY_ALL){//全部订单
            json=new GsonBuilder().addSerializationExclusionStrategy(new FilterExclusionStrategy("orderStatus")).create().toJson(map);
        }else {
            json=new Gson().toJson(map);
        }


        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                orderListFrRefresh.setRefreshing(false);
                ResponseQueryOrderList entity=new Gson().fromJson(s,ResponseQueryOrderList.class);
                if(entity.getStatusCode()==1){
                    for (ResponseQueryOrderList.ListOrderBean listBean:
                            entity.getList_Order()) {
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.TITLE,"便宜通生鲜",getStatusName(listBean.getOrderStatus())));
                        for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean bean:listBean.getListOrderDetail()
                             ) {
                            data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.BODY,bean,listBean.getOrderStatus()));
                        }
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.FOOTER));
                        setOperationMenu(MultiItemView.OPERATION,listBean.getOrderStatus(),listBean.getOrderID());//添加操作菜单
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
                    ToastUtils.showToast(getActivity(),entity.getAlertMessage());
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
        }).getEntityData(HttpURL.HTTP_POST_ORDER_QUERY,json);
    }

    private void setOperationMenu(int viewType,int status,int orderId){
        String msg1,msg2;
        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                msg1="取消订单";
                msg2="付款";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                msg1="";
                msg2="提醒发货";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                msg1="查看物流";
                msg2="确认收货";
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                msg1="删除订单";
                msg2="评价";
                break;
            default:
                msg1="";
                msg2="";
                break;
        }
        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(viewType,msg1,msg2,status,orderId));
    }

    private String getStatusName(int status){
        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                return "等待买家付款";
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                return "等待卖家发货";
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                return "卖家已发货";
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                return "交易成功";
            default:
                return "未知状态";
        }
    }

    @Override
    public void onRefresh() {
        orderListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        data.clear();
        requestOrderList(orderType);
    }

    public void onAutoRefresh(){
        orderListFrRefresh.post(new Runnable() {
            @Override
            public void run() {
                orderListFrRefresh.setRefreshing(true);
                currentPageNumber=1;
                data.clear();
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

    private void checkOperation(int btnType,int position){
        int status= data.get(position).getStatus();

        switch (status){
            case Constant.ORDER_STATUS.STATUS_PAY_WAIT://待付款
                if (btnType==1){//
                    ToastUtils.showToast(getActivity(),"付款");
                }else {
                    ToastUtils.showToast(getActivity(),"取消订单");
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_FINSH://待发货
                if (btnType==1){//
                    ToastUtils.showToast(getActivity(),"提醒发货");
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_SEND://待收货
                if (btnType==1){//
                    ToastUtils.showToast(getActivity(),"确认收货");
                }else {
                    ToastUtils.showToast(getActivity(),"查看物流");
                }
                break;
            case Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT://待评价
                if (btnType==1){//
                    ToastUtils.showToast(getActivity(),"评价");
                }else {
                    ToastUtils.showToast(getActivity(),"删除订单");
                }
                break;
        }
    }
}
