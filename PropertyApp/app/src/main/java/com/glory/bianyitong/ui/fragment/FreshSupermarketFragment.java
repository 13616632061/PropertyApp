package com.glory.bianyitong.ui.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.FreshLeftInfo;
import com.glory.bianyitong.bean.entity.request.RequestFreshType;
import com.glory.bianyitong.bean.entity.request.RequestQuerysShopInfo;
import com.glory.bianyitong.bean.entity.response.ResponseQueryMyLocal;
import com.glory.bianyitong.bean.entity.response.ResponseQueryShopInfo;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.ui.adapter.shop.PopFreshAdapter;
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

import static android.R.id.list;

/**
 * Created by lucy on 2016/11/10.
 * 生鲜
 */
public class FreshSupermarketFragment extends BaseFragment implements BDLocationListener{



    @BindView(R.id.tv_fresh_address)
    TextView tvFreshAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_fresh_sort)
    TextView tvFreshSort;
    @BindView(R.id.rl_zonghe)
    RelativeLayout rlZonghe;
    @BindView(R.id.iv_rec_line)
    ImageView ivRecLine;
    @BindView(R.id.rec_left_button)
    RecyclerView recLeftButton;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.rec_right_list)
    RecyclerView recRightList;
    @BindView(R.id.main)
    LinearLayout main;

    private LocationClient client;
    private ResponseQueryMyLocal myLocal=null;//定位信息

    private List<FreshLeftInfo> list=new ArrayList<>();
    private PopupWindow popupWindowSort,popupWindowLeft;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client=new LocationClient(getActivity());
        initLocalData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fresh, null);
        ButterKnife.bind(view);
        initView();
        return view;
    }


    @OnClick({R.id.rl_address})
    void onClickView(View view){
        switch (view.getId()){
            case R.id.rl_address://切换地址
                initPopupWindowLeft();
                break;
        }
    }
    private void initView() {
//        titleAcText.setText("生鲜");
    }



    private void queryShopInfo(int areaId){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("freshMerchant",new RequestQuerysShopInfo(areaId));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryShopInfo info=new Gson().fromJson(s,ResponseQueryShopInfo.class);
                if(info.getStatusCode()==1){
                    for (ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean bean:info.getListFreshMerchant().get(0).getListType()
                         ) {
                        list.add(new FreshLeftInfo(false,bean.getFreshTypeName()));
                    }
                }else {

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
        }).getEntityData(HttpURL.HTTP_POST_SHOP_QUERY_INFO,json);
    }


    private void queryFreshTypeOne(){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("",new RequestFreshType(1));
        String json="";
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

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
        }).getEntityData(HttpURL.HTTP_POST_FRESH_TYPE,json);
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
         if(AndPermission.hasPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)){

             client.start();
         }else {
             AndPermission.with(this)
                     .requestCode(100)
                     .permission(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                     .send();
         }

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
                    myLocal=local;
                    queryShopInfo(myLocal.getArea().getArea_ID());
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
        }).getEntityData(HttpURL.HTTP_POST_MY_LOCAL,json);

    }


    private void initPopupWindowLeft() {
        if (popupWindowLeft == null) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            final View pView = inflater.inflate(R.layout.pop_fresh_left, null);
            popupWindowLeft = new PopupWindow(pView);
            popupWindowLeft.setWidth(((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 153, getResources().getDisplayMetrics())));
            popupWindowLeft.setHeight(((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 239, getResources().getDisplayMetrics())));
            RecyclerView rec_pop= (RecyclerView) pView.findViewById(R.id.rec_pop);
            LinearLayoutManager pop_linearLayoutManager=new LinearLayoutManager(getActivity());
            pop_linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rec_pop.setLayoutManager(pop_linearLayoutManager);

//            PopFreshAdapter popFreshAdapter=new PopFreshAdapter(this,R.layout.item_pop_fresh,list);
            rec_pop.setAdapter(popFreshAdapter);

            //点击弹窗内容改变弹窗中文字颜色
            popFreshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    PopFreshAdapter.popFreshCallBack.popTextColorCall(position);
                }
            });
//            popFreshAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
//                @Override
//                public void onItemClick(View view, int i) {
//                    PopFreshAdapter.popFreshCallBack.popTextColorCall(i);
//                }
//            });
        }
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        switch (bdLocation.getLocType()){
            case BDLocation.TypeGpsLocation://GPS
                getMyLocal(bdLocation);
                client.stop();
                break;
            case BDLocation.TypeNetWorkLocation://网络
                getMyLocal(bdLocation);
                client.stop();
                break;
            case BDLocation.TypeOffLineLocation://离线
                getMyLocal(bdLocation);
                client.stop();
                break;
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


}
