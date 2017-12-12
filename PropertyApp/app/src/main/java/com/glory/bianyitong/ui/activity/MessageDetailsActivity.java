package com.glory.bianyitong.ui.activity;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.ui.fragment.IndexFragment;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/28.
 * 消息详情
 */
public class MessageDetailsActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_ans_title)
    TextView tv_ans_title;

    @BindView(R.id.tv_ans_time)
    TextView tv_ans_time;

    @BindView(R.id.tv_ans_content)
    TextView tv_ans_content;

    String id = "";
    private int PushID;
    private String messageTitle;
    private String messageContext;
    private String messageTime;

    @Override
    protected int getContentId() {
        return R.layout.ac_message_details;
    }

    @Override
    protected void init() {
        super.init();
        PushID = getIntent().getIntExtra("PushID", 0);

        id = getIntent().getStringExtra("id");
        inintTitle(getString(R.string.message_details), true, "");
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                MessageDetailsActivity.this.finish();
            }
        });
        messageTitle = getIntent().getStringExtra("messageTitle");
        messageContext = getIntent().getStringExtra("messageContext");
        messageTime = getIntent().getStringExtra("messageTime");
        if (messageTitle == null) {
            messageTitle = "";
        }
        if (messageContext == null) {
            messageContext = "";
        }
        if (messageTime == null) {
            messageTime = "";
        }
        tv_ans_title.setText(messageTitle);
        tv_ans_content.setText(messageContext);
        if (messageTime.length()>10){
            tv_ans_time.setText(messageTime.substring(0,10));
        }else {
            tv_ans_time.setText(messageTime);
        }
        String msg = getIntent().getStringExtra("msg");
        if (msg!=null){
            tv_ans_content.setText(msg);
        }
        if (PushID != 0) {
            request(PushID);
            List<String> messageRead = SharedUtil.getDataList("messageRead");
            messageRead.add(PushID+"");
            SharedUtil.setDataList("messageRead",messageRead);
            IndexFragment.callBack.notifyNum();
        }
    }

    private void request(int messageid) {
//        String userID = RequestUtil.getuserid();
//        int communityID = RequestUtil.getcommunityid();
//
//        String json = "{\"systemMsg\": {\"messageID\":" + messageid + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
//                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
//                "\"controllerName\": \"SystemMsg\",\"actionName\": \"StructureQuery\"}";
//        Log.i("resultString", "json----------" + json);
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> map2=new HashMap<>();
        map2.put("messageID",messageid);
        map.put("systemMsg",map2);
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                if (hashMap2 != null && hashMap2.get("listSystemMsg") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listSystemMsg");
                    if (list != null && list.size() != 0) {
                        if (list.get(0) != null && list.get(0).get("messageTitle") != null) {
                            tv_ans_title.setText(list.get(0).get("messageTitle").toString());
                        }
                        if (list.get(0) != null && list.get(0).get("messageContext") != null) {
                            tv_ans_content.setText(list.get(0).get("messageContext").toString());
                        }
                        if (list.get(0) != null && list.get(0).get("messageTime") != null) {
                            if (list.get(0).get("messageTime").toString().length()>10){
                                tv_ans_time.setText(list.get(0).get("messageTime").toString().substring(0,10));
                            }else {
                                tv_ans_time.setText(list.get(0).get("messageTime").toString());
                            }
                        }
                    }
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
        }).getEntityData(this,HttpURL.HTTP_POST_COUPON_SYSTEMMSG,json);

    }

}
