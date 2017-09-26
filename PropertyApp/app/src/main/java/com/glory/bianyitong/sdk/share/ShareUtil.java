package com.glory.bianyitong.sdk.share;

import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.constants.Database;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareUtil {

    /**
     * 2016330
     *
     * @param context
     * @param mShareContent
     * @param --Picurl
     * @param url
     */
    public static void initOnekeyShare(final Context context, final String titile, final String mShareContent, final String url, final String logopath) {// final String Picurl
        // 获取缓存微信分享的秘钥key值
        String wxAppId = Constant.AppID;
        // 获取缓存微信分享的秘钥key值
        String wxAppSecret = Constant.AppSecret;//动态获取
        Log.i("resultString", "wxAppSecret------------" + wxAppSecret);
        // ShareSDK分享初始化
        // ShareSDK.initSDK(context);
//		Log.i("resultString", "resultString 检测微信支付== " + Database.WX_APP_ID+"\t\t\t"+Database.WX_APP_SECRETS);
        // 第二个参数为：你的应用在Sharesdk注册时返回的AppKey。
        ShareSDK.initSDK(context, "1a11d52921447");
        /*
		 * 代码配置注册信息.
		 */
        if (wxAppId != null && !wxAppId.equals("") && wxAppId.length() != 0
                && wxAppSecret != null && !wxAppSecret.equals("") && wxAppSecret.length() != 0) {
            // 微信分享的配置注册信息.
            HashMap<String, Object> wxMap = new HashMap<String, Object>();
            wxMap.put("Id", "4");
            wxMap.put("SortId", "4");
            wxMap.put("AppId", wxAppId);
            wxMap.put("AppSecret", wxAppSecret);
            wxMap.put("BypassApproval", "false");
            wxMap.put("Enable", "true");
            ShareSDK.setPlatformDevInfo(Wechat.NAME, wxMap);
            // 微信朋友圈分享的配置注册信息.
            HashMap<String, Object> wxMomentsMap = new HashMap<String, Object>();
            wxMomentsMap.put("Id", "5");
            wxMomentsMap.put("SortId", "5");
            wxMomentsMap.put("AppId", wxAppId);
            wxMomentsMap.put("AppSecret", wxAppSecret);
            wxMomentsMap.put("BypassApproval", "false");
            wxMomentsMap.put("Enable", "true");
            ShareSDK.setPlatformDevInfo(WechatMoments.NAME, wxMomentsMap);
            // 微信收藏的配置注册信息.
            HashMap<String, Object> wxFavoriteMap = new HashMap<String, Object>();
            wxFavoriteMap.put("Id", "6");
            wxFavoriteMap.put("SortId", "6");
            wxFavoriteMap.put("AppId", wxAppId);
            wxFavoriteMap.put("AppSecret", wxAppSecret);
            wxFavoriteMap.put("Enable", "false");
            ShareSDK.setPlatformDevInfo(WechatFavorite.NAME, wxFavoriteMap);
        }

        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(context.getString(R.string.app_name));
        oks.setTitle(titile);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mShareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(logopath);// 确保SDcard下面存在此张图片
        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段
//		oks.setImageUrl(Picurl);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                // 改写twitter分享内容中的text字段，否则会超长，
                // 因为twitter会将图片地址当作文本的一部分去计算长度
                if ("Twitter".equals(platform.getName())) {
                    paramsToShare.setText(mShareContent);
                }
            }
        });
        // 启动分享GUI
        oks.show(context);
    }


    @SuppressWarnings("deprecation")
    public static String getRealPathFromURI(Context context) {
        Resources r = context.getApplicationContext().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.drawable.logo_5) + "/"
                + r.getResourceTypeName(R.drawable.logo_5) + "/"
                + r.getResourceEntryName(R.drawable.logo_5));

        String res = null;
        String[] imgs = {MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
        Cursor cursor = Database.currentActivity.managedQuery(uri, imgs, null, null, null);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        res = cursor.getString(index);
        return res;
    }

    /**
     * 演示调用ShareSDK执行分享
     *
     * @param context
     * @param platformToShare  指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit  是否显示编辑页
     */
    public static void showShare(Context context, String platformToShare, boolean showContentEdit,
                                 final String titile, final String mShareContent, final String url, final String logopath) {
        // 获取缓存微信分享的秘钥key值
        String wxAppId = Constant.AppID;
        // 获取缓存微信分享的秘钥key值
        String wxAppSecret = Constant.AppSecret;//动态获取
//        ShareSDK.initSDK(context, "1a11d52921447");
        if (wxAppId != null && !wxAppId.equals("") && wxAppId.length() != 0
                && wxAppSecret != null && !wxAppSecret.equals("") && wxAppSecret.length() != 0) {
            // 微信分享的配置注册信息.
            HashMap<String, Object> wxMap = new HashMap<String, Object>();
            wxMap.put("Id", "4");
            wxMap.put("SortId", "4");
            wxMap.put("AppId", wxAppId);
            wxMap.put("AppSecret", wxAppSecret);
            wxMap.put("BypassApproval", "false");
            wxMap.put("Enable", "true");
            ShareSDK.setPlatformDevInfo(Wechat.NAME, wxMap);
            // 微信朋友圈分享的配置注册信息.
            HashMap<String, Object> wxMomentsMap = new HashMap<String, Object>();
            wxMomentsMap.put("Id", "5");
            wxMomentsMap.put("SortId", "5");
            wxMomentsMap.put("AppId", wxAppId);
            wxMomentsMap.put("AppSecret", wxAppSecret);
            wxMomentsMap.put("BypassApproval", "false");
            wxMomentsMap.put("Enable", "true");
            ShareSDK.setPlatformDevInfo(WechatMoments.NAME, wxMomentsMap);
        }
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
//        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
//        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(context.getString(R.string.app_name));
        oks.setTitle(titile);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mShareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(logopath);// 确保SDcard下面存在此张图片
        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段
//		oks.setImageUrl(Picurl);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                // 改写twitter分享内容中的text字段，否则会超长，
                // 因为twitter会将图片地址当作文本的一部分去计算长度
                if ("Twitter".equals(platform.getName())) {
                    paramsToShare.setText(mShareContent);
                }
            }
        });

        // 启动分享
        oks.show(context);
    }

    /**
     * 演示调用ShareSDK执行分享
     *
     * @param context
     * @param platformToShare  指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
     * @param showContentEdit  是否显示编辑页
     */
    public static void showShareOne(Context context, String platformToShare, boolean showContentEdit,
                                 final String titile, final String mShareContent, final String url, final String logopath) {
        // 获取缓存微信分享的秘钥key值
        String wxAppId = Constant.AppID;
        // 获取缓存微信分享的秘钥key值
        String wxAppSecret = Constant.AppSecret;//动态获取
//        ShareSDK.initSDK(context, "1a11d52921447");
        if (wxAppId != null && !wxAppId.equals("") && wxAppId.length() != 0
                && wxAppSecret != null && !wxAppSecret.equals("") && wxAppSecret.length() != 0) {
            // 微信分享的配置注册信息.
            HashMap<String, Object> wxMap = new HashMap<String, Object>();
            wxMap.put("Id", "4");
            wxMap.put("SortId", "4");
            wxMap.put("AppId", wxAppId);
            wxMap.put("AppSecret", wxAppSecret);
            wxMap.put("BypassApproval", "false");
            wxMap.put("Enable", "true");
            ShareSDK.setPlatformDevInfo(Wechat.NAME, wxMap);
            // 微信朋友圈分享的配置注册信息.
            HashMap<String, Object> wxMomentsMap = new HashMap<String, Object>();
            wxMomentsMap.put("Id", "5");
            wxMomentsMap.put("SortId", "5");
            wxMomentsMap.put("AppId", wxAppId);
            wxMomentsMap.put("AppSecret", wxAppSecret);
            wxMomentsMap.put("BypassApproval", "false");
            wxMomentsMap.put("Enable", "false");
            ShareSDK.setPlatformDevInfo(WechatMoments.NAME, wxMomentsMap);
        }
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
//        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
//        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(context.getString(R.string.app_name));
        oks.setTitle(titile);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mShareContent);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(logopath);// 确保SDcard下面存在此张图片
        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段
//		oks.setImageUrl(Picurl);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                // 改写twitter分享内容中的text字段，否则会超长，
                // 因为twitter会将图片地址当作文本的一部分去计算长度
                if ("Twitter".equals(platform.getName())) {
                    paramsToShare.setText(mShareContent);
                }
            }
        });

        // 启动分享
        oks.show(context);
    }
}
