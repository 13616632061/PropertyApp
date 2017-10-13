package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.MyBillInfo;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.MyBillAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/10/13.
 * //我的账单
 */
public class MyBillActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(R.id.rec_collection)
    RecyclerView recyclerView;
    @BindView(R.id.fresh_list_fr_refresh)
    SwipeRefreshLayout freshListFrRefresh;

    private List<ItemMenu<MyBillInfo.ListUserBillBean>> list=new ArrayList<>();
    private int currentPageNumber=1;
    private MyBillAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_mybill;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("我的账单", true, "");//我的账单
        initview();
    }

    private void initview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyBillAdapter(R.layout.item_mybill, list);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
        freshListFrRefresh.setOnRefreshListener(this);
        onBill();
    }

    private void onBill() {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("currentPageNumber",currentPageNumber);
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    freshListFrRefresh.setRefreshing(false);
                    MyBillInfo bean = new Gson().fromJson(s, MyBillInfo.class);
                    if (bean.getStatusCode() == 1) {
                        for (MyBillInfo.ListUserBillBean beans : bean.getListUserBill()) {
                            list.add(new ItemMenu<MyBillInfo.ListUserBillBean>(beans));
                        }
                        adapter.notifyDataSetChanged();

                        if(currentPageNumber<bean.getPageRowNumber()){
                            adapter.setEnableLoadMore(true);
                            adapter.loadMoreComplete();
                        }else {
                            adapter.setEnableLoadMore(false);
                            adapter.loadMoreEnd();
                        }
                    }else if(bean.getStatusCode()==2){
                        if(list.size()<=0)
                            adapter.setEmptyView(R.layout.layout_empty_orderlist);
                        else {
                            adapter.loadMoreEnd();
                        }

//                    ToastUtils.showToast(getActivity(),entity.getAlertMessage());
                    }else{
                        ToastUtils.showToast(getApplicationContext(),bean.getAlertMessage());
                    }
                }

                @Override
                public void onError() {
                    freshListFrRefresh.setRefreshing(false);
                    ToastUtils.showToast(getApplicationContext(),getString(R.string.system_error));
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
            }).getEntityData(this, HttpURL.HTTP_POST_COUPON_APIUSERBILL, json);
        }catch (Exception e){

        }
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(this,BillDetailsActivity.class);
        intent.putExtra("userBillID",list.get(position).getData().getUserBillID());
        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        onBill();
    }

    @Override
    public void onRefresh() {
        freshListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        list.clear();
//        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        adapter.notifyDataSetChanged();
        onBill();
    }
}
