package com.glory.bianyitong.ui.activity.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.CommInfo;
import com.glory.bianyitong.bean.CommentInfo;
import com.glory.bianyitong.bean.entity.request.RequestNeighborhood;
import com.glory.bianyitong.bean.entity.request.RequestOrderOperation;
import com.glory.bianyitong.bean.entity.response.ResponseAliyun;
import com.glory.bianyitong.bean.entity.response.ResponseQueryOrderList;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.ReleaseDynamicActivity;
import com.glory.bianyitong.ui.adapter.shop.CommentAddAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.util.ImageUtil;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.glory.bianyitong.widght.photos.PhotosActivity;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ArrayList<LinkedTreeMap<String, Object>> pic_list; //要上传的图片地址
    private ProgressDialog progressDialog = null;

    private List<CommInfo> list = new ArrayList<CommInfo>();//存储已选择图片路径
    private List<EventEntry> imageList=new ArrayList<>();


    // 提交数据到后台。
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {



        //重写handleMessage方法
        public void handleMessage(android.os.Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {//判断传入的消息
                case 1:

                    break;
                case 2:
                    CommInfo commInfo = (CommInfo) msg.obj;
                    list.add(commInfo);
                    Log.v("jsonss",picSize+"----"+commInfo.getHand());
                    if (picSize==commInfo.getHand()){
                       postComment();
                    }



                    break;
                default:
                    break;
            }
        }


    };
    private List<CommentInfo.ListEvaluationPicBean> listEvaluationPicBeanlist;
    private CommentInfo commentInfo;
    private List<CommentInfo> commlist;
    private int hand;
    private int picSize;

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
        getOssData();

        initData();

    }

    private void initData() {
        for (ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean entity:bean.getListOrderDetail()){
            addAdapter.addData(new ItemMenu<ResponseQueryOrderList.ListOrderBean.ListOrderDetailBean>(entity));
        }

        addAdapter.notifyItemRangeInserted(0,addAdapter.getData().size());
        Log.v("photo",addAdapter.getData().size()+"");
        for (int i=0;i<addAdapter.getData().size();i++){
            imageList.add(new EventEntry(new ArrayList<PhotoEntry>(),0));
        }
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right})
    void onClickGroup(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_text_right://发布
                boolean flag=false;
                for (int i=0;i<imageList.size();i++){
                    if (imageList.get(i).photos.size()!=0){
                        flag=false;
                        goRest();

                        return;
                    }else {
                        flag=true;
                    }
                }
                if (flag){
                    postComment();
                }
                break;

        }
    }

    private void postComment(){
        try {


        commlist = new ArrayList<>();
//                        listEvaluationPicBeanlist = new ArrayList<>();
        for (int i=0;i<bean.getListOrderDetail().size();i++){
            commentInfo = new CommentInfo();
            listEvaluationPicBeanlist=new ArrayList<>();
            for (int j=0;j<list.size();j++){
                Log.v("jsonss",list.get(j).getPosition()+"---"+list.get(j).getPath());
                if (list.get(j).getPosition()==i){
                    CommentInfo.ListEvaluationPicBean picBean=new CommentInfo.ListEvaluationPicBean();
                    picBean.setPicturePath(list.get(j).getPath());
                    listEvaluationPicBeanlist.add(picBean);
//                                    commentInfo.getListEvaluationPic().add(picBean);
                }
            }
            commentInfo.setEvaluationLevel(imageList.get(i).ratingBar+"");
            commentInfo.setEvaluationContext(imageList.get(i).comment);
            commentInfo.setAnonymous(imageList.get(i).anonymous);
            commentInfo.setOrderID(bean.getListOrderDetail().get(i).getOrderID());
            commentInfo.setFreshID(bean.getListOrderDetail().get(i).getFresh().getFreshID());
            commentInfo.setMerchant_ID(bean.getListOrderDetail().get(i).getFresh().getMerchant_ID());
            commentInfo.setListEvaluationPic(listEvaluationPicBeanlist);
            commlist.add(commentInfo);
        }

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("listFreshEvaluation",commlist);
        String json=new Gson().toJson(map);
        Log.v("jsonss",json);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if (progressDialog!=null){
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_SHORT).show();
                finish();
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

            }
        }).getEntityData(getApplicationContext(),HttpURL.HTTP_POST_APIFRESHEVALUATION_ADD,json);
        }catch (Exception e){

        }
    }



    private void goRest(){
        // 正在发送,显示进度条对话框
        progressDialog = ProgressDialog.show(CommentActivity.this, "", getString(R.string.upload), true);//上传
        progressDialog.setCanceledOnTouchOutside(true);
        hand = 0;
        picSize = 0;
        List<PhotoEntry> plist;
        for (int i=0;i<bean.getListOrderDetail().size();i++){
            plist=imageList.get(i).photos;
            if (!endpoint.equals("") && !accessKeyId.equals("") && !accessKeySecret.equals("") && !testBucket.equals("") && oss != null) {
                uploadpics(plist, i);
            } else {
                // 获取阿里云OSS相关秘钥数据
                getOssData();
            }
            picSize+=imageList.get(i).photos.size();
        }

    }


    private void uploadpics(List<PhotoEntry> plist, int content) {

        pic_list = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < plist.size(); i++) {
//            PhotoEntry pe = plist.get(i);
            urls.add(plist.get(i).getPath());
        }
        toSend(urls, content);
    }


    public void toSend(final List<String> urls, final int  content) {
        if (urls.size() <= 0) {
            // 文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
//            ImageUtil.deleteFile();//删除上传图片的文件夹
            if (content==bean.getListOrderDetail().size()-1){

                Message message = new Message();
                message.what = 1;
                message.obj = content;
                mHandler.sendMessage(message);
            }

            return;
        }
        final String url = urls.get(0);
        if (TextUtils.isEmpty(url)) {
            urls.remove(0);
            // url为空就没必要上传了，这里做的是跳过它继续上传的逻辑。
            toSend(urls, content);
            return;
        }

        File file = new File(url);
        if (null == file || !file.exists()) {
            urls.remove(0);
            // 文件为空或不存在就没必要上传了，这里做的是跳过它继续上传的逻辑。
            toSend(urls, content);
            return;
        }
        new Thread() {
            @Override
            public void run() {
                hand++;
                //压缩图片
                Bitmap drawable = BitmapFactory.decodeFile(url);
                if (drawable == null) {//如果不是图片
                    return;
                } else { //是图片
                    Bitmap bmap = ImageUtil.comp(drawable);// 初次压缩
                    Log.i("resultString", "---bmap-----" + bmap);
//                    Bitmap image = ImageUtil.compressImage(bmap);// 二次压缩
                    String filename = url.substring(url.lastIndexOf("/") + 1);// 截取pic名字
                    Log.i("resultString", "---filename-----" + filename);
                    String uploadFilePath = ImageUtil.saveMyBitmap(filename, bmap); //image
                    Log.i("resultString", "---ff-----" + uploadFilePath);
                    bmap.recycle();
//                    image.recycle();
                    // 用户ID + 时间戳 视频名称。第一个UID为上传到的bucket下面的文件夹名称。
                    final String testObject =  RequestUtil.getuserid() + "/suggestion/_" + System.currentTimeMillis() + "_SuggestPhoto" + ".jpg";
                    // 构造上传请求
                    PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);

                    // 异步上传时可以设置进度回调
                    put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                        @Override
                        public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                            Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                        }
                    });

                    OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                        @Override
                        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                            Log.d("PutObject", "UploadSuccess");

                            Log.d("ETag", result.getETag());
                            Log.d("RequestId", result.getRequestId());
                            //看这里
                            String picpath = "https://" + testBucket + "." + endpoint + "/" + testObject;
                            CommInfo commInfo=new CommInfo();
                            commInfo.setPath(picpath);
                            commInfo.setPosition(content);
                            commInfo.setHand(hand);
                            Message message = new Message();
                            message.what = 2;
                            message.obj=commInfo;
                            mHandler.sendMessage(message);
//                            CommentInfo.ListEvaluationPicBean picBean=new CommentInfo.ListEvaluationPicBean();
//                            picBean.setPicturePath(picpath);

                            Log.e("picpath--->>", picpath);
                            LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                            map.put("complaintsPictureID", 1);
                            map.put("complaintsID", 1);
                            map.put("picturePath", picpath);
                            pic_list.add(map);

                            urls.remove(0);
                            toSend(urls, content);// 递归同步效果
                        }

                        @Override
                        public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                            // 请求异常
                            if (clientExcepion != null) {
                                // 本地异常如网络异常等
                                clientExcepion.printStackTrace();
                            }
                            if (serviceException != null) {
                                // 服务异常
                                Log.e("ErrorCode", serviceException.getErrorCode());
                                Log.e("RequestId", serviceException.getRequestId());
                                Log.e("HostId", serviceException.getHostId());
                                Log.e("RawMessage", serviceException.getRawMessage());
                            }
                            Toast.makeText(CommentActivity.this, getString(R.string.image_upload_failed), Toast.LENGTH_SHORT).show();//图片上传失败
                        }

                    });
                }
            }
        }.start();
    }



    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId()==R.id.comment_photo_select){
            if(permissionCheck()){
                Intent intent=new Intent(CommentActivity.this, PhotosActivity.class);
                intent.putExtra("position",position);
                startActivityForResult(intent,2);

            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries) {
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
                imageList.set(entries.position,new EventEntry(new ArrayList<PhotoEntry>(),0));
                imageList.set(entries.position,entries);
                addAdapter.setImageList(imageList,entries.position);
                addAdapter.notifyDataSetChanged();

        }
        Log.i("imageList",imageList.size()+"");
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
        try{
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
        }catch (Exception e){

        }
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
