package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.InspectionReportInfo;
import com.glory.bianyitong.bean.OrderDetailsInfo;
import com.glory.bianyitong.bean.OrderDetailsRequest;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.InspectionReportAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/9/28.
 */
public class InspectionReportActivity extends BaseActivity {

    List<ItemMenu<InspectionReportInfo.ListQualityPicBean>> data = new ArrayList<>();
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
    private InspectionReportAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_inspectionreport;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("质检报告", false, "");
        initView();

    }

    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new InspectionReportAdapter(R.layout.item_inspectionreport, data, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getInspection();
    }

    private void getInspection() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("qualityID", getIntent().getIntExtra("qualityID",0));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                InspectionReportInfo bean = new Gson().fromJson(s, InspectionReportInfo.class);
                if (bean.getStatusCode() == 1) {
                    for (InspectionReportInfo.ListQualityPicBean enty:bean.getListQualityPic()){
                        data.add(new ItemMenu<InspectionReportInfo.ListQualityPicBean>(enty));
                    }
                    adapter.notifyDataSetChanged();
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
        }).getEntityData(this, HttpURL.HTTP_POST_ORDER_QUALITY_QUERY, json);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
