package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.fragment.RootFragment;
import com.glory.bianyitong.ui.fragment.shop.CouponListFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/14.
 * 优惠券列表
 */

@Route(value = RouterMapping.ROUTER_ACTIVITY_COUPON_LIST,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class CouponListActivity extends BaseActivity {
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
    @BindView(R.id.coupon_list_tabmenu)
    TabLayout couponListTabmenu;

    @BindView(R.id.coupon_list_viewpage)
    ViewPager viewPager;

    FragmentStatePagerAdapter adapter;
    @Override
    protected int getContentId() {
        return R.layout.activity_coupon_list;
    }

    // TODO: 2017/7/14 来源 1:我的优惠券 2:可用优惠券(提交订单页面)
    @InjectParam(key = "source")
    int source=1;

    @InjectParam(key = "formValue")
    String jsonValue;

    private ResponseQueryCouponList couponList;

    private List<CouponListFragment> listFragments=new ArrayList<>();
    private String[] title={"未使用","已使用","过期"};


    @Override
    protected void init() {
        super.init();
        inintTitle("优惠券", false, "");
        Router.injectParams(this);
        beforInitData();
        initView();
    }

    private void beforInitData() {
        if(!TextUtils.isEmpty(jsonValue)){
            couponList=new Gson().fromJson(jsonValue,ResponseQueryCouponList.class);
            title[0]="未使用("+couponList.getCouponReceive().getNotused()+")";
            title[1]="已使用("+couponList.getCouponReceive().getUsed()+")";
            title[2]="过期("+couponList.getCouponReceive().getTimeout()+")";
        }
    }

    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickBtn(View view ){
        switch (view.getId()){
            case R.id.iv_title_back:
            case R.id.iv_title_text_left2:
                finish();
                break;
        }
    }

    private void initView() {
        for (int i=0;i<title.length;i++){
            TabLayout.Tab tab=couponListTabmenu.newTab().setText(title[i]);
            couponListTabmenu.addTab(tab);
            CouponListFragment listFragment=new CouponListFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("source" ,source);
            bundle.putInt("type" ,i);
            bundle.putInt("num" ,1);
            bundle.putString("coupon",jsonValue);
            if(i==2)
                bundle.putInt("type" ,-1);
            listFragment.setArguments(bundle);
            listFragments.add(listFragment);
        }

        couponListTabmenu.setSelected(true);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        couponListTabmenu.setupWithViewPager(viewPager);

    }

    class MyPageAdapter extends FragmentStatePagerAdapter{

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
