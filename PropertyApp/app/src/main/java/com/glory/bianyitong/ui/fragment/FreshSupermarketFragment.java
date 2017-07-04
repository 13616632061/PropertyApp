package com.glory.bianyitong.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.util.ActivityUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseFragment;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.activity.SearchActivity;
import com.glory.bianyitong.ui.adapter.FragmentVPAdapter;
import com.glory.bianyitong.ui.fragment.fresh.ChoicenessFragment;
import com.glory.bianyitong.ui.fragment.fresh.FruitFragment;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/10.
 * 生鲜
 */
public class FreshSupermarketFragment extends BaseFragment {
    Context context;
    //    Toast mToast;
    @BindView(R.id.lay_search)
    LinearLayout lay_search;

    ViewPager viewPager;
    @BindView(R.id.ll_fresh_tag)
    LinearLayout ll_fresh_tag;

    //顶部标签栏
    DisplayMetrics displayMetrics;
    List<LinkedTreeMap<String, Object>> typelist; // 主 类型
    List<ArrayList<LinkedTreeMap<String, Object>>> alltaglist; //生鲜类型标签数据
    @BindView(R.id.hs_fresh_tag)
    HorizontalScrollView hs_fresh_tag;
    @BindView(R.id.lay_hs_fresh_tag)
    LinearLayout lay_hs_fresh_tag;
    @BindView(R.id.lay_no_network)
    LinearLayout lay_no_network;
    FragmentVPAdapter fragmentVPAdapter;
    private View fresh_view;
    private LayoutInflater inflater;
    //精选
    private View choiceness_view;
    //水果
    private List<Fragment> fragmentList = null; //碎片链表
//    private ProgressDialog progressDialog = null;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        //重写handleMessage方法
        public void handleMessage(android.os.Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {//判断传入的消息
                case 1:
                    fragmentVPAdapter = new FragmentVPAdapter(getActivity().getSupportFragmentManager(), (ArrayList<Fragment>) fragmentList);
                    viewPager.setOffscreenPageLimit(2);
//                    viewPager.setOffscreenPageLimit(1);
                    viewPager.setAdapter(fragmentVPAdapter);
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * 页面监听事件
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageSelected(int arg0) {// 页面选择响应函数
            // 如果需要实现页面滑动时动态添加 请在此判断arg0的值
            // 当然此方式在必须在初始化ViewPager的时候给的页数必须>2
            // 因为给1页的话 ViewPager是响应不了此函数的
            // 例：
            if (arg0 == viewPager.getAdapter().getCount() - 1) {// 滑动到最后一页

            }
//            if (mToast == null) {
//                mToast = Toast.makeText(context, "翻到了第" + (arg0 + 1) + "页", Toast.LENGTH_SHORT);
//            } else {
//                mToast.setText("翻到了第" + (arg0 + 1) + "页");
//            }
//            mToast.show();
            HorizontalScrollViewLayout(context, typelist, ll_fresh_tag, arg0);
            if (arg0 > 4) { //标签滚动
                hs_fresh_tag.scrollTo((displayMetrics.widthPixels / 5) * (arg0 - 2), 0);
            } else {
                hs_fresh_tag.scrollTo(0, 0);
            }

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。
        }

        public void onPageScrollStateChanged(int arg0) {// 滑动状态改变
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        fresh_view = inflater.inflate(R.layout.fg_freshsupermarket2, container, false);
        ButterKnife.bind(this, fresh_view);
        return fresh_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lay_search.setOnClickListener(this);

        inflater = getActivity().getLayoutInflater();
        choiceness_view = inflater.inflate(R.layout.fg_choiceness, null); //精选
        typelist = new ArrayList<>();
        alltaglist = new ArrayList<>();

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        viewPager = (ViewPager) view.findViewById(R.id.fresh_pager);// 找到ViewPager
        viewPager.setOnPageChangeListener(pageChangeListener);// 设置页面滑动监听

        request_freshType();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ActivityUtils.isNetworkAvailable()) {
            lay_hs_fresh_tag.setVisibility(View.VISIBLE);
            lay_no_network.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
        } else {
            lay_hs_fresh_tag.setVisibility(View.GONE);
            lay_no_network.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
        }
    }

    /**
     * 动态添加标签
     */
    public void HorizontalScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery, int index) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.lay_fresh_tag_text, lay_gallery, false);
                final RelativeLayout lay_resh_tag = (RelativeLayout) view.findViewById(R.id.lay_resh_tag);
                final TextView tv_fresh_tag = (TextView) view.findViewById(R.id.tv_fresh_tag);
                final ImageView iv_fresh_san = (ImageView) view.findViewById(R.id.iv_fresh_san);
                ViewGroup.LayoutParams p = lay_resh_tag.getLayoutParams();
                p.width = displayMetrics.widthPixels / 5;
//                tv_fresh_tag.setLayoutParams(p);  这句好像可有可无

                if (list != null && list.get(i).get("freshTypeName") != null && list.get(i).get("freshTypeName").toString().length() != 0 && !list.get(i).get("freshTypeName").toString().equals("")) {
                    tv_fresh_tag.setText(list.get(i).get("freshTypeName").toString());
                }
                iv_fresh_san.setVisibility(View.GONE);
                if (index == i) { //分类选择
                    tv_fresh_tag.setTextColor(getResources().getColor(R.color.module_green_color));
                    iv_fresh_san.setVisibility(View.VISIBLE);
                }
                final int j = i;
                lay_resh_tag.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list.get(j).get("freshTypeName") != null) {
                            viewPager.setCurrentItem(j);
                        }
                    }
                });

                lay_gallery.addView(view);
            }
        }
    }

    private void request_freshType() { //生鲜类型
        int communityID = RequestUtil.getcommunityid();
        String userID = RequestUtil.getuserid();
        //"freshTypeID": 1,"freshTypeName": "","freshTypeLeaf": 1,
        //freshTypeLeaf 下级归属 字段
        String json = "{\"freshType\": {\"communityID\":" + communityID + "}," +
                "\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\"," +
                "\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"FreshType\",\"actionName\": \"StructureQuery\"}";

//        String json = "{\"freshType\": {}," +
//                "\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\"," +
//                "\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
//                "\"controllerName\": \"FreshType\",\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json生鲜类型--------" + json);
        String url = HttpURL.HTTP_LOGIN_AREA + "/FreshTypeQuery/StructureQuery";
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("list_FreshType") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list_all = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("list_FreshType");
                    if (list_all != null && list_all.size() != 0) {
                        LinkedTreeMap<String, Object> map1 = new LinkedTreeMap<>();
                        map1.put("freshTypeID", "1");
                        map1.put("freshTypeName", getString(R.string.featured));//精选
//                                map1.put("freshTypeLeaf", "3");
                        map1.put("freshTypeLeaf", "1");
//                                map1.put("communityID","1");
                        typelist.add(map1);
                        for (int i = 0; i < list_all.size(); i++) {
                            //主类型
                            if (list_all.get(i).get("freshTypeID") != null && list_all.get(i).get("freshTypeLeaf") != null
                                    && list_all.get(i).get("freshTypeID").toString().equals(list_all.get(i).get("freshTypeLeaf").toString())) {
                                LinkedTreeMap<String, Object> map = list_all.get(i);
                                typelist.add(map);
                            }
                        }
                        //类型标签
                        for (int i = 0; i < typelist.size(); i++) {
                            ArrayList<LinkedTreeMap<String, Object>> taglist = new ArrayList<>(); //标签组
                            for (int j = 0; j < list_all.size(); j++) {
                                if (typelist.get(i).get("freshTypeLeaf").toString().equals(list_all.get(j).get("freshTypeLeaf").toString())
                                        && !list_all.get(j).get("freshTypeID").toString().equals(list_all.get(j).get("freshTypeLeaf").toString())) {
                                    LinkedTreeMap<String, Object> map = list_all.get(j); //标签对象
                                    taglist.add(map);
                                }
                            }
                            alltaglist.add(taglist);
                        }
                        Database.alltaglist = alltaglist;
                        HorizontalScrollViewLayout(context, typelist, ll_fresh_tag, 0);
                        fragmentList = new ArrayList<>(); //碎片链表

//                                FruitFragment 个数
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < typelist.size(); i++) {
                                    if (i == 0) {
                                        ChoicenessFragment choicenessFragment = new ChoicenessFragment().newInstance(typelist.get(i).get("freshTypeName").toString() + i, i); //添加精选
                                        fragmentList.add(choicenessFragment);
                                    } else {
                                        FruitFragment testFm = new FruitFragment().newInstance(typelist.get(i).get("freshTypeName").toString() + i, i);
                                        fragmentList.add(testFm);
                                    }
                                }
                            }
                        }).start();

//                                ChoicenessFragment choicenessFragment = new ChoicenessFragment().newInstance(typelist.get(0).get("freshTypeName").toString() + 0, 0); //添加精选
//                                fragmentList.add(choicenessFragment);

                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(HttpURL.HTTP_POST_FRESH_TYPE, json);
    }

    @Override
    public void onResume() {
        super.onResume();
//        offset_last = ll_fresh_tag.getWidth() -  (taglist.size() / 5) * displayMetrics.widthPixels; //总长度 - n个屏幕
//        offset_last = (taglist.size() % 5) * (displayMetrics.widthPixels / 5); //余数 * 单个长度
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lay_search:
                Intent intent = new Intent(context, SearchActivity.class);
                context.startActivity(intent);
                break;
        }
    }

}
