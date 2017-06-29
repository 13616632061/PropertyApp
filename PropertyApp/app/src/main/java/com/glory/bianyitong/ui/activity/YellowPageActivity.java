package com.glory.bianyitong.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.YellowPageAllInfo;
import com.glory.bianyitong.bean.YellowPageGroupInfo;
import com.glory.bianyitong.bean.YellowPageInfo;
import com.glory.bianyitong.bean.entity.request.RequestYellowItem;
import com.glory.bianyitong.bean.entity.response.ResponseQueryYelloPage;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.google.gson.Gson;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.adapter.ConveniencePhoneAdapter;
import com.glory.bianyitong.ui.dialog.CallPhoneDialog;
import com.google.gson.internal.LinkedTreeMap;
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
 * Created by lucy on 2016/11/11.
 * 便民黄页
 */
public class YellowPageActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.list_conveniencephone)
    ListView list_conveniencephone;
    ConveniencePhoneAdapter adapter;

//    List<LinkedTreeMap<String, Object>> qiList; //电话列表数据

    private String phone_str = "";//要拨打的电话
    private Handler mhandler;
    private ProgressDialog progressDialog = null;

    private List<YellowPageAllInfo> dataMap=new ArrayList<>();

    //加载布局
    @Override
    protected int getContentId() {
        return R.layout.ac_yellowpage;
    }

    //初始化控件
    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.convenience_phone1), true, "");

        left_return_btn.setOnClickListener(this);
        mhandler = new Handler() { //传电话过来
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Log.i("resultString", "----------msg");
                        phone_str = (String) msg.obj;
                        startLocation();
                        break;
                }
            }
        };
//        if (Database.my_community != null && Database.my_community_List != null) {
//
//        } else {
//            Intent intent = new Intent(YellowPageActivity.this, AddAreaActivity.class); //
//            intent.putExtra("from", "index");
//            startActivity(intent);
//        }
        requestyellowPageGroup();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (Database.list_yellow != null && Database.list_yellow.size() > 0 && adapter == null) {
//            adapter = new ConveniencePhoneAdapter(YellowPageActivity.this, dataMap, mhandler);
//            list_conveniencephone.setAdapter(adapter);
//        } else if (Database.list_yellow == null || Database.list_yellow.size() == 0) {
//            requestyellowPageGroup();
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_return_btn: //返回
                YellowPageActivity.this.finish();
                break;
        }
    }

    /**
     * 黄页分组
     */
    private void requestyellowPageGroup() {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("yellowPageGroup",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryYelloPage ygInfo = new Gson().fromJson(s.toString(), ResponseQueryYelloPage.class);
                if (ygInfo.getStatusCode()==1){
                    dataMap.clear();
                    if (ygInfo != null && ygInfo.getListYellowPageGroup() != null) {
                        List<ResponseQueryYelloPage.ListYellowPageGroupBean> list = ygInfo.getListYellowPageGroup();
                        if (list != null && list.size() > 0) {
                            getallinfo(list);
                            for (int i=0;i<list.size();i++){
                                YellowPageAllInfo pageAllInfo=new YellowPageAllInfo();
                                pageAllInfo.setPageInfo(list.get(i));
                                dataMap.add(i,pageAllInfo);
                                requestlistYellowPage(list.get(i),i);
                            }

                        }
                    }
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
                progressDialog = ProgressDialog.show(YellowPageActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(HttpURL.HTTP_POST_YELLOWPAGE_QUERY,json);

    }

    /**
     * 根据分组请求详细列表
     * @param bean
     */
    private void requestlistYellowPage(final ResponseQueryYelloPage.ListYellowPageGroupBean bean,final int i) {

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("yellowPage",new RequestYellowItem(bean.getYellowPageGroupID()));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                final YellowPageAllInfo yInfo = new Gson().fromJson(s, YellowPageAllInfo.class);

                if(yInfo.getStatusCode()==1){
                    List<YellowPageAllInfo.ListYellowPageBean> list = yInfo.getListYellowPage();
                    YellowPageAllInfo allInfo=dataMap.get(i);
                    allInfo.setListYellowPage(list);
                    if (list != null && list.size() > 0) {
                        for (int j = 0; j < Database.list_yellow.size(); j++) {


                            List<YellowPageAllInfo.ListYellowPageBean> ylist = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i) != null && Database.list_yellow.get(j) != null &&
                                        list.get(i).getYellowPageGroupID() == Database.list_yellow.get(j).getYellowPageGroupID()) {
                                    YellowPageAllInfo.ListYellowPageBean bean = new YellowPageAllInfo.ListYellowPageBean();
                                    bean.setYellowPageGroupID(list.get(i).getYellowPageGroupID());
                                    if(list.get(i).getYellowPageContext() != null){
                                        bean.setYellowPageContext(list.get(i).getYellowPageContext());//名称
                                    }
                                    if(list.get(i).getYellowPageTEL() != null){
                                        bean.setYellowPageTEL(list.get(i).getYellowPageTEL()); //电话
                                    }
                                    ylist.add(bean);
                                }
                            }
                            Database.list_yellow.get(j).setListYellowPage(ylist);
                        }
                        if (Database.list_yellow.size() > 0) {
                            adapter = new ConveniencePhoneAdapter(YellowPageActivity.this, dataMap, mhandler);
                            list_conveniencephone.setAdapter(adapter);
                        } else {//没有数据

                        }
                    }
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

            }
        }).getEntityData(HttpURL.HTTP_POST_YELLOWITEM_QUERY,json);


    }

    private void getallinfo(List<ResponseQueryYelloPage.ListYellowPageGroupBean> list) {
        Database.list_yellow = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            YellowPageAllInfo bean = new YellowPageAllInfo();
            if (list.get(i) != null) {
                bean.setYellowPageGroupID(list.get(i).getYellowPageGroupID());
            }
            if (list.get(i) != null && list.get(i).getYellowPageGroupName() != null) {
                bean.setYellowPageGroupName(list.get(i).getYellowPageGroupName());
            }
            if (list.get(i) != null && list.get(i).getYellowPageGroupPicture() != null) {
                bean.setYellowPageGroupPicture(list.get(i).getYellowPageGroupPicture());
            }
            Database.list_yellow.add(bean);
        }
    }

    // android 6.0d 权限管理变了，需要在运行时手动申请，参考如下代码
    private void startLocation() {
        Log.i("resultString", "----------msg");
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(YellowPageActivity.this, Manifest.permission.CALL_PHONE);//拨打电话权限
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            } else {
                Log.i("resultString", "----------msg");
                CallPhoneDialog calldialog = CallPhoneDialog.createDialog(YellowPageActivity.this, phone_str);
                calldialog.show();
            }
        } else {
            Log.i("resultString", "----------msg");
            CallPhoneDialog calldialog = CallPhoneDialog.createDialog(YellowPageActivity.this, phone_str);
            calldialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(YellowPageActivity.this, phone_str);
                    calldialog.show();
                } else {
                    CallPhoneDialog calldialog = CallPhoneDialog.createDialog(YellowPageActivity.this, phone_str);
                    calldialog.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dataMap!=null)
            dataMap.clear();
        dataMap=null;
    }
}
