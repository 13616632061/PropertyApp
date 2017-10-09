package com.glory.bianyitong.ui.fragment.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.lazylibrary.util.StringUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.glory.bianyitong.ui.activity.shop.CouponListActivity.belongCalendar;
import static com.glory.bianyitong.ui.activity.shop.CouponListActivity.strToDateLong;

/**
 * Created by Administrator on 2017/7/14.
 */

public class CouponListFragment extends RootFragment implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.coupon_fr_list)
    RecyclerView couponFrList;

    // TODO: 2017/7/14 优惠券状态 
    private int type=-1;
    // TODO: 2017/7/14 优惠券数量
    private int num=0;
    // TODO: 2017/7/14 来源 1:我的优惠券 2:可用优惠券(提交订单页面)
    private int source=1;

    // TODO: 2017/7/18 来源数据
    private String couponJson;

    private List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data=new ArrayList<>();
    private CouponListAdapter adapter;
    private String jsonResponse=null;
    @Override
    protected void initView() {
        type=getArguments().getInt("type");
        num=getArguments().getInt("num");
        source=getArguments().getInt("source");
        couponJson=getArguments().getString("coupon");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        adapter=new CouponListAdapter(R.layout.item_fr_couponlist,data,type);
        couponFrList.setLayoutManager(layoutManager);
        couponFrList.setAdapter(adapter);
        adapter.bindToRecyclerView(couponFrList);
        adapter.setOnItemChildClickListener(this);
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
        if (jsonResponse!=null){
            formatData(jsonResponse);
        }else {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    jsonResponse = s;
                    formatData(s);
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
            }).getEntityData(getActivity(), HttpURL.HTTP_POST_COUPON_QUERY_LIST, json);
        }
    }

    private void formatData(String json){
        try {
            ResponseQueryCouponList bean = new Gson().fromJson(json, ResponseQueryCouponList.class);
            if (bean.getStatusCode() == 1) {
                for (ResponseQueryCouponList.ListCouponReceiveBean entity : bean.getListCouponReceive()) {

                    switch (type) {//（0未使用1已使用2已过期）
                        case 0:
                            if (entity.getCouponStatus() == 0 ) {
                                data.add(new ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>(entity));
                            }
                            break;

                        case 1:
                            if (entity.getCouponStatus() == 1) {// 0未使用 1已使用
                                data.add(new ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>(entity));
                            }

                            break;

                        case 2:
                            if (entity.getCouponStatus() == 2 ) {
                                data.add(new ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>(entity));
                            }
                            break;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception ex){

        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(source==2 && type==0){
            ResponseQueryCouponList.ListCouponReceiveBean bena=data.get(position).getData();
            if(bena!=null){
                Intent intent=new Intent();
                intent.putExtra("data",new Gson().toJson(bena));
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        }
    }
}
