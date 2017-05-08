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
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.ImageUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.photos.ChooseAdapter;
import com.glory.bianyitong.widght.photos.EventEntry;
import com.glory.bianyitong.widght.photos.PhotosActivity;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;
//import top.zibin.luban.Luban;
//import top.zibin.luban.OnCompressListener;

/**
 * Created by lucy on 2016/11/21.
 * 发布动态
 */
public class ReleaseDynamicActivity extends BaseActivity implements ChooseAdapter.OnItmeClickListener {

    @BindView(R.id.et_release_dynamic)
    EditText et_release_dynamic;
    //标题
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_left)
    TextView iv_title_text_left;

    private RecyclerView mRecyclerView;
    private ChooseAdapter mAdapter;

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
    private String userID = "";
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
                    release(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_release_dynamic;
    }

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
        inintTitle(getString(R.string.publish_dynamic), false, getString(R.string.release));//发布动态  发布
        left_return_btn.setVisibility(View.GONE);

        iv_title_text_left.setVisibility(View.VISIBLE);
        iv_title_text_left.setText(getString(R.string.cancel));//取消
        iv_title_text_left.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                ReleaseDynamicActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//发布
            @Override
            public void onClick(View v) {
                String content = et_release_dynamic.getText().toString().trim();
                if (content.equals("")) {
                    ToastUtils.showToast(ReleaseDynamicActivity.this, getString(R.string.publish_content_can_not_be_empty));//发布内容不能为空
                } else {
                    List<PhotoEntry> plist = mAdapter.getData(); //从图片选择器中获取图片
                    if (plist != null && plist.size() > 0) { //有图片
                        iv_title_text_right.setClickable(false);
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
                        iv_title_text_right.setClickable(false);
                        release(content);
                    }
                }

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_release_dynamic);
        mAdapter = new ChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 4, true));
        userID = RequestUtil.getuserid();
        getOssData();
    }

    private void uploadpics(List<PhotoEntry> plist, String content) {
        // 正在发送,显示进度条对话框
        progressDialog = ProgressDialog.show(ReleaseDynamicActivity.this, "", getString(R.string.upload), true);//上传
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
//        Luban.get(this)
//                .load(file)                     //传人要压缩的图片
//                .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
//                .setCompressListener(new OnCompressListener() { //设置回调
//                    @Override
//                    public void onStart() {
//                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
//                    }
//                    @Override
//                    public void onSuccess(File file) {
//                        // TODO 压缩成功后调用，返回压缩后的图片文件
//                        Log.i("resultString", "-后--file-----" + file);
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        // TODO 当压缩过去出现问题时调用
//                    }
//                }).launch();    //启动压缩

        new Thread() {
            @Override
            public void run() {
                //压缩图片
                Bitmap drawable = BitmapFactory.decodeFile(url);
                if (drawable == null) {//如果不是图片
                    return;
                } else { //是图片
                    Bitmap bmap = ImageUtil.comp(drawable);// 初次压缩
//                    Bitmap image = ImageUtil.compressImage(bmap);// 二次压缩
                    String filename = url.substring(url.lastIndexOf("/") + 1);// 截取pic名字
                    Log.i("resultString", "---filename-----" + filename);
                    String uploadFilePath = ImageUtil.saveMyBitmap(filename, bmap); //image
                    Log.i("resultString", "---ff-----" + uploadFilePath);
                    bmap.recycle();
//                    image.recycle();
                    // 用户ID + 时间戳 视频名称。第一个UID为上传到的bucket下面的文件夹名称。
                    final String testObject = userID + "/surrounding/_" + System.currentTimeMillis() + "ReleasePhoto" + ".jpg";
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
                            map.put("neighborhoodPictureID", 1);
                            map.put("neighborhoodID", 1);
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
                            Toast.makeText(ReleaseDynamicActivity.this, getString(R.string.image_upload_failed), Toast.LENGTH_SHORT).show();//图片上传失败
                        }
                    });
                }
            }
        }.start();
    }

    private void release(String content) { //发布
        String jsonlist = "";
        if (pic_list != null && pic_list.size() > 0) {
            jsonlist = JsonHelper.toJson(pic_list);
        } else {
            jsonlist = "[]";
        }
        String loginName = "";
//        if (Database.USER_MAP != null && Database.USER_MAP.get("loginName") != null) {
//            loginName = Database.USER_MAP.get("loginName").toString();
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
            loginName = Database.USER_MAP.getLoginName();
        }
        String customerPhoto = "";
//        if (Database.USER_MAP != null && Database.USER_MAP.get("customerPhoto") != null) {
//            customerPhoto = Database.USER_MAP.get("customerPhoto").toString();
//        }
        if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
            customerPhoto = Database.USER_MAP.getCustomerPhoto();
        }
        int communityID = RequestUtil.getcommunityid();
//        String communityName = "";//
//        if (Database.my_community != null && Database.my_community.get("communityName") != null) {
//            communityName = Database.my_community.get("communityName").toString();
//        }
        String communityName = "";//
        if (Database.my_community != null && Database.my_community.getCommunityName() != null) {
            communityName = Database.my_community.getCommunityName();
        }
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间
        String json = "{\"neighborhood\":{\"neighborhoodID\":1,\"userID\":1,\"userName\":\"" + loginName + "\",\"userPhoto\":\"" + customerPhoto + "\"," +
                "\"communityID\":" + communityID + ",\"communityName\":\"" + communityName + "\",\"neighborhoodContent\":\"" + content + "\"," +
                "\"datetime\":\"" + nowdate + "\"},\"listNeighborhoodPicture\":" + jsonlist + ",\"controllerName\":\"Neighborhood\"," +
                "\"actionName\":\"ADD\",\"userID\":\"" + userID + "\"}";
        Log.i("resultString", "json--------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/Neighborhood/ADD")
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
                            Toast.makeText(ReleaseDynamicActivity.this, getString(R.string.published_successfully), Toast.LENGTH_SHORT).show();//发布成功
                            Database.isAddarea = true;
                            EventBus.getDefault().post("MyRelease");
                            ReleaseDynamicActivity.this.finish();
                        } else {
                            Toast.makeText(ReleaseDynamicActivity.this, getString(R.string.release_failed), Toast.LENGTH_SHORT).show();//发布失败
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(ReleaseDynamicActivity.this, "请求失败...");
                        ServiceDialog.showRequestFailed();
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (progressDialog != null) {
                            // 更新完列表数据，则关闭对话框
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                        iv_title_text_right.setClickable(true);
                    }
                });

    }

    // 获取阿里云OSS相关秘钥数据
    public void getOssData() {
        String json = "{\"settingkey\": \"ALiOSSBucket\",\"controllerName\": \"\",\"actionName\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"" +
                ",\"userID\": \"" + userID + "\"}";
        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/Setting/SelectAllByOSS")
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
                        if (hashMap2 != null && hashMap2.get("listsettingvalue") != null) {
                            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listsettingvalue");
                            if (list != null && list.size() != 0) {
                                // ALiOSSAKeyID ALiOSSKeySecret ALiOSSRegion ALiOSSBucket
                                if (list.get(0) != null && list.get(0).get("settingValue") != null) {
                                    accessKeyId = list.get(0).get("settingValue").toString();
                                }
                                if (list.get(1) != null && list.get(1).get("settingValue") != null) {
                                    accessKeySecret = list.get(1).get("settingValue").toString();
                                }
                                if (list.get(2) != null && list.get(2).get("settingValue") != null) {
                                    endpoint = list.get(2).get("settingValue").toString() + ".aliyuncs.com";
                                }
                                if (list.get(3) != null && list.get(3).get("settingValue") != null) {
                                    testBucket = list.get(3).get("settingValue").toString();
                                }
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
                            }
                        }
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
                });
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount() - 1) {
//            count++;
//            if(count==1){
            startxc(); //权限请求
//            }
            startActivity(new Intent(ReleaseDynamicActivity.this, PhotosActivity.class));
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
    }

    // android 6.0d 权限管理变了，属于隐私权限，需要在运行时手动申请，参考如下代码
    private void startxc() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(ReleaseDynamicActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return;
            } else {
                //有权限
            }
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
