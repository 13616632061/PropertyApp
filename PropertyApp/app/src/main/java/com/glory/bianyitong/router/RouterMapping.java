package com.glory.bianyitong.router;

/**
 * Created by Administrator on 2017/6/27.
 */

public class RouterMapping  {
    //拦截器
    public static final String INTERCEPTOR_LOGIN="UserInterceptor";//登录
    //路由
    public static final String ROUTER_ACTIVITY_LOGIN="ROUTER_ACTIVITY_LOGIN";//登录

    public static final String ROUTER_ACTIVITY_MAIN="ROUTER_ACTIVITY_MAIN";//首页

    public static final String ROUTER_ACTIVITY_INDEX="ROUTER_ACTIVITY_INDEX";//首页fragment


    /**
     * 快递
     */
    public static final String ROUTER_ACTIVITY_PICKUP="ROUTER_ACTIVITY_PICKUP";//取件

    /**
     * 授权
     */
    public static final String ROUTER_ACTIVITY_AddAWARD="ROUTER_ACTIVITY_AddAWARD";//授权添加


    /**
     * 我的
     */
    public static final String ROUTER_ACTIVITY_PERSION="ROUTER_ACTIVITY_PERSION";//个人资料
    public static final String ROUTER_ACTIVITY_AUTHAREA="ROUTER_ACTIVITY_AUTHAREA";//认证小区
    public static final String ROUTER_ACTIVITY_AAWARD_MANAGER="ROUTER_ACTIVITY_AAWARD_MANAGER";//授权管理
    public static final String ROUTER_ACTIVITY_AAWARD_MY_RELEASE="ROUTER_ACTIVITY_AAWARD_MY_RELEASE";//我的发布
    public static final String ROUTER_ACTIVITY_SETTING="ROUTER_ACTIVITY_SETTING";//设置
    public static final String ROUTER_ACTIVITY_FEEDBACK="ROUTER_ACTIVITY_FEEDBACK";//意见反馈
    public static final String ROUTER_ACTIVITY_FAMILYMANAGEMENT="ROUTER_ACTIVITY_FAMILYMANAGEMENT";//家庭管理

    /**
     * 个人信息
     */
    public static final String ROUTER_ACTIVITY_MY_UPDATE_NAME="ROUTER_ACTIVITY_MY_UPDATE_NAME";//修改昵称
    public static final String ROUTER_ACTIVITY_MY_UPDATE_DESC="ROUTER_ACTIVITY_MY_UPDATE_DESC";//修改描述
    public static final String ROUTER_ACTIVITY_MY_ADDRESS_MANAGER="ROUTER_ACTIVITY_MY_ADDRESS_MANAGER";//收货地址管理
    public static final String ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_ADD="ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_ADD";//绑定收货柜
    public static final String ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_MAP="ROUTER_ACTIVITY_MY_ADDRESS_EXPRESS_MAP";//绑定收货柜
    public static final String ROUTER_ACTIVITY_MY_ADDRESS_ADD="ROUTER_ACTIVITY_MY_ADDRESS_ADD";//编辑和添加地址

    /**
     * 小区信息
     *
     */
    public static final String ROUTER_ACTIVITY_AREA_ADD="ROUTER_ACTIVITY_AREA_ADD";//添加小区
    public static final String ROUTER_ACTIVITY_AREA_LIST="ROUTER_ACTIVITY_AREA_LIST";//小区列表


    /**
     * 近邻
     */
    public static final String ROUTER_ACTIVITY_FRIEND_DETAIL="ROUTER_ACTIVITY_FRIEND_DETAIL";//近邻详情
    public static final String ROUTER_ACTIVITY_FRIEND_COMMENT="ROUTER_ACTIVITY_FRIEND_COMMENT";//添加评论
    public static final String ROUTER_ACTIVITY_FRIEND_USER_INFO="ROUTER_ACTIVITY_FRIEND_USER_INFO";//个人主页


    /**
     * 订单
     */
    public static final String ROUTER_ACTIVITY_ORDER="ROUTER_ACTIVITY_ORDER";//订单列表
    public static final String ROUTER_ACTIVITY_ORDER_FIRM="ROUTER_ACTIVITY_ORDER_FIRM";//提交订单
    public static final String ROUTER_ACTIVITY_ORDER_PAY="ROUTER_ACTIVITY_ORDER_PAY";//订单支付
    public static final String ROUTER_ACTIVITY_ORDER_COMMENT="ROUTER_ACTIVITY_ORDER_COMMENT";//订单评论
    public static final String ROUTER_ACTIVITY_ALLORDER_COMMENT="ROUTER_ACTIVITY_ALLORDER_COMMENT";//查看全部评论

    /**
     * 生鲜
     */
    public static final String ROUTER_ACTIVITY_PRODUCT_DETAIL="ROUTER_ACTIVITY_PRODUCT_DETAIL";//商品详情
    public static final String ROUTER_ACTIVITY_PRODUCT_SEARCH="ROUTER_ACTIVITY_PRODUCT_SEARCH";//搜索商品
    public static final String ROUTER_ACTIVITY_PRODUCT_SELECT_LOCAL="ROUTER_ACTIVITY_PRODUCT_SELECT_LOCAL";//选择地区

    /**
     * 购物车
     */
    public static final String ROUTER_ACTIVITY_SHOPPINGCART="ROUTER_ACTIVITY_SHOPPINGCART";//购物车


    /**
     * 优惠券
     */
    public static final String ROUTER_ACTIVITY_COUPON_LIST="ROUTER_ACTIVITY_COUPON_LIST";//优惠券列表
    /**
     * 收藏
     */
    public static final String ROUTER_ACTIVITY_COLLECTION_LIST="ROUTER_ACTIVITY_COLLECTION_LIST";//收藏
}
