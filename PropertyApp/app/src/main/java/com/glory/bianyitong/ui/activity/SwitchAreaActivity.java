package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.CommnunityInfo;
import com.glory.bianyitong.bean.entity.request.RequestQueryUserInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUserById;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/22.
 * 切换小区
 */
public class SwitchAreaActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.ll_area_list)
    LinearLayout ll_area_list;
    @BindView(R.id.tv_addarea_auth)
    TextView tvAddareaAuth;
    private AuthAreaInfo areaInfo;
    private String userName;

    @Override
    protected int getContentId() {
        return R.layout.ac_switch_area;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.switch_the_cell), true, "");//切换小区

        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                SwitchAreaActivity.this.finish();
            }
        });

        tvAddareaAuth.setOnClickListener(new View.OnClickListener() { //添加小区
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(AuthAreaActivity.this, AddAreaActivity.class);
//                startActivity(intent);
                if (userName!=null){
                    Intent intent = new Intent(SwitchAreaActivity.this, AddAreaCityActivity.class);
                    startActivity(intent);
                }else {
                    showShort("请填写您的真实姓名");
                    Intent intent = new Intent(SwitchAreaActivity.this, PersonalDataActivity.class);
                    startActivity(intent);
                }

            }
        });
//        request();
//        if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null){
//            Router.build(RouterMapping.ROUTER_ACTIVITY_AUTHAREA)
//                    .go(SwitchAreaActivity.this);
//        }



//        if (Database.my_community_List != null) {
////            ArrayList<LinkedTreeMap<String, Object>> list = Database.my_community_List;
////            for (int i = 0; i < list.size(); i++) {
////                for (int j = list.size() - 1; j > i; j--) {
////                    if  (list.get(j).get("communityID").toString().equals(list.get(i).get("communityID").toString()))   {
////                        list.remove(j);
////                    }
////                }
////            }
////            ScrollViewLayout(SwitchAreaActivity.this, list, ll_area_list);
//        }else {
//            Intent intent = new Intent(SwitchAreaActivity.this, AddAreaCityActivity.class); //
//            intent.putExtra("from", "");//index
//            startActivity(intent);
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
        user_request();

    }
    private ProgressDialog progressDialog;
    private void request() { //获取社区
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("userCommnunityMapping", new Object());
            String jsons = new Gson().toJson(map);
            progressDialog = ProgressDialog.show(this, "","加载中", true);
            progressDialog.setCanceledOnTouchOutside(true);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    try {
                        JSONObject jo = new JSONObject(s);
                        areaInfo = new Gson().fromJson(jo.toString(), AuthAreaInfo.class);
                        if (areaInfo != null && areaInfo.getListUserCommnunityMapping() != null) {
                            DataUtils.getUesrCommunity2(areaInfo.getListUserCommnunityMapping());
//                            DataUtils.saveSharePreToolsKits(SwitchAreaActivity.this);
                            ScrollViewLayout(SwitchAreaActivity.this, Database.my_community_List, ll_area_list);
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onError() {
                    progressDialog.dismiss();
                }

                @Override
                public void parseError() {
                    progressDialog.dismiss();
                }

                @Override
                public void onBefore() {

                }

                @Override
                public void onAfter() {
                    progressDialog.dismiss();
                }
            }).getEntityData(this, "/ApiUserCommnunity/Query", jsons);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (Database.my_community_List != null) {
////            ArrayList<LinkedTreeMap<String, Object>> list = Database.my_community_List;
////            for (int i = 0; i < list.size(); i++) {
////                for (int j = list.size() - 1; j > i; j--) {
////                    if  (list.get(j).get("communityID").toString().equals(list.get(i).get("communityID").toString()))   {
////                        list.remove(j);
////                    }
////                }
////            }
//
////            ScrollViewLayout(SwitchAreaActivity.this, list, ll_area_list);
//            ScrollViewLayout(SwitchAreaActivity.this, Database.my_community_List, ll_area_list);
//        }
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<CommnunityInfo> list, LinearLayout lay_gallery) {//List<LinkedTreeMap<String, Object>> list
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {

                    final View view = mInflater.inflate(R.layout.view_item_switch_area, lay_gallery, false);
                    final RelativeLayout item_area_lay = (RelativeLayout) view.findViewById(R.id.item_area_lay);
                    final TextView item_area_name = (TextView) view.findViewById(R.id.item_area_name);
                    final TextView item_area_line = (TextView) view.findViewById(R.id.item_area_line);
                    final ImageView item_area_select = (ImageView) view.findViewById(R.id.item_area_select);
                    final TextView auth_text = (TextView) view.findViewById(R.id.auth_text);
                    final ImageView auth_image = (ImageView) view.findViewById(R.id.auth_image);
//                if (list != null && list.get(i).get("communityName") != null && list.get(i).get("communityName").toString().length() != 0 && !list.get(i).get("communityName").toString().equals("")) {
//                    item_area_name.setText(list.get(i).get("communityName").toString());
//                }
                    if (list != null && list.get(i).getCommunityName() != null && list.get(i).getCommunityName().length() != 0 && !list.get(i).getCommunityName().toString().equals("")) {
                        item_area_name.setText(list.get(i).getCommunityName() + list.get(i).getBuildingName() + list.get(i).getUnitName() + list.get(i).getRoomName()); //小区名称
                    }
//                if (Database.my_community != null && Database.my_community.get("communityID") != null) {
//                    if (list.get(i).get("communityID").toString().equals(Database.my_community.get("communityID").toString())) {
//                        item_area_select.setVisibility(View.VISIBLE);
//                        item_area_lay.setClickable(false);
//                    }
//                }
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
                    if (Database.my_community != null) {
                        if (list.get(i).getUserCommunityID() == Database.my_community.getUserCommunityID()) {
                            item_area_select.setVisibility(View.VISIBLE);
                            item_area_lay.setClickable(false);
                        }
                    }
                    if (i == list.size() - 1) { //最后一根线
                        item_area_line.setVisibility(View.GONE);
                    }
                    final int j = i;

                final int finalI = i;
                item_area_lay.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            // TODO Auto-generated method stub
//                        if (list.get(j).get("communityID") != null) {
//                            Database.my_community = list.get(j);
//                            SharePreToolsKits.putString(SwitchAreaActivity.this, Constant.communityID, list.get(j).get("communityID").toString()); //缓存所选的小区id
//                            item_area_select.setVisibility(View.VISIBLE);
//                            EventBus.getDefault().post(true);
//                            SwitchAreaActivity.this.finish();
//                        }
                            if (list.get(finalI).getApprovalStatus()==1) {

                                if (list.get(j) != null) {
                                    Database.my_community = list.get(j);
                                    mCache.put(Constant.community, new Gson().toJson(Database.my_community)); //缓存所选的社区
                                    mCache.put(Constant.communityID, list.get(j).getUserCommunityID() + ""); //缓存所选的小区id
                                    item_area_select.setVisibility(View.VISIBLE);
                                    EventBus.getDefault().post(true);
                                    SwitchAreaActivity.this.finish();
                                }
                            }else {
                                showShort("请添加已认证小区");
                            }
                        }
                    });
                lay_gallery.addView(view);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
