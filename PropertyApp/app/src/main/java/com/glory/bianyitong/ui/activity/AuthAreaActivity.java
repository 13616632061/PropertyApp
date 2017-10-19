package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.CommnunityInfo;
import com.glory.bianyitong.bean.entity.request.RequestQueryUserInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUserById;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.util.DataUtils;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/14.
 * 认证小区
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AUTHAREA,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AuthAreaActivity extends BaseActivity {

    @BindView(R.id.tv_addarea_auth) //添加小区
            TextView tv_addarea_auth;

    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.auth_area_list)
    LinearLayout auth_area_list;
    private ProgressDialog progressDialog = null;
    private String userName;
    //    ArrayList<LinkedTreeMap<String, Object>> communitylist;
//    private String from = "";

    @Override
    protected int getContentId() {
        return R.layout.activity_auth_area;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.certified_area), true, "");//认证小区
//        from = getIntent().getStringExtra("from");
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                AuthAreaActivity.this.finish();
            }
        });
        tv_addarea_auth.setOnClickListener(new View.OnClickListener() { //添加小区
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(AuthAreaActivity.this, AddAreaActivity.class);
//                startActivity(intent);
                if (userName!=null){
                    Intent intent = new Intent(AuthAreaActivity.this, AddAreaCityActivity.class);
                    startActivity(intent);
                }else {
                    showShort("请填写您的真实姓名");
                    Intent intent = new Intent(AuthAreaActivity.this, PersonalDataActivity.class);
                    startActivity(intent);
                }
            }
        });
//        if (getIntent().getStringExtra("from").equals("index")) {
//            Intent intent = new Intent(AuthAreaActivity.this, AddAreaActivity.class);
//            startActivity(intent);
//        }
        request();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user_request();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Database.isAddarea) {
            request();
            Database.isAddarea = false;
        }
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<CommnunityInfo> list, LinearLayout lay_gallery) {//List<LinkedTreeMap<String, Object>> list
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_auth_area, lay_gallery, false);
                final RelativeLayout item_auth_lay = (RelativeLayout) view.findViewById(R.id.item_auth_lay);
                final TextView auth_area_name = (TextView) view.findViewById(R.id.auth_area_name);
                final ImageView auth_image = (ImageView) view.findViewById(R.id.auth_image);
                final TextView auth_text = (TextView) view.findViewById(R.id.auth_text);
                final TextView auth_area_line = (TextView) view.findViewById(R.id.auth_area_line);
//                if (list != null && list.get(i).get("communityName") != null && list.get(i).get("communityName").toString().length() != 0 && !list.get(i).get("communityName").toString().equals("")) {
//                    auth_area_name.setText(list.get(i).get("communityName").toString()); //小区名称
//                }
                if (list != null && list.get(i).getCommunityName() != null && list.get(i).getCommunityName().length() != 0 && !list.get(i).getCommunityName().toString().equals("")) {
                    auth_area_name.setText(list.get(i).getCommunityName()+list.get(i).getBuildingName()+list.get(i).getUnitName()+list.get(i).getRoomName()); //小区名称
                }
                if (list != null && list.get(i) != null) {
//                    int sta = Double.valueOf(list.get(i).get("approvalStatus").toString()).intValue();
                    int sta = list.get(i).getApprovalStatus();
                    if (sta == 1) { //已审核
                        auth_text.setText(getString(R.string.audited));
                        auth_image.setImageResource(R.drawable.log_auth_already);
                    } else if (sta == 2) {//待审核
                        auth_text.setText(getString(R.string.pending_review));
                        auth_image.setImageResource(R.drawable.log_auth_checking);
                    } else if (sta == 0) {//审核失败
                        auth_text.setText(getString(R.string.audit_failure));
                        auth_image.setImageResource(R.drawable.log_auth_checking);
                    }
                }
                if (i == list.size() - 1) { //最后一根线
                    auth_area_line.setVisibility(View.GONE);
                }
                final int j = i;
                item_auth_lay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    private void request() { //获取社区
        try {
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userCommnunityMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

                try {
                    JSONObject jo = new JSONObject(s);
                    AuthAreaInfo areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);
                    if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                        DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
                        DataUtils.saveSharePreToolsKits(AuthAreaActivity.this);
                        ScrollViewLayout(AuthAreaActivity.this, Database.my_community_List, auth_area_list);
                    } else {
//                        ToastUtils.showToast(AuthAreaActivity.this, getString(R.string.get_community_failure)); //获取社区失败
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AuthAreaActivity.this, "", getString(R.string.obtain), true);//Obtain 获取
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,"/ApiUserCommnunity/Query", jsons);
        }catch (Exception e){

        }
    }
    private void user_request() { //获取个人信息
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("user", new RequestQueryUserInfo((String) (map.get("userID"))));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryUserById responseQueryUserById = new Gson().fromJson(s, ResponseQueryUserById.class);
                if (responseQueryUserById.getStatusCode() == 1) {
                    userName = responseQueryUserById.getListUser().get(0).getUserName();
                } else {
                }

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
        }).getEntityData(this, HttpURL.HTTP_POST_QUERY_USER_INFO, json);
    }
}
