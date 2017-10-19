package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.bean.entity.request.RequestShoppingCartAdd;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.CollectionAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收藏商品
 * Created by lucy on 2017/6/29.
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_COLLECTION_LIST, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class CollectionActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @BindView(R.id.fresh_list_fr_refresh)
    SwipeRefreshLayout freshListFrRefresh;
    private RecyclerView recyclerView;
    private LinearLayout ll_cancle_collection;
    private LinearLayout ll_last_month;
    private boolean EDIT = true;
    private CollectionAdapter collectionAdapter;
    private CheckBox all_button;
    private List<ItemMenu<RefundInfo.ListFreshCollectionBean>> list = new ArrayList<>();
    private int currentPageNumber=1;

    @Override
    protected void init() {
        super.init();
        inintTitle("收藏", false, "编辑");//收藏
        initview();

    }

    @Override
    protected int getContentId() {
        return R.layout.activity_collection;
    }

    private void initview() {

        all_button = (CheckBox) findViewById(R.id.all_button);
        ll_cancle_collection = (LinearLayout) findViewById(R.id.ll_cancle_collection);
        ll_last_month = (LinearLayout) findViewById(R.id.ll_last_month);

        all_button.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rec_collection);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        collectionAdapter = new CollectionAdapter(this, R.layout.item_collection, list);
        collectionAdapter.setOnItemChildClickListener(this);
        collectionAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(collectionAdapter);
        collectionAdapter.setOnLoadMoreListener(this,recyclerView);
        freshListFrRefresh.setOnRefreshListener(this);
        onRefrush();

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
                freshListFrRefresh.setRefreshing(false);
                RefundInfo bean = new Gson().fromJson(s, RefundInfo.class);
                if (bean.getStatusCode() == 1) {
                    for (RefundInfo.ListFreshCollectionBean beans : bean.getListFreshCollection()) {
                        list.add(new ItemMenu<RefundInfo.ListFreshCollectionBean>(beans));
                    }
                    collectionAdapter.notifyDataSetChanged();

                    if(currentPageNumber<bean.getPageRowNumber()){
                        collectionAdapter.setEnableLoadMore(true);
                        collectionAdapter.loadMoreComplete();
                    }else {
                        collectionAdapter.setEnableLoadMore(false);
                        collectionAdapter.loadMoreEnd();
                    }
                }else if(bean.getStatusCode()==2){
                    if(list.size()<=0)
                        collectionAdapter.setEmptyView(R.layout.layout_empty_wushuju);
                    else {
                        collectionAdapter.loadMoreEnd();
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
        }).getEntityData(this, HttpURL.HTTP_POST_COLLECTION_QUERY, json);
        }catch (Exception e){

        }
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right, R.id.all_button, R.id.tv_cancel})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_cancel://取消收藏
                deleteCollection();
                break;
            case R.id.iv_title_text_right://编辑按钮
                if (EDIT) {
                    EDIT = false;
                    ll_cancle_collection.setVisibility(View.VISIBLE);
                    ll_last_month.setVisibility(View.VISIBLE);
                    ivTitleTextRight.setText(getString(R.string.carry_out));
                    collectionAdapter.setEDIT(EDIT);
                    collectionAdapter.notifyDataSetChanged();
                } else {
                    EDIT = true;
                    ll_cancle_collection.setVisibility(View.GONE);
                    ll_last_month.setVisibility(View.GONE);
                    ivTitleTextRight.setText(getString(R.string.edit));
                    collectionAdapter.setEDIT(EDIT);
                    collectionAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.all_button://全选按钮
                if (all_button.isChecked()) {
//                    all_button.setChecked(true);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).getData().setFlag(true);
                        collectionAdapter.notifyDataSetChanged();
                    }
                } else {
//                    all_button.setChecked(false);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).getData().setFlag(false);
                        collectionAdapter.notifyDataSetChanged();
                    }
                }

                break;

        }
    }

    /**
     * 取消收藏
     */
    private void deleteCollection() {
        try{
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        List<Integer> freshId = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getData().isFlag()) {
                freshId.add(list.get(i).getData().getFreshID());
            }
        }
        map.put("listFreshID", freshId);
        String json = new Gson().toJson(map);
        if (freshId.size() > 0) {
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                    if (bean.getStatusCode() == 1) {

                        onRefresh();
                    }
                    showShort(bean.getAlertMessage());
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
            }).getEntityData(this, HttpURL.HTTP_POST_COLLECTION_DELETE, json);
        }
        }catch (Exception e){

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
        switch (view.getId()) {
            case R.id.add_gouwuche:
                if (!list.get(position).getData().getFresh().isEnable() && list.get(position).getData().getFresh().getIsDelete()) {
                    ToastUtils.showToast(CollectionActivity.this, "商品已下架或删除，不能加入购物车");
                } else {
                    addShopCart(position);

                }
                break;
        }
    }

    private void addShopCart(int position) {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shoppingCart", new RequestShoppingCartAdd(list.get(position).getData().getFreshID(), list.get(position).getData().getFresh().getFreshTypeID(), 1, list.get(position).getData().getFresh().getFreshPrice()));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
                    ToastUtils.showToast(CollectionActivity.this, "添加购物车成功");

                } else {
                    ToastUtils.showToast(CollectionActivity.this, bean.getAlertMessage());
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_ADD, json);
        }catch (Exception e){

        }
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        onRefrush();
    }

    @Override
    public void onRefresh() {
        freshListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        list.clear();
//        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        collectionAdapter.notifyDataSetChanged();
        onRefrush();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ResponseSearchFresh.ListfreshBean product=new ResponseSearchFresh.ListfreshBean();
        product.setFreshID(list.get(position).getData().getFreshID());
        Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_DETAIL)
                .with("data", product)
                .go(this);
    }
}
