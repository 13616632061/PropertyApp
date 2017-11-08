package com.glory.bianyitong.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.glory.bianyitong.bean.AuthAreaInfo;
import com.glory.bianyitong.bean.CommnunityInfo;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.bean.UserInfo;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lucy on 2017/2/7.
 */
public class DataUtils {

    public static void my_community(Context context) { //我的社区
        ACache cache=ACache.get(context);
        String communityID_str = cache.getAsString(Constant.communityID);
        int communityID = 0;
        if (communityID_str != null) {
            communityID = Double.valueOf(communityID_str).intValue();
        }
        if (communityID != 0) { //有缓存
            if (Database.my_community_List != null && Database.my_community_List.size() > 0) {
                for (int i = 0; i < Database.my_community_List.size(); i++) {
//                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).get("communityID") != null
//                            && Database.my_community_List.get(i).get("communityID").toString().equals(communityID)) {
//                        Database.my_community = Database.my_community_List.get(i);
//                    }
                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getCommunityID() != 0
                            && Database.my_community_List.get(i).getCommunityID() == communityID) {
                        Database.my_community = Database.my_community_List.get(i);
                    }
                }
                if (Database.my_community == null) { //没找到 缓存失效
//					if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).get("communityID") != null) {
//						Database.my_community = Database.my_community_List.get(0);
//					}
                    for (int i=0;i<Database.my_community_List.size();i++){
                        if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getCommunityID() != 0) {
                            if (Database.my_community_List.get(i).getApprovalStatus()==1){
                                Database.my_community = Database.my_community_List.get(i);
                            }
                        }
                    }
                }
            }
        } else { //无缓存 获取第一个作为默认小区
            if (Database.my_community_List != null && Database.my_community_List.size() > 0) {
//				if (Database.my_community_List.get(0) != null && Database.my_community_List.get(0).get("communityID") != null) {
//					Database.my_community = Database.my_community_List.get(0);
//				}
                for (int i=0;i<Database.my_community_List.size();i++){
                    if (Database.my_community_List.get(i) != null && Database.my_community_List.get(i).getCommunityID() != 0) {
                        if (Database.my_community_List.get(i).getApprovalStatus()==1){
                            Database.my_community = Database.my_community_List.get(i);
                        }
                    }
                }
            }
        }
        Log.i("resultString", "Database.my_community_List--------" + Database.my_community_List);
        Log.i("resultString", "Database.my_community--------" + Database.my_community);
    }

    /**
     * userCommunityID : 1024
     * userID : 25
     * userName : 里斯
     * communityID : 1
     * communityName : 西丽小区
     * userIDentityID : 1
     * userIDentityName : 业主
     * provinceID : 440000
     * provinceName : 广东省
     * cityID : 440300
     * cityName : 深圳市
     * buildingID : 1
     * buildingName : 1号楼
     * roomID : 2
     * roomName : 102
     * approvalStatus : 1
     * unitID : 1
     * unitName : A单元
     * approvalDate : 2017-01-03T16:14:00
     */
    public static void getUesrCommunity(LoginUserInfo loginUserInfo) {
        if (loginUserInfo==null){
            return;
        }
        Database.my_community_List = null;
        Database.my_community_List = new ArrayList<>();
        for (int i = 0; i < loginUserInfo.getUserCommnunity().size(); i++) {
            if (loginUserInfo.getUserCommnunity().get(i) != null) {
                LoginUserInfo.UserCommnunityBean userCommnunityBean = loginUserInfo.getUserCommnunity().get(i);
                CommnunityInfo commnunityInfo = new CommnunityInfo();
                if (userCommnunityBean.getUserCommunityID() != 0) {
                    commnunityInfo.setUserCommunityID(userCommnunityBean.getUserCommunityID());
                }else {
                    commnunityInfo.setUserCommunityID(0);
                }
                if(!(loginUserInfo==null || loginUserInfo.getUser() ==null )){
                    commnunityInfo.setUserID(loginUserInfo.getUser().getUserID());
                    commnunityInfo.setUserName(loginUserInfo.getUser().getUserName());
                }
//                if(!TextUtils.isEmpty(loginUserInfo.getUser().getUserID()))
//
//
//                if(!TextUtils.isEmpty(loginUserInfo.getUser().getUserName()))
//
//                else {
//                    commnunityInfo.setUserName("");
//                }
                if (userCommnunityBean.getApprovalStatusName() != null) {
                    commnunityInfo.setApprovalStatusName(userCommnunityBean.getApprovalStatusName());
                }else {
                    commnunityInfo.setApprovalStatusName("");
                }

                if (userCommnunityBean.getCommunityID() != 0) {
                    commnunityInfo.setCommunityID(userCommnunityBean.getCommunityID());
                }else {
                    commnunityInfo.setCommunityID(0);
                }
                if (userCommnunityBean.getCommunityName() != null) {
                    commnunityInfo.setCommunityName(userCommnunityBean.getCommunityName());
                }else {
                    commnunityInfo.setCommunityName("");
                }
                if (userCommnunityBean.getUserIDentityID() != 0) {
                    commnunityInfo.setUserIDentityID(userCommnunityBean.getUserIDentityID());
                }else {
                    commnunityInfo.setUserIDentityID(0);
                }
                if (userCommnunityBean.getUserIDentityName() != null) {
                    commnunityInfo.setUserIDentityName(userCommnunityBean.getUserIDentityName());
                }else {
                    commnunityInfo.setUserIDentityName("");
                }
                if (userCommnunityBean.getProvinceID() != 0) {
                    commnunityInfo.setProvinceID(userCommnunityBean.getProvinceID());
                }else {
                    commnunityInfo.setProvinceID(0);
                }
//                if (userCommnunityBean.getProvinceName() != null) {
//                    commnunityInfo.setProvinceName(userCommnunityBean.getProvinceName());
//                }else {
//                    commnunityInfo.setProvinceName("");
//                }
                if (userCommnunityBean.getCityID() != 0) {
                    commnunityInfo.setCityID(userCommnunityBean.getCityID());
                }else {
                    commnunityInfo.setCityID(0);
                }
//                if (userCommnunityBean.getCityName() != null) {
//                    commnunityInfo.setCityName(userCommnunityBean.getCityName());
//                }else {
//                    commnunityInfo.setCityName("");
//                }
                if (userCommnunityBean.getBuildingID() != 0) {
                    commnunityInfo.setBuildingID(userCommnunityBean.getBuildingID());
                }else {
                    commnunityInfo.setBuildingID(0);
                }
                if (userCommnunityBean.getBuildingName() != null) {
                    commnunityInfo.setBuildingName(userCommnunityBean.getBuildingName());
                }else {
                    commnunityInfo.setBuildingName("");
                }
                if (userCommnunityBean.getRoomID() != 0) {
                    commnunityInfo.setRoomID(userCommnunityBean.getRoomID());
                }else {
                    commnunityInfo.setRoomID(0);
                }
                if (userCommnunityBean.getRoomName() != null) {
                    commnunityInfo.setRoomName(userCommnunityBean.getRoomName());
                }else {
                    commnunityInfo.setRoomName("");
                }
                if (userCommnunityBean.getApprovalStatus() != 0) {
                    commnunityInfo.setApprovalStatus(userCommnunityBean.getApprovalStatus());
                }else {
                    commnunityInfo.setApprovalStatus(0);
                }
                if (userCommnunityBean.getUnitID() != 0) {
                    commnunityInfo.setUnitID(userCommnunityBean.getUnitID());
                }else {
                    commnunityInfo.setUnitID(0);
                }
                if (userCommnunityBean.getUnitName() != null) {
                    commnunityInfo.setUnitName(userCommnunityBean.getUnitName());
                }else {
                    commnunityInfo.setUnitName("");
                }
                if (userCommnunityBean.getApprovalDate() != null) {
                    commnunityInfo.setApprovalDate(userCommnunityBean.getApprovalDate());
                }else {
                    commnunityInfo.setApprovalDate("");
                }
                Database.my_community_List.add(commnunityInfo);
            }
        }
    }

    public static void getUesrCommunity2(List<AuthAreaInfo.ListUserCommnunityMappingBean> list) {
        Database.my_community_List = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                AuthAreaInfo.ListUserCommnunityMappingBean userCommnunityBean = list.get(i);
                CommnunityInfo commnunityInfo = new CommnunityInfo();
                if (userCommnunityBean.getUserCommunityID() != 0) {
                    commnunityInfo.setUserCommunityID(userCommnunityBean.getUserCommunityID());
                }else {
                    commnunityInfo.setUserCommunityID(0);
                }
                if (userCommnunityBean.getApprovalStatusName() != null) {
                    commnunityInfo.setApprovalStatusName(userCommnunityBean.getApprovalStatusName());
                }else {
                    commnunityInfo.setApprovalStatusName("");
                }
                if (userCommnunityBean.getUserID() != null) {
                    commnunityInfo.setUserID(userCommnunityBean.getUserID());
                }else {
                    commnunityInfo.setUserID("0");
                }
                if (userCommnunityBean.getUserName() != null) {
                    commnunityInfo.setUserName(userCommnunityBean.getUserName());
                }else {
                    commnunityInfo.setUserName("");
                }
                if (userCommnunityBean.getCommunityID() != 0) {
                    commnunityInfo.setCommunityID(userCommnunityBean.getCommunityID());
                }else {
                    commnunityInfo.setCommunityID(0);
                }
                if (userCommnunityBean.getCommunityName() != null) {
                    commnunityInfo.setCommunityName(userCommnunityBean.getCommunityName());
                }else {
                    commnunityInfo.setCommunityName("");
                }
                if (userCommnunityBean.getUserIDentityID() != 0) {
                    commnunityInfo.setUserIDentityID(userCommnunityBean.getUserIDentityID());
                }else {
                    commnunityInfo.setUserIDentityID(0);
                }
                if (userCommnunityBean.getUserIDentityName() != null) {
                    commnunityInfo.setUserIDentityName(userCommnunityBean.getUserIDentityName());
                }else {
                    commnunityInfo.setUserIDentityName("");
                }
                if (userCommnunityBean.getProvinceID() != 0) {
                    commnunityInfo.setProvinceID(userCommnunityBean.getProvinceID());
                }else {
                    commnunityInfo.setProvinceID(0);
                }
                if (userCommnunityBean.getProvinceName() != null) {
                    commnunityInfo.setProvinceName(userCommnunityBean.getProvinceName());
                }else {
                    commnunityInfo.setProvinceName("");
                }
                if (userCommnunityBean.getCityID() != 0) {
                    commnunityInfo.setCityID(userCommnunityBean.getCityID());
                }else {
                    commnunityInfo.setCityID(0);
                }
                if (userCommnunityBean.getCityName() != null) {
                    commnunityInfo.setCityName(userCommnunityBean.getCityName());
                }else {
                    commnunityInfo.setCityName("");
                }
                if (userCommnunityBean.getBuildingID() != 0) {
                    commnunityInfo.setBuildingID(userCommnunityBean.getBuildingID());
                }else {
                    commnunityInfo.setBuildingID(0);
                }
                if (userCommnunityBean.getBuildingName() != null) {
                    commnunityInfo.setBuildingName(userCommnunityBean.getBuildingName());
                }else {
                    commnunityInfo.setBuildingName("");
                }
                if (userCommnunityBean.getRoomID() != 0) {
                    commnunityInfo.setRoomID(userCommnunityBean.getRoomID());
                }else {
                    commnunityInfo.setRoomID(0);
                }
                if (userCommnunityBean.getRoomName() != null) {
                    commnunityInfo.setRoomName(userCommnunityBean.getRoomName());
                }else {
                    commnunityInfo.setRoomName("");
                }
                if (userCommnunityBean.getApprovalStatus() != 0) {
                    commnunityInfo.setApprovalStatus(userCommnunityBean.getApprovalStatus());
                }else {
                    commnunityInfo.setApprovalStatus(0);
                }
                if (userCommnunityBean.getUnitID() != 0) {
                    commnunityInfo.setUnitID(userCommnunityBean.getUnitID());
                }else {
                    commnunityInfo.setUnitID(0);
                }
                if (userCommnunityBean.getUnitName() != null) {
                    commnunityInfo.setUnitName(userCommnunityBean.getUnitName());
                }else {
                    commnunityInfo.setUnitName("");
                }
                if (userCommnunityBean.getApprovalDate() != null) {
                    commnunityInfo.setApprovalDate(userCommnunityBean.getApprovalDate());
                }else {
                    commnunityInfo.setApprovalDate("");
                }
                Database.my_community_List.add(commnunityInfo);
            }
        }
    }

    public static HashMap<String, Object> toUserMap(){
        HashMap<String, Object> map_user = JsonHelper.fromJson(Database.USER_MAP.toString(), new TypeToken<HashMap<String, Object>>() {
        });

        return map_user;
    }

    public static List<HashMap<String, Object>> tohserMaplist(){
        List<HashMap<String, Object>> commnunity_list = new ArrayList<>();
        for (int i = 0; i < Database.my_community_List.size(); i++) {
            if(Database.my_community_List.get(i)!=null){
                HashMap<String, Object> commnunity = JsonHelper.fromJson(Database.my_community_List.get(i).toString(), new TypeToken<HashMap<String, Object>>() {
                });
                commnunity_list.add(commnunity);
            }
        }

        return commnunity_list;
    }

    public static void saveSharePreToolsKits(Context context){ //缓存用户信息
        HashMap<String, Object> hashMap3 = new HashMap<>();
        hashMap3.put("user", toUserMap());
        hashMap3.put("userCommnunity", tohserMaplist());
        String json = JsonHelper.toJson(hashMap3);
//        SharePreToolsKits.putJsonDataString(context, Constant.user, json); //缓存登录后信息 修改
        ACache cache=ACache.get(context);
        cache.put(Constant.user,json);
    }

    public static void getgoods(LinkedTreeMap<String, Object> map) {
        Database.goodsdetails = new FreashInfo.ListFreshBean();
        if (map.get("freshName") != null) { //商品名称
            Database.goodsdetails.setFreshName(map.get("freshName").toString());
        }
        if (map.get("freshPrice") != null) { //商品价格
            Database.goodsdetails.setFreshPrice(Double.valueOf(map.get("freshPrice").toString()));
        }
        if (map.get("freshTypeName") != null) { // freshTypeName
            Database.goodsdetails.setFreshTypeName(map.get("freshTypeName").toString());
        }
        if (map.get("weight") != null) { //净含量
            Database.goodsdetails.setWeight(Double.valueOf(map.get("weight").toString()));
        }
        if (map.get("packingType") != null) { //包装方式
            Database.goodsdetails.setPackingType(map.get("packingType").toString());
        }
        if (map.get("brandName") != null) { //品牌
            Database.goodsdetails.setBrandName(map.get("brandName").toString());
        }
        if (map.get("originName") != null) { //产地
            Database.goodsdetails.setOriginName(map.get("originName").toString());
        }
        if (map.get("brandTEL") != null) { //厂家联系方式
            Database.goodsdetails.setBrandTEL(map.get("brandTEL").toString());
        }
        if (map.get("shelfLife") != null) { //保质期
            Database.goodsdetails.setShelfLife(map.get("shelfLife").toString());
        }
        if (map.get("freshUrl") != null) { //
            Database.goodsdetails.setFreshUrl(map.get("freshUrl").toString());
        }
        if (map.get("nutritiveValue") != null) { //营养价值
            Database.goodsdetails.setNutritiveValue(map.get("nutritiveValue").toString());
        }
        if (map.get("listfreshPicture") != null) { //生鲜标题图片路径
            ArrayList<LinkedTreeMap<String, Object>> picture_list = (ArrayList<LinkedTreeMap<String, Object>>) map.get("listfreshPicture");
            List<FreashInfo.ListFreshBean.ListfreshPictureBean> picturelist = new ArrayList<>();
            if (picture_list != null && picture_list.size() != 0) {
                for (int i = 0; i < picture_list.size(); i++) {
                    FreashInfo.ListFreshBean.ListfreshPictureBean pic = new FreashInfo.ListFreshBean.ListfreshPictureBean();
                    if (picture_list.get(i) != null && picture_list.get(i).get("picturePath") != null) {
                        pic.setPicturePath(picture_list.get(i).get("picturePath").toString());
                    }
                    picturelist.add(pic);
                }
                Database.goodsdetails.setListfreshPicture(picturelist);
            }
        }
    }

}
