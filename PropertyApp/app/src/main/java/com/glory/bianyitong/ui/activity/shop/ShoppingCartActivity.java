package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestShoppingCartAdd;
import com.glory.bianyitong.bean.entity.request.RequestShoppingUpDate;
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.ShoppingCardAdapter;
import com.glory.bianyitong.widght.shop.AmountView;
import com.google.gson.Gson;

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
public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, CompoundButton.OnCheckedChangeListener,AmountView.OnAmountChangeListener {


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
    private ShoppingCardAdapter adapter;
    private boolean isEditStatus=true;//编辑   true编辑  false完成
    private List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> data = new ArrayList<>();
    private Map<Integer, ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> commitData = new HashMap<>();

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
        adapter = new ShoppingCardAdapter(R.layout.item_shoppingcart, R.layout.item_title_shoppingcart, data, this, this,commitData,this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recShoppingcart.setAdapter(adapter);
        recShoppingcart.setLayoutManager(linearLayout);
        adapter.setOnItemChildClickListener(this);
        adapter.bindToRecyclerView(recShoppingcart);
        shoppingCartPay.setText("结算("+commitData.size()+")");
        queryShoppingCart();
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2, R.id.iv_title_text_right,R.id.shopping_cart_pay,R.id.shopping_cart_collection})
    void OnclickViews(View view) {
        switch (view.getId()) {
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.shopping_cart_pay://支付或删除
                if(isEditStatus){//删除
                    showShort("结算");
                }else {
                    removeShoppingCard(0);
                }
                break;
            case R.id.iv_title_text_right://编辑
                if(isEditStatus){//完成状态

                    ivTitleTextRight.setText("编辑");
                    shoppingCartCollection.setVisibility(View.GONE);
                    shoppingCartTxtYunfei.setVisibility(View.VISIBLE);

                }else {//编辑状态

                    ivTitleTextRight.setText("完成");
                    shoppingCartCollection.setVisibility(View.VISIBLE);
                    shoppingCartTxtYunfei.setVisibility(View.GONE);

                }

                isEditStatus=isEditStatus?false:true;
                updateShoppingCardPrice();
                break;

            case R.id.shopping_cart_collection://收藏
                break;
        }
    }

    @OnCheckedChanged(R.id.iv_buttons)
    void onChangesBtn(CompoundButton view, boolean isCheck) {

        if(isCheck) {
            commitData.clear();
            for (int i = 0; i < data.size(); i++) {
                commitData.put(i, data.get(i));
            }
        }else {
            if(commitData.size()==data.size()){
                commitData.clear();
            }

        }
        adapter.notifyDataSetChanged();
    }


    /**
     * 查询购物车
     */
    private void queryShoppingCart() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseShoppingCart bean = new Gson().fromJson(s, ResponseShoppingCart.class);
                if (bean.getStatusCode() == 1) {
                    List<ResponseShoppingCart.ListShoppingCartBean> list = bean.getListShoppingCart();
                    if (!(list == null || list.size() <= 0)) {
                        data.clear();
                        for (ResponseShoppingCart.ListShoppingCartBean entity : list
                                ) {
                            data.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean>(entity));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }else {
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
        }).getEntityData(HttpURL.HTTP_POST_SHOPPINGCART_QUERY, json);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.tv_shop_delete) {//删除
            removeShoppingCard(position);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag(R.id.shopCard_check);
        if (position < 0)
            return;
        ItemMenu<ResponseShoppingCart.ListShoppingCartBean> bean = data.get(position);
        if (!(bean == null || bean.getData() == null)) {

            if (isChecked) {
                commitData.put(position, bean);
                if(commitData.size()==data.size()){
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
        double allPrice = 0;
        for (Integer data : commitData.keySet()
                ) {
            ResponseShoppingCart.ListShoppingCartBean bean = commitData.get(data).getData();
            allPrice += bean.getPrice() * bean.getQuantity();
        }

        allMoney.setText("￥" + allPrice);
        if(isEditStatus){
            shoppingCartPay.setText("结算("+commitData.size()+")");
        }else {
            shoppingCartPay.setText("删除");
        }
    }

    /**
     * 更新购物车
     * @param position
     * @param num
     */
    private void upDateShoppingCart(final int position, final int num){
        int cartId=data.get(position).getData().getCartID();
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("shoppingCart",new RequestShoppingUpDate(cartId,num));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    data.get(position).getData().setQuantity(num);
                    if(commitData.containsKey(position)){
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
        }).getEntityData(HttpURL.HTTP_POST_SHOPPINGCART_EDIT,json);
    }

    /**
     * 删除购物车
     *
     * @param position
     */
    private void removeShoppingCard(final int  position) {

        List<Integer> listCartID = new ArrayList<>();

        if(!isEditStatus){//编辑状态
            for (Integer i:commitData.keySet()
                 ) {
                listCartID.add(commitData.get(i).getData().getCartID());
            }
        }else {
            listCartID.add(data.get(position).getData().getCartID());
        }
        if(listCartID.size()<=0){
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
                    if(!isEditStatus){
                        data.removeAll(commitData.values());
                        commitData.clear();
                        adapter.notifyDataSetChanged();
                        updateShoppingCardPrice();
                    }else {
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
        }).getEntityData(HttpURL.HTTP_POST_SHOPPINGCART_DELETE, json);
    }


    @Override
    public void onAmountChange(View view, int amount,int position) {
        if(amount>0)
        upDateShoppingCart(position,amount);
    }
}
