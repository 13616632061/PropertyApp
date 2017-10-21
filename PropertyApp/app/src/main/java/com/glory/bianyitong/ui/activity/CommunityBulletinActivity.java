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
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.CommunityAnnouceAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.SharedUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/11.
 * 社区公告
 */
public class CommunityBulletinActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    List<ItemMenu<listCommunityBulletinInfo.ListCommunityBulletinBean>> list = new ArrayList<>();
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
    @BindView(R.id.ac_ca_title_line)
    View acCaTitleLine;
    @BindView(R.id.listView_ca)
    RecyclerView listViewCa;
    @BindView(R.id.gonggao_list_refresh)
    SwipeRefreshLayout gonggaoListRefresh;
    private int currentPageNumber=1;

    private CommunityAnnouceAdapter adapter;


    @Override
    protected int getContentId() {
        return R.layout.ac_communtiy;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.community_announcement), false, getString(R.string.all_read)); //社区公告 全部已读

        leftReturnBtn.setOnClickListener(this);//返回
        ivTitleTextRight.setOnClickListener(this); //编辑  取消

        initview();

        if (Database.my_community != null && Database.my_community_List != null) {

        } else {
            Intent intent = new Intent(CommunityBulletinActivity.this, AddAreaCityActivity.class); //
            intent.putExtra("from", "index");
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        request();
    }

    private void initview() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listViewCa.setLayoutManager(linearLayoutManager);
        adapter = new CommunityAnnouceAdapter(R.layout.view_item_commutiy, list);

        adapter.setOnItemClickListener(this);
        listViewCa.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, listViewCa);
        gonggaoListRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                CommunityBulletinActivity.this.finish();
                break;
            case R.id.iv_title_text_right: /// 全部已读
                ServiceDialog.ButtonClickZoomInAnimation(ivTitleTextRight, 0.95f);
                List<String> communityRead;
                if (SharedUtil.getDataList("communityRead") != null) {
                    communityRead = SharedUtil.getDataList("communityRead");
                } else {
                    communityRead = new ArrayList<>();
                }
                if (list!= null) {
                    for (int i = 0; i <list.size(); i++) {
                        communityRead.add(list.get(i).getData().getBulletinID() + "");
                    }
                    SharedUtil.setDataList("communityRead", communityRead);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    private void request() { //请求社区公告
        adapter.setEnableLoadMore(false);
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("communityBulletin", new Object());
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    gonggaoListRefresh.setRefreshing(false);
                    listCommunityBulletinInfo cbinfo = new Gson().fromJson(s, listCommunityBulletinInfo.class);
                    if (cbinfo != null && cbinfo.getListCommunityBulletin() != null) {
                        for (listCommunityBulletinInfo.ListCommunityBulletinBean data : cbinfo.getListCommunityBulletin()) {
                            list.add(new ItemMenu<listCommunityBulletinInfo.ListCommunityBulletinBean>(data));
                        }
                        adapter.notifyDataSetChanged();

                        if(currentPageNumber<cbinfo.getPageRowNumber()){
                            adapter.setEnableLoadMore(true);
                            adapter.loadMoreComplete();
                        }else {
                            adapter.setEnableLoadMore(false);
                            adapter.loadMoreEnd();
                        }
                    }else {
                        if(list.size()<=0)
                            adapter.setEmptyView(R.layout.layout_empty_wushuju);
                        else {
                            adapter.loadMoreEnd();
                        }
                    }
                }

                @Override
                public void onError() {
                    gonggaoListRefresh.setRefreshing(false);
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
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY_AREA_NOTICE, json);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //根据 Tag 取消请求
//        OkGo.getInstance().cancelTag(this);
//        SharePreToolsKits.putString(CommunityBulletinActivity.this, Constant.bulletinID, Database.readbulletinid); //缓存已读消息
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, BulletinDetailsActivity.class);
                    intent.putExtra("bulletinId",list.get(position).getData().getBulletinID());
                    if (list.get(position).getData().getBulletinContent() != null) {

                        intent.putExtra("bulletinContent", list.get(position).getData().getBulletinContent());
                    }else {
                        intent.putExtra("bulletinContent", "");
                    }
                    if (list.get(position).getData().getBulletinTittle() != null) {
                        intent.putExtra("bulletinTittle", list.get(position).getData().getBulletinTittle());
                    } else {
                        intent.putExtra("bulletinTittle", "");
                    }

                    if (list.get(position).getData().getBulletinDatetime() != null) {
                        intent.putExtra("bulletinDatetime", list.get(position).getData().getBulletinDatetime().substring(0, 10));
                    } else {
                        intent.putExtra("bulletinDatetime", "");
                    }
                    intent.putExtra("PushID", 0);
                    startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        request();
    }

    @Override
    public void onRefresh() {
        gonggaoListRefresh.setRefreshing(true);
        currentPageNumber=1;
        list.clear();
        adapter.notifyDataSetChanged();
        request();
    }
}
