package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.adapter.CommunityAnnouceAdapter;
import com.glory.bianyitong.util.SharePreToolsKits;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/11.
 * 社区公告
 */
public class CommunityBulletinActivity extends BaseActivity {
//    private ArrayList<LinkedHashTreeMap<String, Object>> removeList;
//    private ArrayList<LinkedHashTreeMap<String, Object>> community_List;
// 高版本gson(估计是2.5以上吧) LinkedHashTreeMap不能用了 com.google.gson.internal.LinkedTreeMap cannot be cast to com.google.gson.internal.LinkedHashTreeMap
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    //    private ArrayList<LinkedTreeMap<String, Object>> community_List; //社区公告列表
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    //    @BindView(R.id.rl_bottom_ca)
//    RelativeLayout rl_bottom_ca;
//    @BindView(R.id.tv_del_ca)
//    TextView tv_del_ca;
//    @BindView(R.id.tv_all_read)
//    TextView tv_all_read;
    @BindView(R.id.lay_no_message)
    RelativeLayout lay_no_message;
    private ArrayList<LinkedTreeMap<String, Object>> removeList;     //移除的
    private HashMap<Integer, Boolean> checkList;//这是头部那个chexkbox
    private boolean isDoMore;//是否进行编辑默认为false
    private ListView gg_Listview;
    private CommunityAnnouceAdapter adapter;
    //    @BindView(R.id.gonggao_list_refresh)
//    NewPullToRefreshView pullToRefreshView;
//    private View view_loading;
//    private TextView noGoods;
//    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.ac_communtiy;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.community_announcement), false, getString(R.string.all_read)); //社区公告 全部已读
//        inintTitle(getString(R.string.community_announcement), false, getString(R.string.edit));
//        inintTitle(getString(R.string.community_announcement), true, "");
//        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
//        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
//        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);
        left_return_btn.setOnClickListener(this);//返回
        iv_title_text_right.setOnClickListener(this); //编辑  取消
//        tv_del_ca.setOnClickListener(this);//删除
//        tv_all_read.setOnClickListener(this);

        gg_Listview = (ListView) findViewById(R.id.listView_ca);
//        SharePreToolsKits.clearShare(CommunityBulletinActivity.this); //清除缓存
        if (Database.readbulletinid == null || Database.readbulletinid.equals("")) {
            Database.readbulletinid = SharePreToolsKits.fetchString(CommunityBulletinActivity.this, Constant.bulletinID);
        }
        if (Database.readbulletinid == null || Database.readbulletinid.equals("")) {
            Database.readbulletinid = "";
        }
        isDoMore = false;
        checkList = new HashMap<Integer, Boolean>();
//        buttonList = new HashMap<Integer, Boolean>();
        removeList = new ArrayList<>();
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
        if (Database.list_communityBulletin != null && Database.list_communityBulletin.size() > 0) {
            adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, Database.list_communityBulletin,
                    checkList, isDoMore);
            gg_Listview.setAdapter(adapter);
        } else {
            request();
        }
    }

    private void initview() {
//        gg_Listview.addFooterView(view_loading);
        gg_Listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
//                        if (Database.GOODS_LIST != null && have_GoodsList && !getGoodsListStart) {
//                            getGoodsListStart = true;
//                            loading_lay.setVisibility(View.VISIBLE);
//                            index_page++;
//                            getGoodsList(index_page, false);
//                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
        });
//        pullToRefreshView.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {
//
//            @Override
//            public void onHeaderRefresh(NewPullToRefreshView view) {
//                if (Database.list_communityBulletin != null) {
//                    getGoodsListStart = true;
//                    index_page = 0;//重置index_page
//                    index_page++;
//                    getGoodsList(index_page, true);//刷新
//
//                request();
//                }
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                CommunityBulletinActivity.this.finish();
                break;
            case R.id.iv_title_text_right: //编辑 取消/ 全部已读
                ServiceDialog.ButtonClickZoomInAnimation(iv_title_text_right, 0.95f);
                if (Database.list_communityBulletin != null && Database.list_communityBulletin.size() > 0) {
//                    if (isDoMore) {//编辑
//                        adapter.setIsDoMore(false);
//                        adapter.notifyDataSetChanged();
//                        iv_title_text_right.setText("编辑");
//                        rl_bottom_ca.setVisibility(View.GONE);
//                        isDoMore = false;
//                    } else {
//                        adapter.setIsDoMore(true);
//                        adapter.notifyDataSetChanged();
//                        isDoMore = true;
//                        iv_title_text_right.setText("取消");
//                        rl_bottom_ca.setVisibility(View.VISIBLE);
//                    }
                    Database.readbulletinid = "";
                    for (int i = 0; i < Database.list_communityBulletin.size(); i++) {
//                        if (Database.list_communityBulletin.get(i) != null && Database.list_communityBulletin.get(i).get("bulletinID") != null) {
//                            Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(i).get("bulletinID").toString() + ",";
//                        }

//                        if (Database.list_communityBulletin.get(i) != null && Database.list_communityBulletin.get(i).getBulletinID() != null) {
//                            Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(i).getBulletinID() + ",";
//                        }
                        if (Database.list_communityBulletin.get(i) != null && Database.list_communityBulletin.get(i).getBulletinID() != 0) {
                            Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(i).getBulletinID() + ",";
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Database.notreadbulletinSize = 0;
                    SharePreToolsKits.putString(CommunityBulletinActivity.this, Constant.bulletinID, Database.readbulletinid); //缓存已读消息
                }
                break;
//            case R.id.tv_del_ca: //删除
////                删除这部分有点复杂，比如12345 你删除了13 haspmap里面保存了245 但是2这项其实到了第一条了，如果你不做处理的话，
////                他会显示第二条已读所以要再删除后重新排列，下面先移除选中数据
//                checkList = CommunityAnnouceAdapter.getIsCheck();
//                for (int i = 0; i < checkList.size(); i++) {
//                    if (checkList.get(i)) {
//                        removeList.add(Database.list_communityBulletin.get(i));
//                    }
//                }
//                if (removeList.size() > 0) {
//                    //  删掉文本数据
//                    Database.list_communityBulletin.removeAll(removeList);
//                    removeList = null;
//                    adapter.updateList(Database.list_communityBulletin);
//                    isDoMore = false;
//
//                    //  这里是更新选中的checkbox，删除后 全部设置为false
//                    adapter.upDateCheckList();
//                    //  隐藏checkbox 恢复显示图片
//                    adapter.setIsDoMore(isDoMore);
//                    adapter.notifyDataSetChanged();
//                    iv_title_text_right.setText("编辑");
//                    rl_bottom_ca.setVisibility(View.GONE);
//                }
//
//                break;
//            case R.id.tv_all_read://全部已读
//                checkList = CommunityAnnouceAdapter.getIsCheck();
//                for (int i = 0; i < checkList.size(); i++) {
//                    if (checkList.get(i)) {
//                        removeList.add(Database.list_communityBulletin.get(i));
//                    }
//                }
//                for (int i = 0; i < removeList.size(); i++) {
//                    if (removeList != null && removeList.get(i) != null && removeList.get(i).get("bulletinID") != null) {
//                        boolean has = false;
//                        String[] array = Database.readbulletinid.split(",");
//                        if (array != null && array.length > 0) {
//                            for (int j = 0; j < array.length; j++) {
//                                if (array[i].equals(removeList.get(i).get("bulletinID").toString())) {
//                                    has = true;
//                                }
//                            }
//                        }
//                        if (!has) {
//                            Database.readbulletinid = Database.readbulletinid + removeList.get(i).get("bulletinID").toString() + ",";
//                        }
//                        Log.i("resultString", "------------" + removeList.get(i).get("bulletinID").toString());
//                    }
//                    Log.i("resultString", "------------" + removeList);
//                }
//
//                adapter.updateList(Database.list_communityBulletin);
//                isDoMore = false;
//
//                //  这里是更新选中的checkbox，删除后 全部设置为false
//                adapter.upDateCheckList();
//                //  隐藏checkbox 恢复显示图片
//
//                adapter.setIsDoMore(isDoMore);
//                adapter.notifyDataSetChanged();
//                iv_title_text_right.setText("编辑");
//                rl_bottom_ca.setVisibility(View.GONE);
//                Log.i("resultString", "Database.readbulletinid------" + Database.readbulletinid);
//                break;
        }
    }

    public void upButtonlist(HashMap<Integer, Boolean> list) {
        HashMap<Integer, Boolean> lists = new HashMap<Integer, Boolean>();
        int j = 0;
        for (java.util.Map.Entry<Integer, Boolean> entry : list.entrySet()) {
            if (j < list.size()) {
                lists.put(j, entry.getValue());
                j++;
            }
        }
//        buttonList.clear();
//        buttonList = lists;
    }

    private void request() { //请求社区公告
//        String userID = RequestUtil.getuserid();
//        int communityID = RequestUtil.getcommunityid();
//
//        String json = "{\"communityBulletin\": {\"communityID\":" + communityID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\"" +
//                ",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\"" +
//                ",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
//                "\"controllerName\": \"CommunityBulletin\",\"actionName\": \"StructureQuery\"}";
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("communityBulletin","");
        String json=new Gson().toJson(map);
        String url = "/ApiCommunityBulletin/Query";
//        OkGo.post(HttpURL.HTTP_LOGIN)
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
////                        pullToRefreshView.onHeaderRefreshComplete();
//                        getGoodsListStart = false;
////                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
////                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
////                        });
////                        if (hashMap2 != null && hashMap2.get("listCommunityBulletin") != null) {
////                            Database.list_communityBulletin = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunityBulletin");
////                            if (Database.list_communityBulletin.size() > 0) {
////                                if (adapter == null) {
////                                    have_GoodsList = true;
////                                    adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, Database.list_communityBulletin, checkList
////                                            , isDoMore);
//////                                    adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, community_List, checkList
//////                                            , buttonList, isDoMore);
////                                    gg_Listview.setAdapter(adapter);
////                                } else {
////                                    have_GoodsList = true;
////                                    adapter.notifyDataSetChanged();
////                                }
////
////                                //清空过期的已读缓存
//////        if (Database.readbulletinid != null) {
////                                String[] array = Database.readbulletinid.split(",");
////                                if (array != null && Database.list_communityBulletin != null) {
////                                    Database.readbulletinid = "";
////                                    for (int i = 0; i < array.length; i++) {
////                                        for (int j = 0; j < Database.list_communityBulletin.size(); j++) {
////                                            if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).get("bulletinID") != null) {
////                                                if (array[i].equals(Database.list_communityBulletin.get(j).get("bulletinID").toString())) {
////                                                    Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).get("bulletinID").toString() + ",";
////                                                }
////                                            }
////                                        }
////                                    }
////                                }
//////        }
////                            } else {//没有数据
//////                                noGoods.setVisibility(View.VISIBLE);
////                                gg_Listview.setAdapter(null);
////                                have_GoodsList = false;
////                                getGoodsListStart = false;
////                                lay_no_message.setVisibility(View.VISIBLE);
////                            }
////                        }
//
//                        try {
//                            JSONObject jo = new JSONObject(s);
////                            String statuscode = jo.getString("statuscode");
////                            String statusmessage = jo.getString("statusmessage");
//                            listCommunityBulletinInfo cbinfo = new Gson().fromJson(jo.toString(), listCommunityBulletinInfo.class);
////                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
//                            if (cbinfo != null && cbinfo.getListCommunityBulletin() != null) {
//                                Database.list_communityBulletin = cbinfo.getListCommunityBulletin();
//                                if (Database.list_communityBulletin.size() > 0) {
//                                    if (adapter == null) {
//                                        have_GoodsList = true;
//                                        adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, Database.list_communityBulletin, checkList
//                                                , isDoMore);
////                                    adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, community_List, checkList
////                                            , buttonList, isDoMore);
//                                        gg_Listview.setAdapter(adapter);
//                                    } else {
//                                        have_GoodsList = true;
//                                        adapter.notifyDataSetChanged();
//                                    }
//
//                                    //清空过期的已读缓存
////        if (Database.readbulletinid != null) {
//                                    String[] array = Database.readbulletinid.split(",");
//                                    if (array != null && Database.list_communityBulletin != null) {
//                                        Database.readbulletinid = "";
//                                        for (int i = 0; i < array.length; i++) {
//                                            for (int j = 0; j < Database.list_communityBulletin.size(); j++) {
////                                                if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).get("bulletinID") != null) {
////                                                    if (array[i].equals(Database.list_communityBulletin.get(j).get("bulletinID").toString())) {
////                                                        Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).get("bulletinID").toString() + ",";
////                                                    }
////                                                }
//                                                if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).getBulletinID() != null) {
//                                                    if (array[i] != null && array[i].equals(Database.list_communityBulletin.get(j).getBulletinID())) {
//                                                        Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).getBulletinID() + ",";
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
////        }
//                                } else {//没有数据
////                                noGoods.setVisibility(View.VISIBLE);
//                                    gg_Listview.setAdapter(null);
//                                    have_GoodsList = false;
//                                    getGoodsListStart = false;
//                                    lay_no_message.setVisibility(View.VISIBLE);
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
////                        pullToRefreshView.onHeaderRefreshComplete();
//                        getGoodsListStart = false;
////                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(CommunityBulletinActivity.this, getString(R.string.failed_to_connect_to_server));//未能连接到服务器
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
//                        progressDialog = ProgressDialog.show(CommunityBulletinActivity.this, "", getString(R.string.load), true);//加载
//                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
//                pullToRefreshView.onHeaderRefreshComplete();
                getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                try {
                    JSONObject jo = new JSONObject(s);
//                            String statuscode = jo.getString("statuscode");
//                            String statusmessage = jo.getString("statusmessage");
                    listCommunityBulletinInfo cbinfo = new Gson().fromJson(jo.toString(), listCommunityBulletinInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                    if (cbinfo != null && cbinfo.getListCommunityBulletin() != null) {
                        Database.list_communityBulletin = cbinfo.getListCommunityBulletin();
                        if (Database.list_communityBulletin.size() > 0) {
                            if (adapter == null) {
                                have_GoodsList = true;
                                adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, Database.list_communityBulletin, checkList, isDoMore);
//                              adapter = new CommunityAnnouceAdapter(CommunityBulletinActivity.this, community_List, checkList, buttonList, isDoMore);
                                gg_Listview.setAdapter(adapter);
                            } else {
                                have_GoodsList = true;
                                adapter.notifyDataSetChanged();
                            }

                            //清空过期的已读缓存
//        if (Database.readbulletinid != null) {
                            String[] array = Database.readbulletinid.split(",");
                            if (array != null && Database.list_communityBulletin != null) {
                                Database.readbulletinid = "";
                                for (int i = 0; i < array.length; i++) {
                                    for (int j = 0; j < Database.list_communityBulletin.size(); j++) {
//                                                if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).get("bulletinID") != null) {
//                                                    if (array[i].equals(Database.list_communityBulletin.get(j).get("bulletinID").toString())) {
//                                                        Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).get("bulletinID").toString() + ",";
//                                                    }
//                                                }
//                                        if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).getBulletinID() != null) {
//                                            if (array[i] != null && array[i].equals(Database.list_communityBulletin.get(j).getBulletinID())) {
//                                                Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).getBulletinID() + ",";
//                                            }
//                                        }
                                        if (Database.list_communityBulletin.get(j) != null && Database.list_communityBulletin.get(j).getBulletinID() != 0) {
                                            if (array[i] != null && array[i].equals(Database.list_communityBulletin.get(j).getBulletinID())) {
                                                Database.readbulletinid = Database.readbulletinid + Database.list_communityBulletin.get(j).getBulletinID() + ",";
                                            }
                                        }
                                    }
                                }
                            }
//        }
                        } else {//没有数据
//                                noGoods.setVisibility(View.VISIBLE);
                            gg_Listview.setAdapter(null);
                            have_GoodsList = false;
                            getGoodsListStart = false;
                            lay_no_message.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {
                getGoodsListStart = false;
            }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(CommunityBulletinActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url,json);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        Log.i("resultString", "Database.readbulletinid------" + Database.readbulletinid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //根据 Tag 取消请求
//        OkGo.getInstance().cancelTag(this);
//        SharePreToolsKits.putString(CommunityBulletinActivity.this, Constant.bulletinID, Database.readbulletinid); //缓存已读消息
    }


}
