package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenenyu.router.Router;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.router.RouterMapping;
import com.glory.bianyitong.ui.activity.ImagePagerActivity;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.view.MyGridView;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.activity.DynamicDetailsActivity;
import com.glory.bianyitong.ui.activity.PersonalHomePageActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/10.
 * 近邻
 */
public class NeighbourAdapter extends BaseAdapter {
    private Context context;

    private  List<ResponseFriendDetail.ListNeighborhoodBean> list;

    private String from = "";

    private LayoutInflater mInflater = null;

    private Handler mhandler;

    public NeighbourAdapter(Context context, List<ResponseFriendDetail.ListNeighborhoodBean> list, String from) {
        this.context = context;
        this.list = list;
        this.from = from;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public NeighbourAdapter(Context context,  List<ResponseFriendDetail.ListNeighborhoodBean> list, String from, Handler mhandler) {
        this.context = context;
        this.list = list;
        this.from = from;
        this.mhandler = mhandler;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.view_item_neighbour, null);

            holder.item_user_info_lay = (LinearLayout) convertView.findViewById(R.id.item_user_info_lay);//
            holder.item_near_content_lay = (LinearLayout) convertView.findViewById(R.id.item_near_content_lay);//布局
            holder.item_near_head_pic = (CircleImageView) convertView.findViewById(R.id.item_near_head_pic); //头像
            holder.tv_near_userName = (TextView) convertView.findViewById(R.id.tv_near_userName);
            holder.tv_near_text = (TextView) convertView.findViewById(R.id.tv_near_text);

//            holder.near_image_one = (ImageView) convertView.findViewById(R.id.near_image_one);
//            holder.near_pic_lay2 = (LinearLayout) convertView.findViewById(R.id.near_pic_lay2);
//            holder.near_image_two1 = (ImageView) convertView.findViewById(R.id.near_image_two1);
//            holder.near_image_two2 = (ImageView) convertView.findViewById(R.id.near_image_two2);
//            holder.near_pic_lay3 = (LinearLayout) convertView.findViewById(R.id.near_pic_lay3);
//            holder.near_image_three1 = (ImageView) convertView.findViewById(R.id.near_image_three1);
//            holder.near_image_three2 = (ImageView) convertView.findViewById(R.id.near_image_three2);
//            holder.near_image_three3 = (ImageView) convertView.findViewById(R.id.near_image_three3);
            holder.gv_dynamic_pic2 = (MyGridView) convertView.findViewById(R.id.gv_dynamic_pic2);
            holder.near_pic_list_lay = (LinearLayout) convertView.findViewById(R.id.near_pic_list_lay);

            holder.tv_near_praise = (TextView) convertView.findViewById(R.id.tv_near_praise);
            holder.tv_near_comment = (TextView) convertView.findViewById(R.id.tv_near_comment);

            holder.lay_iv_mynews_more = (RelativeLayout) convertView.findViewById(R.id.lay_iv_mynews_more);  //更多
            holder.iv_icon_yellow = (ImageView) convertView.findViewById(R.id.iv_icon_yellow);  //黄条
            holder.tv_mynews_time = (TextView) convertView.findViewById(R.id.tv_mynews_time);  //发布时间

            holder.item_lay_like = (LinearLayout) convertView.findViewById(R.id.item_lay_like);
            holder.item_lay_comment = (LinearLayout) convertView.findViewById(R.id.item_lay_comment);
            holder.view_neighbor_line = (TextView) convertView.findViewById(R.id.view_neighbor_line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (from.equals("my")) { //我的发布
            holder.lay_iv_mynews_more.setVisibility(View.VISIBLE);
            holder.iv_icon_yellow.setVisibility(View.VISIBLE);
            holder.tv_mynews_time.setVisibility(View.VISIBLE);

            holder.item_near_head_pic.setVisibility(View.GONE);
            holder.tv_near_userName.setVisibility(View.GONE);
        } else if (from.equals("near")) {//近邻
            holder.item_near_head_pic.setVisibility(View.VISIBLE);
            holder.tv_near_userName.setVisibility(View.VISIBLE);

            holder.lay_iv_mynews_more.setVisibility(View.GONE);
            holder.iv_icon_yellow.setVisibility(View.GONE);
            holder.tv_mynews_time.setVisibility(View.GONE);
        } else if (from.equals("personal")) { //个人中心
            holder.lay_iv_mynews_more.setVisibility(View.GONE);
            holder.iv_icon_yellow.setVisibility(View.VISIBLE);
            holder.tv_mynews_time.setVisibility(View.VISIBLE);

            holder.item_near_head_pic.setVisibility(View.GONE);
            holder.tv_near_userName.setVisibility(View.GONE);
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
            if (position == list.size() - 1) {
                holder.view_neighbor_line.setVisibility(View.GONE);
            }
            String pic = list.get(position).getUserPhoto().toString();//  用户名
            Glide.with(context).load(pic).error(R.drawable.wait).placeholder(R.drawable.wait).into(holder.item_near_head_pic);
            holder.tv_near_userName.setText(list.get(position).getUserName());//  用户名
            String date = DateUtil.format(DateUtil.parse(list.get(position).getDatetime(),DateUtil.DEFAULT_PATTERN));
            holder.tv_mynews_time.setText(date);//  发布时间
            holder.tv_near_text.setText(list.get(position).getNeighborhoodContent());//  文字内容
            holder.tv_near_praise.setText(list.get(position).getLikeCount()+ "");//  赞
            holder.tv_near_comment.setText(list.get(position).getCommentCount()+"");//  评论

            if (list.get(position).getListNeighborhoodPic() != null) {// 图片组
                List<ResponseFriendDetail.ListNeighborhoodBean.ListNeighborhoodPicBean> pics = list.get(position).getListNeighborhoodPic();
                if (pics != null && pics.size() > 0) {
                    holder.gv_dynamic_pic2.setVisibility(View.VISIBLE);
                    DynamicPicsAdapter picsAdapter = new DynamicPicsAdapter(context, pics);
                    holder.gv_dynamic_pic2.setAdapter(picsAdapter);
//                    final ArrayList<String> pictureList = new ArrayList<>();
//                    for (int i = 0; i < pics.size(); i++) {
//                        String str_pic = pics.get(i).get("picturePath").toString();
//                        pictureList.add(str_pic);
//                    }
//                    final ArrayList<String> pictureList2 = pictureList;
                    final int j = position;
                    holder.gv_dynamic_pic2.setOnItemClickListener(new AdapterView.OnItemClickListener() { //看大图
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Intent intent = new Intent();
//                            intent.setClass(context, ImagePagerActivity.class);
//                            intent.putExtra("pictureList", pictureList2);
//                            context.startActivity(intent);
                            Router.build(RouterMapping.ROUTER_ACTIVITY_FRIEND_DETAIL)
                                    .with("neighborhoodID",list.get(j).getNeighborhoodID())
                                    .go(context);
//                            Intent intent = new Intent(context, DynamicDetailsActivity.class);
//                            intent.putExtra("neighborhoodID", Double.valueOf(list.get(j).get("neighborhoodID").toString()).intValue());
//                            context.startActivity(intent);
                        }
                    });
                } else {
                    //没图片
                    holder.gv_dynamic_pic2.setVisibility(View.GONE);
                }
//                if (pics.size() == 1) {
//                    holder.near_image_one.setVisibility(View.VISIBLE);
//                    holder.near_pic_lay2.setVisibility(View.GONE);
//                    holder.near_pic_lay3.setVisibility(View.GONE);
//                    if (pics.get(0) != null && pics.get(0).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(0).get("picturePath").toString(), holder.near_image_one, null);
//                    }
//                } else if (pics.size() == 2) {
//                    holder.near_image_one.setVisibility(View.GONE);
//                    holder.near_pic_lay2.setVisibility(View.VISIBLE);
//                    holder.near_pic_lay3.setVisibility(View.GONE);
//                    if (pics.get(0) != null && pics.get(0).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(0).get("picturePath").toString(), holder.near_image_two1, null);
//                    }
//                    if (pics.get(1) != null && pics.get(1).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(1).get("picturePath").toString(), holder.near_image_two2, null);
//                    }
//
//                } else if (pics.size() >= 3) {
//                    holder.near_image_one.setVisibility(View.GONE);
//                    holder.near_pic_lay2.setVisibility(View.GONE);
//                    holder.near_pic_lay3.setVisibility(View.VISIBLE);
//                    if (pics.get(0) != null && pics.get(0).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(0).get("picturePath").toString(), holder.near_image_three1, null);
//                    }
//                    if (pics.get(1) != null && pics.get(1).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(1).get("picturePath").toString(), holder.near_image_three2, null);
//                    }
//                    if (pics.get(2) != null && pics.get(2).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(2).get("picturePath").toString(), holder.near_image_three3, null);
//                    }
//                }
            } else {
                //没图片
                holder.gv_dynamic_pic2.setVisibility(View.GONE);
            }
        }

        holder.item_near_content_lay.setOnClickListener(new View.OnClickListener() {//动态详情
            @Override
            public void onClick(View v) {
                Router.build(RouterMapping.ROUTER_ACTIVITY_FRIEND_DETAIL)
                        .with("neighborhoodID",list.get(position).getNeighborhoodID())
                        .go(context);
//                Intent intent = new Intent(context, DynamicDetailsActivity.class);
//                intent.putExtra("neighborhoodID", Double.valueOf(list.get(position).get("neighborhoodID").toString()).intValue());
//                context.startActivity(intent);
            }
        });

        holder.item_user_info_lay.setOnClickListener(new View.OnClickListener() {//个人主页
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonalHomePageActivity.class);
                if (list.get(position).getAesUserID() != null) {
                    intent.putExtra("userID", list.get(position).getAesUserID());
                } else {
                    intent.putExtra("userID", 0);
                }
                context.startActivity(intent);
            }
        });

        //-----------------------------------------我的发布----------------------------------------
        holder.lay_iv_mynews_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //调出对话框
                Message msg = new Message();
                msg.arg1 = position;
                msg.what = 0;
                mhandler.sendMessage(msg);
            }
        });

//        ServiceDialog.setPicture(HttpURL.HTTP_LOGIN2 + url_pic, holder.pic_goods, null);

//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {
        public LinearLayout item_user_info_lay;//用户部分
        public LinearLayout item_near_content_lay; //布局
        public CircleImageView item_near_head_pic;  // 头像
        public TextView tv_near_userName; // 用户名称
        public TextView tv_near_text;  // 文字内容

        //        public ImageView near_image_one; //图片组
//        public LinearLayout near_pic_lay2;
//        public ImageView near_image_two1;
//        public ImageView near_image_two2;
//        public LinearLayout near_pic_lay3;
//        public ImageView near_image_three1;
//        public ImageView near_image_three2;
//        public ImageView near_image_three3;
        public MyGridView gv_dynamic_pic2;
        public LinearLayout near_pic_list_lay;//图片

        public TextView tv_near_praise;// 点赞数
        public TextView tv_near_comment;// 评论数

        public RelativeLayout lay_iv_mynews_more; //更多
        public ImageView iv_icon_yellow; //黄条
        public TextView tv_mynews_time;  //发布时间

        public LinearLayout item_lay_comment;//评论
        public LinearLayout item_lay_like; //点赞

        public TextView view_neighbor_line;
    }

}
