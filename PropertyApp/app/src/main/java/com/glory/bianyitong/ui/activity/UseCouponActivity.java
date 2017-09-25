package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.UseCouponListAdapter;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/9/22.
 * 使用优惠券
 */
public class UseCouponActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    List<ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>> data = new ArrayList<>();
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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private UseCouponListAdapter adapter;
    private final int REQUEST_CODE_COUPON=101;//选择地址,选择优惠券


    @Override
    protected int getContentId() {
        return R.layout.activity_usecoupon;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("选择优惠券", false, "");
        initView();

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new UseCouponListAdapter(R.layout.item_usecoupon, data,this);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        queryCouponForYES();
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2,R.id.tv_coupon})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;

            case R.id.tv_coupon://确定
                int money=0;
                ArrayList<Integer> list=new ArrayList<>();
                for (int i=0;i<data.size();i++){
                    if (data.get(i).getData().getClikcType()==2){
                        list.add(data.get(i).getData().getReceiveID());
                        money+=data.get(i).getData().getCoupon().getFreeMoney();
                    }
                }
                Intent intent=new Intent();

                intent.putIntegerArrayListExtra("data",list);
                intent.putExtra("money",money);
                setResult(RESULT_OK, intent);
                finish();
                break;

        }
    }
    //获取优惠券
    private void queryCouponForYES() {
        String json = getIntent().getStringExtra("json");
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryCouponList queryCouponListbean = new Gson().fromJson(s, ResponseQueryCouponList.class);
                if (queryCouponListbean.getStatusCode() == 1) {
                    for (ResponseQueryCouponList.ListCouponReceiveBean bean : queryCouponListbean.getListCouponReceive()) {
                        data.add(new ItemMenu<ResponseQueryCouponList.ListCouponReceiveBean>(bean));
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
        }).getEntityData(this, HttpURL.HTTP_POST_COUPON_QUERY_OTHERONE, json);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if ( data.get(position).getData().getCoupon().getPlatFormType()==1){//1是平台
            for (int i=0;i<data.size();i++){
                data.get(i).getData().setClikcType(1);
            }
            data.get(position).getData().setClikcType(2);
        } else if (data.get(position).getData().getCoupon().getPlatFormType() == 2) {//2是商家
            for (int i=0;i<data.size();i++){
                if (data.get(position).getData().getCoupon().getMerchantID()==data.get(i).getData().getCoupon().getMerchantID()){
                    data.get(i).getData().setClikcType(1);
                }else if (data.get(i).getData().getCoupon().getMerchantID()==0){
                    data.get(i).getData().setClikcType(1);
                }else {
                    data.get(position).getData().setClikcType(2);
                }
            }
            data.get(position).getData().setClikcType(2);

        }
        adapter.notifyDataSetChanged();
    }
}
