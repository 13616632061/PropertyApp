package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.fragment.shop.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/3.
 * 订单列表
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_ORDER,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class OrderListActivity extends BaseActivity {

    @BindView(R.id.order_list_tab)
    TabLayout orderListTab;
    @BindView(R.id.order_list_viewpage)
    ViewPager viewPager;


    OrderPageAdapter adapter;
    OrderListFragment orderAllFragment,orderPayFragment,orderSendFragment,orderGetFragment,orderCommentFragment;
    TabLayout.Tab order_All,wait_pay,wait_send,wait_get,wait_comment;
    List<OrderListFragment> fragments=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    @Override
    protected int getContentId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle("我的订单",false,null);
        order_All=orderListTab.newTab().setText("全部");
        wait_pay=orderListTab.newTab().setText("待付款");
        wait_send=orderListTab.newTab().setText("待发货");
        wait_get=orderListTab.newTab().setText("待收货");
        wait_comment=orderListTab.newTab().setText("待评价");



        orderAllFragment=new OrderListFragment();


        orderPayFragment=new OrderListFragment();


        orderSendFragment=new OrderListFragment();
        orderGetFragment=new OrderListFragment();
        orderCommentFragment=new OrderListFragment();


        Bundle bundleAll,bundlePay,bundleSend,bundleGet,bundleComment;

        bundleAll=new Bundle();//全部
        bundleAll.putInt("type",Constant.ORDER_STATUS.STATUS_PAY_ALL);

        bundlePay=new Bundle();//待支付
        bundlePay.putInt("type",Constant.ORDER_STATUS.STATUS_PAY_WAIT);
        bundleSend=new Bundle();//待发货
        bundleSend.putInt("type",Constant.ORDER_STATUS.STATUS_PAY_FINSH);
        bundleGet=new Bundle();//待收货
        bundleGet.putInt("type",Constant.ORDER_STATUS.STATUS_PAY_SEND);
        bundleComment=new Bundle();//待评论
        bundleComment.putInt("type",Constant.ORDER_STATUS.STATUS_PAY_GOODSRECEPIT);

        orderPayFragment.setArguments(bundlePay);
        orderSendFragment.setArguments(bundleSend);
        orderGetFragment.setArguments(bundleGet);
        orderCommentFragment.setArguments(bundleComment);
        orderAllFragment.setArguments(bundleAll);

        fragments.add(orderAllFragment);
        fragments.add(orderPayFragment);
        fragments.add(orderSendFragment);
        fragments.add(orderGetFragment);
        fragments.add(orderCommentFragment);

        titles.add("全部");titles.add("待付款");titles.add("待发货");titles.add("待收货");titles.add("待评价");
        adapter=new OrderPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        orderListTab.setupWithViewPager(viewPager);
    }


    @OnClick({R.id.iv_title_back,R.id.iv_title_text_left2})
    void onClickGroup(View view){
        switch (view.getId()){
            case R.id.iv_title_text_left2:
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

    class OrderPageAdapter extends FragmentPagerAdapter{

        public OrderPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
