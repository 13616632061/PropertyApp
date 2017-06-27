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
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.adapter.MenuViewTypeAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
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
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 授权管理
 */
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
    private ArrayList<LinkedTreeMap<String, Object>> list_man;
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
            Intent intent = new Intent(AwardManagerActivity.this, AddAwardActivity.class);
            intent.putExtra("from", "edit");
            if (list_man.get(position).get("authorizationUserID") != null) {
                intent.putExtra("authorizationUserID", Double.valueOf(list_man.get(position).get("authorizationUserID").toString()).intValue());
            } else {
                intent.putExtra("authorizationUserID", 0);
            }
            startActivity(intent);
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
                if (list_man.get(adapterPosition).get("authorizationUserID") != null) {
                    final int index = adapterPosition;
                    AlertDialog.Builder builder = new AlertDialog.Builder(AwardManagerActivity.this);
                    builder.setMessage(getString(R.string.whether_to_delete_the_donor));//是否删除授权人
                    builder.setTitle(getString(R.string.prompt));//提示
                    builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {//删除
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete(Double.valueOf(list_man.get(index).get("authorizationUserID").toString()).intValue(), index);
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
                }
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
//                Intent intent = new Intent(AwardManagerActivity.this, AddAwardActivity.class);
//                intent.putExtra("from", "add");
//                intent.putExtra("authorizationUserID", 0);
//                startActivity(intent);
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
        request();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Database.isAddComment) {
            request();
            Database.isAddComment = false;
        }
    }

    private void request() { //获取授权人

        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userLockMapping",new Object());
        String jsons=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("listUserLock") != null) {
                    list_man = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listUserLock");
                    if (list_man != null && list_man.size() > 0) {
//                                awardPeopleAdapter = new AwardPeopleAdapter(AwardManagerActivity.this, list);
//                                list_people.setAdapter(awardPeopleAdapter);

                        awardPeopleAdapter = new MenuViewTypeAdapter(list_man);
                        awardPeopleAdapter.setOnItemClickListener(onItemClickListener);
                        list_people.setAdapter(awardPeopleAdapter);
                    } else {
                        lay_award_list.setVisibility(View.VISIBLE);
                        lay_no_list.setVisibility(View.GONE);
                    }
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
        }).getEntityData("/ApiUserLockMapping/Query",jsons);
    }

    //保存
    private void delete(int authorizationUserID, final int position) {//
        String userID = RequestUtil.getuserid();
        int communityID = RequestUtil.getcommunityid();

        String json = "{\"userLock\":{\"authorizationUserID\":" + authorizationUserID + ",\"communityID\":" + communityID + "},\"controllerName\":\"UserLockMapping\"," +
                "\"actionName\":\"Delete\",\"userID\":\"" + userID + "\"}";

        Log.i("resultString", "json------------" + json);
        String url = HttpURL.HTTP_LOGIN_AREA + "/UserLockMapping/Delete";
//        OkGo.post(url) //删除
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
//                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
//                            list_man.remove(position);
//                            awardPeopleAdapter.notifyDataSetChanged();
//                            ToastUtils.showToast(AwardManagerActivity.this, getString(R.string.successfully_deleted));//删除成功
//                        } else {
//                            ToastUtils.showToast(AwardManagerActivity.this, getString(R.string.failed_to_delete));//删除失败
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ServiceDialog.showRequestFailed();
//                    }
//
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                        progressDialog = ProgressDialog.show(AwardManagerActivity.this, "", getString(R.string.delete), true);//删除..
//                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
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
        }).getEntityData(url,json);
    }

}
