package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.MessageInfo;
import com.glory.bianyitong.http.OkGoRequest;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/28.
 * 信息中心
 */
public class MessageActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    //    @BindView(R.id.rl_bottom_mes)
//    RelativeLayout rl_bottom_mes;
//    @BindView(R.id.tv_del_mes)
//    TextView tv_del_mes;
//    @BindView(R.id.tv_all_read_mes)
//    TextView tv_all_read_mes;
    @BindView(R.id.lay_no_message)
    RelativeLayout lay_no_message;
    private List<LinkedTreeMap<String, Object>> removeList;
//    private List<LinkedTreeMap<String, Object>> message_List;
    private List<MessageInfo.ListSystemMsgBean> message_List;
    private HashMap<Integer, Boolean> checkList;//这是头部那个chexkbox
    private boolean isDoMore;//是否进行编辑默认为false
    private MessageAdapter adapter;
    private ListView listView_mes;
    //    @BindView(R.id.message_list_refresh)
//    NewPullToRefreshView pullToRefreshView;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_message;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.message_center), false, getString(R.string.all_read));//消息中心 全部已读
//        inintTitle("消息中心",false,getString(R.string.edit));
//        inintTitle("消息中心", true, "");
        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        left_return_btn.setOnClickListener(this);//返回
        iv_title_text_right.setOnClickListener(this); //编辑  取消
//        tv_del_mes.setOnClickListener(this);//删除
//        tv_all_read_mes.setOnClickListener(this);
        listView_mes = (ListView) findViewById(R.id.listView_mes);
        if (Database.readmessageid == null || Database.readmessageid.equals("")) {
            Database.readmessageid = mCache.getAsString(Constant.messageID);
        }
        if (Database.readmessageid == null || Database.readmessageid.equals("")) {
            Database.readmessageid = "";
        }
        isDoMore = false;
        checkList = new HashMap<Integer, Boolean>();
        removeList = new ArrayList<>();
        initview();
//        getLsist();
        request();

    }

    private void initview() {
        listView_mes.addFooterView(view_loading);
//        listView_mes.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                // 当不滚动时
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    // 判断是否滚动到底部
//                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                        //加载更多功能的代码
//                        if (Database.GOODS_LIST != null && have_GoodsList && !getGoodsListStart) {
//                            getGoodsListStart = true;
//                            loading_lay.setVisibility(View.VISIBLE);
//                            index_page++;
//                            getGoodsList(index_page, false);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });

//        pullToRefreshView.setOnHeaderRefreshListener(new NewPullToRefreshView.OnHeaderRefreshListener() {
//
//            @Override
//            public void onHeaderRefresh(NewPullToRefreshView view) {
////                if (message_List != null) {
////                    getGoodsListStart = true;
////                    index_page = 0;//重置index_page
////                    index_page++;
////                    getGoodsList(index_page, true);//刷新
//
//                request();
////                }
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                MessageActivity.this.finish();
                break;
            case R.id.iv_title_text_right: //编辑 取消
//                if (message_List != null && message_List.size() > 0) {
//                    if (isDoMore) {//编辑
//                        adapter.setIsDoMore(false);
//                        adapter.notifyDataSetChanged();
//                        iv_title_text_right.setText("编辑");
//                        rl_bottom_mes.setVisibility(View.GONE);
//                        isDoMore = false;
//                    } else {
//                        adapter.setIsDoMore(true);
//                        adapter.notifyDataSetChanged();
//                        isDoMore = true;
//                        iv_title_text_right.setText("取消");
//                        rl_bottom_mes.setVisibility(View.VISIBLE);
//                    }
//                }
                Database.readmessageid = "";
                for (int i = 0; i < message_List.size(); i++) {
//                    if (message_List.get(i) != null && message_List.get(i).get("messageID") != null) {
//                        Database.readmessageid = Database.readmessageid + message_List.get(i).get("messageID").toString() + ",";
//                    }
                    if (message_List.get(i) != null && message_List.get(i).getMessageID() != null) {
                        Database.readmessageid = Database.readmessageid + message_List.get(i).getMessageID() + ",";
                    }
                }
                adapter.notifyDataSetChanged();
                Database.notreadmessageidSize = 0;
                mCache.put(Constant.messageID,Database.readmessageid);
//                SharePreToolsKits.putString(MessageActivity.this, Constant.messageID, Database.readmessageid); //缓存已读消息
                break;
//            case R.id.tv_del_mes: //删除
////                删除这部分有点复杂，比如12345 你删除了13 haspmap里面保存了245 但是2这项其实到了第一条了，如果你不做处理的话，
////                他会显示第二条已读所以要再删除后重新排列，下面先移除选中数据
//                checkList = MessageAdapter.getIsCheck();
//                for (int i = 0; i < checkList.size(); i++) {
//                    if (checkList.get(i)) {
//                        removeList.add(message_List.get(i));
//                    }
//                }
//                if (removeList.size() > 0) {
//                    //  删掉文本数据
//                    message_List.removeAll(removeList);
//                    removeList = null;
//                    adapter.updateList(message_List);
//                    isDoMore = false;
//                    //  这里是更新选中的checkbox，删除后 全部设置为false
//                    adapter.upDateCheckList();
//                    //  隐藏checkbox 恢复显示图片
//                    adapter.setIsDoMore(isDoMore);
//                    adapter.notifyDataSetChanged();
//                    iv_title_text_right.setText("编辑");
//                    rl_bottom_mes.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.tv_all_read_mes://全部已读
//                checkList = MessageAdapter.getIsCheck();
//                for (int i = 0; i < checkList.size(); i++) {
//                    if (checkList.get(i)) {
//                        removeList.add(message_List.get(i));
//                    }
//                }
//                for (int i = 0; i < removeList.size(); i++) {
//                    if (removeList != null && removeList.get(i) != null && removeList.get(i).get("messageID") != null) {
//                        boolean has = false;
//                        String[] array = Database.readmessageid.split(",");
//                        if (array != null && array.length > 0) {
//                            for (int j = 0; j < array.length; j++) {
//                                if (array[i].equals(removeList.get(i).get("messageID").toString())) {
//                                    has = true;
//                                }
//                            }
//                        }
//                        if (!has) {
//                            Database.readmessageid = Database.readmessageid + removeList.get(i).get("messageID").toString() + ",";
//                        }
//                        Log.i("resultString", "------------" + removeList.get(i).get("messageID").toString());
//                    }
//                    Log.i("resultString", "------------" + removeList);
//                }
//
//                adapter.updateList(message_List);
//                isDoMore = false;
//
//                //  这里是更新选中的checkbox，删除后 全部设置为false
//                adapter.upDateCheckList();
//                //  隐藏checkbox 恢复显示图片
//
//                adapter.setIsDoMore(isDoMore);
//                adapter.notifyDataSetChanged();
//                iv_title_text_right.setText("编辑");
//                rl_bottom_mes.setVisibility(View.GONE);
//                Log.i("resultString", "Database.readmessageid------" + Database.readmessageid);
//                break;
        }
    }

    private void request() { //请求社区公告

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("systemMsg",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                MessageInfo minfo = new Gson().fromJson(s, MessageInfo.class);
//                            Log.i("resultString", "adinfo.getListHousekeeper()-------" + hinfo.getListHousekeeper());
                if (minfo != null && minfo.getListSystemMsg() != null) {
                    message_List = minfo.getListSystemMsg();
                    if (message_List.size() > 0) {
                        if (adapter == null) {
                            have_GoodsList = true;
                            adapter = new MessageAdapter(MessageActivity.this, message_List, checkList, isDoMore);
                            listView_mes.setAdapter(adapter);
                        } else {
                            have_GoodsList = true;
                            adapter.notifyDataSetChanged();
                        }

                        //清空过期的已读缓存
                        //if (Database.readmessageid != null) {
                        String[] array = Database.readmessageid.split(",");
                        if (array != null && message_List != null) {
                            Database.readmessageid = "";
                            for (int i = 0; i < array.length; i++) {
                                for (int j = 0; j < message_List.size(); j++) {
//                                                if (message_List.get(j) != null && message_List.get(j).get("messageID") != null) {
//                                                    if (array[i].equals(message_List.get(j).get("messageID").toString())) {
//                                                        Database.readmessageid = Database.readmessageid + message_List.get(j).get("messageID").toString() + ",";
//                                                    }
//                                                }
                                    if (message_List.get(j) != null && message_List.get(j).getMessageID() != null) {
                                        if (array[i].equals(message_List.get(j).getMessageID())) {
                                            Database.readmessageid = Database.readmessageid + message_List.get(j).getMessageID() + ",";
                                        }
                                    }
                                }
                            }
                        }
//        }
                    } else {//没有数据
//                                noGoods.setVisibility(View.VISIBLE);
                        listView_mes.setAdapter(null);
                        have_GoodsList = false;
                        listView_mes.setVisibility(View.GONE);
                        lay_no_message.setVisibility(View.VISIBLE);
                    }
                } else {
//                            noGoods.setVisibility(View.VISIBLE);
                    listView_mes.setAdapter(null);
                    have_GoodsList = false;
                    listView_mes.setVisibility(View.GONE);
                    lay_no_message.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError() {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                listView_mes.setVisibility(View.GONE);
                lay_no_message.setVisibility(View.VISIBLE);
            }

            @Override
            public void parseError() {

            }

            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(MessageActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(HttpURL.HTTP_POST_GET_MESSAGE,json);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        Log.i("resultString", "Database.messageID------" + Database.readmessageid);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        SharePreToolsKits.putString(MessageActivity.this, Constant.messageID, Database.readmessageid); //缓存已读消息
//    }

}
