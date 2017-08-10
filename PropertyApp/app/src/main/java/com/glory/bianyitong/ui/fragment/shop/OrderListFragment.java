package com.glory.bianyitong.ui.fragment.shop;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class OrderListFragment extends RootFragment {


    @BindView(R.id.order_list_fr_recycle)
    RecyclerView orderListFrRecycle;
    @BindView(R.id.order_list_fr_refresh)
    SwipeRefreshLayout orderListFrRefresh;


    private int orderType=0;

    private List<MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>> data=new ArrayList<>();
    private OrderListAdapter adapter;
    @Override
    protected void initView() {

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
            orderType=getArguments().getInt("type");

            LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
            orderListFrRecycle.setLayoutManager(layoutManager);
            adapter=new OrderListAdapter(data);
            orderListFrRecycle.setAdapter(adapter);
            requestOrderList(orderType);
        }
    }


    private void requestOrderList(int orderStatus){
        String json;
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("entityOrder",new RequestOrderList(new RequestOrderList.OrderStatus(orderStatus)));
        if(orderStatus== Constant.ORDER_STATUS.STATUS_PAY_ALL){//全部订单
            json=new GsonBuilder().addSerializationExclusionStrategy(new FilterExclusionStrategy("orderStatus")).create().toJson(map);
        }else {
            json=new Gson().toJson(map);
        }


        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryOrderList entity=new Gson().fromJson(s,ResponseQueryOrderList.class);
                if(entity.getStatusCode()==1){
                    for (ResponseQueryOrderList.ListOrderBean listBean:
                            entity.getList_Order()) {
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.TITLE,"便宜通生鲜",getStatusName(listBean.getOrderStatus())));
                        for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean bean:listBean.getListOrderDetail()
                             ) {
                            data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.BODY,bean));
                        }
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.FOOTER));
                        data.add(new MultiItemView<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(MultiItemView.OPERATION));
                    }
                    adapter.notifyDataSetChanged();

                }else {
                    ToastUtils.showToast(getActivity(),entity.getAlertMessage());
                }

            }

            @Override
            public void onError() {
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
}
