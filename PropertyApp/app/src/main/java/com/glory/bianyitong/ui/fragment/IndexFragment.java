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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.AdvertisingInfo2;
import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestAdvertising;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.bean.MessageInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.activity.AddAreaCityActivity;
import com.glory.bianyitong.ui.activity.AuthAreaActivity;
import com.glory.bianyitong.ui.activity.BulletinDetailsActivity;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.adapter.CommunityAnnouceAdapter;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.activity.CommunityBulletinActivity;
import com.glory.bianyitong.ui.activity.YellowPageActivity;
import com.glory.bianyitong.ui.activity.MessageActivity;
import com.glory.bianyitong.ui.activity.StewardActivity;
import com.glory.bianyitong.ui.activity.SuggestActivity;
import com.glory.bianyitong.ui.activity.SwitchAreaActivity;
import com.glory.bianyitong.ui.adapter.EveryDayRecommendAdapter;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.NetworkImageHolderView;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.convenientbanner.ConvenientBanner;
import com.glory.bianyitong.widght.convenientbanner.holder.CBViewHolderCreator;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/10.
 * 首页
 */
public class IndexFragment extends BaseFragment {
    Context context;
    ConvenientBanner convenientBanner; //广告图
    @BindView(R.id.village_name)
    LinearLayout villageName;
    //    private ArrayList<LinkedTreeMap<String, Object>> listnews;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.tv_villageName)
    TextView tvVillageName;
    @BindView(R.id.lay_home_message)//消息
            RelativeLayout lay_home_message;
    @BindView(R.id.iv_home_message)
    ImageView iv_home_message;
    @BindView(R.id.tv_msg_number)
    TextView tv_msg_number;
    TextView tv_notice_number;
//    @BindView(R.id.news_list_refresh)
//    NewPullToRefreshView news_list_refresh;
    @BindView(R.id.listView)
    ListView listView;
    private EveryDayRecommendAdapter everyDayRecommendAdapter;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //

    private String[] images;
    private List<String> imageList = new ArrayList<String>();
    private int index_page = 0;
    private ProgressDialog progressDialog = null;
    private String userID;
    private List<listCommunityBulletinInfo.ListCommunityBulletinBean> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fg_index, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Database.notreadmessageidSize > 0) {
            tv_msg_number.setVisibility(View.VISIBLE);
            tv_msg_number.setText(Database.notreadmessageidSize + "");
        } else {
            tv_msg_number.setVisibility(View.GONE);
        }

        if (Database.notreadbulletinSize > 0) {
            tv_notice_number.setVisibility(View.VISIBLE);
            tv_notice_number.setText(Database.notreadbulletinSize + "");
        } else {
            tv_notice_number.setVisibility(View.GONE);
        }
//        if (Database.USER_MAP != null) {
////            if (Database.my_community != null && Database.my_community.get("communityName") != null) {
////                tvVillageName.setText(Database.my_community.get("communityName").toString());
////            } else {
////                tvVillageName.setText("点击添加小区");
////            }
//            if (Database.my_community != null && Database.my_community.getCommunityName() != null) {
//                if (Database.my_community.getApprovalStatus()==1){
//                }
//            }
//        }
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View headview = LayoutInflater.from(getActivity()).inflate(R.layout.index_headview, null);
        View headviewtwo = LayoutInflater.from(getActivity()).inflate(R.layout.index_headviewtwo, null);
        TextView tvCommunity = (TextView) headviewtwo.findViewById(R.id.tv_community);
        tvCommunity.setOnClickListener(this);
        TextView tvConveniencePhone = (TextView) headviewtwo.findViewById(R.id.tv_convenience_phone);
        tvConveniencePhone.setOnClickListener(this);
        TextView tvSuggest = (TextView) headviewtwo.findViewById(R.id.tv_suggest);
        tvSuggest.setOnClickListener(this);
        TextView tvTenementSteward = (TextView) headviewtwo.findViewById(R.id.tv_tenement_steward);
        tvTenementSteward.setOnClickListener(this);
        tv_notice_number = (TextView) headviewtwo.findViewById(R.id.tv_notice_number);
        convenientBanner = (ConvenientBanner) headview.findViewById(R.id.convenientBanner);
        view_loading = getActivity().getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);
        listView.addFooterView(view_loading);
        userID = RequestUtil.getuserid();

        initView();
        listView.addHeaderView(headview);
        listView.addHeaderView(headviewtwo);

//        initList();
        progressDialog = ProgressDialog.show(context, "", getResources().getString(R.string.load), true);
        progressDialog.setCanceledOnTouchOutside(true);
        ad_request();

        if (Database.readbulletinid == null || Database.readbulletinid.equals("")) { //获取已读公告缓存
            Database.readbulletinid = mCache.getAsString(Constant.bulletinID);
        }
        if (Database.readbulletinid == null || Database.readbulletinid.equals("")) {
            Database.readbulletinid = "";
        }
        if (Database.readmessageid == null || Database.readmessageid.equals("")) {//获取已读消息缓存
            Database.readmessageid = mCache.getAsString( Constant.messageID);
        }
        if (Database.readmessageid == null || Database.readmessageid.equals("")) {
            Database.readmessageid = "";
        }
        requestmsg();

    }


    private void initView() {
        villageName.setOnClickListener(this);
        lay_home_message.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (Database.USER_MAP != null) {
//            if (Database.my_community != null && Database.my_community.get("communityName") != null) {
//                tvVillageName.setText(Database.my_community.get("communityName").toString());
//            } else {
//                tvVillageName.setText("点击添加小区");
//            }
            requestSQ();

        } else {
            SharedUtil.putBoolean("login", false);
            SharedUtil.putString("jgPushID","");
            tvVillageName.setText(getResources().getString(R.string.click_to_Login)); //点击登录
        }
//        if (images != null && images.length > 0) {
//
//        } else {
//
//        }

        if (list == null && have_GoodsList && !getGoodsListStart || Database.isAddComment) {
            getGoodsListStart = true;
            index_page = 0;
            index_page++;
            Database.isAddComment = false;
//            getNews(index_page, true);
        } else {
            everyDayRecommendAdapter = new EveryDayRecommendAdapter(context, list);
            listView.setAdapter(everyDayRecommendAdapter);
        }

        if (Database.list_communityBulletin != null) {

        } else {
            requestnotice();
        }

    }

    private void requestSQ() { //社区
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jo = new JSONObject(s);
                    AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);

                    if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                        DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
                        for (int i = 0; i < Database.my_community_List.size(); i++) {
                            if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getUserCommunityID()
                                    == Database.my_community.getUserCommunityID()) {
                                Database.my_community = Database.my_community_List.get(i);
                            }
                            Log.v("sadaddwww",Database.my_community_List.get(i)+"");

                        }


                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
            }
            @Override
            public void onAfter() {
                if (Database.my_community != null && Database.my_community.getCommunityName() != null) {
                    if (Database.my_community.getApprovalStatus()==1){
                        request();
                        tvVillageName.setText(Database.my_community.getCommunityName()+"("+getString(R.string.audited)+")");
                    }else if (Database.my_community.getApprovalStatus()==2){
                        list.clear();
                        everyDayRecommendAdapter.notifyDataSetChanged();
                        tvVillageName.setText(Database.my_community.getCommunityName()+"("+getString(R.string.pending_review)+")");

                    }else if (Database.my_community.getApprovalStatus()==0){
                        list.clear();
                        everyDayRecommendAdapter.notifyDataSetChanged();
                        tvVillageName.setText(Database.my_community.getCommunityName()+"("+getString(R.string.audit_failure)+")");
                    }
                } else {
                    tvVillageName.setText(getResources().getString(R.string.click_add_community));//点击添加小区
                }
            }
        }).getEntityData(getActivity(),"/ApiUserCommnunity/Query", jsons);

    }

    private void request() { //请求社区公告

        try {
            Map<String,Object> map=new BaseRequestBean().getBaseRequest();
            map.put("communityBulletin",new Object());
            String json=new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
//                pullToRefreshView.onHeaderRefreshComplete();
                    listCommunityBulletinInfo cbinfo = new Gson().fromJson(s, listCommunityBulletinInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                    if (cbinfo != null && cbinfo.getListCommunityBulletin() != null) {
                        list = cbinfo.getListCommunityBulletin();

                    }
                    everyDayRecommendAdapter = new EveryDayRecommendAdapter(context, list);
                    listView.setAdapter(everyDayRecommendAdapter);

                }

                @Override
                public void onError() {
                    getGoodsListStart = false;
                }
                @Override
                public void parseError() {}
                @Override
                public void onBefore() {

                }
                @Override
                public void onAfter() {

                }
            }).getEntityData(getActivity(),HttpURL.HTTP_POST_LOCAL_AREA_QUERY_AREA_NOTICE,json);
        }catch (Exception e){

        }
    }

    /**
     * 广告轮播图
     */
    private void getBanner() {
        Collections.addAll(imageList, images);
        convenientBanner.startTurning(4000);
        convenientBanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, imageList)
                //显示小圆圈
                .setPageIndicator(new int[]{R.drawable.point_unfocused, R.drawable.point_focused})
                //小圆圈的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)

                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //每一张图片的点击事件

                    }
                });
    }

    public void initList() {
//        news_list_refresh.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {
//            @Override
//            public void onHeaderRefresh(NewPullToRefreshView view) {
//                if (list != null && !getGoodsListStart) {
//                    getGoodsListStart = true;
//                    index_page = 0;//重置index_page
//                    index_page++;
//                    ad_request();
////                    getNews(index_page, true);//刷新
//                    request();
//                }
//            }
//        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (list != null && have_GoodsList && !getGoodsListStart) {
                            getGoodsListStart = true;
                            loading_lay.setVisibility(View.VISIBLE);
                            index_page++;
//                            getNews(index_page, false);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.village_name:
                if (Database.USER_MAP != null) {
                    intent = new Intent(context, SwitchAreaActivity.class); //切换小区
                    startActivity(intent);
//                    if (Database.my_community != null && Database.my_community_List != null) {
//                        intent = new Intent(context, SwitchAreaActivity.class); //切换小区
//                        startActivity(intent);
//                    } else {//没有小区
//                        intent = new Intent(context, AddAreaCityActivity.class); //
//                        intent.putExtra("from", "");//index
//                        startActivity(intent);
//                    }
                } else {
                    login();
                }
                break;
            case R.id.lay_home_message: //消息中心
                intent = new Intent(context, MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_community: //社区公告
                if (Database.USER_MAP != null) {
                    if (Database.my_community != null) {
                        if (Database.my_community.getApprovalStatus() == 1) {

                            intent = new Intent(context, CommunityBulletinActivity.class); //社区公告
                            startActivity(intent);
                        } else if (Database.my_community.getApprovalStatus() == 2){
                            showShort("请等待社区审核");
                        }else if (Database.my_community.getApprovalStatus() == 0){
                            showShort("社区审核未通过");
                        } {
                            requestlist("community");
                        }
                    } else {//没有小区
                        ToastUtils.showToast(context, getResources().getString(R.string.no_district)); //暂无小区
                        intent = new Intent(context, AuthAreaActivity.class); //
                        intent.putExtra("from", "");//index
                        startActivity(intent);
                    }
                } else {
                    login();
                }
                break;
            case R.id.tv_convenience_phone: //便民黄页
                if (Database.USER_MAP != null) {
                    if (Database.my_community != null && Database.my_community_List != null) {
                        if (Database.my_community.getApprovalStatus() == 1) {
                            intent = new Intent(context, YellowPageActivity.class);
                            startActivity(intent);
                        } else if (Database.my_community.getApprovalStatus() == 2){
                            showShort("请等待社区审核");
                        }else if (Database.my_community.getApprovalStatus() == 0){
                            showShort("社区审核未通过");
                        }else {
                            requestlist("phone");
                        }
                    } else {//没有小区
                        ToastUtils.showToast(context, getResources().getString(R.string.no_district)); //暂无小区
                        intent = new Intent(context, AuthAreaActivity.class); //
                        intent.putExtra("from", "");//index
                        startActivity(intent);
                    }
                } else {
                    login();
                }
                break;
            case R.id.tv_suggest://投诉建议
                if (Database.USER_MAP != null) {
                    if (Database.my_community != null && Database.my_community_List != null) {
                        if (Database.my_community.getApprovalStatus() == 1) {
                            intent = new Intent(context, SuggestActivity.class);
                            startActivity(intent);
                        } else if (Database.my_community.getApprovalStatus() == 2){
                            showShort("请等待社区审核");
                        }else if (Database.my_community.getApprovalStatus() == 0){
                            showShort("社区审核未通过");
                        }{
                            requestlist("suggest");
                        }
                    } else {//没有小区
                        ToastUtils.showToast(context, getResources().getString(R.string.no_district)); //暂无小区
                        intent = new Intent(context, AuthAreaActivity.class); //
                        intent.putExtra("from", "");
                        startActivity(intent);
                    }
                } else {
                    login();
                }
                break;
            case R.id.tv_tenement_steward://物业管家
                if (Database.USER_MAP != null) {
                    if (Database.my_community != null && Database.my_community_List != null) {
                        if (Database.my_community.getApprovalStatus() == 1) {
                            intent = new Intent(context, StewardActivity.class);
                            startActivity(intent);
                        } else if (Database.my_community.getApprovalStatus() == 2){
                            showShort("请等待社区审核");
                        }else if (Database.my_community.getApprovalStatus() == 0){
                            showShort("社区审核未通过");
                        }{
                            requestlist("steward");
                        }
                    } else {//没有小区
                        ToastUtils.showToast(context, getResources().getString(R.string.no_district)); //暂无小区
                        intent = new Intent(context, AuthAreaActivity.class); //
                        intent.putExtra("from", ""); //index
                        startActivity(intent);
                    }
                } else {
                    login();
                }
                break;
        }
    }

    private void login() {
        ToastUtils.showToast(context, getResources().getString(R.string.please_login_first));//请先登录
        Intent intent_login = new Intent();
        intent_login.setClass(context, LoginActivity.class);
        startActivity(intent_login);
    }

    private void ad_request() { //获取广告轮播    advertisingLocation 广告位置  - 1首页广告  2开门页广告
        images = null;
        imageList = new ArrayList<>();

        int communityID = RequestUtil.getcommunityid();

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("advertising",new RequestAdvertising(1));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                AdvertisingInfo2 adinfo = new Gson().fromJson(s, AdvertisingInfo2.class);
//                    Log.i("resultString", "adinfo.getListAdvertising()-------" + adinfo.getListAdvertising());
                if (adinfo != null && adinfo.getListAdvertising() != null) {
                    List<AdvertisingInfo2.ListAdvertisingBean> adlist = adinfo.getListAdvertising();
                    String adstr = "";
                    for (int i = 0; i < adlist.size(); i++) {
                        if (adlist.get(i) != null && adlist.get(i).getAdvertisingPicture() != null) {
                            adstr = adstr + adlist.get(i).getAdvertisingPicture().toString() + ","; //广告图  ,号隔开
                        }
                    }
                    if (adstr.split(",") != null && adstr.split(",").length > 0) {
                        images = adstr.split(",");
                    } else {
                        images = new String[]{adstr};
                    }
                    getBanner(); //开始广告轮播

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
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(getActivity(),HttpURL.HTTP_POST_GET_AD,json);
    }


//    private void getNews(int page, final boolean isrefresh) {
//        int communityID = RequestUtil.getcommunityid();
//
//        String nowdate = DateUtil.formatDefaultDate(DateUtil.getCurrentDate());//获取当前时间
//        String json = "{\"news\":{\"communityID\":" + communityID + ",\"newsReviewed\":\"True\"},\"userid\":\"" + userID + "\",\"groupid\":\"\"," +
//                "\"datetime\":\"" + nowdate + "\",\"controllerName\":\"News\",\"DeviceType\":\"\",\"version\":\"\",\"accesstoken\":\"\"," +
//                "\"messagetoken\":\"\",\"actionName\":\"StructureQuery\",\"nowpagenum\":" + page + ",\"pagerownum\":\"10\"}";
////        String json = "{\"news\":{\"newsReviewed\":\"True\"},\"userid\":\"" + userID + "\",\"groupid\":\"\"," +
////                "\"datetime\":\"" + nowdate + "\",\"controllerName\":\"News\",\"DeviceType\":\"\",\"version\":\"\",\"accesstoken\":\"\"," +
////                "\"messagetoken\":\"\",\"actionName\":\"StructureQuery\",\"nowpagenum\":" + page + ",\"pagerownum\":\"10\"}";
//        Log.i("resultString", "json-------" + json);
//        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/NewsQuery/StructureQuery")
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        news_list_refresh.onHeaderRefreshComplete();
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listNews") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> listnews = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNews");
//                            //分页加载数据----------------------------------------------------
//                            if (list == null) {
//                                list = listnews;
//                            } else {
//                                if (isrefresh) {
//                                    if (list != null) {
//                                        list = null;
//                                        list = listnews;
//                                    }
//                                }
//                                if (list.size() != 0
//                                        && listnews.get(listnews.size() - 1).get("newsID")
//                                        .equals(list.get(list.size() - 1).get("newsID"))) {
//                                    have_GoodsList = false;
//                                } else {
//                                    for (int i = 0; i < listnews.size(); i++) {
//                                        Database.list_news.add(listnews.get(i));
//                                    }
//                                    have_GoodsList = true;
//                                }
//                            }
//                            //---------------------------------------------------------------
//                            if (Database.list_news != null && Database.list_news.size() != 0) {
//                                if (everyDayRecommendAdapter == null || isrefresh) {
//                                    everyDayRecommendAdapter = new EveryDayRecommendAdapter(context, Database.list_news);
//                                    listView.setAdapter(everyDayRecommendAdapter);
//                                    have_GoodsList = true;
//                                    noGoods.setVisibility(View.GONE);
//                                } else if (have_GoodsList) {
//                                    listView.requestLayout();
//                                    everyDayRecommendAdapter.notifyDataSetChanged();
//                                    noGoods.setVisibility(View.GONE);
//                                } else {
//                                    noGoods.setVisibility(View.VISIBLE);
//                                }
//                            } else {
//                                noGoods.setVisibility(View.VISIBLE);
//                                listView.setAdapter(null);
//                                have_GoodsList = false;
//                            }
//                        } else {
//                            if (Database.list_news != null && Database.list_news.size() > 0) { //分页加载无数据
//                                have_GoodsList = false;
//                                noGoods.setVisibility(View.VISIBLE);
//                            } else { //加载无数据
//                                listView.setAdapter(null);
//                                have_GoodsList = false;
//                                noGoods.setVisibility(View.VISIBLE);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        news_list_refresh.onHeaderRefreshComplete();
//                        showShort(getString(R.string.failed_to_connect_to_server));
//                        listView.setAdapter(null);
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                    }
//                });
//    }

    private void requestlist(final String from) { //获取社区

        String json = "{\"userCommnunityMapping\": {\"userID\": 4},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"1\",\"pagerownum\": \"10\"," +
                "\"controllerName\": \"UserCommnunityMapping\",\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json---------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN)
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
                        try {
                            JSONObject jo = new JSONObject(s);
                            AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);
                            if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                                DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
                                DataUtils.saveSharePreToolsKits(context);
                                for (int i = 0; i < Database.my_community_List.size(); i++) {
                                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getUserCommunityID()
                                            == Database.my_community.getUserCommunityID()) {
                                        Database.my_community = Database.my_community_List.get(i);
                                        if (Database.my_community.getApprovalStatus() == 1) {
                                            startac(from);
                                        } else {
//                                            ToastUtils.showToast(context, "该小区还在审核中");
                                        }
                                    }
                                }
                            } else {
                                ToastUtils.showToast(context, getResources().getString(R.string.the_district_is_still_under_review));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listUserCommnunityMapping") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> communitylist = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listUserCommnunityMapping");
//                            if (communitylist != null && communitylist.size() != 0) {
//                                Database.my_community_List = null;
//                                Database.my_community_List = communitylist;
//
//                                HashMap<String, Object> hashMap3 = new HashMap<>();
//                                hashMap3.put("user", Database.USER_MAP);
//                                hashMap3.put("userCommnunity", Database.my_community_List);
//                                String json = JsonHelper.toJson(hashMap3);
//                                SharePreToolsKits.putJsonDataString(context, Constant.user, json); //缓存登录后信息 修改
//
//                                for (int i = 0; i < communitylist.size(); i++) {
//                                    if (communitylist.get(i) != null && communitylist.get(i).get("userCommunityID") != null &&
//                                            communitylist.get(i).get("userCommunityID").toString().equals(Database.my_community.get("userCommunityID").toString())) {
//                                        Database.my_community = communitylist.get(i);
//                                        if (Database.my_community.get("approvalStatus") != null
//                                                && Double.valueOf(Database.my_community.get("approvalStatus").toString()).intValue() == 1) {
//                                            startac(from);
//                                        } else {
//                                            ToastUtils.showToast(context, "该小区还在审核中");
//                                        }
//                                    }
//                                }
//                            } else {
//                                ToastUtils.showToast(context, "该小区还在审核中");
//                            }
//                        } else {
//                            ToastUtils.showToast(context, "该小区还在审核中");
//                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });
    }

    private void startac(String from) {
        switch (from) {
            case "community":
                Intent intent = new Intent(context, CommunityBulletinActivity.class);
                startActivity(intent);
                break;
            case "phone":
                Intent intent1 = new Intent(context, YellowPageActivity.class);
                startActivity(intent1);
                break;
            case "steward":
                Intent intent2 = new Intent(context, StewardActivity.class);
                startActivity(intent2);
                break;
            case "suggest":
                Intent intent3 = new Intent(context, SuggestActivity.class);
                startActivity(intent3);
                break;
        }
    }

    private void requestnotice() { //请求社区公告
        int communityID = RequestUtil.getcommunityid();
        String json = "{\"communityBulletin\": {\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\"" +
                ",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\"" +
                ",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"CommunityBulletin\",\"actionName\": \"StructureQuery\"}";

        OkGo.post(HttpURL.HTTP_LOGIN)
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listCommunityBulletin") != null) {
//                            Database.list_communityBulletin = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBulletin");
//                            if (Database.list_communityBulletin.size() > 0) {
//                                //清空过期的已读缓存
//                                //        if (Database.readbulletinid != null) {
//                                String[] array = Database.readbulletinid.split(",");
//                                int size = 0;
//                                if (array != null && Database.list_communityBulletin != null) {
//                                    Database.readbulletinid = "";
//                                    for (int i = 0; i < array.length; i++) {
//                                        for (int j = 0; j < Database.list_communityBulletin.size(); j++) {
//                                            if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).get("bulletinID") != null) {
//                                                if (array[i].equals(Database.list_communityBulletin.get(j).get("bulletinID").toString())) {
//                                                    Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).get("bulletinID").toString() + ",";
//                                                    size++;
//                                                }
//                                            }
//                                        }
//                                    }
//                                    Database.notreadbulletinSize = Database.list_communityBulletin.size() - size;
//                                    if (Database.notreadbulletinSize > 0) {
//                                        tv_notice_number.setVisibility(View.VISIBLE);
//                                        tv_notice_number.setText(Database.notreadbulletinSize + "");
//                                    }
//                                }
////        }
//                            } else {//没有数据
//
//                            }
//                        }
                        try {
                            JSONObject jo = new JSONObject(s);
//                            String statuscode = jo.getString("statuscode");
//                            String statusmessage = jo.getString("statusmessage");
                            listCommunityBulletinInfo cbinfo = new Gson().fromJson(jo.toString(), listCommunityBulletinInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                            if (cbinfo != null && cbinfo.getListCommunityBulletin() != null) {
                                Database.list_communityBulletin = cbinfo.getListCommunityBulletin();
                                if (Database.list_communityBulletin.size() > 0) {
                                    //清空过期的已读缓存
                                    //        if (Database.readbulletinid != null) {
                                    String[] array = Database.readbulletinid.split(",");
                                    int size = 0;
                                    if (array != null && Database.list_communityBulletin != null) {
                                        Database.readbulletinid = "";
                                        for (int i = 0; i < array.length; i++) {
                                            for (int j = 0; j < Database.list_communityBulletin.size(); j++) {
                                                //Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).getBulletinID() != null
                                                if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).getBulletinID() != 0) {
                                                    if (array[i] != null && array[i].equals(Database.list_communityBulletin.get(j).getBulletinID())) {
                                                        Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).getBulletinID() + ",";
                                                        size++;
                                                    }
                                                }
                                            }
                                        }
                                        Database.notreadbulletinSize = Database.list_communityBulletin.size() - size;
                                        if (Database.notreadbulletinSize > 0) {
                                            tv_notice_number.setVisibility(View.VISIBLE);
                                            tv_notice_number.setText(Database.notreadbulletinSize + "");
                                        }
                                    }
//        }
                                } else {//没有数据

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });
    }

    private void requestmsg() { //请求系统消息
        int communityID = RequestUtil.getcommunityid();

        String json = "{\"systemMsg\": {\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"SystemMsg\",\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json----------" + json);

        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/SystemMsg/StructureQuery")
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listSystemMsg") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> message_List = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listSystemMsg");
//                            if (message_List.size() > 0) {
//                                //清空过期的已读缓存
////        if (Database.readmessageid != null) {
//                                String[] array = Database.readmessageid.split(",");
//                                int size = 0;
//                                if (array != null && message_List != null) {
//                                    Database.readmessageid = "";
//                                    for (int i = 0; i < array.length; i++) {
//                                        for (int j = 0; j < message_List.size(); j++) {
//                                            if (message_List.get(j) != null && message_List.get(j).get("messageID") != null) {
//                                                if (array[i].equals(message_List.get(j).get("messageID").toString())) {
//                                                    Database.readmessageid = Database.readmessageid + message_List.get(j).get("messageID").toString() + ",";
//                                                    size++;
//                                                }
//                                            }
//                                        }
//                                    }
//                                    Database.notreadmessageidSize = message_List.size() - size;
//                                    if (Database.notreadmessageidSize > 0) {
//                                        tv_msg_number.setVisibility(View.VISIBLE);
//                                        tv_msg_number.setText(Database.notreadmessageidSize + "");
//                                    }
//                                }
//                            } else {//没有数据
//                            }
//                        } else {
//                        }

                        try {
                            JSONObject jo = new JSONObject(s);
//                            String statuscode = jo.getString("statuscode");
//                            String statusmessage = jo.getString("statusmessage");
                            MessageInfo minfo = new Gson().fromJson(jo.toString(), MessageInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                            if (minfo != null && minfo.getListSystemMsg() != null) {
                                List<MessageInfo.ListSystemMsgBean> message_List = minfo.getListSystemMsg();
                                if (message_List.size() > 0) {
                                    String[] array = Database.readmessageid.split(",");
                                    int size = 0;
                                    if (array != null && message_List != null) {
                                        Database.readmessageid = "";
                                        for (int i = 0; i < array.length; i++) {
                                            for (int j = 0; j < message_List.size(); j++) {
                                                if (message_List.get(j) != null && message_List.get(j).getMessageID() != null) {
                                                    if (array[i].equals(message_List.get(j).getMessageID())) {
                                                        Database.readmessageid = Database.readmessageid + message_List.get(j).getMessageID() + ",";
                                                        size++;
                                                    }
                                                }
                                            }
                                        }
//                                        Database.notreadmessageidSize = message_List.size() - size;
//                                        if (Database.notreadmessageidSize > 0) {
//                                            tv_msg_number.setVisibility(View.VISIBLE);
//                                            tv_msg_number.setText(Database.notreadmessageidSize + "");
//                                        }
                                    }
                                } else {//没有数据
                                }
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                    }
                });

    }

}