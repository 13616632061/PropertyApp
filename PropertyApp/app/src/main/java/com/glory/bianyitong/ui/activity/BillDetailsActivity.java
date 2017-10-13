package com.glory.bianyitong.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BillDetailsInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2017/10/13.
 * 账单详情
 */
public class BillDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.left_return_btn)
    RelativeLayout leftReturnBtn;
    @BindView(R.id.iv_title_text_left)
    TextView ivTitleTextLeft;
    @BindView(R.id.title_ac_text)
    TextView titleAcText;
    @BindView(R.id.iv_title_text_right)
    TextView ivTitleTextRight;
    @BindView(R.id.shequ)
    TextView shequ;
    @BindView(R.id.fangjian)
    TextView fangjian;
    @BindView(R.id.leixing)
    TextView leixing;
    @BindView(R.id.feiyong)
    TextView feiyong;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.beizhu)
    TextView beizhu;

    @Override
    protected int getContentId() {
        return R.layout.activity_billdetails;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("账单详情", true, "");//账单详情
        initview();
    }

    private void initview() {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("userBillID",getIntent().getIntExtra("userBillID",0));
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    BillDetailsInfo bean = new Gson().fromJson(s, BillDetailsInfo.class);
                    if (bean.getStatusCode()==1){
                        shequ.setText(bean.getUserBill().getCommnuityName());
                        fangjian.setText(bean.getUserBill().getRoomName());
                        leixing.setText(bean.getUserBill().getDisplayName());
                        feiyong.setText(bean.getUserBill().getAmount()+"");
                        time.setText(bean.getUserBill().getCreateDate());
                        beizhu.setText(bean.getUserBill().getRemark());
                    }else {
                        showShort(bean.getAlertMessage());
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
            }).getEntityData(this, HttpURL.HTTP_POST_COUPON_APIUSERBILL_QUERY, json);
        } catch (Exception e) {

        }
    }
    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2})
    void onClickBtn(View view) {
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
}
