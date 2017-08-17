package com.glory.bianyitong.ui.activity.shop;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestNeighborhood;
import com.glory.bianyitong.bean.entity.response.ResponseAliyun;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.ReleaseDynamicActivity;
import com.glory.bianyitong.ui.adapter.shop.CommentAddAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.glory.bianyitong.widght.photos.PhotosActivity;
import com.google.gson.Gson;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 * 订单评论页面
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_COMMENT, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class CommentActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;


    @InjectParam(key = "data")
    ResponseQueryOrderList.ListOrderBean bean;
    @BindView(R.id.comment_list)
    RecyclerView commentList;

    private CommentAddAdapter addAdapter;



    // 运行sample前需要配置以下字段为有效的值
    private String endpoint = "";
    private String accessKeyId = "";
    private String accessKeySecret = "";
    private String testBucket = "";
    // 阿里云上传
    private OSS oss;
    private boolean btnbool = true;
    private ArrayList<RequestNeighborhood.ListNeighborhoodPic> pic_list; //要上传的图片地址
    private ProgressDialog progressDialog = null;

    private List<PhotoEntry> list = new ArrayList<PhotoEntry>();//存储已选择图片
    private List<EventEntry> imageList=new ArrayList<>();

    @Override
    protected int getContentId() {
        return R.layout.activity_order_comment;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        EventBus.getDefault().register(this);
        inintTitle("评价", false, "发布");
        ivTitleTextRight.setTextColor(ContextCompat.getColor(this, R.color.golden));
        if (bean != null) {
            initView();
        }

    }

    private void initView() {
        addAdapter=new CommentAddAdapter(R.layout.item_comment_list_add,this,imageList);
        addAdapter.setOnItemChildClickListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        commentList.setLayoutManager(layoutManager);
        commentList.setAdapter(addAdapter);


        initData();

    }

    private void initData() {
        for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean entity:bean.getListOrderDetail()){
            addAdapter.addData(new ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(entity));
        }

        addAdapter.notifyItemRangeInserted(0,addAdapter.getData().size());
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right})
    void onClickGroup(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_text_right://发布
                break;
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId()==R.id.comment_photo_select){
            if(permissionCheck())
                startActivityForResult(new Intent(CommentActivity.this, PhotosActivity.class),2);

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries) {
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry) {

    }


    private boolean permissionCheck(){
        boolean flag=false;
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //有权限
            flag=true;
        }
        return flag;
    }

    // 获取阿里云OSS相关秘钥数据
    public void getOssData() {
        String json=new Gson().toJson(new BaseRequestBean().getBaseRequest());
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseAliyun aliyun=new Gson().fromJson(s,ResponseAliyun.class);
                if(aliyun.getStatusCode()==1){
                    accessKeyId=aliyun.getListSetting().get(0).getSettingValue();
                    accessKeySecret=aliyun.getListSetting().get(1).getSettingValue();
                    endpoint=aliyun.getListSetting().get(2).getSettingValue()+ ".aliyuncs.com";
                    testBucket=aliyun.getListSetting().get(3).getSettingValue();
                    // 阿里云上传
                    OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
                    ClientConfiguration conf = new ClientConfiguration();
                    conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
                    conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
                    conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
                    conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
                    OSSLog.enableLog();
                    //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com"
                    oss = new OSSClient(getApplicationContext(), "http://" + endpoint, credentialProvider, conf);
                }else {
                    showShort(aliyun.getAlertMessage());
                }

            }

            @Override
            public void onError() {
                showShort("系统异常");
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
        }).getEntityData(this, HttpURL.HTTP_POST_GET_ALIYUN,json);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(CommentActivity.this, PhotosActivity.class),2);
            }else {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
