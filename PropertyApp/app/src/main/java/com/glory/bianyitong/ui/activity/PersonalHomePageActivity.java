package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQuerySendInfo;
import com.glory.bianyitong.bean.entity.request.RequestQueryUserInfo;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.bean.entity.response.ResponseQuerySendInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUserById;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.PersionInfoAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.ui.adapter.NeighbourAdapter;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 个人主页
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_FRIEND_USER_INFO,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PersonalHomePageActivity extends BaseActivity {
    @BindView(R.id.base_listView)
    ListView base_listView;

    private View headview;
    private RelativeLayout left_return_btn;
    private CircleImageView cim_ta_head_pic; //头像
    private TextView text_ta_name;//名称
    private TextView ta_signature;// 个人留言
    private TextView ta_dynamic_num;// ta的动态

    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;
    private List<ResponseQuerySendInfo.ListNeighborhoodBean> list_dongtai;
    private PersionInfoAdapter mMainAdapter;

    @InjectParam(key = "userID")
     String userID ;
    private int index_page = 0;

    @Override
    protected int getContentId() {
        return R.layout.lay_listview_no_title2;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        headview = getLayoutInflater().inflate(R.layout.ac_personalhomepage, null);
        left_return_btn = (RelativeLayout) headview.findViewById(R.id.left_return_btn);
        cim_ta_head_pic = (CircleImageView) headview.findViewById(R.id.cim_ta_head_pic);
        text_ta_name = (TextView) headview.findViewById(R.id.text_ta_name);
        ta_signature = (TextView) headview.findViewById(R.id.ta_signature);
        ta_dynamic_num = (TextView) headview.findViewById(R.id.ta_dynamic_num);

        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                PersonalHomePageActivity.this.finish();
            }
        });

        base_listView.addHeaderView(headview);
        base_listView.addFooterView(view_loading);

        base_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        user_request();
        index_page = 0;
        index_page++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        request(index_page, false);
    }

    private void request(int page, final boolean isrefresh) {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("currentPageNumber",page);
        map.put("neighborhood",new RequestQuerySendInfo(userID));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                ResponseQuerySendInfo querySendInfo=new Gson().fromJson(s, ResponseQuerySendInfo.class);
                if(querySendInfo.getStatusCode()==1){
                    //分页加载数据----------------------------------------------------
                    if (list_dongtai == null) {
                        list_dongtai = querySendInfo.getListNeighborhood();
                    } else {
                        if (isrefresh) {
                            if (list_dongtai != null) {
                                list_dongtai = null;
                                list_dongtai = querySendInfo.getListNeighborhood();
                            }
                        }
                        if (list_dongtai.size() != 0
                                && querySendInfo.getListNeighborhood().get(querySendInfo.getListNeighborhood().size() - 1).getNeighborhoodID()==(list_dongtai.get(list_dongtai.size() - 1).getNeighborhoodID())) {
                            have_GoodsList = false;
                        } else {
                            for (int i = 0; i < querySendInfo.getListNeighborhood().size(); i++) {
                                list_dongtai.add(querySendInfo.getListNeighborhood().get(i));
                            }
                            have_GoodsList = true;
                        }
                    }
                    //---------------------------------------------------------------
                    if (list_dongtai != null && list_dongtai.size() != 0) {
                        if (mMainAdapter == null || isrefresh) {
                            have_GoodsList = true;
                            mMainAdapter = new PersionInfoAdapter(PersonalHomePageActivity.this, list_dongtai, "personal");
                            base_listView.setAdapter(mMainAdapter);
                            noGoods.setVisibility(View.GONE);
                        } else if (have_GoodsList) {
                            base_listView.requestLayout();
                            mMainAdapter.notifyDataSetChanged();
                            noGoods.setVisibility(View.GONE);
                        } else {
                            noGoods.setVisibility(View.VISIBLE);
                        }
                    } else {//没有数据
                        noGoods.setVisibility(View.VISIBLE);
                        base_listView.setAdapter(null);
                        have_GoodsList = false;
                    }
                } else {
                    if (Database.list_news != null && Database.list_news.size() > 0) { //分页加载无数据

                    } else { //加载无数据
//                        base_listView.setAdapter(null);
                    }
                    have_GoodsList = false;
                    noGoods.setVisibility(View.VISIBLE);
                    loading_lay.setVisibility(View.GONE);
                }


            }

            @Override
            public void onError() {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(PersonalHomePageActivity.this, "请求失败...");
                ServiceDialog.showRequestFailed();
            }

            @Override
            public void parseError() {

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
            }
        }).getEntityData(this,HttpURL.HTTP_POST_MY_GETSEND_INFO,json);


    }

    private void user_request() { //获取个人信息
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("user",new RequestQueryUserInfo(userID));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryUserById responseQueryUserById=new Gson().fromJson(s,ResponseQueryUserById.class);
                if(responseQueryUserById.getStatusCode()==1){
                    showinfo(responseQueryUserById);
                }else {
                    showShort(responseQueryUserById.getAlertMessage());
                }

            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(this,HttpURL.HTTP_POST_QUERY_USER_INFO,json);
    }

    //显示用户个人信息
    private void showinfo(ResponseQueryUserById user_list) {
        if (user_list.getListUser().get(0) != null) {
            if(!TextUtil.isEmpty(user_list.getListUser().get(0).getLoginName()))
                text_ta_name.setText(user_list.getListUser().get(0).getLoginName());


            if(!TextUtil.isEmpty(user_list.getListUser().get(0).getSignature()))
                ta_signature.setText(user_list.getListUser().get(0).getSignature());

            if(!TextUtil.isEmpty(user_list.getListUser().get(0).getCustomerPhoto()))
                ServiceDialog.setPicture(user_list.getListUser().get(0).getCustomerPhoto(), cim_ta_head_pic, null);


        }
    }

}
