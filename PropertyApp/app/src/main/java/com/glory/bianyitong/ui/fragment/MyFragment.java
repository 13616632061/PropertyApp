package com.glory.bianyitong.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.response.ResponseShare;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.sdk.share.ShareUtil;
import com.glory.bianyitong.ui.dialog.ShareSdkDialog;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/10.
 * 我的
 */
public class MyFragment extends BaseFragment {
    Context context;
    @BindView(R.id.tv_auth_area) //认证小区
            TextView tv_auth_area;
    @BindView(R.id.tv_award_manager) //授权管理
            TextView tv_award_manager;
    @BindView(R.id.tv_feedback) //意见反馈
            TextView tvFeedback;
    @BindView(R.id.fg_tv_my_news) //我的发布
            TextView fg_tv_my_news;
    @BindView(R.id.tv_share_app) //推荐给其他朋友
            TextView tv_share_app;
    @BindView(R.id.rl_setting) //设置
            RelativeLayout rl_setting;
    @BindView(R.id.text_user_name)
    TextView text_user_name;
    @BindView(R.id.ll_describe)
    LinearLayout ll_describe;
    @BindView(R.id.tv_user_signature) //签名
            TextView tv_user_signature;

    @BindView(R.id.tv_my_account)
    TextView txtUserInfo;
    @BindView(R.id.my_fragment_allorder)
    RelativeLayout myFragmentAllorder;
    @BindView(R.id.tv_address_manager)
    TextView tvAddressManager;
    @BindView(R.id.tv_family_manager)//家庭管理
            TextView tvFamilyManager;
    @BindView(R.id.cim_my_head_portrait)
    CircleImageView cimMyHeadPortrait;
    @BindView(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @BindView(R.id.tv_cart_number)
    TextView tvCartNumber;
    @BindView(R.id.lay_home_notice)
    RelativeLayout layHomeNotice;
    @BindView(R.id.tv_favorite_product)
    TextView tvFavoriteProduct;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_customer_service)
    TextView tvCustomerService;
    @BindView(R.id.tv_pending_payment)
    TextView tvPendingPayment;
    @BindView(R.id.tv_to_be_delivered)
    TextView tvToBeDelivered;
    @BindView(R.id.tv_to_be_received)
    TextView tvToBeReceived;
    @BindView(R.id.tv_be_evaluated)
    TextView tvBeEvaluated;
    @BindView(R.id.tv_refund_sale)
    TextView tvRefundSale;
    @BindView(R.id.lay_fg_my)
    LinearLayout layFgMy;
    private View view_my;
    private CircleImageView headPortraitCiv; //个人信息
    private String customerPhoto = "";

    private String tittle;
    private String subTittle;
    private String share_url = "";
    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: //朋友圈
                    ShareUtil.showShare(context, msg.obj.toString(), true, tittle, subTittle, share_url, Constant.logo_path);
                    break;
                case 1://微信好友
                    Log.i("resultString", "tittle---------" + tittle);
                    Log.i("resultString", "subTittle---------" + subTittle);
                    Log.i("resultString", "share_url---------" + share_url);
                    Log.i("resultString", "Constant.logo_path---------" + Constant.logo_path);
                    ShareUtil.showShare(context, msg.obj.toString(), true, tittle, subTittle, share_url, Constant.logo_path);
                    break;
            }
        }
    };
    private ShareSdkDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_my = inflater.inflate(R.layout.fg_my2, container, false);
        context = getActivity();
        ButterKnife.bind(this, view_my);
        return view_my;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headPortraitCiv = (CircleImageView) view.findViewById(R.id.cim_my_head_portrait);
        headPortraitCiv.setOnClickListener(this);
        text_user_name.setOnClickListener(this);
        ll_describe.setOnClickListener(this);

//        rl_myAddress.setOnClickListener(this);
        tv_award_manager.setOnClickListener(this);
        tv_auth_area.setOnClickListener(this);
        fg_tv_my_news.setOnClickListener(this);
        tv_share_app.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        txtUserInfo.setOnClickListener(this);
        myFragmentAllorder.setOnClickListener(this);
        tvAddressManager.setOnClickListener(this);
        tvFamilyManager.setOnClickListener(this);
        tvPendingPayment.setOnClickListener(this);
        tvToBeDelivered.setOnClickListener(this);
        tvToBeReceived.setOnClickListener(this);
        tvBeEvaluated.setOnClickListener(this);
        tvRefundSale.setOnClickListener(this);
        tvShoppingCart.setOnClickListener(this);
        tvCoupon.setOnClickListener(this);
        tvFavoriteProduct.setOnClickListener(this);
        getShareInfo();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cim_my_head_portrait: //个人信息
                Router.build(RouterMapping.ROUTER_ACTIVITY_PERSION)
                        .go(this);
                break;
            case R.id.text_user_name://个人信息
                Router.build(RouterMapping.ROUTER_ACTIVITY_PERSION)
                        .go(this);
//                if (Database.USER_MAP != null) {//登录
//                    Intent intent3 = new Intent(context, PersonalDataActivity.class);
//                    context.startActivity(intent3);
//                } else {
//                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
//                    login();
//                }
                break;
            case R.id.ll_describe://个人信息
                Router.build(RouterMapping.ROUTER_ACTIVITY_PERSION)
                        .go(this);
//                if (Database.USER_MAP != null) {//登录
//                    Intent intent4 = new Intent(context, PersonalDataActivity.class);
//                    context.startActivity(intent4);
//                } else {
//                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
//                    login();
//                }
                break;
            case R.id.tv_auth_area: //认证小区
                Router.build(RouterMapping.ROUTER_ACTIVITY_AUTHAREA)
                        .go(this);
//                if (Database.USER_MAP != null) {//登录
//                    Intent intent7 = new Intent(context, AuthAreaActivity.class);
//                    intent7.putExtra("from", "");
//                    context.startActivity(intent7);
//                } else {
//                    ToastUtils.showToast(context, getResources().getString(R.string.please_login_first)); //请先登录
//                    login();
//                }
                break;
            case R.id.tv_award_manager: //授权管理

                if (Database.USER_MAP == null)
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN)
                            .go(this);

                if (Database.my_community == null) {
                    ToastUtils.showToast(context, getString(R.string.you_have_no_district_please_first_certification_district));//您还没有小区,请先认证小区
                } else {
                    Router.build(RouterMapping.ROUTER_ACTIVITY_AAWARD_MANAGER)
                            .go(this);
                }
                break;
            case R.id.fg_tv_my_news: //我的发布 /登录
                Router.build(RouterMapping.ROUTER_ACTIVITY_AAWARD_MY_RELEASE)
                        .go(this);
                break;
            case R.id.tv_address_manager: //地址管理
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER)
                        .go(this);
                break;
            case R.id.tv_share_app: //推荐给其他朋友
                dialog = new ShareSdkDialog(context, mhandler);
                // 显示窗口
                dialog.showAtLocation(view_my.findViewById(R.id.lay_fg_my),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_feedback: //意见反馈 /登录
                Router.build(RouterMapping.ROUTER_ACTIVITY_FEEDBACK)
                        .go(this);
                break;
            case R.id.rl_setting: //设置
//                Intent intent9 = new Intent(context, SettingActivity.class);
//                context.startActivity(intent9);
                Router.build(RouterMapping.ROUTER_ACTIVITY_SETTING)
                        .go(this);
                break;
            case R.id.tv_my_account:
                Router.build(RouterMapping.ROUTER_ACTIVITY_PERSION)
                        .go(this);
                break;

            case R.id.my_fragment_allorder://我的订单
                Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER)
                        .with("type", 0)
                        .go(this);
                break;
            case R.id.tv_pending_payment://待付款
                Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER)
                        .with("type", 1)
                        .go(this);
                break;
            case R.id.tv_to_be_delivered://待发货
                Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER)
                        .with("type", 2)
                        .go(this);
                break;
            case R.id.tv_to_be_received://待收货
                Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER)
                        .with("type", 3)
                        .go(this);
                break;
            case R.id.tv_be_evaluated://待评价
                Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER)
                        .with("type", 4)
                        .go(this);
                break;
            case R.id.tv_family_manager://家庭管理
                Router.build(RouterMapping.ROUTER_ACTIVITY_FAMILYMANAGEMENT)
                        .go(this);
                break;
            case R.id.tv_shopping_cart://购物车
                Router.build(RouterMapping.ROUTER_ACTIVITY_SHOPPINGCART)
                        .go(this);
                break;
            case R.id.tv_coupon://优惠券券
                Router.build(RouterMapping.ROUTER_ACTIVITY_COUPON_LIST)
                        .go(this);
                break;
            case R.id.tv_favorite_product://收藏
                Router.build(RouterMapping.ROUTER_ACTIVITY_COLLECTION_LIST)
                        .go(this);
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Database.USER_MAP != null && Database.accessToken != null) { //登录
            //用户名
            if (Database.accessToken != null && Database.USER_MAP != null) {
                String name1 = text_user_name.getText().toString();
                String name2 = Database.USER_MAP.getLoginName();
                if (!name1.equals(name2)) {
                    text_user_name.setText(name2);
                }
            } else {
                text_user_name.setText("");
            }
            //头像
            if (Database.accessToken != null && Database.USER_MAP != null) {
                String pic = Database.USER_MAP.getCustomerPhoto();
//                if (!customerPhoto.equals(pic)) {
//                    ServiceDialog.setPicture(pic, headPortraitCiv, null);
//                    customerPhoto = pic;
//                }
            } else {
                headPortraitCiv.setImageResource(R.drawable.head);
            }
            //签名
            if (Database.USER_MAP != null && Database.USER_MAP.getSignature() != null) {
                String signature = tv_user_signature.getText().toString();
//                String signature2 = Database.USER_MAP.getSignature();
                String signature2 = Database.USER_MAP.getSignature().toString();
                if (!signature.equals(signature2)) {
                    tv_user_signature.setText(signature2);
                }
            } else {
                tv_user_signature.setText("");
            }
        } else { //未登录
            text_user_name.setText(getResources().getString(R.string.not_logged_in));//未登录
            tv_user_signature.setText("");
            headPortraitCiv.setImageResource(R.drawable.head);
        }
    }

    private void getShareInfo() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if (TextUtil.isEmpty(s)) {
                    showShort("系统异常");
                    return;
                }
                ResponseShare share = new Gson().fromJson(s, ResponseShare.class);
                if (share.getStatusCode() == 1) {
                    if (share.getListSetting() != null && share.getListSetting().size() > 0) {
                        share_url = share.getListSetting().get(0).getSettingValue();
                        tittle = share.getListSetting().get(1).getSettingValue();
                        subTittle = share.getListSetting().get(2).getSettingRemark();
                    } else {
                        showShort(share.getAlertMessage());
                    }
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
        }).getEntityData(getActivity(), HttpURL.HTTP_POST_MY_GETSHARE, json);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
