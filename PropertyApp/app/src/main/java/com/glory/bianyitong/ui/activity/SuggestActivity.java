package com.glory.bianyitong.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestSuggest;
import com.glory.bianyitong.bean.entity.response.ResponseAliyun;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.PhotoPopuWindow;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.ActivityUtils;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.ImageUtil;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.photos.ChooseAdapter;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.glory.bianyitong.widght.photos.PhotosActivity;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/11.
 * 投诉建议
 */
public class SuggestActivity extends BaseActivity implements ChooseAdapter.OnItmeClickListener {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.tv_suggest_type_name)
    TextView tv_suggest_type_name;
    @BindView(R.id.tv_suggest_submit)
    TextView tv_suggest_submit;
    @BindView(R.id.et_content_suggest_type)
    EditText et_content_suggest_type;

    private RecyclerView mRecyclerView;
    private ChooseAdapter mAdapter;
    private int typeid = 0; //投诉类型id
    private String typename = ""; //投诉类型名称
    private String userID = "";
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
    //    private int count = 0;
    // 提交数据到后台。
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        //重写handleMessage方法
        public void handleMessage(android.os.Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {//判断传入的消息
                case 1:
                    submit(typeid, typename, msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.ac_suggest;
    }

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);

        inintTitle(getString(R.string.suggest), true, "");//投诉建议

        left_return_btn.setOnClickListener(this);
        tv_suggest_type_name.setOnClickListener(this);
        tv_suggest_submit.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 4, true));
        userID = RequestUtil.getuserid();
        getOssData();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn:
                SuggestActivity.this.finish();
                break;
            case R.id.tv_suggest_type_name:
                Intent intent = new Intent(SuggestActivity.this, SuggestTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_suggest_submit:
                String content = et_content_suggest_type.getText().toString().trim();
                if (typeid == 0 || typename.equals("")) {
                    ToastUtils.showToast(SuggestActivity.this, getString(R.string.please_select_the_type_of_complaint));//请选择投诉类型
                } else if (content.equals("")) {
                    ToastUtils.showToast(SuggestActivity.this, getString(R.string.complaints_can_not_be_empty));//投诉内容不能为空
                } else {
                    List<PhotoEntry> plist = mAdapter.getData(); //从图片选择器中获取图片
                    if (plist != null && plist.size() > 0) { //有图片
                        if (btnbool) {
                            // 发送消息
                            if (!endpoint.equals("") && !accessKeyId.equals("") && !accessKeySecret.equals("") && !testBucket.equals("") && oss != null) {
                                uploadpics(plist, content);
                            } else {
                                // 获取阿里云OSS相关秘钥数据
                                getOssData();
                            }
                        }
                    } else { //无图片
                        submit(typeid, typename, content);
                    }
                }
                break;

        }
    }

    private void uploadpics(List<PhotoEntry> plist, String content) {
        // 正在发送,显示进度条对话框
        progressDialog = ProgressDialog.show(SuggestActivity.this, "", getString(R.string.upload), true);//上传
        progressDialog.setCanceledOnTouchOutside(true);
        pic_list = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < plist.size(); i++) {
//            PhotoEntry pe = plist.get(i);
            urls.add(plist.get(i).getPath());
        }
        toSend(urls, content);
    }

    public void toSend(final List<String> urls, final String content) {
        if (urls.size() <= 0) {
            // 文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
            ImageUtil.deleteFile();//删除上传图片的文件夹
            Message message = new Message();
            message.what = 1;
            message.obj = content;
            mHandler.sendMessage(message);
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
                    final String testObject = userID + "/suggestion/_" + System.currentTimeMillis() + "_SuggestPhoto" + ".jpg";
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
                            String picpath = "https://" + testBucket + "." + endpoint + "/" + testObject;
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
                            Toast.makeText(SuggestActivity.this, getString(R.string.image_upload_failed), Toast.LENGTH_SHORT).show();//图片上传失败
                        }
                    });
                }
            }
        }.start();
    }

    private void submit(int TypeID, String TypeName, String content) { //提交
        String jsonlist = "";
        if (pic_list != null && pic_list.size() > 0) {
            jsonlist = JsonHelper.toJson(pic_list);
        } else {
            jsonlist = "[]";
        }
        String userName = "";
//        if (Database.USER_MAP != null && Database.USER_MAP.get("userName") != null) {
//            userName = Database.USER_MAP.get("userName").toString();
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getUserName() != null) {
            userName = Database.USER_MAP.getUserName();
        }
        String phoneNumber = "";
//        if (Database.USER_MAP != null && Database.USER_MAP.get("phoneNumber") != null) {
//            phoneNumber = Database.USER_MAP.get("phoneNumber").toString();
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getPhoneNumber() != null) {
            phoneNumber = Database.USER_MAP.getPhoneNumber();
        }
        int communityID = RequestUtil.getcommunityid();
        String communityName = "";//
        if (Database.my_community != null && Database.my_community.getCommunityName() != null) {
            communityName = Database.my_community.getCommunityName();
        }
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间
        String json = "{\"complaints\":{\"complaintsID\":1,\"complaintsTypeID\":" + TypeID + ",\"complaintsTypeName\":\"" + TypeName + "\",\"userID\":5," +
                "\"userName\":\"" + userName + "\",\"complaintsPhone\":\"" + phoneNumber + "\",\"complaintsDateTime\":\"" + nowdate + "\",\"communityID\":" + communityID + "," +
                "\"communityName\":\"" + communityName + "\",\"complaintsContent\":\"" + content + "\"},\"listComplaintsPicture\":" + jsonlist + "," +
                "\"controllerName\":\"Complaints\",\"actionName\":\"ADD\",\"userID\":\"" + userID + "\"}";
        Log.i("resultString", "json--------" + json);

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        RequestSuggest requestSuggest=new RequestSuggest(TypeID,Database.USER_MAP.getPhoneNumber(),content);
        List<RequestSuggest.ListComplaintsPic> list=new ArrayList<>();
//        map.put("complaintsPictureID", 1);
//        map.put("complaintsID", 1);
//        map.put("picturePath", picpath);
//        pic_list.add(map);
        if(pic_list!=null)
        for (LinkedTreeMap<String,Object> maps:pic_list
             ) {
            if(maps!=null){
                list.add(new RequestSuggest.ListComplaintsPic((int)maps.get("complaintsID"),(String) maps.get("picturePath")));
            }
        }
        requestSuggest.setListComplaintsPic(list);
        map.put("complaints",requestSuggest);
        json=new Gson().toJson(map);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                showShort(bean.getAlertMessage());
                if(bean.getStatusCode()==1){
                    finish();
                }

            }

            @Override
            public void onError() {
                showShort(getString(R.string.system_error));
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
                    // 更新完列表数据，则关闭对话框
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_COMPLAINTS_ADD,json);



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
        }).getEntityData(this,HttpURL.HTTP_POST_GET_ALIYUN,json);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Database.suggest_type_name_id.equals("")) {
            String[] array = Database.suggest_type_name_id.split(",");
            typeid = Double.valueOf(array[1]).intValue();
            typename = array[0];
            tv_suggest_type_name.setText(typename);
        }
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount() - 1) {
//            count++;
//            if(count==1){
            startxc(); //权限请求
//            }
//            startActivity(new Intent(SuggestActivity.this, PhotosActivity.class));
            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(), EventEntry.SELECTED_PHOTOS_ID));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries) {
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry) {
        mAdapter.appendPhoto(entry);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        Database.suggest_type_name_id = "";
    }

    // android 6.0d 权限管理变了，属于隐私权限，需要在运行时手动申请，参考如下代码
    private void startxc() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(SuggestActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return;
            } else {
                //有权限
                startActivity(new Intent(SuggestActivity.this, PhotosActivity.class));
            }
        } else {
            startActivity(new Intent(SuggestActivity.this, PhotosActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(SuggestActivity.this, PhotosActivity.class));
                } else {
                    startActivity(new Intent(SuggestActivity.this, PhotosActivity.class));
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
