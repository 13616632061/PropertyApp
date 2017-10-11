package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.LogisticsInfo;
import com.glory.bianyitong.bean.entity.response.ResponseSubmitOrder;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.LogisticsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/9/22.
 * //查看物流
 */
public class LogisticsActivity extends BaseActivity {
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
    @BindView(R.id.iv_list_item_goods_pic)
    ImageView ivListItemGoodsPic;
    @BindView(R.id.tv_list_item_goods_name)
    TextView tvListItemGoodsName;
    @BindView(R.id.tv_list_item_goods_price)
    TextView tvListItemGoodsPrice;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<ItemMenu<LogisticsInfo.OrderBean.ListStatusRecordBean>> data=new ArrayList<>();
    private LogisticsAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("查看物流", false, "");
        initView();

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new LogisticsAdapter(R.layout.item_logistics, data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getLogistics();
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

    private void getLogistics() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        Map<String, Object> maps = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        maps.put("orderID", getIntent().getIntExtra("orderID",0));
        map2.put("order",maps);
        map.put("entityOrder", map2);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                LogisticsInfo bean = new Gson().fromJson(s, LogisticsInfo.class);
                if (bean.getStatusCode() == 1) {
                    for (LogisticsInfo.OrderBean.ListStatusRecordBean enty:bean.getOrder().getListStatusRecord()){
                        data.add(new ItemMenu<LogisticsInfo.OrderBean.ListStatusRecordBean>(enty));
                    }
                    Glide.with(getApplicationContext()).load(bean.getOrder().getFreshPicture()).error(R.drawable.wait).placeholder(R.drawable.wait).into(ivListItemGoodsPic);
                    tvListItemGoodsPrice.setText("订单编号："+bean.getOrder().getOrderCode());
                    //订单状态  0 已提交订单1 备货中 2 配送中3 已配送 9 分拣中
                    switch (bean.getOrder().getListStatusRecord().get(data.size()-1).getOrderStatus()){
                        case 0:
                            tvListItemGoodsName.setText("订单状态:已提交订单");
                            break;
                        case 1:
                            tvListItemGoodsName.setText("订单状态:备货中");
                            break;
                        case 2:
                            tvListItemGoodsName.setText("订单状态:配送中");
                            break;
                        case 3:
                            tvListItemGoodsName.setText("订单状态:已配送");
                            break;
                        case 9:
                            tvListItemGoodsName.setText("订单状态:分拣中");
                            break;
                    }
                    adapter.notifyDataSetChanged();
                } else {
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
        }).getEntityData(this, HttpURL.HTTP_POST_ORDER_OTHERTWO, json);
        }catch (Exception e){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
