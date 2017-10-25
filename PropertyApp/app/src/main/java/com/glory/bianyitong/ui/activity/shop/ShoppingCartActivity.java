package com.glory.bianyitong.ui.activity.shop;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.GetCouponInfo;
import com.glory.bianyitong.bean.GodownDetailInfo;
import com.glory.bianyitong.bean.ShopcartInfo;
import com.glory.bianyitong.bean.entity.CouponEntry;
import com.glory.bianyitong.bean.entity.request.RequestCollectionAdd;
import com.glory.bianyitong.bean.entity.request.RequestShoppingUpDate;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryProductDetail;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.ShopCouponListAdapter;
import com.glory.bianyitong.ui.adapter.shop.ShoppingCardAdapter;
import com.glory.bianyitong.widght.shop.AmountView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 购物车
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_SHOPPINGCART, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, CompoundButton.OnCheckedChangeListener, AmountView.OnAmountChangeListener, BaseQuickAdapter.OnItemClickListener {


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
    @BindView(R.id.rec_shoppingcart)
    RecyclerView recShoppingcart;
    @BindView(R.id.iv_buttons)
    CheckBox ivButton;
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.shopping_cart_txt_yunfei)
    TextView shoppingCartTxtYunfei;
    @BindView(R.id.shopping_cart_collection)
    Button shoppingCartCollection;
    @BindView(R.id.shopping_cart_pay)
    Button shoppingCartPay;
    @BindView(R.id.firm_order_address_lin)
    LinearLayout firmOrderAddressLin;

    private ShoppingCardAdapter adapter;
    private boolean isEditStatus = true;//编辑   true编辑  false完成
    private List<ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> data = new ArrayList<>();
    private Map<Integer, ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> commitData = new HashMap<>();
    private ResponseQueryAddress.ListShippingAddressBean addressBeabean = null;//默认收货地址实体类
    private final int REQUEST_CODE_ADDRESS = 100;//选择地址
    private View addressInitView;
    private PopupWindow popupWindowSort;
    private RecyclerView popFrList;
    private List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListCouponBean>> poplist= new ArrayList<>();
    private ResponseShoppingCart shoppingCartbean;


    @Override
    protected int getContentId() {
        return R.layout.activity_shoppingcart;
    }


    @Override
    protected void init() {
        super.init();
        initView();
    }

    private void initView() {
        inintTitle("购物车", false, "编辑");
        adapter = new ShoppingCardAdapter(R.layout.item_shoppingcart, R.layout.item_title_shoppingcart, data, this, this, commitData, this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recShoppingcart.setAdapter(adapter);
        recShoppingcart.setLayoutManager(linearLayout);
        adapter.setOnItemChildClickListener(this);
        adapter.bindToRecyclerView(recShoppingcart);
        adapter.setOnItemClickListener(this);
        shoppingCartPay.setText("结算(" + commitData.size() + ")");
        initPopupWindowSort(0);
        queryAddress();

    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right, R.id.shopping_cart_pay, R.id.shopping_cart_collection, R.id.firm_order_address_lin})
    void OnclickViews(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.shopping_cart_pay://支付或删除
                if (isEditStatus) {//删除
//                    showShort("结算");
//                    if(commitData.size()>0){
//                        String json=new Gson().toJson(commitData.values());
//                        Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_FIRM)
//                                .with("shops",json)
//                                .with("type",2)
//                                .go(ShoppingCartActivity.this);
//                        Log.i("json",json);
//
//                    }
                    godownFind();
                } else {
                    removeShoppingCard(0);
                }

                break;
            case R.id.iv_title_text_right://编辑
                if (isEditStatus) {//完成状态

                    ivTitleTextRight.setText("完成");
                    shoppingCartCollection.setVisibility(View.VISIBLE);
                    shoppingCartTxtYunfei.setVisibility(View.GONE);

                } else {//编辑状态

                    ivTitleTextRight.setText("编辑");
                    shoppingCartCollection.setVisibility(View.GONE);
                    shoppingCartTxtYunfei.setVisibility(View.VISIBLE);

                }

                isEditStatus = isEditStatus ? false : true;
                updateShoppingCardPrice();
                break;

            case R.id.shopping_cart_collection://收藏
                if (!isEditStatus) {//删除
                    addCollection();
                }
                break;

            case R.id.firm_order_address_lin://选择地址
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER)
                        .with("source", true)
                        .requestCode(REQUEST_CODE_ADDRESS)
                        .go(this);
                break;
        }
    }

    /**
     * 立即购买时查询商品剩余
     */
    private void godownFind() {//库存大于0时才可以购买
        try {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        final String shops = new Gson().toJson(commitData.values());
        List<GodownDetailInfo.ListDetailBean> list = new ArrayList<>();
        List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>> datas = new Gson().fromJson(shops, new TypeToken<List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>>>() {
        }.getType());
        for (int i = 0; i < datas.size(); i++) {
            if (!datas.get(i).isHeader)
                list.add(new GodownDetailInfo.ListDetailBean(datas.get(i).getData().getFreshID(), datas.get(i).getData().getQuantity()));
        }
        map.put("listDetail", list);
            if (list.size()<=0){
                showShort("请选择商品");
                return;
            }
        final String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryProductDetail detail = new Gson().fromJson(s, ResponseQueryProductDetail.class);
                if (detail.getStatusCode() == 1) {
                    if (commitData.size() > 0) {
                        String addressBean = new Gson().toJson(addressBeabean);

                        Router.build(RouterMapping.ROUTER_ACTIVITY_ORDER_FIRM)
                                .with("shops", shops)
                                .with("addressBean", addressBean)
                                .with("type", 2)
                                .go(ShoppingCartActivity.this);
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

    private void queryAddress() {//默认收货地址
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
                            finish();
                        } else {
                            addressInitView = LayoutInflater.from(ShoppingCartActivity.this).inflate(R.layout.item_firm_order_address_init, null);
                            firmOrderAddressLin.removeAllViews();
                            firmOrderAddressLin.addView(addressInitView);

                            TextView txtName = ButterKnife.findById(addressInitView, R.id.firm_order_item_name);
                            TextView txtNumber = ButterKnife.findById(addressInitView, R.id.firm_order_item_number);
                            TextView txtAddress = ButterKnife.findById(addressInitView, R.id.address_list_address);
                            TextView name_and_phone = ButterKnife.findById(addressInitView, R.id.name_and_phone);
                            name_and_phone.setText(addressBeabean.getHarvesterName()+"  "+addressBeabean.getHarvestePhone());
                            txtName.setText(addressBeabean.getFreshCabinet().getCommunityName() + addressBeabean.getFreshCabinet().getCabinetName());
                            txtAddress.setText(addressBeabean.getFreshCabinet().getCommunity().getProvinceName() + addressBeabean.getFreshCabinet().getCommunity().getCityName() + addressBeabean.getFreshCabinet().getCommunity().getDistrictName() + addressBeabean.getFreshCabinet().getCommunity().getStreet());
                            SpannableString spannable = new SpannableString(addressBeabean.getFreshCabinet().getUsed() + "/" + addressBeabean.getFreshCabinet().getNum());
                            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            txtNumber.setText(spannable);
                            queryShoppingCart();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADDRESS) {
                ResponseQueryAddress.ListShippingAddressBean addressBeabean = (ResponseQueryAddress.ListShippingAddressBean) data.getSerializableExtra("data");
                if (addressBeabean != null) {
                    this.addressBeabean = addressBeabean;
                    firmOrderAddressLin.removeAllViews();
                    firmOrderAddressLin.addView(addressInitView);
                    TextView txtName = ButterKnife.findById(addressInitView, R.id.firm_order_item_name);
                    TextView txtNumber = ButterKnife.findById(addressInitView, R.id.firm_order_item_number);
                    TextView txtAddress = ButterKnife.findById(addressInitView, R.id.address_list_address);
                    TextView name_and_phone = ButterKnife.findById(addressInitView, R.id.name_and_phone);
                    name_and_phone.setText(addressBeabean.getHarvesterName()+"  "+addressBeabean.getHarvestePhone());
                    txtName.setText(this.addressBeabean.getFreshCabinet().getCommunityName() + this.addressBeabean.getFreshCabinet().getCabinetName());
                    txtAddress.setText(this.addressBeabean.getFreshCabinet().getCommunity().getProvinceName() + this.addressBeabean.getFreshCabinet().getCommunity().getCityName() + this.addressBeabean.getFreshCabinet().getCommunity().getDistrictName() + this.addressBeabean.getFreshCabinet().getCommunity().getStreet());

                    SpannableString spannable = new SpannableString(addressBeabean.getFreshCabinet().getUsed() + "/" + addressBeabean.getFreshCabinet().getNum());
                    spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#eb0002")), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtNumber.setText(spannable);

                }
            }
        }
    }

    @OnCheckedChanged(R.id.iv_buttons)
    void onChangesBtn(CompoundButton view, boolean isCheck) {
        if (isCheck) {
            commitData.clear();
            for (int i = 0; i < data.size(); i++) {
                if (!data.get(i).isHeader){
                    if (data.get(i).getData().isOK()){
                        commitData.put(i, data.get(i));
                    }
                }

            }
        } else {
            if (commitData.size() == data.size()) {
                commitData.clear();
            }

        }
        adapter.notifyDataSetChanged();
    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        ivButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    commitData.clear();
//                    for (int i = 0; i < data.size(); i++) {
//                        if (!data.get(i).isHeader){
//                            if (data.get(i).getData().isOK()){
//                                commitData.put(i, data.get(i));
//                            }
//                        }
//
//                    }
//                } else {
//                    if (commitData.size() == data.size()) {
//                        commitData.clear();
//                    }
//
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }

    /**
     * 查询购物车
     */
    private void queryShoppingCart() {
        try {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("cabinetID", addressBeabean.getCabinetID());
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {


            @Override
            public void onSuccess(String s) {
                shoppingCartbean =     new Gson().fromJson(s, ResponseShoppingCart.class);
                if (shoppingCartbean.getStatusCode() == 1) {
                    List<ResponseShoppingCart.ListShoppingCartBean> list = shoppingCartbean.getListShoppingCart();
                    if (!(list == null || list.size() <= 0)) {
                        data.clear();

//                        for (ResponseShoppingCart.ListShoppingCartBean entity : list
//                                ) {
//                            data.add(new ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean>(entity));
//                        }
                        for (int i = 0; i < list.size(); i++) {
                            data.add(new ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>(true, list.get(i).getMerchantName(), list.get(i).isIsHave()));
                            for (int j = 0; j < list.get(i).getListShopping().size(); j++) {
                                data.add((new ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean>(list.get(i).getListShopping().get(j))));
                            }
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_QUERY, json);
        }catch (Exception e){

        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_shop_delete://删除
                Log.i("ppositionposition", position + "");
                removeShoppingCard(position);
                break;
            case R.id.tv_coupon:
                initPopupWindowSort(position);
                break;
            case R.id.item_fr_couponlist_btn:
                getCoupon(position);
                break;
            case R.id.iv_list_item_goods_pic:
            case R.id.all_money_and_other:
                ResponseSearchFresh.ListfreshBean product=new ResponseSearchFresh.ListfreshBean();
                product.setFreshID(data.get(position).getData().getFreshID());
                Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_DETAIL)
                        .with("data", product)
                        .go(this);
                break;
        }

    }
    //领取优惠券
    public void getCoupon(int position) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        GetCouponInfo getCouponInfo=new GetCouponInfo();
        getCouponInfo.getCouponReceive().setCouponID(poplist.get(position).getData().getCouponID());
        map.put("entityCouponReceive", getCouponInfo);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                CouponEntry couponEntry =new Gson().fromJson(s, CouponEntry.class);
                if (couponEntry.getStatusCode() == 1) {
                    showShort("领取成功");
                } else {
                    showShort(couponEntry.getAlertMessage());
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
        }).getEntityData(this, HttpURL.HTTP_POST_COUPON_QUERY_ADD, json);

    }

    //优惠券排序弹出窗口
    private void initPopupWindowSort(int position) {
        if (popupWindowSort == null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            final View pView = inflater.inflate(R.layout.pop_shoppingcart, null);
            popupWindowSort = new PopupWindow(pView, this.getWindowManager().getDefaultDisplay().getWidth() - firmOrderAddressLin.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
//            //获取控件宽度
//            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//            rlAddress.measure(w, h);
//            int width =rlAddress.getMeasuredWidth();
            //设置popwindow显示宽度
//            popupWindowSort.setWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth()-width);
//            popupWindowSort.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindowSort.setBackgroundDrawable(new BitmapDrawable());
            popupWindowSort.setFocusable(true);
            popupWindowSort.setOutsideTouchable(true);
            popFrList = (RecyclerView) pView.findViewById(R.id.popFrList);


            //点击弹窗下方关闭
            View pop_dismis = pView.findViewById(R.id.pop_dismis);
            pop_dismis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindowSort.dismiss();
                }
            });

        } else {
            poplist.clear();
            for (ResponseShoppingCart.ListShoppingCartBean.ListCouponBean enty : shoppingCartbean.getListShoppingCart().get(position).getListCoupon()) {
                poplist.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean.ListCouponBean>(enty));
            }
            Log.i("listsizi", poplist.size()+"");
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            ShopCouponListAdapter listAdapter = new ShopCouponListAdapter(R.layout.item_fr_couponlist, poplist);
            popFrList.setLayoutManager(layoutManager);
            popFrList.setAdapter(listAdapter);
            listAdapter.bindToRecyclerView(popFrList);
            listAdapter.setOnItemChildClickListener(this);
            listAdapter.notifyDataSetChanged();
            if (!popupWindowSort.isShowing()) {
//                popupWindowSort.showAtLocation(rlZonghe, Gravity.LEFT|Gravity.RIGHT,0,0);
                popupWindowSort.showAsDropDown(firmOrderAddressLin);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag(R.id.shopCard_check);
        if (position < 0)
            return;
        ShopcartInfo<ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean> bean = data.get(position);
        if (!(bean == null || bean.getData() == null)) {

            if (isChecked) {
                commitData.put(position, bean);
                if (commitData.size() == data.size()) {
                    ivButton.setChecked(isChecked);
                }
            } else {
                commitData.remove(position);
                ivButton.setChecked(isChecked);
            }
            updateShoppingCardPrice();
        }


    }

    private void updateShoppingCardPrice() {
        float allPrice = 0;
        for (Integer data : commitData.keySet()) {
            ResponseShoppingCart.ListShoppingCartBean.ListShoppingBean bean = commitData.get(data).getData();
            if (!commitData.get(data).isHeader && bean.getFresh().isEnable() && !bean.getFresh().isIsDelete() && bean.getFresh().getGodownNumber() > 0) {
                allPrice += bean.getPrice() * bean.getQuantity();

            }

        }
        BigDecimal b   =   new   BigDecimal(allPrice);
        allPrice  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        allMoney.setText("￥" + allPrice);
        if (isEditStatus) {
            shoppingCartPay.setText("结算(" + commitData.size() + ")");
        } else {
            shoppingCartPay.setText("删除");
        }
    }

    /**
     * 更新购物车
     *
     * @param position
     * @param num
     */
    private void upDateShoppingCart(final int position, final int num) {
        try {


        int cartId = data.get(position).getData().getCartID();
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("shoppingCart", new RequestShoppingUpDate(cartId, num));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
                    data.get(position).getData().setQuantity(num);
                    if (commitData.containsKey(position)) {
                        commitData.get(position).getData().setQuantity(num);
                        updateShoppingCardPrice();
                    }
                    adapter.notifyDataSetChanged();
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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_EDIT, json);
        }catch (Exception e){

        }
    }
    /**
     * 添加收藏
     */
    private void addCollection() {
        try{
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            List<RequestCollectionAdd> list = new ArrayList<>();
            for (Integer i : commitData.keySet()) {
                if (!commitData.get(i).isHeader){
                    list.add(new RequestCollectionAdd(commitData.get(i).getData().getFreshID(), commitData.get(i).getData().getFresh().getFreshTypeID()));
                }
            }
            map.put("listCollection", list);
            String json = new Gson().toJson(map);
                OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                    @Override
                    public void onSuccess(String s) {
                        BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                        if (bean.getStatusCode() == 1) {

//                            onRefresh();
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
     * 删除购物车
     *
     * @param position
     */
    private void removeShoppingCard(final int position) {
        try {


        List<Integer> listCartID = new ArrayList<>();

        if (!isEditStatus) {//编辑状态
            for (Integer i : commitData.keySet()) {
                if (!commitData.get(i).isHeader)
                    listCartID.add(commitData.get(i).getData().getCartID());
            }
        } else {
//            if (!commitData.get(position).isHeader)
            listCartID.add(data.get(position).getData().getCartID());
        }
        if (listCartID.size() <= 0) {
            showShort("请先选择商品");
            return;
        }
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("listCartID", listCartID);
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean = new Gson().fromJson(s, BaseResponseBean.class);
                if (bean.getStatusCode() == 1) {
                    if (!isEditStatus) {
                        data.removeAll(commitData.values());
                        commitData.clear();
                        adapter.notifyDataSetChanged();
                        updateShoppingCardPrice();
                    } else {
                        adapter.remove(position);
                    }

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
        }).getEntityData(this, HttpURL.HTTP_POST_SHOPPINGCART_DELETE, json);
        }catch (Exception e){

        }
    }


    @Override
    public void onAmountChange(View view, int amount, int position) {
        if (amount == data.get(position).getData().getFresh().getGodownNumber()) {
            showShort("亲，宝贝不能购买更多哦！");
        }
        if (amount > 0)
            upDateShoppingCart(position, amount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private boolean isOne = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isOne) {
//            data.clear();
            ivButton.setChecked(false);
            queryShoppingCart();
        } else {
            isOne = true;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
