package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.AllEvaluationInfo;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.AllEvaluationAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/8/25.
 * 查看全部评论
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ALLORDER_COMMENT)
public class AllEvaluationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rb_tab_1)
    RadioButton rbTab1;
    @BindView(R.id.rb_tab_2)
    RadioButton rbTab2;
    @BindView(R.id.rb_tab_3)
    RadioButton rbTab3;
    @BindView(R.id.rb_tab_4)
    RadioButton rbTab4;
    @BindView(R.id.rg_all)
    RadioGroup rgAll;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.rec_right_list)
    RecyclerView recRightList;
    private ResponseQueryProductDetail.ListfreshBean.FreshEvaluationBean freshEvaluation;
    private List<ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean>> data = new ArrayList<>();
    private AllEvaluationAdapter adapter;
    private int freshId;
    private int evaluationLevel=0;//评价等级（0全部，1好评，3中评，3差评）

    @Override
    protected int getContentId() {
        return R.layout.activity_allevaluation;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("查看评论", false, null);
        rgAll.setOnCheckedChangeListener(this);
//        rgAll.check(R.id.rb_tab_1);
        freshEvaluation = (ResponseQueryProductDetail.ListfreshBean.FreshEvaluationBean) getIntent().getSerializableExtra("freshEvaluation");
        freshId = getIntent().getIntExtra("freshId",0);
        rbTab1.setText("全部(" + freshEvaluation.getTotalEvaluation() + ")");
        rbTab2.setText("好评(" + freshEvaluation.getPraiseNum() + ")");
        rbTab3.setText("中评(" + freshEvaluation.getCommentsNum() + ")");
        rbTab4.setText("差评(" + freshEvaluation.getBadNum() + ")");

        initView();
    }

    private void initView() {

        adapter = new AllEvaluationAdapter(R.layout.item_allevaluation, data,this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recRightList.setAdapter(adapter);
        recRightList.setLayoutManager(linearLayout);
        adapter.bindToRecyclerView(recRightList);
        onRefrush();
    }

    private void onRefrush() {
        try {


        data.clear();
        adapter.notifyDataSetChanged();
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        Map<String, Object> freshMap=new HashMap<>();
        freshMap.put("freshID",freshId);
        freshMap.put("evaluationLevel",evaluationLevel);//评价等级（0全部，1好评，3中评，5差评）
        map.put("freshEvaluation",freshMap);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                AllEvaluationInfo bean = new Gson().fromJson(s, AllEvaluationInfo.class);
                if (bean.getStatusCode() == 1) {
                    List<AllEvaluationInfo.ListFreshEvaluationBean> list = bean.getListFreshEvaluation();
                    if (!(list == null || list.size() <= 0)) {
                        for (AllEvaluationInfo.ListFreshEvaluationBean entity : list) {
                            data.add(new ItemMenu<AllEvaluationInfo.ListFreshEvaluationBean>(entity));
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.setEmptyView(R.layout.layout_empty);
                }
            }

            @Override
            public void onError() {
                adapter.setEmptyView(R.layout.layout_empty);
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
        }).getEntityData(this, HttpURL.HTTP_POST_EVALUATION_QUERY, json);
        }catch (Exception e){

        }
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();

    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2})
    void onClickGroup(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_tab_1://全部评论
                data.clear();
                evaluationLevel=0;
                onRefrush();
                break;
            case R.id.rb_tab_2://好评
                data.clear();
                evaluationLevel=5;
                onRefrush();
                break;
            case R.id.rb_tab_3://中评
                data.clear();
                evaluationLevel=3;
                onRefrush();
                break;
            case R.id.rb_tab_4://差评
                data.clear();
                evaluationLevel=1;
                onRefrush();
                break;
        }
    }
}
