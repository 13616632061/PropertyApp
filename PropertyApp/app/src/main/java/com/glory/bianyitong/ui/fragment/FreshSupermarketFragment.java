package com.glory.bianyitong.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chenenyu.router.Router;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.FreshTypeInfo;
import com.glory.bianyitong.bean.OrderNumberInfo;
import com.glory.bianyitong.bean.entity.request.RequestFreshType;
import com.glory.bianyitong.bean.entity.request.RequestProductList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryAddress;
import com.glory.bianyitong.bean.entity.response.ResponseQueryMyLocal;
import com.glory.bianyitong.bean.entity.response.ResponseQueryTwoType;
import com.glory.bianyitong.bean.entity.response.ResponseSearchFresh;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.shop.FreshPopTypeTwoAdapter;
import com.glory.bianyitong.ui.adapter.shop.FreshShopListAdapter;
import com.glory.bianyitong.ui.adapter.shop.FreshSuperMarketTypeAdapter;
import com.glory.bianyitong.ui.adapter.shop.ItemMenu;
import com.glory.bianyitong.util.SharedUtil;
import com.glory.bianyitong.util.TextUtil;
import com.google.gson.Gson;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lucy on 2016/11/10.
 * 生鲜
 */
public class FreshSupermarketFragment extends BaseFragment implements BDLocationListener, RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.topBar)
    RelativeLayout topBar;
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
    @BindView(R.id.fresh_list_fr_refresh)
    SwipeRefreshLayout freshListFrRefresh;
    @BindView(R.id.tv_cart_number)
    TextView tvCartNumber;

    private LocationClient client;
    private ResponseQueryMyLocal myLocal = null;//定位信息
    private PopupWindow popupWindowSort;
    private boolean isAll = true;
    private int orderType = 0;
    private int currentPageNumber = 1;


    // TODO: 2017/7/10 一级分类列表
    private FreshSuperMarketTypeAdapter typeAdapter;
    //    private List<ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean>> typeData=new ArrayList<>();
    private List<ItemMenu<FreshTypeInfo.ListFreshTypeBean>> typeData = new ArrayList<>();
    // TODO: 2017/7/10 二级分类列表
    private FreshPopTypeTwoAdapter twoAdapter;
    private PopupWindow popupWindowLeft;
    private List<ItemMenu<ResponseQueryTwoType.ListFreshTypeBean>> typeTwoData = new ArrayList<>();
    // TODO: 2017/7/10 商品列表
    private FreshShopListAdapter shopListAdapter;
    private List<ItemMenu<ResponseSearchFresh.ListfreshBean>> shopData = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    // TODO: 2017/7/10 其他参数
    private String orderBy = "";
    private int freshTypeID;//查询商品列表所需参数
    private boolean isLocal = false;//定位状态 false 失败 true 成功
    private int twoTypeFreshLeafID, twoTypeMerchantId, typeFreshLeafID = 99;//查询二级分类所需参数  类型ID  商户ID
    private boolean listModel = true;//是列表模式
    private ResponseQueryMyLocal.ListAreaBean nowLocal;//当前行政区对象
    public int cabinetID = 0;
    private final int REQUEST_CODE_ADDRESS = 100;//选择地址
    private double latitude;
    private double longitude;
    private boolean isOne = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fresh, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    @OnClick({R.id.rl_address, R.id.rl_zonghe, R.id.iv_title_left, R.id.iv_rec_line, R.id.iv_title_right})
    void onClickView(View view) {
        switch (view.getId()) {
            case R.id.rl_address://切换地址
                if (!SharedUtil.getBoolean("login")){
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(getActivity());
                }else{
                    Router.build(RouterMapping.ROUTER_ACTIVITY_MY_ADDRESS_MANAGER)
                            .with("source", true)
                            .requestCode(REQUEST_CODE_ADDRESS)
                            .go(this);
                }
//                Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_SELECT_LOCAL)
//                        .with("data",myLocal)
//                        .requestCode(200)
//                        .go(getActivity());

//                Intent intent = new Intent(getActivity(), SelectLocalActivity.class);
//                intent.putExtra("data", myLocal);
//                startActivityForResult(intent, 200);
                break;
            case R.id.rl_zonghe://弹出筛选框
                initPopupWindowSort();
                break;

            case R.id.iv_title_left://搜索
                Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_SEARCH)
                        .with("cabinetID", cabinetID)
                        .go(getActivity());
                break;
            case R.id.iv_title_right://购物车
                if (!SharedUtil.getBoolean("login")){
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(getActivity());
                }else {
                    Router.build(RouterMapping.ROUTER_ACTIVITY_SHOPPINGCART)
                            .go(getActivity());
                }
                break;
            case R.id.iv_rec_line://切换列表样式
                listModel = !listModel;
                if (listModel) {//列表
                    shopListAdapter = new FreshShopListAdapter(R.layout.item_fresh_list, shopData, getActivity());
                    shopListAdapter.setOnLoadMoreListener(this);
                    shopListAdapter.setOnItemClickListener(this);
                    gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                    recRightList.setLayoutManager(gridLayoutManager);
                    recRightList.setAdapter(shopListAdapter);
                    shopListAdapter.notifyDataSetChanged();
                } else {
                    shopListAdapter = new FreshShopListAdapter(R.layout.item_fresh_list_v, shopData, getActivity());
                    shopListAdapter.setOnLoadMoreListener(this);
                    shopListAdapter.setOnItemClickListener(this);
                    gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    recRightList.setLayoutManager(gridLayoutManager);
                    recRightList.setAdapter(shopListAdapter);
                    shopListAdapter.notifyDataSetChanged();
                }


                break;
        }
    }

    private void initView() {
        getShowNumber();
        initPopupWindowSort();
        freshListFrRefresh.setOnRefreshListener(this);
        client = new LocationClient(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        typeAdapter = new FreshSuperMarketTypeAdapter(R.layout.item_fresh_left, typeData, getActivity());
        typeAdapter.setOnItemClickListener(this);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                typeAdapter.setPosition(position);
                if (typeData.size() > 1) {
                    if (position == 0) {
                        isAll = true;
                        typeFreshLeafID = 99;
                        shopData.clear();
//                        getShopList();
                        onAutoRefresh();
                    } else {
                        FreshTypeInfo.ListFreshTypeBean bean = typeData.get(position).getData();
                        freshTypeID = bean.getFreshTypeID();
                        if (bean.getFreshTypeID() != -1) {//生鲜精选
                            twoTypeFreshLeafID = bean.getFreshTypeID();
                            twoTypeMerchantId = bean.getMerchant_ID();
                            queryFreshTypeTwo();
                        } else {
                            twoTypeMerchantId = typeData.get(1).getData().getMerchant_ID();
                            shopData.clear();
//                            getShopList();
                            onAutoRefresh();
                        }
                    }

                }
                typeAdapter.notifyDataSetChanged();

            }
        });
        recLeftButton.setLayoutManager(layoutManager);
        recLeftButton.setAdapter(typeAdapter);


        shopListAdapter = new FreshShopListAdapter(R.layout.item_fresh_list, shopData, getActivity());
        shopListAdapter.setOnItemClickListener(this);
        shopListAdapter.setOnLoadMoreListener(this);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recRightList.setLayoutManager(gridLayoutManager);
        recRightList.setAdapter(shopListAdapter);

        tvFreshAddress.setText("定位中");

        initPopupWindowLeft(false);
        initLocalData();

    }

    private void typeGo(double latitude, double longitude) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        Map<String, Object> freshMap = new HashMap<>();
        freshMap.put("cabinetID", cabinetID);//默认为0
        freshMap.put("latitude", latitude);
        freshMap.put("longitude", longitude);
        map.put("freshCabinet", freshMap);
        String json = new Gson().toJson(map);
        Log.v("json", json);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                FreshTypeInfo info = new Gson().fromJson(s, FreshTypeInfo.class);
                FreshTypeInfo.ListFreshTypeBean bean = new FreshTypeInfo.ListFreshTypeBean();
                bean.setFreshTypeName("精品首选");
                typeData.clear();
                typeData.add(new ItemMenu<FreshTypeInfo.ListFreshTypeBean>(bean));
                if (info.getStatusCode() == 1) {
                    for (FreshTypeInfo.ListFreshTypeBean beans : info.getListFreshType()) {
                        if (beans.getFreshTypeLeaf() == 0) {
                            typeData.add(new ItemMenu<FreshTypeInfo.ListFreshTypeBean>(beans));
                        }
                    }
                    typeAdapter.notifyDataSetChanged();
                    cabinetID = info.getFreshCabinet().getCabinetID();
                    tvFreshAddress.setText(info.getFreshCabinet().getCommunityName() + info.getFreshCabinet().getCabinetName());
                    onAutoRefresh();
                } else {
//                    showShort(info.getAlertMessage());
                }

//                getShopList();
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
        }).getEntityData(getActivity(), HttpURL.HTTP_POST_SHOP_QUERY_TTPE_RIGHT, json);
    }

    //左侧列表弹出窗口
    private void initPopupWindowLeft(boolean isShow) {
        if (popupWindowLeft == null) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            final View pView = inflater.inflate(R.layout.pop_fresh_left, null);
            popupWindowLeft = new PopupWindow(pView);
            popupWindowLeft.setWidth(((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 153, getResources().getDisplayMetrics())));
            popupWindowLeft.setHeight(((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 239, getResources().getDisplayMetrics())));
            RecyclerView rec_pop = (RecyclerView) pView.findViewById(R.id.rec_pop);
            twoAdapter = new FreshPopTypeTwoAdapter(R.layout.item_pop_fresh, typeTwoData, getActivity());
            twoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ItemMenu<ResponseQueryTwoType.ListFreshTypeBean> bean = typeTwoData.get(position);
                    if (bean != null)
                        freshTypeID = bean.getData().getFreshTypeID();
                    shopData.clear();
                    if (position == 0) {
                        typeFreshLeafID = twoTypeFreshLeafID;
                        isAll = true;
                    } else {
                        isAll = false;
                    }
//                    getShopList();
                    onAutoRefresh();
                    popupWindowLeft.dismiss();
                }
            });

            LinearLayoutManager pop_linearLayoutManager = new LinearLayoutManager(getActivity());
            pop_linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rec_pop.setLayoutManager(pop_linearLayoutManager);
            rec_pop.setAdapter(twoAdapter);
//            PopFreshAdapter popFreshAdapter=new PopFreshAdapter(this,R.layout.item_pop_fresh,list);
//            rec_pop.setAdapter(popFreshAdapter);

            //点击弹窗内容改变弹窗中文字颜色
//            popFreshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    PopFreshAdapter.popFreshCallBack.popTextColorCall(position);
//                }
//            });
        } else {
            if (!popupWindowLeft.isShowing() && isShow) {
                popupWindowLeft.showAtLocation(recLeftButton, Gravity.LEFT, recLeftButton.getWidth(), -(rlAddress.getHeight() + topBar.getHeight()));
            } else {
                popupWindowLeft.dismiss();
            }
        }
    }

    //综合排序弹出窗口
    private void initPopupWindowSort() {
        if (popupWindowSort == null) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            final View pView = inflater.inflate(R.layout.pop_fresh_sort, null);
            popupWindowSort = new PopupWindow(pView, getActivity().getWindowManager().getDefaultDisplay().getWidth() - rlAddress.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
//            //获取控件宽度
//            int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//            int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//            rlAddress.measure(w, h);
//            int width =rlAddress.getMeasuredWidth();
            //设置popwindow显示宽度
//            popupWindowSort.setWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth()-width);
//            popupWindowSort.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindowSort.setFocusable(true);
            popupWindowSort.setOutsideTouchable(true);
            //综合排序
            RadioGroup radioGroup = (RadioGroup) pView.findViewById(R.id.tabs_rg);
            radioGroup.setOnCheckedChangeListener(this);

            //点击弹窗下方关闭
            View pop_dismis = pView.findViewById(R.id.pop_dismis);
            pop_dismis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindowSort.dismiss();
                }
            });
        } else {
            if (!popupWindowSort.isShowing()) {
//                popupWindowSort.showAtLocation(rlZonghe, Gravity.LEFT|Gravity.RIGHT,0,0);
                popupWindowSort.showAsDropDown(rlZonghe);
            }
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
        Log.v("song", "1");
        if (AndPermission.hasPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.v("song", "2");
            client.start();
            client.requestLocation();
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

//    /**
//     * 根据区域ID查询商户信息/一级分类
//     * @param areaId
//     */
//    private void queryShopInfo(int areaId){
//        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
//        map.put("freshMerchant",new RequestQuerysShopInfo(areaId));
//        String json=new Gson().toJson(map);
//        typeData.clear();
//
//
//        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
//            @Override
//            public void onSuccess(String s) {
//                ResponseQueryShopInfo info=new Gson().fromJson(s,ResponseQueryShopInfo.class);
//                if(info.getStatusCode()==1){
//                    ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean jingPin=new ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean();
//                    jingPin.setFreshTypeID(-1);
//                    jingPin.setFreshTypeName("生鲜精选");
//                    typeData.add(0,new ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean>(jingPin));
//                    for (ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean bean:info.getListFreshMerchant().get(0).getListType()
//                         ) {
//                        typeData.add(new ItemMenu<ResponseQueryShopInfo.ListFreshMerchantBean.ListTypeBean>(bean));
//                    }
//
//                    if((info.getListFreshMerchant()!=null && info.getListFreshMerchant().size()>0) &&( info.getListFreshMerchant().get(0).getListType()!=null && info.getListFreshMerchant().get(0).getListType().size()>1)){
//                        freshTypeID=jingPin.getFreshTypeID();
//                        twoTypeFreshLeafID=info.getListFreshMerchant().get(0).getListType().get(1).getFreshTypeLeaf();
//                        twoTypeMerchantId=info.getListFreshMerchant().get(0).getListType().get(1).getMerchant_ID();
//                        shopData.clear();
//                        getShopList();
//                    }else {
//                        typeTwoData.clear();
//                        shopData.clear();
//                        typeAdapter.notifyDataSetChanged();
//                        shopListAdapter.notifyDataSetChanged();
//                    }
//
//                    typeAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onError() {
//
//            }
//
//            @Override
//            public void parseError() {
//
//            }
//
//            @Override
//            public void onBefore() {
//
//            }
//
//            @Override
//            public void onAfter() {
//
//            }
//        }).getEntityData(getActivity(),HttpURL.HTTP_POST_SHOP_QUERY_INFO,json);
//    }
//


    /**
     * 查询二级分类
     */
    private void queryFreshTypeTwo() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();

        map.put("freshType", new RequestFreshType(twoTypeFreshLeafID, twoTypeMerchantId));
        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryTwoType bean = new Gson().fromJson(s, ResponseQueryTwoType.class);
                if (bean.getStatusCode() == 1) {
                    typeTwoData.clear();
                    for (ResponseQueryTwoType.ListFreshTypeBean typeBean :
                            bean.getListFreshType()) {
                        typeTwoData.add(new ItemMenu<ResponseQueryTwoType.ListFreshTypeBean>(typeBean));
                    }
                    freshTypeID = bean.getListFreshType().get(0).getFreshTypeID();
                    twoAdapter.notifyDataSetChanged();

                    initPopupWindowLeft(true);
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
        }).getEntityData(getActivity(), HttpURL.HTTP_POST_FRESH_TYPE, json);
    }


    /**
     * 查询商品列表
     */
    private void getShopList() {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        String url;
        url = HttpURL.HTTP_POST_FRESH_QUERY_DETAIL;
        map.put("currentPageNumber", currentPageNumber);
        map.put("cabinetID", cabinetID);
        if (isAll) {
            Map<String, Object> map2 = new HashMap<>();
            map2.put("orderBy", "");
            map2.put("freshLeafID", typeFreshLeafID);
            map.put("fresh", map2);
        } else {
            map.put("fresh", new RequestProductList(freshTypeID, orderBy, twoTypeMerchantId));
        }


        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                freshListFrRefresh.setRefreshing(false);
                ResponseSearchFresh detail = new Gson().fromJson(s, ResponseSearchFresh.class);
                if (detail.getStatusCode() == 1) {
                    if (detail.getListfresh() == null || detail.getListfresh().size() <= 0) {
//                        showShort(detail.getAlertMessage());
                    } else {
                        for (ResponseSearchFresh.ListfreshBean beans : detail.getListfresh()
                                ) {
                            shopData.add(new ItemMenu<ResponseSearchFresh.ListfreshBean>(beans));
                        }
                        shopListAdapter.notifyDataSetChanged();

                    }
                    if (currentPageNumber < detail.getPageRowNumber()) {
                        shopListAdapter.setEnableLoadMore(true);
                        shopListAdapter.loadMoreComplete();
                    } else {
                        shopListAdapter.setEnableLoadMore(false);
                        shopListAdapter.loadMoreEnd();
                    }

                } else {

                    if (shopData.size() <= 0) {
                        showShort(detail.getAlertMessage());
                        shopData.clear();
                        shopListAdapter.notifyDataSetChanged();
                    } else {
                        shopListAdapter.loadMoreEnd();
                    }


                }
            }

            @Override
            public void onError() {
                freshListFrRefresh.setRefreshing(false);
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
        }).getEntityData(getActivity(), url, json);
    }


    /**
     * 获取当前位置信息
     *
     * @param bdLocation
     */
    private void getMyLocal(BDLocation bdLocation) {
        Map<String, Object> map = new BaseRequestBean().getBaseRequest();
        map.put("longitude", bdLocation.getLongitude());
        map.put("latitude", bdLocation.getLatitude());


        String json = new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                ResponseQueryMyLocal local = new Gson().fromJson(s, ResponseQueryMyLocal.class);
                if (local.getStatusCode() == 1) {
                    myLocal = local;
                    tvFreshAddress.setText(myLocal.getArea().getArea_Name());
//                    queryShopInfo(myLocal.getArea().getArea_ID());
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
        }).getEntityData(getActivity(), HttpURL.HTTP_POST_MY_LOCAL, json);

    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        switch (bdLocation.getLocType()) {
            case BDLocation.TypeGpsLocation://GPS
            case BDLocation.TypeNetWorkLocation://网络
            case BDLocation.TypeOffLineLocation://离线
                getMyLocal(bdLocation);
                Log.v("song", "3");
                typeGo(bdLocation.getLatitude(), bdLocation.getLongitude());
                latitude=bdLocation.getLatitude();
                longitude=bdLocation.getLongitude();
                mCache.put("longitude", bdLocation.getLongitude());
                mCache.put("latitude", bdLocation.getLatitude());
                isLocal = true;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {//选择地址
                ResponseQueryMyLocal.ListAreaBean area = (ResponseQueryMyLocal.ListAreaBean) data.getSerializableExtra("data");
                if (area != null) {
                    nowLocal = area;
                    tvFreshAddress.setText(nowLocal.getArea_Name());
                    typeData.clear();

//                    queryShopInfo(nowLocal.getArea_ID());
                }
            } else if (requestCode == REQUEST_CODE_ADDRESS) {//地址选择
                ResponseQueryAddress.ListShippingAddressBean addressBean = (ResponseQueryAddress.ListShippingAddressBean) data.getSerializableExtra("data");
                if (addressBean != null) {
                    typeData.clear();
                    cabinetID = addressBean.getCabinetID();
                    latitude = addressBean.getFreshCabinet().getCommunity().getLatitude();
                    longitude = addressBean.getFreshCabinet().getCommunity().getLongitude();
                    typeGo(addressBean.getFreshCabinet().getCommunity().getLatitude(), addressBean.getFreshCabinet().getCommunity().getLongitude());
                    onRefresh();

                }
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (popupWindowSort.isShowing())
            popupWindowSort.dismiss();

        switch (checkedId) {
            case R.id.rb_tab_1://综合搜索
                tvFreshSort.setText("综合搜索");
                orderBy = "";
                break;
            case R.id.rb_tab_2://价格最低
                tvFreshSort.setText("价格最低");
                orderBy = "ASC";
                break;
            case R.id.rb_tab_3://价格最高
                tvFreshSort.setText("价格最高");
                orderBy = "DESC";
                break;
        }
        shopData.clear();
        onAutoRefresh();
//        getShopList();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ResponseSearchFresh.ListfreshBean bean = shopData.get(position).getData();
        if (bean != null) {
            Router.build(RouterMapping.ROUTER_ACTIVITY_PRODUCT_DETAIL)
                    .with("data", bean)
                    .go(getActivity());
        }
    }


    @Override
    public void onRefresh() {
        freshListFrRefresh.setRefreshing(true);
        getShowNumber();
        currentPageNumber = 1;
        shopData.clear();
        shopListAdapter.notifyDataSetChanged();
        getShopList();
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageNumber++;
        getShopList();
    }


    /**
     * 自动刷新列表
     */
    public void onAutoRefresh() {
        freshListFrRefresh.post(new Runnable() {
            @Override
            public void run() {
                freshListFrRefresh.setRefreshing(true);
                currentPageNumber = 1;
                shopData.clear();
//                adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
                shopListAdapter.notifyDataSetChanged();
                getShopList();
            }
        });
    }

    private boolean isGetData = false;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
            if (enter && !isGetData) {
                isGetData = true;
                //   这里可以做网络请求或者需要的数据刷新操作
                typeData.clear();
                cabinetID = 0;
                typeGo(latitude, longitude);
                onRefresh();
            } else {
                isGetData = false;
            }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    //获取订单数量 购物车数量
    private void getShowNumber() {
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
        }).getEntityData(getActivity(), HttpURL.HTTP_POST_ORDER_OTHERONE, json);
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }
}
