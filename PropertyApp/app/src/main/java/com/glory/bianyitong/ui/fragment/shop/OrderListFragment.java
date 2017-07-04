package com.glory.bianyitong.ui.fragment.shop;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.fragment.RootFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/3.
 */

public class OrderListFragment extends RootFragment {


    @BindView(R.id.order_list_fr_recycle)
    RecyclerView orderListFrRecycle;
    @BindView(R.id.order_list_fr_refresh)
    SwipeRefreshLayout orderListFrRefresh;

    @Override
    protected void initView() {

    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_orderlist;
    }

    @Override
    protected void initData() {

    }




}
