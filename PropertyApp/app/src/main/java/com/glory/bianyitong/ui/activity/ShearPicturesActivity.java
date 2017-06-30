package com.glory.bianyitong.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestUserBean;
import com.glory.bianyitong.bean.entity.response.ResponseAliyun;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.ImageUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.SharePreToolsKits;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.photoViewUtil.ClipZoomImageView;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 修改头像
 */
public class ShearPicturesActivity extends BaseActivity implements OnClickListener {

    private RelativeLayout left_return_btn, save_btn; //, rotating_btn
    private TextView the_title;
    private ClipZoomImageView imageView;

    private Bitmap bm_degree = null;// 要旋转的图片。
    private BitmapDrawable drawable_degree = null;// 将Bitmap转换成Drawable对象

    private boolean isCamera = false;
    private ProgressDialog progressDialog = null;

    private String type = "";
    private int okok = 0; //判断能否保存

    // 运行sample前需要配置以下字段为有效的值
    private String endpoint = "";
    private String accessKeyId = "";
    private String accessKeySecret = "";
    private String testBucket = "";
    // 阿里云上传
    private OSS oss;
    private boolean btnbool = true;
    private String picpath = "";// 上传阿里云的头像图地址
    private String userID = "";
    // 提交数据到后台。
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        //重写handleMessage方法
        public void handleMessage(android.os.Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {//判断传入的消息
                case 1:
                    ImageUtil.deleteFile(); //上传成功删除图片
                    if (picpath != null && !picpath.equals("") && picpath.length() != 0) {

                        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
                        map.put("user",new RequestUserBean(Database.USER_MAP.getLoginName(),Database.USER_MAP.getCustomerPhoto(),picpath));
                        String json=new Gson().toJson(map);
                        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                            @Override
                            public void onSuccess(String s) {
                                if(TextUtil.isEmpty(s)){
                                    showShort("系统异常");
                                    return;
                                }
                                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                                if(bean.getStatusCode()==1){
                                    Database.USER_MAP.setCustomerPhoto(picpath);
                                    showShort( getString(R.string.successfully_modified));
                                    DataUtils.saveSharePreToolsKits(ShearPicturesActivity.this);
                                    ShearPicturesActivity.this.finish();
                                }else {
                                    ToastUtils.showToast(ShearPicturesActivity.this,bean.getAlertMessage());//修改失败
                                }
                            }

                            @Override
                            public void onError() {
                                showShort(getString(R.string.system_error));
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
                                    // 更新完列表数据，则关闭对话框
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                                save_btn.setClickable(true);
                                ShearPicturesActivity.this.finish();
                            }
                        }).getEntityData(HttpURL.HTTP_POST_MY_EDITUSERINFO,json);

                    } else {
                        if (progressDialog != null) {
                            // 更新完列表数据，则关闭对话框
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                        // 发送失败
                        Toast.makeText(ShearPicturesActivity.this, getString(R.string.save_failed), Toast.LENGTH_SHORT).show();//保存失败
                        ShearPicturesActivity.this.finish();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * use to lessen pic 50%
     *
     * @param path sd card path
     * @return bitmap
     */
    public final static Bitmap lessenUriImage(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时返回 bm 为空
        options.inJustDecodeBounds = false; //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = (int) (options.outHeight / (float) 320);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be; //重新读入图片，注意此时已经把 options.inJustDecodeBounds 设回 false 了
        bitmap = BitmapFactory.decodeFile(path, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        System.out.println(w + " " + h); //after zoom
        return bitmap;
    }

    @Override
    protected int getContentId() {
        return R.layout.shear_pictures_lay;
    }

    @Override
    protected void init() {
        super.init();
        type = getIntent().getStringExtra("type");
        Log.i("resultString", "type----" + type);
        if (type.equals("Photo")) {// 相册//
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 1);
        } else if (type.equals("Camera")) {// 拍照
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 2);
        }
        userID = RequestUtil.getuserid();
        left_return_btn = (RelativeLayout) findViewById(R.id.left_return_btn);
        save_btn = (RelativeLayout) findViewById(R.id.save_btn);
        the_title = (TextView) findViewById(R.id.the_title);
        the_title.setText(getString(R.string.modify_avatar));// 修改头像
        left_return_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);

        imageView = (ClipZoomImageView) findViewById(R.id.id_clipImageLayout);
        imageView.setHorizontalPadding(55);// 图片水平方向与View的边距
        // 获取阿里云OSS相关秘钥数据
        getData();
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.left_return_btn:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShearPicturesActivity.this.finish();
                    }
                }, 1200);
                break;
            case R.id.save_btn:
                ServiceDialog.ButtonClickZoomInAnimation(save_btn, 0.95f);
                if (okok == 1) {
                    ToastUtils.showToast(ShearPicturesActivity.this, getString(R.string.this_image_is_invalid));//此图片无效
                    return;
                }
                if (type.equals("Camera")) {
                    if (isCamera) { //是否取到照片
                        // 此处获取剪裁后的bitmap
                        Bitmap bitmap = imageView.clip();
                        if (btnbool) {
                            // 发送消息
                            if (!endpoint.equals("") && !accessKeyId.equals("") && !accessKeySecret.equals("") && !testBucket.equals("") && oss != null) {
                                if (bitmap != null && !bitmap.equals("")) {
                                    toSend(bitmap);
                                } else {
                                    ToastUtils.showToast(ShearPicturesActivity.this, getString(R.string.authentication_can_not_be_empty));//认证不能为空
                                }
                            } else {
                                // 获取阿里云OSS相关秘钥数据
                                getData();
                            }
                        }

                    } else {
                        ToastUtils.showToast(ShearPicturesActivity.this, getString(R.string.no_image));//没有图片
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ShearPicturesActivity.this.finish();
                            }
                        }, 1200);
                    }
                } else if (type.equals("Photo")) {
                    // 此处获取剪裁后的bitmap
                    Bitmap bitmap = imageView.clip();
                    if (btnbool) {
                        // 发送消息
                        if (!endpoint.equals("") && !accessKeyId.equals("") && !accessKeySecret.equals("") && !testBucket.equals("") && oss != null) {
                            if (bitmap != null && !bitmap.equals("")) {
                                toSend(bitmap);
                            } else {
                                ToastUtils.showToast(ShearPicturesActivity.this, getString(R.string.the_picture_can_not_be_empty));//头像不能为空
                            }
                        } else {
                            // 获取阿里云OSS相关秘钥数据
                            getData();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    // 发送消息
    public void toSend(final Bitmap bitm) {
        save_btn.setClickable(false);
        if (bitm != null && !bitm.equals("")) {
            // 正在发送,显示进度条对话框
            progressDialog = ProgressDialog.show(ShearPicturesActivity.this, "", getString(R.string.save), true);//保存
            progressDialog.setCanceledOnTouchOutside(true);
            // 上传
            new Thread(new Runnable() {
                public void run() {
                    btnbool = false;
                    // 上传图片
                    // 用户ID + 时间戳 视频名称。第一个UID为上传到的bucket下面的文件夹名称。
                    String uploadObject = userID + "/userHeader/_" + System.currentTimeMillis() + "_logo" + ".jpg";
                    // 把图片进行压缩
                    Bitmap bitmap = ImageUtil.comp(bitm);
                    if (bitmap != null) {
                        // 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] datas = baos.toByteArray();
                        // 把二进制转换成String./ String str = Base64.encodeToString(datas, Base64.DEFAULT);

                        // 直接上传二进制数据，使用阻塞的同步接口
                        // 构造上传请求
                        PutObjectRequest put2 = new PutObjectRequest(testBucket, uploadObject, datas);
                        try {
                            PutObjectResult putResult = oss.putObject(put2);
                            picpath = "https://" + testBucket + "." + endpoint + "/" + uploadObject;

                            Log.e("picpath--->>", picpath);
                            Log.d("PutObject", "UploadSuccess");
                            Log.d("ETag", putResult.getETag());
                            Log.d("RequestId", putResult.getRequestId());
                            Log.e("ETag", putResult.getETag());
                        } catch (ClientException e) {
                            // 本地异常如网络异常等
                            e.printStackTrace();
                        } catch (ServiceException e) {
                            // 服务异常
                            Log.e("RequestId", e.getRequestId());
                            Log.e("ErrorCode", e.getErrorCode());
                            Log.e("HostId", e.getHostId());
                            Log.e("RawMessage", e.getRawMessage());
                        }
                    }

                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
            }).start();
        } else {
            btnbool = true;
        }
    }

    // 获取阿里云OSS相关秘钥数据
    public void getData() {
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
        }).getEntityData(HttpURL.HTTP_POST_GET_ALIYUN,json);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:  // 如果是直接从相册获取
                if (data != null && !data.equals("")) {
                    try {
                        String selectedImagePath2 = getPath(data.getData()); //selectedImagePath2=/mnt/sdcard/DCIM/100MEDIA/IMAG0054.jpg;
                        bm_degree = lessenUriImage(selectedImagePath2);
                        BitmapDrawable drawable = new BitmapDrawable(getResources(), lessenUriImage(selectedImagePath2));
                        // 设置需要裁剪的图片
                        imageView.setImageDrawable(drawable);
                    } catch (Exception e) {
                        ToastUtils.showToast(ShearPicturesActivity.this, getString(R.string.this_image_is_invalid));//此图片无效
                        okok = 1;
                    }
                } else {
                    ShearPicturesActivity.this.finish();
                }
                break;
            case 2:  // 如果是调用相机拍照时
                Log.i("resultString", "22222----" + data);
                if (data != null && !data.equals("")) {
                    final Bitmap photo = data.getParcelableExtra("data");
                    if (photo != null && !photo.equals("")) {
                        bm_degree = photo;
                        BitmapDrawable drawable = new BitmapDrawable(getResources(), photo);
                        // 设置需要裁剪的图片
                        imageView.setImageDrawable(drawable);
                        isCamera = true;
                    }
                } else {
                    ShearPicturesActivity.this.finish();
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
