package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.ui.adapter.KeyListAdapter;
import com.glory.bianyitong.view.ListViewDecoration;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/28.
 * 钥匙管理
 */
public class KeyManagerActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.key_recycler_view)
    SwipeMenuRecyclerView key_recycler_view;
    KeyListAdapter keyListAdapter;
    //    private List<LinkedTreeMap<String, Object>> list_door;
    private List<UserLockInfo.ListUserLockMappingBean> list_door;
    private ProgressDialog progressDialog = null;
    private List<UserLockInfo.ListUserLock> list_sort; //排序后的list
    private String userID = "";
    private boolean isChange = false;
    /**
     * 当Item移动的时候。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(list_door, fromPosition, toPosition);
            keyListAdapter.notifyItemMoved(fromPosition, toPosition);
            Collections.swap(list_sort, fromPosition, toPosition);

            sort();
//            Toast.makeText(KeyManagerActivity.this, "排序中----" + toPosition, Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            list_door.remove(position);
            keyListAdapter.notifyItemRemoved(position);
//            Toast.makeText(KeyManagerActivity.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
            Toast.makeText(KeyManagerActivity.this, getString(R.string.now_the_first) + position + getString(R.string.the_article_is_deleted), Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * Item的滑动状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(final RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == ACTION_STATE_DRAG) {
                viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.bg_blue));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //z方向
                    viewHolder.itemView.setTranslationZ(13);
                }
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//获取系统振动
                vibrator.vibrate(100);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.all_background)); //
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //z方向
                            viewHolder.itemView.setTranslationZ(0);
                        }
                    }
                }, 1200);
            } else if (actionState == ACTION_STATE_SWIPE) {
            } else if (actionState == ACTION_STATE_IDLE) {}
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
//            Toast.makeText(KeyManagerActivity.this, "我现在是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.ac_key_manager;
    }

    @Override
    protected void init() {
        super.init();
//        inintTitle("钥匙管理", false, "完成");
        inintTitle(getString(R.string.key_management), true, "");//钥匙管理
        left_return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isChange) {
//                } else {
                    KeyManagerActivity.this.finish();
//                }
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//完成
            @Override
            public void onClick(View v) {
                if (isChange) {
                } else {
                    KeyManagerActivity.this.finish();
                }
            }
        });
//        if (Database.USER_MAP != null && Database.USER_MAP.get("userID") != null) {
//            userID = Database.USER_MAP.get("userID").toString();
//        }
        userID = RequestUtil.getuserid();
        key_recycler_view.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        key_recycler_view.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        key_recycler_view.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        key_recycler_view.addItemDecoration(new ListViewDecoration());// 添加分割线。

        // 这个就不用添加菜单啦，因为滑动删除和菜单是冲突的。

        key_recycler_view.setLongPressDragEnabled(true);
        key_recycler_view.setItemViewSwipeEnabled(false);// 开启滑动删除。
        key_recycler_view.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI。
        key_recycler_view.setOnItemStateChangedListener(mOnItemStateChangedListener);
        request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isChange)
        save();
    }

    private void request() { //钥匙查询
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("userLockMapping",new Object());
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

                UserLockInfo uinfo = new Gson().fromJson(s, UserLockInfo.class);
                if(uinfo.getStatusCode()==1){
                    list_door = uinfo.getListUserLockMapping();
                    if (list_door != null && list_door.size() > 0) {
                        keyListAdapter = new KeyListAdapter(list_door);
                        keyListAdapter.setOnItemClickListener(onItemClickListener);
                        key_recycler_view.setAdapter(keyListAdapter);

                        list_sort = new ArrayList<>(); //要排序的集合
                        for (int i = 0; i < list_door.size(); i++) {
                            if (list_door.get(i) != null) {
//                                HashMap<String, Object> map = new HashMap<>();
//                                map.put("userLockID", list_door.get(i).getUserLockID());
//                                map.put("lockSort", list_door.get(i).getLockSort());
                                UserLockInfo.ListUserLock userLockMappingBean=new UserLockInfo.ListUserLock(list_door.get(i).getUserLockID(),list_door.get(i).getLockSort());
                                list_sort.add(userLockMappingBean);
                            }
                        }
                    }
                }else {
                    showShort(uinfo.getAlertMessage());
                }

            }
            @Override
            public void onError() {
                showShort(getString(R.string.system_error));
            }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(KeyManagerActivity.this, "", getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }

            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_LOCAL_KEY_MANAGER, json);
        }catch (Exception e){

        }
    }

    private void save() { //保存 钥匙排序
        try {


        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("listUserLock",list_sort);
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                if(bean.getStatusCode()==1){
                    EventBus.getDefault().post(true);
                    finish();
                }
            }

            @Override
            public void onError() {
                showShort(getString(R.string.system_error));
            }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(this,HttpURL.HTTP_POST_LOCAL_KEY_SORT, json);
        }catch (Exception e){

        }
    }

    private void sort() { //排序
        isChange = true;
        for (int i = 0; i < list_sort.size(); i++) {
            list_sort.get(i).setLockSort(i);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
