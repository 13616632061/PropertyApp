package com.glory.bianyitong.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 * 广告 实体类
 */
public class AdvertisingInfo2 extends BaseResponseBean{

    private List<ListAdvertisingBean> listAdvertising;
    private List<ListSystemMsgBean> listSystemMsg;

    public List<ListAdvertisingBean> getListAdvertising() {
        return listAdvertising;
    }

    public void setListAdvertising(List<ListAdvertisingBean> listAdvertising) {
        this.listAdvertising = listAdvertising;
    }

    public List<ListSystemMsgBean> getListSystemMsg() {
        return listSystemMsg;
    }

    public void setListSystemMsg(List<ListSystemMsgBean> listSystemMsg) {
        this.listSystemMsg = listSystemMsg;
    }

    public static class ListAdvertisingBean {
        /**
         * advertisingID : 95
         * advertisingType : 1
         * advertisingPicture : https://byt.bytsz.com.cn/images/Advertising/ad1.jpg
         * dvertisingVideo : null
         * advertisingStartDate : 2016-11-20T00:00:00
         * endAdvertisingStartDate : null
         * advertisingEndDate : 2016-12-20T00:00:00
         * endAdvertisingEndDate : null
         * advertisingShowTime : 1
         * communityID : 0
         * advertisingLocation : 1
         */

        private int advertisingID;              //广告唯一ID
        private String advertisingType;       //广告类型 - 1:图片  2:图文  3:视频
        private String advertisingPicture;       //广告图片路径
        private Object dvertisingVideo;       //广告视频路径
        private String advertisingStartDate;       //广告起始日期
        private Object endAdvertisingStartDate;
        private String advertisingEndDate;       //广告结束日期
        private Object endAdvertisingEndDate;
        private int advertisingShowTime;       //广告播放时间段 - 位与运算
        @SerializedName("communityID")
        private int communityIDX;       //社区ID
        private int advertisingLocation;       //广告位置  - 1首页广告  2开门页广告 3生鲜广告
        private String advertisingUrl;

        public String getAdvertisingUrl() {
            return advertisingUrl;
        }

        public void setAdvertisingUrl(String advertisingUrl) {
            this.advertisingUrl = advertisingUrl;
        }

        public int getAdvertisingID() {
            return advertisingID;
        }

        public void setAdvertisingID(int advertisingID) {
            this.advertisingID = advertisingID;
        }

        public String getAdvertisingType() {
            return advertisingType;
        }

        public void setAdvertisingType(String advertisingType) {
            this.advertisingType = advertisingType;
        }

        public String getAdvertisingPicture() {
            return advertisingPicture;
        }

        public void setAdvertisingPicture(String advertisingPicture) {
            this.advertisingPicture = advertisingPicture;
        }

        public Object getDvertisingVideo() {
            return dvertisingVideo;
        }

        public void setDvertisingVideo(Object dvertisingVideo) {
            this.dvertisingVideo = dvertisingVideo;
        }

        public String getAdvertisingStartDate() {
            return advertisingStartDate;
        }

        public void setAdvertisingStartDate(String advertisingStartDate) {
            this.advertisingStartDate = advertisingStartDate;
        }

        public Object getEndAdvertisingStartDate() {
            return endAdvertisingStartDate;
        }

        public void setEndAdvertisingStartDate(Object endAdvertisingStartDate) {
            this.endAdvertisingStartDate = endAdvertisingStartDate;
        }

        public String getAdvertisingEndDate() {
            return advertisingEndDate;
        }

        public void setAdvertisingEndDate(String advertisingEndDate) {
            this.advertisingEndDate = advertisingEndDate;
        }

        public Object getEndAdvertisingEndDate() {
            return endAdvertisingEndDate;
        }

        public void setEndAdvertisingEndDate(Object endAdvertisingEndDate) {
            this.endAdvertisingEndDate = endAdvertisingEndDate;
        }

        public int getAdvertisingShowTime() {
            return advertisingShowTime;
        }

        public void setAdvertisingShowTime(int advertisingShowTime) {
            this.advertisingShowTime = advertisingShowTime;
        }

        public int getCommunityIDX() {
            return communityIDX;
        }

        public void setCommunityIDX(int communityIDX) {
            this.communityIDX = communityIDX;
        }

        public int getAdvertisingLocation() {
            return advertisingLocation;
        }

        public void setAdvertisingLocation(int advertisingLocation) {
            this.advertisingLocation = advertisingLocation;
        }
    }

    public static class ListSystemMsgBean {
        /**
         * messageID : 1
         * communityID : 0
         * messageTime : 2016-12-20T00:00:00
         * messageTitle : 系统消息
         * messageContext : 一期
         * userID : 0
         * messageType : 1
         */

        private int messageID;        //系统消息唯一ID
        @SerializedName("communityID")
        private int communityIDX;  //社区ID
        private String messageTime;  //消息时间
        private String messageTitle;  //消息标题
        private String messageContext;  //消息内容
        @SerializedName("userID")
        private int userIDX;  //用户ID
        private int messageType;  //消息类型 - 1系统消息 2社区消息 3个人消息

        public int getMessageID() {
            return messageID;
        }

        public void setMessageID(int messageID) {
            this.messageID = messageID;
        }

        public int getCommunityIDX() {
            return communityIDX;
        }

        public void setCommunityIDX(int communityIDX) {
            this.communityIDX = communityIDX;
        }

        public String getMessageTime() {
            return messageTime;
        }

        public void setMessageTime(String messageTime) {
            this.messageTime = messageTime;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public String getMessageContext() {
            return messageContext;
        }

        public void setMessageContext(String messageContext) {
            this.messageContext = messageContext;
        }

        public int getUserIDX() {
            return userIDX;
        }

        public void setUserIDX(int userIDX) {
            this.userIDX = userIDX;
        }

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }
    }
}
