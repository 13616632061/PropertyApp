package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.ComplaintsTypeInfo;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/28.
 * 投诉建议类型
 */
public class SuggestTypeActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.ll_suggest_type_list)
    LinearLayout ll_suggest_type_list;
//    ArrayList<LinkedTreeMap<String, Object>> typelist;
    ComplaintsTypeInfo typelist;
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_suggest_type;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.types_of_complaint_suggestions), true, "");//投诉建议类型
        left_return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuggestTypeActivity.this.finish();
            }
        });
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("text", "公共设施" + i);
//            list.add(map);
//        }
        request();
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<ComplaintsTypeInfo.ListComplaintsTypeBean> list, LinearLayout lay_gallery) {//ArrayList<LinkedTreeMap<String, Object>>
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_suggest_type, lay_gallery, false);

                final TextView item_tv_suggest_type = (TextView) view.findViewById(R.id.item_tv_suggest_type);
                final TextView item_tv_suggest_line = (TextView) view.findViewById(R.id.item_tv_suggest_line);
//            if (list != null && list.get(i).get("picture") != null && list.get(i).get("picture").toString().length() != 0 && !list.get(i).get("picture").toString().equals("")) {
//                setPicture(list.get(i).get("picture").toString(), type_pic, ImageView.ScaleType.FIT_CENTER);
//            }

//                if (list != null && list.get(i).get("complaintsTypeName") != null && list.get(i).get("complaintsTypeName").toString().length() != 0 && !list.get(i).get("complaintsTypeName").toString().equals("")) {
//                    item_tv_suggest_type.setText(list.get(i).get("complaintsTypeName").toString());
//                }
                if (list != null && list.get(i).getComplaintsTypeName() != null && list.get(i).getComplaintsTypeName().length() != 0) {
                    item_tv_suggest_type.setText(list.get(i).getComplaintsTypeName());
                }
                if (i == list.size() - 1) {
                    item_tv_suggest_line.setVisibility(View.GONE);
                }
                final int j = i;
                item_tv_suggest_type.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
//                        if (list.get(j).get("complaintsTypeName") != null) {
//                            String name = list.get(j).get("complaintsTypeName").toString(); //类型名称
//                            String id = list.get(j).get("complaintsTypeID").toString();     //类型id
//                            Database.suggest_type_name_id = name + "," + id;
//                            SuggestTypeActivity.this.finish();
//                        }
                        if (list.get(j).getComplaintsTypeName() != null) {
                            String name = list.get(j).getComplaintsTypeName(); //类型名称
                            int id = list.get(j).getComplaintsTypeID();     //类型id
                            Database.suggest_type_name_id = name + "," + id;
                            SuggestTypeActivity.this.finish();
                        }
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    private void request() { //获取投诉类型

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("complaintsType",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if(TextUtil.isEmpty(s)){
                    showShort("系统异常");
                    return;
                }

                typelist = new Gson().fromJson(s, ComplaintsTypeInfo.class);
                if (typelist != null && typelist.getListComplaintsType() != null) {
                    if(typelist.getStatusCode()==1){
                        List<ComplaintsTypeInfo.ListComplaintsTypeBean> clist = typelist.getListComplaintsType();
                        if (clist.size() > 0) {
                            ScrollViewLayout(SuggestTypeActivity.this, clist, ll_suggest_type_list);
                        } else {//没有数据
                            showShort(typelist.getAlertMessage());
                        }
                    }else {
                        showShort(typelist.getAlertMessage());
                    }

                }else {
                    showShort("系统异常");
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
        }).getEntityData(this,HttpURL.HTTP_POST_COMPLAINTS_TYPE,json);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }


}
