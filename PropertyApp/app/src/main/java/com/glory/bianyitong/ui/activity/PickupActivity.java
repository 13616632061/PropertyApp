package com.glory.bianyitong.ui.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.PickupAdapter;
import com.glory.bianyitong.view.NewPullToRefreshView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lucy on 2017/5/3.
 * 取件
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_PICKUP,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class PickupActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.base_pullToRefreshView)
    NewPullToRefreshView base_pullToRefreshView;

    @BindView(R.id.base_listView)
    ListView base_listView;
    PickupAdapter pickupAdapter;


    @Override
    protected int getContentId() {
        return R.layout.lay_listview;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.pickup), true, ""); //取件
        left_return_btn.setOnClickListener(this);
        ArrayList<LinkedTreeMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
            map.put("name", "快件" + i);
            map.put("address", "南山区" + i);
            list.add(map);
        }

        pickupAdapter = new PickupAdapter(PickupActivity.this, list);
        base_listView.setAdapter(pickupAdapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                PickupActivity.this.finish();
                break;
        }
    }
}
