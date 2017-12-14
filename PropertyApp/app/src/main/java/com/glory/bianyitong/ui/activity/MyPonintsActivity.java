package com.glory.bianyitong.ui.activity;

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
import com.glory.bianyitong.bean.MyPointsInfo;
import com.glory.bianyitong.bean.MyPointsListInfo;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.CollectionAdapter;
import com.glory.bianyitong.ui.adapter.MyPonintsAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/12/12.
 * 我的积分
 */
public class MyPonintsActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {
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
    @BindView(R.id.points)
    TextView points;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    private List<ItemMenu<MyPointsListInfo.ListUserPointDetailBean>> list=new ArrayList<>();
    private int currentPageNumber=1;
    private MyPonintsAdapter myPonintsAdapter;

    @Override
    protected int getContentId() {

        return R.layout.activity_mypoints;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("我的积分", false, "");//我的积分
        getMyPoint();
        initview();
    }
    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
        }
    }
    private void initview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        myPonintsAdapter = new MyPonintsAdapter(this,R.layout.view_item_myponintslist, list);
        recyclerview.setAdapter(myPonintsAdapter);
        myPonintsAdapter.setOnLoadMoreListener(this,recyclerview);
        onRefrush();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void onRefrush() {
//        collectionAdapter.notifyDataSetChanged();
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("currentPageNumber",currentPageNumber);
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    MyPointsListInfo bean = new Gson().fromJson(s, MyPointsListInfo.class);
                    if (bean.getStatusCode() == 1) {
                        for (MyPointsListInfo.ListUserPointDetailBean beans : bean.getListUserPointDetail()) {
                            list.add(new ItemMenu<MyPointsListInfo.ListUserPointDetailBean>(beans));
                        }
                        myPonintsAdapter.notifyDataSetChanged();

                        if(currentPageNumber<bean.getPageRowNumber()){
                            myPonintsAdapter.setEnableLoadMore(true);
                            myPonintsAdapter.loadMoreComplete();
                        }else {
                            myPonintsAdapter.setEnableLoadMore(false);
                            myPonintsAdapter.loadMoreEnd();
                        }
                    }else if(bean.getStatusCode()==2){
                        if(list.size()<=0)
                            myPonintsAdapter.setEmptyView(R.layout.layout_empty_wushuju);
                        else {
                            myPonintsAdapter.loadMoreEnd();
                        }

//                    ToastUtils.showToast(getActivity(),entity.getAlertMessage());
                    }else{
                        ToastUtils.showToast(getApplicationContext(),bean.getAlertMessage());
                    }
                }

                @Override
                public void onError() {
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
            }).getEntityData(this, HttpURL.HTTP_POST_APIUSERPOINTDETAIL_QUERY, json);
        }catch (Exception e){

        }
    }

    private void getMyPoint() {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    MyPointsInfo bean = new Gson().fromJson(s, MyPointsInfo.class);
                    if (bean.getStatusCode() == 1) {
                        points.setText(bean.getUserPoint().getRealtimePoints()+"");
                    }else{
                        ToastUtils.showToast(getApplicationContext(),bean.getAlertMessage());
                    }
                }

                @Override
                public void onError() {
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
            }).getEntityData(this, HttpURL.HTTP_POST_APIUSERPOINT_QUERY, json);
        }catch (Exception e){

        }
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        onRefrush();
    }

}
