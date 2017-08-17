package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.bean.entity.request.RequestSearchFresh;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.bean.entity.response.ResponseSearchTag;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.FruitListAdapter;
import com.glory.bianyitong.ui.adapter.GridSearchTagAdapter;
import com.glory.bianyitong.ui.adapter.shop.FreshSearchAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 搜索页
 */
@Route(RouterMapping.ROUTER_ACTIVITY_PRODUCT_SEARCH)
public class SearchActivity extends BaseActivity implements View.OnKeyListener,BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.RequestLoadMoreListener,TextWatcher {
    @BindView(R.id.tv_search_cancel)
    TextView tv_search_cancel;
    @BindView(R.id.clean_word)
    RelativeLayout clean_word;
    @BindView(R.id.tv_search_txt)
    EditText tv_search_txt;

    @BindView(R.id.lay_search_last) //最近搜索
            LinearLayout lay_search_last;
    @BindView(R.id.lay_search_last_delete)
    RelativeLayout lay_search_last_delete;
    @BindView(R.id.gv_search_last)
    GridView gv_search_last;
    @BindView(R.id.lay_search_hot)//热门搜索
            LinearLayout lay_search_hot;
    @BindView(R.id.gv_search_hot)
    GridView gv_search_hot;
    @BindView(R.id.lay_search_nothing) //无结果
            LinearLayout lay_search_nothing;
    @BindView(R.id.search_listView)
    RecyclerView search_listView;

    private FruitListAdapter fruitListAdapter;
    private ProgressDialog progressDialog = null;

    private int index_page = 1;
    //    private String tag_str = "";
//    private int type_search = 0;
    private String freshName = "";

    private Handler cc_handler;

    private FreshSearchAdapter adapter;
    private List<ItemMenu<ResponseSearchFresh.ListfreshBean>> datas=new ArrayList<>();

    private GridSearchTagAdapter hotTagAdapter;
    private List<String> hotTag=new ArrayList<>();//热门标签数据
    private GridSearchTagAdapter localTagAdapter;
    private List<String> localTag=new ArrayList<>();//最近搜索标签数据

    @Override
    protected int getContentId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        super.init();
        adapter=new FreshSearchAdapter(R.layout.view_item_commodity,datas);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        search_listView.setLayoutManager(layoutManager);
        search_listView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(this,search_listView);


        cc_handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: // 输入框
                        freshName = msg.obj.toString();
                        index_page=1;
                            lay_search_last.setVisibility(View.GONE);
                            lay_search_hot.setVisibility(View.GONE);
                            tv_search_txt.setText(freshName);
                            tv_search_txt.setSelection(freshName.length());
                            request2(index_page, freshName);//, tag_str, 0
                        if(!localTag.contains(freshName)){
                            localTag.add(freshName);
                            localTagAdapter.notifyDataSetChanged();
                        }
                        if(!TextUtils.isEmpty(freshName)){
                            datas.clear();
                            request2(index_page, freshName);
                        }


                        break;
                    case 1: //热门搜索标签
                        index_page=1;
                        freshName = msg.obj.toString();
                            progressDialog = ProgressDialog.show(SearchActivity.this, "", "", true);
                            progressDialog.setCanceledOnTouchOutside(true);

                            lay_search_last.setVisibility(View.GONE);
                            lay_search_hot.setVisibility(View.GONE);
                            tv_search_txt.setText(freshName);
                            tv_search_txt.setSelection(freshName.length());
                        if(!TextUtils.isEmpty(freshName)){
                            datas.clear();
                            request2(index_page, freshName);
                        }
                        break;
                    case 2: // 最近搜索
                        index_page=1;
                        freshName=localTag.get(msg.arg1);
                        if(!TextUtils.isEmpty(freshName)){
                            lay_search_last.setVisibility(View.GONE);
                            lay_search_hot.setVisibility(View.GONE);
                            tv_search_txt.setText(freshName);
                            tv_search_txt.setSelection(freshName.length());
                            datas.clear();
                            request2(index_page,freshName);
                        }

                        break;
                }
            }
        };


        hotTagAdapter=new GridSearchTagAdapter(this,hotTag,cc_handler,1);
        localTagAdapter=new GridSearchTagAdapter(this,localTag,cc_handler,2);

        gv_search_hot.setAdapter(hotTagAdapter);
        gv_search_last.setAdapter(localTagAdapter);


        initSearch();
        initview();
        request_hottag();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 初始化搜索记录
     */
    private void initSearch() {
        String json=mCache.getAsString(Constant.SEARCH);
        if(!TextUtils.isEmpty(json)){
            List<String> list=new Gson().fromJson(json,new TypeToken<List<String>>(){}.getType());
            localTag.addAll(list);
        }

    }

    private void initview() {


        tv_search_txt.setOnKeyListener(this);
        tv_search_cancel.setOnClickListener(this);
        clean_word.setOnClickListener(this);
        lay_search_last_delete.setOnClickListener(this);
        tv_search_txt.addTextChangedListener(this);

    }

    /**
     * 查询商品列表
     * @param page
     * @param name
     */
    private void request2(int page, String name) { //1 热门标签搜索  0 输入框搜索 , String tag, int type

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("fresh",new RequestSearchFresh(name));
        map.put("currentPageNumber",page);
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseSearchFresh searchFresh=new Gson().fromJson(s,ResponseSearchFresh.class);
                if(searchFresh.getStatusCode()==1){
                    List<ResponseSearchFresh.ListfreshBean> list=searchFresh.getListfresh();

                    search_listView.setAdapter(adapter);
                    if(!(list==null || list.size()<=0)){
                        for (ResponseSearchFresh.ListfreshBean bean:list
                                ) {
                            datas.add(new ItemMenu<ResponseSearchFresh.ListfreshBean>(bean));
                        }
                        adapter.notifyDataSetChanged();
                    }else {

                    }
                }
            }

            @Override
            public void onError() {
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
        }).getEntityData(this,HttpURL.HTTP_POST_FRESH_QUERY_SEARCH,json);

    }

    /**
     * 热门搜索标签
     */
    private void request_hottag() { //获取热门搜索
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                hotTag.clear();
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                ResponseSearchTag tags=new Gson().fromJson(s,ResponseSearchTag.class);
                if(tags.getStatusCode()==1){
                        hotTag.addAll(tags.getTags());
                        hotTagAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError() {
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

            }
        }).getEntityData(this,HttpURL.HTTP_POST_FRESH_QUERY_TAG,json);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                SearchActivity.this.finish();
                break;
            case R.id.clean_word:
                tv_search_txt.setText("");
                if (hotTag != null && hotTag.size() != 0) {
                    lay_search_last.setVisibility(View.VISIBLE);
                } else {
                    lay_search_last.setVisibility(View.GONE);
                }
                lay_search_hot.setVisibility(View.VISIBLE);
                search_listView.setAdapter(null);
                break;
            case R.id.lay_search_last_delete:
                lay_search_last.setVisibility(View.GONE);
                mCache.remove(Constant.SEARCH);//缓存最近搜索数据
                localTag.clear();
                localTagAdapter.notifyDataSetChanged();
                break;
        }
    }



    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            //这里写发送信息的方法
            String tag_name = tv_search_txt.getText().toString().trim();
            if(TextUtils.isEmpty(tag_name)){
                showShort(getString(R.string.search_can_not_be_empty));
            }else {
                Message msg = new Message();
                msg.obj = tag_name;
                msg.what = 0;
                cc_handler.sendMessage(msg);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_DETAIL)
                .with("data",datas.get(position).getData())
                .go(this);
    }

    @Override
    public void onLoadMoreRequested() {
        index_page++;
        request2(index_page, freshName); //, tag_str, type_search
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() > 0) {
            clean_word.setVisibility(View.VISIBLE);
        } else {
            clean_word.setVisibility(View.GONE);


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCache.put(Constant.SEARCH,new Gson().toJson(localTag));
    }
}
