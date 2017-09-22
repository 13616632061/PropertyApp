package com.glory.bianyitong.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.github.lazylibrary.util.StringUtils;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.entity.request.RequestQueryCouponList;
import com.glory.bianyitong.bean.entity.response.ResponseQueryCouponList;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.fragment.RootFragment;
import com.glory.bianyitong.ui.fragment.shop.CouponListFragment;
import com.google.gson.Gson;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        requestCouponList();
    }

    private void requestCouponList(){
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {


            @Override
            public void onSuccess(String s) {
                int notuse=0,inuse=0,overdue=0;
                if(!TextUtils.isEmpty(s)){
                    couponList=new Gson().fromJson(s,ResponseQueryCouponList.class);
                    if (couponList.getStatusCode()==1){
                        for (ResponseQueryCouponList.ListCouponReceiveBean bean:couponList.getListCouponReceive()){

                            Date beginDate = strToDateLong(bean.getBeginDate().replace("T"," "));
                            Date endDate = strToDateLong(bean.getEndDate().replace("T"," "));
                            Date nowDate = strToDateLong(bean.getDateNow().replace("T"," "));
                            if (bean.getCouponStatus()==1){// 0未使用 1已使用
                                inuse++;
                            }else if (bean.getCouponStatus()==0&&belongCalendar(nowDate,beginDate,endDate)){
                                notuse++;
                            }else {
                                overdue++;
                            }
                        }
                        Log.i("useed",inuse+"--"+notuse+"--"+overdue);
                        title[0] = "未使用(" +notuse + ")";
                        title[1] = "已使用(" +inuse + ")";
                        title[2] = "过期(" + overdue+ ")";
                        initView();
                    }
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
        }).getEntityData(this, HttpURL.HTTP_POST_COUPON_QUERY_LIST,json);

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

    /**
     * 判断time是否在from，to之内
     *
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
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
