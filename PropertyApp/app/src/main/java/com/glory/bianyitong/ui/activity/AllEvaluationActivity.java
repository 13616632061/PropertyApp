package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.router.RouterMapping;

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
    private ResponseQueryProductDetail.ListfreshBean.FreshEvaluationBean freshEvaluation;


    @Override
    protected int getContentId() {
        return R.layout.activity_allevaluation;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("查看评论", false, null);
        rgAll.setOnCheckedChangeListener(this);
        rgAll.check(R.id.rb_tab_1);
        freshEvaluation = (ResponseQueryProductDetail.ListfreshBean.FreshEvaluationBean) getIntent().getSerializableExtra("freshEvaluation");
        rbTab1.setText("全部("+freshEvaluation.getTotalEvaluation()+")");
        rbTab2.setText("好评("+freshEvaluation.getPraiseNum()+")");
        rbTab3.setText("中评("+freshEvaluation.getCommentsNum()+")");
        rbTab4.setText("差评("+freshEvaluation.getBadNum()+")");
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();

    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickGroup(View view){
        switch (view.getId()){
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
        switch (checkedId){
            case R.id.rb_tab_1://全部评论
                break;
            case R.id.rb_tab_2://好评
                break;
            case R.id.rb_tab_3://中评
                break;
            case R.id.rb_tab_4://差评
                break;
        }
    }
}
