package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.glory.bianyitong.bean.entity.response.ResponseShoppingCart;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.ShoppingCardAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购物车
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_SHOPPINGCART, interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener,BaseQuickAdapter.OnItemChildClickListener,CompoundButton.OnCheckedChangeListener {


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
    @BindView(R.id.iv_button)
    ImageView ivButton;
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.move_collection)
    TextView moveCollection;
    @BindView(R.id.tv_Settlement)
    TextView tvSettlement;
    private ShoppingCardAdapter adapter;
    private List<ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> data = new ArrayList<>();
    private Map<Integer,ItemMenu<ResponseShoppingCart.ListShoppingCartBean>> commitData=new HashMap<>();
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
        adapter = new ShoppingCardAdapter(R.layout.item_shoppingcart, R.layout.item_title_shoppingcart, data,this,this);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        recShoppingcart.setAdapter(adapter);
        recShoppingcart.setLayoutManager(linearLayout);
        adapter.setOnItemChildClickListener(this);
        queryShoppingCart();
    }


    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2,R.id.iv_title_text_right})
    void OnclickViews(View view){
        switch (view.getId()){
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_text_right:

                break;
        }
    }
    private void queryShoppingCart(){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseShoppingCart bean=new Gson().fromJson(s,ResponseShoppingCart.class);
                if(bean.getStatusCode()==1){
                    List<ResponseShoppingCart.ListShoppingCartBean> list=bean.getListShoppingCart();
                    if(!(list==null || list.size()<=0)){
                        data.clear();
                        for (ResponseShoppingCart.ListShoppingCartBean entity:list
                             ) {
//                            data.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean>(true,entity.getFresh().getMerchantName()));
                            data.add(new ItemMenu<ResponseShoppingCart.ListShoppingCartBean>(entity));
                        }
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
        }).getEntityData(HttpURL.HTTP_POST_SHOPPINGCART_QUERY,json);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId()==R.id.tv_shop_delete){//删除
            removeShoppingCard(position);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position= (int) buttonView.getTag(R.id.shopCard_check);
        if(position<0)
            return;
        Log.d("position:=========",""+data.get(position).getData().getFreshName());
        ItemMenu<ResponseShoppingCart.ListShoppingCartBean> bean=data.get(position);
        if(!(bean==null || bean.getData()==null)){
            if(commitData.containsKey(position)){//取消选中
                commitData.remove(position);

                showShort("取消选中:"+position);
            }else {
                commitData.put(position,bean);
                showShort("选中:"+position);
            }
            updateShoppingCardPrice();
        }

    }

    private void updateShoppingCardPrice(){
        double allPrice=0;
        for (Integer data:commitData.keySet()
             ) {
            ResponseShoppingCart.ListShoppingCartBean bean=commitData.get(data).getData();
            allPrice+=bean.getPrice()*bean.getQuantity();
        }

        allMoney.setText("￥"+allPrice);
    }

    /**
     * 删除购物车
     * @param position
     */
    private void removeShoppingCard(final int position){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        List<Integer> listCartID=new ArrayList<>();
        listCartID.add(data.get(position).getData().getCartID());
        map.put("listCartID",listCartID);
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    adapter.remove(position);
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
        }).getEntityData(HttpURL.HTTP_POST_SHOPPINGCART_DELETE,json);
    }



}
