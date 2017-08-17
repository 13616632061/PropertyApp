package com.glory.bianyitong.ui.activity.shop;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryFreshLocal;
import com.glory.bianyitong.bean.entity.response.ResponseQueryMyLocal;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.ui.adapter.shop.SelectLocalAdapter;
import com.google.gson.Gson;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;


/**
 * Created by Administrator on 2017/7/10.
 * 选择地区
 */

@Route(RouterMapping.ROUTER_ACTIVITY_PRODUCT_SELECT_LOCAL)
public class SelectLocalActivity extends BaseActivity implements BaseSectionQuickAdapter.OnItemClickListener ,BDLocationListener{

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
    @BindView(R.id.add_local_list)
    RecyclerView addLocalList;
//    @InjectParam(key = "data")
    ResponseQueryMyLocal myLocal;


    // TODO: 2017/7/10 列表
    private SelectLocalAdapter adapter;
    private List<ItemMenu<ResponseQueryMyLocal.ListAreaBean>> datas=new ArrayList<>();

    // TODO: 2017/7/10 定位
    private LocationClient client;
    @Override
    protected int getContentId() {
        return R.layout.activity_add_local;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        inintTitle("选择地区", false, "");
        myLocal= (ResponseQueryMyLocal) getIntent().getSerializableExtra("data");
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        adapter=new SelectLocalAdapter(R.layout.item_fresh_local_content,R.layout.item_fresh_local_title,datas);
        adapter.setOnItemClickListener(this);
        addLocalList.setLayoutManager(layoutManager);
        addLocalList.setAdapter(adapter);

        if(myLocal!=null){
            addData(myLocal);
        }else {
            initLocal();
        }

    }


    private void initLocal() {
        client=new LocationClient(this);
        initLocalData();
    }


    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickText(View view){
        switch (view.getId()){
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }

    @OnEditorAction(R.id.et_search_area_txt)
    boolean onEditSearch(TextView txtView, int actionId, KeyEvent event){
        if ((actionId == 0 || actionId == 3) && event != null) {
            String keyWord=etSearchAreaTxt.getText().toString().trim();
            if(!TextUtils.isEmpty(keyWord)) {
                queryLocal(keyWord);
            }else {
                showShort("搜索关键字不能为空");
            }


        }
        return false;
    }




    private void addData(ResponseQueryMyLocal local){
        datas.clear();
        if(local!=null){
            if(local.getArea()!=null){
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(true,"定位地区"));
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(local.getArea()));
            }else {
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(true,"无定位地区"));
            }

            if(local.getListArea()==null || local.getListArea().size()<=0){
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(true,"无其他地区"));
            }else {
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(true,"其他地区"));
            }

            for (ResponseQueryMyLocal.ListAreaBean bean:local.getListArea()
                 ) {
                datas.add(new ItemMenu<ResponseQueryMyLocal.ListAreaBean>(bean));
            }
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * 获取当前位置信息
     * @param bdLocation
     */
    private void getMyLocal(BDLocation bdLocation){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("longitude",bdLocation.getLongitude());
        map.put("latitude",bdLocation.getLatitude());


        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryMyLocal local=new Gson().fromJson(s,ResponseQueryMyLocal.class);
                if(local.getStatusCode()==1){
                    addData(local);
                }else {
                    datas.clear();
                    View view= LayoutInflater.from(SelectLocalActivity.this).inflate(R.layout.layout_empty,null);
                    adapter.setEmptyView(view);
                }
            }

            @Override
            public void onError() {
                datas.clear();
                View view= LayoutInflater.from(SelectLocalActivity.this).inflate(R.layout.layout_empty,null);
                adapter.setEmptyView(view);
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
        }).getEntityData(this,HttpURL.HTTP_POST_MY_LOCAL,json);

    }

    private void queryLocal(String keyWord){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("area",new RequestQueryFreshLocal(keyWord));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryMyLocal local=new Gson().fromJson(s,ResponseQueryMyLocal.class);
                if(local.getStatusCode()==1){
                    addData(local);
                }else {
                    datas.clear();
                    View view= LayoutInflater.from(SelectLocalActivity.this).inflate(R.layout.layout_empty,null);
                    adapter.setEmptyView(view);
                }
            }

            @Override
            public void onError() {
                datas.clear();
                View view= LayoutInflater.from(SelectLocalActivity.this).inflate(R.layout.layout_empty,null);
                adapter.setEmptyView(view);
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
        }).getEntityData(this,HttpURL.HTTP_POST_SHOP_QUERY_LOCAL,json);
    }

    void initLocalData(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span=6000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        client.setLocOption(option);
        client.registerLocationListener(this);
        if(AndPermission.hasPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)){

            client.start();
        }else {
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                    .send();
        }

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        switch (bdLocation.getLocType()){
            case BDLocation.TypeGpsLocation://GPS
            case BDLocation.TypeNetWorkLocation://网络
            case BDLocation.TypeOffLineLocation://离线
                getMyLocal(bdLocation);
                mCache.put("longitude",bdLocation.getLongitude());
                mCache.put("latitude",bdLocation.getLatitude());
                client.stop();
                break;
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @PermissionYes(100)
    private void getYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        client.start();
    }

    // 失败回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionNo(100)
    private void getNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            AndPermission.defaultSettingDialog(this, 1).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ItemMenu<ResponseQueryMyLocal.ListAreaBean> itemMenu=datas.get(position);
        if(!itemMenu.isHeader){
            Intent intent=new Intent();
            intent.putExtra("data",itemMenu.getData());
            setResult(Activity.RESULT_OK,intent);
            finish();
        }
    }
}
