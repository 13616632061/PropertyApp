package com.glory.bianyitong.constants;

import android.app.Activity;

import com.glory.bianyitong.bean.CommnunityInfo;
import com.glory.bianyitong.bean.UserLockInfo;
import com.glory.bianyitong.bean.entity.response.ResponseFriendDetail;
import com.glory.bianyitong.bean.entity.response.ResponseQueryBuild;
import com.glory.bianyitong.bean.entity.response.ResponseQueryRoom;
import com.glory.bianyitong.bean.entity.response.ResponseQueryUnit;
import com.glory.bianyitong.bean.listCommunityBulletinInfo;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.bean.LoginUserInfo;
import com.glory.bianyitong.bean.YellowPageAllInfo;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by lucy on 2016/11/18.
 */
public class Database {
    public static int NEW_VERSION = 0;
    public static Activity currentActivity = null; // 当前页面

    public static String contact_phone_name = "";//选择的联系人 电话  姓名
    public static String suggest_type_name_id = "";//建议类型

    public static String readbulletinid = "";// 已读的公告id
    public static String readmessageid = "";// 已读的消息id

    public static int notreadbulletinSize = 0;// 未读的公告数量
    public static int notreadmessageidSize = 0;// 未读的消息数量

    public static String login_return = null;// 登录后信息
//    public static LinkedTreeMap<String, Object> USER_MAP = null;// 用户信息
//    public static UserInfo.UserBean USER_MAP = null;// 用户信息
    public static LoginUserInfo.UserBean USER_MAP = null;// 用户信息
    public static String accessToken="";
//    public static ArrayList<LinkedTreeMap<String, Object>> my_community_List = null;// 小区列表
//    public static LinkedTreeMap<String, Object> my_community = null;// 我的小区

    public static List<CommnunityInfo> my_community_List = null;// 小区列表
    public static CommnunityInfo my_community = null;// 我的小区

//    public static ArrayList<LinkedTreeMap<String, Object>> list_communityBulletin = null;// 社区公告
    public static List<listCommunityBulletinInfo.ListCommunityBulletinBean> list_communityBulletin = null;// 社区公告

//    public static ArrayList<LinkedTreeMap<String, Object>> list_yellow = null;//黄页 电话列表数据
    public static List<YellowPageAllInfo> list_yellow = null;//黄页 电话列表数据

    public static ArrayList<LinkedTreeMap<String, Object>> list_news = null;// 新闻列表

    public static List<ResponseFriendDetail.ListNeighborhoodBean>  list_neighbour = null;//近邻列表

    public static ArrayList<LinkedTreeMap<String, Object>> list_myRelease = null;//我的发布列表

    public static List<ArrayList<LinkedTreeMap<String, Object>>> alltaglist; //生鲜类型标签数据

//    public static LinkedTreeMap<String, Object> goodsdetails = null;//生鲜商品
    public static FreashInfo.ListFreshBean goodsdetails = null;//生鲜商品

    public static boolean isAddComment = false; //是否评论 (用于刷新判断)

    public static boolean islogin = false; //登录刷新

    public static boolean isAddarea = false;

    public static ArrayList<LinkedTreeMap<String, Object>> list_AllCity; //所有城市列表
    public static ArrayList<LinkedTreeMap<String, Object>> province_list; //省份列表
    public static ArrayList<LinkedTreeMap<String, Object>> city_list; //城市列表
    public static ArrayList<LinkedTreeMap<String, Object>> district_list; //区列表
    public static String str_province = ""; //省
    public static int id_province = 0; //省 id
    public static String str_city = ""; //市
    public static int id_city = 0; //市 id
    public static String str_district = ""; //区
    public static int id_district = 0; //区 id
    public static int district_id = 0; //地区 id

    public static ArrayList<LinkedTreeMap<String, Object>> list_community; //城市内的小区
    public static String communityName = ""; //小区名称
    public static int communityID = 0; //小区 id

    public static List<ResponseQueryBuild.ListCommunityBuildingBean> list_CommunityBuilding; //小区内的楼栋
    public static String buildingName = ""; //楼栋名称
    public static int buildingID = 0; //楼栋 id

    public static List<ResponseQueryUnit.ListCommunityUnitBean> listCommunityUnit; //小区内的楼栋 单元
    public static String unitName = ""; //单元名称
    public static int unitID = 0; //单元 id

    public static List<ResponseQueryRoom.ListCommunityRoomBean> listCommunityRoom; //小区内的楼栋 单元 房号
    public static String roomName = ""; //房号名称
    public static int roomID = 0; //房号 id

    public static UserLockInfo.ListUserLockMappingBean awardpeople; //授权用户

    //当前的头像名称
    public static String portrait = null;

    public static HashMap<String, Object> AppShare; //应用分享

    public static String registrationId = "";//设备号

}
