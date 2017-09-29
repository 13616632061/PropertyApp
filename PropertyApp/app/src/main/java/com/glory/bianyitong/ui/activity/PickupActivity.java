package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.GodownDetailInfo;
import com.glory.bianyitong.bean.PickupInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.sdk.share.ShareUtil;
import com.glory.bianyitong.ui.adapter.PickupAdapter;
import com.glory.bianyitong.ui.adapter.shop.FirmOrderAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ShareSdkDialog;
import com.glory.bianyitong.util.SharedUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/5/3.
 * 取件
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_PICKUP, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PickupActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;


    PickupAdapter pickupAdapter;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_title_text_left)
    TextView ivTitleTextLeft;
    @BindView(R.id.title_ac_text)
    TextView titleAcText;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String share_url;

    private List<ItemMenu<PickupInfo.ListOrderBean>> data=new ArrayList<>();
    private ShareSdkDialog dialog;
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://微信好友
//                    Log.i("resultString", "tittle---------" + tittle);
//                    Log.i("resultString", "subTittle---------" + subTittle);
//                    Log.i("resultString", "share_url---------" + share_url);
                    Log.i("resultString", "Constant.logo_path---------" + Constant.logo_path);
                    ShareUtil.showShareOne(PickupActivity.this, msg.obj.toString(), true, "标题", "内容", share_url, Constant.logo_path);
                    break;
            }
        }
    };


    @Override
    protected int getContentId() {
        return R.layout.lay_listview;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.pickup), true, ""); //取件

        if (!SharedUtil.getBoolean("login")){
            finish();
            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(this);
        }

        pickList();
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        pickupAdapter = new PickupAdapter(R.layout.view_item_pickup, data);
        recyclerView.setLayoutManager(layoutManager);
        pickupAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(pickupAdapter);

    }

    /**
     * 列表数据
     */
    private void pickList() {
        data.clear();
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        final String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                PickupInfo detail = new Gson().fromJson(s, PickupInfo.class);
                if (detail.getStatusCode() == 1) {
                    for (PickupInfo.ListOrderBean bean:detail.getListOrder()){
                        data.add(new ItemMenu<PickupInfo.ListOrderBean>(bean));
                    }
                    pickupAdapter.notifyDataSetChanged();
                } else {
                    showShort(detail.getAlertMessage());
                }
            }


            @Override
            public void onError() {
                showShort("系统异常");
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOP_QUERY_PCIKUP, json);
    }

    /**
     * 开柜
     */
    private void openCabinet(int orderID) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("orderID",orderID);
        final String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                PickupInfo detail = new Gson().fromJson(s, PickupInfo.class);
                if (detail.getStatusCode() == 1) {
                    showShort(detail.getAlertMessage());
                    pickList();
                } else {
                    showShort(detail.getAlertMessage());
                }
                if (detail.getAccessToken()!=""){
                    Database.accessToken=detail.getAccessToken();
                }
            }


            @Override
            public void onError() {
                showShort("系统异常");
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOP_QUERY_CABINET_OPEN, json);
    }


    /**
     * 分享链接
     */
    private void getShareURL(int orderID) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("orderID",orderID);
        final String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                PickupInfo detail = new Gson().fromJson(s, PickupInfo.class);
                if (detail.getStatusCode() == 1) {
                    share_url=detail.getShareURL();
                    dialog = new ShareSdkDialog(PickupActivity.this, mhandler,true);
                    // 显示窗口
                    dialog.showAtLocation(findViewById(R.id.lay_fg_my),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                } else {
                    showShort(detail.getAlertMessage());
                }
            }


            @Override
            public void onError() {
                showShort("系统异常");
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOP_QUERY_PCIKUPADD, json);
    }
    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickBtn(View view){
        switch (view.getId()){
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.iv_share:
                getShareURL(data.get(position).getData().getOrderID());

                break;
            case R.id.iv_open_the_cabinet:
                openCabinet(data.get(position).getData().getOrderID());
                break;
        }
    }
}
