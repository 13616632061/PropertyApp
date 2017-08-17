package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQuerySendInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQuerySendInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.PersionInfoAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.adapter.NeighbourAdapter;
import com.glory.bianyitong.ui.dialog.NewsDeletePopuWindow;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 我的发布
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AAWARD_MY_RELEASE,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class MyReleaseActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.listView_mynews)
    ListView listView_mynews;
    @BindView(R.id.lay_no_release)
    RelativeLayout lay_no_release;
    //    private ArrayList<LinkedTreeMap<String, Object>> mylist;
    private NeighbourAdapter mynewsAdapter;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;

    private Handler mhandler;
    private Handler del_handler;
    private NewsDeletePopuWindow deleteDialog;
    private int index_page = 0;
    private PersionInfoAdapter mMainAdapter;
    private List<ResponseQuerySendInfo.ListNeighborhoodBean> list_dongtai;

    @Override
    protected int getContentId() {
        return R.layout.activity_my_release;
    }

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
        inintTitle(getString(R.string.my_release), true, "");//我的发布
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                MyReleaseActivity.this.finish();
            }
        });
        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        listView_mynews.addFooterView(view_loading);
        listView_mynews.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (Database.list_myRelease != null && have_GoodsList && !getGoodsListStart) {
                            getGoodsListStart = true;
                            loading_lay.setVisibility(View.VISIBLE);
                            index_page++;
                            reqeust(index_page, false);
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
        });

        del_handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: //删除
                        final int index = msg.arg1;
                        if (Database.list_myRelease != null && Database.list_myRelease.get(index) != null &&
                                Database.list_myRelease.get(index).get("neighborhoodID") != null) {
                            final int neighborhoodID = Double.valueOf(Database.list_myRelease.get(index).get("neighborhoodID").toString()).intValue();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MyReleaseActivity.this);
                            builder.setMessage(getString(R.string.whether_to_delete_the_release));//是否删除发布
                            builder.setTitle(getString(R.string.prompt));//"提示"
                            builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() { //删除
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    delete(neighborhoodID, index);
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {//"取消"
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();
                        }
                        break;
                }
            }
        };
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: //调出对话框
                        deleteDialog = new NewsDeletePopuWindow(MyReleaseActivity.this, msg.arg1, del_handler);
                        // 显示窗口
                        deleteDialog.showAtLocation(MyReleaseActivity.this.findViewById(R.id.lay_ac_my_news),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                        break;
                }
            }
        };

//        if (Database.list_myRelease != null && Database.list_myRelease.size() > 0) {
//            mynewsAdapter = new NeighbourAdapter(MyReleaseActivity.this, Database.list_myRelease, "my", mhandler);
//            listView_mynews.setAdapter(mynewsAdapter);
//        } else {
//            getGoodsListStart = true;
//            loading_lay.setVisibility(View.VISIBLE);
//            index_page++;
//            reqeust(index_page, false);
//        }
        getGoodsListStart = true;
        loading_lay.setVisibility(View.VISIBLE);
        index_page++;
        reqeust(index_page, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shuaxin(String msg) {
        if (msg.equals("MyRelease")) {
//            ToastUtils.showToast(MyReleaseActivity.this, "刷新了...");
//            index_page = 0;
//            index_page++;
//            reqeust(index_page, true);
        }
    }

    //保存
    private void delete(int neighborhoodID, final int position) {//
        String userID = RequestUtil.getuserid();
        String json = "{\"neighborhood\":{\"neighborhoodID\":" + neighborhoodID + "},\"controllerName\":\"Neighborhood\",\"actionName\":\"Delete\"," +
                "\"userID\":\"" + userID + "\"}";

        Log.i("resultString", "json------------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/Neighborhood/Delete") //删除
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                        });
                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
                            Database.list_myRelease.remove(position);
                            mynewsAdapter.notifyDataSetChanged();
                            ToastUtils.showToast(MyReleaseActivity.this, getString(R.string.successfully_deleted));//删除成功
                        } else {
                            ToastUtils.showToast(MyReleaseActivity.this, getString(R.string.failed_to_delete));//删除失败
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(MyReleaseActivity.this, "请求失败...");
                        ServiceDialog.showRequestFailed();
                    }
                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        progressDialog = ProgressDialog.show(MyReleaseActivity.this, "", getString(R.string.delete), true);//删除
                        progressDialog.setCanceledOnTouchOutside(true);
                    }
                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                    }
                });
    }

    /**
     * 请求发布信息
     * @param page
     * @param isrefresh
     */
    private void reqeust(int page, final boolean isrefresh) {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("currentPageNumber",page);
        map.put("neighborhood",new RequestQuerySendInfo((String) map.get("userID")));
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
                            mMainAdapter = new PersionInfoAdapter(MyReleaseActivity.this, list_dongtai, "personal");
                            listView_mynews.setAdapter(mMainAdapter);
                            noGoods.setVisibility(View.GONE);
                        } else if (have_GoodsList) {
                            listView_mynews.requestLayout();
                            mMainAdapter.notifyDataSetChanged();
                            noGoods.setVisibility(View.GONE);
                        } else {
                            noGoods.setVisibility(View.VISIBLE);
                        }
                    } else {//没有数据
                        noGoods.setVisibility(View.VISIBLE);
                        listView_mynews.setAdapter(null);
                        have_GoodsList = false;
                    }
                } else {
                    if (Database.list_news != null && Database.list_news.size() > 0) { //分页加载无数据

                    } else { //加载无数据
                        listView_mynews.setAdapter(null);
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
            }

            @Override
            public void parseError() {

            }

            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(MyReleaseActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_MY_GETSEND_INFO,json);
//        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/Neighborhood/StructureQuery") //我的发布
//                .tag(this)//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
//                        if (hashMap2 != null && hashMap2.get("listNeighborhood") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> neighborlist = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNeighborhood");
//                            //分页加载数据----------------------------------------------------
//                            if (Database.list_myRelease == null) {
//                                Database.list_myRelease = neighborlist;
//                            } else {
//                                if (isrefresh) {
//                                    if (Database.list_myRelease != null) {
//                                        Database.list_myRelease = null;
//                                        Database.list_myRelease = neighborlist;
//                                    }
//                                }
//                                if (Database.list_myRelease.size() != 0
//                                        && neighborlist.get(neighborlist.size() - 1).get("neighborhoodID")
//                                        .equals(Database.list_myRelease.get(Database.list_myRelease.size() - 1).get("neighborhoodID"))) {
//                                    have_GoodsList = false;
//                                } else {
//                                    for (int i = 0; i < neighborlist.size(); i++) {
//                                        Database.list_myRelease.add(neighborlist.get(i));
//                                    }
//                                    have_GoodsList = true;
//                                }
//                            }
//                            //---------------------------------------------------------------
//                            if (Database.list_myRelease != null && Database.list_myRelease.size() != 0) {
//                                if (mynewsAdapter == null || isrefresh) {
//                                    have_GoodsList = true;
//                                    mynewsAdapter = new NeighbourAdapter(MyReleaseActivity.this, Database.list_myRelease, "my", mhandler);
//                                    listView_mynews.setAdapter(mynewsAdapter);
//                                    noGoods.setVisibility(View.GONE);
//                                } else if (have_GoodsList) {
//                                    listView_mynews.requestLayout();
//                                    mynewsAdapter.notifyDataSetChanged();
//                                    noGoods.setVisibility(View.GONE);
//                                } else {
//                                    noGoods.setVisibility(View.VISIBLE);
//                                }
//                            } else {//没有数据
//                                noGoods.setVisibility(View.VISIBLE);
//                                listView_mynews.setAdapter(null);
//                                lay_no_release.setVisibility(View.VISIBLE);
//                                have_GoodsList = false;
//                            }
//                        } else {
//                            if (Database.list_myRelease != null && Database.list_myRelease.size() > 0) { //分页加载无数据
//                                noGoods.setVisibility(View.VISIBLE);
//                                loading_lay.setVisibility(View.GONE);
//                            } else { //加载无数据
//                                listView_mynews.setAdapter(null);
//                                lay_no_release.setVisibility(View.VISIBLE);
//                            }
//                            have_GoodsList = false;
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "请求错误------");
////                        ToastUtils.showToast(MyReleaseActivity.this, "请求失败...");
//                        ServiceDialog.showRequestFailed();
//                    }
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                        progressDialog = ProgressDialog.show(MyReleaseActivity.this, "", getString(R.string.load), true);//加载
//                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
//                    }
//                });
    }

}
