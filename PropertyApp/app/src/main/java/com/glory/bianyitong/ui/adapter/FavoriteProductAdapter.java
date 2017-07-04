package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.BulletinDetailsActivity;
import com.glory.bianyitong.util.ACache;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lucy on 2016/11/11.
 * 收藏商品
 */
public class FavoriteProductAdapter extends BaseAdapter {
    public static HashMap<Integer, Boolean> checkList;//这是头部那个chexkbox
    private Context context;
//    private ArrayList<LinkedTreeMap<String, Object>> qiList;
    private List<listCommunityBulletinInfo.ListCommunityBulletinBean> qiList;
    //    public static HashMap<Integer, Boolean> checkimglist;//这是显示那个读不读的 我用一个button表示
    private boolean isDoMore; //是否进行编辑默认为false

    public FavoriteProductAdapter(Context context, List<listCommunityBulletinInfo.ListCommunityBulletinBean> qiList, HashMap<Integer, Boolean> checkList,
                                  boolean isDoMore) { //ArrayList<LinkedTreeMap<String, Object>>
//        public CommunityAnnouceAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> qiList, HashMap<Integer, Boolean> checkList,
//                HashMap<Integer, Boolean> checkimglist, boolean isDoMore) {
        this.context = context;
        this.qiList = qiList;
        this.checkList = checkList;
//        this.checkimglist = checkimglist;
        this.isDoMore = isDoMore;
        initData();
    }

    //暴露给外面使用这是checkbox
    public static HashMap<Integer, Boolean> getIsCheck() {
        return FavoriteProductAdapter.checkList;
    }

    public static void setIsCheck(HashMap<Integer, Boolean> isCheck) {
        FavoriteProductAdapter.checkList = isCheck;
    }

    public void setIsDoMore(Boolean isDoMore) {
        this.isDoMore = isDoMore;
    }

    public void updateList(List<listCommunityBulletinInfo.ListCommunityBulletinBean> qiList) {//ArrayList<LinkedTreeMap<String, Object>>
        this.qiList = qiList;
    }

//    public void upDateButtonList(HashMap<Integer, Boolean> list) {
//        checkimglist = new HashMap<Integer, Boolean>();
//        Log.i("ppppp" + checkimglist.size(), "ddddd" + list.size());
//        checkimglist = list;
//        Log.i("ppppp" + checkimglist.size(), "ddddd" + list.size());
//    }
    public void upDateCheckList() {
        checkList.clear();
        for (int i = 0; i < qiList.size(); i++) {
            checkList.put(i, false);

        }
    }

    private void initData() {
        for (int i = 0; i < qiList.size(); i++) {
            //全部设为false 表示未点击和未读
            checkList.put(i, false);
//            checkimglist.put(i, true);
        }
    }

    @Override
    public int getCount() {
        return qiList.size();
    }

    @Override
    public Object getItem(int i) {
        return qiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_favorite_product, parent, false);
            holder.item_favorite_product = (LinearLayout) convertView.findViewById(R.id.item_favorite_product);
            holder.iv_fp_pic = (ImageView) convertView.findViewById(R.id.iv_fp_pic);
            holder.item_fp_checkbox = (CheckBox) convertView.findViewById(R.id.item_fp_checkbox);
            holder.tv_fp_name = (TextView) convertView.findViewById(R.id.tv_fp_name);
            holder.tv_fp_price = (TextView) convertView.findViewById(R.id.tv_fp_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }  //是否编辑，编辑就显示checkbox
        if (!isDoMore) {
            holder.item_fp_checkbox.setVisibility(View.GONE);
        } else {
            holder.item_fp_checkbox.setVisibility(View.VISIBLE);
        }

        holder.item_fp_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIsCheck().get(position)) {
                    checkList.put(position, false);
                    setIsCheck(checkList);
                } else {
                    checkList.put(position, true);
                    setIsCheck(checkList);
                }
            }
        });
        String bulletinID = "";
        if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
            String bulletinTittle = "";
            String bulletinContent = "";
//            if (qiList.get(position).get("bulletinTittle") != null) { //
//                bulletinTittle = qiList.get(position).get("bulletinTittle").toString();
//            }
            if (qiList.get(position).getBulletinTittle() != null) { //
                bulletinTittle = qiList.get(position).getBulletinTittle();
            }
//            if (qiList.get(position).get("bulletinContent") != null) { //
//                bulletinContent = qiList.get(position).get("bulletinContent").toString();
//            }
            if (qiList.get(position).getBulletinContent() != null) { //
                bulletinContent = qiList.get(position).getBulletinContent();
            }
            holder.tv_fp_name.setText(bulletinTittle);

//            if (qiList.get(position).get("bulletinDatetime") != null) { //时间
//                String time = qiList.get(position).get("bulletinDatetime").toString().substring(0, 10);
//                holder.item_ca_msg_tv_date.setText(time);
//            } else {
//                holder.item_ca_msg_tv_date.setText("");
//            }
            if (qiList.get(position).getBulletinDatetime() != null) { //时间
                String time = qiList.get(position).getBulletinDatetime().substring(0, 10);
                holder.tv_fp_price.setText(time);
            } else {
                holder.tv_fp_price.setText("");
            }
//            if (qiList.get(position).getBulletinID() != null) {
//                bulletinID = qiList.get(position).getBulletinID();
//            }
            if (qiList.get(position).getBulletinID() != 0) {
                bulletinID = qiList.get(position).getBulletinID()+"";
            }
        } else {
            holder.tv_fp_name.setText("");
            holder.tv_fp_price.setText("");
        }
        boolean has = false;
        String[] array = Database.readbulletinid.split(",");
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(bulletinID)) {
                    has = true;
                }
            }
        }

        //上面是队点击进行处理，这里是对显示进行处理
        if (getIsCheck().get(position)) {
            holder.item_fp_checkbox.setChecked(true);
        } else {
            holder.item_fp_checkbox.setChecked(false);
        }

        holder.item_favorite_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qiList != null && qiList.size() != 0 && qiList.get(position) != null) {
//                    if (qiList.get(position).get("bulletinID") != null) {
//                        String bulletinId = qiList.get(position).get("bulletinID").toString();
//                        Log.i("resultString", "bulletinId--------" + bulletinId);
//                        boolean isread = false; //默认未读
//                        String[] array = Database.readbulletinid.split(",");
//                        if (array != null && array.length > 0) {
//                            for (int i = 0; i < array.length; i++) {
//                                if (array[i].equals(bulletinId)) {
//                                    isread = true;
//                                } else {
//                                    isread = false;
//                                }
//                            }
//                        }
//                        if (!isread) {
//                            Database.readbulletinid = Database.readbulletinid + bulletinId + ","; //id 拼接字符串 ,分隔 "id,id2,id3"
//                            Database.notreadbulletinSize --;
//                        }
//                    }
                    if (qiList.get(position).getBulletinID() != 0) { //qiList.get(position).getBulletinID() != null
                        String bulletinId = qiList.get(position).getBulletinID()+"";
                        Log.i("resultString", "bulletinId--------" + bulletinId);
                        boolean isread = false; //默认未读
                        String[] array = Database.readbulletinid.split(",");
                        if (array != null && array.length > 0) {
                            for (int i = 0; i < array.length; i++) {
                                if (array[i].equals(bulletinId)) {
                                    isread = true;
                                } else {
                                    isread = false;
                                }
                            }
                        }
                        if (!isread) {
                            Database.readbulletinid = Database.readbulletinid + bulletinId + ","; //id 拼接字符串 ,分隔 "id,id2,id3"
                            Database.notreadbulletinSize --;
                        }
                    }
                    Intent intent = new Intent(context, BulletinDetailsActivity.class);

                    if (qiList.get(position).getBulletinContent() != null) {
                        intent.putExtra("bulletinContent", qiList.get(position).getBulletinContent());
                    }else {
                        intent.putExtra("bulletinContent", "");
                    }

//                    if (qiList.get(position).getCommunityName() != null) {
//                        intent.putExtra("communityName", qiList.get(position).getCommunityName());
//                    } else {
//                        intent.putExtra("communityName", "");
//                    }

                    if (qiList.get(position).getBulletinTittle() != null) {
                        intent.putExtra("bulletinTittle", qiList.get(position).getBulletinTittle());
                    } else {
                        intent.putExtra("bulletinTittle", "");
                    }

                    if (qiList.get(position).getBulletinDatetime() != null) {
                        intent.putExtra("bulletinDatetime", qiList.get(position).getBulletinDatetime().substring(0, 10));
                    } else {
                        intent.putExtra("bulletinDatetime", "");
                    }
                    intent.putExtra("PushID", 0);
                    context.startActivity(intent);
                    ACache cache=ACache.get(context);
                    cache.put(Constant.bulletinID, Database.readbulletinid);//缓存已读消息
                }

            }
        });
        return convertView;
    }

    class ViewHolder {
        LinearLayout item_favorite_product; //item_favorite_product
        CheckBox item_fp_checkbox;//点击编辑的checkbox
        ImageView iv_fp_pic; //收藏商品图片
        TextView tv_fp_name;//商品名称
        TextView tv_fp_price;//商品价格
    }
}
