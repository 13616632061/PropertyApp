package com.glory.bianyitong.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestNeighborhood;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.adapter.ConveniencePhoneAdapter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.activity.ReleaseDynamicActivity;
import com.glory.bianyitong.ui.adapter.NeighbourAdapter;
import com.glory.bianyitong.ui.dialog.CustomProgressDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/10.
 * 近邻
 */
public class NeighbourFragment extends BaseFragment {
    Context context;
    @BindView(R.id.iv_title_right)
    ImageView iv_title_right;
    @BindView(R.id.listView_neighbour)
    ListView listView_neighbour;
    @BindView(R.id.neighbour_pullToRefreshView)
    NewPullToRefreshView base_pullToRefreshView;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;
    //    private ArrayList<LinkedTreeMap<String, Object>> neighbourlist;
    private NeighbourAdapter mMainAdapter;
    private int index_page = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fg_neighbour, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        iv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Database.USER_MAP != null) {
                    Intent intent = new Intent(context, ReleaseDynamicActivity.class);//发布动态
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first));
                    Intent intent_login = new Intent();
                    intent_login.setClass(context, LoginActivity.class);
                    startActivity(intent_login);
                }
            }
        });
        view_loading = getActivity().getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        listView_neighbour.addFooterView(view_loading);
        listView_neighbour.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (Database.list_neighbour != null && have_GoodsList && !getGoodsListStart) {
                            getGoodsListStart = true;
                            loading_lay.setVisibility(View.VISIBLE);
                            index_page++;
                            request(index_page, false);
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
        });

        base_pullToRefreshView.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(NewPullToRefreshView view) {
                if (Database.list_neighbour != null) {
                    getGoodsListStart = true;
                    index_page = 0;//重置index_page
                    index_page++;
                    request(index_page, true);//刷新
                }
            }
        });

        if (Database.list_neighbour != null && Database.list_neighbour.size() > 0) {
            mMainAdapter = new NeighbourAdapter(context, Database.list_neighbour, "near");
            listView_neighbour.setAdapter(mMainAdapter);
        } else {
            progressDialog = ProgressDialog.show(context, "", getResources().getString(R.string.load), true);
            progressDialog.setCanceledOnTouchOutside(true);
            getGoodsListStart = true;
            loading_lay.setVisibility(View.VISIBLE);
            index_page++;
            request(index_page, false);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Database.isAddarea) {
            getGoodsListStart = true;
            index_page = 0;//重置index_page
            index_page++;
            request(index_page, true);//刷新
            Database.isAddarea = false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(String ss) {
        if (ss.equals("addComment")) {
//            ToastUtils.showToast(context, "刷新了...");
            getGoodsListStart = true;
            index_page = 0;//重置index_page
            index_page++;
            request(index_page, true);//刷新
        }
    }

    /**
     * 近邻查询
     * @param page
     * @param isrefresh
     */
    private void request(int page, final boolean isrefresh) {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("currentPageNumber",page);
        map.put("neighborhood",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                base_pullToRefreshView.onHeaderRefreshComplete();
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                ResponseFriendDetail detail=new Gson().fromJson(s,ResponseFriendDetail.class);
                if(detail.getStatusCode()==1){
                    List<ResponseFriendDetail.ListNeighborhoodBean> neighborlist = detail.getListNeighborhood();
                    //分页加载数据----------------------------------------------------
                    if (Database.list_neighbour == null) {
                        Database.list_neighbour = neighborlist;
                    } else {
                        if (isrefresh) {
                            if (Database.list_neighbour != null) {
                                Database.list_neighbour = null;
                                Database.list_neighbour = neighborlist;
                            }
                        }
                        if (Database.list_neighbour.size() != 0
                                && neighborlist.get(neighborlist.size() - 1).getNeighborhoodID() ==(Database.list_neighbour.get(Database.list_neighbour.size() - 1).getNeighborhoodID())) {
                            have_GoodsList = false;
                        } else {
                            for (int i = 0; i < neighborlist.size(); i++) {
                                Database.list_neighbour.add(neighborlist.get(i));
                            }
                            have_GoodsList = true;
                        }
                    }
                    //---------------------------------------------------------------
                    if (Database.list_neighbour != null && Database.list_neighbour.size() != 0) {
                        if (mMainAdapter == null || isrefresh) {
                            have_GoodsList = true;
                            mMainAdapter = new NeighbourAdapter(context, Database.list_neighbour, "near");
                            listView_neighbour.setAdapter(mMainAdapter);
                            noGoods.setVisibility(View.GONE);
                        } else if (have_GoodsList) {
                            listView_neighbour.requestLayout();
                            mMainAdapter.notifyDataSetChanged();
                            noGoods.setVisibility(View.GONE);
                        } else {
                            noGoods.setVisibility(View.VISIBLE);
                        }
                    } else {//没有数据
                        noGoods.setVisibility(View.VISIBLE);
                        listView_neighbour.setAdapter(null);
                        have_GoodsList = false;
                    }
                } else {
                    if (Database.list_news != null && Database.list_news.size() > 0) { //分页加载无数据

                    } else { //加载无数据
                        listView_neighbour.setAdapter(null);
                    }
                    have_GoodsList = false;
                    noGoods.setVisibility(View.VISIBLE);
                    loading_lay.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError() {
                base_pullToRefreshView.onHeaderRefreshComplete();
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
            }

            @Override
            public void parseError() {
                base_pullToRefreshView.onHeaderRefreshComplete();
            }

            @Override
            public void onBefore() {

            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                base_pullToRefreshView.onHeaderRefreshComplete();
            }
        }).getEntityData(getActivity(),HttpURL.HTTP_POST_FRIEND_QUERY,json);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
