package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.WeiXinInfo;
import com.glory.bianyitong.bean.entity.response.ResponseSubmitOrder;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 * 支付订单页面
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER_PAY, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PayActivity extends BaseActivity {
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
    @BindView(R.id.order_pay_number)
    TextView orderPayNumber;
    @BindView(R.id.order_pay_money)
    TextView orderPayMoney;
    @BindView(R.id.order_pay_group_wx)
    RadioButton orderPayGroupWx;
    @BindView(R.id.order_pay_group_apay)
    RadioButton orderPayGroupApay;
    @BindView(R.id.order_pay_group)
    RadioGroup orderPayGroup;

    @InjectParam(key = "OrderID")
    int OrderID;
    @InjectParam(key = "orderCode")
    long orderCode;

    @InjectParam(key = "price")
    double price;
    public static String appId;

    @Override
    protected int getContentId() {
        return R.layout.activity_order_play;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("收银台",false,"");
        if(orderCode>0)
            initView();

    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.order_pay_btn})
    void onClickGroup(View view){
        switch (view.getId()){
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.order_pay_btn:
                orderCommit();
                break;
        }
    }
    private void initView(){
        orderPayNumber.setText(orderCode+"");
        orderPayMoney.setText("¥ "+price);
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderCommit();
    }

    /**
     * 支付回调
     */
    private void orderCommit(){
        Map<String,Object> map=new HashMap<>();
        map.put("OrderID",OrderID);
        map.put("DeviceType",2);//,1、ios 2、android
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                WeiXinInfo bean=new Gson().fromJson(s,WeiXinInfo.class);
                appId = bean.getAppId();
                WeiXinPayRequest(bean);
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
        }).getEntityData(this, HttpURL.HTTP_POST_COUPON_PAY,json);
    }

    private void weChatPay(WeiXinInfo bean) {
        IWXAPI api = WXAPIFactory.createWXAPI(this, bean.getAppId(),true);
        api.registerApp(bean.getAppId());
        PayReq req = new PayReq();
        req.appId = bean.getAppId();
        req.partnerId =bean.getPartnerid();
        req.prepayId =bean.getPrepayid();
        req.packageValue = bean.getPackageX();
        req.nonceStr = bean.getNonceStr();
        req.timeStamp = bean.getTimeStamp();
        req.sign = bean.getPaySign();
        boolean b = api.sendReq(req);
        Log.i("sign", b+"");

//        api.openWXApp();
    }

    public void WeiXinPayRequest(final WeiXinInfo bean ) {
        new Thread() {
            public void run() {

//                // 随机数
//                String nonceStr = GenRandom(9);
//                nonceStr=weixin_nonceStr;
                // 时间戳
                String timeStamp = System.currentTimeMillis() + "";
                timeStamp = timeStamp.substring(0, 10);

                TreeMap<String, String> treeMap = new TreeMap<String, String>();
                // 应用ID
                treeMap.put("appid",  bean.getAppId());
                // 商户号
                treeMap.put("partnerid", bean.getPartnerid());
                // 预支付交易会话ID
                treeMap.put("prepayid", bean.getPrepayid());
                // 随机字符串
                treeMap.put("noncestr", bean.getNonceStr());
                // 时间戳
                treeMap.put("timestamp", bean.getTimeStamp());
                // 扩展字段
                treeMap.put("package", "Sign=WXPay");
                //   treeMap.put("extData", "app data");

                StringBuilder sb = new StringBuilder();
                for (String key : treeMap.keySet()) {
                    sb.append(key).append("=").append(treeMap.get(key))
                            .append("&");
                }
//                sb.append("key=" + weiXin_miyao);
                System.out.println("支付-签名前字符串_" + sb.toString());

                // 签名
                String sign = bean.getPaySign();
                System.out.println("支付-签名后sign_" + sign);

                PayReq req = new PayReq();

				/*
				 * // 应用ID req.appId = weiXin_appid; // 商户号 req.partnerId =
				 * weiXin_shangHuHao; // 预支付交易会话ID req.prepayId =
				 * wenxin_Prepay_id; // 随机字符串 req.nonceStr = nonceStr; // 时间戳
				 * req.timeStamp = timeStamp; // 扩展字段 req.packageValue =
				 * "Sign=WXPay"; // 签名 req.sign = sign; //req.extData =
				 * "app data"; // optional
				 */


                    // 应用ID
                    req.appId =  bean.getAppId();;
                    // 商户号
                    req.partnerId  = bean.getPartnerid();;
                    // 预支付交易会话ID
                    req.prepayId =  bean.getPrepayid();
                    // 随机字符串
                    req.nonceStr =     bean.getNonceStr();
                    // 时间戳
                    req.timeStamp =    bean.getTimeStamp();
                    // 扩展字段
                    req.packageValue =  bean.getPackageX();
                    // 签名
                    req.sign =  bean.getPaySign();





				/*
				 * MyToast.makeText(ClearingCentreActivity.this, "正常调起支付",
				 * Toast.LENGTH_SHORT).show();
				 */
                // handlerNew.sendEmptyMessage(9);

                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                // 将该app注册到微信
                IWXAPI api = WXAPIFactory.createWXAPI(PayActivity.this, bean.getAppId());
                api.registerApp(bean.getAppId());
               boolean b= api.sendReq(req);
                Log.i("sign", b+"");
                //修改微信支付成功后退出支付中心界面
//               finish();
            }
        }.start();
    }
}
