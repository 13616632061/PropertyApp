package com.glory.bianyitong.ui.activity.shop;

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

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * 添加生鲜柜
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_MAP)
public class ExpressMapActivity extends BaseActivity {


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
    @BindView(R.id.bmapView)
    MapView mapView;

    private BaiduMap baiduMap;


    @Override
    protected int getContentId() {
        return R.layout.activity_express_bar_map;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("地址地图",false,"");
        baiduMap=mapView.getMap();
        //普通地图
        LatLng latLng=new LatLng((Double) mCache.getAsObject("latitude"),(Double) mCache.getAsObject("longitude"));
        MapStatus mapStatus=new MapStatus.Builder()
                .target(latLng)
                .zoom(18).build();

        MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newMapStatus(mapStatus);

        baiduMap.setMapStatus(mapStatusUpdate);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_text_left2})
    void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }





}
