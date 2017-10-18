package com.glory.bianyitong.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.RouteCallback;
import com.chenenyu.router.RouteResult;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.github.lazylibrary.util.ToastUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.GodownDetailInfo;
import com.glory.bianyitong.bean.OrderNumberInfo;
import com.glory.bianyitong.bean.entity.request.RequestCollectionAdd;
import com.glory.bianyitong.bean.entity.request.RequestProductDetail;
import com.glory.bianyitong.bean.entity.request.RequestShoppingCartAdd;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.CommentPicAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.ActivityManager;
import com.glory.bianyitong.util.NetworkImageHolderView;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.widght.CircleImageView;
import com.glory.bianyitong.widght.convenientbanner.ConvenientBanner;
import com.glory.bianyitong.widght.convenientbanner.holder.CBViewHolderCreator;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lucy on 2016/11/22.
 * 商品详情
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_PRODUCT_DETAIL)
public class GoodsDetailsActivity extends BaseActivity implements RouteCallback {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    ConvenientBanner convenientBanner2;
    @BindView(R.id.lay_goods_pic)
    LinearLayout lay_goods_pic;
    @BindView(R.id.tv_goods_name)
    TextView tv_goods_name;//商品名称
    @BindView(R.id.tv_goods_price)
    TextView tv_goods_price;//商品价格
    @BindView(R.id.tv_quick_deliver_goods)
    TextView tv_quick_deliver_goods;// 快速发货
    @BindView(R.id.tv_goods_weight)
    TextView tv_goods_weight; //净含量
    @BindView(R.id.goods_packaging)
    TextView goods_packaging; //包装方式
    @BindView(R.id.tv_goods_brand)
    TextView tv_goods_brand; //品牌
    @BindView(R.id.tv_producing_area)
    TextView tv_producing_area; //产地
    @BindView(R.id.tv_vender_phone)
    TextView tv_vender_phone; //厂家联系方式
    @BindView(R.id.tv_expiration_date)
    TextView tv_expiration_date; //保质期
    @BindView(R.id.tv_nutritiveValue)
    TextView tv_nutritiveValue; //营养价值
    @BindView(R.id.webview_ervery)
    WebView webview_ervery;
    @BindView(R.id.my_progress)
    ProgressBar my_progress;
    @BindView(R.id.detail_kefu)
    LinearLayout detailKefu;
    @BindView(R.id.detail_shoucang_image)
    ImageView detailShoucangImage;
    @BindView(R.id.detail_shoucang)
    LinearLayout detailShoucang;
    @BindView(R.id.detail_addshopping_cart)
    Button detailAddshoppingCart;
    @BindView(R.id.detail_addshopping_payproduct)
    Button detailAddshoppingPayproduct;
    @BindView(R.id.iv_title_text_left)
    TextView ivTitleTextLeft;
    @BindView(R.id.total_evaluation)
    TextView totalEvaluation;
    @BindView(R.id.tv_percentage)
    TextView tvPercentage;
    @BindView(R.id.iv_head_pic)//用户头像
            CircleImageView ivHeadPic;
    @BindView(R.id.iv_name)//用户名称
            TextView ivName;
    @BindView(R.id.ratingba)//评星
            RatingBar ratingba;
    @BindView(R.id.tv_plnr)//评论内容
            TextView tvPlnr;
    @BindView(R.id.tv_look_all)//查看所有评论
            Button tvLookAll;
    @BindView(R.id.rec_pic)//图片列表
            RecyclerView rec_pic;
    @BindView(R.id.pingjia)
    LinearLayout pingjia;
    @BindView(R.id.fresh_content)
    TextView freshContent;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_text_left2)
    TextView ivTitleTextLeft2;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_cart_number)
    TextView tvCartNumber;
    @BindView(R.id.title_ac_text)
    TextView titleAcText;

    private String[] images;
    private List<String> imageList = new ArrayList<String>();
    private ResponseQueryProductDetail.ListfreshBean.FreshEvaluationBean freshEvaluation;//好评中评差评数量bean
    private int godownNumber;
    private ResponseQueryAddress.ListShippingAddressBean addressBeabean = null;


    @InjectParam(key = "data")
    ResponseSearchFresh.ListfreshBean product;

    private ResponseQueryProductDetail.ListfreshBean productDetail = null;
    private boolean enable;//是否下架

    @Override
    protected int getContentId() {
        return R.layout.ac_goods_details;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
//        inintTitle(getString(R.string.product_details), true, "");//商品详情
        ActivityManager.addActivity(this,"goodsdetailsactivity");
        titleAcText.setText(getString(R.string.product_details));
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                GoodsDetailsActivity.this.finish();
            }
        });
        View headview = LayoutInflater.from(GoodsDetailsActivity.this).inflate(R.layout.index_headview_goods_details, null);
        convenientBanner2 = (ConvenientBanner) headview.findViewById(R.id.convenientBanner2);
        lay_goods_pic.addView(headview);
        initview();

    }

    private void initview() {
//        tv_goods_name.setText(product.getFreshName()); //商品名称
//        tv_goods_price.setText("¥ " + product.getFreshPrice());//商品价格
//        tv_quick_deliver_goods.setText(product.getFreshTypeName());
//        tv_goods_weight.setText(product.getWeight() + "g");//净含量
        getShowNumber();
        requestProductDetail(product.getFreshID());
    }

    @OnClick({R.id.detail_kefu, R.id.detail_shoucang, R.id.detail_addshopping_cart, R.id.detail_addshopping_payproduct, R.id.tv_look_all, R.id.iv_title_right})
    void onClickBtn(View view) {
        if (Database.USER_MAP==null) {
            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(this);
        } else {

            switch (view.getId()) {
                case R.id.iv_title_right:
                    Router.build(RouterMapping.ROUTER_ACTIVITY_SHOPPINGCART)
                            .go(this);
                    break;
                case R.id.detail_kefu://客服
                    break;
                case R.id.detail_shoucang://收藏
                    if (!(Database.USER_MAP == null || Database.USER_MAP.getUserID() == null)) {
                        if (!(productDetail == null || productDetail.getFreshID() <= 0)) {
                            if (productDetail.isCollectionStatu()) {
                                deleteCollection();
                            } else {
                                addCollection();
                            }
                        } else {
                            showShort("数据异常,请返回重试");
                        }
                    } else {
                        Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN)
                                .go(this, this);
                    }
                    break;
                case R.id.detail_addshopping_cart://加入购物车
                    if (!(Database.USER_MAP == null || Database.USER_MAP.getUserID() == null)) {
                        if (!(productDetail == null || productDetail.getFreshID() <= 0)) {
                            addShoppingCart();
                            getShowNumber();
                        } else {
                            showShort("数据异常,请返回重试");
                        }
                    } else {
                        Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN)
                                .go(this, this);
                    }
                    break;
                case R.id.detail_addshopping_payproduct://立即购买
                    queryAddress();
                    break;
                case R.id.tv_look_all://查看全部评论
                    if (product != null) {
                        Router.build(RouterMapping.ROUTER_ACTIVITY_ALLORDER_COMMENT)
                                .with("freshEvaluation", freshEvaluation)
                                .with("freshId", productDetail.getFreshID())
                                .go(this);
                    }

                    break;
            }
        }
    }

    /**
     * 立即购买时查询商品剩余
     */
    private void godownFind() {//库存大于0时才可以购买
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        List<GodownDetailInfo.ListDetailBean> list = new ArrayList<>();
        list.add(new GodownDetailInfo.ListDetailBean(productDetail.getFreshID(), 1));
        map.put("listDetail", list);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryProductDetail detail = new Gson().fromJson(s, ResponseQueryProductDetail.class);
                if (detail.getStatusCode() == 1) {
                    if (product != null) {
                        String addressBean = new Gson().toJson(addressBeabean);
                        Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_FIRM)
                                .with("shop", new Gson().toJson(productDetail))
                                .with("addressBean", addressBean)
                                .with("type", 1)
                                .go(GoodsDetailsActivity.this);
                    }
                } else {
                    showShort(detail.getAlertMessage());

                }

            }


            @Override
            public void onError() {
                showShort("系统异常");
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOP_QUERY_GODOWNDETAIL, json);
        }catch (Exception e){

        }
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_goods_pic, lay_gallery, false);
                final ImageView iv_goods_pic = (ImageView) view.findViewById(R.id.iv_goods_pic);

                if (list != null && list.get(i).get("picturePath") != null && list.get(i).get("picturePath").toString().length() != 0 && !list.get(i).get("picturePath").toString().equals("")) {
                    String data = list.get(i).get("picturePath").toString();
//                    ServiceDialog.setPicture(list.get(i).get("picturePath").toString(), iv_goods_pic, null);
                    Glide.with(context).load(data).error(R.drawable.wait).placeholder(R.drawable.wait).into(iv_goods_pic);
                }

                lay_gallery.addView(view);
            }
        }
    }

    /**
     * 商品详情图片
     */
    private void getBanner() {
        Collections.addAll(imageList, images);
        convenientBanner2.startTurning(4000);
        convenientBanner2.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, imageList)
                //显示小圆圈
                .setPageIndicator(new int[]{R.drawable.point_unfocused, R.drawable.point_focused})
                //小圆圈的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)

                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //每一张图片的点击事件

                    }
                });
    }

    /**
     * 收藏改变
     *
     * @param status
     */
    private void collectionChange(boolean status) {
        if (status) {
            detailShoucangImage.setImageResource(R.mipmap.shoucang);
        } else {
            detailShoucangImage.setImageResource(R.mipmap.icon_collection_normal);
        }
    }

    //获取订单数量 购物车数量
    private void getShowNumber() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                if (TextUtil.isEmpty(s)) {
                    showShort("系统异常");
                    return;
                }
                OrderNumberInfo share = new Gson().fromJson(s, OrderNumberInfo.class);
                if (share.getStatusCode() == 1) {
                    tvCartNumber.setText(share.getOrder().getCartNum() + "");
                    if (share.getOrder().getCartNum() == 0) {
                        tvCartNumber.setVisibility(View.GONE);
                    }else {
                        tvCartNumber.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvCartNumber.setVisibility(View.GONE);
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
        }).getEntityData(this, HttpURL.HTTP_POST_ORDER_OTHERONE, json);
        }catch (Exception e){

        }
    }

    /**
     * 查询产品详情
     *
     * @param freshId
     */
    private void requestProductDetail(int freshId) {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("fresh", new RequestProductDetail(freshId));
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    ResponseQueryProductDetail detail = new Gson().fromJson(s, ResponseQueryProductDetail.class);
                    if (detail.getStatusCode() == 1) {
                        if (detail.getListfresh() == null || detail.getListfresh().size() <= 0) {
                            showShort(detail.getAlertMessage());
                        } else {
                            productDetail = detail.getListfresh().get(0);
                            goods_packaging.setText(detail.getListfresh().get(0).getPackingType());//包装方式
                            tv_goods_brand.setText(detail.getListfresh().get(0).getMerchantName());//品牌
                            tv_producing_area.setText(detail.getListfresh().get(0).getOriginName());//产地
                            tv_vender_phone.setText(detail.getListfresh().get(0).getMerchantTel());//厂家联系方式
                            tv_expiration_date.setText(detail.getListfresh().get(0).getShelfLife());//保质期
                            freshContent.setText(detail.getListfresh().get(0).getFreshContent());
                            tv_goods_name.setText(detail.getListfresh().get(0).getFreshName()); //商品名称
                            tv_goods_price.setText("¥ " + detail.getListfresh().get(0).getFreshPrice());//商品价格
                            tv_quick_deliver_goods.setText(detail.getListfresh().get(0).getFreshTypeName());
                            tv_goods_weight.setText(detail.getListfresh().get(0).getWeight());//净含量

//                        load(Database.goodsdetails.getFreshUrl());
                            tv_nutritiveValue.setText(detail.getListfresh().get(0).getNutritiveValue() + ""); //营养价值
                            collectionChange(productDetail.isCollectionStatu());
                            //评论内容
                            if (productDetail.getList_FreshEvaluation() != null) {
                                if (productDetail.getList_FreshEvaluation().size() > 0) {
                                    tvPlnr.setText(productDetail.getList_FreshEvaluation().get(0).getEvaluationContext());
                                    ServiceDialog.setPicture(productDetail.getList_FreshEvaluation().get(0).getUser().getCustomerPhoto(), ivHeadPic, null);
                                    ratingba.setRating(productDetail.getList_FreshEvaluation().get(0).getEvaluationLevel());
                                    if (productDetail.getList_FreshEvaluation().get(0).getAnonymous() == null) {
                                        ivName.setText(productDetail.getList_FreshEvaluation().get(0).getUser().getLoginName());
                                    } else {
                                        ivName.setText(productDetail.getList_FreshEvaluation().get(0).getAnonymous());
                                    }
                                }
                            }

                            if (productDetail.getFreshEvaluation().getTotalEvaluation() > 0) {
                                totalEvaluation.setText("宝贝评价数量(" + productDetail.getFreshEvaluation().getTotalEvaluation() + ")");
                                // 创建一个数值格式化对象
                                NumberFormat numberFormat = NumberFormat.getInstance();

                                // 设置精确到小数点后2位
                                numberFormat.setMaximumFractionDigits(0);

                                String result = numberFormat.format((float) productDetail.getFreshEvaluation().getPraiseNum() / (float) productDetail.getFreshEvaluation().getTotalEvaluation() * 100);
                                godownNumber = productDetail.getGodownNumber();

                                enable = productDetail.isEnable();

                                tvPercentage.setText(result + "%");
                                List<String> imageUrlList = new ArrayList<String>();

                                //获取评论图片
                                for (int i = 0; i < productDetail.getList_FreshEvaluation().get(0).getListEvaluationPic().size(); i++) {
                                    imageUrlList.add(productDetail.getList_FreshEvaluation().get(0).getListEvaluationPic().get(i).getPicturePath());
                                }
                                //好评中评差评数量bean
                                freshEvaluation = productDetail.getFreshEvaluation();

                                //评论图片
                                CommentPicAdapter commentPicAdapter = new CommentPicAdapter(R.layout.item_commentpic, getApplicationContext());
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                                rec_pic.setLayoutManager(gridLayoutManager);
                                rec_pic.setAdapter(commentPicAdapter);
                                commentPicAdapter.addData(imageUrlList);
                                commentPicAdapter.notifyDataSetChanged();
                            } else {
                                pingjia.setVisibility(View.GONE);
                            }


                            List<ResponseQueryProductDetail.ListfreshBean.ListfreshPictureBean> picture_list = detail.getListfresh().get(0).getListfreshPicture();
                            if (picture_list != null && picture_list.size() != 0) {
                                String pic_str = "";
                                for (int i = 0; i < picture_list.size(); i++) {
                                    if (picture_list.get(i) != null && picture_list.get(i).getPicturePath() != null) {
                                        pic_str = pic_str + picture_list.get(i).getPicturePath() + ","; //生鲜图片  ,号隔开
                                    }
                                }
                                if (pic_str.split(",") != null && pic_str.split(",").length > 0) {
                                    images = pic_str.split(",");
                                } else {
                                    images = new String[]{pic_str};
                                }
                                getBanner(); //开始广告轮播
//                    ScrollViewLayout(GoodsDetailsActivity.this, picture_list, lay_goodsImage_list);
                            }

                        }
                        if (detail.getListfresh().get(0).getFreshContents() != null) {
                            load(detail.getListfresh().get(0).getFreshContents().toString());
                        }

                    } else {
                        showShort(detail.getAlertMessage());
                    }

                }


                @Override
                public void onError() {
                    showShort("系统异常");
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
            }).getEntityData(this, HttpURL.HTTP_POST_FRESH_QUERY_DETAIL, json);

        } catch (Exception e) {

        }

    }


    /**
     * 添加购物车
     */
    private void addShoppingCart() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shoppingCart", new RequestShoppingCartAdd(productDetail.getFreshID(), productDetail.getFreshTypeID(), 1, productDetail.getFreshPrice()));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                ToastUtils.showToast(GoodsDetailsActivity.this, bean.getAlertMessage());
//                showShort(bean.getAlertMessage());

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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_ADD, json);
        }catch (Exception e){

        }
    }

    /**
     * 添加收藏
     */
    private void addCollection() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        List<RequestCollectionAdd> list = new ArrayList<>();
        list.add(new RequestCollectionAdd(productDetail.getFreshID(), productDetail.getFreshTypeID()));
        map.put("listCollection", list);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
                    productDetail.setCollectionStatu(true);
                    collectionChange(true);
                }
                showShort(bean.getAlertMessage());
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
        }).getEntityData(this, HttpURL.HTTP_POST_COLLECTION_ADD, json);
        }catch (Exception e){

        }
    }

    /**
     * //默认收货地址
     */
    private void queryAddress() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shippingAddress", new Object());
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {


            @Override
            public void onSuccess(String s) {
                ResponseQueryAddress queryAddress = new Gson().fromJson(s, ResponseQueryAddress.class);
                if (queryAddress.getStatusCode() == 1) {
                    if (queryAddress.getListShippingAddress() != null && queryAddress.getListShippingAddress().size() > 0) {
                        for (ResponseQueryAddress.ListShippingAddressBean bean : queryAddress.getListShippingAddress()) {
                            if (bean.isDefaults()) {
                                addressBeabean = bean;
                            }
                        }
                        if (addressBeabean == null) {
                            showShort("请添加默认收货地址");
                        } else {
                            godownFind();//
                        }
                    }
                } else if (queryAddress.getStatusCode() == 2) {
                    showShort("请添加默认收货地址");
                    finish();
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
        }).getEntityData(this, HttpURL.HTTP_POST_QUERY_ADDRESS, json);
        }catch (Exception e){

        }
    }

    /**
     * 取消收藏
     */
    private void deleteCollection() {
        try {


        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        List<Integer> freshId = new ArrayList<>();
        freshId.add(productDetail.getFreshID());
        map.put("listFreshID", freshId);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
                    productDetail.setCollectionStatu(false);
                    collectionChange(false);

                }
                showShort(bean.getAlertMessage());
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
        }).getEntityData(this, HttpURL.HTTP_POST_COLLECTION_DELETE, json);
        }catch (Exception e){

        }
    }

    private void load(String html) {
        //加载需要显示的网页
        webview_ervery.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
        //设置WebView属性，能够执行Javascript脚本
        webview_ervery.getSettings().setPluginState(WebSettings.PluginState.ON); //支持插件
        WebSettings webSettings = webview_ervery.getSettings();  //android 5.0以上
//        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        //Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows(); //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        // 在Android中点击一个链接，默认是调用应用程序来启动，因此WebView需要代为处理这个动作 通过WebViewClient
        // 设置WebViewClient
        webview_ervery.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) { //https
                handler.proceed(); //接受证书
            }
        });

        webview_ervery.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    my_progress.setVisibility(View.GONE);
                } else {
                    if (View.GONE == my_progress.getVisibility()) {
                        my_progress.setVisibility(View.VISIBLE);
                    }
                    my_progress.setProgress(newProgress);
                    int x = (int) (newProgress / 2);
                    my_progress.setSecondaryProgress(newProgress + x);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.goodsdetails = null;
    }

    @Override
    public void callback(RouteResult state, Uri uri, String message) {
        if (!(Database.USER_MAP == null || Database.USER_MAP.getUserID() == null)) {
            requestProductDetail(product.getFreshID());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
