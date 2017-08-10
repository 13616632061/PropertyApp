package com.glory.bianyitong.constants;

import com.glory.bianyitong.util.ActivityUtils;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Constant {
       public static final String bulletinID = "bulletinID"; //缓存已读公告的标识

       public static final String messageID = "messageID"; //缓存已读消息的标识

       public static final String communityID = "communityID"; //缓存选择社区的id

       public static final String user = "user_json"; //缓存用户登录后信息
       public static final String userToken = "user_token"; //缓存用户登录后信息

       public static final String SEARCH = "search";// PreferenceHelper中搜索记录key值

       public static final String VERSIONCODE = ActivityUtils.getVersionCode();// 版本号

       public static final String VERSIONNAME = ActivityUtils.getVersionName();// 版本名称

       public static final String AppID = "wxd5296e8e8d5d33e4"; //AppID

       public static String AppSecret = ""; //那你们很棒哦

//       public static final String AppSecret = "b6488f57140494c9d10ad8c6ff3cf27e"; //AppSecret


       public static String logo_path = ""; //logo在手机上的地址




       public static class ORDER_STATUS{
               //0 待支付 1已支付 2已发货 3已到货 4已收货 5已评价 -1退款中 -2已退款 -3已取消
               public static final int STATUS_PAY_ALL=-5;
              public static final int STATUS_PAY_WAIT=0;
              public static final int STATUS_PAY_FINSH=1;
              public static final int STATUS_PAY_SEND=2;
              public static final int STATUS_PAY_LOCAL=3;
              public static final int STATUS_PAY_GOODSRECEPIT=4;
              public static final int STATUS_PAY_COMMENT=5;
              public static final int STATUS_PAY_REFUNDING=-1;
              public static final int STATUS_PAY_REFUNDED=-2;
              public static final int STATUS_PAY_EXIT=-3;
       }
}



