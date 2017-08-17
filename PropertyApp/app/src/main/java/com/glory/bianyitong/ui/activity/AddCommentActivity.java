package com.glory.bianyitong.ui.activity;


import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.glory.bianyitong.bean.BaseRequestBean;
import com.glory.bianyitong.bean.BaseResponseBean;
import com.glory.bianyitong.bean.entity.request.RequestNeighborhoodComment;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.router.RouterMapping;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.ContainsEmojiEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 我来(添加)评论
 */
@Route(value = RouterMapping.ROUTER_ACTIVITY_FRIEND_COMMENT,interceptors = RouterMapping.INTERCEPTOR_LOGIN)
public class AddCommentActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.iv_title_text_left)
    TextView iv_title_text_left;

    @BindView(R.id.et_comment_txt)
    ContainsEmojiEditText et_comment_txt;


    private ProgressDialog progressDialog = null;
    @InjectParam(key = "neighborhoodID")
     int neighborhoodid; //近邻 id
    @InjectParam(key = "CommentToID")
     int CommentToID; //近邻 评论 id
    @InjectParam(key = "commentToUserID")
     int commentToUserID; //被评论 评论的用户id
    @InjectParam(key = "neighborhoodID")
     String commentToUserName ; //被评论的用户名

    @Override
    protected int getContentId() {
        return R.layout.ac_addcomment;
    }

    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        //初始化标题栏
        inintTitle(getResources().getString(R.string.neighborhood_comments), false, getResources().getString(R.string.release)); //近邻评论  发布
        left_return_btn.setVisibility(View.GONE);
        iv_title_text_left.setVisibility(View.VISIBLE);
        iv_title_text_left.setText(getResources().getString(R.string.cancel));//取消
        iv_title_text_left.setOnClickListener(new View.OnClickListener() { //取消
            @Override
            public void onClick(View view) {
                AddCommentActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//发布
            @Override
            public void onClick(View v) {
                String desc = et_comment_txt.getText().toString();
                if (desc.equals("")) {
                    ToastUtils.showToast(AddCommentActivity.this, getResources().getString(R.string.comments_can_not_be_empty));//评论不能为空
                } else {
                    request(desc);
                }
            }
        });
    }

    private void request(String txt) { //评论
        Map<String,Object> map=new BaseRequestBean().getBaseRequest();
        map.put("neighborhoodComment",new RequestNeighborhoodComment(neighborhoodid,Database.USER_MAP.getCustomerPhoto(),txt,CommentToID,commentToUserName));
        String json=new Gson().toJson(map);
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {

                BaseResponseBean bean=new Gson().fromJson(s,BaseResponseBean.class);
                showShort(bean.getAlertMessage());
                if(bean.getStatusCode()==1){
                    Database.isAddComment = true;
                    AddCommentActivity.this.finish();
                    EventBus.getDefault().post("addComment");
                }

            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AddCommentActivity.this, "", getResources().getString(R.string.release), true);
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(this,HttpURL.HTTP_POST_FRIEND_COMMENT_ADD, json);
    }

}
