package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.fragment.shop.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    class OrderPageAdapter extends FragmentStatePagerAdapter{

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
