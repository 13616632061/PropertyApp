package com.glory.bianyitong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.CollectionNiInfo;
import com.glory.bianyitong.bean.RefundInfo;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.CollctionNiAdapter;
import com.glory.bianyitong.ui.adapter.CollectionAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/10/30.
 * 收藏近邻
 */
public class CollectionNiActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
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
    @BindView(R.id.all_button)
    CheckBox allButton;
    @BindView(R.id.ll_last_month)
    LinearLayout llLastMonth;
    @BindView(R.id.rec_collection)
    RecyclerView recyclerView;
    @BindView(R.id.fresh_list_fr_refresh)
    SwipeRefreshLayout freshListFrRefresh;
    private ArrayList<ItemMenu<CollectionNiInfo.ListNeighborhoodCollectBean>> list = new ArrayList<>();
    private CollctionNiAdapter adapter;
    private int currentPageNumber=1;
    public static CallBack callBack;

    @Override
    protected int getContentId() {
        return R.layout.activity_collectionni;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("收藏", true, "");//收藏
        initview();
        callBack = new CallBack() {
            @Override
            public void delCollection(int position) {
                delete(position);
            }


        };
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

    /**
     * 删除
     */
    private void delete(final int position) { //删除
        try {
            Map<String,Object> map=new BaseRequestBean().getBaseRequest();
            Map<String,Object> map2=new HashMap<>();
            map2.put("neighborhoodCollectID",list.get(position).getData().getNeighborhoodCollectID());
            map.put("neighborhoodCollect",map2);
            String json=new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    ResponseFriendDetail detail=new Gson().fromJson(s,ResponseFriendDetail.class);
                    if (detail.getStatusCode()==1){
//                        recyclerView.removeViewAt(position);
                        adapter.remove(position);
                        adapter.notifyDataSetChanged();

                    }

                    showShort(detail.getAlertMessage());

                }

                @Override
                public void onError() {

                }
                @Override
                public void parseError() {}
                @Override
                public void onBefore() { }
                @Override
                public void onAfter() {
                }
            }).getEntityData(this,HttpURL.HTTP_POST_FRIEND_APINEIGHCOLLECTION_DELETE,json);
        }catch (Exception e){

        }
    }

    private void initview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CollctionNiAdapter(this,R.layout.item_collectionni, list);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnLoadMoreListener(this,recyclerView);
        recyclerView.setAdapter(adapter);
        freshListFrRefresh.setOnRefreshListener(this);
        onRefrush();
    }

    private void onRefrush() {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("currentPageNumber",currentPageNumber);
            map.put("neighborhoodCollect",new Object());
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    freshListFrRefresh.setRefreshing(false);
                    CollectionNiInfo bean = new Gson().fromJson(s, CollectionNiInfo.class);
                    if (bean.getStatusCode() == 1) {
                        for (CollectionNiInfo.ListNeighborhoodCollectBean beans : bean.getListNeighborhoodCollect() ) {
                            list.add(new ItemMenu<CollectionNiInfo.ListNeighborhoodCollectBean>(beans));
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
                            adapter.setEmptyView(R.layout.layout_empty_wushuju);
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
            }).getEntityData(this, HttpURL.HTTP_POST_COLLECTIONNI_QUERY, json);
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
        adapter.notifyDataSetChanged();
        onRefrush();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.collection_pic:
                ArrayList<String> pictureList=new ArrayList<>();
                pictureList.add(list.get(position).getData().getCollectContent());
                Intent intent = new Intent();
                intent.setClass(CollectionNiActivity.this, ImagePagerActivity.class);
                intent.putExtra("pictureList", pictureList);
                startActivity(intent);
                break;
//            case R.id.tv_shop_delete:
//                delete(position);
//                break;
            case R.id.llear:
                Intent intent1=new Intent(CollectionNiActivity.this,CollectionNiDetailsActivity.class);
                intent1.putExtra("createDate",list.get(position).getData().getCreateDate());
                intent1.putExtra("collectContent",list.get(position).getData().getCollectContent());
                intent1.putExtra("collectUserName",list.get(position).getData().getCollectUserName());
                intent1.putExtra("collectUserPicture",list.get(position).getData().getCollectUserPicture());
                intent1.putExtra("collectType",list.get(position).getData().getCollectType());
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    public interface CallBack{
        public void delCollection(int position);
    }
}
