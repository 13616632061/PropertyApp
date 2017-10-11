package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.GetOrderCommitAddress;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.exception.MyApplication;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.MenuViewTypeAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.TextUtil;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.ListViewDecoration;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 授权管理
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_AAWARD_MANAGER,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AwardManagerActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.tv_add_award)
    TextView tv_add_award;
    @BindView(R.id.lay_no_list)
    LinearLayout lay_no_list;
    //    @BindView(R.id.list_people)
//    ListView list_people;
//  private AwardPeopleAdapter awardPeopleAdapter;
    @BindView(R.id.lay_award_list)
    LinearLayout lay_award_list;
    @BindView(R.id.list_people)
    SwipeMenuRecyclerView list_people;
    private ProgressDialog progressDialog = null;
    private MenuViewTypeAdapter awardPeopleAdapter;
    private List<UserLockInfo.ListUserLockMappingBean> list_man=new ArrayList<>();
    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);
            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE) {// 需要添加单个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(AwardManagerActivity.this)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setText(getString(R.string.delete))//删除
                        .setTextColor(getResources().getColor(R.color.white))
                        .setTextSize(15)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(wechatItem);
                swipeRightMenu.addMenuItem(wechatItem);
            }
        }
    };
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Database.awardpeople = list_man.get(position);
            Router.build(RouterMapping.ROUTER_ACTIVITY_AddAWARD)
                    .with("from","edit")
                    .with("aESUserID",list_man.get(position).getaESUserID())
                    .with("authorizationUserID", list_man.get(position).getAuthorizationUserID())
                    .go(AwardManagerActivity.this);
        }
    };
    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                    final int index = adapterPosition;
                    AlertDialog.Builder builder = new AlertDialog.Builder(AwardManagerActivity.this);
                    builder.setMessage(getString(R.string.whether_to_delete_the_donor));//是否删除授权人
                    builder.setTitle(getString(R.string.prompt));//提示
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {//删除
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete(index);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {//取消
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.ac_award_manager;
    }

    @Override
    protected void init() {
        super.init();
        //初始化标题栏
        inintTitle(getString(R.string.authorized_management), true, "");//授权管理
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                AwardManagerActivity.this.finish();
            }
        });
        tv_add_award.setOnClickListener(new View.OnClickListener() { //添加授权
            @Override
            public void onClick(View view) {
                Router.build(RouterMapping.ROUTER_ACTIVITY_AddAWARD)
                        .with("from","add")
                        .with("authorizationUserID",0)
                        .go(AwardManagerActivity.this);

            }
        });

        list_people.setLayoutManager(new LinearLayoutManager(this));
        list_people.setHasFixedSize(true);
        list_people.setItemAnimator(new DefaultItemAnimator());
        list_people.addItemDecoration(new ListViewDecoration());
        list_people.setSwipeMenuCreator(swipeMenuCreator);
        list_people.setSwipeMenuItemClickListener(menuItemClickListener);

        awardPeopleAdapter = new MenuViewTypeAdapter(list_man);
        awardPeopleAdapter.setOnItemClickListener(onItemClickListener);
        list_people.setAdapter(awardPeopleAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Database.isAddComment) {
            Database.isAddComment = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void request() { //获取授权人
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userLockMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

                if(TextUtil.isEmpty(s)){
                    ToastUtils.showShort(AwardManagerActivity.this,"获取授权人失败");
                    return;
                }
                UserLockInfo baseResponseBean=new Gson().fromJson(s,UserLockInfo.class);
                if(baseResponseBean.getStatusCode()==-105){
                    Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN)
                            .go(AwardManagerActivity.this);
                    return;
                }else if(baseResponseBean.getStatusCode()==1){

                    list_man.clear();
                    list_man.addAll(baseResponseBean.getListUserLockMapping());
                    awardPeopleAdapter.notifyDataSetChanged();
                }else {
                    lay_award_list.setVisibility(View.VISIBLE);
                    lay_no_list.setVisibility(View.GONE);
                }
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }

            }
            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AwardManagerActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_LOCKMAPPING_QUERY,jsons);
        }catch (Exception e){

        }
    }

    /**
     * 删除授权
     * @param position
     */
    private void delete( final int position) {//
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        Map<String,Object> maps=new HashMap<>();
        maps.put("aESUserID",list_man.get(position).getaESUserID());
        map.put("userLockMapping",maps);
        String json = new Gson().toJson(map);

        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                GetOrderCommitAddress bean=new Gson().fromJson(s,GetOrderCommitAddress.class);
                if (bean.getStatusCode()==1) {
                    list_man.remove(position);
                    awardPeopleAdapter.notifyDataSetChanged();
                    ToastUtils.showToast(AwardManagerActivity.this, getString(R.string.successfully_deleted));//删除成功
                } else {
                    ToastUtils.showToast(AwardManagerActivity.this, getString(R.string.failed_to_delete));//删除失败
                }
            }

            @Override
            public void onError() { }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AwardManagerActivity.this, "", getString(R.string.delete), true);//删除..
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_LOCKMAPPING_DELETE,json);
        }catch (Exception e){

        }
    }

}
