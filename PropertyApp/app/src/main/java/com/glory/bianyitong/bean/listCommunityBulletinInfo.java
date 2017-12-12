package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/14.
 * 社区公告 实体类
 */
public class listCommunityBulletinInfo extends ResponseBaseInfo{

    private List<ListCommunityBulletinBean> listCommunityBulletin;

    public List<ListCommunityBulletinBean> getListCommunityBulletin() {
        return listCommunityBulletin;
    }

    public void setListCommunityBulletin(List<ListCommunityBulletinBean> listCommunityBulletin) {
        this.listCommunityBulletin = listCommunityBulletin;
    }

    public static class ListCommunityBulletinBean {
        /**
         * bulletinID : 1
         * communityID : 1
         * bulletinTittle : 公告
         * bulletinDatetime : 2016-10-10T00:00:00
         * endBulletinDatetime : null
         * bulletinDeadline : 2016-10-10T00:00:00
         * endBulletinDeadline : null
         * bulletinContent : 行解决用水
         */

        private int bulletinID;         //公告唯一ID
        private int communityID;        //社区ID
        private String bulletinTittle;   //公告标题
        private String bulletinDatetime;   //公告内容起始日期
        private Object endBulletinDatetime;   //
        private String bulletinDeadline;   //公告截止日期
        private Object endBulletinDeadline;   //
        private String bulletinContent;   //公告内容
        private String createDate;//公告创建日期


        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getBulletinID() {
            return bulletinID;
        }

        public void setBulletinID(int bulletinID) {
            this.bulletinID = bulletinID;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getBulletinTittle() {
            return bulletinTittle;
        }

        public void setBulletinTittle(String bulletinTittle) {
            this.bulletinTittle = bulletinTittle;
        }

        public String getBulletinDatetime() {
            return bulletinDatetime;
        }

        public void setBulletinDatetime(String bulletinDatetime) {
            this.bulletinDatetime = bulletinDatetime;
        }

        public Object getEndBulletinDatetime() {
            return endBulletinDatetime;
        }

        public void setEndBulletinDatetime(Object endBulletinDatetime) {
            this.endBulletinDatetime = endBulletinDatetime;
        }

        public String getBulletinDeadline() {
            return bulletinDeadline;
        }

        public void setBulletinDeadline(String bulletinDeadline) {
            this.bulletinDeadline = bulletinDeadline;
        }

        public Object getEndBulletinDeadline() {
            return endBulletinDeadline;
        }

        public void setEndBulletinDeadline(Object endBulletinDeadline) {
            this.endBulletinDeadline = endBulletinDeadline;
        }

        public String getBulletinContent() {
            return bulletinContent;
        }

        public void setBulletinContent(String bulletinContent) {
            this.bulletinContent = bulletinContent;
        }
    }
}
