package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.sdk.share.ShareUtil;
import com.glory.bianyitong.ui.adapter.NewsCommentAdapter;
import com.glory.bianyitong.ui.dialog.ReportPopuWindow;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.ui.dialog.ShareSdkDialog;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 每日推荐详情
 */
public class EveryDayDetailsActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.erverday_bottom_like) //点赞
            LinearLayout erverday_bottom_like;
    @BindView(R.id.erverday_bottom_share) //分享
            LinearLayout erverday_bottom_share;
    @BindView(R.id.erverday_bottom_lay_right) //评论
            LinearLayout erverday_bottom_lay_right;
    @BindView(R.id.iv_like_news)
    ImageView iv_like_news; //点赞图标
    @BindView(R.id.list_everyday_comment)
    ListView list_everyday_comment;
    Handler rhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
//                bundle.putInt("reportType",1);//举报类型：1近邻2近邻评论3新闻评论
//                bundle.putInt("reportID",neighborhoodid);//举报ID(近邻id或评论id)
//                bundle.putInt("reportUserID",0);//举报人ID（默认0）
//                bundle.putString("reportUserName",Database.USER_MAP.getUserName());//举报人姓名
//                bundle.putInt("publisherID", publisherID);//发布者ID

                int reportType = msg.getData().getInt("reportType");
                int reportID = msg.getData().getInt("reportID");
                int reportUserID = msg.getData().getInt("reportUserID");
                String reportUserName = msg.getData().getString("reportUserName");
                int publisherID = msg.getData().getInt("publisherID");
                reports(reportType, reportID, reportUserID, reportUserName, publisherID);
            }
        }
    };
    private TextView tv_likeNumber;//点赞数
    private TextView dynamic_tv_comment_number;//评论数
    private NewsCommentAdapter newsAdapter;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;
    private ArrayList<LinkedTreeMap<String, Object>> commentlist;//评论数据
    private int newsid; //新闻 id
    private boolean islike = false; //新闻是否可以点赞
    private Handler handler;
    private LinkedTreeMap<String, Object> newsDetails;//新闻详情数据
    private View view_web;
    private WebView webview_ervery;
    private ProgressBar my_progress;
    private String newsURL;
    private String newsTittle;
    private String newsSubTittle;
    private String share_newsurl = "";
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: //朋友圈
                    ShareUtil.showShare(EveryDayDetailsActivity.this, msg.obj.toString(), true, newsSubTittle, newsTittle, share_newsurl, Constant.logo_path);
                    break;
                case 1://微信好友
                    Log.i("resultString", "newsSubTittle---------" + newsSubTittle);
                    Log.i("resultString", "newsTittle---------" + newsTittle);
                    Log.i("resultString", "share_newsurl---------" + share_newsurl);
                    Log.i("resultString", "Constant.logo_path---------" + Constant.logo_path);
                    ShareUtil.showShare(EveryDayDetailsActivity.this, msg.obj.toString(), true, newsSubTittle, newsTittle, share_newsurl, Constant.logo_path);
                    break;
            }
        }
    };
    private ReportPopuWindow reportPopuWindow;
    private ShareSdkDialog dialog;
    private boolean isload = false;
    private String userID;

    @Override
    protected int getContentId() {
        return R.layout.ac_everyday_details;
    }

    @Override
    protected void init() {
        super.init();
        newsURL = getIntent().getStringExtra("newsURL");
        newsid = getIntent().getIntExtra("newsID", 0); //新闻 id
        newsTittle = getIntent().getStringExtra("newsTittle");
        newsSubTittle = getIntent().getStringExtra("newsSubTittle");

        inintTitle(getString(R.string.news_details), true, "");//新闻详情
        view_web = getLayoutInflater().inflate(R.layout.lay_webview, null);
        webview_ervery = (WebView) view_web.findViewById(R.id.webview_ervery);
        my_progress = (ProgressBar) view_web.findViewById(R.id.my_progress);
        dynamic_tv_comment_number = (TextView) view_web.findViewById(R.id.dynamic_tv_comment_number);
        tv_likeNumber = (TextView) view_web.findViewById(R.id.tv_likeNumber);

        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);

        list_everyday_comment.addHeaderView(view_web);
        list_everyday_comment.addFooterView(view_loading);
        list_everyday_comment.setOnScrollListener(new AbsListView.OnScrollListener() {
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
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        userID = RequestUtil.getuserid();
        handler = new Handler() { //点赞
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:  //点赞
                        request_like(newsid, msg.arg1);
                        break;
                    case 1: //取消点赞
                        request_like_cancel(msg.arg1);
                        break;
                    case 2:
                        reportPopuWindow = new ReportPopuWindow(EveryDayDetailsActivity.this, rhandler, msg.getData());//, msg.arg1, del_handler
                        // 显示窗口
                        reportPopuWindow.showAtLocation(EveryDayDetailsActivity.this.findViewById(R.id.lay_newsdetails),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                        break;
                }
            }
        };
        erverday_bottom_like.setOnClickListener(this);
        erverday_bottom_share.setOnClickListener(this);
        erverday_bottom_lay_right.setOnClickListener(this);
        left_return_btn.setOnClickListener(this);
        view_loading.setVisibility(View.GONE);
        commentlist = new ArrayList<>();
        request(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("resultString", "------------onStart");
        if (Database.isAddComment || Database.islogin) {  //评论后刷新数据
            Log.i("resultString", "------------进来了");
            Database.isAddComment = false;
            Database.islogin = false;
            request(false);
        }
    }

    private void showdata(boolean isrefresh) {
        Log.i("resultString", "newsURL----------" + newsURL);
        if (!isload) {
            load(newsURL); //加载网页
        }
        //判断是否可以点赞
        if (newsDetails != null && newsDetails.get("likeStatu") != null
                && Double.valueOf(newsDetails.get("likeStatu").toString()).intValue() != -1) {//点赞
            iv_like_news.setImageResource(R.drawable.log_already_like);
            islike = false;
        } else {
            iv_like_news.setImageResource(R.drawable.icon_praise);
            islike = true;
        }
        if (newsDetails != null && newsDetails.get("commentCount") != null) {//评论数
            dynamic_tv_comment_number.setText(getString(R.string.comment)+" " + Double.valueOf(newsDetails.get("commentCount").toString()).intValue());
        }
        if (newsDetails != null && newsDetails.get("likeCount") != null) {//点赞数
            tv_likeNumber.setText(getString(R.string.praise)+" " + Double.valueOf(newsDetails.get("likeCount").toString()).intValue());
        }
        if (newsDetails != null && newsDetails.get("listNewsComment") != null) {//评论列表
            //调用 List 的 add()， remove()， clear()，addAll() 等方法，这种情况下，List 指向的始终是你最开始 new 出来的 ArrayList ，
            // 然后调用 adapter.notifyDataSetChanged() 方法，可以更新 ListView；但是如果你重新 new 了一个 ArrayList（重新申请了堆内存），
            // 那么这时候，List 就指向了另外一个 ArrayLIst，这时调用 adapter.notifyDataSetChanged() 方法，就无法刷新 listview 了。
            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) newsDetails.get("listNewsComment");
            if (commentlist != null) {
                commentlist.clear();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    commentlist.add(list.get(i));
                }
            }
            if (commentlist != null && commentlist.size() != 0) {
                if (newsAdapter == null || isrefresh) {
                    have_GoodsList = true;
                    newsAdapter = new NewsCommentAdapter(EveryDayDetailsActivity.this, commentlist, handler, newsid);
                    list_everyday_comment.setAdapter(newsAdapter);
                } else {
                    have_GoodsList = true;
                    newsAdapter.notifyDataSetChanged();
//                    list_everyday_comment.requestLayout();
                }
            } else {//没有数据
                noGoods.setVisibility(View.VISIBLE);
                list_everyday_comment.setAdapter(null); //隐藏listview  可以显示headview
                have_GoodsList = false;
                getGoodsListStart = false;
            }
        }
        if (newsDetails != null && newsDetails.get("newsURL") != null) { //分享的新闻链接
            share_newsurl = newsDetails.get("newsURL").toString();
        } else {
            share_newsurl = "";
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.erverday_bottom_like: //评论 点赞
                if (Database.USER_MAP != null) {
                    if (islike) {// 点赞
                        request_like(newsid, 0);
                    } else {//取消点赞
                        request_like_cancel(Double.valueOf(newsDetails.get("likeStatu").toString()).intValue());
                    }
                } else {//登录
                    Intent intent_login = new Intent();
                    intent_login.setClass(EveryDayDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
                break;
            case R.id.erverday_bottom_share: //新闻 分享
//                String logopath = ShareUtil.getRealPathFromURI(EveryDayDetailsActivity.this);
                Log.i("resultString", "------------" + Constant.logo_path);
//                String logopath = "https://www.pgagolf.cn:4432/images/Logo/%E7%89%A9%E8%81%94%E7%BD%91%E9%94%81LOGO%201024px.png";
//                ShareUtil.initOnekeyShare(EveryDayDetailsActivity.this, newsSubTittle, newsTittle, share_newsurl, Constant.logo_path);
                dialog = new ShareSdkDialog(EveryDayDetailsActivity.this, mhandler);
                // 显示窗口
                dialog.showAtLocation(EveryDayDetailsActivity.this.findViewById(R.id.lay_newsdetails),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.erverday_bottom_lay_right: //新闻 评论
                if (Database.USER_MAP != null) {
                    Intent intent = new Intent(EveryDayDetailsActivity.this, AddNewsCommentActivity.class);
                    intent.putExtra("from", "1");
                    intent.putExtra("newsID", newsid);
                    intent.putExtra("CommentToID", 0);
                    intent.putExtra("commentToUserID", 0);
                    intent.putExtra("commentToUserName", "");
                    startActivity(intent);
                } else {//登录
                    Intent intent_login = new Intent();
                    intent_login.setClass(EveryDayDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
                break;
            case R.id.left_return_btn:
                EveryDayDetailsActivity.this.finish();
                break;
        }
    }

    private void request(final boolean isrefresh) { //获取新闻评论列表
        try {


        String json = "{\"news\":{newsID:" + newsid + "},\"controllerName\":\"News\",\"actionName\":\"StructureQuery\"," +
                "\"userID\":\"" + userID + "\"}";
//        String json = "{\"news\":{newsID:6},\"controllerName\":\"News\",\"actionName\":\"StructureQuery\"," +
//                "\"userID\":\"" + userID + "\"}";
        String url ="/NewsComment/StructureQuery";
//        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/NewsComment/StructureQuery")
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listNews") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNews");
//                            if (list != null && list.size() > 0) {
//                                newsDetails = list.get(0); //近邻详情数据
//                                showdata(isrefresh);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "请求错误------");
//                        ServiceDialog.showRequestFailed();
////                        ToastUtils.showToast(EveryDayDetailsActivity.this, "请求失败...");
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
////                        progressDialog = ProgressDialog.show(EveryDayDetailsActivity.this, "", "加载..", true);
////                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
////                        if (progressDialog != null) {
////                            progressDialog.dismiss();
////                            progressDialog = null;
////                        }
//                    }
//                });

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("listNews") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNews");
                    if (list != null && list.size() > 0) {
                        newsDetails = list.get(0); //近邻详情数据
                        showdata(isrefresh);
                    }
                }
            }

            @Override
            public void onError() {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                Log.i("resultString", "请求错误------");
                ServiceDialog.showRequestFailed();
            }

            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() { }
        }).getEntityData(this,url,json);
        }catch (Exception e){

        }
    }

    private void request_like(int newsID, int commentToID) { //点赞
        try {


        String loginName = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
            loginName = Database.USER_MAP.getLoginName();
        }
        String user_pic = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
            user_pic = Database.USER_MAP.getCustomerPhoto();
        }
        String nowdate = DateUtil.formatDefaultDate(DateUtil.getCurrentDate());//获取当前时间
        String json = "{\"newsLike\": {\"newsLikeID\": 1,\"newsID\": " + newsID + ",\"userID\": 1,\"userName\": \"" + loginName + "\"," +
                "\"userPicture\": \"" + user_pic + "\",\"likeDateTime\": \"" + nowdate + "\",\"commentToID\":" + commentToID + "},\"userid\": \"" + userID + "\"," +
                "\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\"," +
                "\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\",\"controllerName\": \"NewsLike\"," +
                "\"actionName\": \"ADD\"}";
        Log.i("resultString", "json--------" + json);
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
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
                            ToastUtils.showToast(EveryDayDetailsActivity.this, getString(R.string.has_been_praised));//已点赞

                            request(false); //刷新
                            Database.isAddComment = true;
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(EveryDayDetailsActivity.this, "请求失败");
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
                        erverday_bottom_like.setClickable(false);
//                        progressDialog = ProgressDialog.show(EveryDayDetailsActivity.this, "", "加载..", true);
//                        progressDialog.setCanceledOnTouchOutside(true);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        erverday_bottom_like.setClickable(true);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
                    }
                });
        }catch (Exception e){

        }
    }

    private void request_like_cancel(int newsLikeID) { //取消点赞
        try {


        String json = "{\"newsLike\": {\"newsLikeID\": " + newsLikeID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\"," +
                "\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"NewsLike\",\"actionName\": \"Delete\"}";
        String url = HttpURL.HTTP_LOGIN;
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
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                        });
                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
//                            if (neighborhood != null && neighborhood.get("likeCount") != null) {//点赞数
//                                int z = Double.valueOf(neighborhood.get("likeCount").toString()).intValue();
//                                neighborhood.put("likeCount", z - 1);
//                                tv_likeNumber.setText("赞 " + Double.valueOf(neighborhood.get("likeCount").toString()).intValue());
//                                iv_like.setImageResource(R.drawable.icon_praise);
//                            }
                            ToastUtils.showToast(EveryDayDetailsActivity.this, getString(R.string.cancel_the_point_of_praise));//取消点赞
                            request(false); //刷新
                            Database.isAddComment = true;
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(EveryDayDetailsActivity.this, "请求失败...");
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
                        erverday_bottom_like.setClickable(false);
//                        progressDialog = ProgressDialog.show(EveryDayDetailsActivity.this, "", "加载..", true);
//                        progressDialog.setCanceledOnTouchOutside(true);
                    }
                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        erverday_bottom_like.setClickable(true);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
                    }
                });
        }catch (Exception e){

        }
    }

    private void load(String html) {
        //加载需要显示的网页
        webview_ervery.loadUrl(html);
        //设置WebView属性，能够执行Javascript脚本
        webview_ervery.getSettings().setPluginState(WebSettings.PluginState.ON); //支持插件
        WebSettings webSettings = webview_ervery.getSettings();  //android 5.0以上
//        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        //Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setUseWideViewPort(false); //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows(); //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        // 在Android中点击一个链接，默认是调用应用程序来启动，因此WebView需要代为处理这个动作 通过WebViewClient
        // 设置WebViewClient
        webview_ervery.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) { //https
                handler.proceed(); //接受证书
            }
        });

        webview_ervery.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    my_progress.setVisibility(View.GONE);
                } else {
                    if (View.GONE == my_progress.getVisibility()) {
                        my_progress.setVisibility(View.VISIBLE);
                    }
                    my_progress.setProgress(newProgress);
                    int x = (int) (newProgress / 2);
                    my_progress.setSecondaryProgress(newProgress + x);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        isload = true;
    }

    private void reports(int reportType, int reportID, int reportUserID, String reportUserName, int publisherID) {
        //reportType 举报类型：1近邻2近邻评论3新闻评论
        //reportID 举报ID(近邻id或评论id)
        //reportUserID 举报人ID（默认0）
        //reportUserName 举报人姓名
        //publisherID 发布者ID
        //reportTime 举报日期
        //statu 是否处理（默认false）
        String json = "{\"reports\":{\"reportType\":" + reportType + ",\"reportID\":" + reportID + ",\"reportUserID\":" + reportUserID + ",\"reportUserName\":\"" + reportUserName + "\"," +
                "\"publisherID\":" + publisherID + ",\"reportTime\":\"" + RequestUtil.getCurrentTime() + "\",\"statu\":false},\"controllerName\": \"Report\",\"actionName\": \"ADD\"," +
                "\"userID\": \"" + RequestUtil.getuserid() + "\",\"datetime\": \"" + RequestUtil.getCurrentTime() + "\"}";
        String url = HttpURL.HTTP_LOGIN;
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {

                    ToastUtils.showToast(EveryDayDetailsActivity.this, getString(R.string.has_been_reported));//已举报
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
        }).getEntityData(this,url, json);
    }

}
