package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryExpressBarByKeyWord;
import com.glory.bianyitong.bean.entity.request.RequestQueryExpressBarByLocal;
import com.glory.bianyitong.bean.entity.response.ResponseQueryExpressBar;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.ExpressBarAddAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * 添加生鲜柜
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_ADD,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class ExpressBarAddActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener{


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
    @BindView(R.id.clean_word)
    RelativeLayout cleanWord;
    @BindView(R.id.et_search_area_txt)
    EditText etSearchAreaTxt;
    @BindView(R.id.express_bar_addlist)
    RecyclerView expressBarAddlist;
    @BindView(R.id.lay_shopping_car)
    LinearLayout layShoppingCar;

    // TODO: 2017/7/11 快递柜列表
    private ExpressBarAddAdapter  addAdapter;
    private List<ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean>> data=new ArrayList<>();
    @Override
    protected int getContentId() {
        return R.layout.activity_express_bar_add;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("添加新地址",false,"");

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        expressBarAddlist.setLayoutManager(layoutManager);
        addAdapter=new ExpressBarAddAdapter(R.layout.item_address_express,data);
        addAdapter.setOnItemClickListener(this);
        addAdapter.bindToRecyclerView(expressBarAddlist);
        expressBarAddlist.setAdapter(addAdapter);
        queryExpressBarByLocal();
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2,R.id.clean_word})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
            case R.id.clean_word://地图
                Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_MAP)
                        .go(this);
                break;
        }
    }

    @OnEditorAction(R.id.et_search_area_txt)
    boolean onEditSearch(TextView txtView, int actionId, KeyEvent event){
        if ((actionId == 0 || actionId == 3) && event != null) {
            String keyWord=etSearchAreaTxt.getText().toString().trim();
            if(!TextUtils.isEmpty(keyWord)){
                queryExpressBarByKeyWord(keyWord);
            }else {
                showShort("搜索关键字不能为空");
            }


        }
        return false;
    }


    private void queryExpressBarByLocal() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        RequestQueryExpressBarByLocal.Local local = new RequestQueryExpressBarByLocal.Local((Double) mCache.getAsObject("longitude"),
        (Double) mCache.getAsObject("latitude"));
        map.put("shippingAddress", new RequestQueryExpressBarByLocal(local));
        String json = new Gson().toJson(map);
        data.clear();
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryExpressBar expressBar=new Gson().fromJson(s,ResponseQueryExpressBar.class);
                if(expressBar.getStatusCode()==1){
                    data.clear();
                    for (ResponseQueryExpressBar.ListFreshCabinetBean bean:expressBar.getListFreshCabinet()
                         ) {
                        data.add(new ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean>(bean));
                    }
                    addAdapter.notifyDataSetChanged();
                }else {
                    addAdapter.setEmptyView(R.layout.layout_empty);
                    showShort(expressBar.getAlertMessage());
                }
            }

            @Override
            public void onError() {
                addAdapter.setEmptyView(R.layout.layout_empty);
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
        }).getEntityData(HttpURL.HTTP_POST_QUERY_ADDRESS_EXPRESS_BAR_LOCAL, json);

    }

    private void queryExpressBarByKeyWord(String keyWords) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        RequestQueryExpressBarByKeyWord.KeyWord keyWord = new RequestQueryExpressBarByKeyWord.KeyWord(keyWords);
        map.put("shippingAddress", new RequestQueryExpressBarByKeyWord(keyWord));
        String json = new Gson().toJson(map);
        data.clear();
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryExpressBar expressBar=new Gson().fromJson(s,ResponseQueryExpressBar.class);
                if(expressBar.getStatusCode()==1){
                    data.clear();
                    for (ResponseQueryExpressBar.ListFreshCabinetBean bean:expressBar.getListFreshCabinet()
                            ) {
                        data.add(new ItemMenu<ResponseQueryExpressBar.ListFreshCabinetBean>(bean));
                    }
                    addAdapter.notifyDataSetChanged();
                }else {
                    addAdapter.setEmptyView(R.layout.layout_empty);
                    showShort(expressBar.getAlertMessage());
                }
            }

            @Override
            public void onError() {
                addAdapter.setEmptyView(R.layout.layout_empty);
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
        }).getEntityData(HttpURL.HTTP_POST_QUERY_ADDRESS_EXPRESS_BAR_SEARCH, json);

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
