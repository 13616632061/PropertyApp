package com.glory.bianyitong.ui.activity;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestLocalAreaBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryAreaList;
import com.glory.bianyitong.bean.entity.response.ResponseListCommunity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.AddAreaCityAdapter;
import com.glory.bianyitong.util.ActivityManager;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lucy on 2016/11/14.
 * 添加小区
 */
public class AddAreaCityActivity extends BaseActivity implements BDLocationListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_near)
    TextView tv_near;
    @BindView(R.id.city_community_list)
    LinearLayout city_community_list;

    @BindView(R.id.clean_word)
    RelativeLayout clean_word;
    @BindView(R.id.et_search_area_txt)
    EditText et_search_area_txt;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fresh_list_fr_refresh)
    SwipeRefreshLayout freshListFrRefresh;
    private int currentPageNumber=1;

    private String userID = "";
    private ProgressDialog progressDialog = null;
    private LocationClient client;
    private String provider;
    private double latitude; //维度
    private double longitude; //经度
    private List<ResponseListCommunity.ListCommunityBean> nearlist=new ArrayList<>();
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            latitude = arg0.getLatitude(); //纬度
            longitude = arg0.getLongitude(); //经度
            request_community(latitude, longitude);
            Log.i("resultString", "latitude---------" + latitude);
            Log.i("resultString", "longitude---------" + longitude);
        }
    };
    private Handler mhandler;
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                //这里写发送信息的方法
                String tag_name = et_search_area_txt.getText().toString().trim();
                if (tag_name.equals("")) {
                    ToastUtils.showToast(AddAreaCityActivity.this, getResources().getString(R.string.search_can_not_be_empty));//搜索不能为空
                } else {
                    Message msg = new Message();
                    msg.obj = tag_name;
                    msg.what = 0;
                    mhandler.sendMessage(msg);
                }
                return true;
            }
            return false;
        }
    };
    private AddAreaCityAdapter adapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_add_area_city;
    }

    @Override
    protected void init() {
        super.init();
        //初始化标题栏
        inintTitle(getResources().getString(R.string.add_community), true, ""); //添加小区
        client = new LocationClient(this);
        ActivityManager.addActivity(this, "addareacityactivity");
        initview();
        userID = RequestUtil.getuserid();
        //定位
        initLocalData();
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        request_community2(msg.obj.toString());
                        break;
                }
            }
        };

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AddAreaCityAdapter(R.layout.view_item_community_city, nearlist);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
        freshListFrRefresh.setOnRefreshListener(this);

    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<ResponseListCommunity.ListCommunityBean> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_community_city, lay_gallery, false);
                final RelativeLayout item_auth_lay = (RelativeLayout) view.findViewById(R.id.item_auth_lay);
                final TextView auth_area_name = (TextView) view.findViewById(R.id.auth_area_name);
                final TextView tv_city_name = (TextView) view.findViewById(R.id.tv_city_name);
                final TextView auth_area_line = (TextView) view.findViewById(R.id.auth_area_line);

                auth_area_name.setText(list.get(i).getCommunityName());
                tv_city_name.setText(list.get(i).getStreet());
                if (i == list.size() - 1) { //最后一根线
                    auth_area_line.setVisibility(View.GONE);
                }
                final int j = i;
                item_auth_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        Database.communityID = list.get(j).getCommunityID();
                        Database.communityName = list.get(j).getCommunityName();
                        Database.id_province = list.get(j).getProvinceID();
                        Database.id_city = list.get(j).getCityID();
                        Database.str_province = list.get(j).getProvinceName();
                        Database.str_city = list.get(j).getCityName();
                        Router.build(RouterMapping.ROUTER_ACTIVITY_AREA_ADD)
                                .with("communityID", list.get(j).getCommunityID())
                                .go(AddAreaCityActivity.this);
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    /**
     * 搜索小区
     * //获取小区
     *
     * @param name
     */
    private void request_community2(final String name) {
        freshListFrRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request_community2(name);
            }
        });
        adapter.setEnableLoadMore(false);
        freshListFrRefresh.setRefreshing(true);
        nearlist.clear();
        adapter.notifyDataSetChanged();
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("community", new RequestQueryAreaList(name));
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    freshListFrRefresh.setRefreshing(false);
                    if (TextUtil.isEmpty(s)) {
                        showShort("系统异常");
                    } else {
                        ResponseListCommunity responseListCommunity = new Gson().fromJson(s, ResponseListCommunity.class);
                        if (responseListCommunity.getStatusCode() == 1) {
                            for (ResponseListCommunity.ListCommunityBean data : responseListCommunity.getListCommunity()) {
                                nearlist.add(data);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            showShort(responseListCommunity.getAlertMessage());
                        }
                    }
                }

                @Override
                public void onError() {
                    freshListFrRefresh.setRefreshing(false);
                }

                @Override
                public void parseError() {
                }

                @Override
                public void onBefore() {
                    progressDialog = ProgressDialog.show(AddAreaCityActivity.this, "", getResources().getString(R.string.load), true);//加载
                    progressDialog.setCanceledOnTouchOutside(true);
                }

                @Override
                public void onAfter() {
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA_QUERY, json);
        } catch (Exception e) {

        }
    }

    //获取附近小区
    private void request_community(double latitude, double longitude) {
        try {
            Map<String, Object> map = new BaseRequestBean().getBaseRequest();
            map.put("currentPageNumber",currentPageNumber);
            map.put("community", new RequestLocalAreaBean(longitude, latitude));
            String json = new Gson().toJson(map);
            OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
                @Override
                public void onSuccess(String s) {
                    freshListFrRefresh.setRefreshing(false);
                    if (TextUtil.isEmpty(s)) {
                        showShort("系统异常");
                    } else {
                        ResponseListCommunity responseListCommunity = new Gson().fromJson(s, ResponseListCommunity.class);
                        if (responseListCommunity.getStatusCode() == 1) {
//                            nearlist = responseListCommunity.getListCommunity();
                            for (ResponseListCommunity.ListCommunityBean data : responseListCommunity.getListCommunity()) {
                                nearlist.add(data);
                            }
                            adapter.notifyDataSetChanged();
                            if(currentPageNumber<responseListCommunity.getPageRowNumber()){
                                adapter.setEnableLoadMore(true);
                                adapter.loadMoreComplete();
                            }else {
                                adapter.setEnableLoadMore(false);
                                adapter.loadMoreEnd();
                            }
//                            ScrollViewLayout(AddAreaCityActivity.this, nearlist, city_community_list);
                        } else  if(responseListCommunity.getStatusCode()==2){
                            if(nearlist.size()<=0)
                                adapter.setEmptyView(R.layout.layout_empty_wushuju);
                            else {
                                adapter.loadMoreEnd();
                            }

                        }else {
                            showShort(responseListCommunity.getAlertMessage());
                        }
                    }
                }

                @Override
                public void onError() {
                    freshListFrRefresh.setRefreshing(false);
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
            }).getEntityData(this, HttpURL.HTTP_POST_LOCAL_AREA, json);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                AddAreaCityActivity.this.finish();
                break;
            case R.id.clean_word:
                et_search_area_txt.setText("");
//                if (nearlist != null && nearlist.size() != 0) {
//                    ScrollViewLayout(AddAreaCityActivity.this, nearlist, city_community_list);
//                }
                break;
        }
    }

    void initLocalData() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 6000;
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
        if (AndPermission.hasPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)) {

            client.start();
        } else {
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
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

    private void initview() {
        et_search_area_txt.setOnKeyListener(onKeyListener);

        clean_word.setOnClickListener(this);
        left_return_btn.setOnClickListener(this);
        et_search_area_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                if (arg0.length() > 0) {
                    clean_word.setVisibility(View.VISIBLE);
                } else {
                    clean_word.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        switch (bdLocation.getLocType()) {
            case BDLocation.TypeGpsLocation://GPS
                request_community(bdLocation.getLatitude(), bdLocation.getLongitude());
                latitude = bdLocation.getLatitude();
                longitude = bdLocation.getLongitude();
                client.stop();
                break;
            case BDLocation.TypeNetWorkLocation://网络
                request_community(bdLocation.getLatitude(), bdLocation.getLongitude());
                latitude = bdLocation.getLatitude();
                longitude = bdLocation.getLongitude();
                client.stop();
                break;
            case BDLocation.TypeOffLineLocation://离线
                request_community(bdLocation.getLatitude(), bdLocation.getLongitude());
                latitude = bdLocation.getLatitude();
                longitude = bdLocation.getLongitude();
                client.stop();
                break;
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        request_community(latitude, longitude);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Database.communityID = nearlist.get(position).getCommunityID();
        Database.communityName = nearlist.get(position).getCommunityName();
        Database.id_province = nearlist.get(position).getProvinceID();
        Database.id_city = nearlist.get(position).getCityID();
        Database.str_province = nearlist.get(position).getProvinceName();
        Database.str_city = nearlist.get(position).getCityName();
        Router.build(RouterMapping.ROUTER_ACTIVITY_AREA_ADD)
                .with("communityID", nearlist.get(position).getCommunityID())
                .go(AddAreaCityActivity.this);
    }

    @Override
    public void onRefresh() {
        freshListFrRefresh.setRefreshing(true);
        currentPageNumber=1;
        nearlist.clear();
//        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        adapter.notifyDataSetChanged();
        request_community(latitude, longitude);
    }
}
