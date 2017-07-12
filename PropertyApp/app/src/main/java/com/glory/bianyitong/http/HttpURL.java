package com.glory.bianyitong.http;

/**
 * Created by Administrator on 2016/7/1.
 * URL
 */
public class HttpURL {
    //外网
//    public static String HTTP_LOGIN = "https://www.pgagolf.cn:4432/WebApi/Post";
//    public static String HTTP_LOGIN_AREA = "https://www.pgagolf.cn:4432";

    public static String HTTP_LOGIN = "https://byt.bytsz.com.cn/WebApi/Post";
    public static String HTTP_LOGIN_AREA = "https://byt.bytsz.com.cn";

    //请求地址
//    public static String HTTP_LOGIN = "http://192.168.26.114:1755/WebApi/Post";
//    public static String HTTP_LOGIN_AREA = "http://192.168.26.114:1755";

//    public static String HTTP_LOGIN = "http://192.168.26.242:1755/WebApi/Post";
    //请求地址
//    public static String HTTP_LOGIN2 = "http://192.168.26.242:1755/Api/Post";

//    public static String HTTP_LOGIN2 = "http://m.51maimai.com.cn/";
//
//    public static String HTTP_LOGIN3 = "http://ad.51maimai.com.cn/";

    public static String HTTP_NEW_URL = "http://192.168.1.113:1756";


    /**
     * 首页
     */
    //系统消息查询
    public static String HTTP_POST_GET_MESSAGE="/ApiSystemMsg/Query";

    /**
     * 位置信息
     */
    public static String HTTP_POST_MY_LOCAL="/ApiArea/Query";//我的位置查询
    public static String HTTP_POST_LOCAL_INFO="/ApiArea/FuzzyQuery";//区域信息查询


    /**
     * 我的页面
     */
    //添加授权
    public static String HTTP_POST_LOCKMAPPING_ADD="/ApiUserLockMapping/Add";
    //编辑授权
    public static String HTTP_POST_LOCKMAPPING_EDIT="/ApiUserLockMapping/Edit";
    //查询授权
    public static String HTTP_POST_LOCKMAPPING_QUERY="/ApiUserLockMapping/Query";
    //删除授权
    public static String HTTP_POST_LOCKMAPPING_DELETE="/ApiUserLockMapping/Delete";
    //意见反馈
    public static String HTTP_POST_FEEDBACK_ADD="/ApiFeedback/Add";
    //编辑用户信息
    public static String HTTP_POST_MY_EDITUSERINFO="/ApiUser/Edit";
    //获取分享信息
    public static String HTTP_POST_MY_GETSHARE="/ApiSetting/GetShare";
    //获取发布内容
    public static String HTTP_POST_MY_GETSEND_INFO="/ApiNeighborhood/Query";
    //根据用户ID查询用户信息
    public static String HTTP_POST_QUERY_USER_INFO="/ApiUser/Query";

    //我的收货地址
    public static String HTTP_POST_QUERY_ADDRESS="/ApiShopAddress/Query";
    //添加收货地址
    public static String HTTP_POST_ADD_ADDRESS="/ApiShopAddress/Add";
    //附近生鲜柜
    public static String HTTP_POST_QUERY_ADDRESS_EXPRESS_BAR_LOCAL="/ApiShopAddress/OtherOne";
    //搜索生鲜柜
    public static String HTTP_POST_QUERY_ADDRESS_EXPRESS_BAR_SEARCH="/ApiShopAddress/FuzzyQuery";



    /**
     * 社区
     */
    //附近社区
    public static String HTTP_POST_LOCAL_AREA="/ApiCommunity/QueryNearbyCommunity";
    //根据名称查询小区
    public static String HTTP_POST_LOCAL_AREA_QUERY="/ApiCommunity/FuzzyQuery";
    //楼栋查询
    public static String HTTP_POST_LOCAL_AREA_QUERY_BUILD="/ApiCommunityBuilding/Query";
    //单元查询
    public static String HTTP_POST_LOCAL_AREA_QUERY_UNIT="/ApiCommunityUnit/Query";
    //房间号查询
    public static String HTTP_POST_LOCAL_AREA_QUERY_ROOM="/ApiCommunityRoom/Query";
    //申请社区
    public static String HTTP_POST_LOCAL_AREA_ADD_COMMNUNITY="/ApiUserCommnunity/Add";
    //社区公告
    public static String HTTP_POST_LOCAL_AREA_QUERY_AREA_NOTICE="/ApiCommunityBulletin/Query";
    //我的社区查询
    public static String HTTP_POST_LOCAL_AREA_QUERY_AREA="/ApiUserCommnunity/Query";
    //查询我的钥匙
    public static String HTTP_POST_LOCAL_KEY_MANAGER="/ApiUserKey/Query";
    //我的钥匙排序
    public static String HTTP_POST_LOCAL_KEY_SORT="/ApiUserKey/SortUserKey";
    //开锁
    public static String HTTP_POST_OPEN_LOCK="/ApiOpenLock/Open";
    //广告查询
    public static String HTTP_POST_GET_AD="/ApiAdvertising/Query";

    /**
     * 物业管家
     */
    //物业管家查询
    public static String HTTP_POST_OWNER_MANAGER="/ApiHousekeeper/Query";

    /**
     * 便民黄页
     */
    //黄页分类查询
    public static String HTTP_POST_YELLOWPAGE_QUERY="/ApiYellowPageGroup/Query";
    //黄页数据列表
    public static String HTTP_POST_YELLOWITEM_QUERY="/ApiYellowPage/Query";



    /**
     * 投诉建议
     */
    //投诉类型
    public static String HTTP_POST_COMPLAINTS_TYPE="/ApiComplaintsType/Query";
    //新增投诉
    public static String HTTP_POST_COMPLAINTS_ADD="/ApiComplaints/Add";


    /**
     * 配置信息
     */
    //获取阿里云密钥
    public static String HTTP_POST_GET_ALIYUN="/ApiSetting/SelectOSS";


    /**
     * 近邻
     */
    //近邻查询
    public static String HTTP_POST_FRIEND_QUERY="/ApiNeighborhood/Query";
    //近邻发布
    public static String HTTP_POST_FRIEND_ADD="/ApiNeighborhood/Add";
    //近邻详情
    public static String HTTP_POST_FRIEND_DETAIL="/ApiNeighborhood/Query";
    //近邻点赞
    public static String HTTP_POST_FRIEND_LIKE="/ApiNeighborhoodLike/Add";
    //近邻点赞取消
    public static String HTTP_POST_FRIEND_LIKE_CANCEL="/ApiNeighborhoodLike/Delete";
    //近邻评论
    public static String HTTP_POST_FRIEND_COMMENT_ADD="/ApiNeighborhoodComment/Add";
    //举报
    public static String HTTP_POST_FRIEND_COMMENT_REPORT="/ApiReport/Add";


    /**
     * 生鲜
     */
    public static String HTTP_POST_FRESH_TYPE="/ApiFreshType/Query";//生鲜类型
    public static String HTTP_POST_FRESH_QUERY_TAG="/ApiFresh/QueryTag";//获取热门标签
    public static String HTTP_POST_FRESH_QUERY_SEARCH="/ApiFresh/FuzzyQuery";//搜索框搜索生鲜
    public static String HTTP_POST_FRESH_QUERY_DETAIL="/ApiFresh/Query";//查询生鲜详情
    public static String HTTP_POST_SHOP_QUERY_INFO="/ApiFreshMerchant/Query";//查询商户信息
    public static String HTTP_POST_SHOP_QUERY_LOCAL="/ApiArea/FuzzyQuery";//地区搜索
    public static String HTTP_POST_SHOP_QUERY_GOOD_SHOP="/ApiFresh/QueryFreshChoice";//生鲜精品

    /**
     * 购物车
     */
    public static String HTTP_POST_SHOPPINGCART_ADD="/ApiShoppingCart/Add";//添加
    public static String HTTP_POST_SHOPPINGCART_QUERY="/ApiShoppingCart/Query";//查询
    public static String HTTP_POST_SHOPPINGCART_DELETE="/ApiShoppingCart/Delete";//删除
    public static String HTTP_POST_SHOPPINGCART_EDIT="/ApiShoppingCart/Edit";//购物车编辑

    /**
     * 收藏
     */
    public static String HTTP_POST_COLLECTION_ADD="/ApiFreshCollection/Add";//添加
    public static String HTTP_POST_COLLECTION_DELETE="/ApiFreshCollection/Delete";//删除
}
