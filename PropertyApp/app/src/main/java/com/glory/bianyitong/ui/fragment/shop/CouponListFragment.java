package com.glory.bianyitong.ui.fragment.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryCouponList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.shop.CouponListAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.fragment.RootFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14.
 */

public class CouponListFragment extends RootFragment {
    @BindView(R.id.coupon_fr_list)
    RecyclerView couponFrList;

    // TODO: 2017/7/14 优惠券状态 
    private int type=-1;
    // TODO: 2017/7/14 优惠券数量
    private int num=0;
    // TODO: 2017/7/14 来源 1:我的优惠券 2:可用优惠券(提交订单页面)
    private int source=1;

    private List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data=new ArrayList<>();
    private CouponListAdapter adapter;
    @Override
    protected void initView() {
        type=getArguments().getInt("type");
        num=getArguments().getInt("num");
        source=getArguments().getInt("source");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        adapter=new CouponListAdapter(R.layout.item_fr_couponlist,data);
        couponFrList.setLayoutManager(layoutManager);
        couponFrList.setAdapter(adapter);
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_coupon_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            data.clear();
            if(num>0) {
                requestCouponList(type);
            }

        }
    }

    private void requestCouponList(int status){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("couponReceive",new RequestQueryCouponList(status));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryCouponList bean=new Gson().fromJson(s,ResponseQueryCouponList.class);
                if(bean.getStatusCode()==1){
                    for (ResponseQueryCouponList.ListCouponReceiveBean entity:bean.getListCouponReceive()
                         ) {
                        data.add(new ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>(entity));
                    }
                    adapter.notifyDataSetChanged();
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
        }).getEntityData(HttpURL.HTTP_POST_COUPON_QUERY_LIST,json);

    }

}
